/**
 */
package org.palladiosimulator.simulizar.action.core;

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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.action.core.CoreFactory
 * @model kind="package"
 * @generated
 */
public interface CorePackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "core";

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://simulizar.palladiosimulator.org/Actions/Core/1.0";

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "org.palladiosimulator.action";

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	CorePackage eINSTANCE = org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl.init();

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl <em>Action</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.ActionImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAction()
     * @generated
     */
	int ACTION = 0;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Adaptation Steps</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__ADAPTATION_STEPS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Involved Roles</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__INVOLVED_ROLES = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Repository</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__REPOSITORY = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Transient State Profile</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION__TRANSIENT_STATE_PROFILE = EntityPackage.ENTITY_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Action</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl <em>Adaptation Step</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationStep()
     * @generated
     */
	int ADAPTATION_STEP = 1;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Controller Completion URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__CONTROLLER_COMPLETION_URI = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Adaptation Step URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__ADAPTATION_STEP_URI = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Precondition URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__PRECONDITION_URI = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Action</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP__ACTION = EntityPackage.ENTITY_FEATURE_COUNT + 3;

	/**
     * The number of structural features of the '<em>Adaptation Step</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ADAPTATION_STEP_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 4;

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl <em>Resource Demanding Step</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getResourceDemandingStep()
     * @generated
     */
	int RESOURCE_DEMANDING_STEP = 2;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__ID = ADAPTATION_STEP__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__ENTITY_NAME = ADAPTATION_STEP__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Controller Completion URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI = ADAPTATION_STEP__CONTROLLER_COMPLETION_URI;

	/**
     * The feature id for the '<em><b>Adaptation Step URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__ADAPTATION_STEP_URI = ADAPTATION_STEP__ADAPTATION_STEP_URI;

	/**
     * The feature id for the '<em><b>Precondition URI</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__PRECONDITION_URI = ADAPTATION_STEP__PRECONDITION_URI;

	/**
     * The feature id for the '<em><b>Action</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__ACTION = ADAPTATION_STEP__ACTION;

	/**
     * The feature id for the '<em><b>Controller Calls</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS = ADAPTATION_STEP_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Resource Demanding Step</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int RESOURCE_DEMANDING_STEP_FEATURE_COUNT = ADAPTATION_STEP_FEATURE_COUNT + 1;

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl <em>Role Type</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getRoleType()
     * @generated
     */
	int ROLE_TYPE = 3;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_TYPE__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_TYPE__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Type</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_TYPE__TYPE = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Action</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_TYPE__ACTION = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Role Type</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ROLE_TYPE_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.ActionRepositoryImpl <em>Action Repository</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.ActionRepositoryImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getActionRepository()
     * @generated
     */
	int ACTION_REPOSITORY = 4;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION_REPOSITORY__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION_REPOSITORY__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Actions</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION_REPOSITORY__ACTIONS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The number of structural features of the '<em>Action Repository</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ACTION_REPOSITORY_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;


	/**
     * The meta object id for the '{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl <em>Controller Call</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
     * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getControllerCall()
     * @generated
     */
	int CONTROLLER_CALL = 5;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL__ID = EntityPackage.ENTITY__ID;

	/**
     * The feature id for the '<em><b>Entity Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
     * The feature id for the '<em><b>Component</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL__COMPONENT = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Called Signature</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL__CALLED_SIGNATURE = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Resource Demanding Step</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL__RESOURCE_DEMANDING_STEP = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
     * The number of structural features of the '<em>Controller Call</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONTROLLER_CALL_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 3;


	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.Action <em>Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.Action
     * @generated
     */
	EClass getAction();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.action.core.Action#getAdaptationSteps <em>Adaptation Steps</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Adaptation Steps</em>'.
     * @see org.palladiosimulator.simulizar.action.core.Action#getAdaptationSteps()
     * @see #getAction()
     * @generated
     */
	EReference getAction_AdaptationSteps();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.action.core.Action#getInvolvedRoles <em>Involved Roles</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Involved Roles</em>'.
     * @see org.palladiosimulator.simulizar.action.core.Action#getInvolvedRoles()
     * @see #getAction()
     * @generated
     */
	EReference getAction_InvolvedRoles();

	/**
     * Returns the meta object for the container reference '{@link org.palladiosimulator.simulizar.action.core.Action#getRepository <em>Repository</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Repository</em>'.
     * @see org.palladiosimulator.simulizar.action.core.Action#getRepository()
     * @see #getAction()
     * @generated
     */
	EReference getAction_Repository();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.core.Action#getTransientStateProfile <em>Transient State Profile</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Transient State Profile</em>'.
     * @see org.palladiosimulator.simulizar.action.core.Action#getTransientStateProfile()
     * @see #getAction()
     * @generated
     */
	EReference getAction_TransientStateProfile();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep <em>Adaptation Step</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Adaptation Step</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep
     * @generated
     */
	EClass getAdaptationStep();

	/**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getControllerCompletionURI <em>Controller Completion URI</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Controller Completion URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep#getControllerCompletionURI()
     * @see #getAdaptationStep()
     * @generated
     */
	EAttribute getAdaptationStep_ControllerCompletionURI();

	/**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationStepURI <em>Adaptation Step URI</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Adaptation Step URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationStepURI()
     * @see #getAdaptationStep()
     * @generated
     */
	EAttribute getAdaptationStep_AdaptationStepURI();

	/**
     * Returns the meta object for the attribute '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getPreconditionURI <em>Precondition URI</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Precondition URI</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep#getPreconditionURI()
     * @see #getAdaptationStep()
     * @generated
     */
	EAttribute getAdaptationStep_PreconditionURI();

	/**
     * Returns the meta object for the container reference '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep#getAction()
     * @see #getAdaptationStep()
     * @generated
     */
	EReference getAdaptationStep_Action();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep <em>Resource Demanding Step</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Resource Demanding Step</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingStep
     * @generated
     */
	EClass getResourceDemandingStep();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls <em>Controller Calls</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Controller Calls</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls()
     * @see #getResourceDemandingStep()
     * @generated
     */
	EReference getResourceDemandingStep_ControllerCalls();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.RoleType <em>Role Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Role Type</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType
     * @generated
     */
	EClass getRoleType();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.core.RoleType#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Type</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType#getType()
     * @see #getRoleType()
     * @generated
     */
	EReference getRoleType_Type();

	/**
     * Returns the meta object for the container reference '{@link org.palladiosimulator.simulizar.action.core.RoleType#getAction <em>Action</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Action</em>'.
     * @see org.palladiosimulator.simulizar.action.core.RoleType#getAction()
     * @see #getRoleType()
     * @generated
     */
	EReference getRoleType_Action();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.ActionRepository <em>Action Repository</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Action Repository</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ActionRepository
     * @generated
     */
	EClass getActionRepository();

	/**
     * Returns the meta object for the containment reference list '{@link org.palladiosimulator.simulizar.action.core.ActionRepository#getActions <em>Actions</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Actions</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ActionRepository#getActions()
     * @see #getActionRepository()
     * @generated
     */
	EReference getActionRepository_Actions();

	/**
     * Returns the meta object for class '{@link org.palladiosimulator.simulizar.action.core.ControllerCall <em>Controller Call</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Controller Call</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall
     * @generated
     */
	EClass getControllerCall();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent <em>Component</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Component</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent()
     * @see #getControllerCall()
     * @generated
     */
	EReference getControllerCall_Component();

	/**
     * Returns the meta object for the reference '{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature <em>Called Signature</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Called Signature</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature()
     * @see #getControllerCall()
     * @generated
     */
	EReference getControllerCall_CalledSignature();

	/**
     * Returns the meta object for the container reference '{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep <em>Resource Demanding Step</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Resource Demanding Step</em>'.
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep()
     * @see #getControllerCall()
     * @generated
     */
	EReference getControllerCall_ResourceDemandingStep();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	CoreFactory getCoreFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.ActionImpl <em>Action</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.ActionImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAction()
         * @generated
         */
		EClass ACTION = eINSTANCE.getAction();

		/**
         * The meta object literal for the '<em><b>Adaptation Steps</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTION__ADAPTATION_STEPS = eINSTANCE.getAction_AdaptationSteps();

		/**
         * The meta object literal for the '<em><b>Involved Roles</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTION__INVOLVED_ROLES = eINSTANCE.getAction_InvolvedRoles();

		/**
         * The meta object literal for the '<em><b>Repository</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTION__REPOSITORY = eINSTANCE.getAction_Repository();

		/**
         * The meta object literal for the '<em><b>Transient State Profile</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTION__TRANSIENT_STATE_PROFILE = eINSTANCE.getAction_TransientStateProfile();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl <em>Adaptation Step</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.AdaptationStepImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getAdaptationStep()
         * @generated
         */
		EClass ADAPTATION_STEP = eINSTANCE.getAdaptationStep();

		/**
         * The meta object literal for the '<em><b>Controller Completion URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ADAPTATION_STEP__CONTROLLER_COMPLETION_URI = eINSTANCE.getAdaptationStep_ControllerCompletionURI();

		/**
         * The meta object literal for the '<em><b>Adaptation Step URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ADAPTATION_STEP__ADAPTATION_STEP_URI = eINSTANCE.getAdaptationStep_AdaptationStepURI();

		/**
         * The meta object literal for the '<em><b>Precondition URI</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ADAPTATION_STEP__PRECONDITION_URI = eINSTANCE.getAdaptationStep_PreconditionURI();

		/**
         * The meta object literal for the '<em><b>Action</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ADAPTATION_STEP__ACTION = eINSTANCE.getAdaptationStep_Action();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl <em>Resource Demanding Step</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.ResourceDemandingStepImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getResourceDemandingStep()
         * @generated
         */
		EClass RESOURCE_DEMANDING_STEP = eINSTANCE.getResourceDemandingStep();

		/**
         * The meta object literal for the '<em><b>Controller Calls</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS = eINSTANCE.getResourceDemandingStep_ControllerCalls();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl <em>Role Type</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getRoleType()
         * @generated
         */
		EClass ROLE_TYPE = eINSTANCE.getRoleType();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ROLE_TYPE__TYPE = eINSTANCE.getRoleType_Type();

		/**
         * The meta object literal for the '<em><b>Action</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ROLE_TYPE__ACTION = eINSTANCE.getRoleType_Action();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.ActionRepositoryImpl <em>Action Repository</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.ActionRepositoryImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getActionRepository()
         * @generated
         */
		EClass ACTION_REPOSITORY = eINSTANCE.getActionRepository();

		/**
         * The meta object literal for the '<em><b>Actions</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ACTION_REPOSITORY__ACTIONS = eINSTANCE.getActionRepository_Actions();

		/**
         * The meta object literal for the '{@link org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl <em>Controller Call</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.palladiosimulator.simulizar.action.core.impl.ControllerCallImpl
         * @see org.palladiosimulator.simulizar.action.core.impl.CorePackageImpl#getControllerCall()
         * @generated
         */
		EClass CONTROLLER_CALL = eINSTANCE.getControllerCall();

		/**
         * The meta object literal for the '<em><b>Component</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_CALL__COMPONENT = eINSTANCE.getControllerCall_Component();

		/**
         * The meta object literal for the '<em><b>Called Signature</b></em>' reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_CALL__CALLED_SIGNATURE = eINSTANCE.getControllerCall_CalledSignature();

		/**
         * The meta object literal for the '<em><b>Resource Demanding Step</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONTROLLER_CALL__RESOURCE_DEMANDING_STEP = eINSTANCE.getControllerCall_ResourceDemandingStep();

	}

} //CorePackage
