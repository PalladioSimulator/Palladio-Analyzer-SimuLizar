/**
 */
package org.palladiosimulator.simulizar.monitorrepository.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.Repository.RepositoryPackage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall;
import org.palladiosimulator.simulizar.monitorrepository.Intervall;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryFactory;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.simulizar.monitorrepository.TimeFrame;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import de.uka.ipd.sdq.pcm.PcmPackage;
import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class MonitorrepositoryPackageImpl extends EPackageImpl implements MonitorrepositoryPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass monitorRepositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass monitorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass measurementSpecificationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass temporalCharacterizationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass intervallEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass delayedIntervallEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass timeFrameEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum statisticalCharacterizationEnumEEnum = null;

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
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private MonitorrepositoryPackageImpl() {
        super(eNS_URI, MonitorrepositoryFactory.eINSTANCE);
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
     * This method is used to initialize {@link MonitorrepositoryPackage#eINSTANCE} when that field
     * is accessed. Clients should not invoke it directly. Instead, they should simply access that
     * field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static MonitorrepositoryPackage init() {
        if (isInited) {
            return (MonitorrepositoryPackage) EPackage.Registry.INSTANCE.getEPackage(MonitorrepositoryPackage.eNS_URI);
        }

        // Obtain or create and register package
        final MonitorrepositoryPackageImpl theMonitorrepositoryPackage = (MonitorrepositoryPackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof MonitorrepositoryPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new MonitorrepositoryPackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();
        ExperimentDataPackage.eINSTANCE.eClass();
        RepositoryPackage.eINSTANCE.eClass();
        MeasuringpointPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theMonitorrepositoryPackage.createPackageContents();

        // Initialize created meta-data
        theMonitorrepositoryPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theMonitorrepositoryPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(MonitorrepositoryPackage.eNS_URI, theMonitorrepositoryPackage);
        return theMonitorrepositoryPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMonitorRepository() {
        return this.monitorRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMonitorRepository_Monitors() {
        return (EReference) this.monitorRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMonitor() {
        return this.monitorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMonitor_MeasurementSpecifications() {
        return (EReference) this.monitorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMonitor_MeasuringPoint() {
        return (EReference) this.monitorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMonitor_MonitorRepository() {
        return (EReference) this.monitorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getMeasurementSpecification() {
        return this.measurementSpecificationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMeasurementSpecification_TemporalRestriction() {
        return (EReference) this.measurementSpecificationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMeasurementSpecification_StatisticalCharacterization() {
        return (EAttribute) this.measurementSpecificationEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMeasurementSpecification_MetricDescription() {
        return (EReference) this.measurementSpecificationEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getMeasurementSpecification_Monitor() {
        return (EReference) this.measurementSpecificationEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getMeasurementSpecification_Name() {
        return (EAttribute) this.measurementSpecificationEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTemporalCharacterization() {
        return this.temporalCharacterizationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIntervall() {
        return this.intervallEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getIntervall_Intervall() {
        return (EAttribute) this.intervallEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDelayedIntervall() {
        return this.delayedIntervallEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDelayedIntervall_Delay() {
        return (EAttribute) this.delayedIntervallEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTimeFrame() {
        return this.timeFrameEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTimeFrame_Start() {
        return (EAttribute) this.timeFrameEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTimeFrame_Stop() {
        return (EAttribute) this.timeFrameEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getStatisticalCharacterizationEnum() {
        return this.statisticalCharacterizationEnumEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MonitorrepositoryFactory getMonitorrepositoryFactory() {
        return (MonitorrepositoryFactory) this.getEFactoryInstance();
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
        this.monitorRepositoryEClass = this.createEClass(MONITOR_REPOSITORY);
        this.createEReference(this.monitorRepositoryEClass, MONITOR_REPOSITORY__MONITORS);

        this.monitorEClass = this.createEClass(MONITOR);
        this.createEReference(this.monitorEClass, MONITOR__MEASUREMENT_SPECIFICATIONS);
        this.createEReference(this.monitorEClass, MONITOR__MEASURING_POINT);
        this.createEReference(this.monitorEClass, MONITOR__MONITOR_REPOSITORY);

        this.measurementSpecificationEClass = this.createEClass(MEASUREMENT_SPECIFICATION);
        this.createEReference(this.measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION);
        this.createEAttribute(this.measurementSpecificationEClass,
                MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION);
        this.createEReference(this.measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION);
        this.createEReference(this.measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__MONITOR);
        this.createEAttribute(this.measurementSpecificationEClass, MEASUREMENT_SPECIFICATION__NAME);

        this.temporalCharacterizationEClass = this.createEClass(TEMPORAL_CHARACTERIZATION);

        this.intervallEClass = this.createEClass(INTERVALL);
        this.createEAttribute(this.intervallEClass, INTERVALL__INTERVALL);

        this.delayedIntervallEClass = this.createEClass(DELAYED_INTERVALL);
        this.createEAttribute(this.delayedIntervallEClass, DELAYED_INTERVALL__DELAY);

        this.timeFrameEClass = this.createEClass(TIME_FRAME);
        this.createEAttribute(this.timeFrameEClass, TIME_FRAME__START);
        this.createEAttribute(this.timeFrameEClass, TIME_FRAME__STOP);

        // Create enums
        this.statisticalCharacterizationEnumEEnum = this.createEEnum(STATISTICAL_CHARACTERIZATION_ENUM);
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
        final EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE
                .getEPackage(EntityPackage.eNS_URI);
        final MeasuringpointPackage theMeasuringpointPackage = (MeasuringpointPackage) EPackage.Registry.INSTANCE
                .getEPackage(MeasuringpointPackage.eNS_URI);
        final IdentifierPackage theIdentifierPackage = (IdentifierPackage) EPackage.Registry.INSTANCE
                .getEPackage(IdentifierPackage.eNS_URI);
        final MetricSpecPackage theMetricSpecPackage = (MetricSpecPackage) EPackage.Registry.INSTANCE
                .getEPackage(MetricSpecPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.monitorRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.monitorEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.measurementSpecificationEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        this.temporalCharacterizationEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        this.intervallEClass.getESuperTypes().add(this.getTemporalCharacterization());
        this.delayedIntervallEClass.getESuperTypes().add(this.getIntervall());
        this.timeFrameEClass.getESuperTypes().add(this.getTemporalCharacterization());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.monitorRepositoryEClass, MonitorRepository.class, "MonitorRepository", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMonitorRepository_Monitors(), this.getMonitor(),
                this.getMonitor_MonitorRepository(), "monitors", null, 0, -1, MonitorRepository.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        this.initEClass(this.monitorEClass, Monitor.class, "Monitor", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMonitor_MeasurementSpecifications(), this.getMeasurementSpecification(),
                this.getMeasurementSpecification_Monitor(), "measurementSpecifications", null, 1, -1, Monitor.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMonitor_MeasuringPoint(), theMeasuringpointPackage.getMeasuringPoint(), null,
                "measuringPoint", null, 1, 1, Monitor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMonitor_MonitorRepository(), this.getMonitorRepository(),
                this.getMonitorRepository_Monitors(), "monitorRepository", null, 1, 1, Monitor.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.measurementSpecificationEClass, MeasurementSpecification.class,
                "MeasurementSpecification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getMeasurementSpecification_TemporalRestriction(), this.getTemporalCharacterization(),
                null, "temporalRestriction", null, 1, 1, MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMeasurementSpecification_StatisticalCharacterization(),
                this.getStatisticalCharacterizationEnum(), "statisticalCharacterization", "None", 1, 1,
                MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMeasurementSpecification_MetricDescription(),
                theMetricSpecPackage.getMetricDescription(), null, "metricDescription", null, 1, 1,
                MeasurementSpecification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getMeasurementSpecification_Monitor(), this.getMonitor(),
                this.getMonitor_MeasurementSpecifications(), "monitor", null, 1, 1, MeasurementSpecification.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getMeasurementSpecification_Name(), this.ecorePackage.getEString(), "name", "", 0, 1,
                MeasurementSpecification.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, IS_DERIVED, IS_ORDERED);

        this.initEClass(this.temporalCharacterizationEClass, TemporalCharacterization.class,
                "TemporalCharacterization", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.intervallEClass, Intervall.class, "Intervall", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getIntervall_Intervall(), this.ecorePackage.getEDouble(), "intervall", "0.0", 1, 1,
                Intervall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.delayedIntervallEClass, DelayedIntervall.class, "DelayedIntervall", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getDelayedIntervall_Delay(), this.ecorePackage.getEDouble(), "delay", "0.0", 1, 1,
                DelayedIntervall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.timeFrameEClass, TimeFrame.class, "TimeFrame", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getTimeFrame_Start(), this.ecorePackage.getEDouble(), "start", "0.0", 1, 1,
                TimeFrame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getTimeFrame_Stop(), this.ecorePackage.getEDouble(), "stop", "0.0", 1, 1,
                TimeFrame.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Initialize enums and add enum literals
        this.initEEnum(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.class,
                "StatisticalCharacterizationEnum");
        this.addEEnumLiteral(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.NONE);
        this.addEEnumLiteral(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.MEDIAN);
        this.addEEnumLiteral(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.ARITHMETIC_MEAN);
        this.addEEnumLiteral(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.GEOMETRIC_MEAN);
        this.addEEnumLiteral(this.statisticalCharacterizationEnumEEnum, StatisticalCharacterizationEnum.HARMONIC_MEAN);

        // Create resource
        this.createResource(eNS_URI);

        // Create annotations
        // http://www.eclipse.org/OCL/Import
        this.createImportAnnotations();
        // http://www.eclipse.org/emf/2002/Ecore
        this.createEcoreAnnotations();
        // http://www.eclipse.org/emf/2002/Ecore/OCL
        this.createOCLAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createImportAnnotations() {
        final String source = "http://www.eclipse.org/OCL/Import";
        this.addAnnotation(this, source, new String[] { "ecore", "http://www.eclipse.org/emf/2002/Ecore", "edp2",
                "../../../plugin/org.palladiosimulator.edp2/model/EDP2.ecore#//measuringpoint", "entity",
                "../../../plugin/de.uka.ipd.sdq.pcm/model/pcm.ecore#//core/entity", "identifier",
                "../../../plugin/de.uka.ipd.sdq.identifier/model/identifier.ecore#/", "metricspec",
        "../../../plugin/org.palladiosimulator.metricspec/model/metricspec.ecore#/" });
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createEcoreAnnotations() {
        final String source = "http://www.eclipse.org/emf/2002/Ecore";
        this.addAnnotation(this, source, new String[] { "invocationDelegates",
                "http://www.eclipse.org/emf/2002/Ecore/OCL", "settingDelegates",
                "http://www.eclipse.org/emf/2002/Ecore/OCL", "validationDelegates",
        "http://www.eclipse.org/emf/2002/Ecore/OCL" });
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore/OCL</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createOCLAnnotations() {
        final String source = "http://www.eclipse.org/emf/2002/Ecore/OCL";
        this.addAnnotation(
                this.getMeasurementSpecification_Name(),
                source,
                new String[] {
                    "derivation",
                "self.monitor.entityName + \': \' + self.statisticalCharacterization.toString() + \' of \' + self.temporalRestriction.oclAsType(ecore::EObject).eClass().name" });
    }

} // MonitorrepositoryPackageImpl
