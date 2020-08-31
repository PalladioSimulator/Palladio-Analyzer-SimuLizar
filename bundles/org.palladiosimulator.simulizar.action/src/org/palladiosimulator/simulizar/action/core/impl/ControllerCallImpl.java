/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Controller Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getComponent
 * <em>Component</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getCalledSignature
 * <em>Called Signature</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getResourceDemandingStep
 * <em>Resource Demanding Step</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallImpl extends EntityImpl implements ControllerCall {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ControllerCallImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.CONTROLLER_CALL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BasicComponent getComponent() {
        return (BasicComponent) this.eDynamicGet(CorePackage.CONTROLLER_CALL__COMPONENT,
                CorePackage.Literals.CONTROLLER_CALL__COMPONENT, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public BasicComponent basicGetComponent() {
        return (BasicComponent) this.eDynamicGet(CorePackage.CONTROLLER_CALL__COMPONENT,
                CorePackage.Literals.CONTROLLER_CALL__COMPONENT, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setComponent(final BasicComponent newComponent) {
        this.eDynamicSet(CorePackage.CONTROLLER_CALL__COMPONENT, CorePackage.Literals.CONTROLLER_CALL__COMPONENT,
                newComponent);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public OperationSignature getCalledSignature() {
        return (OperationSignature) this.eDynamicGet(CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE,
                CorePackage.Literals.CONTROLLER_CALL__CALLED_SIGNATURE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public OperationSignature basicGetCalledSignature() {
        return (OperationSignature) this.eDynamicGet(CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE,
                CorePackage.Literals.CONTROLLER_CALL__CALLED_SIGNATURE, false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setCalledSignature(final OperationSignature newCalledSignature) {
        this.eDynamicSet(CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE,
                CorePackage.Literals.CONTROLLER_CALL__CALLED_SIGNATURE, newCalledSignature);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceDemandingStep getResourceDemandingStep() {
        return (ResourceDemandingStep) this.eDynamicGet(CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP,
                CorePackage.Literals.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetResourceDemandingStep(final ResourceDemandingStep newResourceDemandingStep,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newResourceDemandingStep,
                CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setResourceDemandingStep(final ResourceDemandingStep newResourceDemandingStep) {
        this.eDynamicSet(CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP,
                CorePackage.Literals.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP, newResourceDemandingStep);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetResourceDemandingStep((ResourceDemandingStep) otherEnd, msgs);
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
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            return this.basicSetResourceDemandingStep(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            return this.eInternalContainer()
                .eInverseRemove(this, CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS,
                        ResourceDemandingStep.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__COMPONENT:
            if (resolve) {
                return this.getComponent();
            }
            return this.basicGetComponent();
        case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
            if (resolve) {
                return this.getCalledSignature();
            }
            return this.basicGetCalledSignature();
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            return this.getResourceDemandingStep();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__COMPONENT:
            this.setComponent((BasicComponent) newValue);
            return;
        case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
            this.setCalledSignature((OperationSignature) newValue);
            return;
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            this.setResourceDemandingStep((ResourceDemandingStep) newValue);
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
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__COMPONENT:
            this.setComponent((BasicComponent) null);
            return;
        case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
            this.setCalledSignature((OperationSignature) null);
            return;
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            this.setResourceDemandingStep((ResourceDemandingStep) null);
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
        switch (featureID) {
        case CorePackage.CONTROLLER_CALL__COMPONENT:
            return this.basicGetComponent() != null;
        case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
            return this.basicGetCalledSignature() != null;
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            return this.getResourceDemandingStep() != null;
        }
        return super.eIsSet(featureID);
    }

} // ControllerCallImpl
