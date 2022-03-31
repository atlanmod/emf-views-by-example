/**
 */
package org.emfviews.examples.Book;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.emfviews.examples.Book.BookFactory
 * @model kind="package"
 * @generated
 */
public interface BookPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Book";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://book";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Book";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BookPackage eINSTANCE = org.emfviews.examples.Book.impl.BookPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.emfviews.examples.Book.impl.BookImpl <em>Book</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emfviews.examples.Book.impl.BookImpl
	 * @see org.emfviews.examples.Book.impl.BookPackageImpl#getBook()
	 * @generated
	 */
	int BOOK = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Author Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__AUTHOR_NAME = 1;

	/**
	 * The feature id for the '<em><b>Chapters</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__CHAPTERS = 2;

	/**
	 * The feature id for the '<em><b>ISBN</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK__ISBN = 3;

	/**
	 * The number of structural features of the '<em>Book</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Book</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.emfviews.examples.Book.impl.ChapterImpl <em>Chapter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.emfviews.examples.Book.impl.ChapterImpl
	 * @see org.emfviews.examples.Book.impl.BookPackageImpl#getChapter()
	 * @generated
	 */
	int CHAPTER = 1;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAPTER__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Nb Pages</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAPTER__NB_PAGES = 1;

	/**
	 * The number of structural features of the '<em>Chapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAPTER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Chapter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHAPTER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.emfviews.examples.Book.Book <em>Book</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Book</em>'.
	 * @see org.emfviews.examples.Book.Book
	 * @generated
	 */
	EClass getBook();

	/**
	 * Returns the meta object for the attribute '{@link org.emfviews.examples.Book.Book#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.emfviews.examples.Book.Book#getTitle()
	 * @see #getBook()
	 * @generated
	 */
	EAttribute getBook_Title();

	/**
	 * Returns the meta object for the attribute '{@link org.emfviews.examples.Book.Book#getAuthorName <em>Author Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author Name</em>'.
	 * @see org.emfviews.examples.Book.Book#getAuthorName()
	 * @see #getBook()
	 * @generated
	 */
	EAttribute getBook_AuthorName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.emfviews.examples.Book.Book#getChapters <em>Chapters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Chapters</em>'.
	 * @see org.emfviews.examples.Book.Book#getChapters()
	 * @see #getBook()
	 * @generated
	 */
	EReference getBook_Chapters();

	/**
	 * Returns the meta object for the attribute '{@link org.emfviews.examples.Book.Book#getISBN <em>ISBN</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ISBN</em>'.
	 * @see org.emfviews.examples.Book.Book#getISBN()
	 * @see #getBook()
	 * @generated
	 */
	EAttribute getBook_ISBN();

	/**
	 * Returns the meta object for class '{@link org.emfviews.examples.Book.Chapter <em>Chapter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Chapter</em>'.
	 * @see org.emfviews.examples.Book.Chapter
	 * @generated
	 */
	EClass getChapter();

	/**
	 * Returns the meta object for the attribute '{@link org.emfviews.examples.Book.Chapter#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.emfviews.examples.Book.Chapter#getTitle()
	 * @see #getChapter()
	 * @generated
	 */
	EAttribute getChapter_Title();

	/**
	 * Returns the meta object for the attribute '{@link org.emfviews.examples.Book.Chapter#getNbPages <em>Nb Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nb Pages</em>'.
	 * @see org.emfviews.examples.Book.Chapter#getNbPages()
	 * @see #getChapter()
	 * @generated
	 */
	EAttribute getChapter_NbPages();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BookFactory getBookFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.emfviews.examples.Book.impl.BookImpl <em>Book</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emfviews.examples.Book.impl.BookImpl
		 * @see org.emfviews.examples.Book.impl.BookPackageImpl#getBook()
		 * @generated
		 */
		EClass BOOK = eINSTANCE.getBook();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOK__TITLE = eINSTANCE.getBook_Title();

		/**
		 * The meta object literal for the '<em><b>Author Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOK__AUTHOR_NAME = eINSTANCE.getBook_AuthorName();

		/**
		 * The meta object literal for the '<em><b>Chapters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BOOK__CHAPTERS = eINSTANCE.getBook_Chapters();

		/**
		 * The meta object literal for the '<em><b>ISBN</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOK__ISBN = eINSTANCE.getBook_ISBN();

		/**
		 * The meta object literal for the '{@link org.emfviews.examples.Book.impl.ChapterImpl <em>Chapter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.emfviews.examples.Book.impl.ChapterImpl
		 * @see org.emfviews.examples.Book.impl.BookPackageImpl#getChapter()
		 * @generated
		 */
		EClass CHAPTER = eINSTANCE.getChapter();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHAPTER__TITLE = eINSTANCE.getChapter_Title();

		/**
		 * The meta object literal for the '<em><b>Nb Pages</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CHAPTER__NB_PAGES = eINSTANCE.getChapter_NbPages();

	}

} //BookPackage
