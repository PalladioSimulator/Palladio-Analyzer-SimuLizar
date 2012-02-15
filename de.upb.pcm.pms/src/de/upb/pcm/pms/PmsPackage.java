/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.upb.pcm.pms.PmsFactory
 * @model kind="package"
 * @generated
 */
public interface PmsPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "pms";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http:///de/upb/pcm/pms/model/pms.ecore";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "pms";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   PmsPackage eINSTANCE = de.upb.pcm.pms.impl.PmsPackageImpl.init();

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.UniqueElementImpl <em>Unique Element</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.UniqueElementImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getUniqueElement()
    * @generated
    */
   int UNIQUE_ELEMENT = 7;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIQUE_ELEMENT__GUID = 0;

   /**
    * The number of structural features of the '<em>Unique Element</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIQUE_ELEMENT_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.PMSModelImpl <em>PMS Model</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.PMSModelImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPMSModel()
    * @generated
    */
   int PMS_MODEL = 0;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PMS_MODEL__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The feature id for the '<em><b>Performance Measurements</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PMS_MODEL__PERFORMANCE_MEASUREMENTS = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PMS Model</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PMS_MODEL_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.PerformanceMeasurementImpl <em>Performance Measurement</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.PerformanceMeasurementImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPerformanceMeasurement()
    * @generated
    */
   int PERFORMANCE_MEASUREMENT = 1;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERFORMANCE_MEASUREMENT__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The feature id for the '<em><b>Measurement Specification</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Pcm Element Classifier</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERFORMANCE_MEASUREMENT__PCM_ELEMENT_CLASSIFIER = UNIQUE_ELEMENT_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Performance Measurement</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERFORMANCE_MEASUREMENT_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.MeasurementSpecificationImpl <em>Measurement Specification</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.MeasurementSpecificationImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getMeasurementSpecification()
    * @generated
    */
   int MEASUREMENT_SPECIFICATION = 2;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MEASUREMENT_SPECIFICATION__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The feature id for the '<em><b>Temporal Restriction</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Performance Metric</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC = UNIQUE_ELEMENT_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Statistical Characterization</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION = UNIQUE_ELEMENT_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>Measurement Specification</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int MEASUREMENT_SPECIFICATION_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 3;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.TemporalCharacterizationImpl <em>Temporal Characterization</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.TemporalCharacterizationImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getTemporalCharacterization()
    * @generated
    */
   int TEMPORAL_CHARACTERIZATION = 3;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORAL_CHARACTERIZATION__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The number of structural features of the '<em>Temporal Characterization</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TEMPORAL_CHARACTERIZATION_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.IntervallImpl <em>Intervall</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.IntervallImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getIntervall()
    * @generated
    */
   int INTERVALL = 4;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INTERVALL__GUID = TEMPORAL_CHARACTERIZATION__GUID;

   /**
    * The feature id for the '<em><b>Intervall</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INTERVALL__INTERVALL = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Intervall</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int INTERVALL_FEATURE_COUNT = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.DelayedIntervallImpl <em>Delayed Intervall</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.DelayedIntervallImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getDelayedIntervall()
    * @generated
    */
   int DELAYED_INTERVALL = 5;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DELAYED_INTERVALL__GUID = INTERVALL__GUID;

   /**
    * The feature id for the '<em><b>Intervall</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DELAYED_INTERVALL__INTERVALL = INTERVALL__INTERVALL;

   /**
    * The feature id for the '<em><b>Delay</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DELAYED_INTERVALL__DELAY = INTERVALL_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Delayed Intervall</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int DELAYED_INTERVALL_FEATURE_COUNT = INTERVALL_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.impl.TimeFrameImpl <em>Time Frame</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.impl.TimeFrameImpl
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getTimeFrame()
    * @generated
    */
   int TIME_FRAME = 6;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TIME_FRAME__GUID = TEMPORAL_CHARACTERIZATION__GUID;

   /**
    * The feature id for the '<em><b>Start</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TIME_FRAME__START = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Stop</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TIME_FRAME__STOP = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Time Frame</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int TIME_FRAME_FEATURE_COUNT = TEMPORAL_CHARACTERIZATION_FEATURE_COUNT + 2;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.PerformanceMetricEnum <em>Performance Metric Enum</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.PerformanceMetricEnum
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPerformanceMetricEnum()
    * @generated
    */
   int PERFORMANCE_METRIC_ENUM = 8;

   /**
    * The meta object id for the '{@link de.upb.pcm.pms.StatisticalCharacterizationEnum <em>Statistical Characterization Enum</em>}' enum.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.pms.StatisticalCharacterizationEnum
    * @see de.upb.pcm.pms.impl.PmsPackageImpl#getStatisticalCharacterizationEnum()
    * @generated
    */
   int STATISTICAL_CHARACTERIZATION_ENUM = 9;


   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.PMSModel <em>PMS Model</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PMS Model</em>'.
    * @see de.upb.pcm.pms.PMSModel
    * @generated
    */
   EClass getPMSModel();

   /**
    * Returns the meta object for the containment reference list '{@link de.upb.pcm.pms.PMSModel#getPerformanceMeasurements <em>Performance Measurements</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Performance Measurements</em>'.
    * @see de.upb.pcm.pms.PMSModel#getPerformanceMeasurements()
    * @see #getPMSModel()
    * @generated
    */
   EReference getPMSModel_PerformanceMeasurements();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.PerformanceMeasurement <em>Performance Measurement</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Performance Measurement</em>'.
    * @see de.upb.pcm.pms.PerformanceMeasurement
    * @generated
    */
   EClass getPerformanceMeasurement();

   /**
    * Returns the meta object for the containment reference list '{@link de.upb.pcm.pms.PerformanceMeasurement#getMeasurementSpecification <em>Measurement Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Measurement Specification</em>'.
    * @see de.upb.pcm.pms.PerformanceMeasurement#getMeasurementSpecification()
    * @see #getPerformanceMeasurement()
    * @generated
    */
   EReference getPerformanceMeasurement_MeasurementSpecification();

   /**
    * Returns the meta object for the reference '{@link de.upb.pcm.pms.PerformanceMeasurement#getPcmElementClassifier <em>Pcm Element Classifier</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Pcm Element Classifier</em>'.
    * @see de.upb.pcm.pms.PerformanceMeasurement#getPcmElementClassifier()
    * @see #getPerformanceMeasurement()
    * @generated
    */
   EReference getPerformanceMeasurement_PcmElementClassifier();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.MeasurementSpecification <em>Measurement Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Measurement Specification</em>'.
    * @see de.upb.pcm.pms.MeasurementSpecification
    * @generated
    */
   EClass getMeasurementSpecification();

   /**
    * Returns the meta object for the containment reference '{@link de.upb.pcm.pms.MeasurementSpecification#getTemporalRestriction <em>Temporal Restriction</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference '<em>Temporal Restriction</em>'.
    * @see de.upb.pcm.pms.MeasurementSpecification#getTemporalRestriction()
    * @see #getMeasurementSpecification()
    * @generated
    */
   EReference getMeasurementSpecification_TemporalRestriction();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.MeasurementSpecification#getPerformanceMetric <em>Performance Metric</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Performance Metric</em>'.
    * @see de.upb.pcm.pms.MeasurementSpecification#getPerformanceMetric()
    * @see #getMeasurementSpecification()
    * @generated
    */
   EAttribute getMeasurementSpecification_PerformanceMetric();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.MeasurementSpecification#getStatisticalCharacterization <em>Statistical Characterization</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Statistical Characterization</em>'.
    * @see de.upb.pcm.pms.MeasurementSpecification#getStatisticalCharacterization()
    * @see #getMeasurementSpecification()
    * @generated
    */
   EAttribute getMeasurementSpecification_StatisticalCharacterization();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.TemporalCharacterization <em>Temporal Characterization</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Temporal Characterization</em>'.
    * @see de.upb.pcm.pms.TemporalCharacterization
    * @generated
    */
   EClass getTemporalCharacterization();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.Intervall <em>Intervall</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Intervall</em>'.
    * @see de.upb.pcm.pms.Intervall
    * @generated
    */
   EClass getIntervall();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.Intervall#getIntervall <em>Intervall</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Intervall</em>'.
    * @see de.upb.pcm.pms.Intervall#getIntervall()
    * @see #getIntervall()
    * @generated
    */
   EAttribute getIntervall_Intervall();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.DelayedIntervall <em>Delayed Intervall</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Delayed Intervall</em>'.
    * @see de.upb.pcm.pms.DelayedIntervall
    * @generated
    */
   EClass getDelayedIntervall();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.DelayedIntervall#getDelay <em>Delay</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Delay</em>'.
    * @see de.upb.pcm.pms.DelayedIntervall#getDelay()
    * @see #getDelayedIntervall()
    * @generated
    */
   EAttribute getDelayedIntervall_Delay();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.TimeFrame <em>Time Frame</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Time Frame</em>'.
    * @see de.upb.pcm.pms.TimeFrame
    * @generated
    */
   EClass getTimeFrame();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.TimeFrame#getStart <em>Start</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Start</em>'.
    * @see de.upb.pcm.pms.TimeFrame#getStart()
    * @see #getTimeFrame()
    * @generated
    */
   EAttribute getTimeFrame_Start();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.TimeFrame#getStop <em>Stop</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Stop</em>'.
    * @see de.upb.pcm.pms.TimeFrame#getStop()
    * @see #getTimeFrame()
    * @generated
    */
   EAttribute getTimeFrame_Stop();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.pms.UniqueElement <em>Unique Element</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Unique Element</em>'.
    * @see de.upb.pcm.pms.UniqueElement
    * @generated
    */
   EClass getUniqueElement();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.pms.UniqueElement#getGuid <em>Guid</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Guid</em>'.
    * @see de.upb.pcm.pms.UniqueElement#getGuid()
    * @see #getUniqueElement()
    * @generated
    */
   EAttribute getUniqueElement_Guid();

   /**
    * Returns the meta object for enum '{@link de.upb.pcm.pms.PerformanceMetricEnum <em>Performance Metric Enum</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Performance Metric Enum</em>'.
    * @see de.upb.pcm.pms.PerformanceMetricEnum
    * @generated
    */
   EEnum getPerformanceMetricEnum();

   /**
    * Returns the meta object for enum '{@link de.upb.pcm.pms.StatisticalCharacterizationEnum <em>Statistical Characterization Enum</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for enum '<em>Statistical Characterization Enum</em>'.
    * @see de.upb.pcm.pms.StatisticalCharacterizationEnum
    * @generated
    */
   EEnum getStatisticalCharacterizationEnum();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   PmsFactory getPmsFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.PMSModelImpl <em>PMS Model</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.PMSModelImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPMSModel()
       * @generated
       */
      EClass PMS_MODEL = eINSTANCE.getPMSModel();

      /**
       * The meta object literal for the '<em><b>Performance Measurements</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PMS_MODEL__PERFORMANCE_MEASUREMENTS = eINSTANCE.getPMSModel_PerformanceMeasurements();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.PerformanceMeasurementImpl <em>Performance Measurement</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.PerformanceMeasurementImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPerformanceMeasurement()
       * @generated
       */
      EClass PERFORMANCE_MEASUREMENT = eINSTANCE.getPerformanceMeasurement();

      /**
       * The meta object literal for the '<em><b>Measurement Specification</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION = eINSTANCE.getPerformanceMeasurement_MeasurementSpecification();

      /**
       * The meta object literal for the '<em><b>Pcm Element Classifier</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PERFORMANCE_MEASUREMENT__PCM_ELEMENT_CLASSIFIER = eINSTANCE.getPerformanceMeasurement_PcmElementClassifier();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.MeasurementSpecificationImpl <em>Measurement Specification</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.MeasurementSpecificationImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getMeasurementSpecification()
       * @generated
       */
      EClass MEASUREMENT_SPECIFICATION = eINSTANCE.getMeasurementSpecification();

      /**
       * The meta object literal for the '<em><b>Temporal Restriction</b></em>' containment reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION = eINSTANCE.getMeasurementSpecification_TemporalRestriction();

      /**
       * The meta object literal for the '<em><b>Performance Metric</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC = eINSTANCE.getMeasurementSpecification_PerformanceMetric();

      /**
       * The meta object literal for the '<em><b>Statistical Characterization</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION = eINSTANCE.getMeasurementSpecification_StatisticalCharacterization();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.TemporalCharacterizationImpl <em>Temporal Characterization</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.TemporalCharacterizationImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getTemporalCharacterization()
       * @generated
       */
      EClass TEMPORAL_CHARACTERIZATION = eINSTANCE.getTemporalCharacterization();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.IntervallImpl <em>Intervall</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.IntervallImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getIntervall()
       * @generated
       */
      EClass INTERVALL = eINSTANCE.getIntervall();

      /**
       * The meta object literal for the '<em><b>Intervall</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute INTERVALL__INTERVALL = eINSTANCE.getIntervall_Intervall();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.DelayedIntervallImpl <em>Delayed Intervall</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.DelayedIntervallImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getDelayedIntervall()
       * @generated
       */
      EClass DELAYED_INTERVALL = eINSTANCE.getDelayedIntervall();

      /**
       * The meta object literal for the '<em><b>Delay</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute DELAYED_INTERVALL__DELAY = eINSTANCE.getDelayedIntervall_Delay();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.TimeFrameImpl <em>Time Frame</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.TimeFrameImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getTimeFrame()
       * @generated
       */
      EClass TIME_FRAME = eINSTANCE.getTimeFrame();

      /**
       * The meta object literal for the '<em><b>Start</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TIME_FRAME__START = eINSTANCE.getTimeFrame_Start();

      /**
       * The meta object literal for the '<em><b>Stop</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute TIME_FRAME__STOP = eINSTANCE.getTimeFrame_Stop();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.impl.UniqueElementImpl <em>Unique Element</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.impl.UniqueElementImpl
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getUniqueElement()
       * @generated
       */
      EClass UNIQUE_ELEMENT = eINSTANCE.getUniqueElement();

      /**
       * The meta object literal for the '<em><b>Guid</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute UNIQUE_ELEMENT__GUID = eINSTANCE.getUniqueElement_Guid();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.PerformanceMetricEnum <em>Performance Metric Enum</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.PerformanceMetricEnum
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getPerformanceMetricEnum()
       * @generated
       */
      EEnum PERFORMANCE_METRIC_ENUM = eINSTANCE.getPerformanceMetricEnum();

      /**
       * The meta object literal for the '{@link de.upb.pcm.pms.StatisticalCharacterizationEnum <em>Statistical Characterization Enum</em>}' enum.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.pms.StatisticalCharacterizationEnum
       * @see de.upb.pcm.pms.impl.PmsPackageImpl#getStatisticalCharacterizationEnum()
       * @generated
       */
      EEnum STATISTICAL_CHARACTERIZATION_ENUM = eINSTANCE.getStatisticalCharacterizationEnum();

   }

} //PmsPackage
