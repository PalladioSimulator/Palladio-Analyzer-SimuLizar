/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.pcm.PcmPackage;
import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;
import de.uka.ipd.sdq.pcm.repository.RepositoryPackage;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.modelversioning.emfprofile.EMFProfilePackage;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CoreFactory;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingStep;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.impl.InstancePackageImpl;
import org.palladiosimulator.simulizar.action.mapping.MappingPackage;
import org.palladiosimulator.simulizar.action.mapping.impl.MappingPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CorePackageImpl extends EPackageImpl implements CorePackage {
	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actionEClass = null;

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
	private EClass resourceDemandingStepEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass roleTypeEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private EClass actionRepositoryEClass = null;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#eNS_URI
     * @see #init()
     * @generated
     */
	private CorePackageImpl() {
        super(eNS_URI, CoreFactory.eINSTANCE);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static boolean isInited = false;

	/**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     * 
     * <p>This method is used to initialize {@link CorePackage#eINSTANCE} when that field is accessed.
     * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
	public static CorePackage init() {
        if (isInited) return (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);

        // Obtain or create and register package
        CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CorePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EMFProfilePackage.eINSTANCE.eClass();
        PcmPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        MappingPackageImpl theMappingPackage = (MappingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI) instanceof MappingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MappingPackage.eNS_URI) : MappingPackage.eINSTANCE);
        InstancePackageImpl theInstancePackage = (InstancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) : InstancePackage.eINSTANCE);

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
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAction() {
        return actionEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAction_AdaptationSteps() {
        return (EReference)actionEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAction_InvolvedRoles() {
        return (EReference)actionEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAction_Repository() {
        return (EReference)actionEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAction_TransientStateProfile() {
        return (EReference)actionEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getAdaptationStep() {
        return adaptationStepEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAdaptationStep_ControllerCompletionURI() {
        return (EAttribute)adaptationStepEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAdaptationStep_AdaptationStepURI() {
        return (EAttribute)adaptationStepEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EAttribute getAdaptationStep_PreconditionURI() {
        return (EAttribute)adaptationStepEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getAdaptationStep_Action() {
        return (EReference)adaptationStepEClass.getEStructuralFeatures().get(3);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getResourceDemandingStep() {
        return resourceDemandingStepEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getResourceDemandingStep_ControllerCalls() {
        return (EReference)resourceDemandingStepEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getRoleType() {
        return roleTypeEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRoleType_Type() {
        return (EReference)roleTypeEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getRoleType_Action() {
        return (EReference)roleTypeEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getActionRepository() {
        return actionRepositoryEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getActionRepository_Actions() {
        return (EReference)actionRepositoryEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getControllerCall() {
        return controllerCallEClass;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getControllerCall_Component() {
        return (EReference)controllerCallEClass.getEStructuralFeatures().get(0);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getControllerCall_CalledSignature() {
        return (EReference)controllerCallEClass.getEStructuralFeatures().get(1);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EReference getControllerCall_ResourceDemandingStep() {
        return (EReference)controllerCallEClass.getEStructuralFeatures().get(2);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public CoreFactory getCoreFactory() {
        return (CoreFactory)getEFactoryInstance();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isCreated = false;

	/**
     * Creates the meta-model objects for the package.  This method is
     * guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void createPackageContents() {
        if (isCreated) return;
        isCreated = true;

        // Create classes and their features
        actionEClass = createEClass(ACTION);
        createEReference(actionEClass, ACTION__ADAPTATION_STEPS);
        createEReference(actionEClass, ACTION__INVOLVED_ROLES);
        createEReference(actionEClass, ACTION__REPOSITORY);
        createEReference(actionEClass, ACTION__TRANSIENT_STATE_PROFILE);

        adaptationStepEClass = createEClass(ADAPTATION_STEP);
        createEAttribute(adaptationStepEClass, ADAPTATION_STEP__CONTROLLER_COMPLETION_URI);
        createEAttribute(adaptationStepEClass, ADAPTATION_STEP__ADAPTATION_STEP_URI);
        createEAttribute(adaptationStepEClass, ADAPTATION_STEP__PRECONDITION_URI);
        createEReference(adaptationStepEClass, ADAPTATION_STEP__ACTION);

        resourceDemandingStepEClass = createEClass(RESOURCE_DEMANDING_STEP);
        createEReference(resourceDemandingStepEClass, RESOURCE_DEMANDING_STEP__CONTROLLER_CALLS);

        roleTypeEClass = createEClass(ROLE_TYPE);
        createEReference(roleTypeEClass, ROLE_TYPE__TYPE);
        createEReference(roleTypeEClass, ROLE_TYPE__ACTION);

        actionRepositoryEClass = createEClass(ACTION_REPOSITORY);
        createEReference(actionRepositoryEClass, ACTION_REPOSITORY__ACTIONS);

        controllerCallEClass = createEClass(CONTROLLER_CALL);
        createEReference(controllerCallEClass, CONTROLLER_CALL__COMPONENT);
        createEReference(controllerCallEClass, CONTROLLER_CALL__CALLED_SIGNATURE);
        createEReference(controllerCallEClass, CONTROLLER_CALL__RESOURCE_DEMANDING_STEP);
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private boolean isInitialized = false;

	/**
     * Complete the initialization of the package and its meta-model.  This
     * method is guarded to have no affect on any invocation but its first.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void initializePackageContents() {
        if (isInitialized) return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        EntityPackage theEntityPackage = (EntityPackage)EPackage.Registry.INSTANCE.getEPackage(EntityPackage.eNS_URI);
        EMFProfilePackage theEMFProfilePackage = (EMFProfilePackage)EPackage.Registry.INSTANCE.getEPackage(EMFProfilePackage.eNS_URI);
        InstancePackage theInstancePackage = (InstancePackage)EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        RepositoryPackage theRepositoryPackage = (RepositoryPackage)EPackage.Registry.INSTANCE.getEPackage(RepositoryPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        actionEClass.getESuperTypes().add(theEntityPackage.getEntity());
        adaptationStepEClass.getESuperTypes().add(theEntityPackage.getEntity());
        resourceDemandingStepEClass.getESuperTypes().add(this.getAdaptationStep());
        roleTypeEClass.getESuperTypes().add(theEntityPackage.getEntity());
        actionRepositoryEClass.getESuperTypes().add(theEntityPackage.getEntity());
        controllerCallEClass.getESuperTypes().add(theEntityPackage.getEntity());

        // Initialize classes and features; add operations and parameters
        initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAction_AdaptationSteps(), this.getAdaptationStep(), this.getAdaptationStep_Action(), "adaptationSteps", null, 1, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAction_InvolvedRoles(), this.getRoleType(), this.getRoleType_Action(), "involvedRoles", null, 1, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAction_Repository(), this.getActionRepository(), this.getActionRepository_Actions(), "repository", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAction_TransientStateProfile(), theEMFProfilePackage.getProfile(), null, "transientStateProfile", null, 0, 1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        EOperation op = addEOperation(actionEClass, ecorePackage.getEBoolean(), "execute", 1, 1, IS_UNIQUE, IS_ORDERED);
        addEParameter(op, theInstancePackage.getRoleSet(), "affectedRoleSet", 1, 1, IS_UNIQUE, IS_ORDERED);

        initEClass(adaptationStepEClass, AdaptationStep.class, "AdaptationStep", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAdaptationStep_ControllerCompletionURI(), ecorePackage.getEString(), "controllerCompletionURI", null, 0, 1, AdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAdaptationStep_AdaptationStepURI(), ecorePackage.getEString(), "adaptationStepURI", null, 1, 1, AdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getAdaptationStep_PreconditionURI(), ecorePackage.getEString(), "preconditionURI", null, 0, 1, AdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getAdaptationStep_Action(), this.getAction(), this.getAction_AdaptationSteps(), "action", null, 0, 1, AdaptationStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(resourceDemandingStepEClass, ResourceDemandingStep.class, "ResourceDemandingStep", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getResourceDemandingStep_ControllerCalls(), this.getControllerCall(), this.getControllerCall_ResourceDemandingStep(), "controllerCalls", null, 1, -1, ResourceDemandingStep.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(roleTypeEClass, RoleType.class, "RoleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getRoleType_Type(), theEcorePackage.getEClass(), null, "type", null, 0, 1, RoleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getRoleType_Action(), this.getAction(), this.getAction_InvolvedRoles(), "action", null, 0, 1, RoleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(actionRepositoryEClass, ActionRepository.class, "ActionRepository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getActionRepository_Actions(), this.getAction(), this.getAction_Repository(), "actions", null, 0, -1, ActionRepository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(controllerCallEClass, ControllerCall.class, "ControllerCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getControllerCall_Component(), theRepositoryPackage.getBasicComponent(), null, "component", null, 1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getControllerCall_CalledSignature(), theRepositoryPackage.getOperationSignature(), null, "calledSignature", null, 1, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getControllerCall_ResourceDemandingStep(), this.getResourceDemandingStep(), this.getResourceDemandingStep_ControllerCalls(), "resourceDemandingStep", null, 0, 1, ControllerCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create resource
        createResource(eNS_URI);
    }

} //CorePackageImpl
