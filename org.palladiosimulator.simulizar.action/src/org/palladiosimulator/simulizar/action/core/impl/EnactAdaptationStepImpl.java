/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enact Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationStepImpl#getAdaptationStepURI <em>Adaptation Step URI</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EnactAdaptationStepImpl extends AdaptationStepImpl implements EnactAdaptationStep {
	/**
	 * The default value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationStepURI()
	 * @generated
	 * @ordered
	 */
	protected static final String ADAPTATION_STEP_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAdaptationStepURI()
	 * @generated
	 * @ordered
	 */
	protected String adaptationStepURI = ADAPTATION_STEP_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnactAdaptationStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ENACT_ADAPTATION_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getAdaptationStepURI() {
		return adaptationStepURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAdaptationStepURI(String newAdaptationStepURI) {
		String oldAdaptationStepURI = adaptationStepURI;
		adaptationStepURI = newAdaptationStepURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI, oldAdaptationStepURI, adaptationStepURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
			return getAdaptationStepURI();
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
		case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
			setAdaptationStepURI((String) newValue);
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
		case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
			setAdaptationStepURI(ADAPTATION_STEP_URI_EDEFAULT);
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
		case CorePackage.ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI:
			return ADAPTATION_STEP_URI_EDEFAULT == null ? adaptationStepURI != null
					: !ADAPTATION_STEP_URI_EDEFAULT.equals(adaptationStepURI);
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
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (adaptationStepURI: ");
		result.append(adaptationStepURI);
		result.append(')');
		return result.toString();
	}

} //EnactAdaptationStepImpl
