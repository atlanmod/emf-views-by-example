/**
 */
package org.emfviews.examples.Book.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.emfviews.examples.Book.BookPackage;
import org.emfviews.examples.Book.Chapter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Chapter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.emfviews.examples.Book.impl.ChapterImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.emfviews.examples.Book.impl.ChapterImpl#getNbPages <em>Nb Pages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChapterImpl extends MinimalEObjectImpl.Container implements Chapter {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getNbPages() <em>Nb Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNbPages()
	 * @generated
	 * @ordered
	 */
	protected static final int NB_PAGES_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNbPages() <em>Nb Pages</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNbPages()
	 * @generated
	 * @ordered
	 */
	protected int nbPages = NB_PAGES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChapterImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BookPackage.Literals.CHAPTER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BookPackage.CHAPTER__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNbPages() {
		return nbPages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNbPages(int newNbPages) {
		int oldNbPages = nbPages;
		nbPages = newNbPages;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, BookPackage.CHAPTER__NB_PAGES, oldNbPages, nbPages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case BookPackage.CHAPTER__TITLE:
				return getTitle();
			case BookPackage.CHAPTER__NB_PAGES:
				return getNbPages();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case BookPackage.CHAPTER__TITLE:
				setTitle((String)newValue);
				return;
			case BookPackage.CHAPTER__NB_PAGES:
				setNbPages((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case BookPackage.CHAPTER__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case BookPackage.CHAPTER__NB_PAGES:
				setNbPages(NB_PAGES_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case BookPackage.CHAPTER__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case BookPackage.CHAPTER__NB_PAGES:
				return nbPages != NB_PAGES_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (title: ");
		result.append(title);
		result.append(", nbPages: ");
		result.append(nbPages);
		result.append(')');
		return result.toString();
	}

} //ChapterImpl
