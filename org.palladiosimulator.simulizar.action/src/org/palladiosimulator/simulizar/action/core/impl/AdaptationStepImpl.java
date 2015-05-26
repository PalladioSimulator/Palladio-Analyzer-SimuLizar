/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;
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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getControllerCompletionURI <em>Controller Completion URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAdaptationStepURI <em>Adaptation Step URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getPreconditionURI <em>Precondition URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AdaptationStepImpl extends EntityImpl implements AdaptationStep {
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
     * The default value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
	protected static final String PRECONDITION_URI_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getPreconditionURI() <em>Precondition URI</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getPreconditionURI()
     * @generated
     * @ordered
     */
	protected String preconditionURI = PRECONDITION_URI_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected AdaptationStepImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_STEP;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getControllerCompletionURI() {
        return controllerCompletionURI;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setControllerCompletionURI(String newControllerCompletionURI) {
        String oldControllerCompletionURI = controllerCompletionURI;
        controllerCompletionURI = newControllerCompletionURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI, oldControllerCompletionURI, controllerCompletionURI));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getAdaptationStepURI() {
        return adaptationStepURI;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAdaptationStepURI(String newAdaptationStepURI) {
        String oldAdaptationStepURI = adaptationStepURI;
        adaptationStepURI = newAdaptationStepURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI, oldAdaptationStepURI, adaptationStepURI));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public String getPreconditionURI() {
        return preconditionURI;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setPreconditionURI(String newPreconditionURI) {
        String oldPreconditionURI = preconditionURI;
        preconditionURI = newPreconditionURI;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__PRECONDITION_URI, oldPreconditionURI, preconditionURI));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Action getAction() {
        if (eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ACTION) return null;
        return (Action)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAction(Action newAction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newAction, CorePackage.ADAPTATION_STEP__ACTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAction(Action newAction) {
        if (newAction != eInternalContainer() || (eContainerFeatureID() != CorePackage.ADAPTATION_STEP__ACTION && newAction != null)) {
            if (EcoreUtil.isAncestor(this, newAction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newAction != null)
                msgs = ((InternalEObject)newAction).eInverseAdd(this, CorePackage.ACTION__ADAPTATION_STEPS, Action.class, msgs);
            msgs = basicSetAction(newAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_STEP__ACTION, newAction, newAction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.ADAPTATION_STEP__ACTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetAction((Action)otherEnd, msgs);
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
            case CorePackage.ADAPTATION_STEP__ACTION:
                return basicSetAction(null, msgs);
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
            case CorePackage.ADAPTATION_STEP__ACTION:
                return eInternalContainer().eInverseRemove(this, CorePackage.ACTION__ADAPTATION_STEPS, Action.class, msgs);
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
            case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
                return getControllerCompletionURI();
            case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
                return getAdaptationStepURI();
            case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
                return getPreconditionURI();
            case CorePackage.ADAPTATION_STEP__ACTION:
                return getAction();
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
            case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
                setControllerCompletionURI((String)newValue);
                return;
            case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
                setAdaptationStepURI((String)newValue);
                return;
            case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
                setPreconditionURI((String)newValue);
                return;
            case CorePackage.ADAPTATION_STEP__ACTION:
                setAction((Action)newValue);
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
            case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
                setControllerCompletionURI(CONTROLLER_COMPLETION_URI_EDEFAULT);
                return;
            case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
                setAdaptationStepURI(ADAPTATION_STEP_URI_EDEFAULT);
                return;
            case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
                setPreconditionURI(PRECONDITION_URI_EDEFAULT);
                return;
            case CorePackage.ADAPTATION_STEP__ACTION:
                setAction((Action)null);
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
            case CorePackage.ADAPTATION_STEP__CONTROLLER_COMPLETION_URI:
                return CONTROLLER_COMPLETION_URI_EDEFAULT == null ? controllerCompletionURI != null : !CONTROLLER_COMPLETION_URI_EDEFAULT.equals(controllerCompletionURI);
            case CorePackage.ADAPTATION_STEP__ADAPTATION_STEP_URI:
                return ADAPTATION_STEP_URI_EDEFAULT == null ? adaptationStepURI != null : !ADAPTATION_STEP_URI_EDEFAULT.equals(adaptationStepURI);
            case CorePackage.ADAPTATION_STEP__PRECONDITION_URI:
                return PRECONDITION_URI_EDEFAULT == null ? preconditionURI != null : !PRECONDITION_URI_EDEFAULT.equals(preconditionURI);
            case CorePackage.ADAPTATION_STEP__ACTION:
                return getAction() != null;
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
        result.append(" (controllerCompletionURI: ");
        result.append(controllerCompletionURI);
        result.append(", adaptationStepURI: ");
        result.append(adaptationStepURI);
        result.append(", preconditionURI: ");
        result.append(preconditionURI);
        result.append(')');
        return result.toString();
    }

} //AdaptationStepImpl
