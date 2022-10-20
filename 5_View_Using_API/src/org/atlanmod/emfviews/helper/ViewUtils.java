package org.atlanmod.emfviews.helper;

import java.util.HashMap;

import org.atlanmod.emfviews.virtuallinks.ConcreteConcept;
import org.atlanmod.emfviews.virtuallinks.ConcreteElement;
import org.atlanmod.emfviews.virtuallinks.ContributingModel;
import org.atlanmod.emfviews.virtuallinks.Filter;
import org.atlanmod.emfviews.virtuallinks.VirtualAssociation;
import org.atlanmod.emfviews.virtuallinks.VirtualLinksFactory;
import org.atlanmod.emfviews.virtuallinks.WeavingModel;

public class ViewUtils {

	protected VirtualLinksFactory vLinksFactory;

	protected WeavingModel weavingModel;

	protected HashMap<String, ContributingModel> contributingModels;

	/**
	 * 
	 * @param vLinksFactory
	 */
	public ViewUtils(VirtualLinksFactory vLinksFactory) {
		this.vLinksFactory = vLinksFactory;
		this.contributingModels = new HashMap<String, ContributingModel>();
	}

	/**
	 * Get a concept from the concepts collections based on the URI
	 * 
	 * @param uri
	 * @return
	 */
	public ContributingModel getContributingModel(String uri) {
		return contributingModels.get(uri);
	}
	
	public WeavingModel getWeavingModel() {
		return weavingModel;
	}

	/**
	 * Create and return a weaving model to be used to create a Viewpoint
	 * programmatically
	 * 
	 * @param name WeavingModel name
	 */
	public void createWeavingModel(String name) {
		weavingModel = vLinksFactory.createWeavingModel();
		weavingModel.setName(name);
		weavingModel.setWhitelist(false);
	}

	/**
	 * 
	 * @param nsURI
	 * @param path
	 * @return
	 */
	public ConcreteConcept createConcreteConcept(String nsURI, String path) {

		ConcreteConcept concreteConcept = vLinksFactory.createConcreteConcept();

		ContributingModel contribModel = vLinksFactory.createContributingModel();
		weavingModel.getContributingModels().add(contribModel);
		contribModel.setURI(nsURI);

		contribModel.getConcreteElements().add(concreteConcept);
		concreteConcept.setPath(path);

		contributingModels.put(nsURI, contribModel);
		
		return concreteConcept;
	}

	/**
	 * 
	 * @param contribModelNsURI
	 * @param elementPath
	 * @return
	 */
	public ConcreteElement createConcreteElement(String contribModelNsURI, String elementPath) {

		ContributingModel contributingModel = this.getContributingModel(contribModelNsURI);

		ConcreteElement cElement = vLinksFactory.createConcreteElement();
		contributingModel.getConcreteElements().add(cElement);
		cElement.setPath(elementPath);
		
		return cElement;
	}

	/**
	 * 
	 * @param name
	 * @param source
	 * @param target
	 * @param upperBound
	 * @return
	 */
	public VirtualAssociation createVirtualAssociation(String name, ConcreteConcept source, ConcreteConcept target,
			int upperBound) {
		
		VirtualAssociation vAssociation = vLinksFactory.createVirtualAssociation();
		weavingModel.getVirtualLinks().add(vAssociation);
		vAssociation.setName(name);
		vAssociation.setUpperBound(upperBound);
		vAssociation.setSource(source);
		vAssociation.setTarget(target);
		
		return vAssociation;
	}

	/**
	 * 
	 * @param name
	 * @param target
	 * @return
	 */
	public Filter createFilter(String name, ConcreteElement target) {
		
		Filter filter = vLinksFactory.createFilter();
		weavingModel.getVirtualLinks().add(filter);
		filter.setName(name);
		filter.setTarget(target);
		
		return filter;
	}
}
