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
import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.instance.RoleSet;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getInvolvedRoles
 * <em>Involved Roles</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getTransientStateProfile
 * <em>Transient State Profile</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getRepository
 * <em>Repository</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationBehaviorImpl extends AbstractAdaptationBehaviorImpl implements AdaptationBehavior {
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
    protected AdaptationBehaviorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ADAPTATION_BEHAVIOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EList<RoleType> getInvolvedRoles() {
        if (this.involvedRoles == null) {
            this.involvedRoles = new EObjectContainmentWithInverseEList<RoleType>(RoleType.class, this,
                    CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES, CorePackage.ROLE_TYPE__ACTION);
        }
        return this.involvedRoles;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Profile getTransientStateProfile() {
        if (this.transientStateProfile != null && this.transientStateProfile.eIsProxy()) {
            final InternalEObject oldTransientStateProfile = (InternalEObject) this.transientStateProfile;
            this.transientStateProfile = (Profile) this.eResolveProxy(oldTransientStateProfile);
            if (this.transientStateProfile != oldTransientStateProfile) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE, oldTransientStateProfile,
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
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE, oldTransientStateProfile,
                    this.transientStateProfile));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AdaptationBehaviorRepository getRepository() {
        if (this.eContainerFeatureID() != CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY) {
            return null;
        }
        return (AdaptationBehaviorRepository) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRepository(final AdaptationBehaviorRepository newRepository,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRepository, CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY,
                msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRepository(final AdaptationBehaviorRepository newRepository) {
        if (newRepository != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY
                        && newRepository != null)) {
            if (EcoreUtil.isAncestor(this, newRepository)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newRepository != null) {
                msgs = ((InternalEObject) newRepository).eInverseAdd(this,
                        CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS, AdaptationBehaviorRepository.class, msgs);
            }
            msgs = this.basicSetRepository(newRepository, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY,
                    newRepository, newRepository));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean execute(final RoleSet affectedRoleSet,
            final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository())
                .addControllerCallVariableUsages(controllerCallsVariableUsages).build().doSwitch(this)
                .getExecutionResultAsBoolean();

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean execute(final RoleSet affectedRoleSet,
            final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            final ExecutionContext executionContext) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository())
                .addControllerCallVariableUsages(controllerCallsVariableUsages).addExecutionContext(executionContext)
                .build().doSwitch(this).getExecutionResultAsBoolean();

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean execute(final RoleSet affectedRoleSet) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository()).build().doSwitch(this)
                .getExecutionResultAsBoolean();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean execute(final RoleSet affectedRoleSet, final ExecutionContext executionContext) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository()).addExecutionContext(executionContext)
                .build().doSwitch(this).getExecutionResultAsBoolean();

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionContext executeAsync(final RoleSet affectedRoleSet,
            final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository())
                .addControllerCallVariableUsages(controllerCallsVariableUsages).isAsync().build().doSwitch(this)
                .getContext().get();

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionContext executeAsync(final RoleSet affectedRoleSet) {
        return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository()).isAsync().build().doSwitch(this)
                .getContext().get();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionContext executeAsync(final RoleSet affectedRoleSet, final ExecutionContext asyncExecutionContext) {
        org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository()).isAsync(asyncExecutionContext).build()
                .doSwitch(this);
        return asyncExecutionContext;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExecutionContext executeAsync(final RoleSet affectedRoleSet,
            final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
            final ExecutionContext asyncExecutionContext) {
        org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
                .getInterpreterBuilder(affectedRoleSet, this.getRepository()).isAsync(asyncExecutionContext)
                .addControllerCallVariableUsages(controllerCallsVariableUsages).build().doSwitch(this);
        return asyncExecutionContext;

    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getInvolvedRoles()).basicAdd(otherEnd,
                    msgs);
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRepository((AdaptationBehaviorRepository) otherEnd, msgs);
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
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            return ((InternalEList<?>) this.getInvolvedRoles()).basicRemove(otherEnd, msgs);
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
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
        switch (this.eContainerFeatureID()) {
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            return this.eInternalContainer().eInverseRemove(this, CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS,
                    AdaptationBehaviorRepository.class, msgs);
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
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            return this.getInvolvedRoles();
        case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
            if (resolve) {
                return this.getTransientStateProfile();
            }
            return this.basicGetTransientStateProfile();
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            return this.getRepository();
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
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            this.getInvolvedRoles().clear();
            this.getInvolvedRoles().addAll((Collection<? extends RoleType>) newValue);
            return;
        case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
            this.setTransientStateProfile((Profile) newValue);
            return;
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            this.setRepository((AdaptationBehaviorRepository) newValue);
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
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            this.getInvolvedRoles().clear();
            return;
        case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
            this.setTransientStateProfile((Profile) null);
            return;
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            this.setRepository((AdaptationBehaviorRepository) null);
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
        case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
            return this.involvedRoles != null && !this.involvedRoles.isEmpty();
        case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
            return this.transientStateProfile != null;
        case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
            return this.getRepository() != null;
        }
        return super.eIsSet(featureID);
    }

} // AdaptationBehaviorImpl
