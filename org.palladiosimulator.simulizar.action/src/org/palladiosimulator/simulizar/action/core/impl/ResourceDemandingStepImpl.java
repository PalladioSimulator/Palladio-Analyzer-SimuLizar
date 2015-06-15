/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
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
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl#getControllerCalls <em>Controller Calls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceDemandingStepImpl extends AdaptationStepImpl implements ResourceDemandingStep {
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
	public EList<ControllerCall> getControllerCalls() {
        if (controllerCalls == null) {
            controllerCalls = new EObjectContainmentWithInverseEList<ControllerCall>(ControllerCall.class, this, CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS, CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);
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
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getControllerCalls()).basicAdd(otherEnd, msgs);
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
                return ((InternalEList<?>)getControllerCalls()).basicRemove(otherEnd, msgs);
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
            case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
                getControllerCalls().clear();
                getControllerCalls().addAll((Collection<? extends ControllerCall>)newValue);
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
            case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
                return controllerCalls != null && !controllerCalls.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} //ResourceDemandingStepImpl
