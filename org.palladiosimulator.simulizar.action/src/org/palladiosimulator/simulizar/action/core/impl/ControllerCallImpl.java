/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Controller Call</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getComponent <em>Component</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getCalledSignature <em>Called Signature</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl#getResourceDemandingStep <em>Resource Demanding Step</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ControllerCallImpl extends EntityImpl implements ControllerCall {
	/**
     * The cached value of the '{@link #getComponent() <em>Component</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getComponent()
     * @generated
     * @ordered
     */
	protected BasicComponent component;

	/**
     * The cached value of the '{@link #getCalledSignature() <em>Called Signature</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getCalledSignature()
     * @generated
     * @ordered
     */
	protected OperationSignature calledSignature;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ControllerCallImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CorePackage.Literals.CONTROLLER_CALL;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public BasicComponent getComponent() {
        if (component != null && ((EObject)component).eIsProxy()) {
            InternalEObject oldComponent = (InternalEObject)component;
            component = (BasicComponent)eResolveProxy(oldComponent);
            if (component != oldComponent) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CONTROLLER_CALL__COMPONENT, oldComponent, component));
            }
        }
        return component;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public BasicComponent basicGetComponent() {
        return component;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setComponent(BasicComponent newComponent) {
        BasicComponent oldComponent = component;
        component = newComponent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__COMPONENT, oldComponent, component));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationSignature getCalledSignature() {
        if (calledSignature != null && ((EObject)calledSignature).eIsProxy()) {
            InternalEObject oldCalledSignature = (InternalEObject)calledSignature;
            calledSignature = (OperationSignature)eResolveProxy(oldCalledSignature);
            if (calledSignature != oldCalledSignature) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE, oldCalledSignature, calledSignature));
            }
        }
        return calledSignature;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public OperationSignature basicGetCalledSignature() {
        return calledSignature;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setCalledSignature(OperationSignature newCalledSignature) {
        OperationSignature oldCalledSignature = calledSignature;
        calledSignature = newCalledSignature;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE, oldCalledSignature, calledSignature));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ResourceDemandingStep getResourceDemandingStep() {
        if (eContainerFeatureID() != CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP) return null;
        return (ResourceDemandingStep)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetResourceDemandingStep(ResourceDemandingStep newResourceDemandingStep, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newResourceDemandingStep, CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setResourceDemandingStep(ResourceDemandingStep newResourceDemandingStep) {
        if (newResourceDemandingStep != eInternalContainer() || (eContainerFeatureID() != CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP && newResourceDemandingStep != null)) {
            if (EcoreUtil.isAncestor(this, newResourceDemandingStep))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newResourceDemandingStep != null)
                msgs = ((InternalEObject)newResourceDemandingStep).eInverseAdd(this, CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS, ResourceDemandingStep.class, msgs);
            msgs = basicSetResourceDemandingStep(newResourceDemandingStep, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP, newResourceDemandingStep, newResourceDemandingStep));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetResourceDemandingStep((ResourceDemandingStep)otherEnd, msgs);
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
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                return basicSetResourceDemandingStep(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
        switch (eContainerFeatureID()) {
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                return eInternalContainer().eInverseRemove(this, CorePackage.RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS, ResourceDemandingStep.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case CorePackage.CONTROLLER_CALL__COMPONENT:
                if (resolve) return getComponent();
                return basicGetComponent();
            case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
                if (resolve) return getCalledSignature();
                return basicGetCalledSignature();
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                return getResourceDemandingStep();
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
            case CorePackage.CONTROLLER_CALL__COMPONENT:
                setComponent((BasicComponent)newValue);
                return;
            case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
                setCalledSignature((OperationSignature)newValue);
                return;
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                setResourceDemandingStep((ResourceDemandingStep)newValue);
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
            case CorePackage.CONTROLLER_CALL__COMPONENT:
                setComponent((BasicComponent)null);
                return;
            case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
                setCalledSignature((OperationSignature)null);
                return;
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                setResourceDemandingStep((ResourceDemandingStep)null);
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
            case CorePackage.CONTROLLER_CALL__COMPONENT:
                return component != null;
            case CorePackage.CONTROLLER_CALL__CALLED_SIGNATURE:
                return calledSignature != null;
            case CorePackage.CONTROLLER_CALL__RESOURCE_DEMANDING_STEP:
                return getResourceDemandingStep() != null;
        }
        return super.eIsSet(featureID);
    }

} //ControllerCallImpl
