/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms.util;

import de.upb.pcm.pms.*;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
 * @see de.upb.pcm.pms.PmsPackage
 * @generated
 */
public class PmsSwitch<T>
{
   /**
    * The cached model package
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static PmsPackage modelPackage;

   /**
    * Creates an instance of the switch.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PmsSwitch()
   {
      if (modelPackage == null)
      {
         modelPackage = PmsPackage.eINSTANCE;
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   public T doSwitch(EObject theEObject)
   {
      return doSwitch(theEObject.eClass(), theEObject);
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(EClass theEClass, EObject theEObject)
   {
      if (theEClass.eContainer() == modelPackage)
      {
         return doSwitch(theEClass.getClassifierID(), theEObject);
      }
      else
      {
         List<EClass> eSuperTypes = theEClass.getESuperTypes();
         return
            eSuperTypes.isEmpty() ?
               defaultCase(theEObject) :
               doSwitch(eSuperTypes.get(0), theEObject);
      }
   }

   /**
    * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the first non-null result returned by a <code>caseXXX</code> call.
    * @generated
    */
   protected T doSwitch(int classifierID, EObject theEObject)
   {
      switch (classifierID)
      {
         case PmsPackage.PMS_MODEL:
         {
            PMSModel pmsModel = (PMSModel)theEObject;
            T result = casePMSModel(pmsModel);
            if (result == null) result = caseUniqueElement(pmsModel);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.PERFORMANCE_MEASUREMENT:
         {
            PerformanceMeasurement performanceMeasurement = (PerformanceMeasurement)theEObject;
            T result = casePerformanceMeasurement(performanceMeasurement);
            if (result == null) result = caseUniqueElement(performanceMeasurement);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.MEASUREMENT_SPECIFICATION:
         {
            MeasurementSpecification measurementSpecification = (MeasurementSpecification)theEObject;
            T result = caseMeasurementSpecification(measurementSpecification);
            if (result == null) result = caseUniqueElement(measurementSpecification);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.TEMPORAL_CHARACTERIZATION:
         {
            TemporalCharacterization temporalCharacterization = (TemporalCharacterization)theEObject;
            T result = caseTemporalCharacterization(temporalCharacterization);
            if (result == null) result = caseUniqueElement(temporalCharacterization);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.INTERVALL:
         {
            Intervall intervall = (Intervall)theEObject;
            T result = caseIntervall(intervall);
            if (result == null) result = caseTemporalCharacterization(intervall);
            if (result == null) result = caseUniqueElement(intervall);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.DELAYED_INTERVALL:
         {
            DelayedIntervall delayedIntervall = (DelayedIntervall)theEObject;
            T result = caseDelayedIntervall(delayedIntervall);
            if (result == null) result = caseIntervall(delayedIntervall);
            if (result == null) result = caseTemporalCharacterization(delayedIntervall);
            if (result == null) result = caseUniqueElement(delayedIntervall);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.TIME_FRAME:
         {
            TimeFrame timeFrame = (TimeFrame)theEObject;
            T result = caseTimeFrame(timeFrame);
            if (result == null) result = caseTemporalCharacterization(timeFrame);
            if (result == null) result = caseUniqueElement(timeFrame);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         case PmsPackage.UNIQUE_ELEMENT:
         {
            UniqueElement uniqueElement = (UniqueElement)theEObject;
            T result = caseUniqueElement(uniqueElement);
            if (result == null) result = defaultCase(theEObject);
            return result;
         }
         default: return defaultCase(theEObject);
      }
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>PMS Model</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>PMS Model</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePMSModel(PMSModel object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Performance Measurement</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Performance Measurement</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T casePerformanceMeasurement(PerformanceMeasurement object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Measurement Specification</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Measurement Specification</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseMeasurementSpecification(MeasurementSpecification object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Temporal Characterization</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Temporal Characterization</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseTemporalCharacterization(TemporalCharacterization object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Intervall</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Intervall</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseIntervall(Intervall object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Delayed Intervall</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Delayed Intervall</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseDelayedIntervall(DelayedIntervall object)
   {
      return null;
   }

   /**
    * Returns the result of interpreting the object as an instance of '<em>Time Frame</em>'.
    * <!-- begin-user-doc -->
    * This implementation returns null;
    * returning a non-null result will terminate the switch.
    * <!-- end-user-doc -->
    * @param object the target of the switch.
    * @return the result of interpreting the object as an instance of '<em>Time Frame</em>'.
    * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
    * @generated
    */
   public T caseTimeFrame(TimeFrame object)
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
   public T defaultCase(EObject object)
   {
      return null;
   }

} //PmsSwitch
