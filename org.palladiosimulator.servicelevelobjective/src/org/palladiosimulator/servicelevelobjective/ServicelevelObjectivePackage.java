/**
 */
package org.palladiosimulator.servicelevelobjective;

import de.uka.ipd.sdq.identifier.IdentifierPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectiveFactory
 * @model kind="package"
 * @generated
 */
public interface ServicelevelObjectivePackage extends EPackage {
    /**
     * The package name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNAME = "servicelevelobjective";

    /**
     * The package namespace URI.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_URI = "http://palladiosimulator.org/ServiceLevelObjective/1.0";

    /**
     * The package namespace name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    String eNS_PREFIX = "slo";

    /**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ServicelevelObjectivePackage eINSTANCE = org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl.init();

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveRepositoryImpl <em>Service Level Objective Repository</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveRepositoryImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getServiceLevelObjectiveRepository()
     * @generated
     */
    int SERVICE_LEVEL_OBJECTIVE_REPOSITORY = 0;

    /**
     * The feature id for the '<em><b>Servicelevelobjectives</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES = 0;

    /**
     * The number of structural features of the '<em>Service Level Objective Repository</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE_REPOSITORY_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Service Level Objective Repository</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE_REPOSITORY_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.NamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.NamedElementImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getNamedElement()
     * @generated
     */
    int NAMED_ELEMENT = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__NAME = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Named Element</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_OPERATION_COUNT = IdentifierPackage.IDENTIFIER_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl <em>Service Level Objective</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getServiceLevelObjective()
     * @generated
     */
    int SERVICE_LEVEL_OBJECTIVE = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE__ID = NAMED_ELEMENT__ID;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE__DESCRIPTION = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Lower Threshold</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Upper Threshold</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Service Level Objective</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Service Level Objective</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int SERVICE_LEVEL_OBJECTIVE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl <em>Threshold</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getThreshold()
     * @generated
     */
    int THRESHOLD = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THRESHOLD__ID = IdentifierPackage.IDENTIFIER__ID;

    /**
     * The feature id for the '<em><b>Threshold Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THRESHOLD__THRESHOLD_LIMIT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THRESHOLD_FEATURE_COUNT = IdentifierPackage.IDENTIFIER_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int THRESHOLD_OPERATION_COUNT = IdentifierPackage.IDENTIFIER_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.HardThresholdImpl <em>Hard Threshold</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.HardThresholdImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getHardThreshold()
     * @generated
     */
    int HARD_THRESHOLD = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HARD_THRESHOLD__ID = THRESHOLD__ID;

    /**
     * The feature id for the '<em><b>Threshold Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HARD_THRESHOLD__THRESHOLD_LIMIT = THRESHOLD__THRESHOLD_LIMIT;

    /**
     * The number of structural features of the '<em>Hard Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HARD_THRESHOLD_FEATURE_COUNT = THRESHOLD_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Hard Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int HARD_THRESHOLD_OPERATION_COUNT = THRESHOLD_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.palladiosimulator.servicelevelobjective.impl.LinearFuzzyThresholdImpl <em>Linear Fuzzy Threshold</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.palladiosimulator.servicelevelobjective.impl.LinearFuzzyThresholdImpl
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getLinearFuzzyThreshold()
     * @generated
     */
    int LINEAR_FUZZY_THRESHOLD = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_FUZZY_THRESHOLD__ID = THRESHOLD__ID;

    /**
     * The feature id for the '<em><b>Threshold Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_FUZZY_THRESHOLD__THRESHOLD_LIMIT = THRESHOLD__THRESHOLD_LIMIT;

    /**
     * The feature id for the '<em><b>Soft Limit</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT = THRESHOLD_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Linear Fuzzy Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_FUZZY_THRESHOLD_FEATURE_COUNT = THRESHOLD_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Linear Fuzzy Threshold</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int LINEAR_FUZZY_THRESHOLD_OPERATION_COUNT = THRESHOLD_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '<em>JS Measure</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see javax.measure.Measure
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getJSMeasure()
     * @generated
     */
    int JS_MEASURE = 6;

    /**
     * The meta object id for the '<em>JS Quantity</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see javax.measure.quantity.Quantity
     * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getJSQuantity()
     * @generated
     */
    int JS_QUANTITY = 7;


    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository <em>Service Level Objective Repository</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Service Level Objective Repository</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository
     * @generated
     */
    EClass getServiceLevelObjectiveRepository();

    /**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository#getServicelevelobjectives <em>Servicelevelobjectives</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Servicelevelobjectives</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository#getServicelevelobjectives()
     * @see #getServiceLevelObjectiveRepository()
     * @generated
     */
    EReference getServiceLevelObjectiveRepository_Servicelevelobjectives();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective <em>Service Level Objective</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Service Level Objective</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjective
     * @generated
     */
    EClass getServiceLevelObjective();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getDescription()
     * @see #getServiceLevelObjective()
     * @generated
     */
    EAttribute getServiceLevelObjective_Description();

    /**
     * Returns the meta object for the containment reference '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getLowerThreshold <em>Lower Threshold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Lower Threshold</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getLowerThreshold()
     * @see #getServiceLevelObjective()
     * @generated
     */
    EReference getServiceLevelObjective_LowerThreshold();

