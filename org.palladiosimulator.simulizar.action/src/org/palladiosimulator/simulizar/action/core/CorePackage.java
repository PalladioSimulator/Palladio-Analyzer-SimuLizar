/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see org.palladiosimulator.simulizar.action.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNAME = "core";

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String eNS_URI = "http://simulizar.palladiosimulator.org/Actions/Core/1.1";

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
    CorePackage eINSTANCE = org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
     * <em>Adaptation Behavior Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehaviorRepository()
     * @generated
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Adaptation Behavior Repository</em>' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl
     * <em>Abstract Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAbstractAdaptationBehavior()
     * @generated
     */
    int ABSTRACT_ADAPTATION_BEHAVIOR = 1;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_ADAPTATION_BEHAVIOR__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ABSTRACT_ADAPTATION_BEHAVIOR__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Actions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Abstract Adaptation Behavior</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
     * <em>Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehavior()
     * @generated
     */
    int ADAPTATION_BEHAVIOR = 2;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ID = ABSTRACT_ADAPTATION_BEHAVIOR__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ENTITY_NAME = ABSTRACT_ADAPTATION_BEHAVIOR__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Actions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS = ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS;

    /**
     * The feature id for the '<em><b>Involved Roles</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__INVOLVED_ROLES = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Transient State Profile</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Repository</b></em>' container reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__REPOSITORY = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Adaptation Behavior</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_FEATURE_COUNT = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.NestedAdaptationBehaviorImpl
     * <em>Nested Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.NestedAdaptationBehaviorImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getNestedAdaptationBehavior()
     * @generated
     */
    int NESTED_ADAPTATION_BEHAVIOR = 3;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NESTED_ADAPTATION_BEHAVIOR__ID = ABSTRACT_ADAPTATION_BEHAVIOR__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int NESTED_ADAPTATION_BEHAVIOR__ENTITY_NAME = ABSTRACT_ADAPTATION_BEHAVIOR__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Actions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NESTED_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS = ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS;

    /**
     * The feature id for the '<em><b>Guarded Transition</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Nested Adaptation Behavior</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NESTED_ADAPTATION_BEHAVIOR_FEATURE_COUNT = ABSTRACT_ADAPTATION_BEHAVIOR_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
     * <em>Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationAction()
     * @generated
     */
    int ADAPTATION_ACTION = 4;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_ACTION__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ADAPTATION_ACTION__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Behavior</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_ACTION__ADAPTATION_BEHAVIOR = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Adaptation Action</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_ACTION_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
     * <em>State Transforming Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getStateTransformingAction()
     * @generated
     */
    int STATE_TRANSFORMING_ACTION = 5;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_TRANSFORMING_ACTION__ID = ADAPTATION_ACTION__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int STATE_TRANSFORMING_ACTION__ENTITY_NAME = ADAPTATION_ACTION__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Behavior</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_TRANSFORMING_ACTION__ADAPTATION_BEHAVIOR = ADAPTATION_ACTION__ADAPTATION_BEHAVIOR;

    /**
     * The number of structural features of the '<em>State Transforming Action</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_TRANSFORMING_ACTION_FEATURE_COUNT = ADAPTATION_ACTION_FEATURE_COUNT + 0;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
     * <em>Enact Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getEnactAdaptationAction()
     * @generated
     */
    int ENACT_ADAPTATION_ACTION = 6;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION__ID = ADAPTATION_ACTION__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION__ENTITY_NAME = ADAPTATION_ACTION__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Behavior</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION__ADAPTATION_BEHAVIOR = ADAPTATION_ACTION__ADAPTATION_BEHAVIOR;

    /**
     * The feature id for the '<em><b>Adaptation Step URI</b></em>' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI = ADAPTATION_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Enact Adaptation Action</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION_FEATURE_COUNT = ADAPTATION_ACTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
     * <em>Resource Demanding Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getResourceDemandingAction()
     * @generated
     */
    int RESOURCE_DEMANDING_ACTION = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__ID = ADAPTATION_ACTION__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__ENTITY_NAME = ADAPTATION_ACTION__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Behavior</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__ADAPTATION_BEHAVIOR = ADAPTATION_ACTION__ADAPTATION_BEHAVIOR;

    /**
     * The feature id for the '<em><b>Controller Completion URI</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI = ADAPTATION_ACTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Controller Calls</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS = ADAPTATION_ACTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Resource Demanding Action</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION_FEATURE_COUNT = ADAPTATION_ACTION_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedActionImpl
     * <em>Guarded Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.GuardedActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedAction()
     * @generated
     */
    int GUARDED_ACTION = 8;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_ACTION__ID = ADAPTATION_ACTION__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_ACTION__ENTITY_NAME = ADAPTATION_ACTION__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Behavior</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ACTION__ADAPTATION_BEHAVIOR = ADAPTATION_ACTION__ADAPTATION_BEHAVIOR;

    /**
     * The feature id for the '<em><b>Guarded Transitions</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ACTION__GUARDED_TRANSITIONS = ADAPTATION_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Guarded Action</em>' class. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_ACTION_FEATURE_COUNT = ADAPTATION_ACTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl
     * <em>Guarded Transition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedTransition()
     * @generated
     */
    int GUARDED_TRANSITION = 9;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Condition URI</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION__CONDITION_URI = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Guarded Action</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION__GUARDED_ACTION = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Nested Adaptation Behavior</b></em>' containment reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Guarded Transition</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_TRANSITION_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl <em>Role Type</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getRoleType()
     * @generated
     */
    int ROLE_TYPE = 10;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROLE_TYPE__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROLE_TYPE__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Type</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROLE_TYPE__TYPE = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROLE_TYPE__ACTION = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Role Type</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int ROLE_TYPE_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
     * <em>Controller Call</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getControllerCall()
     * @generated
     */
    int CONTROLLER_CALL = 11;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Component</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL__COMPONENT = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Called Signature</b></em>' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL__CALLED_SIGNATURE = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Resource Demanding Step</b></em>' container reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL__RESOURCE_DEMANDING_STEP = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Controller Call</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONTROLLER_CALL_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository
     * <em>Adaptation Behavior Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Adaptation Behavior Repository</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository
     * @generated
     */
    EClass getAdaptationBehaviorRepository();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions
     * <em>Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository#getActions()
     * @see #getAdaptationBehaviorRepository()
     * @generated
     */
    EReference getAdaptationBehaviorRepository_Actions();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior
     * <em>Abstract Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Abstract Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior
     * @generated
     */
    EClass getAbstractAdaptationBehavior();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior#getAdaptationActions
     * <em>Adaptation Actions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Adaptation Actions</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior#getAdaptationActions()
     * @see #getAbstractAdaptationBehavior()
     * @generated
     */
    EReference getAbstractAdaptationBehavior_AdaptationActions();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior
     * <em>Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior
     * @generated
     */
    EClass getAdaptationBehavior();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getInvolvedRoles
     * <em>Involved Roles</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Involved Roles</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getInvolvedRoles()
     * @see #getAdaptationBehavior()
     * @generated
     */
    EReference getAdaptationBehavior_InvolvedRoles();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile
     * <em>Transient State Profile</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Transient State Profile</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getTransientStateProfile()
     * @see #getAdaptationBehavior()
     * @generated
     */
    EReference getAdaptationBehavior_TransientStateProfile();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository
     * <em>Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Repository</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getRepository()
     * @see #getAdaptationBehavior()
     * @generated
     */
    EReference getAdaptationBehavior_Repository();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior
     * <em>Nested Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Nested Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior
     * @generated
     */
    EClass getNestedAdaptationBehavior();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior#getGuardedTransition
     * <em>Guarded Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Guarded Transition</em>'.
     * @see org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior#getGuardedTransition()
     * @see #getNestedAdaptationBehavior()
     * @generated
     */
    EReference getNestedAdaptationBehavior_GuardedTransition();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationAction
     * <em>Adaptation Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Adaptation Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationAction
     * @generated
     */
    EClass getAdaptationAction();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAdaptationBehavior
     * <em>Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationAction#getAdaptationBehavior()
     * @see #getAdaptationAction()
     * @generated
     */
    EReference getAdaptationAction_AdaptationBehavior();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.StateTransformingAction
     * <em>State Transforming Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>State Transforming Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.StateTransformingAction
     * @generated
     */
    EClass getStateTransformingAction();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.EnactAdaptationAction
     * <em>Enact Adaptation Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Enact Adaptation Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.EnactAdaptationAction
     * @generated
     */
    EClass getEnactAdaptationAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.action.core.EnactAdaptationAction#getAdaptationStepURI
     * <em>Adaptation Step URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Adaptation Step URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.EnactAdaptationAction#getAdaptationStepURI()
     * @see #getEnactAdaptationAction()
     * @generated
     */
    EAttribute getEnactAdaptationAction_AdaptationStepURI();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.ResourceDemandingAction
     * <em>Resource Demanding Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Resource Demanding Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingAction
     * @generated
     */
    EClass getResourceDemandingAction();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.action.core.ResourceDemandingAction#getControllerCompletionURI
     * <em>Controller Completion URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Controller Completion URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingAction#getControllerCompletionURI()
     * @see #getResourceDemandingAction()
     * @generated
     */
    EAttribute getResourceDemandingAction_ControllerCompletionURI();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.action.core.ResourceDemandingAction#getControllerCalls
     * <em>Controller Calls</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Controller Calls</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingAction#getControllerCalls()
     * @see #getResourceDemandingAction()
     * @generated
     */
    EReference getResourceDemandingAction_ControllerCalls();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedAction <em>Guarded Action</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Guarded Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedAction
     * @generated
     */
    EClass getGuardedAction();

    /**
     * Returns the meta object for the containment reference list '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedAction#getGuardedTransitions
     * <em>Guarded Transitions</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference list '<em>Guarded Transitions</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedAction#getGuardedTransitions()
     * @see #getGuardedAction()
     * @generated
     */
    EReference getGuardedAction_GuardedTransitions();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition
     * <em>Guarded Transition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Guarded Transition</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition
     * @generated
     */
    EClass getGuardedTransition();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getConditionURI
     * <em>Condition URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the attribute '<em>Condition URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getConditionURI()
     * @see #getGuardedTransition()
     * @generated
     */
    EAttribute getGuardedTransition_ConditionURI();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedAction
     * <em>Guarded Action</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Guarded Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedAction()
     * @see #getGuardedTransition()
     * @generated
     */
    EReference getGuardedTransition_GuardedAction();

    /**
     * Returns the meta object for the containment reference '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getNestedAdaptationBehavior
     * <em>Nested Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the containment reference '<em>Nested Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getNestedAdaptationBehavior()
     * @see #getGuardedTransition()
     * @generated
     */
    EReference getGuardedTransition_NestedAdaptationBehavior();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.RoleType <em>Role Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Role Type</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType
     * @generated
     */
    EClass getRoleType();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.action.core.RoleType#getType <em>Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType#getType()
     * @see #getRoleType()
     * @generated
     */
    EReference getRoleType_Type();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.RoleType#getAction <em>Action</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType#getAction()
     * @see #getRoleType()
     * @generated
     */
    EReference getRoleType_Action();

    /**
     * Returns the meta object for class '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall <em>Controller Call</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for class '<em>Controller Call</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall
     * @generated
     */
    EClass getControllerCall();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent
     * <em>Component</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent()
     * @see #getControllerCall()
     * @generated
     */
    EReference getControllerCall_Component();

    /**
     * Returns the meta object for the reference '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature
     * <em>Called Signature</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the reference '<em>Called Signature</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature()
     * @see #getControllerCall()
     * @generated
     */
    EReference getControllerCall_CalledSignature();

    /**
     * Returns the meta object for the container reference '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep
     * <em>Resource Demanding Step</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the meta object for the container reference '<em>Resource Demanding Step</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep()
     * @see #getControllerCall()
     * @generated
     */
    EReference getControllerCall_ResourceDemandingStep();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the factory that creates the instances of the model.
     * @generated
     */
    CoreFactory getCoreFactory();

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
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
         * <em>Adaptation Behavior Repository</em>}' class. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehaviorRepository()
         * @generated
         */
        EClass ADAPTATION_BEHAVIOR_REPOSITORY = eINSTANCE.getAdaptationBehaviorRepository();

        /**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS = eINSTANCE.getAdaptationBehaviorRepository_Actions();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl
         * <em>Abstract Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.palladiosimulator.simulizar.action.core.impl.AbstractAdaptationBehaviorImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAbstractAdaptationBehavior()
         * @generated
         */
        EClass ABSTRACT_ADAPTATION_BEHAVIOR = eINSTANCE.getAbstractAdaptationBehavior();

        /**
         * The meta object literal for the '<em><b>Adaptation Actions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_ACTIONS = eINSTANCE
                .getAbstractAdaptationBehavior_AdaptationActions();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
         * <em>Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehavior()
         * @generated
         */
        EClass ADAPTATION_BEHAVIOR = eINSTANCE.getAdaptationBehavior();

        /**
         * The meta object literal for the '<em><b>Involved Roles</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__INVOLVED_ROLES = eINSTANCE.getAdaptationBehavior_InvolvedRoles();

        /**
         * The meta object literal for the '<em><b>Transient State Profile</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE = eINSTANCE
                .getAdaptationBehavior_TransientStateProfile();

        /**
         * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__REPOSITORY = eINSTANCE.getAdaptationBehavior_Repository();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.NestedAdaptationBehaviorImpl
         * <em>Nested Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.palladiosimulator.simulizar.action.core.impl.NestedAdaptationBehaviorImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getNestedAdaptationBehavior()
         * @generated
         */
        EClass NESTED_ADAPTATION_BEHAVIOR = eINSTANCE.getNestedAdaptationBehavior();

        /**
         * The meta object literal for the '<em><b>Guarded Transition</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION = eINSTANCE
                .getNestedAdaptationBehavior_GuardedTransition();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
         * <em>Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationAction()
         * @generated
         */
        EClass ADAPTATION_ACTION = eINSTANCE.getAdaptationAction();

        /**
         * The meta object literal for the '<em><b>Adaptation Behavior</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ADAPTATION_ACTION__ADAPTATION_BEHAVIOR = eINSTANCE.getAdaptationAction_AdaptationBehavior();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
         * <em>State Transforming Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getStateTransformingAction()
         * @generated
         */
        EClass STATE_TRANSFORMING_ACTION = eINSTANCE.getStateTransformingAction();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
         * <em>Enact Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getEnactAdaptationAction()
         * @generated
         */
        EClass ENACT_ADAPTATION_ACTION = eINSTANCE.getEnactAdaptationAction();

        /**
         * The meta object literal for the '<em><b>Adaptation Step URI</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI = eINSTANCE
                .getEnactAdaptationAction_AdaptationStepURI();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
         * <em>Resource Demanding Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getResourceDemandingAction()
         * @generated
         */
        EClass RESOURCE_DEMANDING_ACTION = eINSTANCE.getResourceDemandingAction();

        /**
         * The meta object literal for the '<em><b>Controller Completion URI</b></em>' attribute
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EAttribute RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI = eINSTANCE
                .getResourceDemandingAction_ControllerCompletionURI();

        /**
         * The meta object literal for the '<em><b>Controller Calls</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS = eINSTANCE.getResourceDemandingAction_ControllerCalls();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedActionImpl
         * <em>Guarded Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.GuardedActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedAction()
         * @generated
         */
        EClass GUARDED_ACTION = eINSTANCE.getGuardedAction();

        /**
         * The meta object literal for the '<em><b>Guarded Transitions</b></em>' containment
         * reference list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GUARDED_ACTION__GUARDED_TRANSITIONS = eINSTANCE.getGuardedAction_GuardedTransitions();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl
         * <em>Guarded Transition</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.GuardedTransitionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedTransition()
         * @generated
         */
        EClass GUARDED_TRANSITION = eINSTANCE.getGuardedTransition();

        /**
         * The meta object literal for the '<em><b>Condition URI</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GUARDED_TRANSITION__CONDITION_URI = eINSTANCE.getGuardedTransition_ConditionURI();

        /**
         * The meta object literal for the '<em><b>Guarded Action</b></em>' container reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GUARDED_TRANSITION__GUARDED_ACTION = eINSTANCE.getGuardedTransition_GuardedAction();

        /**
         * The meta object literal for the '<em><b>Nested Adaptation Behavior</b></em>' containment
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR = eINSTANCE
                .getGuardedTransition_NestedAdaptationBehavior();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl <em>Role Type</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getRoleType()
         * @generated
         */
        EClass ROLE_TYPE = eINSTANCE.getRoleType();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ROLE_TYPE__TYPE = eINSTANCE.getRoleType_Type();

        /**
         * The meta object literal for the '<em><b>Action</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference ROLE_TYPE__ACTION = eINSTANCE.getRoleType_Action();

        /**
         * The meta object literal for the '
         * {@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
         * <em>Controller Call</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @see org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getControllerCall()
         * @generated
         */
        EClass CONTROLLER_CALL = eINSTANCE.getControllerCall();

        /**
         * The meta object literal for the '<em><b>Component</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference CONTROLLER_CALL__COMPONENT = eINSTANCE.getControllerCall_Component();

        /**
         * The meta object literal for the '<em><b>Called Signature</b></em>' reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTROLLER_CALL__CALLED_SIGNATURE = eINSTANCE.getControllerCall_CalledSignature();

        /**
         * The meta object literal for the '<em><b>Resource Demanding Step</b></em>' container
         * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         * 
         * @generated
         */
        EReference CONTROLLER_CALL__RESOURCE_DEMANDING_STEP = eINSTANCE.getControllerCall_ResourceDemandingStep();

    }

} // CorePackage
