/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.palladiosimulator.simulizar.action.core.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CoreFactory init() {
		try {
			CoreFactory theCoreFactory = (CoreFactory) EPackage.Registry.INSTANCE.getEFactory(CorePackage.eNS_URI);
			if (theCoreFactory != null) {
				return theCoreFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CoreFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY:
			return createAdaptationBehaviorRepository();
		case CorePackage.ADAPTATION_BEHAVIOR:
			return createAdaptationBehavior();
		case CorePackage.NESTED_ADAPTATION_BEHAVIOR:
			return createNestedAdaptationBehavior();
		case CorePackage.STATE_TRANSFORMING_STEP:
			return createStateTransformingStep();
		case CorePackage.ENACT_ADAPTATION_STEP:
			return createEnactAdaptationStep();
		case CorePackage.RESOURCE_DEMANDING_STEP:
			return createResourceDemandingStep();
		case CorePackage.GUARDED_STEP:
			return createGuardedStep();
		case CorePackage.GUARDED_TRANSITION:
			return createGuardedTransition();
		case CorePackage.ROLE_TYPE:
			return createRoleType();
		case CorePackage.CONTROLLER_CALL:
			return createControllerCall();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationBehaviorRepository createAdaptationBehaviorRepository() {
		AdaptationBehaviorRepositoryImpl adaptationBehaviorRepository = new AdaptationBehaviorRepositoryImpl();
		return adaptationBehaviorRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AdaptationBehavior createAdaptationBehavior() {
		AdaptationBehaviorImpl adaptationBehavior = new AdaptationBehaviorImpl();
		return adaptationBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NestedAdaptationBehavior createNestedAdaptationBehavior() {
		NestedAdaptationBehaviorImpl nestedAdaptationBehavior = new NestedAdaptationBehaviorImpl();
		return nestedAdaptationBehavior;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StateTransformingStep createStateTransformingStep() {
		StateTransformingStepImpl stateTransformingStep = new StateTransformingStepImpl();
		return stateTransformingStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnactAdaptationStep createEnactAdaptationStep() {
		EnactAdaptationStepImpl enactAdaptationStep = new EnactAdaptationStepImpl();
		return enactAdaptationStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceDemandingStep createResourceDemandingStep() {
		ResourceDemandingStepImpl resourceDemandingStep = new ResourceDemandingStepImpl();
		return resourceDemandingStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GuardedStep createGuardedStep() {
		GuardedStepImpl guardedStep = new GuardedStepImpl();
		return guardedStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GuardedTransition createGuardedTransition() {
		GuardedTransitionImpl guardedTransition = new GuardedTransitionImpl();
		return guardedTransition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RoleType createRoleType() {
		RoleTypeImpl roleType = new RoleTypeImpl();
		return roleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ControllerCall createControllerCall() {
		ControllerCallImpl controllerCall = new ControllerCallImpl();
		return controllerCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CorePackage getCorePackage() {
		return (CorePackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CorePackage getPackage() {
		return CorePackage.eINSTANCE;
	}

} //CoreFactoryImpl
