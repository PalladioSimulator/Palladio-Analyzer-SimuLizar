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
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Resource Demanding Step</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl#getControllerCalls
 * <em>Controller Calls</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceDemandingStepImpl extends AdaptationStepImpl implements ResourceDemandingStep {
    /**
     * The cached value of the '{@link #getControllerCalls() <em>Controller Calls</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getControllerCalls()
     * @generated
     * @ordered
     */
    protected EList<ControllerCall> controllerCalls;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ResourceDemandingStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.RESOURCE_DEMANDING_STEP;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<ControllerCall> getControllerCalls() {
        if (this.controllerCalls == null)
        {
            this.controllerCalls = new EObjectContainmentWithInverseEList<ControllerCall>(ControllerCall.class, this,
                    CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS,
                    CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);
        }
        return this.controllerCalls;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getControllerCalls()).basicAdd(otherEnd,
                    msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            return ((InternalEList<?>) this.getControllerCalls()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            return this.getControllerCalls();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            this.getControllerCalls().clear();
            this.getControllerCalls().addAll((Collection<? extends ControllerCall>) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            this.getControllerCalls().clear();
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID)
        {
        case CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS:
            return this.controllerCalls != null && !this.controllerCalls.isEmpty();
        }
        return super.eIsSet(featureID);
    }

} // ResourceDemandingStepImpl
