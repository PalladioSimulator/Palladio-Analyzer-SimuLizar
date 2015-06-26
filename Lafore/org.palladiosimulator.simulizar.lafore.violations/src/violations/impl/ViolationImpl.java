/**
 */
package violations.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import violations.Violation;
import violations.ViolationType;
import violations.ViolationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Violation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link violations.impl.ViolationImpl#getViolationType <em>Violation Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ViolationImpl extends EntityImpl implements Violation {
	/**
	 * The cached value of the '{@link #getViolationType() <em>Violation Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolationType()
	 * @generated
	 * @ordered
	 */
	protected ViolationType violationType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViolationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationsPackage.Literals.VIOLATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationType getViolationType() {
		if (violationType != null && violationType.eIsProxy()) {
			InternalEObject oldViolationType = (InternalEObject)violationType;
			violationType = (ViolationType)eResolveProxy(oldViolationType);
			if (violationType != oldViolationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViolationsPackage.VIOLATION__VIOLATION_TYPE, oldViolationType, violationType));
			}
		}
		return violationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationType basicGetViolationType() {
		return violationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViolationType(ViolationType newViolationType) {
		ViolationType oldViolationType = violationType;
		violationType = newViolationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationsPackage.VIOLATION__VIOLATION_TYPE, oldViolationType, violationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViolationsPackage.VIOLATION__VIOLATION_TYPE:
				if (resolve) return getViolationType();
				return basicGetViolationType();
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
			case ViolationsPackage.VIOLATION__VIOLATION_TYPE:
				setViolationType((ViolationType)newValue);
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
			case ViolationsPackage.VIOLATION__VIOLATION_TYPE:
				setViolationType((ViolationType)null);
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
			case ViolationsPackage.VIOLATION__VIOLATION_TYPE:
				return violationType != null;
		}
		return super.eIsSet(featureID);
	}

} //ViolationImpl
