/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;

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
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getResourceDemandingStep
 * <em>Resource Demanding Step</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallImpl extends EntityImpl implements ControllerCall {
    /**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getComponent()
     * @generated
     * @ordered
     */
    protected BasicComponent component;

    /**
     * The cached value of the '{@link #getCalledSignature() <em>Called Signature</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getCalledSignature()
     * @generated
     * @ordered
     */
    protected OperationSignature calledSignature;

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
        if (this.component != null && ((EObject) this.component).eIsProxy()) {
            final InternalEObject oldComponent = (InternalEObject) this.component;
            this.component = (BasicComponent) this.eResolveProxy(oldComponent);
            if (this.component != oldComponent) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            CorePackage.CONTROLLER_CALL__COMPONENT, oldComponent, this.component));
                }
            }
        }
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BasicComponent basicGetComponent() {
        return this.component;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setComponent(final BasicComponent newComponent) {
        final BasicComponent oldComponent = this.component;
        this.component = newComponent;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__COMPONENT,
                    oldComponent, this.component));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public OperationSignature getCalledSignature() {
        if (this.calledSignature != null && ((EObject) this.calledSignature).eIsProxy()) {
            final InternalEObject oldCalledSignature = (InternalEObject) this.calledSignature;
            this.calledSignature = (OperationSignature) this.eResolveProxy(oldCalledSignature);
            if (this.calledSignature != oldCalledSignature) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE, oldCalledSignature, this.calledSignature));
                }
            }
        }
        return this.calledSignature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public OperationSignature basicGetCalledSignature() {
        return this.calledSignature;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setCalledSignature(final OperationSignature newCalledSignature) {
        final OperationSignature oldCalledSignature = this.calledSignature;
        this.calledSignature = newCalledSignature;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE,
                    oldCalledSignature, this.calledSignature));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceDemandingAction getResourceDemandingStep() {
        if (this.eContainerFeatureID() != CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP) {
            return null;
        }
        return (ResourceDemandingAction) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetResourceDemandingStep(final ResourceDemandingAction newResourceDemandingStep,
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
    public void setResourceDemandingStep(final ResourceDemandingAction newResourceDemandingStep) {
        if (newResourceDemandingStep != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP
                        && newResourceDemandingStep != null)) {
            if (EcoreUtil.isAncestor(this, newResourceDemandingStep)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newResourceDemandingStep != null) {
                msgs = ((InternalEObject) newResourceDemandingStep).eInverseAdd(this,
                        CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS, ResourceDemandingAction.class, msgs);
            }
            msgs = this.basicSetResourceDemandingStep(newResourceDemandingStep, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(
                    new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP,
                            newResourceDemandingStep, newResourceDemandingStep));
        }
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
            return this.basicSetResourceDemandingStep((ResourceDemandingAction) otherEnd, msgs);
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
            return this.eInternalContainer().eInverseRemove(this,
                    CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS, ResourceDemandingAction.class, msgs);
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
            this.setResourceDemandingStep((ResourceDemandingAction) newValue);
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
            this.setResourceDemandingStep((ResourceDemandingAction) null);
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
            return this.component != null;
        case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
            return this.calledSignature != null;
        case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
            return this.getResourceDemandingStep() != null;
        }
        return super.eIsSet(featureID);
    }

} // ControllerCallImpl
