/**
 */
package vsmappings.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import vsmappings.VSMapping;
import vsmappings.VSMappingRepository;
import vsmappings.VsmappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>VS Mapping Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link vsmappings.impl.VSMappingRepositoryImpl#getVsmappings <em>Vsmappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VSMappingRepositoryImpl extends EntityImpl implements VSMappingRepository {
	/**
	 * The cached value of the '{@link #getVsmappings() <em>Vsmappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVsmappings()
	 * @generated
	 * @ordered
	 */
	protected EList<VSMapping> vsmappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected VSMappingRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return VsmappingsPackage.Literals.VS_MAPPING_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VSMapping> getVsmappings() {
		if (vsmappings == null) {
			vsmappings = new EObjectContainmentEList<VSMapping>(VSMapping.class, this, VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS);
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
			case VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS:
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
			case VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS:
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
			case VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS:
				getVsmappings().clear();
				getVsmappings().addAll((Collection<? extends VSMapping>)newValue);
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
			case VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS:
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
			case VsmappingsPackage.VS_MAPPING_REPOSITORY__VSMAPPINGS:
				return vsmappings != null && !vsmappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //VSMappingRepositoryImpl
