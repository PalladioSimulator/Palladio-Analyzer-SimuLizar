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
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.UniqueElement;

import de.uka.ipd.sdq.pcm.resourcetype.ResourcetypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class PrmPackageImpl extends EPackageImpl implements PrmPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass prmModelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass pcmModelElementMeasurementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass uniqueElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass resourceContainerMeasurementEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.palladiosimulator.simulizar.prm.PrmPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PrmPackageImpl() {
        super(eNS_URI, PrmFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link PrmPackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to
     * obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PrmPackage init() {
        if (isInited) {
            return (PrmPackage) EPackage.Registry.INSTANCE.getEPackage(PrmPackage.eNS_URI);
        }

        // Obtain or create and register package
        final PrmPackageImpl thePrmPackage = (PrmPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PrmPackageImpl ? EPackage.Registry.INSTANCE
                .get(eNS_URI) : new PrmPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        MonitorrepositoryPackage.eINSTANCE.eClass();

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
     *
     * @generated
     */
    @Override
    public EClass getPRMModel() {
        return this.prmModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPRMModel_PcmModelElementMeasurements() {
        return (EReference) this.prmModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPCMModelElementMeasurement() {
        return this.pcmModelElementMeasurementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPCMModelElementMeasurement_PcmModelElement() {
        return (EReference) this.pcmModelElementMeasurementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPCMModelElementMeasurement_MeasurementSpecification() {
        return (EReference) this.pcmModelElementMeasurementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPCMModelElementMeasurement_MeasurementValue() {
        return (EAttribute) this.pcmModelElementMeasurementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUniqueElement() {
        return this.uniqueElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUniqueElement_Guid() {
        return (EAttribute) this.uniqueElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getResourceContainerMeasurement() {
        return this.resourceContainerMeasurementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getResourceContainerMeasurement_ProcessingResourceType() {
        return (EReference) this.resourceContainerMeasurementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PrmFactory getPrmFactory() {
        return (PrmFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;

        // Create classes and their features
        this.prmModelEClass = this.createEClass(PRM_MODEL);
        this.createEReference(this.prmModelEClass, PRM_MODEL__PCM_MODEL_ELEMENT_MEASUREMENTS);

        this.pcmModelElementMeasurementEClass = this.createEClass(PCM_MODEL_ELEMENT_MEASUREMENT);
        this.createEReference(this.pcmModelElementMeasurementEClass, PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT);
        this.createEReference(this.pcmModelElementMeasurementEClass,
                PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION);
        this.createEAttribute(this.pcmModelElementMeasurementEClass, PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE);

        this.uniqueElementEClass = this.createEClass(UNIQUE_ELEMENT);
        this.createEAttribute(this.uniqueElementEClass, UNIQUE_ELEMENT__GUID);

        this.resourceContainerMeasurementEClass = this.createEClass(RESOURCE_CONTAINER_MEASUREMENT);
        this.createEReference(this.resourceContainerMeasurementEClass,
                RESOURCE_CONTAINER_MEASUREMENT__PROCESSING_RESOURCE_TYPE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Obtain other dependent packages
        final MonitorrepositoryPackage theMonitorrepositoryPackage = (MonitorrepositoryPackage) EPackage.Registry.INSTANCE
                .getEPackage(MonitorrepositoryPackage.eNS_URI);
        final ResourcetypePackage theResourcetypePackage = (ResourcetypePackage) EPackage.Registry.INSTANCE
                .getEPackage(ResourcetypePackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.prmModelEClass.getESuperTypes().add(this.getUniqueElement());
        this.pcmModelElementMeasurementEClass.getESuperTypes().add(this.getUniqueElement());
        this.resourceContainerMeasurementEClass.getESuperTypes().add(this.getPCMModelElementMeasurement());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.prmModelEClass, PRMModel.class, "PRMModel", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getPRMModel_PcmModelElementMeasurements(), this.getPCMModelElementMeasurement(), null,
                "pcmModelElementMeasurements", null, 0, -1, PRMModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.pcmModelElementMeasurementEClass, PCMModelElementMeasurement.class,
                "PCMModelElementMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getPCMModelElementMeasurement_PcmModelElement(), this.ecorePackage.getEObject(), null,
                "pcmModelElement", null, 0, 1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getPCMModelElementMeasurement_MeasurementSpecification(),
                theMonitorrepositoryPackage.getMeasurementSpecification(), null, "measurementSpecification", null, 0,
                1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getPCMModelElementMeasurement_MeasurementValue(), this.ecorePackage.getEDouble(),
                "measurementValue", "0.0", 0, 1, PCMModelElementMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.uniqueElementEClass, UniqueElement.class, "UniqueElement", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getUniqueElement_Guid(), this.ecorePackage.getEString(), "guid", null, 0, 1,
                UniqueElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.addEOperation(this.uniqueElementEClass, null, "createAndSetGuid", 0, 1, IS_UNIQUE, IS_ORDERED);

        this.initEClass(this.resourceContainerMeasurementEClass, ResourceContainerMeasurement.class,
                "ResourceContainerMeasurement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getResourceContainerMeasurement_ProcessingResourceType(),
                theResourcetypePackage.getProcessingResourceType(), null, "processingResourceType", null, 0, 1,
                ResourceContainerMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // PrmPackageImpl
