/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tactic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getAction <em>Action</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getTactic()
 * @model
 * @generated
 */
public interface Tactic extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * The default value is <code>"-1"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Priority of the reconfiguration rule represented as Integer. Values from 0 to n are the priority in a ruleset which determine the order of which a reconfiguration rule is executed. Lower values are higher priority, earlier execution. The value -1 means unspecified priority and is equal to the latest execution within the ruleset. The order in which multiple rules with unspecified priority are executed is non-deterministic.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getTactic_Priority()
	 * @model default="-1" required="true"
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getTactic_Condition()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ModelTransformation<?>> getCondition();

	/**
	 * Returns the value of the '<em><b>Action</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation}&lt;?>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Action</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Action</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getTactic_Action()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<ModelTransformation<?>> getAction();

} // Tactic
