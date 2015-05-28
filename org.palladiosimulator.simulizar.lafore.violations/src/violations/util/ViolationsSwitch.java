/**
 */
package violations.util;

import de.uka.ipd.sdq.identifier.Identifier;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import violations.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see violations.ViolationsPackage
 * @generated
 */
public class ViolationsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ViolationsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationsSwitch() {
		if (modelPackage == null) {
			modelPackage = ViolationsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ViolationsPackage.VIOLATION_TYPE: {
				ViolationType violationType = (ViolationType)theEObject;
				T result = caseViolationType(violationType);
				if (result == null) result = caseEntity(violationType);
				if (result == null) result = caseIdentifier(violationType);
				if (result == null) result = caseNamedElement(violationType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViolationsPackage.VIOLATION: {
				Violation violation = (Violation)theEObject;
				T result = caseViolation(violation);
				if (result == null) result = caseEntity(violation);
				if (result == null) result = caseIdentifier(violation);
				if (result == null) result = caseNamedElement(violation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViolationsPackage.VIOLATIONS_REPOSITORY: {
				ViolationsRepository violationsRepository = (ViolationsRepository)theEObject;
				T result = caseViolationsRepository(violationsRepository);
				if (result == null) result = caseEntity(violationsRepository);
				if (result == null) result = caseIdentifier(violationsRepository);
				if (result == null) result = caseNamedElement(violationsRepository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL: {
				RuntimeViolationsModel runtimeViolationsModel = (RuntimeViolationsModel)theEObject;
				T result = caseRuntimeViolationsModel(runtimeViolationsModel);
				if (result == null) result = caseEntity(runtimeViolationsModel);
				if (result == null) result = caseIdentifier(runtimeViolationsModel);
				if (result == null) result = caseNamedElement(runtimeViolationsModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViolationsPackage.QUANTIFIABLE_VIOLATION: {
				QuantifiableViolation quantifiableViolation = (QuantifiableViolation)theEObject;
				T result = caseQuantifiableViolation(quantifiableViolation);
				if (result == null) result = caseViolation(quantifiableViolation);
				if (result == null) result = caseEntity(quantifiableViolation);
				if (result == null) result = caseIdentifier(quantifiableViolation);
				if (result == null) result = caseNamedElement(quantifiableViolation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ViolationsPackage.NON_QUANTIFIABLE_VIOLATION: {
				NonQuantifiableViolation nonQuantifiableViolation = (NonQuantifiableViolation)theEObject;
				T result = caseNonQuantifiableViolation(nonQuantifiableViolation);
				if (result == null) result = caseViolation(nonQuantifiableViolation);
				if (result == null) result = caseEntity(nonQuantifiableViolation);
				if (result == null) result = caseIdentifier(nonQuantifiableViolation);
				if (result == null) result = caseNamedElement(nonQuantifiableViolation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Violation Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Violation Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViolationType(ViolationType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Violation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Violation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViolation(Violation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseViolationsRepository(ViolationsRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Violations Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Violations Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeViolationsModel(RuntimeViolationsModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Quantifiable Violation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Quantifiable Violation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseQuantifiableViolation(QuantifiableViolation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Non Quantifiable Violation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Non Quantifiable Violation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNonQuantifiableViolation(NonQuantifiableViolation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifier(Identifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //ViolationsSwitch
