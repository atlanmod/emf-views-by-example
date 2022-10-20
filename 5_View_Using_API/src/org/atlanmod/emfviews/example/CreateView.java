package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.core.Viewpoint;
import org.atlanmod.emfviews.helper.ViewHelper;
import org.atlanmod.emfviews.helper.ViewUtils;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

public class CreateView {
	static String here = new File("../Resources").getAbsolutePath();

	static URI resourceURI(String relativePath) {
		return URI.createFileURI(here + relativePath);
	}

	public static void main(String[] args) throws IOException {
		// Create basic resources to deal with EMF reflective API
		Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		map.put("xmi", new XMIResourceFactoryImpl());
		map.put("ecore", new EcoreResourceFactoryImpl());

		// Create EMF Resources
		ResourceSet rs = new ResourceSetImpl();
		EPackage Books = (EPackage) rs.getResource(resourceURI("/metamodels/Example/Books.ecore"), true).getContents()
				.get(0);
		EPackage Publs = (EPackage) rs.getResource(resourceURI("/metamodels/Example/Publications.ecore"), true)
				.getContents().get(0);

		EPackage.Registry.INSTANCE.put(Books.getNsURI(), Books);
		EPackage.Registry.INSTANCE.put(Publs.getNsURI(), Publs);

		Resource books = rs.getResource(resourceURI("/models/Example/Books.xmi"), true);
		Resource publs = rs.getResource(resourceURI("/models/Example/Publications.xmi"), true);

		// Create a new instance of Utils class to work with viewpoint elements
		// including a factory for the Virtual Links
		VirtualLinksFactory vLinksFactory = VirtualLinksFactory.eINSTANCE;
		ViewUtils viewpointUtils = new ViewUtils(vLinksFactory);

		// 1. Build viewpoint weaving model
		viewpointUtils.createWeavingModel("publicationsAndBooks");

		// 2. Define the Concrete concepts to be used in the ViewPoint
		ConcreteConcept source = viewpointUtils.createConcreteConcept("http://publications", "Publications");
		ConcreteConcept target = viewpointUtils.createConcreteConcept("http://books", "Chapter");
				
		// 3. Create a new Element and attach it to a concept
		ConcreteElement nbPages = viewpointUtils.createConcreteElement("http://books", "Chapter.nbPages");
		
		// 4. Create the Virtual Association
		viewpointUtils.createVirtualAssociation("bookChapters", source, target, -1);

		// 5. Create the filters
		viewpointUtils.createFilter("nbPages", nbPages);
		
		// 2. Build viewpoint
		Map<String, EPackage> contributingModels = Map.ofEntries(Map.entry("books", Books), Map.entry("publs", Publs));
		Viewpoint viewpoint = new Viewpoint(contributingModels, viewpointUtils.getWeavingModel());

		// 3. Build view weaving model
		WeavingModel viewWeavingModel = vLinksFactory.createWeavingModel();
		viewWeavingModel.setName("publicationsAndBooks");

		{
			ContributingModel cm = vLinksFactory.createContributingModel();
			viewWeavingModel.getContributingModels().add(cm);
			cm.setURI("http://publications");
			ConcreteConcept cc = vLinksFactory.createConcreteConcept();
			cm.getConcreteElements().add(cc);
			cc.setPath(publs.getURIFragment(publs.getContents().get(0)));
			source = cc;
		}

		{
			ContributingModel cm = vLinksFactory.createContributingModel();
			viewWeavingModel.getContributingModels().add(cm);
			cm.setURI("http://books");
			ConcreteConcept cc = vLinksFactory.createConcreteConcept();
			cm.getConcreteElements().add(cc);
			cc.setPath(books.getURIFragment(books.getContents().get(0).eContents().get(0)));
			target = cc;
		}

		{
			VirtualAssociation va = vLinksFactory.createVirtualAssociation();
			viewWeavingModel.getVirtualLinks().add(va);
			va.setName("bookChapters");
			va.setSource(source);
			va.setTarget(target);
		}

		// 4. Build view
		View view = new View(viewpoint, Arrays.asList(books, publs), viewWeavingModel);

		// 5. Navigate the new association in the view
		EObject vpubl = view.getVirtualContents().get(1);
		ViewHelper.printView((List<EObject>) vpubl.eGet(vpubl.eClass().getEStructuralFeature("publications")));
		//System.out.println(vpubl.eGet(vpubl.eClass().getEStructuralFeature("publications")));

		// EStructuralFeature assoc =
		// vpubl.eClass().getEStructuralFeature("bookChapters");
		// EObject vchapter = ((EList<EObject>) vpubl.eGet(assoc)).get(0);
		// System.out.println(vchapter.eGet(vchapter.eClass().getEStructuralFeature("title")));
	}
}