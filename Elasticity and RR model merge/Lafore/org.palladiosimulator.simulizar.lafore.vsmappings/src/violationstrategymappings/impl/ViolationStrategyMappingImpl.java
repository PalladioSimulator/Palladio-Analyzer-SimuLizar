/**
 */
package violationstrategymappings.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import strategies.StrategyType;

import violations.ViolationType;

import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationstrategymappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Violation Strategy Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link violationstrategymappings.impl.ViolationStrategyMappingImpl#getViolation <em>Violation</em>}</li>
 *   <li>{@link violationstrategymappings.impl.ViolationStrategyMappingImpl#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link violationstrategymappings.impl.ViolationStrategyMappingImpl#getStrategyPriority <em>Strategy Priority</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViolationStrategyMappingImpl extends EntityImpl implements ViolationStrategyMapping {
	/**
	 * The cached value of the '{@link #getViolation() <em>Violation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolation()
	 * @generated
	 * @ordered
	 */
	protected ViolationType violation;

	/**
	 * The cached value of the '{@link #getStrategy() <em>Strategy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategy()
	 * @generated
	 * @ordered
	 */
	protected StrategyType strategy;

	/**
	 * The default value of the '{@link #getStrategyPriority() <em>Strategy Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategyPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int STRATEGY_PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStrategyPriority() <em>Strategy Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategyPriority()
	 * @generated
	 * @ordered
	 */
	protected int strategyPriority = STRATEGY_PRIORITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViolationStrategyMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationstrategymappingsPackage.Literals.VIOLATION_STRATEGY_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationType getViolation() {
		if (violation != null && violation.eIsProxy()) {
			InternalEObject oldViolation = (InternalEObject)violation;
			violation = (ViolationType)eResolveProxy(oldViolation);
			if (violation != oldViolation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION, oldViolation, violation));
			}
		}
		return violation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationType basicGetViolation() {
		return violation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setViolation(ViolationType newViolation) {
		ViolationType oldViolation = violation;
		violation = newViolation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION, oldViolation, violation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType getStrategy() {
		if (strategy != null && strategy.eIsProxy()) {
			InternalEObject oldStrategy = (InternalEObject)strategy;
			strategy = (StrategyType)eResolveProxy(oldStrategy);
			if (strategy != oldStrategy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY, oldStrategy, strategy));
			}
		}
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType basicGetStrategy() {
		return strategy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategy(StrategyType newStrategy) {
		StrategyType oldStrategy = strategy;
		strategy = newStrategy;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY, oldStrategy, strategy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStrategyPriority() {
		return strategyPriority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategyPriority(int newStrategyPriority) {
		int oldStrategyPriority = strategyPriority;
		strategyPriority = newStrategyPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY, oldStrategyPriority, strategyPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION:
				if (resolve) return getViolation();
				return basicGetViolation();
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY:
				if (resolve) return getStrategy();
				return basicGetStrategy();
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY:
				return getStrategyPriority();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION:
				setViolation((ViolationType)newValue);
				return;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY:
				setStrategy((StrategyType)newValue);
				return;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY:
				setStrategyPriority((Integer)newValue);
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION:
				setViolation((ViolationType)null);
				return;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY:
				setStrategy((StrategyType)null);
				return;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY:
				setStrategyPriority(STRATEGY_PRIORITY_EDEFAULT);
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__VIOLATION:
				return violation != null;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY:
				return strategy != null;
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY:
				return strategyPriority != STRATEGY_PRIORITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (strategyPriority: ");
		result.append(strategyPriority);
		result.append(')');
		return result.toString();
	}

} //ViolationStrategyMappingImpl
