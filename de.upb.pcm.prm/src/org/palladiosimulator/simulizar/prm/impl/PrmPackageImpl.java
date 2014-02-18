/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.UniqueElement;

import de.uka.ipd.sdq.pcm.PcmPackage;
import de.uka.ipd.sdq.pcm.resourcetype.ResourcetypePackage;


/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class PrmPackageImpl extends EPackageImpl implements PrmPackage
{
   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   private EClass prmModelEClass = null;

   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   private EClass pcmModelElementMeasurementEClass = null;

   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   private EClass uniqueElementEClass = null;

   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   private EClass resourceContainerMeasurementEClass = null;


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
     * @see org.palladiosimulator.simulizar.prm.PrmPackage#eNS_URI
     * @see #init()
     * @generated
     */
   private PrmPackageImpl()
   {
        super(eNS_URI, PrmFactory.eINSTANCE);
    }

   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   private static boolean isInited = false;


   /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link PrmPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
   public static PrmPackage init()
   {
        if (isInited) return (PrmPackage)EPackage.Registry.INSTANCE.getEPackage(PrmPackage.eNS_URI);

        // Obtain or create and register package
        PrmPackageImpl thePrmPackage = (PrmPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PrmPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new PrmPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        PcmPackage.eINSTANCE.eClass();
        PmsPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thePrmPackage.createPackageContents();

        // Initialize created meta-data
        thePrmPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePrmPackage.freeze();

  
        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PrmPackage.eNS_URI, thePrmPackage);
        return thePrmPackage;
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EClass getPRMModel()
   {
        return prmModelEClass;
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EReference getPRMModel_PcmModelElementMeasurements()
   {
        return (EReference)prmModelEClass.getEStructuralFeatures().get(0);
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EClass getPCMModelElementMeasurement()
   {
        return pcmModelElementMeasurementEClass;
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EReference getPCMModelElementMeasurement_PcmModelElement()
   {
        return (EReference)pcmModelElementMeasurementEClass.getEStructuralFeatures().get(0);
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EReference getPCMModelElementMeasurement_MeasurementSpecification()
   {
        return (EReference)pcmModelElementMeasurementEClass.getEStructuralFeatures().get(1);
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EAttribute getPCMModelElementMeasurement_MeasurementValue()
   {
        return (EAttribute)pcmModelElementMeasurementEClass.getEStructuralFeatures().get(2);
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
   public EClass getResourceContainerMeasurement()
   {
        return resourceContainerMeasurementEClass;
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public EReference getResourceContainerMeasurement_ProcessingResourceType()
   {
        return (EReference)resourceContainerMeasurementEClass.getEStructuralFeatures().get(0);
    }


   /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @generated
     */
   public PrmFactory getPrmFactory()
   {
        return (PrmFactory)getEFactoryInstance();
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
        prmModelEClass = createEClass(PRM_MODEL);
        createEReference(prmModelEClass, PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS);

        pcmModelElementMeasurementEClass = createEClass(PCM_MODEL_ELEMENT_MEASUREMENT);
        createEReference(pcmModelElementMeasurementEClass, PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT);
        createEReference(pcmModelElementMeasurementEClass, PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION);
        createEAttribute(pcmModelElementMeasurementEClass, PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE);

        uniqueElementEClass = createEClass(UNIQUE_ELEMENT);
        createEAttribute(uniqueElementEClass, UNIQUE_ELEMENT__GUID);

        resourceContainerMeasurementEClass = createEClass(RESOURCE_CONTAINER_MEASUREMENT);
        createEReference(resourceContainerMeasurementEClass, RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE);
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
        PmsPackage thePmsPackage = (PmsPackage)EPackage.Registry.INSTANCE.getEPackage(PmsPackage.eNS_URI);
        ResourcetypePackage theResourcetypePackage = (ResourcetypePackage)EPackage.Registry.INSTANCE.getEPackage(ResourcetypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        prmModelEClass.getESuperTypes().add(this.getUniqueElement());
        pcmModelElementMeasurementEClass.getESuperTypes().add(this.getUniqueElement());
        resourceContainerMeasurementEClass.getESuperTypes().add(this.getPCMModelElementMeasurement());

        // Initialize classes and features; add operations and parameters
        initEClass(prmModelEClass, PRMModel.class, "PRMModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPRMModel_PcmModelElementMeasurements(), this.getPCMModelElementMeasurement(), null, "pcmModelElementMeasurements", null, 0, -1, PRMModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(pcmModelElementMeasurementEClass, PCMModelElementMeasurement.class, "PCMModelElementMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getPCMModelElementMeasurement_PcmModelElement(), ecorePackage.getEObject(), null, "pcmModelElement", null, 0, 1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getPCMModelElementMeasurement_MeasurementSpecification(), thePmsPackage.getMeasurementSpecification(), null, "measurementSpecification", null, 0, 1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getPCMModelElementMeasurement_MeasurementValue(), ecorePackage.getEDouble(), "measurementValue", "0.0", 0, 1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(uniqueElementEClass, UniqueElement.class, "UniqueElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getUniqueElement_Guid(), ecorePackage.getEString(), "guid", null, 0, 1, UniqueElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        addEOperation(uniqueElementEClass, null, "createAndSetGuid", 0, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(resourceContainerMeasurementEClass, ResourceContainerMeasurement.class, "ResourceContainerMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getResourceContainerMeasurement_ProcessingResourceType(), theResourcetypePackage.getProcessingResourceType(), null, "processingResourceType", null, 0, 1, ResourceContainerMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} // PrmPackageImpl
