/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation#getModelTransformation <em>Model Transformation</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getModelTransformation()
 * @model abstract="true" ExecutableTransformationElementBounds="org.eclipse.emf.ecore.EJavaObject"
 * @generated
 */
public interface ModelTransformation<ExecutableTransformationElement extends Object> extends EObject {
	/**
	 * Returns the value of the '<em><b>Model Transformation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Transformation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Transformation</em>' reference.
	 * @see #setModelTransformation(Object)
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getModelTransformation_ModelTransformation()
	 * @model kind="reference"
	 * @generated
	 */
	ExecutableTransformationElement getModelTransformation();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation#getModelTransformation <em>Model Transformation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Transformation</em>' reference.
	 * @see #getModelTransformation()
	 * @generated
	 */
	void setModelTransformation(ExecutableTransformationElement value);

} // ModelTransformation
