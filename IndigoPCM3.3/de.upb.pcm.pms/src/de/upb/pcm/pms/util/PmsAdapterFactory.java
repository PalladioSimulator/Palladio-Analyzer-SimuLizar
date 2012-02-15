/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms.util;

import de.upb.pcm.pms.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.upb.pcm.pms.PmsPackage
 * @generated
 */
public class PmsAdapterFactory extends AdapterFactoryImpl
{
   /**
    * The cached model package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected static PmsPackage modelPackage;

   /**
    * Creates an instance of the adapter factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public PmsAdapterFactory()
   {
      if (modelPackage == null)
      {
         modelPackage = PmsPackage.eINSTANCE;
      }
   }

   /**
    * Returns whether this factory is applicable for the type of the object.
    * <!-- begin-user-doc -->
    * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
    * <!-- end-user-doc -->
    * @return whether this factory is applicable for the type of the object.
    * @generated
    */
   @Override
   public boolean isFactoryForType(Object object)
   {
      if (object == modelPackage)
      {
         return true;
      }
      if (object instanceof EObject)
      {
         return ((EObject)object).eClass().getEPackage() == modelPackage;
      }
      return false;
   }

   /**
    * The switch that delegates to the <code>createXXX</code> methods.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected PmsSwitch<Adapter> modelSwitch =
      new PmsSwitch<Adapter>()
      {
         @Override
         public Adapter casePMSModel(PMSModel object)
         {
            return createPMSModelAdapter();
         }
         @Override
         public Adapter casePerformanceMeasurement(PerformanceMeasurement object)
         {
            return createPerformanceMeasurementAdapter();
         }
         @Override
         public Adapter caseMeasurementSpecification(MeasurementSpecification object)
         {
            return createMeasurementSpecificationAdapter();
         }
         @Override
         public Adapter caseTemporalCharacterization(TemporalCharacterization object)
         {
            return createTemporalCharacterizationAdapter();
         }
         @Override
         public Adapter caseIntervall(Intervall object)
         {
            return createIntervallAdapter();
         }
         @Override
         public Adapter caseDelayedIntervall(DelayedIntervall object)
         {
            return createDelayedIntervallAdapter();
         }
         @Override
         public Adapter caseTimeFrame(TimeFrame object)
         {
            return createTimeFrameAdapter();
         }
         @Override
         public Adapter caseUniqueElement(UniqueElement object)
         {
            return createUniqueElementAdapter();
         }
         @Override
         public Adapter defaultCase(EObject object)
         {
            return createEObjectAdapter();
         }
      };

   /**
    * Creates an adapter for the <code>target</code>.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param target the object to adapt.
    * @return the adapter for the <code>target</code>.
    * @generated
    */
   @Override
   public Adapter createAdapter(Notifier target)
   {
      return modelSwitch.doSwitch((EObject)target);
   }


   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.PMSModel <em>PMS Model</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.PMSModel
    * @generated
    */
   public Adapter createPMSModelAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.PerformanceMeasurement <em>Performance Measurement</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.PerformanceMeasurement
    * @generated
    */
   public Adapter createPerformanceMeasurementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.MeasurementSpecification <em>Measurement Specification</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.MeasurementSpecification
    * @generated
    */
   public Adapter createMeasurementSpecificationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.TemporalCharacterization <em>Temporal Characterization</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.TemporalCharacterization
    * @generated
    */
   public Adapter createTemporalCharacterizationAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.Intervall <em>Intervall</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.Intervall
    * @generated
    */
   public Adapter createIntervallAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.DelayedIntervall <em>Delayed Intervall</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.DelayedIntervall
    * @generated
    */
   public Adapter createDelayedIntervallAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.TimeFrame <em>Time Frame</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.TimeFrame
    * @generated
    */
   public Adapter createTimeFrameAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for an object of class '{@link de.upb.pcm.pms.UniqueElement <em>Unique Element</em>}'.
    * <!-- begin-user-doc -->
    * This default implementation returns null so that we can easily ignore cases;
    * it's useful to ignore a case when inheritance will catch all the cases anyway.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @see de.upb.pcm.pms.UniqueElement
    * @generated
    */
   public Adapter createUniqueElementAdapter()
   {
      return null;
   }

   /**
    * Creates a new adapter for the default case.
    * <!-- begin-user-doc -->
    * This default implementation returns null.
    * <!-- end-user-doc -->
    * @return the new adapter.
    * @generated
    */
   public Adapter createEObjectAdapter()
   {
      return null;
   }

} //PmsAdapterFactory
