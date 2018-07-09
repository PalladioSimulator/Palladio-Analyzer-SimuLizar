/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Guarded Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.GuardedStep#getGuardedTransitions <em>Guarded Transitions</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedStep()
 * @model
 * @generated
 */
public interface GuardedStep extends AdaptationStep {
	/**
	 * Returns the value of the '<em><b>Guarded Transitions</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.action.core.GuardedTransition}.
	 * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedStep <em>Guarded Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Guarded Transitions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Guarded Transitions</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedStep_GuardedTransitions()
	 * @see org.palladiosimulator.simulizar.action.core.GuardedTransition#getGuardedStep
	 * @model opposite="guardedStep" containment="true"
	 * @generated
	 */
	EList<GuardedTransition> getGuardedTransitions();

} // GuardedStep
