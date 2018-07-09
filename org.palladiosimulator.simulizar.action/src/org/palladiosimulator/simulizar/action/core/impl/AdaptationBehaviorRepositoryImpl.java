/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adaptation Behavior Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl#getActions <em>Actions</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl#getIncludedRepositories <em>Included Repositories</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdaptationBehaviorRepositoryImpl extends EntityImpl implements AdaptationBehaviorRepository {
	/**
	 * The cached value of the '{@link #getActions() <em>Actions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActions()
	 * @generated
	 * @ordered
	 */
	protected EList<AdaptationBehavior> actions;

	/**
	 * The cached value of the '{@link #getIncludedRepositories() <em>Included Repositories</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedRepositories()
	 * @generated
	 * @ordered
	 */
	protected EList<AdaptationBehaviorRepository> includedRepositories;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdaptationBehaviorRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ADAPTATION_BEHAVIOR_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AdaptationBehavior> getActions() {
		if (actions == null) {
			actions = new EObjectContainmentWithInverseEList<AdaptationBehavior>(AdaptationBehavior.class, this,
					CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS, CorePackage.ADAPTATION_BEHAVIOR__REPOSITORY);
		}
		return actions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AdaptationBehaviorRepository> getIncludedRepositories() {
		if (includedRepositories == null) {
			includedRepositories = new EObjectResolvingEList<AdaptationBehaviorRepository>(
					AdaptationBehaviorRepository.class, this,
					CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES);
		}
		return includedRepositories;
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
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getActions()).basicAdd(otherEnd, msgs);
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
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			return ((InternalEList<?>) getActions()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			return getActions();
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
			return getIncludedRepositories();
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
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			getActions().clear();
			getActions().addAll((Collection<? extends AdaptationBehavior>) newValue);
			return;
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
			getIncludedRepositories().clear();
			getIncludedRepositories().addAll((Collection<? extends AdaptationBehaviorRepository>) newValue);
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
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			getActions().clear();
			return;
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
			getIncludedRepositories().clear();
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
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS:
			return actions != null && !actions.isEmpty();
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES:
			return includedRepositories != null && !includedRepositories.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AdaptationBehaviorRepositoryImpl
