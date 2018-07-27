/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagram.model;

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
 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StorydiagramreconfigurationFactory
 * @model kind="package"
 * @generated
 */
public interface StorydiagramreconfigurationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "storydiagramreconfiguration";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://palladiosimulator.org/simulizar/reconfigurationrule/storydiagram/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "storydiagram";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StorydiagramreconfigurationPackage eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StorydiagramreconfigurationPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StoryDiagramActivityImpl <em>Story Diagram Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StoryDiagramActivityImpl
	 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StorydiagramreconfigurationPackageImpl#getStoryDiagramActivity()
	 * @generated
	 */
	int STORY_DIAGRAM_ACTIVITY = 0;

	/**
	 * The feature id for the '<em><b>Model Transformation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_DIAGRAM_ACTIVITY__MODEL_TRANSFORMATION = ReconfigurationrulePackage.MODEL_TRANSFORMATION__MODEL_TRANSFORMATION;

	/**
	 * The number of structural features of the '<em>Story Diagram Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_DIAGRAM_ACTIVITY_FEATURE_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Story Diagram Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STORY_DIAGRAM_ACTIVITY_OPERATION_COUNT = ReconfigurationrulePackage.MODEL_TRANSFORMATION_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StoryDiagramActivity <em>Story Diagram Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Story Diagram Activity</em>'.
	 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StoryDiagramActivity
	 * @generated
	 */
	EClass getStoryDiagramActivity();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	StorydiagramreconfigurationFactory getStorydiagramreconfigurationFactory();

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
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StoryDiagramActivityImpl <em>Story Diagram Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StoryDiagramActivityImpl
		 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StorydiagramreconfigurationPackageImpl#getStoryDiagramActivity()
		 * @generated
		 */
		EClass STORY_DIAGRAM_ACTIVITY = eINSTANCE.getStoryDiagramActivity();

	}

} //StorydiagramreconfigurationPackage
