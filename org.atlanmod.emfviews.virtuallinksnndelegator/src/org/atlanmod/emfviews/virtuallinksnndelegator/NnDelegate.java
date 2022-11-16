package org.atlanmod.emfviews.virtuallinksnndelegator;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.virtuallinks.delegator.IVirtualLinksDelegate;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.atl.core.ATLCoreException;
import org.eclipse.m2m.atl.engine.compiler.Atl2004Compiler;



public class NnDelegate implements IVirtualLinksDelegate {

	private Atl2004Compiler atlCompiler;
	private Map<String, Lambda> compiledRules;

	static interface Lambda {
		Object exec(Object... args) throws ATLCoreException;
	}

	@Override
	public List<EObject> executeMatchRule(String arg0, EObject arg1, boolean arg2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(URI linksDslURI, Map<String, Resource> inputModels) {
		
		//For the Match rules file
		File file;
		
		//For the API
		URL url;
		HttpURLConnection connection;
		
		System.out.println(linksDslURI);
		System.out.println(inputModels);
		
		if (linksDslURI.isPlatform()) {
			// Find the system path for the file from the workspace URI
			IContainer wsroot = EcorePlugin.getWorkspaceRoot();
			IFile ifile = wsroot.getFile(new Path(linksDslURI.toPlatformString(true)));
			file = new File(ifile.getLocationURI());
		} else {
			file = new File(linksDslURI.toFileString());
		}
		
		try{
	        url = new URL(" http://172.26.72.178:8080/process");
	        connection = (HttpURLConnection) url.openConnection();
	        
	        connection.setRequestMethod("POST");
	        connection.setDoOutput(true);
	        connection.setRequestProperty("Content-Type","application/json");
	        connection.setRequestProperty("Accept", "application/json");
	        
	        String payload = "{\"num1\" : [1, 2, 3], \"num2\":[4, 5, 6]}";
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

				// print result
				System.out.println(response.toString());
			} else {
				System.out.println("POST request not worked");
			}
	        connection.disconnect();
	    }catch (Exception e){
	        System.out.println(e);
	        System.out.println("Failed successfully");
	    }
       
		
        
        
        
		/*InputStream fileStream;
		File file;

		if (linksDslURI.isPlatform()) {
			// Find the system path for the file from the workspace URI
			IContainer wsroot = EcorePlugin.getWorkspaceRoot();
			IFile ifile = wsroot.getFile(new Path(linksDslURI.toPlatformString(true)));
			file = new File(ifile.getLocationURI());
		} else {
			file = new File(linksDslURI.toFileString());
		}
		
		try {
			fileStream = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			throw new RuntimeException("Error in parsing ATL file", e1);
		}

		// Use ATL compiler to get the result of the file compilation
		atlCompiler = new Atl2004Compiler();
		try {
			CompileTimeError[] errors = atlCompiler.compile(fileStream, "");
			if (errors.length > 0) {
				System.err.println("Parse errors occured...");
				for (CompileTimeError problem : errors) {
					System.err.println(problem.toString());
				}
				throw new RuntimeException("Error in parsing ATL file.  See stderr for details");
			}
			OutputStream atlCompiled = Atl2004Compiler.getCompilationOutputStream();
		} catch (Exception ex) {
			throw new RuntimeException("Error in parsing ATL file", ex);
		}

		// Add input models and grab the metamodel URI
		for (Entry<String, Resource> e : inputModels.entrySet()) {
			String name = e.getKey();
			Resource modelResource = e.getValue();
//			EmfModel inputModel = new InMemoryEmfModel(name, modelResource);
//			atlCompiler.getContext().getModelRepository().addModel(inputModel);
		}*/
	}

}
