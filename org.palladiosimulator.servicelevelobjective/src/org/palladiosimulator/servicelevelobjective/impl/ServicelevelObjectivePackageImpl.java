/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentDataPackage;
import org.palladiosimulator.edp2.models.Repository.RepositoryPackage;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringpointPackage;
import org.palladiosimulator.metricspec.MetricSpecPackage;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.NamedElement;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class ServicelevelObjectivePackageImpl extends EPackageImpl implements ServicelevelObjectivePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass serviceLevelObjectiveRepositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass serviceLevelObjectiveEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass thresholdEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass hardThresholdEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass linearFuzzyThresholdEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass namedElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType jsMeasureEDataType = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EDataType jsQuantityEDataType = null;

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
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ServicelevelObjectivePackageImpl() {
        super(eNS_URI, ServicelevelObjectiveFactory.eINSTANCE);
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
     * This method is used to initialize {@link ServicelevelObjectivePackage#eINSTANCE} when that
     * field is accessed. Clients should not invoke it directly. Instead, they should simply access
     * that field to obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ServicelevelObjectivePackage init() {
        if (isInited) {
            return (ServicelevelObjectivePackage) EPackage.Registry.INSTANCE
                    .getEPackage(ServicelevelObjectivePackage.eNS_URI);
        }

        // Obtain or create and register package
        final ServicelevelObjectivePackageImpl theServicelevelObjectivePackage = (ServicelevelObjectivePackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof ServicelevelObjectivePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new ServicelevelObjectivePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        ExperimentDataPackage.eINSTANCE.eClass();
        RepositoryPackage.eINSTANCE.eClass();
        MeasuringpointPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        theServicelevelObjectivePackage.createPackageContents();

        // Initialize created meta-data
        theServicelevelObjectivePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theServicelevelObjectivePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ServicelevelObjectivePackage.eNS_URI, theServicelevelObjectivePackage);
        return theServicelevelObjectivePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getServiceLevelObjectiveRepository() {
        return this.serviceLevelObjectiveRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getServiceLevelObjectiveRepository_Servicelevelobjectives() {
        return (EReference) this.serviceLevelObjectiveRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getServiceLevelObjective() {
        return this.serviceLevelObjectiveEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getServiceLevelObjective_Description() {
        return (EAttribute) this.serviceLevelObjectiveEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getServiceLevelObjective_LowerThreshold() {
        return (EReference) this.serviceLevelObjectiveEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getServiceLevelObjective_UpperThreshold() {
        return (EReference) this.serviceLevelObjectiveEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getServiceLevelObjective_MeasuringPoint() {
        return (EReference) this.serviceLevelObjectiveEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EReference getServiceLevelObjective_MetricDescription() {
        return (EReference) this.serviceLevelObjectiveEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getThreshold() {
        return this.thresholdEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getThreshold_ThresholdLimit() {
        return (EAttribute) this.thresholdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getHardThreshold() {
        return this.hardThresholdEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getLinearFuzzyThreshold() {
        return this.linearFuzzyThresholdEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getLinearFuzzyThreshold_SoftLimit() {
        return (EAttribute) this.linearFuzzyThresholdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EClass getNamedElement() {
        return this.namedElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EAttribute getNamedElement_Name() {
        return (EAttribute) this.namedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getJSMeasure() {
        return this.jsMeasureEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EDataType getJSQuantity() {
        return this.jsQuantityEDataType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ServicelevelObjectiveFactory getServicelevelObjectiveFactory() {
        return (ServicelevelObjectiveFactory) this.getEFactoryInstance();
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
        this.serviceLevelObjectiveRepositoryEClass = this.createEClass(SERVICE_LEVEL_OBJECTIVE_REPOSITORY);
        this.createEReference(this.serviceLevelObjectiveRepositoryEClass,
                SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES);

        this.serviceLevelObjectiveEClass = this.createEClass(SERVICE_LEVEL_OBJECTIVE);
        this.createEAttribute(this.serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__DESCRIPTION);
        this.createEReference(this.serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD);
        this.createEReference(this.serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD);
        this.createEReference(this.serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__MEASURING_POINT);
        this.createEReference(this.serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__METRIC_DESCRIPTION);

        this.thresholdEClass = this.createEClass(THRESHOLD);
        this.createEAttribute(this.thresholdEClass, THRESHOLD__THRESHOLD_LIMIT);

        this.hardThresholdEClass = this.createEClass(HARD_THRESHOLD);

        this.linearFuzzyThresholdEClass = this.createEClass(LINEAR_FUZZY_THRESHOLD);
        this.createEAttribute(this.linearFuzzyThresholdEClass, LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT);

        this.namedElementEClass = this.createEClass(NAMED_ELEMENT);
        this.createEAttribute(this.namedElementEClass, NAMED_ELEMENT__NAME);

        // Create data types
        this.jsMeasureEDataType = this.createEDataType(JS_MEASURE);
        this.jsQuantityEDataType = this.createEDataType(JS_QUANTITY);
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
        final MeasuringpointPackage theMeasuringpointPackage = (MeasuringpointPackage) EPackage.Registry.INSTANCE
                .getEPackage(MeasuringpointPackage.eNS_URI);
        final MetricSpecPackage theMetricSpecPackage = (MetricSpecPackage) EPackage.Registry.INSTANCE
                .getEPackage(MetricSpecPackage.eNS_URI);
        final IdentifierPackage theIdentifierPackage = (IdentifierPackage) EPackage.Registry.INSTANCE
                .getEPackage(IdentifierPackage.eNS_URI);

        // Create type parameters
        this.addETypeParameter(this.jsMeasureEDataType, "V");
        final ETypeParameter jsMeasureEDataType_Q = this.addETypeParameter(this.jsMeasureEDataType, "Q");

        // Set bounds for type parameters
        final EGenericType g1 = this.createEGenericType(this.getJSQuantity());
        jsMeasureEDataType_Q.getEBounds().add(g1);

        // Add supertypes to classes
        this.serviceLevelObjectiveEClass.getESuperTypes().add(this.getNamedElement());
        this.thresholdEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        this.hardThresholdEClass.getESuperTypes().add(this.getThreshold());
        this.linearFuzzyThresholdEClass.getESuperTypes().add(this.getThreshold());
        this.namedElementEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.serviceLevelObjectiveRepositoryEClass, ServiceLevelObjectiveRepository.class,
                "ServiceLevelObjectiveRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getServiceLevelObjectiveRepository_Servicelevelobjectives(),
                this.getServiceLevelObjective(), null, "servicelevelobjectives", null, 0, -1,
                ServiceLevelObjectiveRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.serviceLevelObjectiveEClass, ServiceLevelObjective.class, "ServiceLevelObjective",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getServiceLevelObjective_Description(), this.ecorePackage.getEString(), "description",
                null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getServiceLevelObjective_LowerThreshold(), this.getThreshold(), null,
                "lowerThreshold", null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getServiceLevelObjective_UpperThreshold(), this.getThreshold(), null,
                "upperThreshold", null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getServiceLevelObjective_MeasuringPoint(),
                theMeasuringpointPackage.getMeasuringPoint(), null, "measuringPoint", null, 1, 1,
                ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getServiceLevelObjective_MetricDescription(),
                theMetricSpecPackage.getMetricDescription(), null, "metricDescription", null, 1, 1,
                ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
                IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.thresholdEClass, Threshold.class, "Threshold", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getThreshold_ThresholdLimit(), this.getJSMeasure(), "thresholdLimit", null, 0, 1,
                Threshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.hardThresholdEClass, HardThreshold.class, "HardThreshold", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.linearFuzzyThresholdEClass, LinearFuzzyThreshold.class, "LinearFuzzyThreshold",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getLinearFuzzyThreshold_SoftLimit(), this.getJSMeasure(), "softLimit", null, 0, 1,
                LinearFuzzyThreshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getNamedElement_Name(), this.ecorePackage.getEString(), "name", null, 0, 1,
                NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
                !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        this.initEDataType(this.jsMeasureEDataType, Measure.class, "JSMeasure", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);
        this.initEDataType(this.jsQuantityEDataType, Quantity.class, "JSQuantity", IS_SERIALIZABLE,
                !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        this.createResource(eNS_URI);
    }

} // ServicelevelObjectivePackageImpl
