/**
 */
package violations.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import violations.ViolationType;
import violations.ViolationsPackage;
import violations.ViolationsRepository;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repository</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link violations.impl.ViolationsRepositoryImpl#getViolationTypes <em>Violation Types</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViolationsRepositoryImpl extends EntityImpl implements ViolationsRepository {
	/**
	 * The cached value of the '{@link #getViolationTypes() <em>Violation Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolationTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<ViolationType> violationTypes;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViolationsRepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationsPackage.Literals.VIOLATIONS_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ViolationType> getViolationTypes() {
		if (violationTypes == null) {
			violationTypes = new EObjectContainmentEList<ViolationType>(ViolationType.class, this, ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES);
		}
		return violationTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES:
				return ((InternalEList<?>)getViolationTypes()).basicRemove(otherEnd, msgs);
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
			case ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES:
				return getViolationTypes();
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
			case ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES:
				getViolationTypes().clear();
				getViolationTypes().addAll((Collection<? extends ViolationType>)newValue);
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
			case ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES:
				getViolationTypes().clear();
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
			case ViolationsPackage.VIOLATIONS_REPOSITORY__VIOLATION_TYPES:
				return violationTypes != null && !violationTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ViolationsRepositoryImpl
