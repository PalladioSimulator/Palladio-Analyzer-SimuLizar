/**
 */
package violations.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import violations.ViolationType;
import violations.ViolationsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Violation Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link violations.impl.ViolationTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link violations.impl.ViolationTypeImpl#getSlo <em>Slo</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ViolationTypeImpl extends EntityImpl implements ViolationType {
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSlo() <em>Slo</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSlo()
	 * @generated
	 * @ordered
	 */
	protected ServiceLevelObjective slo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ViolationTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ViolationsPackage.Literals.VIOLATION_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationsPackage.VIOLATION_TYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceLevelObjective getSlo() {
		if (slo != null && ((EObject)slo).eIsProxy()) {
			InternalEObject oldSlo = (InternalEObject)slo;
			slo = (ServiceLevelObjective)eResolveProxy(oldSlo);
			if (slo != oldSlo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ViolationsPackage.VIOLATION_TYPE__SLO, oldSlo, slo));
			}
		}
		return slo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ServiceLevelObjective basicGetSlo() {
		return slo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSlo(ServiceLevelObjective newSlo) {
		ServiceLevelObjective oldSlo = slo;
		slo = newSlo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ViolationsPackage.VIOLATION_TYPE__SLO, oldSlo, slo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ViolationsPackage.VIOLATION_TYPE__DESCRIPTION:
				return getDescription();
			case ViolationsPackage.VIOLATION_TYPE__SLO:
				if (resolve) return getSlo();
				return basicGetSlo();
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
			case ViolationsPackage.VIOLATION_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case ViolationsPackage.VIOLATION_TYPE__SLO:
				setSlo((ServiceLevelObjective)newValue);
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
			case ViolationsPackage.VIOLATION_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case ViolationsPackage.VIOLATION_TYPE__SLO:
				setSlo((ServiceLevelObjective)null);
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
			case ViolationsPackage.VIOLATION_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case ViolationsPackage.VIOLATION_TYPE__SLO:
				return slo != null;
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
		result.append(" (description: ");
		result.append(description);
		result.append(')');
		return result.toString();
	}

} //ViolationTypeImpl
