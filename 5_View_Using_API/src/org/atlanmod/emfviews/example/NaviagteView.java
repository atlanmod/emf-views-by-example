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
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class NaviagteView {
	  static String root = new File("../").getAbsolutePath();	  

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(root + relativePath);
	  }
	  
	  /**
	   * Iterate over the a view to change its values and verify what happens with source models
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

	    //Create EMF Resources
	    Resource filteredInfoView  = resSet.getResource(resourceURI("/6_Sync_Filters/views/filteredInfo.eview"), true);
	    filteredInfoView.load(null);
	    
	    //	    
	    List <EObject> vElements = filteredInfoView.getContents();
	    printView(vElements);
	    
	    //Try to adjust the ISBN values in the view
	    
	    
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
	   * Receive view object and change the ISBN value when appropriate
	   * @param EObject object
	   */
	  public static void adjustISBN(EObject object) 
	  {
		  EStructuralFeature feature = object.eClass().getEStructuralFeature("ISBN");
		  if (feature != null && feature.getEType() == EcorePackage.Literals.ESTRING) 
		  {
			  String isbn = (String) object.eGet(feature).toString();
			  object.eSet(feature, "ISBN=" + isbn);
		  }
	  }

}
