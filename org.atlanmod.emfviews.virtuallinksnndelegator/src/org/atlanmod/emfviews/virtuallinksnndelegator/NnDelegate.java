package org.atlanmod.emfviews.virtuallinksnndelegator;

//Main Java imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

//EMF related imports
import org.atlanmod.emfviews.virtuallinks.delegator.IVirtualLinksDelegate;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;

//Import to deal with JSON
import com.google.gson.Gson;
import com.google.gson.JsonPrimitive;

public class NnDelegate implements IVirtualLinksDelegate {

	private Map<String, Boolean> compiledRules;

	@Override
	public void init(URI linksDslURI, Map<String, Resource> inputModels) {

		// For the Match rules file
		File file;

		// For the API
		URL url;
		HttpURLConnection connection;

		System.out.println(linksDslURI);
		System.out.println(inputModels);
		Map<String, String> ruleList = new HashMap<String, String>();
		compiledRules = new HashMap<>();

		if (linksDslURI.isPlatform()) {
			// Find the system path for the file from the workspace URI
			IContainer wsroot = EcorePlugin.getWorkspaceRoot();
			IFile ifile = wsroot.getFile(new Path(linksDslURI.toPlatformString(true)));
			file = new File(ifile.getLocationURI());
		} else {
			file = new File(linksDslURI.toFileString());
		}

		// parse the file to get the rules and parameters
		try {

			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line;

			while ((line = br.readLine()) != null) {
				String[] ruleNameAndParameters = line.split(":=", 2);
				ruleList.put(ruleNameAndParameters[0], ruleNameAndParameters[1]);
				compiledRules.put(ruleNameAndParameters[0], false);
			}
			fr.close();
		} catch (Exception ex) {
			throw new RuntimeException("Error in parsing the NN file", ex);
		}

		Gson gson = new Gson();

		try {
			url = new URL("http://172.26.76.72:8080/process");
			connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");

			Map<String, List<String>> modelsList = new HashMap<String, List<String>>();

			// Get the models' info
			for (Entry<String, Resource> e : inputModels.entrySet()) {
				String name = e.getKey();
				EList<EObject> modelElements = e.getValue().getContents();
				List<String> modelTitles = new ArrayList<String>();
				for (EObject obj : modelElements) {
					String title;
					EStructuralFeature feature = ((EObject) obj).eClass().getEStructuralFeature("title");
					if (feature != null) {
						EObject titleElement = (EObject) obj.eGet(feature);
						title = obj.eClass().getName() + "_" + titleElement.eClass().getName();
					} else {
						title = obj.eClass().getName();
					}
					modelTitles.add(title);
				}

				modelsList.put(name, modelTitles);
			}

			@SuppressWarnings("rawtypes")
			Map<String, Map> finalPayload = new HashMap<String, Map>();
			finalPayload.put("rules", ruleList);
			finalPayload.put("models", modelsList);
			String payload = gson.toJson(finalPayload);
			byte[] out = payload.getBytes(StandardCharsets.UTF_8);
			OutputStream stream = connection.getOutputStream();
			stream.write(out);
			System.out.println(connection.getResponseCode() + " " + connection.getResponseMessage());
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				// print result and save a JSON object for navigation
				String responseStr = response.toString().replaceAll("\\s","");
				JsonPrimitive responseJson = new Gson().fromJson(responseStr, JsonPrimitive.class);
				System.out.println(responseStr);
				
				final Boolean data = responseJson.getAsBoolean();
				System.out.println("rules : " + data);
								
				compiledRules.put("bookPublication", data);
				
			} else {
				System.out.println("POST request is not working");
			}
			connection.disconnect();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Failed sending information to the server");
		}
	}

	@Override
	public List<EObject> executeMatchRule(String ruleName, EObject param, boolean rightHand) throws Exception {
		if (!compiledRules.isEmpty()) {
			return Collections.emptyList();
		}
		return null;
	}

}
