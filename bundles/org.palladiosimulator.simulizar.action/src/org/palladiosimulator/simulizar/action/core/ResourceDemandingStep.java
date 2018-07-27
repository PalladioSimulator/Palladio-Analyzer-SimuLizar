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
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCompletionURI <em>Controller Completion URI</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls <em>Controller Calls</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getResourceDemandingStep()
 * @model
 * @generated
 */
public interface ResourceDemandingStep extends AdaptationStep {
	/**
	 * Returns the value of the '<em><b>Controller Completion URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Controller Completion URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Controller Completion URI</em>' attribute.
	 * @see #setControllerCompletionURI(String)
	 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getResourceDemandingStep_ControllerCompletionURI()
	 * @model
	 * @generated
	 */
	String getControllerCompletionURI();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCompletionURI <em>Controller Completion URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Controller Completion URI</em>' attribute.
	 * @see #getControllerCompletionURI()
	 * @generated
	 */
	void setControllerCompletionURI(String value);

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
