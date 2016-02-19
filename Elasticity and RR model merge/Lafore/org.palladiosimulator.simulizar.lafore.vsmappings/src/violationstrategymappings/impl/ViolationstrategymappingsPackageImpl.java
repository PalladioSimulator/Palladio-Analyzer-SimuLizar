/**
 */
package violationstrategymappings.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.palladiosimulator.pcm.core.entity.EntityPackage;
import strategies.StrategiesPackage;

import violations.ViolationsPackage;

import violationstrategymappings.ViolationStrategyMapping;
import violationstrategymappings.ViolationStrategyMappingRepository;
import violationstrategymappings.ViolationstrategymappingsFactory;
import violationstrategymappings.ViolationstrategymappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViolationstrategymappingsPackageImpl extends EPackageImpl implements ViolationstrategymappingsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass violationStrategyMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass violationStrategyMappingRepositoryEClass = null;

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
	 * @see violationstrategymappings.ViolationstrategymappingsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ViolationstrategymappingsPackageImpl() {
		super(eNS_URI, ViolationstrategymappingsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ViolationstrategymappingsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ViolationstrategymappingsPackage init() {
		if (isInited) return (ViolationstrategymappingsPackage)EPackage.Registry.INSTANCE.getEPackage(ViolationstrategymappingsPackage.eNS_URI);

		// Obtain or create and register package
		ViolationstrategymappingsPackageImpl theViolationstrategymappingsPackage = (ViolationstrategymappingsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ViolationstrategymappingsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ViolationstrategymappingsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		StrategiesPackage.eINSTANCE.eClass();
		ViolationsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theViolationstrategymappingsPackage.createPackageContents();

		// Initialize created meta-data
		theViolationstrategymappingsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theViolationstrategymappingsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ViolationstrategymappingsPackage.eNS_URI, theViolationstrategymappingsPackage);
		return theViolationstrategymappingsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViolationStrategyMapping() {
		return violationStrategyMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolationStrategyMapping_Violation() {
		return (EReference)violationStrategyMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolationStrategyMapping_Strategy() {
		return (EReference)violationStrategyMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getViolationStrategyMapping_StrategyPriority() {
		return (EAttribute)violationStrategyMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getViolationStrategyMappingRepository() {
		return violationStrategyMappingRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getViolationStrategyMappingRepository_Vsmappings() {
		return (EReference)violationStrategyMappingRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationstrategymappingsFactory getViolationstrategymappingsFactory() {
		return (ViolationstrategymappingsFactory)getEFactoryInstance();
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
		violationStrategyMappingEClass = createEClass(VIOLATION_STRATEGY_MAPPING);
		createEReference(violationStrategyMappingEClass, VIOLATION_STRATEGY_MAPPING__VIOLATION);
		createEReference(violationStrategyMappingEClass, VIOLATION_STRATEGY_MAPPING__STRATEGY);
		createEAttribute(violationStrategyMappingEClass, VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY);

		violationStrategyMappingRepositoryEClass = createEClass(VIOLATION_STRATEGY_MAPPING_REPOSITORY);
		createEReference(violationStrategyMappingRepositoryEClass, VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS);
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
		EntityPackage theEntityPackage = (EntityPackage)EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
		ViolationsPackage theViolationsPackage = (ViolationsPackage)EPackage.Registry.INSTANCE.getEPackage(ViolationsPackage.eNS_URI);
		StrategiesPackage theStrategiesPackage = (StrategiesPackage)EPackage.Registry.INSTANCE.getEPackage(StrategiesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		violationStrategyMappingEClass.getESuperTypes().add(theEntityPackage.getEntity());
		violationStrategyMappingRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(violationStrategyMappingEClass, ViolationStrategyMapping.class, "ViolationStrategyMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViolationStrategyMapping_Violation(), theViolationsPackage.getViolationType(), null, "violation", null, 1, 1, ViolationStrategyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getViolationStrategyMapping_Strategy(), theStrategiesPackage.getStrategyType(), null, "strategy", null, 1, 1, ViolationStrategyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getViolationStrategyMapping_StrategyPriority(), ecorePackage.getEInt(), "strategyPriority", null, 0, 1, ViolationStrategyMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(violationStrategyMappingRepositoryEClass, ViolationStrategyMappingRepository.class, "ViolationStrategyMappingRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getViolationStrategyMappingRepository_Vsmappings(), this.getViolationStrategyMapping(), null, "vsmappings", null, 0, -1, ViolationStrategyMappingRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //ViolationstrategymappingsPackageImpl
