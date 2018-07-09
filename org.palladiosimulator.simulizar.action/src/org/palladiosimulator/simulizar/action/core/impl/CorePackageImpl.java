/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.identifier.IdentifierPackage;
import de.uka.ipd.sdq.probfunction.ProbfunctionPackage;
import de.uka.ipd.sdq.stoex.StoexPackage;
import de.uka.ipd.sdq.units.UnitsPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.palladiosimulator.pcm.PcmPackage;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.simulizar.action.context.ContextPackage;
import org.palladiosimulator.simulizar.action.context.impl.ContextPackageImpl;
import org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CoreFactory;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationStep;
import org.palladiosimulator.simulizar.action.core.GuardedStep;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.core.StateTransformingStep;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.impl.InstancePackageImpl;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl;
import org.palladiosimulator.simulizar.action.parameter.ParameterPackage;
import org.palladiosimulator.simulizar.action.parameter.impl.ParameterPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationBehaviorRepositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass abstractAdaptationBehaviorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationBehaviorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nestedAdaptationBehaviorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptationStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stateTransformingStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enactAdaptationStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceDemandingStepEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guardedStepEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass guardedTransitionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass roleTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private EClass controllerCallEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.palladiosimulator.simulizar.action.core.CorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CorePackageImpl() {
		super(eNS_URI, CoreFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CorePackage init() {
		if (isInited)
			return (CorePackage) EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

		// Obtain or create and register package
		CorePackageImpl theCorePackage = (CorePackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
						: new CorePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		EMFProfilePackage.eINSTANCE.eClass();
		IdentifierPackage.eINSTANCE.eClass();
		PcmPackage.eINSTANCE.eClass();
		ProbfunctionPackage.eINSTANCE.eClass();
		StoexPackage.eINSTANCE.eClass();
		UnitsPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		MappingPackageImpl theMappingPackage = (MappingPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(MappingPackage.eNS_URI) instanceof MappingPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI)
						: MappingPackage.eINSTANCE);
		InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI)
						: InstancePackage.eINSTANCE);
		ParameterPackageImpl theParameterPackage = (ParameterPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(ParameterPackage.eNS_URI) instanceof ParameterPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(ParameterPackage.eNS_URI)
						: ParameterPackage.eINSTANCE);
		ContextPackageImpl theContextPackage = (ContextPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(ContextPackage.eNS_URI) instanceof ContextPackageImpl
						? EPackage.Registry.INSTANCE.getEPackage(ContextPackage.eNS_URI)
						: ContextPackage.eINSTANCE);

		// Create package meta-data objects
		theCorePackage.createPackageContents();
		theMappingPackage.createPackageContents();
		theInstancePackage.createPackageContents();
		theParameterPackage.createPackageContents();
		theContextPackage.createPackageContents();

		// Initialize created meta-data
		theCorePackage.initializePackageContents();
		theMappingPackage.initializePackageContents();
		theInstancePackage.initializePackageContents();
		theParameterPackage.initializePackageContents();
		theContextPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCorePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
		return theCorePackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationBehaviorRepository() {
		return adaptationBehaviorRepositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationBehaviorRepository_Actions() {
		return (EReference) adaptationBehaviorRepositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationBehaviorRepository_IncludedRepositories() {
		return (EReference) adaptationBehaviorRepositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAbstractAdaptationBehavior() {
		return abstractAdaptationBehaviorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAbstractAdaptationBehavior_AdaptationSteps() {
		return (EReference) abstractAdaptationBehaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationBehavior() {
		return adaptationBehaviorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationBehavior_InvolvedRoles() {
		return (EReference) adaptationBehaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationBehavior_TransientStateProfile() {
		return (EReference) adaptationBehaviorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationBehavior_Repository() {
		return (EReference) adaptationBehaviorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNestedAdaptationBehavior() {
		return nestedAdaptationBehaviorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNestedAdaptationBehavior_GuardedTransition() {
		return (EReference) nestedAdaptationBehaviorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptationStep() {
		return adaptationStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAdaptationStep_AdaptationBehavior() {
		return (EReference) adaptationStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStateTransformingStep() {
		return stateTransformingStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnactAdaptationStep() {
		return enactAdaptationStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnactAdaptationStep_AdaptationStepURI() {
		return (EAttribute) enactAdaptationStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResourceDemandingStep() {
		return resourceDemandingStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResourceDemandingStep_ControllerCompletionURI() {
		return (EAttribute) resourceDemandingStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResourceDemandingStep_ControllerCalls() {
		return (EReference) resourceDemandingStepEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGuardedStep() {
		return guardedStepEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGuardedStep_GuardedTransitions() {
		return (EReference) guardedStepEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGuardedTransition() {
		return guardedTransitionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGuardedTransition_ConditionURI() {
		return (EAttribute) guardedTransitionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGuardedTransition_GuardedAction() {
		return (EReference) guardedTransitionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGuardedTransition_NestedAdaptationBehavior() {
		return (EReference) guardedTransitionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRoleType() {
		return roleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRoleType_Type() {
		return (EReference) roleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRoleType_Action() {
		return (EReference) roleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getControllerCall() {
		return controllerCallEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getControllerCall_Component() {
		return (EReference) controllerCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getControllerCall_CalledSignature() {
		return (EReference) controllerCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getControllerCall_ResourceDemandingStep() {
		return (EReference) controllerCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CoreFactory getCoreFactory() {
		return (CoreFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		adaptationBehaviorRepositoryEClass = createEClass(ADAPTATION_BEHAVIOR_REPOSITORY);
		createEReference(adaptationBehaviorRepositoryEClass, ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS);
		createEReference(adaptationBehaviorRepositoryEClass, ADAPTATION_BEHAVIOR_REPOSITORY__INCLUDED_REPOSITORIES);

		abstractAdaptationBehaviorEClass = createEClass(ABSTRACT_ADAPTATION_BEHAVIOR);
		createEReference(abstractAdaptationBehaviorEClass, ABSTRACT_ADAPTATION_BEHAVIOR__ADAPTATION_STEPS);

		adaptationBehaviorEClass = createEClass(ADAPTATION_BEHAVIOR);
		createEReference(adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__INVOLVED_ROLES);
		createEReference(adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE);
		createEReference(adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__REPOSITORY);

		nestedAdaptationBehaviorEClass = createEClass(NESTED_ADAPTATION_BEHAVIOR);
		createEReference(nestedAdaptationBehaviorEClass, NESTED_ADAPTATION_BEHAVIOR__GUARDED_TRANSITION);

		adaptationStepEClass = createEClass(ADAPTATION_STEP);
		createEReference(adaptationStepEClass, ADAPTATION_STEP__ADAPTATION_BEHAVIOR);

		stateTransformingStepEClass = createEClass(STATE_TRANSFORMING_STEP);

		enactAdaptationStepEClass = createEClass(ENACT_ADAPTATION_STEP);
		createEAttribute(enactAdaptationStepEClass, ENACT_ADAPTATION_STEP__ADAPTATION_STEP_URI);

		resourceDemandingStepEClass = createEClass(RESOURCE_DEMANDING_STEP);
		createEAttribute(resourceDemandingStepEClass, RESOURCE_DEMANDING_STEP__CONTROLLER_COMPLETION_URI);
		createEReference(resourceDemandingStepEClass, RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS);

		guardedStepEClass = createEClass(GUARDED_STEP);
		createEReference(guardedStepEClass, GUARDED_STEP__GUARDED_TRANSITIONS);

		guardedTransitionEClass = createEClass(GUARDED_TRANSITION);
		createEAttribute(guardedTransitionEClass, GUARDED_TRANSITION__CONDITION_URI);
		createEReference(guardedTransitionEClass, GUARDED_TRANSITION__GUARDED_ACTION);
		createEReference(guardedTransitionEClass, GUARDED_TRANSITION__NESTED_ADAPTATION_BEHAVIOR);

		roleTypeEClass = createEClass(ROLE_TYPE);
		createEReference(roleTypeEClass, ROLE_TYPE__TYPE);
		createEReference(roleTypeEClass, ROLE_TYPE__ACTION);

		controllerCallEClass = createEClass(CONTROLLER_CALL);
		createEReference(controllerCallEClass, CONTROLLER_CALL__COMPONENT);
		createEReference(controllerCallEClass, CONTROLLER_CALL__CALLED_SIGNATURE);
		createEReference(controllerCallEClass, CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
		EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage) EPackage.Registry.INSTANCE
				.getEPackage(EMFProfilePackage.eNS_URI);
		InstancePackage theInstancePackage = (InstancePackage) EPackage.Registry.INSTANCE
				.getEPackage(InstancePackage.eNS_URI);
		ParameterPackage theParameterPackage = (ParameterPackage) EPackage.Registry.INSTANCE
				.getEPackage(ParameterPackage.eNS_URI);
		ContextPackage theContextPackage = (ContextPackage) EPackage.Registry.INSTANCE
				.getEPackage(ContextPackage.eNS_URI);
		EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
		RepositoryPackage theRepositoryPackage = (RepositoryPackage) EPackage.Registry.INSTANCE
				.getEPackage(RepositoryPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		adaptationBehaviorRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
		abstractAdaptationBehaviorEClass.getESuperTypes().add(theEntityPackage.getEntity());
		adaptationBehaviorEClass.getESuperTypes().add(this.getAbstractAdaptationBehavior());
		nestedAdaptationBehaviorEClass.getESuperTypes().add(this.getAbstractAdaptationBehavior());
		adaptationStepEClass.getESuperTypes().add(theEntityPackage.getEntity());
		stateTransformingStepEClass.getESuperTypes().add(this.getAdaptationStep());
		enactAdaptationStepEClass.getESuperTypes().add(this.getAdaptationStep());
		resourceDemandingStepEClass.getESuperTypes().add(this.getAdaptationStep());
		guardedStepEClass.getESuperTypes().add(this.getAdaptationStep());
		guardedTransitionEClass.getESuperTypes().add(theEntityPackage.getEntity());
		roleTypeEClass.getESuperTypes().add(theEntityPackage.getEntity());
		controllerCallEClass.getESuperTypes().add(theEntityPackage.getEntity());

		// Initialize classes and features; add operations and parameters
		initEClass(adaptationBehaviorRepositoryEClass, AdaptationBehaviorRepository.class,
				"AdaptationBehaviorRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdaptationBehaviorRepository_Actions(), this.getAdaptationBehavior(),
				this.getAdaptationBehavior_Repository(), "actions", null, 0, -1, AdaptationBehaviorRepository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationBehaviorRepository_IncludedRepositories(), this.getAdaptationBehaviorRepository(),
				null, "includedRepositories", null, 0, -1, AdaptationBehaviorRepository.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(abstractAdaptationBehaviorEClass, AbstractAdaptationBehavior.class, "AbstractAdaptationBehavior",
				IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAbstractAdaptationBehavior_AdaptationSteps(), this.getAdaptationStep(),
				this.getAdaptationStep_AdaptationBehavior(), "adaptationSteps", null, 1, -1,
				AbstractAdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationBehaviorEClass, AdaptationBehavior.class, "AdaptationBehavior", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdaptationBehavior_InvolvedRoles(), this.getRoleType(), this.getRoleType_Action(),
				"involvedRoles", null, 1, -1, AdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationBehavior_TransientStateProfile(), theEMFProfilePackage.getProfile(), null,
				"transientStateProfile", null, 0, 1, AdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAdaptationBehavior_Repository(), this.getAdaptationBehaviorRepository(),
				this.getAdaptationBehaviorRepository_Actions(), "repository", null, 0, 1, AdaptationBehavior.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(adaptationBehaviorEClass, ecorePackage.getEBoolean(), "execute", 1, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theParameterPackage.getControllerCallInputVariableUsageCollection(),
				"controllerCallsVariableUsages", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, ecorePackage.getEBoolean(), "execute", 1, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theParameterPackage.getControllerCallInputVariableUsageCollection(),
				"controllerCallsVariableUsages", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theContextPackage.getExecutionContext(), "executionContext", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, ecorePackage.getEBoolean(), "execute", 1, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, ecorePackage.getEBoolean(), "execute", 1, 1, IS_UNIQUE,
				IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theContextPackage.getExecutionContext(), "executionContext", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, theContextPackage.getExecutionContext(), "executeAsync", 1, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theParameterPackage.getControllerCallInputVariableUsageCollection(),
				"controllerCallsVariableUsages", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, theContextPackage.getExecutionContext(), "executeAsync", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theParameterPackage.getControllerCallInputVariableUsageCollection(),
				"controllerCallsVariableUsages", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theContextPackage.getExecutionContext(), "asyncExecutionContext", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, theContextPackage.getExecutionContext(), "executeAsync", 1, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(adaptationBehaviorEClass, theContextPackage.getExecutionContext(), "executeAsync", 0, 1,
				IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theContextPackage.getExecutionContext(), "asyncExecutionContext", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(nestedAdaptationBehaviorEClass, NestedAdaptationBehavior.class, "NestedAdaptationBehavior",
				!IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNestedAdaptationBehavior_GuardedTransition(), this.getGuardedTransition(),
				this.getGuardedTransition_NestedAdaptationBehavior(), "guardedTransition", null, 0, 1,
				NestedAdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adaptationStepEClass, AdaptationStep.class, "AdaptationStep", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdaptationStep_AdaptationBehavior(), this.getAbstractAdaptationBehavior(),
				this.getAbstractAdaptationBehavior_AdaptationSteps(), "adaptationBehavior", null, 0, 1,
				AdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stateTransformingStepEClass, StateTransformingStep.class, "StateTransformingStep", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(enactAdaptationStepEClass, EnactAdaptationStep.class, "EnactAdaptationStep", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEnactAdaptationStep_AdaptationStepURI(), ecorePackage.getEString(), "adaptationStepURI", null,
				1, 1, EnactAdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceDemandingStepEClass, ResourceDemandingStep.class, "ResourceDemandingStep", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResourceDemandingStep_ControllerCompletionURI(), ecorePackage.getEString(),
				"controllerCompletionURI", null, 0, 1, ResourceDemandingStep.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceDemandingStep_ControllerCalls(), this.getControllerCall(),
				this.getControllerCall_ResourceDemandingStep(), "controllerCalls", null, 1, -1,
				ResourceDemandingStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(guardedStepEClass, GuardedStep.class, "GuardedStep", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGuardedStep_GuardedTransitions(), this.getGuardedTransition(),
				this.getGuardedTransition_GuardedAction(), "guardedTransitions", null, 0, -1, GuardedStep.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(guardedTransitionEClass, GuardedTransition.class, "GuardedTransition", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGuardedTransition_ConditionURI(), ecorePackage.getEString(), "conditionURI", null, 0, 1,
				GuardedTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getGuardedTransition_GuardedAction(), this.getGuardedStep(),
				this.getGuardedStep_GuardedTransitions(), "guardedAction", null, 0, 1, GuardedTransition.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGuardedTransition_NestedAdaptationBehavior(), this.getNestedAdaptationBehavior(),
				this.getNestedAdaptationBehavior_GuardedTransition(), "nestedAdaptationBehavior", null, 0, 1,
				GuardedTransition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(roleTypeEClass, RoleType.class, "RoleType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoleType_Type(), theEcorePackage.getEClass(), null, "type", null, 0, 1, RoleType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRoleType_Action(), this.getAdaptationBehavior(), this.getAdaptationBehavior_InvolvedRoles(),
				"action", null, 0, 1, RoleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(controllerCallEClass, ControllerCall.class, "ControllerCall", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getControllerCall_Component(), theRepositoryPackage.getBasicComponent(), null, "component", null,
				1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getControllerCall_CalledSignature(), theRepositoryPackage.getOperationSignature(), null,
				"calledSignature", null, 1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getControllerCall_ResourceDemandingStep(), this.getResourceDemandingStep(),
				this.getResourceDemandingStep_ControllerCalls(), "resourceDemandingStep", null, 0, 1,
				ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} // CorePackageImpl
