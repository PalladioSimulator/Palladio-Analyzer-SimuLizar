/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.UniqueElement;

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
 * @see org.palladiosimulator.simulizar.prm.PrmPackage
 * @generated
 */
public class PrmSwitch<T> extends Switch<T>
{
   /**
     * The cached model package
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   protected static PrmPackage modelPackage;

   /**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   public PrmSwitch()
   {
        if (modelPackage == null) {
            modelPackage = PrmPackage.eINSTANCE;
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
protected T doSwitch(int classifierID, EObject theEObject)
   {
        switch (classifierID) {
            case PrmPackage.PRM_MODEL: {
                PRMModel prmModel = (PRMModel)theEObject;
                T result = casePRMModel(prmModel);
                if (result == null) result = caseUniqueElement(prmModel);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT: {
                PCMModelElementMeasurement pcmModelElementMeasurement = (PCMModelElementMeasurement)theEObject;
                T result = casePCMModelElementMeasurement(pcmModelElementMeasurement);
                if (result == null) result = caseUniqueElement(pcmModelElementMeasurement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrmPackage.UNIQUE_ELEMENT: {
                UniqueElement uniqueElement = (UniqueElement)theEObject;
                T result = caseUniqueElement(uniqueElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case PrmPackage.RESOURCE_CONTAINER_MEASUREMENT: {
                ResourceContainerMeasurement resourceContainerMeasurement = (ResourceContainerMeasurement)theEObject;
                T result = caseResourceContainerMeasurement(resourceContainerMeasurement);
                if (result == null) result = casePCMModelElementMeasurement(resourceContainerMeasurement);
                if (result == null) result = caseUniqueElement(resourceContainerMeasurement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

   /**
     * Returns the result of interpreting the object as an instance of '<em>PRM Model</em>'.
     * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PRM Model</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
   public T casePRMModel(PRMModel object)
   {
        return null;
    }

   /**
     * Returns the result of interpreting the object as an instance of '<em>PCM Model Element Measurement</em>'.
     * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>PCM Model Element Measurement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
   public T casePCMModelElementMeasurement(PCMModelElementMeasurement object)
   {
        return null;
    }

   /**
     * Returns the result of interpreting the object as an instance of '<em>Unique Element</em>'.
     * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Unique Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
   public T caseUniqueElement(UniqueElement object)
   {
        return null;
    }

   /**
     * Returns the result of interpreting the object as an instance of '<em>Resource Container Measurement</em>'.
     * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Resource Container Measurement</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
   public T caseResourceContainerMeasurement(ResourceContainerMeasurement object)
   {
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
public T defaultCase(EObject object)
   {
        return null;
    }

} //PrmSwitch
