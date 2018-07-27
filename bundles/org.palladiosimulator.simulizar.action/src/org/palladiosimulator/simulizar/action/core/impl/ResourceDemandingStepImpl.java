/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Demanding Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl#getControllerCompletionURI <em>Controller Completion URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl#getControllerCalls <em>Controller Calls</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceDemandingStepImpl extends AdaptationStepImpl implements ResourceDemandingStep {
	/**
	 * The default value of the '{@link #getControllerCompletionURI() <em>Controller Completion URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControllerCompletionURI()
	 * @generated
	 * @ordered
	 */
	protected static final String CONTROLLER_COMPLETION_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getControllerCompletionURI() <em>Controller Completion URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControllerCompletionURI()
	 * @generated
	 * @ordered
	 */
	protected String controllerCompletionURI = CONTROLLER_COMPLETION_URI_EDEFAULT;

	/**
	 * The cached value of the '{@link #getControllerCalls() <em>Controller Calls</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControllerCalls()
	 * @generated
	 * @ordered
	 */
	protected EList<ControllerCall> controllerCalls;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceDemandingStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.RESOURCE_DEMANDING_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getControllerCompletionURI() {
		return controllerCompletionURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setControllerCompletionURI(String newControllerCompletionURI) {
		String oldControllerCompletionURI = controllerCompletionURI;
		controllerCompletionURI = newControllerCompletionURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI, oldControllerCompletionURI,
					controllerCompletionURI));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ControllerCall> getControllerCalls() {
		if (controllerCalls == null) {
			controllerCalls = new EObjectContainmentWithInverseEList<ControllerCall>(ControllerCall.class, this,
					CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS,
					CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);
		}
		return controllerCalls;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getControllerCalls()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			return ((InternalEList<?>) getControllerCalls()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI:
			return getControllerCompletionURI();
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			return getControllerCalls();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI:
			setControllerCompletionURI((String) newValue);
			return;
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			getControllerCalls().clear();
			getControllerCalls().addAll((Collection<? extends ControllerCall>) newValue);
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
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI:
			setControllerCompletionURI(CONTROLLER_COMPLETION_URI_EDEFAULT);
			return;
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			getControllerCalls().clear();
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
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI:
			return CONTROLLER_COMPLETION_URI_EDEFAULT == null ? controllerCompletionURI != null
					: !CONTROLLER_COMPLETION_URI_EDEFAULT.equals(controllerCompletionURI);
		case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
			return controllerCalls != null && !controllerCalls.isEmpty();
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
		result.append(" (controllerCompletionURI: ");
		result.append(controllerCompletionURI);
		result.append(')');
		return result.toString();
	}

} //ResourceDemandingStepImpl
