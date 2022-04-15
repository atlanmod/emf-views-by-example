package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.atlanmod.emfviews.extra.EmfViewsFactory;
import org.atlanmod.emfviews.helper.ModelHelper;
import org.atlanmod.emfviews.helper.ViewHelper;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ChangeVirtualProperty {
	static String root = new File("../").getAbsolutePath();	  
	  static String serializedFolder = new File("serialized_examples/").getAbsolutePath();	  

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(root + relativePath);
	  }
	  
	  /**
	   * Iterate over the the view to change its filters values and verify what happens with source models (check if it is synchronized somehow)
	   * @param args
	   * @throws IOException
	   */
	  public static void main(String[] args) throws IOException {
		//Create basic resources to deal with EMF reflective API 
	    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	    map.put("xmi", new XMIResourceFactoryImpl());
	    map.put("ecore", new EcoreResourceFactoryImpl());
	    map.put("eview", new EmfViewsFactory());
	    
	    //Register virtualLinks
	    VirtualLinksPackage.eINSTANCE.eClass();
	    
	    // Register metamodels
	    ResourceSet resSet = new ResourceSetImpl();
	    EPackage Publication = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Publication.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Publication.getNsURI(), Publication);
	    
	    //Load the source model that is used in the view
	    Resource publication = resSet.getResource(resourceURI("/Resources/models/Basic/Publication.xmi"), true);

	    //Create EMF Resources for the view
	    Resource agePropertyView  = resSet.getResource(resourceURI("/2_a_View_WeavingModel_Elements/virtualPropertyExample/views/virtualPropertyEx.eview"), true);
	    agePropertyView.load(null);
	    
	    //print the view to check elements	    
	    List <EObject> vElements = agePropertyView.getContents();
	    ViewHelper.printView(vElements);
	    
	    //Try to adjust the ISBN values in the view
	    adjustAge((DynamicEObjectImpl) vElements.get(0));
	    
	    //print the view again to check the changes	    
	    ViewHelper.printView(vElements);
	    
	    //print the book to check if it changes
	    printPublication(publication);
	    
	    //serialize the publication model after changes into a new file to verify if something changes
	    ModelHelper.serializeResource(publication, URI.createFileURI(serializedFolder + "/change_virtual_concept_publication.xmi"));
	  }
	  
	  /**
	   * Receive view object and change the Age value when appropriate
	   * It uses the DynamicEObjectImpl as parameter to ensure that the dynamic property will be changed
	   * @param DynamicEObjectImpl object
	   */
	  public static void adjustAge(DynamicEObjectImpl object) 
	  {
		  EStructuralFeature feature = object.eClass().getEStructuralFeature("Age");
		  if (feature != null) 
		  {
			  Random rd = new Random();
			  object.eSet(feature, rd.nextInt(6) + 5);
		  }
	  }
	  
	  /**
	   * 
	   * @param Resource publication
	   */
	  public static void printPublication(Resource publication) 
	  {
		  ModelHelper.printResource(publication);
	  }
}
