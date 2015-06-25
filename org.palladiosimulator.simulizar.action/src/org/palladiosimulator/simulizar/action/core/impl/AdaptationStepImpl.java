/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getControllerCompletionURI
 * <em>Controller Completion URI</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAdaptationStepURI
 * <em>Adaptation Step URI</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getPreconditionURI
 * <em>Precondition URI</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAction <em>
 * Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AdaptationStepImpl extends org.palladiosimulator.pcm.core.entity.impl.EntityImpl implements
AdaptationStep {
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
     * The default value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdaptationStepURI()
     * @generated
     * @ordered
     */
    protected static final String ADAPTATION_STEP_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getAdaptationStepURI() <em>Adaptation Step URI</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdaptationStepURI()
     * @generated
     * @ordered
     */
    protected String adaptationStepURI = ADAPTATION_STEP_URI_EDEFAULT;

    /**
     * The default value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
    protected static final String PRECONDITION_URI_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
    protected String preconditionURI = PRECONDITION_URI_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected AdaptationStepImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_STEP;
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
                    CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI, oldControllerCompletionURI,
                    this.controllerCompletionURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getAdaptationStepURI() {
        return this.adaptationStepURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAdaptationStepURI(final String newAdaptationStepURI) {
        final String oldAdaptationStepURI = this.adaptationStepURI;
        this.adaptationStepURI = newAdaptationStepURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI,
                    oldAdaptationStepURI, this.adaptationStepURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getPreconditionURI() {
        return this.preconditionURI;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPreconditionURI(final String newPreconditionURI) {
        final String oldPreconditionURI = this.preconditionURI;
        this.preconditionURI = newPreconditionURI;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__PRECONDITION_URI,
                    oldPreconditionURI, this.preconditionURI));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Action getAction() {
        if (this.eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ACTION) {
            return null;
        }
        return (Action) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAction(final Action newAction, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAction, CorePackage.ADAPTATION_STEP__ACTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAction(final Action newAction) {
        if (newAction != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ACTION && newAction != null))
        {
            if (EcoreUtil.isAncestor(this, newAction)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAction != null) {
                msgs = ((InternalEObject) newAction).eInverseAdd(this, CorePackage.ACTION__ADAPTATION_STEPS,
                        Action.class, msgs);
            }
            msgs = this.basicSetAction(newAction, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        }
        else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__ACTION, newAction,
                    newAction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID)
        {
        case CorePackage.ADAPTATION_STEP__ACTION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAction((Action) otherEnd, msgs);
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
        case CorePackage.ADAPTATION_STEP__ACTION:
            return this.basicSetAction(null, msgs);
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
        switch (this.eContainerFeatureID())
        {
        case CorePackage.ADAPTATION_STEP__ACTION:
            return this.eInternalContainer().eInverseRemove(this, CorePackage.ACTION__ADAPTATION_STEPS, Action.class,
                    msgs);
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
        switch (featureID)
        {
        case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
            return this.getControllerCompletionURI();
        case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
            return this.getAdaptationStepURI();
        case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
            return this.getPreconditionURI();
        case CorePackage.ADAPTATION_STEP__ACTION:
            return this.getAction();
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
        switch (featureID)
        {
        case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
            this.setControllerCompletionURI((String) newValue);
            return;
        case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
            this.setAdaptationStepURI((String) newValue);
            return;
        case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
            this.setPreconditionURI((String) newValue);
            return;
        case CorePackage.ADAPTATION_STEP__ACTION:
            this.setAction((Action) newValue);
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
        case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
            this.setControllerCompletionURI(CONTROLLER_COMPLETION_URI_EDEFAULT);
            return;
        case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
            this.setAdaptationStepURI(ADAPTATION_STEP_URI_EDEFAULT);
            return;
        case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
            this.setPreconditionURI(PRECONDITION_URI_EDEFAULT);
            return;
        case CorePackage.ADAPTATION_STEP__ACTION:
            this.setAction((Action) null);
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
        case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
            return CONTROLLER_COMPLETION_URI_EDEFAULT == null ? this.controllerCompletionURI != null
            : !CONTROLLER_COMPLETION_URI_EDEFAULT.equals(this.controllerCompletionURI);
        case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
            return ADAPTATION_STEP_URI_EDEFAULT == null ? this.adaptationStepURI != null
                    : !ADAPTATION_STEP_URI_EDEFAULT
                            .equals(this.adaptationStepURI);
        case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
            return PRECONDITION_URI_EDEFAULT == null ? this.preconditionURI != null : !PRECONDITION_URI_EDEFAULT
            .equals(this.preconditionURI);
        case CorePackage.ADAPTATION_STEP__ACTION:
            return this.getAction() != null;
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
        result.append(", adaptationStepURI: ");
        result.append(this.adaptationStepURI);
        result.append(", preconditionURI: ");
        result.append(this.preconditionURI);
        result.append(')');
        return result.toString();
    }

} // AdaptationStepImpl
