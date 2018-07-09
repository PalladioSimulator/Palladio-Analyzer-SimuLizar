/**
 */
package org.palladiosimulator.simulizar.action.core.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.simulizar.action.core.*;
import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.action.core.CorePackage
 * @generated
 */
public class CoreSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static CorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CoreSwitch() {
		if (modelPackage == null) {
			modelPackage = CorePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY: {
			AdaptationBehaviorRepository adaptationBehaviorRepository = (AdaptationBehaviorRepository) theEObject;
			T result = caseAdaptationBehaviorRepository(adaptationBehaviorRepository);
			if (result == null)
				result = caseEntity(adaptationBehaviorRepository);
			if (result == null)
				result = caseIdentifier(adaptationBehaviorRepository);
			if (result == null)
				result = caseNamedElement(adaptationBehaviorRepository);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR: {
			AbstractAdaptationBehavior abstractAdaptationBehavior = (AbstractAdaptationBehavior) theEObject;
			T result = caseAbstractAdaptationBehavior(abstractAdaptationBehavior);
			if (result == null)
				result = caseEntity(abstractAdaptationBehavior);
			if (result == null)
				result = caseIdentifier(abstractAdaptationBehavior);
			if (result == null)
				result = caseNamedElement(abstractAdaptationBehavior);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.ADAPTATION_BEHAVIOR: {
			AdaptationBehavior adaptationBehavior = (AdaptationBehavior) theEObject;
			T result = caseAdaptationBehavior(adaptationBehavior);
			if (result == null)
				result = caseAbstractAdaptationBehavior(adaptationBehavior);
			if (result == null)
				result = caseEntity(adaptationBehavior);
			if (result == null)
				result = caseIdentifier(adaptationBehavior);
			if (result == null)
				result = caseNamedElement(adaptationBehavior);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.NESTED_ADAPTATION_BEHAVIOR: {
			NestedAdaptationBehavior nestedAdaptationBehavior = (NestedAdaptationBehavior) theEObject;
			T result = caseNestedAdaptationBehavior(nestedAdaptationBehavior);
			if (result == null)
				result = caseAbstractAdaptationBehavior(nestedAdaptationBehavior);
			if (result == null)
				result = caseEntity(nestedAdaptationBehavior);
			if (result == null)
				result = caseIdentifier(nestedAdaptationBehavior);
			if (result == null)
				result = caseNamedElement(nestedAdaptationBehavior);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.ADAPTATION_STEP: {
			AdaptationStep adaptationStep = (AdaptationStep) theEObject;
			T result = caseAdaptationStep(adaptationStep);
			if (result == null)
				result = caseEntity(adaptationStep);
			if (result == null)
				result = caseIdentifier(adaptationStep);
			if (result == null)
				result = caseNamedElement(adaptationStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.STATE_TRANSFORMING_STEP: {
			StateTransformingStep stateTransformingStep = (StateTransformingStep) theEObject;
			T result = caseStateTransformingStep(stateTransformingStep);
			if (result == null)
				result = caseAdaptationStep(stateTransformingStep);
			if (result == null)
				result = caseEntity(stateTransformingStep);
			if (result == null)
				result = caseIdentifier(stateTransformingStep);
			if (result == null)
				result = caseNamedElement(stateTransformingStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.ENACT_ADAPTATION_STEP: {
			EnactAdaptationStep enactAdaptationStep = (EnactAdaptationStep) theEObject;
			T result = caseEnactAdaptationStep(enactAdaptationStep);
			if (result == null)
				result = caseAdaptationStep(enactAdaptationStep);
			if (result == null)
				result = caseEntity(enactAdaptationStep);
			if (result == null)
				result = caseIdentifier(enactAdaptationStep);
			if (result == null)
				result = caseNamedElement(enactAdaptationStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.RESOURCE_DEMANDING_STEP: {
			ResourceDemandingStep resourceDemandingStep = (ResourceDemandingStep) theEObject;
			T result = caseResourceDemandingStep(resourceDemandingStep);
			if (result == null)
				result = caseAdaptationStep(resourceDemandingStep);
			if (result == null)
				result = caseEntity(resourceDemandingStep);
			if (result == null)
				result = caseIdentifier(resourceDemandingStep);
			if (result == null)
				result = caseNamedElement(resourceDemandingStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.GUARDED_STEP: {
			GuardedStep guardedStep = (GuardedStep) theEObject;
			T result = caseGuardedStep(guardedStep);
			if (result == null)
				result = caseAdaptationStep(guardedStep);
			if (result == null)
				result = caseEntity(guardedStep);
			if (result == null)
				result = caseIdentifier(guardedStep);
			if (result == null)
				result = caseNamedElement(guardedStep);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.GUARDED_TRANSITION: {
			GuardedTransition guardedTransition = (GuardedTransition) theEObject;
			T result = caseGuardedTransition(guardedTransition);
			if (result == null)
				result = caseEntity(guardedTransition);
			if (result == null)
				result = caseIdentifier(guardedTransition);
			if (result == null)
				result = caseNamedElement(guardedTransition);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.ROLE_TYPE: {
			RoleType roleType = (RoleType) theEObject;
			T result = caseRoleType(roleType);
			if (result == null)
				result = caseEntity(roleType);
			if (result == null)
				result = caseIdentifier(roleType);
			if (result == null)
				result = caseNamedElement(roleType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case CorePackage.CONTROLLER_CALL: {
			ControllerCall controllerCall = (ControllerCall) theEObject;
			T result = caseControllerCall(controllerCall);
			if (result == null)
				result = caseEntity(controllerCall);
			if (result == null)
				result = caseIdentifier(controllerCall);
			if (result == null)
				result = caseNamedElement(controllerCall);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation Behavior Repository</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation Behavior Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationBehaviorRepository(AdaptationBehaviorRepository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Abstract Adaptation Behavior</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Abstract Adaptation Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAbstractAdaptationBehavior(AbstractAdaptationBehavior object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptation Behavior</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptation Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAdaptationBehavior(AdaptationBehavior object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nested Adaptation Behavior</em>'.
	 * <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nested Adaptation Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNestedAdaptationBehavior(NestedAdaptationBehavior object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>State Transforming Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>State Transforming Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStateTransformingStep(StateTransformingStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enact Adaptation Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enact Adaptation Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnactAdaptationStep(EnactAdaptationStep object) {
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
	 * Returns the result of interpreting the object as an instance of '<em>Guarded Step</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guarded Step</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuardedStep(GuardedStep object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Guarded Transition</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Guarded Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGuardedTransition(GuardedTransition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Role Type</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 *
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Role Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoleType(RoleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Controller Call</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Controller Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControllerCall(ControllerCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 *
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIdentifier(Identifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entity</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch. <!-- end-user-doc -->
	 *
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntity(Entity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
	 * begin-user-doc --> This implementation returns null; returning a non-null result will
	 * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
	 *
	 * @param object
	 *            the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} // CoreSwitch
