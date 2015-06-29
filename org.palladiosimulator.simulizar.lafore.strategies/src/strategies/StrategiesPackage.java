/**
 */
package strategies;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see strategies.StrategiesFactory
 * @model kind="package"
 * @generated
 */
public interface StrategiesPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "strategies";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulizar.palladiosimulator.org/lafore/strategies/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "strategies";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StrategiesPackage eINSTANCE = strategies.impl.StrategiesPackageImpl.init();

	/**
	 * The meta object id for the '{@link strategies.impl.RuntimeStrategiesModelImpl <em>Runtime Strategies Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see strategies.impl.RuntimeStrategiesModelImpl
	 * @see strategies.impl.StrategiesPackageImpl#getRuntimeStrategiesModel()
	 * @generated
	 */
	int RUNTIME_STRATEGIES_MODEL = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_STRATEGIES_MODEL__ID = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_STRATEGIES_MODEL__ENTITY_NAME = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Strategies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_STRATEGIES_MODEL__STRATEGIES = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Runtime Strategies Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_STRATEGIES_MODEL_FEATURE_COUNT = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link strategies.impl.StrategyImpl <em>Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see strategies.impl.StrategyImpl
	 * @see strategies.impl.StrategiesPackageImpl#getStrategy()
	 * @generated
	 */
	int STRATEGY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__ID = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__ENTITY_NAME = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Strategy Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__STRATEGY_TYPE = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_FEATURE_COUNT = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link strategies.impl.StrategyTypeImpl <em>Strategy Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see strategies.impl.StrategyTypeImpl
	 * @see strategies.impl.StrategiesPackageImpl#getStrategyType()
	 * @generated
	 */
	int STRATEGY_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__ID = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__ID;

	/**
	 * The feature id for the '<em><b>Seff Type ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__SEFF_TYPE_ID = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__SEFF_TYPE_ID;

	/**
	 * The feature id for the '<em><b>Described Service SEFF</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__DESCRIBED_SERVICE_SEFF = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__DESCRIBED_SERVICE_SEFF;

	/**
	 * The feature id for the '<em><b>Basic Component Service Effect Specification</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__BASIC_COMPONENT_SERVICE_EFFECT_SPECIFICATION = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__BASIC_COMPONENT_SERVICE_EFFECT_SPECIFICATION;

	/**
	 * The feature id for the '<em><b>Abstract Loop Action Resource Demanding Behaviour</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__ABSTRACT_LOOP_ACTION_RESOURCE_DEMANDING_BEHAVIOUR = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__ABSTRACT_LOOP_ACTION_RESOURCE_DEMANDING_BEHAVIOUR;

	/**
	 * The feature id for the '<em><b>Abstract Branch Transition Resource Demanding Behaviour</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__ABSTRACT_BRANCH_TRANSITION_RESOURCE_DEMANDING_BEHAVIOUR = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__ABSTRACT_BRANCH_TRANSITION_RESOURCE_DEMANDING_BEHAVIOUR;

	/**
	 * The feature id for the '<em><b>Steps Behaviour</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__STEPS_BEHAVIOUR = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__STEPS_BEHAVIOUR;

	/**
	 * The feature id for the '<em><b>Resource Demanding Internal Behaviours</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__RESOURCE_DEMANDING_INTERNAL_BEHAVIOURS = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF__RESOURCE_DEMANDING_INTERNAL_BEHAVIOURS;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__ENTITY_NAME = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE__BEHAVIOR = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Strategy Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_TYPE_FEATURE_COUNT = org.palladiosimulator.pcm.seff.SeffPackage.RESOURCE_DEMANDING_SEFF_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link strategies.impl.ReconfigurationActionImpl <em>Reconfiguration Action</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see strategies.impl.ReconfigurationActionImpl
	 * @see strategies.impl.StrategiesPackageImpl#getReconfigurationAction()
	 * @generated
	 */
	int RECONFIGURATION_ACTION = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__ID = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__ENTITY_NAME = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Predecessor Abstract Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__PREDECESSOR_ABSTRACT_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__PREDECESSOR_ABSTRACT_ACTION;

	/**
	 * The feature id for the '<em><b>Successor Abstract Action</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__SUCCESSOR_ABSTRACT_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__SUCCESSOR_ABSTRACT_ACTION;

	/**
	 * The feature id for the '<em><b>Resource Demanding Behaviour Abstract Action</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__RESOURCE_DEMANDING_BEHAVIOUR_ABSTRACT_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__RESOURCE_DEMANDING_BEHAVIOUR_ABSTRACT_ACTION;

	/**
	 * The feature id for the '<em><b>Resource Demand Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__RESOURCE_DEMAND_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__RESOURCE_DEMAND_ACTION;

	/**
	 * The feature id for the '<em><b>Infrastructure Call Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__INFRASTRUCTURE_CALL_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__INFRASTRUCTURE_CALL_ACTION;

	/**
	 * The feature id for the '<em><b>Resource Call Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__RESOURCE_CALL_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__RESOURCE_CALL_ACTION;

	/**
	 * The feature id for the '<em><b>Internal Failure Occurrence Descriptions Internal Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__INTERNAL_FAILURE_OCCURRENCE_DESCRIPTIONS_INTERNAL_ACTION = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION__INTERNAL_FAILURE_OCCURRENCE_DESCRIPTIONS_INTERNAL_ACTION;

	/**
	 * The feature id for the '<em><b>Code Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION__CODE_REF = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reconfiguration Action</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_ACTION_FEATURE_COUNT = org.palladiosimulator.pcm.seff.SeffPackage.INTERNAL_ACTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link strategies.impl.StrategiesRepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see strategies.impl.StrategiesRepositoryImpl
	 * @see strategies.impl.StrategiesPackageImpl#getStrategiesRepository()
	 * @generated
	 */
	int STRATEGIES_REPOSITORY = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__ID = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__ENTITY_NAME = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Provided Roles Interface Providing Entity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__PROVIDED_ROLES_INTERFACE_PROVIDING_ENTITY;

	/**
	 * The feature id for the '<em><b>Resource Required Roles Resource Interface Requiring Entity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__RESOURCE_REQUIRED_ROLES_RESOURCE_INTERFACE_REQUIRING_ENTITY;

	/**
	 * The feature id for the '<em><b>Required Roles Interface Requiring Entity</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__REQUIRED_ROLES_INTERFACE_REQUIRING_ENTITY;

	/**
	 * The feature id for the '<em><b>Repository Repository Component</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__REPOSITORY_REPOSITORY_COMPONENT = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__REPOSITORY_REPOSITORY_COMPONENT;

	/**
	 * The feature id for the '<em><b>Parent Complete Component Types</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__PARENT_COMPLETE_COMPONENT_TYPES = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__PARENT_COMPLETE_COMPONENT_TYPES;

	/**
	 * The feature id for the '<em><b>Component Parameter Usage Implementation Component Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__COMPONENT_PARAMETER_USAGE_IMPLEMENTATION_COMPONENT_TYPE = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__COMPONENT_PARAMETER_USAGE_IMPLEMENTATION_COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__COMPONENT_TYPE = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__COMPONENT_TYPE;

	/**
	 * The feature id for the '<em><b>Service Effect Specifications Basic Component</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__SERVICE_EFFECT_SPECIFICATIONS_BASIC_COMPONENT = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__SERVICE_EFFECT_SPECIFICATIONS_BASIC_COMPONENT;

	/**
	 * The feature id for the '<em><b>Passive Resource Basic Component</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY__PASSIVE_RESOURCE_BASIC_COMPONENT = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT__PASSIVE_RESOURCE_BASIC_COMPONENT;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGIES_REPOSITORY_FEATURE_COUNT = org.palladiosimulator.pcm.repository.RepositoryPackage.BASIC_COMPONENT_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link strategies.RuntimeStrategiesModel <em>Runtime Strategies Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Strategies Model</em>'.
	 * @see strategies.RuntimeStrategiesModel
	 * @generated
	 */
	EClass getRuntimeStrategiesModel();

	/**
	 * Returns the meta object for the containment reference list '{@link strategies.RuntimeStrategiesModel#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Strategies</em>'.
	 * @see strategies.RuntimeStrategiesModel#getStrategies()
	 * @see #getRuntimeStrategiesModel()
	 * @generated
	 */
	EReference getRuntimeStrategiesModel_Strategies();

	/**
	 * Returns the meta object for class '{@link strategies.Strategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strategy</em>'.
	 * @see strategies.Strategy
	 * @generated
	 */
	EClass getStrategy();

	/**
	 * Returns the meta object for the reference '{@link strategies.Strategy#getStrategyType <em>Strategy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Strategy Type</em>'.
	 * @see strategies.Strategy#getStrategyType()
	 * @see #getStrategy()
	 * @generated
	 */
	EReference getStrategy_StrategyType();

	/**
	 * Returns the meta object for class '{@link strategies.StrategyType <em>Strategy Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strategy Type</em>'.
	 * @see strategies.StrategyType
	 * @generated
	 */
	EClass getStrategyType();

	/**
	 * Returns the meta object for the reference list '{@link strategies.StrategyType#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Behavior</em>'.
	 * @see strategies.StrategyType#getBehavior()
	 * @see #getStrategyType()
	 * @generated
	 */
	EReference getStrategyType_Behavior();

	/**
	 * Returns the meta object for class '{@link strategies.ReconfigurationAction <em>Reconfiguration Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfiguration Action</em>'.
	 * @see strategies.ReconfigurationAction
	 * @generated
	 */
	EClass getReconfigurationAction();

	/**
	 * Returns the meta object for the attribute '{@link strategies.ReconfigurationAction#getCodeRef <em>Code Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Code Ref</em>'.
	 * @see strategies.ReconfigurationAction#getCodeRef()
	 * @see #getReconfigurationAction()
	 * @generated
	 */
	EAttribute getReconfigurationAction_CodeRef();

	/**
	 * Returns the meta object for class '{@link strategies.StrategiesRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see strategies.StrategiesRepository
	 * @generated
	 */
	EClass getStrategiesRepository();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StrategiesFactory getStrategiesFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link strategies.impl.RuntimeStrategiesModelImpl <em>Runtime Strategies Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see strategies.impl.RuntimeStrategiesModelImpl
		 * @see strategies.impl.StrategiesPackageImpl#getRuntimeStrategiesModel()
		 * @generated
		 */
		EClass RUNTIME_STRATEGIES_MODEL = eINSTANCE.getRuntimeStrategiesModel();

		/**
		 * The meta object literal for the '<em><b>Strategies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_STRATEGIES_MODEL__STRATEGIES = eINSTANCE.getRuntimeStrategiesModel_Strategies();

		/**
		 * The meta object literal for the '{@link strategies.impl.StrategyImpl <em>Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see strategies.impl.StrategyImpl
		 * @see strategies.impl.StrategiesPackageImpl#getStrategy()
		 * @generated
		 */
		EClass STRATEGY = eINSTANCE.getStrategy();

		/**
		 * The meta object literal for the '<em><b>Strategy Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGY__STRATEGY_TYPE = eINSTANCE.getStrategy_StrategyType();

		/**
		 * The meta object literal for the '{@link strategies.impl.StrategyTypeImpl <em>Strategy Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see strategies.impl.StrategyTypeImpl
		 * @see strategies.impl.StrategiesPackageImpl#getStrategyType()
		 * @generated
		 */
		EClass STRATEGY_TYPE = eINSTANCE.getStrategyType();

		/**
		 * The meta object literal for the '<em><b>Behavior</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGY_TYPE__BEHAVIOR = eINSTANCE.getStrategyType_Behavior();

		/**
		 * The meta object literal for the '{@link strategies.impl.ReconfigurationActionImpl <em>Reconfiguration Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see strategies.impl.ReconfigurationActionImpl
		 * @see strategies.impl.StrategiesPackageImpl#getReconfigurationAction()
		 * @generated
		 */
		EClass RECONFIGURATION_ACTION = eINSTANCE.getReconfigurationAction();

		/**
		 * The meta object literal for the '<em><b>Code Ref</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECONFIGURATION_ACTION__CODE_REF = eINSTANCE.getReconfigurationAction_CodeRef();

		/**
		 * The meta object literal for the '{@link strategies.impl.StrategiesRepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see strategies.impl.StrategiesRepositoryImpl
		 * @see strategies.impl.StrategiesPackageImpl#getStrategiesRepository()
		 * @generated
		 */
		EClass STRATEGIES_REPOSITORY = eINSTANCE.getStrategiesRepository();

	}

} //StrategiesPackage
