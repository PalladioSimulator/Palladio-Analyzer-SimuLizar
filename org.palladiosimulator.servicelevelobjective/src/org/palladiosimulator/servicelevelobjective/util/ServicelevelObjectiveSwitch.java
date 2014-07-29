/**
 */
package org.palladiosimulator.servicelevelobjective.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.NamedElement;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 * 
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage
 * @generated
 */
public class ServicelevelObjectiveSwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ServicelevelObjectivePackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ServicelevelObjectiveSwitch() {
        if (modelPackage == null) {
            modelPackage = ServicelevelObjectivePackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @parameter ePackage the package in question.
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
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE_REPOSITORY: {
            final ServiceLevelObjectiveRepository serviceLevelObjectiveRepository = (ServiceLevelObjectiveRepository) theEObject;
            T result = this.caseServiceLevelObjectiveRepository(serviceLevelObjectiveRepository);
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case ServicelevelObjectivePackage.SERVICE_LEVEL_OBJECTIVE: {
            final ServiceLevelObjective serviceLevelObjective = (ServiceLevelObjective) theEObject;
            T result = this.caseServiceLevelObjective(serviceLevelObjective);
            if (result == null) {
                result = this.caseNamedElement(serviceLevelObjective);
            }
            if (result == null) {
                result = this.caseIdentifier(serviceLevelObjective);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case ServicelevelObjectivePackage.THRESHOLD: {
            final Threshold threshold = (Threshold) theEObject;
            T result = this.caseThreshold(threshold);
            if (result == null) {
                result = this.caseIdentifier(threshold);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case ServicelevelObjectivePackage.HARD_THRESHOLD: {
            final HardThreshold hardThreshold = (HardThreshold) theEObject;
            T result = this.caseHardThreshold(hardThreshold);
            if (result == null) {
                result = this.caseThreshold(hardThreshold);
            }
            if (result == null) {
                result = this.caseIdentifier(hardThreshold);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD: {
            final LinearFuzzyThreshold linearFuzzyThreshold = (LinearFuzzyThreshold) theEObject;
            T result = this.caseLinearFuzzyThreshold(linearFuzzyThreshold);
            if (result == null) {
                result = this.caseThreshold(linearFuzzyThreshold);
            }
            if (result == null) {
                result = this.caseIdentifier(linearFuzzyThreshold);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case ServicelevelObjectivePackage.NAMED_ELEMENT: {
            final NamedElement namedElement = (NamedElement) theEObject;
            T result = this.caseNamedElement(namedElement);
            if (result == null) {
                result = this.caseIdentifier(namedElement);
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
     * Returns the result of interpreting the object as an instance of '
     * <em>Service Level Objective Repository</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Service Level Objective Repository</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseServiceLevelObjectiveRepository(final ServiceLevelObjectiveRepository object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Service Level Objective</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Service Level Objective</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseServiceLevelObjective(final ServiceLevelObjective object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Threshold</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Threshold</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseThreshold(final Threshold object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hard Threshold</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hard Threshold</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHardThreshold(final HardThreshold object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Linear Fuzzy Threshold</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Linear Fuzzy Threshold</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLinearFuzzyThreshold(final LinearFuzzyThreshold object) {
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

} // ServicelevelObjectiveSwitch
