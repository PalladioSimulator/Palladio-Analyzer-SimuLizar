/**
 */
package vsmappings.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import strategies.StrategyType;

import violations.ViolationType;

import vsmappings.VSMapping;
import vsmappings.VsmappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>VS Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link vsmappings.impl.VSMappingImpl#getViolation <em>Violation</em>}</li>
 *   <li>{@link vsmappings.impl.VSMappingImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link vsmappings.impl.VSMappingImpl#getStrategyPriority <em>Strategy Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VSMappingImpl extends EntityImpl implements VSMapping {
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
	 * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategies()
	 * @generated
	 * @ordered
	 */
	protected StrategyType strategies;

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
	protected VSMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VsmappingsPackage.Literals.VS_MAPPING;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, VsmappingsPackage.VS_MAPPING__VIOLATION, oldViolation, violation));
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
			eNotify(new ENotificationImpl(this, Notification.SET, VsmappingsPackage.VS_MAPPING__VIOLATION, oldViolation, violation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType getStrategies() {
		if (strategies != null && strategies.eIsProxy()) {
			InternalEObject oldStrategies = (InternalEObject)strategies;
			strategies = (StrategyType)eResolveProxy(oldStrategies);
			if (strategies != oldStrategies) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, VsmappingsPackage.VS_MAPPING__STRATEGIES, oldStrategies, strategies));
			}
		}
		return strategies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType basicGetStrategies() {
		return strategies;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategies(StrategyType newStrategies) {
		StrategyType oldStrategies = strategies;
		strategies = newStrategies;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, VsmappingsPackage.VS_MAPPING__STRATEGIES, oldStrategies, strategies));
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
			eNotify(new ENotificationImpl(this, Notification.SET, VsmappingsPackage.VS_MAPPING__STRATEGY_PRIORITY, oldStrategyPriority, strategyPriority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case VsmappingsPackage.VS_MAPPING__VIOLATION:
				if (resolve) return getViolation();
				return basicGetViolation();
			case VsmappingsPackage.VS_MAPPING__STRATEGIES:
				if (resolve) return getStrategies();
				return basicGetStrategies();
			case VsmappingsPackage.VS_MAPPING__STRATEGY_PRIORITY:
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
			case VsmappingsPackage.VS_MAPPING__VIOLATION:
				setViolation((ViolationType)newValue);
				return;
			case VsmappingsPackage.VS_MAPPING__STRATEGIES:
				setStrategies((StrategyType)newValue);
				return;
			case VsmappingsPackage.VS_MAPPING__STRATEGY_PRIORITY:
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
			case VsmappingsPackage.VS_MAPPING__VIOLATION:
				setViolation((ViolationType)null);
				return;
			case VsmappingsPackage.VS_MAPPING__STRATEGIES:
				setStrategies((StrategyType)null);
				return;
			case VsmappingsPackage.VS_MAPPING__STRATEGY_PRIORITY:
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
			case VsmappingsPackage.VS_MAPPING__VIOLATION:
				return violation != null;
			case VsmappingsPackage.VS_MAPPING__STRATEGIES:
				return strategies != null;
			case VsmappingsPackage.VS_MAPPING__STRATEGY_PRIORITY:
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

} //VSMappingImpl
