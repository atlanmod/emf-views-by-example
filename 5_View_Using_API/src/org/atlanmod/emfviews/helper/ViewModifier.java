package org.atlanmod.emfviews.helper;

import java.util.Iterator;
import java.util.List;

import org.atlanmod.emfviews.core.View;
import org.atlanmod.emfviews.core.Virtualizer;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

public class ViewModifier {
	
	private Resource viewRs;
	private Virtualizer view;
	
	/**
	 * Constructor
	 * @param view
	 */
	public ViewModifier(Resource view)
	{
		this.viewRs = view;
	}
	
	/**
	 * Constructor
	 * @param view
	 */
	public ViewModifier(Virtualizer view)
	{
		this.view = view;
	}
		 
	
	/**
	  * Navigate through view's virtual elements, change the value, create a new view (?) and return it
	  * @param String featureName 
	  * @param String newValue
	  */
	 public void changeFilter(String featureName, String newValue) 
	 {
		 /*List <EObject> vElements = view.getContents();
		 for (Iterator<EObject> iter = vElements.iterator() ; iter.hasNext();) {
			  DynamicEObjectImpl vElement = (DynamicEObjectImpl) iter.next();
			  EStructuralFeature feature = vElement.eClass().getEStructuralFeature(featureName);
			  if (feature != null) 
			  {
				  vElement.eSet(feature, newValue);
				  break;
			  }
		 }
		 
		 View updatedView = (View) view;*/
		 
		 
	  /*for (Iterator<EObject> iter = vElements ; iter.hasNext();) {
	    	
		    }
	 }*/
		 View updatedView = (View) view;
		 //get the view filters to be updated
		 for (Filter filter : view.getWeavingModel().getFilters()) {
		      ConcreteElement target = filter.getTarget();
		      EObject targetObj = modelResources.get(target.getModel().getURI()).getEObject(target.getPath());
		      if (targetObj != null) {
		        VirtualEObject v = getVirtual(targetObj);
		        v.setHidden(true);
		      }
		}
	  }
	 
	 /**
	  * Navigate through view's virtual elements
	  * @param String featureName 
	  * @param String newValue
	  */
	 public void changeAssociation(String featureName, String newValue) 
	 {
		 
	 }
}
