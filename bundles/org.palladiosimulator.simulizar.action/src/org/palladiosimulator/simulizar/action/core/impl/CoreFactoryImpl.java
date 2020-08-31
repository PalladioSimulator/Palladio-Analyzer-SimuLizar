/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
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

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class CoreFactoryImpl extends EFactoryImpl implements CoreFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static CoreFactory init() {
        try {
            final CoreFactory theCoreFactory = (CoreFactory) EPackage.Registry.INSTANCE
                .getEFactory(CorePackage.eNS_URI);
            if (theCoreFactory != null) {
                return theCoreFactory;
            }
        } catch (final Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new CoreFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public CoreFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject create(final EClass eClass) {
        switch (eClass.getClassifierID()) {
        case CorePackage.ADAPTATION_BEHAVIOR_REPOSITORY:
            return this.createAdaptationBehaviorRepository();
        case CorePackage.ADAPTATION_BEHAVIOR:
            return this.createAdaptationBehavior();
        case CorePackage.NESTED_ADAPTATION_BEHAVIOR:
            return this.createNestedAdaptationBehavior();
        case CorePackage.STATE_TRANSFORMING_STEP:
            return this.createStateTransformingStep();
        case CorePackage.ENACT_ADAPTATION_STEP:
            return this.createEnactAdaptationStep();
        case CorePackage.RESOURCE_DEMANDING_STEP:
            return this.createResourceDemandingStep();
        case CorePackage.GUARDED_STEP:
            return this.createGuardedStep();
        case CorePackage.GUARDED_TRANSITION:
            return this.createGuardedTransition();
        case CorePackage.ROLE_TYPE:
            return this.createRoleType();
        case CorePackage.CONTROLLER_CALL:
            return this.createControllerCall();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object createFromString(final EDataType eDataType, final String initialValue) {
        switch (eDataType.getClassifierID()) {
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String convertToString(final EDataType eDataType, final Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AdaptationBehaviorRepository createAdaptationBehaviorRepository() {
        final AdaptationBehaviorRepositoryImpl adaptationBehaviorRepository = new AdaptationBehaviorRepositoryImpl();
        return adaptationBehaviorRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AdaptationBehavior createAdaptationBehavior() {
        final AdaptationBehaviorImpl adaptationBehavior = new AdaptationBehaviorImpl();
        return adaptationBehavior;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NestedAdaptationBehavior createNestedAdaptationBehavior() {
        final NestedAdaptationBehaviorImpl nestedAdaptationBehavior = new NestedAdaptationBehaviorImpl();
        return nestedAdaptationBehavior;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StateTransformingStep createStateTransformingStep() {
        final StateTransformingStepImpl stateTransformingStep = new StateTransformingStepImpl();
        return stateTransformingStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EnactAdaptationStep createEnactAdaptationStep() {
        final EnactAdaptationStepImpl enactAdaptationStep = new EnactAdaptationStepImpl();
        return enactAdaptationStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ResourceDemandingStep createResourceDemandingStep() {
        final ResourceDemandingStepImpl resourceDemandingStep = new ResourceDemandingStepImpl();
        return resourceDemandingStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GuardedStep createGuardedStep() {
        final GuardedStepImpl guardedStep = new GuardedStepImpl();
        return guardedStep;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GuardedTransition createGuardedTransition() {
        final GuardedTransitionImpl guardedTransition = new GuardedTransitionImpl();
        return guardedTransition;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RoleType createRoleType() {
        final RoleTypeImpl roleType = new RoleTypeImpl();
        return roleType;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ControllerCall createControllerCall() {
        final ControllerCallImpl controllerCall = new ControllerCallImpl();
        return controllerCall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CorePackage getCorePackage() {
        return (CorePackage) this.getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static CorePackage getPackage() {
        return CorePackage.eINSTANCE;
    }

} // CoreFactoryImpl
