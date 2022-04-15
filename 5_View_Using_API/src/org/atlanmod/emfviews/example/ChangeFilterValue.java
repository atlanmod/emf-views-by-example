package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.core.ViewResource;
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

public class ChangeFilterValue {
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
	    EPackage Book = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Book.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Book.getNsURI(), Book);
	    EPackage Publication = (EPackage) resSet.getResource(resourceURI("/Resources/metamodels/Basic/Publication.ecore"), true).getContents().get(0);
	    EPackage.Registry.INSTANCE.put(Publication.getNsURI(), Publication);
	    
	    //Load the source model that is used in the view
	    Resource book = resSet.getResource(resourceURI("/Resources/models/Basic/Book.xmi"), true);

	    //Create EMF Resources for the view
	    Resource filteredInfoView  = resSet.getResource(resourceURI("/6_Sync_Filters/views/filteredInfo.eview"), true);
	    filteredInfoView.load(null);//serialize the weaving model after modification
	    
	    //print the view to check elements	    
	    List <EObject> vElements = filteredInfoView.getContents();
	    ViewHelper.printView(vElements);
	    
	    //Try to adjust the ISBN values in the view
	    adjustAllISBN(vElements);
	    
	    //print the view again to check the changes	    
	    ViewHelper.printView(vElements);
	    
	    //print the book to check if it changes
	    printBook(book);
	    
	    //serialize the book model after changes into a new file to verify if something changes
	    ModelHelper.serializeResource(book, URI.createFileURI(serializedFolder + "/change_filter_book.xmi"));
	    
	    //Serialize the weaving model to verify if anything changes after modification of the Filter's value
	    //HOW?
	    

	  }
	  	  
	  /**
	   * Navigate through the view to adjust the ISBN of its elements (when exists)
	   * @param List <EObject> vElements
	   */
	  public static void adjustAllISBN(List <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
			  DynamicEObjectImpl vElement = (DynamicEObjectImpl) iter.next();
			    adjustISBN(vElement);
		  }
	  }
	  
	  /**
	   * Receive view object and change the ISBN value when appropriate
	   * @param EObject object
	   */
	  public static void adjustISBN(DynamicEObjectImpl object) 
	  {
		  EStructuralFeature feature = object.eClass().getEStructuralFeature("ISBN");
		  if (feature != null) 
		  {
			  String isbn = (String) object.eGet(feature).toString();

			  object.eSet(feature, "ISBN=" + isbn);
		  }
	  }
	  
	  /**
	   * 
	   * @param Resource book
	   */
	  public static void printBook(Resource book) 
	  {
		  ModelHelper.printResource(book);
	  }

}
