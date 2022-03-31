/**
 */
package org.emfviews.examples.Publication.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.emfviews.examples.Book.BookPackage;

import org.emfviews.examples.Book.impl.BookPackageImpl;

import org.emfviews.examples.Publication.Publication;
import org.emfviews.examples.Publication.PublicationFactory;
import org.emfviews.examples.Publication.PublicationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PublicationPackageImpl extends EPackageImpl implements PublicationPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass publicationEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.emfviews.examples.Publication.PublicationPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PublicationPackageImpl() {
		super(eNS_URI, PublicationFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link PublicationPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PublicationPackage init() {
		if (isInited) return (PublicationPackage)EPackage.Registry.INSTANCE.getEPackage(PublicationPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredPublicationPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		PublicationPackageImpl thePublicationPackage = registeredPublicationPackage instanceof PublicationPackageImpl ? (PublicationPackageImpl)registeredPublicationPackage : new PublicationPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(BookPackage.eNS_URI);
		BookPackageImpl theBookPackage = (BookPackageImpl)(registeredPackage instanceof BookPackageImpl ? registeredPackage : BookPackage.eINSTANCE);

		// Create package meta-data objects
		thePublicationPackage.createPackageContents();
		theBookPackage.createPackageContents();

		// Initialize created meta-data
		thePublicationPackage.initializePackageContents();
		theBookPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePublicationPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PublicationPackage.eNS_URI, thePublicationPackage);
		return thePublicationPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPublication() {
		return publicationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPublication_Title() {
		return (EAttribute)publicationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPublication_Author() {
		return (EAttribute)publicationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPublication_Publisher() {
		return (EAttribute)publicationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPublication_Year() {
		return (EAttribute)publicationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPublication_Code() {
		return (EAttribute)publicationEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicationFactory getPublicationFactory() {
		return (PublicationFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		publicationEClass = createEClass(PUBLICATION);
		createEAttribute(publicationEClass, PUBLICATION__TITLE);
		createEAttribute(publicationEClass, PUBLICATION__AUTHOR);
		createEAttribute(publicationEClass, PUBLICATION__PUBLISHER);
		createEAttribute(publicationEClass, PUBLICATION__YEAR);
		createEAttribute(publicationEClass, PUBLICATION__CODE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(publicationEClass, Publication.class, "Publication", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPublication_Title(), ecorePackage.getEString(), "title", null, 0, 1, Publication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPublication_Author(), ecorePackage.getEString(), "author", null, 0, 1, Publication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPublication_Publisher(), ecorePackage.getEString(), "publisher", null, 0, 1, Publication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPublication_Year(), ecorePackage.getEInt(), "year", null, 0, 1, Publication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPublication_Code(), ecorePackage.getEString(), "Code", null, 0, 1, Publication.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //PublicationPackageImpl
