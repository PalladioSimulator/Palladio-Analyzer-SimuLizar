/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Demanding Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls <em>Controller Calls</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getResourceDemandingStep()
 * @model
 * @generated
 */
public interface ResourceDemandingStep extends AdaptationStep {
	/**
     * Returns the value of the '<em><b>Controller Calls</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.action.core.ControllerCall}.
     * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep <em>Resource Demanding Step</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller Calls</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Controller Calls</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getResourceDemandingStep_ControllerCalls()
     * @see org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep
     * @model opposite="resourceDemandingStep" containment="true" required="true"
     * @generated
     */
	EList<ControllerCall> getControllerCalls();

} // ResourceDemandingStep
