/**
 */
package org.palladiosimulator.runtimemeasurement.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class RuntimeMeasurementPackageImpl extends EPackageImpl implements RuntimeMeasurementPackage {

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass runtimeMeasurementModelEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass runtimeMeasurementEClass = null;

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
     * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private RuntimeMeasurementPackageImpl() {
        super(eNS_URI, RuntimeMeasurementFactory.eINSTANCE);
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
     * This method is used to initialize {@link RuntimeMeasurementPackage#eINSTANCE} when that field
     * is accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static RuntimeMeasurementPackage init() {
        if (isInited) {
            return (RuntimeMeasurementPackage) EPackage.Registry.INSTANCE
                    .getEPackage(RuntimeMeasurementPackage.eNS_URI);
        }

        // Obtain or create and register package
        final RuntimeMeasurementPackageImpl theRuntimeMeasurementPackage = (RuntimeMeasurementPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof RuntimeMeasurementPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new RuntimeMeasurementPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        MonitorRepositoryPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theRuntimeMeasurementPackage.createPackageContents();

        // Initialize created meta-data
        theRuntimeMeasurementPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theRuntimeMeasurementPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(RuntimeMeasurementPackage.eNS_URI, theRuntimeMeasurementPackage);
        return theRuntimeMeasurementPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRuntimeMeasurementModel() {
        return this.runtimeMeasurementModelEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRuntimeMeasurementModel_Measurements() {
        return (EReference) this.runtimeMeasurementModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRuntimeMeasurement() {
        return this.runtimeMeasurementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRuntimeMeasurement_MeasuringPoint() {
        return (EReference) this.runtimeMeasurementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRuntimeMeasurement_MeasurementSpecification() {
        return (EReference) this.runtimeMeasurementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRuntimeMeasurement_MeasuringValue() {
        return (EAttribute) this.runtimeMeasurementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RuntimeMeasurementFactory getRuntimeMeasurementFactory() {
        return (RuntimeMeasurementFactory) this.getEFactoryInstance();
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
        this.runtimeMeasurementModelEClass = this.createEClass(RUNTIME_MEASUREMENT_MODEL);
        this.createEReference(this.runtimeMeasurementModelEClass, RUNTIME_MEASUREMENT_MODEL__MEASUREMENTS);

        this.runtimeMeasurementEClass = this.createEClass(RUNTIME_MEASUREMENT);
        this.createEReference(this.runtimeMeasurementEClass, RUNTIME_MEASUREMENT__MEASURING_POINT);
        this.createEReference(this.runtimeMeasurementEClass, RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION);
        this.createEAttribute(this.runtimeMeasurementEClass, RUNTIME_MEASUREMENT__MEASURING_VALUE);
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
        final IdentifierPackage theIdentifierPackage = (IdentifierPackage) EPackage.Registry.INSTANCE
                .getEPackage(IdentifierPackage.eNS_URI);
        final MeasuringpointPackage theMeasuringpointPackage = (MeasuringpointPackage) EPackage.Registry.INSTANCE
                .getEPackage(MeasuringpointPackage.eNS_URI);
        final MonitorRepositoryPackage theMonitorRepositoryPackage = (MonitorRepositoryPackage) EPackage.Registry.INSTANCE
                .getEPackage(MonitorRepositoryPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.runtimeMeasurementModelEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        this.runtimeMeasurementEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.runtimeMeasurementModelEClass, RuntimeMeasurementModel.class, "RuntimeMeasurementModel",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRuntimeMeasurementModel_Measurements(), this.getRuntimeMeasurement(), null,
                "measurements", null, 0, -1, RuntimeMeasurementModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.runtimeMeasurementEClass, RuntimeMeasurement.class, "RuntimeMeasurement", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRuntimeMeasurement_MeasuringPoint(), theMeasuringpointPackage.getMeasuringPoint(),
                null, "measuringPoint", null, 0, 1, RuntimeMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRuntimeMeasurement_MeasurementSpecification(),
                theMonitorRepositoryPackage.getMeasurementSpecification(), null, "measurementSpecification", null, 0,
                1, RuntimeMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getRuntimeMeasurement_MeasuringValue(), this.ecorePackage.getEDouble(),
                "measuringValue", "0.0", 0, 1, RuntimeMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // RuntimeMeasurementPackageImpl
