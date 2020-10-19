/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage
 * @generated
 */
public interface CoreFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    CoreFactory eINSTANCE = org.palladiosimulator.simulizar.action.core.impl.CoreFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Adaptation Behavior Repository</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Adaptation Behavior Repository</em>'.
     * @generated
     */
    AdaptationBehaviorRepository createAdaptationBehaviorRepository();

    /**
     * Returns a new object of class '<em>Adaptation Behavior</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Adaptation Behavior</em>'.
     * @generated
     */
    AdaptationBehavior createAdaptationBehavior();

    /**
     * Returns a new object of class '<em>Nested Adaptation Behavior</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Nested Adaptation Behavior</em>'.
     * @generated
     */
    NestedAdaptationBehavior createNestedAdaptationBehavior();

    /**
     * Returns a new object of class '<em>State Transforming Step</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>State Transforming Step</em>'.
     * @generated
     */
    StateTransformingStep createStateTransformingStep();

    /**
     * Returns a new object of class '<em>Enact Adaptation Step</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Enact Adaptation Step</em>'.
     * @generated
     */
    EnactAdaptationStep createEnactAdaptationStep();

    /**
     * Returns a new object of class '<em>Resource Demanding Step</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Resource Demanding Step</em>'.
     * @generated
     */
    ResourceDemandingStep createResourceDemandingStep();

    /**
     * Returns a new object of class '<em>Guarded Step</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Guarded Step</em>'.
     * @generated
     */
    GuardedStep createGuardedStep();

    /**
     * Returns a new object of class '<em>Guarded Transition</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Guarded Transition</em>'.
     * @generated
     */
    GuardedTransition createGuardedTransition();

    /**
     * Returns a new object of class '<em>Role Type</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Role Type</em>'.
     * @generated
     */
    RoleType createRoleType();

    /**
     * Returns a new object of class '<em>Controller Call</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Controller Call</em>'.
     * @generated
     */
    ControllerCall createControllerCall();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    CorePackage getCorePackage();

} // CoreFactory
