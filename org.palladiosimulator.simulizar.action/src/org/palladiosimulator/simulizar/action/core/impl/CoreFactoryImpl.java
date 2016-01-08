/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.ControllerCall;
import org.palladiosimulator.simulizar.action.core.CoreFactory;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.EnactAdaptationAction;
import org.palladiosimulator.simulizar.action.core.GuardedAction;
import org.palladiosimulator.simulizar.action.core.GuardedTransition;
import org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.ResourceDemandingAction;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.core.StateTransformingAction;

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
        case CorePackage.STATE_TRANSFORMING_ACTION:
            return this.createStateTransformingAction();
        case CorePackage.ENACT_ADAPTATION_ACTION:
            return this.createEnactAdaptationAction();
        case CorePackage.RESOURCE_DEMANDING_ACTION:
            return this.createResourceDemandingAction();
        case CorePackage.GUARDED_ACTION:
            return this.createGuardedAction();
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
    public StateTransformingAction createStateTransformingAction() {
        final StateTransformingActionImpl stateTransformingAction = new StateTransformingActionImpl();
        return stateTransformingAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EnactAdaptationAction createEnactAdaptationAction() {
        final EnactAdaptationActionImpl enactAdaptationAction = new EnactAdaptationActionImpl();
        return enactAdaptationAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public ResourceDemandingAction createResourceDemandingAction() {
        final ResourceDemandingActionImpl resourceDemandingAction = new ResourceDemandingActionImpl();
        return resourceDemandingAction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public GuardedAction createGuardedAction() {
        final GuardedActionImpl guardedAction = new GuardedActionImpl();
        return guardedAction;
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
