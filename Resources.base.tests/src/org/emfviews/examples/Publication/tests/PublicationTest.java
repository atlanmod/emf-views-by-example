/**
 */
package org.emfviews.examples.Publication.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.emfviews.examples.Publication.Publication;
import org.emfviews.examples.Publication.PublicationFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Publication</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class PublicationTest extends TestCase {

	/**
	 * The fixture for this Publication test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Publication fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(PublicationTest.class);
	}

	/**
	 * Constructs a new Publication test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicationTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Publication test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Publication fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Publication test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Publication getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(PublicationFactory.eINSTANCE.createPublication());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //PublicationTest
