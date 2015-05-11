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
 * <ul>
 *   <li>{@link violations.impl.ViolationImpl#getViolationType <em>Violation Type</em>}</li>
 *   <li>{@link violations.impl.ViolationImpl#getViolatedPercentage <em>Violated Percentage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ViolationImpl extends EntityImpl implements Violation {
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
	 * The default value of the '{@link #getViolatedPercentage() <em>Violated Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolatedPercentage()
	 * @generated
	 * @ordered
	 */
	protected static final double VIOLATED_PERCENTAGE_EDEFAULT = 0.0;
	/**
	 * The cached value of the '{@link #getViolatedPercentage() <em>Violated Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolatedPercentage()
	 * @generated
	 * @ordered
	 */
	protected double violatedPercentage = VIOLATED_PERCENTAGE_EDEFAULT;

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
	public double getViolatedPercentage() {
		return violatedPercentage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViolatedPercentage(double newViolatedPercentage) {
		double oldViolatedPercentage = violatedPercentage;
		violatedPercentage = newViolatedPercentage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationsPackage.VIOLATION__VIOLATED_PERCENTAGE, oldViolatedPercentage, violatedPercentage));
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
			case ViolationsPackage.VIOLATION__VIOLATED_PERCENTAGE:
				return getViolatedPercentage();
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
			case ViolationsPackage.VIOLATION__VIOLATED_PERCENTAGE:
				setViolatedPercentage((Double)newValue);
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
			case ViolationsPackage.VIOLATION__VIOLATED_PERCENTAGE:
				setViolatedPercentage(VIOLATED_PERCENTAGE_EDEFAULT);
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
			case ViolationsPackage.VIOLATION__VIOLATED_PERCENTAGE:
				return violatedPercentage != VIOLATED_PERCENTAGE_EDEFAULT;
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

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (violatedPercentage: ");
		result.append(violatedPercentage);
		result.append(')');
		return result.toString();
	}

} //ViolationImpl
