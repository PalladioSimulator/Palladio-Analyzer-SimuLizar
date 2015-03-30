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
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;
import org.palladiosimulator.simulizar.prm.PrmPackage;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

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
    private EClass prmMeasurementEClass = null;

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
    public EReference getPRMModel_Measurements() {
        return (EReference) this.prmModelEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getPRMMeasurement() {
        return this.prmMeasurementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPRMMeasurement_MeasuringPoint() {
        return (EReference) this.prmMeasurementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getPRMMeasurement_MeasurementSpecification() {
        return (EReference) this.prmMeasurementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getPRMMeasurement_MeasuringValue() {
        return (EAttribute) this.prmMeasurementEClass.getEStructuralFeatures().get(2);
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
        this.createEReference(this.prmModelEClass, PRM_MODEL__MEASUREMENTS);

        this.prmMeasurementEClass = this.createEClass(PRM_MEASUREMENT);
        this.createEReference(this.prmMeasurementEClass, PRM_MEASUREMENT__MEASURING_POINT);
        this.createEReference(this.prmMeasurementEClass, PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION);
        this.createEAttribute(this.prmMeasurementEClass, PRM_MEASUREMENT__MEASURING_VALUE);
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
        final MonitorrepositoryPackage theMonitorrepositoryPackage = (MonitorrepositoryPackage) EPackage.Registry.INSTANCE
                .getEPackage(MonitorrepositoryPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.prmModelEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        this.prmMeasurementEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.prmModelEClass, PRMModel.class, "PRMModel", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getPRMModel_Measurements(), this.getPRMMeasurement(), null, "measurements", null, 0,
                -1, PRMModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.prmMeasurementEClass, PRMMeasurement.class, "PRMMeasurement", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getPRMMeasurement_MeasuringPoint(), theMeasuringpointPackage.getMeasuringPoint(),
                null, "measuringPoint", null, 0, 1, PRMMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getPRMMeasurement_MeasurementSpecification(),
                theMonitorrepositoryPackage.getMeasurementSpecification(), null, "measurementSpecification", null, 0,
                1, PRMMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getPRMMeasurement_MeasuringValue(), this.ecorePackage.getEDouble(), "measuringValue",
                "0.0", 0, 1, PRMMeasurement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // PrmPackageImpl
