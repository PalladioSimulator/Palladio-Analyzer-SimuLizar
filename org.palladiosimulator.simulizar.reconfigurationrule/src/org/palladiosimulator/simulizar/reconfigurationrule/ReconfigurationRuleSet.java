/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration Rule Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getRules <em>Rules</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRuleSet()
 * @model
 * @generated
 */
public interface ReconfigurationRuleSet extends EObject {
    /**
     * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRule}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Rules</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRuleSet_Rules()
     * @model containment="true"
     * @generated
     */
    EList<ReconfigurationRule> getRules();

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
     * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage#getReconfigurationRuleSet_Name()
     * @model required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationRuleSet#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // ReconfigurationRuleSet
