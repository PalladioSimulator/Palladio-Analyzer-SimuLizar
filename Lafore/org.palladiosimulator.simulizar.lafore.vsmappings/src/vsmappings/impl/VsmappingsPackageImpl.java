/**
 */
package vsmappings.impl;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import strategies.StrategiesPackage;

import violations.ViolationsPackage;

import vsmappings.VSMapping;
import vsmappings.VSMappingRepository;
import vsmappings.VsmappingsFactory;
import vsmappings.VsmappingsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VsmappingsPackageImpl extends EPackageImpl implements VsmappingsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vsMappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vsMappingRepositoryEClass = null;

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
	 * @see vsmappings.VsmappingsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private VsmappingsPackageImpl() {
		super(eNS_URI, VsmappingsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link VsmappingsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static VsmappingsPackage init() {
		if (isInited) return (VsmappingsPackage)EPackage.Registry.INSTANCE.getEPackage(VsmappingsPackage.eNS_URI);

		// Obtain or create and register package
		VsmappingsPackageImpl theVsmappingsPackage = (VsmappingsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof VsmappingsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new VsmappingsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		StrategiesPackage.eINSTANCE.eClass();
		ViolationsPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theVsmappingsPackage.createPackageContents();

		// Initialize created meta-data
		theVsmappingsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theVsmappingsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(VsmappingsPackage.eNS_URI, theVsmappingsPackage);
		return theVsmappingsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVSMapping() {
		return vsMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVSMapping_Violation() {
		return (EReference)vsMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVSMapping_Strategies() {
		return (EReference)vsMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getVSMapping_StrategyPriority() {
		return (EAttribute)vsMappingEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getVSMappingRepository() {
		return vsMappingRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getVSMappingRepository_Vsmappings() {
		return (EReference)vsMappingRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VsmappingsFactory getVsmappingsFactory() {
		return (VsmappingsFactory)getEFactoryInstance();
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
		vsMappingEClass = createEClass(VS_MAPPING);
		createEReference(vsMappingEClass, VS_MAPPING__VIOLATION);
		createEReference(vsMappingEClass, VS_MAPPING__STRATEGIES);
		createEAttribute(vsMappingEClass, VS_MAPPING__STRATEGY_PRIORITY);

		vsMappingRepositoryEClass = createEClass(VS_MAPPING_REPOSITORY);
		createEReference(vsMappingRepositoryEClass, VS_MAPPING_REPOSITORY__VSMAPPINGS);
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
		vsMappingEClass.getESuperTypes().add(theEntityPackage.getEntity());
		vsMappingRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(vsMappingEClass, VSMapping.class, "VSMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVSMapping_Violation(), theViolationsPackage.getViolationType(), null, "violation", null, 1, 1, VSMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVSMapping_Strategies(), theStrategiesPackage.getStrategyType(), null, "strategies", null, 1, 1, VSMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVSMapping_StrategyPriority(), ecorePackage.getEInt(), "strategyPriority", null, 0, 1, VSMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(vsMappingRepositoryEClass, VSMappingRepository.class, "VSMappingRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getVSMappingRepository_Vsmappings(), this.getVSMapping(), null, "vsmappings", null, 0, -1, VSMappingRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //VsmappingsPackageImpl
