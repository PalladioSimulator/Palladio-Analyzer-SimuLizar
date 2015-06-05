/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

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
 * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationruleFactory
 * @model kind="package"
 * @generated
 */
public interface reconfigurationrulePackage extends EPackage {
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
    String eNS_URI = "http://palladiosimulator.org/simulizar/reconfigurationrule/1.0";

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
    reconfigurationrulePackage eINSTANCE = org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl.init();

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleSetImpl <em>Reconfiguration Rule Set</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleSetImpl
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getReconfigurationRuleSet()
     * @generated
     */
    int RECONFIGURATION_RULE_SET = 0;

    /**
     * The feature id for the '<em><b>Rules</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_SET__RULES = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_SET__NAME = 1;

    /**
     * The number of structural features of the '<em>Reconfiguration Rule Set</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_SET_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Reconfiguration Rule Set</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_SET_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl <em>Reconfiguration Rule</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getReconfigurationRule()
     * @generated
     */
    int RECONFIGURATION_RULE = 1;

    /**
     * The feature id for the '<em><b>Priority</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE__PRIORITY = 0;

    /**
     * The feature id for the '<em><b>Condition Check</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE__CONDITION_CHECK = 1;

    /**
     * The feature id for the '<em><b>Transformation Action</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE__TRANSFORMATION_ACTION = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE__NAME = 3;

    /**
     * The number of structural features of the '<em>Reconfiguration Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_FEATURE_COUNT = 4;

    /**
     * The number of operations of the '<em>Reconfiguration Rule</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int RECONFIGURATION_RULE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl <em>Model Transformation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl
     * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getModelTransformation()
     * @generated
     */
    int MODEL_TRANSFORMATION = 2;

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
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet <em>Reconfiguration Rule Set</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reconfiguration Rule Set</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet
     * @generated
     */
    EClass getReconfigurationRuleSet();

    /**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getRules <em>Rules</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Rules</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getRules()
     * @see #getReconfigurationRuleSet()
     * @generated
     */
    EReference getReconfigurationRuleSet_Rules();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getName()
     * @see #getReconfigurationRuleSet()
     * @generated
     */
    EAttribute getReconfigurationRuleSet_Name();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule <em>Reconfiguration Rule</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Reconfiguration Rule</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule
     * @generated
     */
    EClass getReconfigurationRule();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getPriority <em>Priority</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Priority</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getPriority()
     * @see #getReconfigurationRule()
     * @generated
     */
    EAttribute getReconfigurationRule_Priority();

    /**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getConditionCheck <em>Condition Check</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Condition Check</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getConditionCheck()
     * @see #getReconfigurationRule()
     * @generated
     */
    EReference getReconfigurationRule_ConditionCheck();

    /**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getTransformationAction <em>Transformation Action</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Transformation Action</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getTransformationAction()
     * @see #getReconfigurationRule()
     * @generated
     */
    EReference getReconfigurationRule_TransformationAction();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getName()
     * @see #getReconfigurationRule()
     * @generated
     */
    EAttribute getReconfigurationRule_Name();

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
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    reconfigurationruleFactory getreconfigurationruleFactory();

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
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleSetImpl <em>Reconfiguration Rule Set</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleSetImpl
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getReconfigurationRuleSet()
         * @generated
         */
        EClass RECONFIGURATION_RULE_SET = eINSTANCE.getReconfigurationRuleSet();

        /**
         * The meta object literal for the '<em><b>Rules</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RECONFIGURATION_RULE_SET__RULES = eINSTANCE.getReconfigurationRuleSet_Rules();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RECONFIGURATION_RULE_SET__NAME = eINSTANCE.getReconfigurationRuleSet_Name();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl <em>Reconfiguration Rule</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationRuleImpl
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getReconfigurationRule()
         * @generated
         */
        EClass RECONFIGURATION_RULE = eINSTANCE.getReconfigurationRule();

        /**
         * The meta object literal for the '<em><b>Priority</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RECONFIGURATION_RULE__PRIORITY = eINSTANCE.getReconfigurationRule_Priority();

        /**
         * The meta object literal for the '<em><b>Condition Check</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RECONFIGURATION_RULE__CONDITION_CHECK = eINSTANCE.getReconfigurationRule_ConditionCheck();

        /**
         * The meta object literal for the '<em><b>Transformation Action</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference RECONFIGURATION_RULE__TRANSFORMATION_ACTION = eINSTANCE.getReconfigurationRule_TransformationAction();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute RECONFIGURATION_RULE__NAME = eINSTANCE.getReconfigurationRule_Name();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl <em>Model Transformation</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl
         * @see org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationrulePackageImpl#getModelTransformation()
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

    }

} //reconfigurationrulePackage
