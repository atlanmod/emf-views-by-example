/**
 */
package org.emfviews.examples.Book.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.emfviews.examples.Book.BookFactory;
import org.emfviews.examples.Book.Chapter;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Chapter</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ChapterTest extends TestCase {

	/**
	 * The fixture for this Chapter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Chapter fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ChapterTest.class);
	}

	/**
	 * Constructs a new Chapter test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChapterTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Chapter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Chapter fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Chapter test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Chapter getFixture() {
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
		setFixture(BookFactory.eINSTANCE.createChapter());
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

} //ChapterTest
