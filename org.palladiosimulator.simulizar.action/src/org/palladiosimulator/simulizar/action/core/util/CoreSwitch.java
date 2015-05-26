/**
 */
package org.palladiosimulator.simulizar.action.core.util;

import de.uka.ipd.sdq.identifier.Identifier;

import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.palladiosimulator.simulizar.action.core.*;

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
 * @see org.palladiosimulator.simulizar.action.core.CorePackage
 * @generated
 */
public class CoreSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static CorePackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CoreSwitch() {
        if (modelPackage == null) {
            modelPackage = CorePackage.eINSTANCE;
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
            case CorePackage.ACTION: {
                Action action = (Action)theEObject;
                T result = caseAction(action);
                if (result == null) result = caseEntity(action);
                if (result == null) result = caseIdentifier(action);
                if (result == null) result = caseNamedElement(action);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.ADAPTATION_STEP: {
                AdaptationStep adaptationStep = (AdaptationStep)theEObject;
                T result = caseAdaptationStep(adaptationStep);
                if (result == null) result = caseEntity(adaptationStep);
                if (result == null) result = caseIdentifier(adaptationStep);
                if (result == null) result = caseNamedElement(adaptationStep);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.RESOURCE_DEMANDING_STEP: {
                ResourceDemandingStep resourceDemandingStep = (ResourceDemandingStep)theEObject;
                T result = caseResourceDemandingStep(resourceDemandingStep);
                if (result == null) result = caseAdaptationStep(resourceDemandingStep);
                if (result == null) result = caseEntity(resourceDemandingStep);
                if (result == null) result = caseIdentifier(resourceDemandingStep);
                if (result == null) result = caseNamedElement(resourceDemandingStep);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.ROLE_TYPE: {
                RoleType roleType = (RoleType)theEObject;
                T result = caseRoleType(roleType);
                if (result == null) result = caseEntity(roleType);
                if (result == null) result = caseIdentifier(roleType);
                if (result == null) result = caseNamedElement(roleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.ACTION_REPOSITORY: {
                ActionRepository actionRepository = (ActionRepository)theEObject;
                T result = caseActionRepository(actionRepository);
                if (result == null) result = caseEntity(actionRepository);
                if (result == null) result = caseIdentifier(actionRepository);
                if (result == null) result = caseNamedElement(actionRepository);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case CorePackage.CONTROLLER_CALL: {
                ControllerCall controllerCall = (ControllerCall)theEObject;
                T result = caseControllerCall(controllerCall);
                if (result == null) result = caseEntity(controllerCall);
                if (result == null) result = caseIdentifier(controllerCall);
                if (result == null) result = caseNamedElement(controllerCall);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Action</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAction(Action object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Adaptation Step</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAdaptationStep(AdaptationStep object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Resource Demanding Step</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Demanding Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseResourceDemandingStep(ResourceDemandingStep object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Role Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Role Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRoleType(RoleType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Action Repository</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Action Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseActionRepository(ActionRepository object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Controller Call</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Controller Call</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseControllerCall(ControllerCall object) {
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

} //CoreSwitch