    /**
     * Returns the meta object for the containment reference '{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getUpperThreshold <em>Upper Threshold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Upper Threshold</em>'.
     * @see org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getUpperThreshold()
     * @see #getServiceLevelObjective()
     * @generated
     */
    EReference getServiceLevelObjective_UpperThreshold();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.Threshold <em>Threshold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Threshold</em>'.
     * @see org.palladiosimulator.servicelevelobjective.Threshold
     * @generated
     */
    EClass getThreshold();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.servicelevelobjective.Threshold#getThresholdLimit <em>Threshold Limit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Threshold Limit</em>'.
     * @see org.palladiosimulator.servicelevelobjective.Threshold#getThresholdLimit()
     * @see #getThreshold()
     * @generated
     */
    EAttribute getThreshold_ThresholdLimit();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.HardThreshold <em>Hard Threshold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Hard Threshold</em>'.
     * @see org.palladiosimulator.servicelevelobjective.HardThreshold
     * @generated
     */
    EClass getHardThreshold();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold <em>Linear Fuzzy Threshold</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Linear Fuzzy Threshold</em>'.
     * @see org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold
     * @generated
     */
    EClass getLinearFuzzyThreshold();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold#getSoftLimit <em>Soft Limit</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Soft Limit</em>'.
     * @see org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold#getSoftLimit()
     * @see #getLinearFuzzyThreshold()
     * @generated
     */
    EAttribute getLinearFuzzyThreshold_SoftLimit();

    /**
     * Returns the meta object for class '{@link org.palladiosimulator.servicelevelobjective.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for class '<em>Named Element</em>'.
     * @see org.palladiosimulator.servicelevelobjective.NamedElement
     * @generated
     */
    EClass getNamedElement();

    /**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.servicelevelobjective.NamedElement#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.palladiosimulator.servicelevelobjective.NamedElement#getName()
     * @see #getNamedElement()
     * @generated
     */
    EAttribute getNamedElement_Name();

    /**
     * Returns the meta object for data type '{@link javax.measure.Measure <em>JS Measure</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>JS Measure</em>'.
     * @see javax.measure.Measure
     * @model instanceClass="javax.measure.Measure" typeParameters="V Q" QBounds="org.palladiosimulator.servicelevelobjective.JSQuantity"
     * @generated
     */
    EDataType getJSMeasure();

    /**
     * Returns the meta object for data type '{@link javax.measure.quantity.Quantity <em>JS Quantity</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for data type '<em>JS Quantity</em>'.
     * @see javax.measure.quantity.Quantity
     * @model instanceClass="javax.measure.quantity.Quantity"
     * @generated
     */
    EDataType getJSQuantity();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ServicelevelObjectiveFactory getServicelevelObjectiveFactory();

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
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveRepositoryImpl <em>Service Level Objective Repository</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveRepositoryImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getServiceLevelObjectiveRepository()
         * @generated
         */
        EClass SERVICE_LEVEL_OBJECTIVE_REPOSITORY = eINSTANCE.getServiceLevelObjectiveRepository();

        /**
         * The meta object literal for the '<em><b>Servicelevelobjectives</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_LEVEL_OBJECTIVE_REPOSITORY__SERVICELEVELOBJECTIVES = eINSTANCE.getServiceLevelObjectiveRepository_Servicelevelobjectives();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl <em>Service Level Objective</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.ServiceLevelObjectiveImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getServiceLevelObjective()
         * @generated
         */
        EClass SERVICE_LEVEL_OBJECTIVE = eINSTANCE.getServiceLevelObjective();

        /**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute SERVICE_LEVEL_OBJECTIVE__DESCRIPTION = eINSTANCE.getServiceLevelObjective_Description();

        /**
         * The meta object literal for the '<em><b>Lower Threshold</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_LEVEL_OBJECTIVE__LOWER_THRESHOLD = eINSTANCE.getServiceLevelObjective_LowerThreshold();

        /**
         * The meta object literal for the '<em><b>Upper Threshold</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EReference SERVICE_LEVEL_OBJECTIVE__UPPER_THRESHOLD = eINSTANCE.getServiceLevelObjective_UpperThreshold();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl <em>Threshold</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getThreshold()
         * @generated
         */
        EClass THRESHOLD = eINSTANCE.getThreshold();

        /**
         * The meta object literal for the '<em><b>Threshold Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute THRESHOLD__THRESHOLD_LIMIT = eINSTANCE.getThreshold_ThresholdLimit();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.HardThresholdImpl <em>Hard Threshold</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.HardThresholdImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getHardThreshold()
         * @generated
         */
        EClass HARD_THRESHOLD = eINSTANCE.getHardThreshold();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.LinearFuzzyThresholdImpl <em>Linear Fuzzy Threshold</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.LinearFuzzyThresholdImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getLinearFuzzyThreshold()
         * @generated
         */
        EClass LINEAR_FUZZY_THRESHOLD = eINSTANCE.getLinearFuzzyThreshold();

        /**
         * The meta object literal for the '<em><b>Soft Limit</b></em>' attribute feature.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        EAttribute LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT = eINSTANCE.getLinearFuzzyThreshold_SoftLimit();

        /**
         * The meta object literal for the '{@link org.palladiosimulator.servicelevelobjective.impl.NamedElementImpl <em>Named Element</em>}' class.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see org.palladiosimulator.servicelevelobjective.impl.NamedElementImpl
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getNamedElement()
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

        /**
         * The meta object literal for the '<em>JS Measure</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see javax.measure.Measure
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getJSMeasure()
         * @generated
         */
        EDataType JS_MEASURE = eINSTANCE.getJSMeasure();

        /**
         * The meta object literal for the '<em>JS Quantity</em>' data type.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @see javax.measure.quantity.Quantity
         * @see org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectivePackageImpl#getJSQuantity()
         * @generated
         */
        EDataType JS_QUANTITY = eINSTANCE.getJSQuantity();

    }

} //ServicelevelObjectivePackage
