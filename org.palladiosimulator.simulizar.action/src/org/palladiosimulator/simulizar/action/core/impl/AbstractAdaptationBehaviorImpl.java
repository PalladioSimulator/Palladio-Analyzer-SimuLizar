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
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.CorePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Abstract Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl#getAdaptationSteps <em>Adaptation Steps</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class AbstractAdaptationBehaviorImpl extends EntityImpl implements AbstractAdaptationBehavior {
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractAdaptationBehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CorePackage.Literals.ABSTRACT_ADAPTATION_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<AdaptationStep> getAdaptationSteps() {
		if (adaptationSteps == null) {
			adaptationSteps = new EObjectContainmentWithInverseEList<AdaptationStep>(AdaptationStep.class, this,
					CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS,
					CorePackage.ADAPTATION_STEP__ADAPTATION_BEHAVIOR);
		}
		return adaptationSteps;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getAdaptationSteps()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			return ((InternalEList<?>) getAdaptationSteps()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			return getAdaptationSteps();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			getAdaptationSteps().clear();
			getAdaptationSteps().addAll((Collection<? extends AdaptationStep>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			getAdaptationSteps().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS:
			return adaptationSteps != null && !adaptationSteps.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // AbstractAdaptationBehaviorImpl
