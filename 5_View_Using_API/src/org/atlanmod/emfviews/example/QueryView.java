package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.atlanmod.emfviews.extra.EmfViewsFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.atlanmod.emfviews.virtuallinks.delegator.VirtualLinksDelegator;
import org.atlanmod.emfviews.virtuallinksepsilondelegate.EclDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.helper.OCLHelper;

public class QueryView {
	  static String root = new File("../").getAbsolutePath();	  
	  static String serializedFolder = new File("serialized_examples/").getAbsolutePath();	  

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(root + relativePath);
	  }

	  public static void main(String args[]) throws ParserException, IOException {
	    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	    map.put("xmi", new XMIResourceFactoryImpl());
	    map.put("ecore", new EcoreResourceFactoryImpl());
	    map.put("eview", new EmfViewsFactory());
	    
	    //Register EMF Views Features 
	    VirtualLinksPackage.eINSTANCE.eClass();
	    VirtualLinksDelegator.register("ecl", new EclDelegate());
	    
	    // Register metamodels
	    ResourceSet resSet = new ResourceSetImpl();
	    EPackage Book = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Book.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Book.getNsURI(), Book);
	    EPackage Publication = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Publication.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Publication.getNsURI(), Publication);

	    // Initialize OCL
	    OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
	    OCLHelper oclHelper = ocl.createOCLHelper();

	    // Load the view
	    Resource view  = new ResourceSetImpl().getResource(resourceURI("/1_Basic_View/views/allChapters.eview"), true);
	    view.load(null);

	    // Set the query context
	    EObject root = view.getContents().get(0);
	    EObject context = root.eClass();
	    oclHelper.setContext(context);

	    // Create the query
	    Query query = ocl.createQuery(oclHelper.createQuery("self.bookChapters->collect(c | c.title)"));

	    // Evaluate and print result
	    System.out.println("Result: " + query.evaluate(root));

	    ocl.dispose();
	  }
	
}
