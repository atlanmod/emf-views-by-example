/**
 */
package org.emfviews.examples.Book;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.emfviews.examples.Book.Chapter#getTitle <em>Title</em>}</li>
 *   <li>{@link org.emfviews.examples.Book.Chapter#getNbPages <em>Nb Pages</em>}</li>
 * </ul>
 *
 * @see org.emfviews.examples.Book.BookPackage#getChapter()
 * @model
 * @generated
 */
public interface Chapter extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.emfviews.examples.Book.BookPackage#getChapter_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.emfviews.examples.Book.Chapter#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Nb Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nb Pages</em>' attribute.
	 * @see #setNbPages(int)
	 * @see org.emfviews.examples.Book.BookPackage#getChapter_NbPages()
	 * @model
	 * @generated
	 */
	int getNbPages();

	/**
	 * Sets the value of the '{@link org.emfviews.examples.Book.Chapter#getNbPages <em>Nb Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nb Pages</em>' attribute.
	 * @see #getNbPages()
	 * @generated
	 */
	void setNbPages(int value);

} // Chapter
