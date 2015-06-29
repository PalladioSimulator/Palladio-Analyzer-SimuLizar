/**
 */
package strategies.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import strategies.ReconfigurationAction;
import strategies.StrategiesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reconfiguration Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link strategies.impl.ReconfigurationActionImpl#getCodeRef <em>Code Ref</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReconfigurationActionImpl extends org.palladiosimulator.pcm.seff.impl.InternalActionImpl implements ReconfigurationAction {
	/**
	 * The default value of the '{@link #getCodeRef() <em>Code Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeRef()
	 * @generated
	 * @ordered
	 */
	protected static final String CODE_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodeRef() <em>Code Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodeRef()
	 * @generated
	 * @ordered
	 */
	protected String codeRef = CODE_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReconfigurationActionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return StrategiesPackage.Literals.RECONFIGURATION_ACTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCodeRef() {
		return codeRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCodeRef(String newCodeRef) {
		String oldCodeRef = codeRef;
		codeRef = newCodeRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StrategiesPackage.RECONFIGURATION_ACTION__CODE_REF, oldCodeRef, codeRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StrategiesPackage.RECONFIGURATION_ACTION__CODE_REF:
				return getCodeRef();
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
			case StrategiesPackage.RECONFIGURATION_ACTION__CODE_REF:
				setCodeRef((String)newValue);
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
			case StrategiesPackage.RECONFIGURATION_ACTION__CODE_REF:
				setCodeRef(CODE_REF_EDEFAULT);
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
			case StrategiesPackage.RECONFIGURATION_ACTION__CODE_REF:
				return CODE_REF_EDEFAULT == null ? codeRef != null : !CODE_REF_EDEFAULT.equals(codeRef);
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
		result.append(" (codeRef: ");
		result.append(codeRef);
		result.append(')');
		return result.toString();
	}

} //ReconfigurationActionImpl
