/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl;

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
 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationruleFactory
 * @model kind="package"
 * @generated
 */
public interface ReconfigurationrulePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "reconfigurationrule";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://palladiosimulator.org/simulizar/reconfiguration/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "reconf";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReconfigurationrulePackage eINSTANCE = ReconfigurationrulePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl <em>Model Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getModelTransformation()
	 * @generated
	 */
	int MODEL_TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Model Transformation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TRANSFORMATION__MODEL_TRANSFORMATION = 0;

	/**
	 * The number of structural features of the '<em>Model Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TRANSFORMATION_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Model Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_TRANSFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.NamedElementImpl
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationImpl <em>Reconfiguration</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationImpl
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getReconfiguration()
	 * @generated
	 */
	int RECONFIGURATION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Strategies</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION__STRATEGIES = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Reconfiguration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Reconfiguration</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECONFIGURATION_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl <em>Tactic</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getTactic()
	 * @generated
	 */
	int TACTIC = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC__PRIORITY = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC__CONDITION = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Action</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC__ACTION = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Tactic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Tactic</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TACTIC_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl <em>Strategy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getStrategy()
	 * @generated
	 */
	int STRATEGY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Slos</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__SLOS = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Tactics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY__TACTICS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Strategy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRATEGY_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation <em>Model Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Transformation</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation
	 * @generated
	 */
	EClass getModelTransformation();

	/**
	 * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation#getModelTransformation <em>Model Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model Transformation</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation#getModelTransformation()
	 * @see #getModelTransformation()
	 * @generated
	 */
	EReference getModelTransformation_ModelTransformation();

	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.Reconfiguration <em>Reconfiguration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reconfiguration</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Reconfiguration
	 * @generated
	 */
	EClass getReconfiguration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.Reconfiguration#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Strategies</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Reconfiguration#getStrategies()
	 * @see #getReconfiguration()
	 * @generated
	 */
	EReference getReconfiguration_Strategies();

	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic <em>Tactic</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tactic</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Tactic
	 * @generated
	 */
	EClass getTactic();

	/**
	 * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getPriority <em>Priority</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Priority</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getPriority()
	 * @see #getTactic()
	 * @generated
	 */
	EAttribute getTactic_Priority();

	/**
	 * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Condition</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getCondition()
	 * @see #getTactic()
	 * @generated
	 */
	EReference getTactic_Condition();

	/**
	 * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Action</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getAction()
	 * @see #getTactic()
	 * @generated
	 */
	EReference getTactic_Action();

	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy <em>Strategy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strategy</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Strategy
	 * @generated
	 */
	EClass getStrategy();

	/**
	 * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getSlos <em>Slos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Slos</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getSlos()
	 * @see #getStrategy()
	 * @generated
	 */
	EReference getStrategy_Slos();

	/**
	 * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getTactics <em>Tactics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tactics</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getTactics()
	 * @see #getStrategy()
	 * @generated
	 */
	EReference getStrategy_Tactics();

	/**
	 * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.reconfigurationrule.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ReconfigurationruleFactory getReconfigurationruleFactory();

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
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl <em>Model Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getModelTransformation()
		 * @generated
		 */
		EClass MODEL_TRANSFORMATION = eINSTANCE.getModelTransformation();

		/**
		 * The meta object literal for the '<em><b>Model Transformation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_TRANSFORMATION__MODEL_TRANSFORMATION = eINSTANCE.getModelTransformation_ModelTransformation();

		/**
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationImpl <em>Reconfiguration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationImpl
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getReconfiguration()
		 * @generated
		 */
		EClass RECONFIGURATION = eINSTANCE.getReconfiguration();

		/**
		 * The meta object literal for the '<em><b>Strategies</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RECONFIGURATION__STRATEGIES = eINSTANCE.getReconfiguration_Strategies();

		/**
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl <em>Tactic</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.TacticImpl
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getTactic()
		 * @generated
		 */
		EClass TACTIC = eINSTANCE.getTactic();

		/**
		 * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TACTIC__PRIORITY = eINSTANCE.getTactic_Priority();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIC__CONDITION = eINSTANCE.getTactic_Condition();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TACTIC__ACTION = eINSTANCE.getTactic_Action();

		/**
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl <em>Strategy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.StrategyImpl
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getStrategy()
		 * @generated
		 */
		EClass STRATEGY = eINSTANCE.getStrategy();

		/**
		 * The meta object literal for the '<em><b>Slos</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGY__SLOS = eINSTANCE.getStrategy_Slos();

		/**
		 * The meta object literal for the '<em><b>Tactics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRATEGY__TACTICS = eINSTANCE.getStrategy_Tactics();

		/**
		 * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.NamedElementImpl
		 * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationrulePackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

	}

} //ReconfigurationrulePackage
