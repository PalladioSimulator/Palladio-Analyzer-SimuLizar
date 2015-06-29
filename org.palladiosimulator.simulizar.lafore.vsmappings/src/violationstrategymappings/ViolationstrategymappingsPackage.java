/**
 */
package violationstrategymappings;

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
 * @see violationstrategymappings.ViolationstrategymappingsFactory
 * @model kind="package"
 * @generated
 */
public interface ViolationstrategymappingsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "violationstrategymappings";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulizar.palladiosimulator.org/lafore/violationstrategymappings/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "violationstrategymappings";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViolationstrategymappingsPackage eINSTANCE = violationstrategymappings.impl.ViolationstrategymappingsPackageImpl.init();

	/**
	 * The meta object id for the '{@link violationstrategymappings.impl.ViolationStrategyMappingImpl <em>Violation Strategy Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violationstrategymappings.impl.ViolationStrategyMappingImpl
	 * @see violationstrategymappings.impl.ViolationstrategymappingsPackageImpl#getViolationStrategyMapping()
	 * @generated
	 */
	int VIOLATION_STRATEGY_MAPPING = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING__ID = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING__ENTITY_NAME = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING__VIOLATION = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Strategies</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING__STRATEGIES = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Strategy Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Violation Strategy Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING_FEATURE_COUNT = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link violationstrategymappings.impl.ViolationStrategyMappingRepositoryImpl <em>Violation Strategy Mapping Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violationstrategymappings.impl.ViolationStrategyMappingRepositoryImpl
	 * @see violationstrategymappings.impl.ViolationstrategymappingsPackageImpl#getViolationStrategyMappingRepository()
	 * @generated
	 */
	int VIOLATION_STRATEGY_MAPPING_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING_REPOSITORY__ID = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING_REPOSITORY__ENTITY_NAME = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Vsmappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Violation Strategy Mapping Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_STRATEGY_MAPPING_REPOSITORY_FEATURE_COUNT = org.palladiosimulator.pcm.core.entity.EntityPackage.ENTITY_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link violationstrategymappings.ViolationStrategyMapping <em>Violation Strategy Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Violation Strategy Mapping</em>'.
	 * @see violationstrategymappings.ViolationStrategyMapping
	 * @generated
	 */
	EClass getViolationStrategyMapping();

	/**
	 * Returns the meta object for the reference '{@link violationstrategymappings.ViolationStrategyMapping#getViolation <em>Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Violation</em>'.
	 * @see violationstrategymappings.ViolationStrategyMapping#getViolation()
	 * @see #getViolationStrategyMapping()
	 * @generated
	 */
	EReference getViolationStrategyMapping_Violation();

	/**
	 * Returns the meta object for the reference '{@link violationstrategymappings.ViolationStrategyMapping#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Strategies</em>'.
	 * @see violationstrategymappings.ViolationStrategyMapping#getStrategies()
	 * @see #getViolationStrategyMapping()
	 * @generated
	 */
	EReference getViolationStrategyMapping_Strategies();

	/**
	 * Returns the meta object for the attribute '{@link violationstrategymappings.ViolationStrategyMapping#getStrategyPriority <em>Strategy Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy Priority</em>'.
	 * @see violationstrategymappings.ViolationStrategyMapping#getStrategyPriority()
	 * @see #getViolationStrategyMapping()
	 * @generated
	 */
	EAttribute getViolationStrategyMapping_StrategyPriority();

	/**
	 * Returns the meta object for class '{@link violationstrategymappings.ViolationStrategyMappingRepository <em>Violation Strategy Mapping Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Violation Strategy Mapping Repository</em>'.
	 * @see violationstrategymappings.ViolationStrategyMappingRepository
	 * @generated
	 */
	EClass getViolationStrategyMappingRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link violationstrategymappings.ViolationStrategyMappingRepository#getVsmappings <em>Vsmappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vsmappings</em>'.
	 * @see violationstrategymappings.ViolationStrategyMappingRepository#getVsmappings()
	 * @see #getViolationStrategyMappingRepository()
	 * @generated
	 */
	EReference getViolationStrategyMappingRepository_Vsmappings();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ViolationstrategymappingsFactory getViolationstrategymappingsFactory();

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
		 * The meta object literal for the '{@link violationstrategymappings.impl.ViolationStrategyMappingImpl <em>Violation Strategy Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violationstrategymappings.impl.ViolationStrategyMappingImpl
		 * @see violationstrategymappings.impl.ViolationstrategymappingsPackageImpl#getViolationStrategyMapping()
		 * @generated
		 */
		EClass VIOLATION_STRATEGY_MAPPING = eINSTANCE.getViolationStrategyMapping();

		/**
		 * The meta object literal for the '<em><b>Violation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION_STRATEGY_MAPPING__VIOLATION = eINSTANCE.getViolationStrategyMapping_Violation();

		/**
		 * The meta object literal for the '<em><b>Strategies</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION_STRATEGY_MAPPING__STRATEGIES = eINSTANCE.getViolationStrategyMapping_Strategies();

		/**
		 * The meta object literal for the '<em><b>Strategy Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIOLATION_STRATEGY_MAPPING__STRATEGY_PRIORITY = eINSTANCE.getViolationStrategyMapping_StrategyPriority();

		/**
		 * The meta object literal for the '{@link violationstrategymappings.impl.ViolationStrategyMappingRepositoryImpl <em>Violation Strategy Mapping Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violationstrategymappings.impl.ViolationStrategyMappingRepositoryImpl
		 * @see violationstrategymappings.impl.ViolationstrategymappingsPackageImpl#getViolationStrategyMappingRepository()
		 * @generated
		 */
		EClass VIOLATION_STRATEGY_MAPPING_REPOSITORY = eINSTANCE.getViolationStrategyMappingRepository();

		/**
		 * The meta object literal for the '<em><b>Vsmappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION_STRATEGY_MAPPING_REPOSITORY__VSMAPPINGS = eINSTANCE.getViolationStrategyMappingRepository_Vsmappings();

	}

} //ViolationstrategymappingsPackage
