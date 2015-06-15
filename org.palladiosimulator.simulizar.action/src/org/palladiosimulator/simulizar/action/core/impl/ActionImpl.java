/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.modelversioning.emfprofile.Profile;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.instance.RoleSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Action</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getAdaptationSteps <em>Adaptation Steps</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getInvolvedRoles <em>Involved Roles</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getRepository <em>Repository</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getTransientStateProfile <em>Transient State Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionImpl extends EntityImpl implements Action {
	/**
     * The cached value of the '{@link #getAdaptationSteps() <em>Adaptation Steps</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getAdaptationSteps()
     * @generated
     * @ordered
     */
	protected EList<AdaptationStep> adaptationSteps;

	/**
     * The cached value of the '{@link #getInvolvedRoles() <em>Involved Roles</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getInvolvedRoles()
     * @generated
     * @ordered
     */
	protected EList<RoleType> involvedRoles;

	/**
     * The cached value of the '{@link #getTransientStateProfile() <em>Transient State Profile</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getTransientStateProfile()
     * @generated
     * @ordered
     */
	protected Profile transientStateProfile;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ActionImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CorePackage.Literals.ACTION;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<AdaptationStep> getAdaptationSteps() {
        if (adaptationSteps == null) {
            adaptationSteps = new EObjectContainmentWithInverseEList<AdaptationStep>(AdaptationStep.class, this, CorePackage.ACTION__ADAPTATION_STEPS, CorePackage.ADAPTATION_STEP__ACTION);
        }
        return adaptationSteps;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EList<RoleType> getInvolvedRoles() {
        if (involvedRoles == null) {
            involvedRoles = new EObjectContainmentWithInverseEList<RoleType>(RoleType.class, this, CorePackage.ACTION__INVOLVED_ROLES, CorePackage.ROLE_TYPE__ACTION);
        }
        return involvedRoles;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ActionRepository getRepository() {
        if (eContainerFeatureID() != CorePackage.ACTION__REPOSITORY) return null;
        return (ActionRepository)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetRepository(ActionRepository newRepository, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newRepository, CorePackage.ACTION__REPOSITORY, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRepository(ActionRepository newRepository) {
        if (newRepository != eInternalContainer() || (eContainerFeatureID() != CorePackage.ACTION__REPOSITORY && newRepository != null)) {
            if (EcoreUtil.isAncestor(this, newRepository))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newRepository != null)
                msgs = ((InternalEObject)newRepository).eInverseAdd(this, CorePackage.ACTION_REPOSITORY__ACTIONS, ActionRepository.class, msgs);
            msgs = basicSetRepository(newRepository, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ACTION__REPOSITORY, newRepository, newRepository));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Profile getTransientStateProfile() {
        if (transientStateProfile != null && transientStateProfile.eIsProxy()) {
            InternalEObject oldTransientStateProfile = (InternalEObject)transientStateProfile;
            transientStateProfile = (Profile)eResolveProxy(oldTransientStateProfile);
            if (transientStateProfile != oldTransientStateProfile) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.ACTION__TRANSIENT_STATE_PROFILE, oldTransientStateProfile, transientStateProfile));
            }
        }
        return transientStateProfile;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Profile basicGetTransientStateProfile() {
        return transientStateProfile;
    }

	/**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setTransientStateProfile(Profile newTransientStateProfile) {
        Profile oldTransientStateProfile = transientStateProfile;
        transientStateProfile = newTransientStateProfile;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ACTION__TRANSIENT_STATE_PROFILE, oldTransientStateProfile, transientStateProfile));
    }

    /**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public boolean execute(final RoleSet affectedRoleSet) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState.createTransientEffectInterpreter(affectedRoleSet, getRepository()).caseAction(this);
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getAdaptationSteps()).basicAdd(otherEnd, msgs);
            case CorePackage.ACTION__INVOLVED_ROLES:
                return ((InternalEList<InternalEObject>)(InternalEList<?>)getInvolvedRoles()).basicAdd(otherEnd, msgs);
            case CorePackage.ACTION__REPOSITORY:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetRepository((ActionRepository)otherEnd, msgs);
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                return ((InternalEList<?>)getAdaptationSteps()).basicRemove(otherEnd, msgs);
            case CorePackage.ACTION__INVOLVED_ROLES:
                return ((InternalEList<?>)getInvolvedRoles()).basicRemove(otherEnd, msgs);
            case CorePackage.ACTION__REPOSITORY:
                return basicSetRepository(null, msgs);
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
            case CorePackage.ACTION__REPOSITORY:
                return eInternalContainer().eInverseRemove(this, CorePackage.ACTION_REPOSITORY__ACTIONS, ActionRepository.class, msgs);
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                return getAdaptationSteps();
            case CorePackage.ACTION__INVOLVED_ROLES:
                return getInvolvedRoles();
            case CorePackage.ACTION__REPOSITORY:
                return getRepository();
            case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
                if (resolve) return getTransientStateProfile();
                return basicGetTransientStateProfile();
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                getAdaptationSteps().clear();
                getAdaptationSteps().addAll((Collection<? extends AdaptationStep>)newValue);
                return;
            case CorePackage.ACTION__INVOLVED_ROLES:
                getInvolvedRoles().clear();
                getInvolvedRoles().addAll((Collection<? extends RoleType>)newValue);
                return;
            case CorePackage.ACTION__REPOSITORY:
                setRepository((ActionRepository)newValue);
                return;
            case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
                setTransientStateProfile((Profile)newValue);
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                getAdaptationSteps().clear();
                return;
            case CorePackage.ACTION__INVOLVED_ROLES:
                getInvolvedRoles().clear();
                return;
            case CorePackage.ACTION__REPOSITORY:
                setRepository((ActionRepository)null);
                return;
            case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
                setTransientStateProfile((Profile)null);
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
            case CorePackage.ACTION__ADAPTATION_STEPS:
                return adaptationSteps != null && !adaptationSteps.isEmpty();
            case CorePackage.ACTION__INVOLVED_ROLES:
                return involvedRoles != null && !involvedRoles.isEmpty();
            case CorePackage.ACTION__REPOSITORY:
                return getRepository() != null;
            case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
                return transientStateProfile != null;
        }
        return super.eIsSet(featureID);
    }

} //ActionImpl
