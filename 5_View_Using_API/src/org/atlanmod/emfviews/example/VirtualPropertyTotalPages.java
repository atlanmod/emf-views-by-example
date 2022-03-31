package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.core.ViewResource;
import org.atlanmod.emfviews.core.Viewpoint;
import org.atlanmod.emfviews.core.ViewpointResource;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.VirtualProperty;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

//TODO: This example is not working properly. Keep for reference

public class VirtualPropertyTotalPages {
	static String here = new File("../Resources").getAbsolutePath();
	static String examples = new File("example_results").getAbsolutePath();

	  static URI resourceURI(String relativePath) {
	    return URI.createFileURI(here + relativePath);
	  }

	  /**
	   * Iterate over the instances of book and set the total page numbers into the view
	   * @param args
	   * @throws IOException
	   */
	  public static void main(String[] args) throws IOException {
	    //Create basic resources to deal with EMF reflective API 
	    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	    map.put("xmi", new XMIResourceFactoryImpl());
	    map.put("ecore", new EcoreResourceFactoryImpl());

	    //Create EMF Resources
	    ResourceSet resSet = new ResourceSetImpl();
	    EPackage Book = (EPackage) resSet.getResource(resourceURI("/metamodels/Basic/Book.ecore"), true).getContents().get(0);

	    EPackage.Registry.INSTANCE.put(Book.getNsURI(), Book);

	    Resource book = resSet.getResource(resourceURI("/models/Basic/Book.xmi"), true);

	    // 1. Build viewpoint weaving model
	    VirtualLinksFactory vLinksFactory = VirtualLinksFactory.eINSTANCE;
	    WeavingModel viewpointWeavingModel = vLinksFactory.createWeavingModel();
	    viewpointWeavingModel.setName("booksCalcPages");

	    ConcreteConcept bookConcept;	    
	    {
	      ContributingModel contributingModel = vLinksFactory.createContributingModel();
	      viewpointWeavingModel.getContributingModels().add(contributingModel);
	      contributingModel.setURI("http://book");
	      ConcreteConcept cConcept = vLinksFactory.createConcreteConcept();
	      contributingModel.getConcreteElements().add(cConcept);
	      cConcept.setPath("Book");
	      bookConcept = cConcept;
	    }
	    
	    //Create virtual property to store total page number of the publication
	    VirtualProperty pagesTotal;
	    {
	      VirtualProperty vProperty = vLinksFactory.createVirtualProperty();
		  viewpointWeavingModel.getVirtualLinks().add(vProperty);
		  vProperty.setName("pagesTotal");
		  vProperty.setType("int");
		  vProperty.setOptional(false);
		  vProperty.setParent(bookConcept);
		  pagesTotal = vProperty;
	    }

	    // 2. Build viewpoint
	    Map<String, EPackage> contributingModels = Map.ofEntries(
	            Map.entry("book", Book)
	            );
	    Viewpoint viewpoint = new Viewpoint(contributingModels, viewpointWeavingModel);
	    
        //3. Navigate through the model to set the VirtualProperty totalPages	    
	    int totalPages = 0;
	    List <EObject> modelChapters = book.getContents().get(0).eContents();
	    for (Iterator<EObject> iter = modelChapters.iterator() ; iter.hasNext();) {
	    	EObject chapter = iter.next();
		    EClass chapterModelClass = chapter.eClass();
		    //System.out.println(chapterModelClass.getName());
		    //Navigate through book chapters and print its information
		    for (Iterator<EAttribute> iterAttr = chapterModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
		    	EAttribute chapterAttribute = (EAttribute) iterAttr.next();
		    	
		    	Object bookAttributeValue = chapter.eGet(chapterAttribute);
		    	String attrName = chapterAttribute.getName();
    			if (attrName.equals("nbPages")) {
    				totalPages += (int) bookAttributeValue;
		    	}
		    	//System.out.println(" " + chapterAttribute.getName() + ": " + bookAttributeValue);
		    	if (chapter.eIsSet(chapterAttribute)) {
		    		//System.out.println();
		    	} else {
		    		//System.out.println(" (default)");
		    	}		    	
		    }
	    }

	    // 3. Build view weaving model
	    WeavingModel viewWeavingModel = vLinksFactory.createWeavingModel();
	    viewWeavingModel.setName("booksCalcPages");

	    {
	        ContributingModel cm = vLinksFactory.createContributingModel();
	        viewWeavingModel.getContributingModels().add(cm);
	        cm.setURI("http://book");
	        ConcreteConcept cc = vLinksFactory.createConcreteConcept();
	        cm.getConcreteElements().add(cc);
	        cc.setPath(book.getURIFragment(book.getContents().get(0).eContents().get(0)));
	        bookConcept = cc;
	    }
	    
	    {
	        VirtualProperty vProperty = vLinksFactory.createVirtualProperty();
	        viewWeavingModel.getVirtualLinks().add(vProperty);
			vProperty.setName("pagesTotal");
			vProperty.setType("int");
			vProperty.setOptional(false);
			vProperty.setParent(bookConcept);
	    }

	    // 4. Build view
	    View view = new View(viewpoint, Arrays.asList(book), viewWeavingModel);
	    
	    //SAVE VIEWPOINT AND VIEW - NOT WORKING
	    /*ViewpointResource vpr = new ViewpointResource(URI.createFileURI("./my.eviewpoint"));
	    viewpoint.setResource(vpr);
	    vpr.save(null);

	    ViewResource vr = new ViewResource(URI.createURI("./myvieq.eview"));
	    vr.setView(view);
	    vr.save(null);*/
	    
	    Resource univ1 = resSet.createResource(URI.createURI("./myTest1.xmi"));
	    univ1.getContents().addAll(viewWeavingModel.getContributingModels());
	    univ1.getContents().addAll(viewWeavingModel.getFilters());
	    univ1.getContents().addAll(viewWeavingModel.getVirtualLinks());
	    univ1.getContents().addAll(viewWeavingModel.getVirtualAssociations());
	    univ1.getContents().addAll(viewWeavingModel.getVirtualProperties());
	    univ1.getContents().addAll(viewWeavingModel.getVirtualConcepts());
	    univ1.getContents().addAll(viewWeavingModel.getVirtualElements());
	    try{	
	        univ1.save(null);
	    }
	    catch (IOException e) {e.printStackTrace();}

	    // 5. Navigate the view to check the concrete properties and also the virtual one
	    List <EObject> vbooks = view.getVirtualContents().get(0).eContents();
	    for (Iterator<EObject> iter = vbooks.iterator() ; iter.hasNext();) {
	    	EObject vBook = iter.next();
		    EClass vBookModelClass = vBook.eClass();
		    System.out.println(vBookModelClass.getName());
		    for (Iterator<EAttribute> iterAttr = vBookModelClass.getEAllAttributes().iterator() ; iterAttr.hasNext();) {
		    	EAttribute vBookAttribute = (EAttribute) iterAttr.next();
		    	
		    	Object bookAttributeValue = vBook.eGet(vBookAttribute);
		    	String attrName = vBookAttribute.getName();
		    	System.out.println(" " + vBookAttribute.getName() + ": " + bookAttributeValue);
		    	if (vBook.eIsSet(vBookAttribute)) {
		    		System.out.println();
		    	} else {
		    		System.out.println(" (default)");
		    	}		    	
		    }
	    }
	    
	    /*EObject vproperty = view.get
	    /*System.out.println(vbook.eGet(vbook.eClass().getEStructuralFeature("title")));
	    System.out.println(vbook);*/
	    
	    /*EStructuralFeature vprop = vpubl.eClass().getEStructuralFeature("pagesTotal");
	    vprop.eSet(vprop, totalPages);
	    System.out.println(vchapter.eGet(vchapter.eClass().getEStructuralFeature("title")));*/
	  }
 }
