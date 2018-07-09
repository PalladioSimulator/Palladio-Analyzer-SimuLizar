/**
 */
package org.palladiosimulator.simulizar.action.parameter;

import org.eclipse.emf.ecore.EObject;

import org.palladiosimulator.pcm.core.entity.Entity;

import org.palladiosimulator.pcm.parameter.VariableUsage;

import org.palladiosimulator.simulizar.action.core.ControllerCall;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Controller Call Input Variable Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getVariableUsage <em>Variable Usage</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getCorrespondingControllerCall <em>Corresponding Controller Call</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection <em>Containing Collection</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsage()
 * @model
 * @generated
 */
public interface ControllerCallInputVariableUsage extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Variable Usage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Usage</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Usage</em>' containment reference.
	 * @see #setVariableUsage(VariableUsage)
	 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsage_VariableUsage()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableUsage getVariableUsage();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getVariableUsage <em>Variable Usage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Usage</em>' containment reference.
	 * @see #getVariableUsage()
	 * @generated
	 */
	void setVariableUsage(VariableUsage value);

	/**
	 * Returns the value of the '<em><b>Corresponding Controller Call</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Corresponding Controller Call</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Corresponding Controller Call</em>' reference.
	 * @see #setCorrespondingControllerCall(ControllerCall)
	 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsage_CorrespondingControllerCall()
	 * @model required="true"
	 * @generated
	 */
	ControllerCall getCorrespondingControllerCall();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getCorrespondingControllerCall <em>Corresponding Controller Call</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Corresponding Controller Call</em>' reference.
	 * @see #getCorrespondingControllerCall()
	 * @generated
	 */
	void setCorrespondingControllerCall(ControllerCall value);

	/**
	 * Returns the value of the '<em><b>Containing Collection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection#getControllerCallInputVariableUsages <em>Controller Call Input Variable Usages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Containing Collection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Containing Collection</em>' container reference.
	 * @see #setContainingCollection(ControllerCallInputVariableUsageCollection)
	 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage#getControllerCallInputVariableUsage_ContainingCollection()
	 * @see org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsageCollection#getControllerCallInputVariableUsages
	 * @model opposite="controllerCallInputVariableUsages" transient="false"
	 * @generated
	 */
	ControllerCallInputVariableUsageCollection getContainingCollection();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.action.parameter.ControllerCallInputVariableUsage#getContainingCollection <em>Containing Collection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Containing Collection</em>' container reference.
	 * @see #getContainingCollection()
	 * @generated
	 */
	void setContainingCollection(ControllerCallInputVariableUsageCollection value);

} // ControllerCallInputVariableUsage
