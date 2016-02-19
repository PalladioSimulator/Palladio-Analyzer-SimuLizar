/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import de.upb.pcm.pms.DelayedIntervall;
import de.upb.pcm.pms.Intervall;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.PMSModel;
import de.upb.pcm.pms.PerformanceMeasurement;
import de.upb.pcm.pms.PerformanceMetricEnum;
import de.upb.pcm.pms.PmsFactory;
import de.upb.pcm.pms.PmsPackage;
import de.upb.pcm.pms.StatisticalCharacterizationEnum;
import de.upb.pcm.pms.TemporalCharacterization;
import de.upb.pcm.pms.TimeFrame;
import de.upb.pcm.pms.UniqueElement;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class PmsPackageImpl extends EPackageImpl implements PmsPackage
{
   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass pmsModelEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass performanceMeasurementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass measurementSpecificationEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass temporalCharacterizationEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass intervallEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass delayedIntervallEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass timeFrameEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EClass uniqueElementEClass = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum performanceMetricEnumEEnum = null;

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private EEnum statisticalCharacterizationEnumEEnum = null;


   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see de.upb.pcm.pms.PmsPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private PmsPackageImpl()
   {
      super(eNS_URI, PmsFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;


   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    * 
    * <p>This method is used to initialize {@link PmsPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static PmsPackage init()
   {
      if (isInited) return (PmsPackage)EPackage.Registry.INSTANCE.getEPackage(PmsPackage.eNS_URI);

      // Obtain or create and register package
      PmsPackageImpl thePmsPackage = (PmsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PmsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PmsPackageImpl());

      isInited = true;

      // Initialize simple dependencies
      EcorePackage.eINSTANCE.eClass();

      // Create package meta-data objects
      thePmsPackage.createPackageContents();

      // Initialize created meta-data
      thePmsPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      thePmsPackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(PmsPackage.eNS_URI, thePmsPackage);
      return thePmsPackage;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getPMSModel()
   {
      return pmsModelEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPMSModel_PerformanceMeasurements()
   {
      return (EReference)pmsModelEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getPerformanceMeasurement()
   {
      return performanceMeasurementEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPerformanceMeasurement_MeasurementSpecification()
   {
      return (EReference)performanceMeasurementEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getPerformanceMeasurement_PcmElementClassifier()
   {
      return (EReference)performanceMeasurementEClass.getEStructuralFeatures().get(1);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getMeasurementSpecification()
   {
      return measurementSpecificationEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EReference getMeasurementSpecification_TemporalRestriction()
   {
      return (EReference)measurementSpecificationEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getMeasurementSpecification_PerformanceMetric()
   {
      return (EAttribute)measurementSpecificationEClass.getEStructuralFeatures().get(1);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getMeasurementSpecification_StatisticalCharacterization()
   {
      return (EAttribute)measurementSpecificationEClass.getEStructuralFeatures().get(2);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTemporalCharacterization()
   {
      return temporalCharacterizationEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getIntervall()
   {
      return intervallEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getIntervall_Intervall()
   {
      return (EAttribute)intervallEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getDelayedIntervall()
   {
      return delayedIntervallEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getDelayedIntervall_Delay()
   {
      return (EAttribute)delayedIntervallEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getTimeFrame()
   {
      return timeFrameEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTimeFrame_Start()
   {
      return (EAttribute)timeFrameEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getTimeFrame_Stop()
   {
      return (EAttribute)timeFrameEClass.getEStructuralFeatures().get(1);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EClass getUniqueElement()
   {
      return uniqueElementEClass;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getUniqueElement_Guid()
   {
      return (EAttribute)uniqueElementEClass.getEStructuralFeatures().get(0);
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getPerformanceMetricEnum()
   {
      return performanceMetricEnumEEnum;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public EEnum getStatisticalCharacterizationEnum()
   {
      return statisticalCharacterizationEnumEEnum;
   }


   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public PmsFactory getPmsFactory()
   {
      return (PmsFactory)getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private boolean isCreated = false;


   /**
    * Creates the meta-model objects for the package.  This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      pmsModelEClass = createEClass(PMS_MODEL);
      createEReference(pmsModelEClass, PMS_MODEL__PERFORMANCE_MEASUREMENTS);

      performanceMeasurementEClass = createEClass(PERFORMANCE_MEASUREMENT);
      createEReference(performanceMeasurementEClass, PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION);
      createEReference(performanceMeasurementEClass, PERFORMANCE_MEASUREMENT__PCM_ELEMENT_CLASSIFIER);

      measurementSpecificationEClass = createEClass(MEASUREMENT_SPECIFICATION);
      createEReference(measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION);
      createEAttribute(measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC);
      createEAttribute(measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION);

      temporalCharacterizationEClass = createEClass(TEMPORAL_CHARACTERIZATION);

      intervallEClass = createEClass(INTERVALL);
      createEAttribute(intervallEClass, INTERVALL__INTERVALL);

      delayedIntervallEClass = createEClass(DELAYED_INTERVALL);
      createEAttribute(delayedIntervallEClass, DELAYED_INTERVALL__DELAY);

      timeFrameEClass = createEClass(TIME_FRAME);
      createEAttribute(timeFrameEClass, TIME_FRAME__START);
      createEAttribute(timeFrameEClass, TIME_FRAME__STOP);

      uniqueElementEClass = createEClass(UNIQUE_ELEMENT);
      createEAttribute(uniqueElementEClass, UNIQUE_ELEMENT__GUID);

      // Create enums
      performanceMetricEnumEEnum = createEEnum(PERFORMANCE_METRIC_ENUM);
      statisticalCharacterizationEnumEEnum = createEEnum(STATISTICAL_CHARACTERIZATION_ENUM);
   }

   /**
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   private boolean isInitialized = false;


   /**
    * Complete the initialization of the package and its meta-model.  This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc --> <!-- end-user-doc -->
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Obtain other dependent packages
      EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

      // Create type parameters

      // Set bounds for type parameters

      // Add supertypes to classes
      pmsModelEClass.getESuperTypes().add(this.getUniqueElement());
      performanceMeasurementEClass.getESuperTypes().add(this.getUniqueElement());
      measurementSpecificationEClass.getESuperTypes().add(this.getUniqueElement());
      temporalCharacterizationEClass.getESuperTypes().add(this.getUniqueElement());
      intervallEClass.getESuperTypes().add(this.getTemporalCharacterization());
      delayedIntervallEClass.getESuperTypes().add(this.getIntervall());
      timeFrameEClass.getESuperTypes().add(this.getTemporalCharacterization());

      // Initialize classes and features; add operations and parameters
      initEClass(pmsModelEClass, PMSModel.class, "PMSModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPMSModel_PerformanceMeasurements(), this.getPerformanceMeasurement(), null, "performanceMeasurements", null, 0, -1, PMSModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(performanceMeasurementEClass, PerformanceMeasurement.class, "PerformanceMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getPerformanceMeasurement_MeasurementSpecification(), this.getMeasurementSpecification(), null, "measurementSpecification", null, 1, -1, PerformanceMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getPerformanceMeasurement_PcmElementClassifier(), theEcorePackage.getEClass(), null, "pcmElementClassifier", null, 0, 1, PerformanceMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(measurementSpecificationEClass, MeasurementSpecification.class, "MeasurementSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEReference(getMeasurementSpecification_TemporalRestriction(), this.getTemporalCharacterization(), null, "temporalRestriction", null, 1, 1, MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getMeasurementSpecification_PerformanceMetric(), this.getPerformanceMetricEnum(), "performanceMetric", null, 1, 1, MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getMeasurementSpecification_StatisticalCharacterization(), this.getStatisticalCharacterizationEnum(), "statisticalCharacterization", null, 1, 1, MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(temporalCharacterizationEClass, TemporalCharacterization.class, "TemporalCharacterization", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

      initEClass(intervallEClass, Intervall.class, "Intervall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getIntervall_Intervall(), ecorePackage.getEDouble(), "intervall", "0.0", 1, 1, Intervall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(delayedIntervallEClass, DelayedIntervall.class, "DelayedIntervall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getDelayedIntervall_Delay(), ecorePackage.getEDouble(), "delay", "0.0", 1, 1, DelayedIntervall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(timeFrameEClass, TimeFrame.class, "TimeFrame", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getTimeFrame_Start(), ecorePackage.getEDouble(), "start", "0.0", 1, 1, TimeFrame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEAttribute(getTimeFrame_Stop(), ecorePackage.getEDouble(), "stop", "0.0", 1, 1, TimeFrame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(uniqueElementEClass, UniqueElement.class, "UniqueElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getUniqueElement_Guid(), ecorePackage.getEString(), "guid", null, 0, 1, UniqueElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      addEOperation(uniqueElementEClass, null, "createAndSetGuid", 0, 1, IS_UNIQUE, IS_ORDERED);

      // Initialize enums and add enum literals
      initEEnum(performanceMetricEnumEEnum, PerformanceMetricEnum.class, "PerformanceMetricEnum");
      addEEnumLiteral(performanceMetricEnumEEnum, PerformanceMetricEnum.WAITING_TIME);
      addEEnumLiteral(performanceMetricEnumEEnum, PerformanceMetricEnum.RESPONSE_TIME);
      addEEnumLiteral(performanceMetricEnumEEnum, PerformanceMetricEnum.UTILIZATION);
      addEEnumLiteral(performanceMetricEnumEEnum, PerformanceMetricEnum.ARRIVAL_RATE);
      addEEnumLiteral(performanceMetricEnumEEnum, PerformanceMetricEnum.THROUGHPUT);

      initEEnum(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.class, "StatisticalCharacterizationEnum");
      addEEnumLiteral(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.NONE);
      addEEnumLiteral(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.MEDIAN);
      addEEnumLiteral(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
      addEEnumLiteral(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.GEOMETRIC_MEAN);
      addEEnumLiteral(statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.HARMONIC_MEAN);

      // Create resource
      createResource(eNS_URI);
   }

} // PmsPackageImpl
