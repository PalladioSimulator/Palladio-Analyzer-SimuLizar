/**
 */
package strategies.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import strategies.StrategiesPackage;
import strategies.Strategy;
import strategies.StrategyType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strategy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link strategies.impl.StrategyImpl#getStrategyType <em>Strategy Type</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StrategyImpl extends org.palladiosimulator.pcm.core.entity.impl.EntityImpl implements Strategy {
	/**
	 * The cached value of the '{@link #getStrategyType() <em>Strategy Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStrategyType()
	 * @generated
	 * @ordered
	 */
	protected StrategyType strategyType;

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
		return StrategiesPackage.Literals.STRATEGY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType getStrategyType() {
		if (strategyType != null && strategyType.eIsProxy()) {
			InternalEObject oldStrategyType = (InternalEObject)strategyType;
			strategyType = (StrategyType)eResolveProxy(oldStrategyType);
			if (strategyType != oldStrategyType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, StrategiesPackage.STRATEGY__STRATEGY_TYPE, oldStrategyType, strategyType));
			}
		}
		return strategyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategyType basicGetStrategyType() {
		return strategyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrategyType(StrategyType newStrategyType) {
		StrategyType oldStrategyType = strategyType;
		strategyType = newStrategyType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StrategiesPackage.STRATEGY__STRATEGY_TYPE, oldStrategyType, strategyType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case StrategiesPackage.STRATEGY__STRATEGY_TYPE:
				if (resolve) return getStrategyType();
				return basicGetStrategyType();
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
			case StrategiesPackage.STRATEGY__STRATEGY_TYPE:
				setStrategyType((StrategyType)newValue);
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
			case StrategiesPackage.STRATEGY__STRATEGY_TYPE:
				setStrategyType((StrategyType)null);
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
			case StrategiesPackage.STRATEGY__STRATEGY_TYPE:
				return strategyType != null;
		}
		return super.eIsSet(featureID);
	}

} //StrategyImpl
