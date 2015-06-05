/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getConditionCheck <em>Condition Check</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getTransformationAction <em>Transformation Action</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRule()
 * @model
 * @generated
 */
public interface ReconfigurationRule extends EObject {
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
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRule_Priority()
     * @model default="-1" required="true"
     * @generated
     */
    int getPriority();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getPriority <em>Priority</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Priority</em>' attribute.
     * @see #getPriority()
     * @generated
     */
    void setPriority(int value);

    /**
     * Returns the value of the '<em><b>Condition Check</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation}&lt;?>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Condition Check</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Condition Check</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRule_ConditionCheck()
     * @model containment="true" required="true"
     * @generated
     */
    EList<ModelTransformation<?>> getConditionCheck();

    /**
     * Returns the value of the '<em><b>Transformation Action</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation}&lt;?>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Transformation Action</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Transformation Action</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRule_TransformationAction()
     * @model containment="true" required="true"
     * @generated
     */
    EList<ModelTransformation<?>> getTransformationAction();

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRule_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // ReconfigurationRule
