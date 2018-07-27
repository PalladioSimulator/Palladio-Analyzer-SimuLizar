/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;
import org.palladiosimulator.simulizar.reconfigurationrule.Strategy;
import org.palladiosimulator.simulizar.reconfigurationrule.Tactic;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl#getSlos <em>Slos</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl#getTactics <em>Tactics</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StrategyImpl extends NamedElementImpl implements Strategy {
	/**
	 * The cached value of the '{@link #getSlos() <em>Slos</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlos()
	 * @generated
	 * @ordered
	 */
	protected ServiceLevelObjectiveRepository slos;

	/**
	 * The cached value of the '{@link #getTactics() <em>Tactics</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTactics()
	 * @generated
	 * @ordered
	 */
	protected EList<Tactic> tactics;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StrategyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ReconfigurationrulePackage.Literals.STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceLevelObjectiveRepository getSlos() {
		if (slos != null && ((EObject)slos).eIsProxy()) {
			InternalEObject oldSlos = (InternalEObject)slos;
			slos = (ServiceLevelObjectiveRepository)eResolveProxy(oldSlos);
			if (slos != oldSlos) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ReconfigurationrulePackage.STRATEGY__SLOS, oldSlos, slos));
			}
		}
		return slos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceLevelObjectiveRepository basicGetSlos() {
		return slos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlos(ServiceLevelObjectiveRepository newSlos) {
		ServiceLevelObjectiveRepository oldSlos = slos;
		slos = newSlos;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ReconfigurationrulePackage.STRATEGY__SLOS, oldSlos, slos));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Tactic> getTactics() {
		if (tactics == null) {
			tactics = new EObjectContainmentEList<Tactic>(Tactic.class, this, ReconfigurationrulePackage.STRATEGY__TACTICS);
		}
		return tactics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ReconfigurationrulePackage.STRATEGY__TACTICS:
				return ((InternalEList<?>)getTactics()).basicRemove(otherEnd, msgs);
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
			case ReconfigurationrulePackage.STRATEGY__SLOS:
				if (resolve) return getSlos();
				return basicGetSlos();
			case ReconfigurationrulePackage.STRATEGY__TACTICS:
				return getTactics();
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
			case ReconfigurationrulePackage.STRATEGY__SLOS:
				setSlos((ServiceLevelObjectiveRepository)newValue);
				return;
			case ReconfigurationrulePackage.STRATEGY__TACTICS:
				getTactics().clear();
				getTactics().addAll((Collection<? extends Tactic>)newValue);
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
			case ReconfigurationrulePackage.STRATEGY__SLOS:
				setSlos((ServiceLevelObjectiveRepository)null);
				return;
			case ReconfigurationrulePackage.STRATEGY__TACTICS:
				getTactics().clear();
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
			case ReconfigurationrulePackage.STRATEGY__SLOS:
				return slos != null;
			case ReconfigurationrulePackage.STRATEGY__TACTICS:
				return tactics != null && !tactics.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //StrategyImpl
