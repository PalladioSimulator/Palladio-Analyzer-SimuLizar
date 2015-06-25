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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Action</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getAdaptationSteps <em>
 * Adaptation Steps</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getInvolvedRoles <em>
 * Involved Roles</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getRepository <em>
 * Repository</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl#getTransientStateProfile
 * <em>Transient State Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActionImpl extends org.palladiosimulator.pcm.core.entity.impl.EntityImpl implements Action {
    /**
     * The cached value of the '{@link #getAdaptationSteps() <em>Adaptation Steps</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getAdaptationSteps()
     * @generated
     * @ordered
     */
    protected EList<AdaptationStep> adaptationSteps;

    /**
     * The cached value of the '{@link #getInvolvedRoles() <em>Involved Roles</em>}' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getInvolvedRoles()
     * @generated
     * @ordered
     */
    protected EList<RoleType> involvedRoles;

    /**
     * The cached value of the '{@link #getTransientStateProfile() <em>Transient State Profile</em>}
     * ' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTransientStateProfile()
     * @generated
     * @ordered
     */
    protected Profile transientStateProfile;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ActionImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ACTION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<AdaptationStep> getAdaptationSteps() {
        if (this.adaptationSteps == null)
        {
            this.adaptationSteps = new EObjectContainmentWithInverseEList<AdaptationStep>(AdaptationStep.class, this,
                    CorePackage.ACTION__ADAPTATION_STEPS, CorePackage.ADAPTATION_STEP__ACTION);
        }
        return this.adaptationSteps;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RoleType> getInvolvedRoles() {
        if (this.involvedRoles == null)
        {
            this.involvedRoles = new EObjectContainmentWithInverseEList<RoleType>(RoleType.class, this,
                    CorePackage.ACTION__INVOLVED_ROLES, CorePackage.ROLE_TYPE__ACTION);
        }
        return this.involvedRoles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ActionRepository getRepository() {
        if (this.eContainerFeatureID() != CorePackage.ACTION__REPOSITORY) {
            return null;
        }
        return (ActionRepository) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRepository(final ActionRepository newRepository, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRepository, CorePackage.ACTION__REPOSITORY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepository(final ActionRepository newRepository) {
        if (newRepository != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.ACTION__REPOSITORY && newRepository != null))
        {
            if (EcoreUtil.isAncestor(this, newRepository)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newRepository != null) {
                msgs = ((InternalEObject) newRepository).eInverseAdd(this, CorePackage.ACTION_REPOSITORY__ACTIONS,
                        ActionRepository.class, msgs);
            }
            msgs = this.basicSetRepository(newRepository, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        }
        else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ACTION__REPOSITORY, newRepository,
                    newRepository));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Profile getTransientStateProfile() {
        if (this.transientStateProfile != null && this.transientStateProfile.eIsProxy())
        {
            final InternalEObject oldTransientStateProfile = (InternalEObject) this.transientStateProfile;
            this.transientStateProfile = (Profile) this.eResolveProxy(oldTransientStateProfile);
            if (this.transientStateProfile != oldTransientStateProfile)
            {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            CorePackage.ACTION__TRANSIENT_STATE_PROFILE, oldTransientStateProfile,
                            this.transientStateProfile));
                }
            }
        }
        return this.transientStateProfile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public Profile basicGetTransientStateProfile() {
        return this.transientStateProfile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTransientStateProfile(final Profile newTransientStateProfile) {
        final Profile oldTransientStateProfile = this.transientStateProfile;
        this.transientStateProfile = newTransientStateProfile;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ACTION__TRANSIENT_STATE_PROFILE,
                    oldTransientStateProfile, this.transientStateProfile));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean execute(final RoleSet affectedRoleSet) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState.createTransientEffectInterpreter(
                affectedRoleSet, this.getRepository()).caseAction(this);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID)
        {
        case CorePackage.ACTION__ADAPTATION_STEPS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getAdaptationSteps()).basicAdd(otherEnd,
                    msgs);
        case CorePackage.ACTION__INVOLVED_ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getInvolvedRoles()).basicAdd(otherEnd,
                    msgs);
        case CorePackage.ACTION__REPOSITORY:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRepository((ActionRepository) otherEnd, msgs);
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
        case CorePackage.ACTION__ADAPTATION_STEPS:
            return ((InternalEList<?>) this.getAdaptationSteps()).basicRemove(otherEnd, msgs);
        case CorePackage.ACTION__INVOLVED_ROLES:
            return ((InternalEList<?>) this.getInvolvedRoles()).basicRemove(otherEnd, msgs);
        case CorePackage.ACTION__REPOSITORY:
            return this.basicSetRepository(null, msgs);
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
        case CorePackage.ACTION__REPOSITORY:
            return this.eInternalContainer().eInverseRemove(this, CorePackage.ACTION_REPOSITORY__ACTIONS,
                    ActionRepository.class, msgs);
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
        case CorePackage.ACTION__ADAPTATION_STEPS:
            return this.getAdaptationSteps();
        case CorePackage.ACTION__INVOLVED_ROLES:
            return this.getInvolvedRoles();
        case CorePackage.ACTION__REPOSITORY:
            return this.getRepository();
        case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
            if (resolve) {
                return this.getTransientStateProfile();
            }
            return this.basicGetTransientStateProfile();
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
        case CorePackage.ACTION__ADAPTATION_STEPS:
            this.getAdaptationSteps().clear();
            this.getAdaptationSteps().addAll((Collection<? extends AdaptationStep>) newValue);
            return;
        case CorePackage.ACTION__INVOLVED_ROLES:
            this.getInvolvedRoles().clear();
            this.getInvolvedRoles().addAll((Collection<? extends RoleType>) newValue);
            return;
        case CorePackage.ACTION__REPOSITORY:
            this.setRepository((ActionRepository) newValue);
            return;
        case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
            this.setTransientStateProfile((Profile) newValue);
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
        case CorePackage.ACTION__ADAPTATION_STEPS:
            this.getAdaptationSteps().clear();
            return;
        case CorePackage.ACTION__INVOLVED_ROLES:
            this.getInvolvedRoles().clear();
            return;
        case CorePackage.ACTION__REPOSITORY:
            this.setRepository((ActionRepository) null);
            return;
        case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
            this.setTransientStateProfile((Profile) null);
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
        case CorePackage.ACTION__ADAPTATION_STEPS:
            return this.adaptationSteps != null && !this.adaptationSteps.isEmpty();
        case CorePackage.ACTION__INVOLVED_ROLES:
            return this.involvedRoles != null && !this.involvedRoles.isEmpty();
        case CorePackage.ACTION__REPOSITORY:
            return this.getRepository() != null;
        case CorePackage.ACTION__TRANSIENT_STATE_PROFILE:
            return this.transientStateProfile != null;
        }
        return super.eIsSet(featureID);
    }

} // ActionImpl
