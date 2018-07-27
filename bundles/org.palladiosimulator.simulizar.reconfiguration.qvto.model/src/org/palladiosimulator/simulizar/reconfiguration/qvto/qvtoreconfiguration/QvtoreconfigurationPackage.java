/**
 */
package org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration;

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
 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoreconfigurationFactory
 * @model kind="package"
 * @generated
 */
public interface QvtoreconfigurationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "qvtoreconfiguration";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://palladiosimulator.org/simulizar/reconfigurationrule/qvto/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "qvtoreconfiguration";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	QvtoreconfigurationPackage eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoreconfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoTransformationImpl <em>Qvto Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoTransformationImpl
	 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoreconfigurationPackageImpl#getQvtoTransformation()
	 * @generated
	 */
	int QVTO_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Model Transformation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QVTO_TRANSFORMATION__MODEL_TRANSFORMATION = ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION;

	/**
	 * The number of structural features of the '<em>Qvto Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QVTO_TRANSFORMATION_FEATURE_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Qvto Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QVTO_TRANSFORMATION_OPERATION_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoTransformation <em>Qvto Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Qvto Transformation</em>'.
	 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.QvtoTransformation
	 * @generated
	 */
	EClass getQvtoTransformation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	QvtoreconfigurationFactory getQvtoreconfigurationFactory();

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
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoTransformationImpl <em>Qvto Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoTransformationImpl
		 * @see org.palladiosimulator.simulizar.reconfiguration.qvto.qvtoreconfiguration.impl.QvtoreconfigurationPackageImpl#getQvtoTransformation()
		 * @generated
		 */
		EClass QVTO_TRANSFORMATION = eINSTANCE.getQvtoTransformation();

	}

} //QvtoreconfigurationPackage
