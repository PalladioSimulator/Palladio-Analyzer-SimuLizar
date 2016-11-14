/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.common.util.EList;

import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getSlos <em>Slos</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getTactics <em>Tactics</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getStrategy()
 * @model
 * @generated
 */
public interface Strategy extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Slos</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Slos</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Slos</em>' reference.
	 * @see #setSlos(ServiceLevelObjectiveRepository)
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getStrategy_Slos()
	 * @model required="true"
	 * @generated
	 */
	ServiceLevelObjectiveRepository getSlos();

	/**
	 * Sets the value of the '{@link org.palladiosimulator.simulizar.reconfigurationrule.Strategy#getSlos <em>Slos</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Slos</em>' reference.
	 * @see #getSlos()
	 * @generated
	 */
	void setSlos(ServiceLevelObjectiveRepository value);

	/**
	 * Returns the value of the '<em><b>Tactics</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.reconfigurationrule.Tactic}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tactics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tactics</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage#getStrategy_Tactics()
	 * @model containment="true"
	 * @generated
	 */
	EList<Tactic> getTactics();

} // Strategy
