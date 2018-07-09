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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getInvolvedRoles <em>Involved Roles</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getTransientStateProfile <em>Transient State Profile</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl#getRepository <em>Repository</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationBehaviorImpl extends AbstractAdaptationBehaviorImpl implements AdaptationBehavior {
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
	protected AdaptationBehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ADAPTATION_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<RoleType> getInvolvedRoles() {
		if (involvedRoles == null) {
			involvedRoles = new EObjectContainmentWithInverseEList<RoleType>(RoleType.class, this,
					CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES, CorePackage.ROLE_TYPE__ACTION);
		}
		return involvedRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Profile getTransientStateProfile() {
		if (transientStateProfile != null && transientStateProfile.eIsProxy()) {
			InternalEObject oldTransientStateProfile = (InternalEObject) transientStateProfile;
			transientStateProfile = (Profile) eResolveProxy(oldTransientStateProfile);
			if (transientStateProfile != oldTransientStateProfile) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE, oldTransientStateProfile,
							transientStateProfile));
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
	@Override
	public void setTransientStateProfile(Profile newTransientStateProfile) {
		Profile oldTransientStateProfile = transientStateProfile;
		transientStateProfile = newTransientStateProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE, oldTransientStateProfile,
					transientStateProfile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationBehaviorRepository getRepository() {
		if (eContainerFeatureID() != CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY)
			return null;
		return (AdaptationBehaviorRepository) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRepository(AdaptationBehaviorRepository newRepository, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newRepository, CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRepository(AdaptationBehaviorRepository newRepository) {
		if (newRepository != eInternalContainer()
				|| (eContainerFeatureID() != CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY && newRepository != null)) {
			if (EcoreUtil.isAncestor(this, newRepository))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRepository != null)
				msgs = ((InternalEObject) newRepository).eInverseAdd(this,
						CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS, AdaptationBehaviorRepository.class, msgs);
			msgs = basicSetRepository(newRepository, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY,
					newRepository, newRepository));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean execute(final RoleSet affectedRoleSet,
			final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository())
				.addControllerCallVariableUsages(controllerCallsVariableUsages).build().doSwitch(this)
				.getExecutionResultAsBoolean();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean execute(final RoleSet affectedRoleSet,
			final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
			final ExecutionContext executionContext) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository())
				.addControllerCallVariableUsages(controllerCallsVariableUsages).addExecutionContext(executionContext)
				.build().doSwitch(this).getExecutionResultAsBoolean();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean execute(final RoleSet affectedRoleSet) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository()).build().doSwitch(this)
				.getExecutionResultAsBoolean();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean execute(final RoleSet affectedRoleSet, final ExecutionContext executionContext) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository()).addExecutionContext(executionContext).build()
				.doSwitch(this).getExecutionResultAsBoolean();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionContext executeAsync(final RoleSet affectedRoleSet,
			final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository())
				.addControllerCallVariableUsages(controllerCallsVariableUsages).isAsync().build().doSwitch(this)
				.getContext().get();

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionContext executeAsync(final RoleSet affectedRoleSet,
			final ControllerCallInputVariableUsageCollection controllerCallsVariableUsages,
			final ExecutionContext asyncExecutionContext) {
		org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository()).isAsync(asyncExecutionContext)
				.addControllerCallVariableUsages(controllerCallsVariableUsages).build().doSwitch(this);
		return asyncExecutionContext;

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionContext executeAsync(final RoleSet affectedRoleSet) {
		return org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository()).isAsync().build().doSwitch(this).getContext()
				.get();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecutionContext executeAsync(final RoleSet affectedRoleSet, final ExecutionContext asyncExecutionContext) {
		org.palladiosimulator.simulizar.action.interpreter.ActionRuntimeState
				.getInterpreterBuilder(affectedRoleSet, getRepository()).isAsync(asyncExecutionContext).build()
				.doSwitch(this);
		return asyncExecutionContext;

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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getInvolvedRoles()).basicAdd(otherEnd, msgs);
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetRepository((AdaptationBehaviorRepository) otherEnd, msgs);
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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			return ((InternalEList<?>) getInvolvedRoles()).basicRemove(otherEnd, msgs);
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
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
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			return eInternalContainer().eInverseRemove(this, CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS,
					AdaptationBehaviorRepository.class, msgs);
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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			return getInvolvedRoles();
		case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
			if (resolve)
				return getTransientStateProfile();
			return basicGetTransientStateProfile();
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			return getRepository();
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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			getInvolvedRoles().clear();
			getInvolvedRoles().addAll((Collection<? extends RoleType>) newValue);
			return;
		case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
			setTransientStateProfile((Profile) newValue);
			return;
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			setRepository((AdaptationBehaviorRepository) newValue);
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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			getInvolvedRoles().clear();
			return;
		case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
			setTransientStateProfile((Profile) null);
			return;
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			setRepository((AdaptationBehaviorRepository) null);
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
		case CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES:
			return involvedRoles != null && !involvedRoles.isEmpty();
		case CorePackage.ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE:
			return transientStateProfile != null;
		case CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY:
			return getRepository() != null;
		}
		return super.eIsSet(featureID);
	}

} //AdaptationBehaviorImpl
