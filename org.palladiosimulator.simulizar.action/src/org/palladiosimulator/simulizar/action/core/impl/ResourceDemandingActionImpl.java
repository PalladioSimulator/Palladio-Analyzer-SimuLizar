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
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Resource Demanding Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl#getControllerCompletionURI
 * <em>Controller Completion URI</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl#getControllerCalls
 * <em>Controller Calls</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceDemandingActionImpl extends AdaptationActionImpl implements ResourceDemandingAction {
    /**
     * The default value of the '{@link #getControllerCompletionURI()
     * <em>Controller Completion URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getControllerCompletionURI()
     * @generated
     * @ordered
     */
    protected static final String CONTROLLER_COMPLETION_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getControllerCompletionURI()
     * <em>Controller Completion URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getControllerCompletionURI()
     * @generated
     * @ordered
     */
    protected String controllerCompletionURI = CONTROLLER_COMPLETION_URI_EDEFAULT;

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
    protected ResourceDemandingActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.RESOURCE_DEMANDING_ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String getControllerCompletionURI() {
        return this.controllerCompletionURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setControllerCompletionURI(final String newControllerCompletionURI) {
        final String oldControllerCompletionURI = this.controllerCompletionURI;
        this.controllerCompletionURI = newControllerCompletionURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI, oldControllerCompletionURI,
                    this.controllerCompletionURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<ControllerCall> getControllerCalls() {
        if (this.controllerCalls == null) {
            this.controllerCalls = new EObjectContainmentWithInverseEList<ControllerCall>(ControllerCall.class, this,
                    CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS,
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI:
            return this.getControllerCompletionURI();
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI:
            this.setControllerCompletionURI((String) newValue);
            return;
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI:
            this.setControllerCompletionURI(CONTROLLER_COMPLETION_URI_EDEFAULT);
            return;
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
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
        switch (featureID) {
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI:
            return CONTROLLER_COMPLETION_URI_EDEFAULT == null ? this.controllerCompletionURI != null
                    : !CONTROLLER_COMPLETION_URI_EDEFAULT.equals(this.controllerCompletionURI);
        case CorePackage.RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS:
            return this.controllerCalls != null && !this.controllerCalls.isEmpty();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (controllerCompletionURI: ");
        result.append(this.controllerCompletionURI);
        result.append(')');
        return result.toString();
    }

} // ResourceDemandingActionImpl
