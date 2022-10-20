package org.atlanmod.emfviews.example;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.core.Viewpoint;
import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Filter;
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
    //Create basic resources to deal with EMF reflective API 
    Map<String, Object> map = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
    map.put("xmi", new XMIResourceFactoryImpl());
    map.put("ecore", new EcoreResourceFactoryImpl());

    //Create EMF Resources
    ResourceSet rs = new ResourceSetImpl();
    EPackage Books = (EPackage) rs.getResource(resourceURI("/metamodels/Example/Books.ecore"), true).getContents().get(0);
    EPackage Publs = (EPackage) rs.getResource(resourceURI("/metamodels/Example/Publications.ecore"), true).getContents().get(0);

    EPackage.Registry.INSTANCE.put(Books.getNsURI(), Books);
    EPackage.Registry.INSTANCE.put(Publs.getNsURI(), Publs);

    Resource books = rs.getResource(resourceURI("/models/Example/Books.xmi"), true);
    Resource publs = rs.getResource(resourceURI("/models/Example/Publications.xmi"), true);

    // 1. Build viewpoint weaving model
    VirtualLinksFactory vLinksFactory = VirtualLinksFactory.eINSTANCE;
    WeavingModel viewpointWeavingModel = vLinksFactory.createWeavingModel();
    viewpointWeavingModel.setName("publicationsAndBooks");

    ConcreteConcept source;
    {
      ContributingModel cm = vLinksFactory.createContributingModel();
      viewpointWeavingModel.getContributingModels().add(cm);
      cm.setURI("http://publications");
      ConcreteConcept cc = vLinksFactory.createConcreteConcept();
      cm.getConcreteElements().add(cc);
      cc.setPath("Publications");
      source = cc;
    }

    ConcreteConcept target;
    
    ConcreteElement nbPages;
    {
      ContributingModel contributingModel = vLinksFactory.createContributingModel();
      viewpointWeavingModel.getContributingModels().add(contributingModel);
      contributingModel.setURI("http://books");
      ConcreteConcept cConcept = vLinksFactory.createConcreteConcept();
      contributingModel.getConcreteElements().add(cConcept);
      cConcept.setPath("Chapter");
      target = cConcept;
      ConcreteElement cElement = vLinksFactory.createConcreteElement();
      contributingModel.getConcreteElements().add(cElement);
      cElement.setPath("Chapter.nbPages");
      nbPages = cElement;
    }

    {
      VirtualAssociation vAssociation = vLinksFactory.createVirtualAssociation();
      viewpointWeavingModel.getVirtualLinks().add(vAssociation);
      vAssociation.setName("bookChapters");
      vAssociation.setUpperBound(-1);
      vAssociation.setSource(source);
      vAssociation.setTarget(target);
    }

    {
      Filter fi = vLinksFactory.createFilter();
      viewpointWeavingModel.getVirtualLinks().add(fi);
      fi.setName("nbPages");
      fi.setTarget(nbPages);
    }

    // 2. Build viewpoint
    Map<String, EPackage> contributingModels = Map.ofEntries(
            Map.entry("books", Books),
            Map.entry("publs", Publs)
            );
    Viewpoint viewpoint = new Viewpoint(contributingModels, viewpointWeavingModel);

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
    System.out.println(vpubl.eGet(vpubl.eClass().getEStructuralFeature("title")));

    //EStructuralFeature assoc = vpubl.eClass().getEStructuralFeature("bookChapters");
    //EObject vchapter = ((EList<EObject>) vpubl.eGet(assoc)).get(0);
    //System.out.println(vchapter.eGet(vchapter.eClass().getEStructuralFeature("title")));
  }
}