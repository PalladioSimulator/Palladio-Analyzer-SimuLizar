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
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
     * <em>Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehavior()
     * @generated
     */
    int ADAPTATION_BEHAVIOR = 0;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ID = EntityPackage.ENTITY__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Adaptation Steps</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__ADAPTATION_STEPS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Involved Roles</b></em>' containment reference list. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__INVOLVED_ROLES = EntityPackage.ENTITY_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Repository</b></em>' container reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__REPOSITORY = EntityPackage.ENTITY_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Transient State Profile</b></em>' reference. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE = EntityPackage.ENTITY_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Adaptation Behavior</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_BEHAVIOR_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 4;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
     * <em>Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationAction()
     * @generated
     */
    int ADAPTATION_ACTION = 1;

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
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ADAPTATION_ACTION__ACTION = EntityPackage.ENTITY_FEATURE_COUNT + 0;

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
     * {@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
     * <em>Resource Demanding Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getResourceDemandingAction()
     * @generated
     */
    int RESOURCE_DEMANDING_ACTION = 2;

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
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RESOURCE_DEMANDING_ACTION__ACTION = ADAPTATION_ACTION__ACTION;

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
     * {@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl <em>Role Type</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getRoleType()
     * @generated
     */
    int ROLE_TYPE = 3;

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
     * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
     * <em>Adaptation Behavior Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorRepositoryImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehaviorRepository()
     * @generated
     */
    int ADAPTATION_BEHAVIOR_REPOSITORY = 4;

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
     * {@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
     * <em>Controller Call</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getControllerCall()
     * @generated
     */
    int CONTROLLER_CALL = 5;

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
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
     * <em>State Transforming Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.StateTransformingActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getStateTransformingAction()
     * @generated
     */
    int STATE_TRANSFORMING_ACTION = 6;

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
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STATE_TRANSFORMING_ACTION__ACTION = ADAPTATION_ACTION__ACTION;

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
     * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedAdaptationBehaviorImpl
     * <em>Guarded Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.GuardedAdaptationBehaviorImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedAdaptationBehavior()
     * @generated
     */
    int GUARDED_ADAPTATION_BEHAVIOR = 7;

    /**
     * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ADAPTATION_BEHAVIOR__ID = ADAPTATION_ACTION__ID;

    /**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ADAPTATION_BEHAVIOR__ENTITY_NAME = ADAPTATION_ACTION__ENTITY_NAME;

    /**
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ADAPTATION_BEHAVIOR__ACTION = ADAPTATION_ACTION__ACTION;

    /**
     * The feature id for the '<em><b>Precondition URI</b></em>' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI = ADAPTATION_ACTION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Guarded Adaptation Behavior</em>' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int GUARDED_ADAPTATION_BEHAVIOR_FEATURE_COUNT = ADAPTATION_ACTION_FEATURE_COUNT + 1;

    /**
     * The meta object id for the '
     * {@link org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
     * <em>Enact Adaptation Action</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.palladiosimulator.simulizar.action.core.impl.EnactAdaptationActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getEnactAdaptationAction()
     * @generated
     */
    int ENACT_ADAPTATION_ACTION = 8;

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
     * The feature id for the '<em><b>Action</b></em>' container reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENACT_ADAPTATION_ACTION__ACTION = ADAPTATION_ACTION__ACTION;

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
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getAdaptationSteps
     * <em>Adaptation Steps</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Adaptation Steps</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getAdaptationSteps()
     * @see #getAdaptationBehavior()
     * @generated
     */
    EReference getAdaptationBehavior_AdaptationSteps();

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
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAction <em>Action</em>
     * }'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the container reference '<em>Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationAction#getAction()
     * @see #getAdaptationAction()
     * @generated
     */
    EReference getAdaptationAction_Action();

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
     * {@link org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior
     * <em>Guarded Adaptation Behavior</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Guarded Adaptation Behavior</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior
     * @generated
     */
    EClass getGuardedAdaptationBehavior();

    /**
     * Returns the meta object for the attribute '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior#getPreconditionURI
     * <em>Precondition URI</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Precondition URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior#getPreconditionURI()
     * @see #getGuardedAdaptationBehavior()
     * @generated
     */
    EAttribute getGuardedAdaptationBehavior_PreconditionURI();

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
         * {@link org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
         * <em>Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationBehaviorImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationBehavior()
         * @generated
         */
        EClass ADAPTATION_BEHAVIOR = eINSTANCE.getAdaptationBehavior();

        /**
         * The meta object literal for the '<em><b>Adaptation Steps</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__ADAPTATION_STEPS = eINSTANCE.getAdaptationBehavior_AdaptationSteps();

        /**
         * The meta object literal for the '<em><b>Involved Roles</b></em>' containment reference
         * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__INVOLVED_ROLES = eINSTANCE.getAdaptationBehavior_InvolvedRoles();

        /**
         * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__REPOSITORY = eINSTANCE.getAdaptationBehavior_Repository();

        /**
         * The meta object literal for the '<em><b>Transient State Profile</b></em>' reference
         * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE = eINSTANCE
                .getAdaptationBehavior_TransientStateProfile();

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
         * The meta object literal for the '<em><b>Action</b></em>' container reference feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ADAPTATION_ACTION__ACTION = eINSTANCE.getAdaptationAction_Action();

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
         * {@link org.palladiosimulator.simulizar.action.core.impl.GuardedAdaptationBehaviorImpl
         * <em>Guarded Adaptation Behavior</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
         * -->
         *
         * @see org.palladiosimulator.simulizar.action.core.impl.GuardedAdaptationBehaviorImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getGuardedAdaptationBehavior()
         * @generated
         */
        EClass GUARDED_ADAPTATION_BEHAVIOR = eINSTANCE.getGuardedAdaptationBehavior();

        /**
         * The meta object literal for the '<em><b>Precondition URI</b></em>' attribute feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI = eINSTANCE
                .getGuardedAdaptationBehavior_PreconditionURI();

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

    }

} // CorePackage
