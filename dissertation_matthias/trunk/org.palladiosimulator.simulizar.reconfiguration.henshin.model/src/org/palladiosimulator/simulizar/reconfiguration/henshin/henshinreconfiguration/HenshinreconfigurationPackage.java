/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage;

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
 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationFactory
 * @model kind="package"
 * @generated
 */
public interface HenshinreconfigurationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "henshinreconfiguration";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://palladiosimulator.org/simulizar/reconfigurationrule/henshin/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "henshinreconfiguration";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HenshinreconfigurationPackage eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinreconfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinTransformationImpl <em>Henshin Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinTransformationImpl
	 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinreconfigurationPackageImpl#getHenshinTransformation()
	 * @generated
	 */
	int HENSHIN_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Model Transformation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HENSHIN_TRANSFORMATION__MODEL_TRANSFORMATION = ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION;

	/**
	 * The number of structural features of the '<em>Henshin Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HENSHIN_TRANSFORMATION_FEATURE_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Henshin Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HENSHIN_TRANSFORMATION_OPERATION_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinTransformation <em>Henshin Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Henshin Transformation</em>'.
	 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinTransformation
	 * @generated
	 */
	EClass getHenshinTransformation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	HenshinreconfigurationFactory getHenshinreconfigurationFactory();

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
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinTransformationImpl <em>Henshin Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinTransformationImpl
		 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinreconfigurationPackageImpl#getHenshinTransformation()
		 * @generated
		 */
		EClass HENSHIN_TRANSFORMATION = eINSTANCE.getHenshinTransformation();

	}

} //HenshinreconfigurationPackage
