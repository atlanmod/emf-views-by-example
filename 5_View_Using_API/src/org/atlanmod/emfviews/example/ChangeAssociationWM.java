package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.atlanmod.emfviews.helper.ViewHelper;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksPackage;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class ChangeAssociationWM {
	static String here = new File("../").getAbsolutePath();

	static URI resourceURI(String relativePath) {
		return URI.createFileURI(here + relativePath);
	}
	
	public static void main(String[] args) throws IOException {
		//Create basic resources to deal with EMF reflective API 
	    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	    map.put("xmi", new XMIResourceFactoryImpl());
	    map.put("ecore", new EcoreResourceFactoryImpl());
	    
	    //Register virtualLinks
	    VirtualLinksPackage.eINSTANCE.eClass();
	    
	    // Get ResourceSet
	    ResourceSet resSet = new ResourceSetImpl();

	    //Read viewpoint WeavingModel using EMF API
	    Resource filterViewPoint  = resSet.getResource(resourceURI("/6_Sync_Filters/src-gen/filters.xmi"), true);
	    filterViewPoint.load(null);
	    
	    //print the WeavingModel to check elements	    
	    TreeIterator <EObject> vElements = filterViewPoint.getAllContents();
	    ViewHelper.printWeavingModel(vElements);
	  }
}
