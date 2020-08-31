/**
 */
package org.palladiosimulator.simulizar.action.core.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;
import org.palladiosimulator.simulizar.action.core.GuardedStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.core.StateTransformingStep;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage
 * @generated
 */
public class CoreSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static CorePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CoreSwitch() {
        if (modelPackage == null) {
            modelPackage = CorePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param ePackage
     *            the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result;
     * it yields that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY: {
            final AdaptationBehaviorRepository adaptationBehaviorRepository = (AdaptationBehaviorRepository) theEObject;
            T result = this.caseAdaptationBehaviorRepository(adaptationBehaviorRepository);
            if (result == null) {
                result = this.caseEntity(adaptationBehaviorRepository);
            }
            if (result == null) {
                result = this.caseIdentifier(adaptationBehaviorRepository);
            }
            if (result == null) {
                result = this.caseNamedElement(adaptationBehaviorRepository);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ABSTRACT_ADAPTATION_BEHAVIOR: {
            final AbstractAdaptationBehavior abstractAdaptationBehavior = (AbstractAdaptationBehavior) theEObject;
            T result = this.caseAbstractAdaptationBehavior(abstractAdaptationBehavior);
            if (result == null) {
                result = this.caseEntity(abstractAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseIdentifier(abstractAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseNamedElement(abstractAdaptationBehavior);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ADAPTATION_BEHAVIOR: {
            final AdaptationBehavior adaptationBehavior = (AdaptationBehavior) theEObject;
            T result = this.caseAdaptationBehavior(adaptationBehavior);
            if (result == null) {
                result = this.caseAbstractAdaptationBehavior(adaptationBehavior);
            }
            if (result == null) {
                result = this.caseEntity(adaptationBehavior);
            }
            if (result == null) {
                result = this.caseIdentifier(adaptationBehavior);
            }
            if (result == null) {
                result = this.caseNamedElement(adaptationBehavior);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR: {
            final NestedAdaptationBehavior nestedAdaptationBehavior = (NestedAdaptationBehavior) theEObject;
            T result = this.caseNestedAdaptationBehavior(nestedAdaptationBehavior);
            if (result == null) {
                result = this.caseAbstractAdaptationBehavior(nestedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseEntity(nestedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseIdentifier(nestedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseNamedElement(nestedAdaptationBehavior);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ADAPTATION_STEP: {
            final AdaptationStep adaptationStep = (AdaptationStep) theEObject;
            T result = this.caseAdaptationStep(adaptationStep);
            if (result == null) {
                result = this.caseEntity(adaptationStep);
            }
            if (result == null) {
                result = this.caseIdentifier(adaptationStep);
            }
            if (result == null) {
                result = this.caseNamedElement(adaptationStep);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.STATE_TRANSFORMING_STEP: {
            final StateTransformingStep stateTransformingStep = (StateTransformingStep) theEObject;
            T result = this.caseStateTransformingStep(stateTransformingStep);
            if (result == null) {
                result = this.caseAdaptationStep(stateTransformingStep);
            }
            if (result == null) {
                result = this.caseEntity(stateTransformingStep);
            }
            if (result == null) {
                result = this.caseIdentifier(stateTransformingStep);
            }
            if (result == null) {
                result = this.caseNamedElement(stateTransformingStep);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ENACT_ADAPTATION_STEP: {
            final EnactAdaptationStep enactAdaptationStep = (EnactAdaptationStep) theEObject;
            T result = this.caseEnactAdaptationStep(enactAdaptationStep);
            if (result == null) {
                result = this.caseAdaptationStep(enactAdaptationStep);
            }
            if (result == null) {
                result = this.caseEntity(enactAdaptationStep);
            }
            if (result == null) {
                result = this.caseIdentifier(enactAdaptationStep);
            }
            if (result == null) {
                result = this.caseNamedElement(enactAdaptationStep);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.RESOURCE_DEMANDING_STEP: {
            final ResourceDemandingStep resourceDemandingStep = (ResourceDemandingStep) theEObject;
            T result = this.caseResourceDemandingStep(resourceDemandingStep);
            if (result == null) {
                result = this.caseAdaptationStep(resourceDemandingStep);
            }
            if (result == null) {
                result = this.caseEntity(resourceDemandingStep);
            }
            if (result == null) {
                result = this.caseIdentifier(resourceDemandingStep);
            }
            if (result == null) {
                result = this.caseNamedElement(resourceDemandingStep);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.GUARDED_STEP: {
            final GuardedStep guardedStep = (GuardedStep) theEObject;
            T result = this.caseGuardedStep(guardedStep);
            if (result == null) {
                result = this.caseAdaptationStep(guardedStep);
            }
            if (result == null) {
                result = this.caseEntity(guardedStep);
            }
            if (result == null) {
                result = this.caseIdentifier(guardedStep);
            }
            if (result == null) {
                result = this.caseNamedElement(guardedStep);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.GUARDED_TRANSITION: {
            final GuardedTransition guardedTransition = (GuardedTransition) theEObject;
            T result = this.caseGuardedTransition(guardedTransition);
            if (result == null) {
                result = this.caseEntity(guardedTransition);
            }
            if (result == null) {
                result = this.caseIdentifier(guardedTransition);
            }
            if (result == null) {
                result = this.caseNamedElement(guardedTransition);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ROLE_TYPE: {
            final RoleType roleType = (RoleType) theEObject;
            T result = this.caseRoleType(roleType);
            if (result == null) {
                result = this.caseEntity(roleType);
            }
            if (result == null) {
                result = this.caseIdentifier(roleType);
            }
            if (result == null) {
                result = this.caseNamedElement(roleType);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.CONTROLLER_CALL: {
            final ControllerCall controllerCall = (ControllerCall) theEObject;
            T result = this.caseControllerCall(controllerCall);
            if (result == null) {
                result = this.caseEntity(controllerCall);
            }
            if (result == null) {
                result = this.caseIdentifier(controllerCall);
            }
            if (result == null) {
                result = this.caseNamedElement(controllerCall);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        default:
            return this.defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Adaptation Behavior
     * Repository</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
     * non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation Behavior
     *         Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationBehaviorRepository(final AdaptationBehaviorRepository object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Adaptation
     * Behavior</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
     * non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Adaptation
     *         Behavior</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractAdaptationBehavior(final AbstractAdaptationBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Adaptation
     * Behavior</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
     * non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation
     *         Behavior</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationBehavior(final AdaptationBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Nested Adaptation
     * Behavior</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
     * non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Nested Adaptation
     *         Behavior</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNestedAdaptationBehavior(final NestedAdaptationBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Adaptation Step</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationStep(final AdaptationStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>State Transforming
     * Step</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null
     * result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>State Transforming
     *         Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStateTransformingStep(final StateTransformingStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Enact Adaptation
     * Step</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null
     * result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enact Adaptation
     *         Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnactAdaptationStep(final EnactAdaptationStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Resource Demanding
     * Step</em>'. <!-- begin-user-doc --> This implementation returns null; returning a non-null
     * result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Demanding
     *         Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResourceDemandingStep(final ResourceDemandingStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Guarded Step</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Guarded Step</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGuardedStep(final GuardedStep object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Guarded
     * Transition</em>'. <!-- begin-user-doc --> This implementation returns null; returning a
     * non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Guarded
     *         Transition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGuardedTransition(final GuardedTransition object) {
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
    public T caseRoleType(final RoleType object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Controller Call</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Controller Call</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseControllerCall(final ControllerCall object) {
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
    public T caseIdentifier(final Identifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamedElement(final NamedElement object) {
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
    public T caseEntity(final Entity object) {
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
    public T defaultCase(final EObject object) {
        return null;
    }

} // CoreSwitch
