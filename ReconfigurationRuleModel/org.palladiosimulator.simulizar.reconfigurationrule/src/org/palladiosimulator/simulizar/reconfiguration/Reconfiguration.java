/**
 */
package org.palladiosimulator.simulizar.reconfiguration;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.reconfiguration.Reconfiguration#getStrategies <em>Strategies</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.reconfiguration.ReconfigurationPackage#getReconfiguration()
 * @model
 * @generated
 */
public interface Reconfiguration extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Strategies</b></em>' containment reference list.
	 * The list contents are of type {@link org.palladiosimulator.simulizar.reconfiguration.Strategy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' containment reference list.
	 * @see org.palladiosimulator.simulizar.reconfiguration.ReconfigurationPackage#getReconfiguration_Strategies()
	 * @model containment="true"
	 * @generated
	 */
	EList<Strategy> getStrategies();

} // Reconfiguration
