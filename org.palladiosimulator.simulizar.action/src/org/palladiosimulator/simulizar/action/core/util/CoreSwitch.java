/**
 */
package org.palladiosimulator.simulizar.action.core.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.core.entity.NamedElement;
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;
import org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.core.StateTransformingAction;

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
        case CorePackage.ADAPTATION_BEHAVIOR: {
            final AdaptationBehavior adaptationBehavior = (AdaptationBehavior) theEObject;
            T result = this.caseAdaptationBehavior(adaptationBehavior);
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
        case CorePackage.ADAPTATION_ACTION: {
            final AdaptationAction adaptationAction = (AdaptationAction) theEObject;
            T result = this.caseAdaptationAction(adaptationAction);
            if (result == null) {
                result = this.caseEntity(adaptationAction);
            }
            if (result == null) {
                result = this.caseIdentifier(adaptationAction);
            }
            if (result == null) {
                result = this.caseNamedElement(adaptationAction);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.RESOURCE_DEMANDING_ACTION: {
            final ResourceDemandingAction resourceDemandingAction = (ResourceDemandingAction) theEObject;
            T result = this.caseResourceDemandingAction(resourceDemandingAction);
            if (result == null) {
                result = this.caseAdaptationAction(resourceDemandingAction);
            }
            if (result == null) {
                result = this.caseEntity(resourceDemandingAction);
            }
            if (result == null) {
                result = this.caseIdentifier(resourceDemandingAction);
            }
            if (result == null) {
                result = this.caseNamedElement(resourceDemandingAction);
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
        case CorePackage.STATE_TRANSFORMING_ACTION: {
            final StateTransformingAction stateTransformingAction = (StateTransformingAction) theEObject;
            T result = this.caseStateTransformingAction(stateTransformingAction);
            if (result == null) {
                result = this.caseAdaptationAction(stateTransformingAction);
            }
            if (result == null) {
                result = this.caseEntity(stateTransformingAction);
            }
            if (result == null) {
                result = this.caseIdentifier(stateTransformingAction);
            }
            if (result == null) {
                result = this.caseNamedElement(stateTransformingAction);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.GUARDED_ADAPTATION_BEHAVIOR: {
            final GuardedAdaptationBehavior guardedAdaptationBehavior = (GuardedAdaptationBehavior) theEObject;
            T result = this.caseGuardedAdaptationBehavior(guardedAdaptationBehavior);
            if (result == null) {
                result = this.caseAdaptationAction(guardedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseEntity(guardedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseIdentifier(guardedAdaptationBehavior);
            }
            if (result == null) {
                result = this.caseNamedElement(guardedAdaptationBehavior);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case CorePackage.ENACT_ADAPTATION_ACTION: {
            final EnactAdaptationAction enactAdaptationAction = (EnactAdaptationAction) theEObject;
            T result = this.caseEnactAdaptationAction(enactAdaptationAction);
            if (result == null) {
                result = this.caseAdaptationAction(enactAdaptationAction);
            }
            if (result == null) {
                result = this.caseEntity(enactAdaptationAction);
            }
            if (result == null) {
                result = this.caseIdentifier(enactAdaptationAction);
            }
            if (result == null) {
                result = this.caseNamedElement(enactAdaptationAction);
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
     * Returns the result of interpreting the object as an instance of '<em>Adaptation Behavior</em>
     * '. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation Behavior</em>
     *         '.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationBehavior(final AdaptationBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Adaptation Action</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Adaptation Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationAction(final AdaptationAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Resource Demanding Action</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Resource Demanding Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseResourceDemandingAction(final ResourceDemandingAction object) {
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
     * Returns the result of interpreting the object as an instance of '
     * <em>Adaptation Behavior Repository</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Adaptation Behavior Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdaptationBehaviorRepository(final AdaptationBehaviorRepository object) {
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
     * Returns the result of interpreting the object as an instance of '
     * <em>State Transforming Action</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>State Transforming Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStateTransformingAction(final StateTransformingAction object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Guarded Adaptation Behavior</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Guarded Adaptation Behavior</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGuardedAdaptationBehavior(final GuardedAdaptationBehavior object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Enact Adaptation Action</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Enact Adaptation Action</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEnactAdaptationAction(final EnactAdaptationAction object) {
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
