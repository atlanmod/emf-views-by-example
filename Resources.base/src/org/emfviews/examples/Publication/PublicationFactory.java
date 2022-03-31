/**
 */
package org.emfviews.examples.Publication;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.emfviews.examples.Publication.PublicationPackage
 * @generated
 */
public interface PublicationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	PublicationFactory eINSTANCE = org.emfviews.examples.Publication.impl.PublicationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Publication</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Publication</em>'.
	 * @generated
	 */
	Publication createPublication();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	PublicationPackage getPublicationPackage();

} //PublicationFactory
