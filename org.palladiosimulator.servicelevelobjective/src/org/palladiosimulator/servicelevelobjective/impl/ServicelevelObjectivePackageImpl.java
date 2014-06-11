/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import javax.measure.Measure;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.palladiosimulator.servicelevelobjective.HardThreshold;
import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.NamedElement;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ServicelevelObjectivePackageImpl extends EPackageImpl implements ServicelevelObjectivePackage {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass serviceLevelObjectiveRepositoryEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass serviceLevelObjectiveEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass thresholdEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass hardThresholdEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass linearFuzzyThresholdEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EClass namedElementEClass = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType jsMeasureEDataType = null;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private EDataType jsQuantityEDataType = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
     * package URI value.
     * <p>Note: the correct way to create the package is via the static
     * factory method {@link #init init()}, which also performs
     * initialization of the package, or returns the registered package,
     * if one already exists.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ServicelevelObjectivePackageImpl() {
        super(eNS_URI, ServicelevelObjectiveFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link ServicelevelObjectivePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ServicelevelObjectivePackage init() {
        if (isInited) return (ServicelevelObjectivePackage)EPackage.Registry.INSTANCE.getEPackage(ServicelevelObjectivePackage.eNS_URI);

        // Obtain or create and register package
        ServicelevelObjectivePackageImpl theServicelevelObjectivePackage = (ServicelevelObjectivePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ServicelevelObjectivePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ServicelevelObjectivePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        IdentifierPackage.eINSTANCE.eClass();

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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getServiceLevelObjectiveRepository() {
        return serviceLevelObjectiveRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceLevelObjectiveRepository_Servicelevelobjectives() {
        return (EReference)serviceLevelObjectiveRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getServiceLevelObjective() {
        return serviceLevelObjectiveEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getServiceLevelObjective_Description() {
        return (EAttribute)serviceLevelObjectiveEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceLevelObjective_LowerThreshold() {
        return (EReference)serviceLevelObjectiveEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EReference getServiceLevelObjective_UpperThreshold() {
        return (EReference)serviceLevelObjectiveEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getThreshold() {
        return thresholdEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getThreshold_ThresholdLimit() {
        return (EAttribute)thresholdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getHardThreshold() {
        return hardThresholdEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getLinearFuzzyThreshold() {
        return linearFuzzyThresholdEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getLinearFuzzyThreshold_SoftLimit() {
        return (EAttribute)linearFuzzyThresholdEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EClass getNamedElement() {
        return namedElementEClass;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EAttribute getNamedElement_Name() {
        return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getJSMeasure() {
        return jsMeasureEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EDataType getJSQuantity() {
        return jsQuantityEDataType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ServicelevelObjectiveFactory getServicelevelObjectiveFactory() {
        return (ServicelevelObjectiveFactory)getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        serviceLevelObjectiveRepositoryEClass = createEClass(SERVICE_LEVEL_OBJECTIVE_REPOSITORY);
        createEReference(serviceLevelObjectiveRepositoryEClass, SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES);

        serviceLevelObjectiveEClass = createEClass(SERVICE_LEVEL_OBJECTIVE);
        createEAttribute(serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__DESCRIPTION);
        createEReference(serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD);
        createEReference(serviceLevelObjectiveEClass, SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD);

        thresholdEClass = createEClass(THRESHOLD);
        createEAttribute(thresholdEClass, THRESHOLD__THRESHOLD_LIMIT);

        hardThresholdEClass = createEClass(HARD_THRESHOLD);

        linearFuzzyThresholdEClass = createEClass(LINEAR_FUZZY_THRESHOLD);
        createEAttribute(linearFuzzyThresholdEClass, LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT);

        namedElementEClass = createEClass(NAMED_ELEMENT);
        createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

        // Create data types
        jsMeasureEDataType = createEDataType(JS_MEASURE);
        jsQuantityEDataType = createEDataType(JS_QUANTITY);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        IdentifierPackage theIdentifierPackage = (IdentifierPackage)EPackage.Registry.INSTANCE.getEPackage(IdentifierPackage.eNS_URI);

        // Create type parameters
        addETypeParameter(jsMeasureEDataType, "V");
        ETypeParameter jsMeasureEDataType_Q = addETypeParameter(jsMeasureEDataType, "Q");

        // Set bounds for type parameters
        EGenericType g1 = createEGenericType(this.getJSQuantity());
        jsMeasureEDataType_Q.getEBounds().add(g1);

        // Add supertypes to classes
        serviceLevelObjectiveEClass.getESuperTypes().add(this.getNamedElement());
        thresholdEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());
        hardThresholdEClass.getESuperTypes().add(this.getThreshold());
        linearFuzzyThresholdEClass.getESuperTypes().add(this.getThreshold());
        namedElementEClass.getESuperTypes().add(theIdentifierPackage.getIdentifier());

        // Initialize classes, features, and operations; add parameters
        initEClass(serviceLevelObjectiveRepositoryEClass, ServiceLevelObjectiveRepository.class, "ServiceLevelObjectiveRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getServiceLevelObjectiveRepository_Servicelevelobjectives(), this.getServiceLevelObjective(), null, "servicelevelobjectives", null, 0, -1, ServiceLevelObjectiveRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(serviceLevelObjectiveEClass, ServiceLevelObjective.class, "ServiceLevelObjective", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getServiceLevelObjective_Description(), ecorePackage.getEString(), "description", null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getServiceLevelObjective_LowerThreshold(), this.getThreshold(), null, "lowerThreshold", null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getServiceLevelObjective_UpperThreshold(), this.getThreshold(), null, "upperThreshold", null, 0, 1, ServiceLevelObjective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(thresholdEClass, Threshold.class, "Threshold", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getThreshold_ThresholdLimit(), this.getJSMeasure(), "thresholdLimit", null, 0, 1, Threshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(hardThresholdEClass, HardThreshold.class, "HardThreshold", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(linearFuzzyThresholdEClass, LinearFuzzyThreshold.class, "LinearFuzzyThreshold", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLinearFuzzyThreshold_SoftLimit(), this.getJSMeasure(), "softLimit", null, 0, 1, LinearFuzzyThreshold.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Initialize data types
        initEDataType(jsMeasureEDataType, Measure.class, "JSMeasure", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
        initEDataType(jsQuantityEDataType, javax.measure.quantity.Quantity.class, "JSQuantity", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

        // Create resource
        createResource(eNS_URI);
    }

} //ServicelevelObjectivePackageImpl
