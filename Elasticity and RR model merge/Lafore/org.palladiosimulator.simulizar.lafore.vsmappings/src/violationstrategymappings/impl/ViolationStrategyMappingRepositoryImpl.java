/**
 */
package violationstrategymappings.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationStrategyMappingRepository;
import violationstrategymappings.ViolationstrategymappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Violation Strategy Mapping Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link violationstrategymappings.impl.ViolationStrategyMappingRepositoryImpl#getVsmappings <em>Vsmappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViolationStrategyMappingRepositoryImpl extends EntityImpl implements ViolationStrategyMappingRepository {
	/**
	 * The cached value of the '{@link #getVsmappings() <em>Vsmappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVsmappings()
	 * @generated
	 * @ordered
	 */
	protected EList<ViolationStrategyMapping> vsmappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViolationStrategyMappingRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationstrategymappingsPackage.Literals.VIOLATION_STRATEGY_MAPPING_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ViolationStrategyMapping> getVsmappings() {
		if (vsmappings == null) {
			vsmappings = new EObjectContainmentEList<ViolationStrategyMapping>(ViolationStrategyMapping.class, this, ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS);
		}
		return vsmappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS:
				return ((InternalEList<?>)getVsmappings()).basicRemove(otherEnd, msgs);
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS:
				return getVsmappings();
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS:
				getVsmappings().clear();
				getVsmappings().addAll((Collection<? extends ViolationStrategyMapping>)newValue);
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS:
				getVsmappings().clear();
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS:
				return vsmappings != null && !vsmappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ViolationStrategyMappingRepositoryImpl
