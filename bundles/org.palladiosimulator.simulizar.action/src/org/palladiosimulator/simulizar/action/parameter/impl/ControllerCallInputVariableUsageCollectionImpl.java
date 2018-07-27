/**
 */
package org.palladiosimulator.simulizar.action.parameter.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;

import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage;
import org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Controller Call Input Variable Usage Collection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageCollectionImpl#getControllerCallInputVariableUsages <em>Controller Call Input Variable Usages</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ControllerCallInputVariableUsageCollectionImpl extends EntityImpl
		implements ControllerCallInputVariableUsageCollection {
	/**
	 * The cached value of the '{@link #getControllerCallInputVariableUsages() <em>Controller Call Input Variable Usages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getControllerCallInputVariableUsages()
	 * @generated
	 * @ordered
	 */
	protected EList<ControllerCallInputVariableUsage> controllerCallInputVariableUsages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ControllerCallInputVariableUsageCollectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ParameterPackage.Literals.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ControllerCallInputVariableUsage> getControllerCallInputVariableUsages() {
		if (controllerCallInputVariableUsages == null) {
			controllerCallInputVariableUsages = new EObjectContainmentWithInverseEList<ControllerCallInputVariableUsage>(
					ControllerCallInputVariableUsage.class, this,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES,
					ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION);
		}
		return controllerCallInputVariableUsages;
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getControllerCallInputVariableUsages())
					.basicAdd(otherEnd, msgs);
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			return ((InternalEList<?>) getControllerCallInputVariableUsages()).basicRemove(otherEnd, msgs);
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			return getControllerCallInputVariableUsages();
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			getControllerCallInputVariableUsages().clear();
			getControllerCallInputVariableUsages()
					.addAll((Collection<? extends ControllerCallInputVariableUsage>) newValue);
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			getControllerCallInputVariableUsages().clear();
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
		case ParameterPackage.CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES:
			return controllerCallInputVariableUsages != null && !controllerCallInputVariableUsages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ControllerCallInputVariableUsageCollectionImpl
