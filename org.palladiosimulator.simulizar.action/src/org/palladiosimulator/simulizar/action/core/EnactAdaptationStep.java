/**
 */
package org.palladiosimulator.simulizar.action.core;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enact Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.EnactAdaptationStep#getAdaptationStepURI <em>Adaptation Step URI</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getEnactAdaptationStep()
 * @model
 * @generated
 */
public interface EnactAdaptationStep extends AdaptationStep {
	/**
	 * Returns the value of the '<em><b>Adaptation Step URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adaptation Step URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adaptation Step URI</em>' attribute.
	 * @see #setAdaptationStepURI(String)
	 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getEnactAdaptationStep_AdaptationStepURI()
	 * @model required="true"
	 * @generated
	 */
	String getAdaptationStepURI();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.action.core.EnactAdaptationStep#getAdaptationStepURI <em>Adaptation Step URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adaptation Step URI</em>' attribute.
	 * @see #getAdaptationStepURI()
	 * @generated
	 */
	void setAdaptationStepURI(String value);

} // EnactAdaptationStep
