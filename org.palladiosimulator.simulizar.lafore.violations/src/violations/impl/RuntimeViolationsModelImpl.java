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

import violations.RuntimeViolationsModel;
import violations.Violation;
import violations.ViolationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Runtime Violations Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link violations.impl.RuntimeViolationsModelImpl#getViolations <em>Violations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuntimeViolationsModelImpl extends EntityImpl implements RuntimeViolationsModel {
	/**
	 * The cached value of the '{@link #getViolations() <em>Violations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getViolations()
	 * @generated
	 * @ordered
	 */
	protected EList<Violation> violations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RuntimeViolationsModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationsPackage.Literals.RUNTIME_VIOLATIONS_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Violation> getViolations() {
		if (violations == null) {
			violations = new EObjectContainmentEList<Violation>(Violation.class, this, ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS);
		}
		return violations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS:
				return ((InternalEList<?>)getViolations()).basicRemove(otherEnd, msgs);
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
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS:
				return getViolations();
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
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS:
				getViolations().clear();
				getViolations().addAll((Collection<? extends Violation>)newValue);
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
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS:
				getViolations().clear();
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
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL__VIOLATIONS:
				return violations != null && !violations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RuntimeViolationsModelImpl
