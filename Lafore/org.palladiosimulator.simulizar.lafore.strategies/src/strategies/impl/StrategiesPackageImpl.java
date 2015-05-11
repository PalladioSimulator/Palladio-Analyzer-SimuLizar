/**
 */
package strategies.impl;

import de.uka.ipd.sdq.pcm.PcmPackage;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;

import de.uka.ipd.sdq.pcm.seff.SeffPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import strategies.ReconfigurationAction;
import strategies.RuntimeStrategiesModel;
import strategies.StrategiesFactory;
import strategies.StrategiesPackage;
import strategies.StrategiesRepository;
import strategies.Strategy;
import strategies.StrategyType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StrategiesPackageImpl extends EPackageImpl implements StrategiesPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass runtimeStrategiesModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass strategyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass strategyTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reconfigurationActionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass strategiesRepositoryEClass = null;

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
	 * @see strategies.StrategiesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private StrategiesPackageImpl() {
		super(eNS_URI, StrategiesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link StrategiesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static StrategiesPackage init() {
		if (isInited) return (StrategiesPackage)EPackage.Registry.INSTANCE.getEPackage(StrategiesPackage.eNS_URI);

		// Obtain or create and register package
		StrategiesPackageImpl theStrategiesPackage = (StrategiesPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StrategiesPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StrategiesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PcmPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theStrategiesPackage.createPackageContents();

		// Initialize created meta-data
		theStrategiesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theStrategiesPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(StrategiesPackage.eNS_URI, theStrategiesPackage);
		return theStrategiesPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRuntimeStrategiesModel() {
		return runtimeStrategiesModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRuntimeStrategiesModel_Strategies() {
		return (EReference)runtimeStrategiesModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStrategy() {
		return strategyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStrategy_StrategyType() {
		return (EReference)strategyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStrategyType() {
		return strategyTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getStrategyType_Behavior() {
		return (EReference)strategyTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getReconfigurationAction() {
		return reconfigurationActionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getReconfigurationAction_CodeRef() {
		return (EAttribute)reconfigurationActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStrategiesRepository() {
		return strategiesRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrategiesFactory getStrategiesFactory() {
		return (StrategiesFactory)getEFactoryInstance();
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
		runtimeStrategiesModelEClass = createEClass(RUNTIME_STRATEGIES_MODEL);
		createEReference(runtimeStrategiesModelEClass, RUNTIME_STRATEGIES_MODEL__STRATEGIES);

		strategyEClass = createEClass(STRATEGY);
		createEReference(strategyEClass, STRATEGY__STRATEGY_TYPE);

		strategyTypeEClass = createEClass(STRATEGY_TYPE);
		createEReference(strategyTypeEClass, STRATEGY_TYPE__BEHAVIOR);

		reconfigurationActionEClass = createEClass(RECONFIGURATION_ACTION);
		createEAttribute(reconfigurationActionEClass, RECONFIGURATION_ACTION__CODE_REF);

		strategiesRepositoryEClass = createEClass(STRATEGIES_REPOSITORY);
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
		SeffPackage theSeffPackage = (SeffPackage)EPackage.Registry.INSTANCE.getEPackage(SeffPackage.eNS_URI);
		EntityPackage theEntityPackage = (EntityPackage)EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
		RepositoryPackage theRepositoryPackage = (RepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(RepositoryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		strategyTypeEClass.getESuperTypes().add(theSeffPackage.getResourceDemandingSEFF());
		strategyTypeEClass.getESuperTypes().add(theEntityPackage.getEntity());
		reconfigurationActionEClass.getESuperTypes().add(theSeffPackage.getInternalAction());
		reconfigurationActionEClass.getESuperTypes().add(theEntityPackage.getEntity());
		strategiesRepositoryEClass.getESuperTypes().add(theRepositoryPackage.getBasicComponent());
		strategiesRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(runtimeStrategiesModelEClass, RuntimeStrategiesModel.class, "RuntimeStrategiesModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRuntimeStrategiesModel_Strategies(), this.getStrategy(), null, "strategies", null, 0, -1, RuntimeStrategiesModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(strategyEClass, Strategy.class, "Strategy", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStrategy_StrategyType(), this.getStrategyType(), null, "strategyType", null, 1, 1, Strategy.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(strategyTypeEClass, StrategyType.class, "StrategyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getStrategyType_Behavior(), this.getReconfigurationAction(), null, "behavior", null, 0, -1, StrategyType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reconfigurationActionEClass, ReconfigurationAction.class, "ReconfigurationAction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReconfigurationAction_CodeRef(), ecorePackage.getEString(), "codeRef", null, 0, 1, ReconfigurationAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(strategiesRepositoryEClass, StrategiesRepository.class, "StrategiesRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //StrategiesPackageImpl
