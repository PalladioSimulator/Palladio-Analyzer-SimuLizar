/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Guarded Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.GuardedAction#getGuardedTransitions
 * <em>Guarded Transitions</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedAction()
 * @model
 * @generated
 */
public interface GuardedAction extends AdaptationAction {
    /**
     * Returns the value of the '<em><b>Guarded Transitions</b></em>' containment reference list.
     * The list contents are of type
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition}. It is bidirectional
     * and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedAction
     * <em>Guarded Action</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Guarded Transitions</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Guarded Transitions</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedAction_GuardedTransitions()
     * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedAction
     * @model opposite="guardedAction" containment="true"
     * @generated
     */
    EList<GuardedTransition> getGuardedTransitions();

} // GuardedAction
