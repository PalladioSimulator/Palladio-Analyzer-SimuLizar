/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.simulizar.reconfigurationrule.Reconfiguration;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;
import org.palladiosimulator.simulizar.reconfigurationrule.Strategy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reconfiguration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationImpl#getStrategies <em>Strategies</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReconfigurationImpl extends NamedElementImpl implements Reconfiguration {
	/**
	 * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategies()
	 * @generated
	 * @ordered
	 */
	protected EList<Strategy> strategies;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReconfigurationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReconfigurationrulePackage.Literals.RECONFIGURATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Strategy> getStrategies() {
		if (strategies == null) {
			strategies = new EObjectContainmentEList<Strategy>(Strategy.class, this, ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES);
		}
		return strategies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES:
				return ((InternalEList<?>)getStrategies()).basicRemove(otherEnd, msgs);
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
			case ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES:
				return getStrategies();
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
			case ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES:
				getStrategies().clear();
				getStrategies().addAll((Collection<? extends Strategy>)newValue);
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
			case ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES:
				getStrategies().clear();
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
			case ReconfigurationrulePackage.RECONFIGURATION__STRATEGIES:
				return strategies != null && !strategies.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ReconfigurationImpl
