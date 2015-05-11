/**
 */
package vsmappings;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see vsmappings.VsmappingsFactory
 * @model kind="package"
 * @generated
 */
public interface VsmappingsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "vsmappings";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulizar.palladiosimulator.org/lafore/vsmappings/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "vsmappings";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VsmappingsPackage eINSTANCE = vsmappings.impl.VsmappingsPackageImpl.init();

	/**
	 * The meta object id for the '{@link vsmappings.impl.VSMappingImpl <em>VS Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vsmappings.impl.VSMappingImpl
	 * @see vsmappings.impl.VsmappingsPackageImpl#getVSMapping()
	 * @generated
	 */
	int VS_MAPPING = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING__VIOLATION = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Strategies</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING__STRATEGIES = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Strategy Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING__STRATEGY_PRIORITY = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>VS Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link vsmappings.impl.VSMappingRepositoryImpl <em>VS Mapping Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see vsmappings.impl.VSMappingRepositoryImpl
	 * @see vsmappings.impl.VsmappingsPackageImpl#getVSMappingRepository()
	 * @generated
	 */
	int VS_MAPPING_REPOSITORY = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING_REPOSITORY__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING_REPOSITORY__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Vsmappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING_REPOSITORY__VSMAPPINGS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>VS Mapping Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VS_MAPPING_REPOSITORY_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * Returns the meta object for class '{@link vsmappings.VSMapping <em>VS Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>VS Mapping</em>'.
	 * @see vsmappings.VSMapping
	 * @generated
	 */
	EClass getVSMapping();

	/**
	 * Returns the meta object for the reference '{@link vsmappings.VSMapping#getViolation <em>Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Violation</em>'.
	 * @see vsmappings.VSMapping#getViolation()
	 * @see #getVSMapping()
	 * @generated
	 */
	EReference getVSMapping_Violation();

	/**
	 * Returns the meta object for the reference '{@link vsmappings.VSMapping#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Strategies</em>'.
	 * @see vsmappings.VSMapping#getStrategies()
	 * @see #getVSMapping()
	 * @generated
	 */
	EReference getVSMapping_Strategies();

	/**
	 * Returns the meta object for the attribute '{@link vsmappings.VSMapping#getStrategyPriority <em>Strategy Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Strategy Priority</em>'.
	 * @see vsmappings.VSMapping#getStrategyPriority()
	 * @see #getVSMapping()
	 * @generated
	 */
	EAttribute getVSMapping_StrategyPriority();

	/**
	 * Returns the meta object for class '{@link vsmappings.VSMappingRepository <em>VS Mapping Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>VS Mapping Repository</em>'.
	 * @see vsmappings.VSMappingRepository
	 * @generated
	 */
	EClass getVSMappingRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link vsmappings.VSMappingRepository#getVsmappings <em>Vsmappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vsmappings</em>'.
	 * @see vsmappings.VSMappingRepository#getVsmappings()
	 * @see #getVSMappingRepository()
	 * @generated
	 */
	EReference getVSMappingRepository_Vsmappings();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	VsmappingsFactory getVsmappingsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link vsmappings.impl.VSMappingImpl <em>VS Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vsmappings.impl.VSMappingImpl
		 * @see vsmappings.impl.VsmappingsPackageImpl#getVSMapping()
		 * @generated
		 */
		EClass VS_MAPPING = eINSTANCE.getVSMapping();

		/**
		 * The meta object literal for the '<em><b>Violation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VS_MAPPING__VIOLATION = eINSTANCE.getVSMapping_Violation();

		/**
		 * The meta object literal for the '<em><b>Strategies</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VS_MAPPING__STRATEGIES = eINSTANCE.getVSMapping_Strategies();

		/**
		 * The meta object literal for the '<em><b>Strategy Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VS_MAPPING__STRATEGY_PRIORITY = eINSTANCE.getVSMapping_StrategyPriority();

		/**
		 * The meta object literal for the '{@link vsmappings.impl.VSMappingRepositoryImpl <em>VS Mapping Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see vsmappings.impl.VSMappingRepositoryImpl
		 * @see vsmappings.impl.VsmappingsPackageImpl#getVSMappingRepository()
		 * @generated
		 */
		EClass VS_MAPPING_REPOSITORY = eINSTANCE.getVSMappingRepository();

		/**
		 * The meta object literal for the '<em><b>Vsmappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VS_MAPPING_REPOSITORY__VSMAPPINGS = eINSTANCE.getVSMappingRepository_Vsmappings();

	}

} //VsmappingsPackage
