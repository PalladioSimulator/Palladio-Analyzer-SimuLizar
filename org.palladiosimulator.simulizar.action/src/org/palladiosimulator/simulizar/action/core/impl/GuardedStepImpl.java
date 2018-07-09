/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.GuardedStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Guarded Step</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.GuardedStepImpl#getGuardedTransitions <em>Guarded Transitions</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GuardedStepImpl extends AdaptationStepImpl implements GuardedStep {
	/**
	 * The cached value of the '{@link #getGuardedTransitions() <em>Guarded Transitions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGuardedTransitions()
	 * @generated
	 * @ordered
	 */
	protected EList<GuardedTransition> guardedTransitions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GuardedStepImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.GUARDED_STEP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<GuardedTransition> getGuardedTransitions() {
		if (guardedTransitions == null) {
			guardedTransitions = new EObjectContainmentWithInverseEList<GuardedTransition>(GuardedTransition.class,
					this, CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS, CorePackage.GUARDED_TRANSITION__GUARDED_STEP);
		}
		return guardedTransitions;
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getGuardedTransitions()).basicAdd(otherEnd,
					msgs);
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			return ((InternalEList<?>) getGuardedTransitions()).basicRemove(otherEnd, msgs);
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			return getGuardedTransitions();
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			getGuardedTransitions().clear();
			getGuardedTransitions().addAll((Collection<? extends GuardedTransition>) newValue);
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			getGuardedTransitions().clear();
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
		case CorePackage.GUARDED_STEP__GUARDED_TRANSITIONS:
			return guardedTransitions != null && !guardedTransitions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GuardedStepImpl
