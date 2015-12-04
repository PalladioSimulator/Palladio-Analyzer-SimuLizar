/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

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
import org.palladiosimulator.simulizar.action.core.AdaptationAction;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CoreFactory;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;
import org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.core.StateTransformingAction;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.impl.InstancePackageImpl;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass adaptationBehaviorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass adaptationActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass resourceDemandingActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass roleTypeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass adaptationBehaviorRepositoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass controllerCallEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass stateTransformingActionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass guardedAdaptationBehaviorEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass enactAdaptationActionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package package URI
     * value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init
     * init()}, which also performs initialization of the package, or returns the registered
     * package, if one already exists. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
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
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others
     * upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to
     * obtain the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static CorePackage init() {
        if (isInited) {
            return (CorePackage) EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
        }

        // Obtain or create and register package
        final CorePackageImpl theCorePackage = (CorePackageImpl) (EPackage.Registry.INSTANCE
                .get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI)
                        : new CorePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EMFProfilePackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        final MappingPackageImpl theMappingPackage = (MappingPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(MappingPackage.eNS_URI) instanceof MappingPackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI) : MappingPackage.eINSTANCE);
        final InstancePackageImpl theInstancePackage = (InstancePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl
                        ? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) : InstancePackage.eINSTANCE);

        // Create package meta-data objects
        theCorePackage.createPackageContents();
        theMappingPackage.createPackageContents();
        theInstancePackage.createPackageContents();

        // Initialize created meta-data
        theCorePackage.initializePackageContents();
        theMappingPackage.initializePackageContents();
        theInstancePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theCorePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(CorePackage.eNS_URI, theCorePackage);
        return theCorePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAdaptationBehavior() {
        return this.adaptationBehaviorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationBehavior_AdaptationSteps() {
        return (EReference) this.adaptationBehaviorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationBehavior_InvolvedRoles() {
        return (EReference) this.adaptationBehaviorEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationBehavior_Repository() {
        return (EReference) this.adaptationBehaviorEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationBehavior_TransientStateProfile() {
        return (EReference) this.adaptationBehaviorEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAdaptationAction() {
        return this.adaptationActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationAction_Action() {
        return (EReference) this.adaptationActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getResourceDemandingAction() {
        return this.resourceDemandingActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getResourceDemandingAction_ControllerCompletionURI() {
        return (EAttribute) this.resourceDemandingActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getResourceDemandingAction_ControllerCalls() {
        return (EReference) this.resourceDemandingActionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRoleType() {
        return this.roleTypeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRoleType_Type() {
        return (EReference) this.roleTypeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getRoleType_Action() {
        return (EReference) this.roleTypeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAdaptationBehaviorRepository() {
        return this.adaptationBehaviorRepositoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAdaptationBehaviorRepository_Actions() {
        return (EReference) this.adaptationBehaviorRepositoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getControllerCall() {
        return this.controllerCallEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCall_Component() {
        return (EReference) this.controllerCallEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCall_CalledSignature() {
        return (EReference) this.controllerCallEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getControllerCall_ResourceDemandingStep() {
        return (EReference) this.controllerCallEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStateTransformingAction() {
        return this.stateTransformingActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGuardedAdaptationBehavior() {
        return this.guardedAdaptationBehaviorEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGuardedAdaptationBehavior_PreconditionURI() {
        return (EAttribute) this.guardedAdaptationBehaviorEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEnactAdaptationAction() {
        return this.enactAdaptationActionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEnactAdaptationAction_AdaptationStepURI() {
        return (EAttribute) this.enactAdaptationActionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CoreFactory getCoreFactory() {
        return (CoreFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on
     * any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated) {
            return;
        }
        this.isCreated = true;

        // Create classes and their features
        this.adaptationBehaviorEClass = this.createEClass(ADAPTATION_BEHAVIOR);
        this.createEReference(this.adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__ADAPTATION_STEPS);
        this.createEReference(this.adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__INVOLVED_ROLES);
        this.createEReference(this.adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__REPOSITORY);
        this.createEReference(this.adaptationBehaviorEClass, ADAPTATION_BEHAVIOR__TRANSIENT_STATE_PROFILE);

        this.adaptationActionEClass = this.createEClass(ADAPTATION_ACTION);
        this.createEReference(this.adaptationActionEClass, ADAPTATION_ACTION__ACTION);

        this.resourceDemandingActionEClass = this.createEClass(RESOURCE_DEMANDING_ACTION);
        this.createEAttribute(this.resourceDemandingActionEClass, RESOURCE_DEMANDING_ACTION__CONTROLLER_COMPLETION_URI);
        this.createEReference(this.resourceDemandingActionEClass, RESOURCE_DEMANDING_ACTION__CONTROLLER_CALLS);

        this.roleTypeEClass = this.createEClass(ROLE_TYPE);
        this.createEReference(this.roleTypeEClass, ROLE_TYPE__TYPE);
        this.createEReference(this.roleTypeEClass, ROLE_TYPE__ACTION);

        this.adaptationBehaviorRepositoryEClass = this.createEClass(ADAPTATION_BEHAVIOR_REPOSITORY);
        this.createEReference(this.adaptationBehaviorRepositoryEClass, ADAPTATION_BEHAVIOR_REPOSITORY__ACTIONS);

        this.controllerCallEClass = this.createEClass(CONTROLLER_CALL);
        this.createEReference(this.controllerCallEClass, CONTROLLER_CALL__COMPONENT);
        this.createEReference(this.controllerCallEClass, CONTROLLER_CALL__CALLED_SIGNATURE);
        this.createEReference(this.controllerCallEClass, CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);

        this.stateTransformingActionEClass = this.createEClass(STATE_TRANSFORMING_ACTION);

        this.guardedAdaptationBehaviorEClass = this.createEClass(GUARDED_ADAPTATION_BEHAVIOR);
        this.createEAttribute(this.guardedAdaptationBehaviorEClass, GUARDED_ADAPTATION_BEHAVIOR__PRECONDITION_URI);

        this.enactAdaptationActionEClass = this.createEClass(ENACT_ADAPTATION_ACTION);
        this.createEAttribute(this.enactAdaptationActionEClass, ENACT_ADAPTATION_ACTION__ADAPTATION_STEP_URI);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have
     * no affect on any invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized) {
            return;
        }
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Obtain other dependent packages
        final EntityPackage theEntityPackage = (EntityPackage) EPackage.Registry.INSTANCE
                .getEPackage(EntityPackage.eNS_URI);
        final EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage) EPackage.Registry.INSTANCE
                .getEPackage(EMFProfilePackage.eNS_URI);
        final InstancePackage theInstancePackage = (InstancePackage) EPackage.Registry.INSTANCE
                .getEPackage(InstancePackage.eNS_URI);
        final EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE
                .getEPackage(EcorePackage.eNS_URI);
        final RepositoryPackage theRepositoryPackage = (RepositoryPackage) EPackage.Registry.INSTANCE
                .getEPackage(RepositoryPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.adaptationBehaviorEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.adaptationActionEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.resourceDemandingActionEClass.getESuperTypes().add(this.getAdaptationAction());
        this.roleTypeEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.adaptationBehaviorRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.controllerCallEClass.getESuperTypes().add(theEntityPackage.getEntity());
        this.stateTransformingActionEClass.getESuperTypes().add(this.getAdaptationAction());
        this.guardedAdaptationBehaviorEClass.getESuperTypes().add(this.getAdaptationAction());
        this.enactAdaptationActionEClass.getESuperTypes().add(this.getAdaptationAction());

        // Initialize classes and features; add operations and parameters
        this.initEClass(this.adaptationBehaviorEClass, AdaptationBehavior.class, "AdaptationBehavior", !IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getAdaptationBehavior_AdaptationSteps(), this.getAdaptationAction(),
                this.getAdaptationAction_Action(), "adaptationSteps", null, 1, -1, AdaptationBehavior.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getAdaptationBehavior_InvolvedRoles(), this.getRoleType(), this.getRoleType_Action(),
                "involvedRoles", null, 1, -1, AdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getAdaptationBehavior_Repository(), this.getAdaptationBehaviorRepository(),
                this.getAdaptationBehaviorRepository_Actions(), "repository", null, 0, 1, AdaptationBehavior.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getAdaptationBehavior_TransientStateProfile(), theEMFProfilePackage.getProfile(), null,
                "transientStateProfile", null, 0, 1, AdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        final EOperation op = this.addEOperation(this.adaptationBehaviorEClass, this.ecorePackage.getEBoolean(),
                "execute", 1, 1, IS_UNIQUE, IS_ORDERED);
        this.addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);

        this.initEClass(this.adaptationActionEClass, AdaptationAction.class, "AdaptationAction", IS_ABSTRACT,
                !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getAdaptationAction_Action(), this.getAdaptationBehavior(),
                this.getAdaptationBehavior_AdaptationSteps(), "action", null, 0, 1, AdaptationAction.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.resourceDemandingActionEClass, ResourceDemandingAction.class, "ResourceDemandingAction",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getResourceDemandingAction_ControllerCompletionURI(), this.ecorePackage.getEString(),
                "controllerCompletionURI", null, 0, 1, ResourceDemandingAction.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getResourceDemandingAction_ControllerCalls(), this.getControllerCall(),
                this.getControllerCall_ResourceDemandingStep(), "controllerCalls", null, 1, -1,
                ResourceDemandingAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.roleTypeEClass, RoleType.class, "RoleType", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getRoleType_Type(), theEcorePackage.getEClass(), null, "type", null, 0, 1,
                RoleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getRoleType_Action(), this.getAdaptationBehavior(),
                this.getAdaptationBehavior_InvolvedRoles(), "action", null, 0, 1, RoleType.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
                IS_ORDERED);

        this.initEClass(this.adaptationBehaviorRepositoryEClass, AdaptationBehaviorRepository.class,
                "AdaptationBehaviorRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getAdaptationBehaviorRepository_Actions(), this.getAdaptationBehavior(),
                this.getAdaptationBehavior_Repository(), "actions", null, 0, -1, AdaptationBehaviorRepository.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.controllerCallEClass, ControllerCall.class, "ControllerCall", !IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        this.initEReference(this.getControllerCall_Component(), theRepositoryPackage.getBasicComponent(), null,
                "component", null, 1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getControllerCall_CalledSignature(), theRepositoryPackage.getOperationSignature(),
                null, "calledSignature", null, 1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getControllerCall_ResourceDemandingStep(), this.getResourceDemandingAction(),
                this.getResourceDemandingAction_ControllerCalls(), "resourceDemandingStep", null, 0, 1,
                ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.stateTransformingActionEClass, StateTransformingAction.class, "StateTransformingAction",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        this.initEClass(this.guardedAdaptationBehaviorEClass, GuardedAdaptationBehavior.class,
                "GuardedAdaptationBehavior", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getGuardedAdaptationBehavior_PreconditionURI(), this.ecorePackage.getEString(),
                "preconditionURI", null, 0, 1, GuardedAdaptationBehavior.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.enactAdaptationActionEClass, EnactAdaptationAction.class, "EnactAdaptationAction",
                !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        this.initEAttribute(this.getEnactAdaptationAction_AdaptationStepURI(), this.ecorePackage.getEString(),
                "adaptationStepURI", null, 1, 1, EnactAdaptationAction.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        this.createResource(eNS_URI);
    }

} // CorePackageImpl
