/**
 */
package org.emfviews.examples.Book;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.emfviews.examples.Book.Book#getTitle <em>Title</em>}</li>
 *   <li>{@link org.emfviews.examples.Book.Book#getAuthorName <em>Author Name</em>}</li>
 *   <li>{@link org.emfviews.examples.Book.Book#getChapters <em>Chapters</em>}</li>
 *   <li>{@link org.emfviews.examples.Book.Book#getISBN <em>ISBN</em>}</li>
 * </ul>
 *
 * @see org.emfviews.examples.Book.BookPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends EObject {
	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.emfviews.examples.Book.BookPackage#getBook_Title()
	 * @model
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.emfviews.examples.Book.Book#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Author Name</em>' attribute.
	 * @see #setAuthorName(String)
	 * @see org.emfviews.examples.Book.BookPackage#getBook_AuthorName()
	 * @model
	 * @generated
	 */
	String getAuthorName();

	/**
	 * Sets the value of the '{@link org.emfviews.examples.Book.Book#getAuthorName <em>Author Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author Name</em>' attribute.
	 * @see #getAuthorName()
	 * @generated
	 */
	void setAuthorName(String value);

	/**
	 * Returns the value of the '<em><b>Chapters</b></em>' containment reference list.
	 * The list contents are of type {@link org.emfviews.examples.Book.Chapter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Chapters</em>' containment reference list.
	 * @see org.emfviews.examples.Book.BookPackage#getBook_Chapters()
	 * @model containment="true"
	 * @generated
	 */
	EList<Chapter> getChapters();

	/**
	 * Returns the value of the '<em><b>ISBN</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>ISBN</em>' attribute.
	 * @see #setISBN(String)
	 * @see org.emfviews.examples.Book.BookPackage#getBook_ISBN()
	 * @model
	 * @generated
	 */
	String getISBN();

	/**
	 * Sets the value of the '{@link org.emfviews.examples.Book.Book#getISBN <em>ISBN</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>ISBN</em>' attribute.
	 * @see #getISBN()
	 * @generated
	 */
	void setISBN(String value);

} // Book
