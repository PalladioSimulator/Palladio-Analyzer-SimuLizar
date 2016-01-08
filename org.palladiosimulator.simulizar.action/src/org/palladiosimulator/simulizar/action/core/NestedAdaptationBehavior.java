/**
 */
package org.palladiosimulator.simulizar.action.core;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Nested Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior#getGuardedTransition
 * <em>Guarded Transition</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getNestedAdaptationBehavior()
 * @model
 * @generated
 */
public interface NestedAdaptationBehavior extends AbstractAdaptationBehavior {
    /**
     * Returns the value of the '<em><b>Guarded Transition</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getNestedAdaptationBehavior
     * <em>Nested Adaptation Behavior</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Guarded Transition</em>' container reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Guarded Transition</em>' container reference.
     * @see #setGuardedTransition(GuardedTransition)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getNestedAdaptationBehavior_GuardedTransition()
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getNestedAdaptationBehavior
     * @model opposite="nestedAdaptationBehavior" transient="false"
     * @generated
     */
    GuardedTransition getGuardedTransition();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.NestedAdaptationBehavior#getGuardedTransition
     * <em>Guarded Transition</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Guarded Transition</em>' container reference.
     * @see #getGuardedTransition()
     * @generated
     */
    void setGuardedTransition(GuardedTransition value);

} // NestedAdaptationBehavior
