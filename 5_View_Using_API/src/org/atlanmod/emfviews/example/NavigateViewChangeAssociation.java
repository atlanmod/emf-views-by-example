package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.extra.EmfViewsFactory;
import org.atlanmod.emfviews.helper.ModelHelper;
import org.atlanmod.emfviews.helper.ViewHelper;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.atlanmod.emfviews.virtuallinks.delegator.VirtualLinksDelegator;
import org.atlanmod.emfviews.virtuallinksepsilondelegate.EclDelegate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;


public class NavigateViewChangeAssociation {
	  static String root = new File("../").getAbsolutePath();	  

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(root + relativePath);
	  }
	  
	  /**
	   * Iterate over the the view to change its association values and verify what happens with source models
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
	    VirtualLinksDelegator.register("ecl", new EclDelegate());
	    
	    // Register metamodels
	    ResourceSet resSet = new ResourceSetImpl();
	    EPackage Book = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Book.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Book.getNsURI(), Book);
	    EPackage Publication = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Publication.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Publication.getNsURI(), Publication);
	    
	    //Load the source model that is used in the view
	    Resource publication = resSet.getResource(resourceURI("/Resources/models/Basic/Publication.xmi"), true);

	    //Create EMF Resources for the view
	    Resource bookPublisherView  = resSet.getResource(resourceURI("/3_View_Simple_Join_Rule/views/bookPublisher.eview"), true);
	    bookPublisherView.load(null);
	    
	    //print the view to check elements	    
	    List <EObject> vElements = bookPublisherView.getContents();
	    ViewHelper.printView(vElements);
	    
	    //Try to adjust the publisher values in the view
	    adjustAllPublisher(vElements);
	    
	    //print the view again to check the changes	    
	    ViewHelper.printView(vElements);
	    
	    //print the publication to check if it changes
	    printPublication(publication);
	  }
	   
	  /**
	   * Navigate through the view to adjust the publisher of its elements (when exists)
	   * @param List <EObject> vElements
	   */
	  public static void adjustAllPublisher(List <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
		    	EObject vElement = iter.next();
			    adjustPublisher(vElement);
		  }
	  }
	  
	  /**
	   * Receive view object and change the Publisher value when appropriate
	   * @param EObject object
	   */
	  public static void adjustPublisher(EObject object) 
	  {
		  EStructuralFeature feature = object.eClass().getEStructuralFeature("publisher");
		  if (feature != null) 
		  {
			  String publisher = (String) object.eGet(feature).toString();
			  object.eSet(feature, publisher + " association");
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
