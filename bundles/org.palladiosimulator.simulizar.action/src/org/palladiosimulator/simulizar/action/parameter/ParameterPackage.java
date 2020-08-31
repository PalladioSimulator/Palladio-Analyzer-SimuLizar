/**
 */
package org.palladiosimulator.simulizar.action.parameter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.palladiosimulator.pcm.core.entity.EntityPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta
 * objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.parameter.ParameterFactory
 * @model kind="package"
 * @generated
 */
public interface ParameterPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "parameter";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/Actions/Parameter/1.1";

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "org.palladiosimulator.action";

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ParameterPackage eINSTANCE = org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl.init();

    /**
     * The meta object id for the
     * '{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl
     * <em>Controller Call Input Variable Usage</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl
     * @see org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl#getControllerCallInputVariableUsage()
     * @generated
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Variable Usage</b></em>' containment reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Corresponding Controller Call</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Containing Collection</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Controller Call Input Variable Usage</em>'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * The meta object id for the
     * '{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageCollectionImpl
     * <em>Controller Call Input Variable Usage Collection</em>}' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageCollectionImpl
     * @see org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl#getControllerCallInputVariableUsageCollection()
     * @generated
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Controller Call Input Variable Usages</b></em>' containment
     * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES = EntityPackage.ENTITY_FEATURE_COUNT
            + 0;

    /**
     * The number of structural features of the '<em>Controller Call Input Variable Usage
     * Collection</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * Returns the meta object for class
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage
     * <em>Controller Call Input Variable Usage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for class '<em>Controller Call Input Variable Usage</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage
     * @generated
     */
    EClass getControllerCallInputVariableUsage();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getVariableUsage
     * <em>Variable Usage</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Variable Usage</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getVariableUsage()
     * @see #getControllerCallInputVariableUsage()
     * @generated
     */
    EReference getControllerCallInputVariableUsage_VariableUsage();

    /**
     * Returns the meta object for the reference
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getCorrespondingControllerCall
     * <em>Corresponding Controller Call</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Corresponding Controller Call</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getCorrespondingControllerCall()
     * @see #getControllerCallInputVariableUsage()
     * @generated
     */
    EReference getControllerCallInputVariableUsage_CorrespondingControllerCall();

    /**
     * Returns the meta object for the container reference
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection
     * <em>Containing Collection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Containing Collection</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection()
     * @see #getControllerCallInputVariableUsage()
     * @generated
     */
    EReference getControllerCallInputVariableUsage_ContainingCollection();

    /**
     * Returns the meta object for class
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection
     * <em>Controller Call Input Variable Usage Collection</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Controller Call Input Variable Usage Collection</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection
     * @generated
     */
    EClass getControllerCallInputVariableUsageCollection();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection#getControllerCallInputVariableUsages
     * <em>Controller Call Input Variable Usages</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return the meta object for the containment reference list '<em>Controller Call Input
     *         Variable Usages</em>'.
     * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection#getControllerCallInputVariableUsages()
     * @see #getControllerCallInputVariableUsageCollection()
     * @generated
     */
    EReference getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ParameterFactory getParameterFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the
         * '{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl
         * <em>Controller Call Input Variable Usage</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageImpl
         * @see org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl#getControllerCallInputVariableUsage()
         * @generated
         */
        EClass CONTROLLER_CALL_INPUT_VARIABLE_USAGE = eINSTANCE.getControllerCallInputVariableUsage();

        /**
         * The meta object literal for the '<em><b>Variable Usage</b></em>' containment reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTROLLER_CALL_INPUT_VARIABLE_USAGE__VARIABLE_USAGE = eINSTANCE
            .getControllerCallInputVariableUsage_VariableUsage();

        /**
         * The meta object literal for the '<em><b>Corresponding Controller Call</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CORRESPONDING_CONTROLLER_CALL = eINSTANCE
            .getControllerCallInputVariableUsage_CorrespondingControllerCall();

        /**
         * The meta object literal for the '<em><b>Containing Collection</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTROLLER_CALL_INPUT_VARIABLE_USAGE__CONTAINING_COLLECTION = eINSTANCE
            .getControllerCallInputVariableUsage_ContainingCollection();

        /**
         * The meta object literal for the
         * '{@link org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageCollectionImpl
         * <em>Controller Call Input Variable Usage Collection</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.action.parameter.impl.ControllerCallInputVariableUsageCollectionImpl
         * @see org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl#getControllerCallInputVariableUsageCollection()
         * @generated
         */
        EClass CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION = eINSTANCE
            .getControllerCallInputVariableUsageCollection();

        /**
         * The meta object literal for the '<em><b>Controller Call Input Variable Usages</b></em>'
         * containment reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTROLLER_CALL_INPUT_VARIABLE_USAGE_COLLECTION__CONTROLLER_CALL_INPUT_VARIABLE_USAGES = eINSTANCE
            .getControllerCallInputVariableUsageCollection_ControllerCallInputVariableUsages();

    }

} // ParameterPackage
