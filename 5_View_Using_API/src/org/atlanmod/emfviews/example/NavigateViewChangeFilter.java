package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.extra.EmfViewsFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class NavigateViewChangeFilter {
	  static String root = new File("../").getAbsolutePath();	  

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(root + relativePath);
	  }
	  
	  /**
	   * Iterate over the the view to change its filters values and verify what happens with source models
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
	    filteredInfoView.load(null);
	    
	    //print the view to check elements	    
	    List <EObject> vElements = filteredInfoView.getContents();
	    printView(vElements);
	    
	    //Try to adjust the ISBN values in the view
	    adjustAllISBN(vElements);
	    
	    //print the view again to check the changes	    
	    printView(vElements);
	    
	    //print the book to check if it changes
	    printBook(book);	    
	  }
	  
	  /**
	   * Navigate through the view to print out attributes
	   * @param List <EObject> vElements
	   */
	  public static void printView(List <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
		    	EObject vElement = iter.next();
			    EClass vElementModelClass = vElement.eClass();
			    System.out.println(vElementModelClass.getName());
			    for (Iterator<EAttribute> iterAttr = vElementModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
			    	EAttribute vElementAttribute = (EAttribute) iterAttr.next();
			    	
			    	Object elementAttributeValue = vElement.eGet(vElementAttribute);
			    	String attrName = vElementAttribute.getName();
			    	System.out.println(" " + attrName + ": " + elementAttributeValue);
			    	if (vElement.eIsSet(vElementAttribute)) {
			    		System.out.println();
			    	} else {
			    		System.out.println(" (default)");
			    	}
			    }
		    }
	  }
	  
	  /**
	   * Navigate through the view to adjust the ISBN of its elements (when exists)
	   * @param List <EObject> vElements
	   */
	  public static void adjustAllISBN(List <EObject> vElements) 
	  {
		  for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
		    	EObject vElement = iter.next();
			    adjustISBN(vElement);
		  }
	  }
	  
	  /**
	   * Receive view object and change the ISBN value when appropriate
	   * @param EObject object
	   */
	  public static void adjustISBN(EObject object) 
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
		  List <EObject> bookElements = book.getContents();
		  for (Iterator<EObject> iter = bookElements.iterator() ; iter.hasNext();) {
		    	EObject bookElement = iter.next();
			    EClass bookModelClass = bookElement.eClass();
			    System.out.println(bookModelClass.getName());
			    for (Iterator<EAttribute> iterAttr = bookModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
			    	EAttribute bookElementAttribute = (EAttribute) iterAttr.next();
			    	
			    	Object elementAttributeValue = bookElement.eGet(bookElementAttribute);
			    	String attrName = bookElementAttribute.getName();
			    	System.out.println(" " + attrName + ": " + elementAttributeValue);
			    	if (bookElement.eIsSet(bookElementAttribute)) {
			    		System.out.println();
			    	} else {
			    		System.out.println(" (default)");
			    	}
			    }
		    }
	  }

}
