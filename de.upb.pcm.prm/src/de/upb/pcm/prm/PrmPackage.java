/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.prm;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see de.upb.pcm.prm.PrmFactory
 * @model kind="package"
 * @generated
 */
public interface PrmPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "prm";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http:///de/upb/pcm/prm/model/prm.ecore";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "prm";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   PrmPackage eINSTANCE = de.upb.pcm.prm.impl.PrmPackageImpl.init();

   /**
    * The meta object id for the '{@link de.upb.pcm.prm.impl.UniqueElementImpl <em>Unique Element</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.prm.impl.UniqueElementImpl
    * @see de.upb.pcm.prm.impl.PrmPackageImpl#getUniqueElement()
    * @generated
    */
   int UNIQUE_ELEMENT = 2;

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
    * The meta object id for the '{@link de.upb.pcm.prm.impl.PRMModelImpl <em>PRM Model</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.prm.impl.PRMModelImpl
    * @see de.upb.pcm.prm.impl.PrmPackageImpl#getPRMModel()
    * @generated
    */
   int PRM_MODEL = 0;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PRM_MODEL__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The feature id for the '<em><b>Pcm Model Element Measurements</b></em>' containment reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>PRM Model</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PRM_MODEL_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 1;

   /**
    * The meta object id for the '{@link de.upb.pcm.prm.impl.PCMModelElementMeasurementImpl <em>PCM Model Element Measurement</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.prm.impl.PCMModelElementMeasurementImpl
    * @see de.upb.pcm.prm.impl.PrmPackageImpl#getPCMModelElementMeasurement()
    * @generated
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT = 1;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT__GUID = UNIQUE_ELEMENT__GUID;

   /**
    * The feature id for the '<em><b>Pcm Model Element</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT = UNIQUE_ELEMENT_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Measurement Specification</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION = UNIQUE_ELEMENT_FEATURE_COUNT + 1;

   /**
    * The feature id for the '<em><b>Measurement Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE = UNIQUE_ELEMENT_FEATURE_COUNT + 2;

   /**
    * The number of structural features of the '<em>PCM Model Element Measurement</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PCM_MODEL_ELEMENT_MEASUREMENT_FEATURE_COUNT = UNIQUE_ELEMENT_FEATURE_COUNT + 3;


   /**
    * The meta object id for the '{@link de.upb.pcm.prm.impl.ResourceContainerMeasurementImpl <em>Resource Container Measurement</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see de.upb.pcm.prm.impl.ResourceContainerMeasurementImpl
    * @see de.upb.pcm.prm.impl.PrmPackageImpl#getResourceContainerMeasurement()
    * @generated
    */
   int RESOURCE_CONTAINER_MEASUREMENT = 3;

   /**
    * The feature id for the '<em><b>Guid</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT__GUID = PCM_MODEL_ELEMENT_MEASUREMENT__GUID;

   /**
    * The feature id for the '<em><b>Pcm Model Element</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT__PCM_MODEL_ELEMENT = PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT;

   /**
    * The feature id for the '<em><b>Measurement Specification</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT__MEASUREMENT_SPECIFICATION = PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION;

   /**
    * The feature id for the '<em><b>Measurement Value</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT__MEASUREMENT_VALUE = PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE;

   /**
    * The feature id for the '<em><b>Processing Resource Type</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE = PCM_MODEL_ELEMENT_MEASUREMENT_FEATURE_COUNT + 0;

   /**
    * The number of structural features of the '<em>Resource Container Measurement</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int RESOURCE_CONTAINER_MEASUREMENT_FEATURE_COUNT = PCM_MODEL_ELEMENT_MEASUREMENT_FEATURE_COUNT + 1;


   /**
    * Returns the meta object for class '{@link de.upb.pcm.prm.PRMModel <em>PRM Model</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PRM Model</em>'.
    * @see de.upb.pcm.prm.PRMModel
    * @generated
    */
   EClass getPRMModel();

   /**
    * Returns the meta object for the containment reference list '{@link de.upb.pcm.prm.PRMModel#getPcmModelElementMeasurements <em>Pcm Model Element Measurements</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the containment reference list '<em>Pcm Model Element Measurements</em>'.
    * @see de.upb.pcm.prm.PRMModel#getPcmModelElementMeasurements()
    * @see #getPRMModel()
    * @generated
    */
   EReference getPRMModel_PcmModelElementMeasurements();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.prm.PCMModelElementMeasurement <em>PCM Model Element Measurement</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>PCM Model Element Measurement</em>'.
    * @see de.upb.pcm.prm.PCMModelElementMeasurement
    * @generated
    */
   EClass getPCMModelElementMeasurement();

   /**
    * Returns the meta object for the reference '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getPcmModelElement <em>Pcm Model Element</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Pcm Model Element</em>'.
    * @see de.upb.pcm.prm.PCMModelElementMeasurement#getPcmModelElement()
    * @see #getPCMModelElementMeasurement()
    * @generated
    */
   EReference getPCMModelElementMeasurement_PcmModelElement();

   /**
    * Returns the meta object for the reference '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementSpecification <em>Measurement Specification</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Measurement Specification</em>'.
    * @see de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementSpecification()
    * @see #getPCMModelElementMeasurement()
    * @generated
    */
   EReference getPCMModelElementMeasurement_MeasurementSpecification();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementValue <em>Measurement Value</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Measurement Value</em>'.
    * @see de.upb.pcm.prm.PCMModelElementMeasurement#getMeasurementValue()
    * @see #getPCMModelElementMeasurement()
    * @generated
    */
   EAttribute getPCMModelElementMeasurement_MeasurementValue();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.prm.UniqueElement <em>Unique Element</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Unique Element</em>'.
    * @see de.upb.pcm.prm.UniqueElement
    * @generated
    */
   EClass getUniqueElement();

   /**
    * Returns the meta object for the attribute '{@link de.upb.pcm.prm.UniqueElement#getGuid <em>Guid</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Guid</em>'.
    * @see de.upb.pcm.prm.UniqueElement#getGuid()
    * @see #getUniqueElement()
    * @generated
    */
   EAttribute getUniqueElement_Guid();

   /**
    * Returns the meta object for class '{@link de.upb.pcm.prm.ResourceContainerMeasurement <em>Resource Container Measurement</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Resource Container Measurement</em>'.
    * @see de.upb.pcm.prm.ResourceContainerMeasurement
    * @generated
    */
   EClass getResourceContainerMeasurement();

   /**
    * Returns the meta object for the reference '{@link de.upb.pcm.prm.ResourceContainerMeasurement#getProcessingResourceType <em>Processing Resource Type</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Processing Resource Type</em>'.
    * @see de.upb.pcm.prm.ResourceContainerMeasurement#getProcessingResourceType()
    * @see #getResourceContainerMeasurement()
    * @generated
    */
   EReference getResourceContainerMeasurement_ProcessingResourceType();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   PrmFactory getPrmFactory();

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
       * The meta object literal for the '{@link de.upb.pcm.prm.impl.PRMModelImpl <em>PRM Model</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.prm.impl.PRMModelImpl
       * @see de.upb.pcm.prm.impl.PrmPackageImpl#getPRMModel()
       * @generated
       */
      EClass PRM_MODEL = eINSTANCE.getPRMModel();

      /**
       * The meta object literal for the '<em><b>Pcm Model Element Measurements</b></em>' containment reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS = eINSTANCE.getPRMModel_PcmModelElementMeasurements();

      /**
       * The meta object literal for the '{@link de.upb.pcm.prm.impl.PCMModelElementMeasurementImpl <em>PCM Model Element Measurement</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.prm.impl.PCMModelElementMeasurementImpl
       * @see de.upb.pcm.prm.impl.PrmPackageImpl#getPCMModelElementMeasurement()
       * @generated
       */
      EClass PCM_MODEL_ELEMENT_MEASUREMENT = eINSTANCE.getPCMModelElementMeasurement();

      /**
       * The meta object literal for the '<em><b>Pcm Model Element</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT = eINSTANCE.getPCMModelElementMeasurement_PcmModelElement();

      /**
       * The meta object literal for the '<em><b>Measurement Specification</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION = eINSTANCE.getPCMModelElementMeasurement_MeasurementSpecification();

      /**
       * The meta object literal for the '<em><b>Measurement Value</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE = eINSTANCE.getPCMModelElementMeasurement_MeasurementValue();

      /**
       * The meta object literal for the '{@link de.upb.pcm.prm.impl.UniqueElementImpl <em>Unique Element</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.prm.impl.UniqueElementImpl
       * @see de.upb.pcm.prm.impl.PrmPackageImpl#getUniqueElement()
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
       * The meta object literal for the '{@link de.upb.pcm.prm.impl.ResourceContainerMeasurementImpl <em>Resource Container Measurement</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see de.upb.pcm.prm.impl.ResourceContainerMeasurementImpl
       * @see de.upb.pcm.prm.impl.PrmPackageImpl#getResourceContainerMeasurement()
       * @generated
       */
      EClass RESOURCE_CONTAINER_MEASUREMENT = eINSTANCE.getResourceContainerMeasurement();

      /**
       * The meta object literal for the '<em><b>Processing Resource Type</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE = eINSTANCE.getResourceContainerMeasurement_ProcessingResourceType();

   }

} //PrmPackage
