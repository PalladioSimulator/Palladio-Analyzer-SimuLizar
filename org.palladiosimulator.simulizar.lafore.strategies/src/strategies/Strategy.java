/**
 */
package strategies;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link strategies.Strategy#getStrategyType <em>Strategy Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see strategies.StrategiesPackage#getStrategy()
 * @model
 * @generated
 */
public interface Strategy extends EObject {
	/**
	 * Returns the value of the '<em><b>Strategy Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy Type</em>' reference.
	 * @see #setStrategyType(StrategyType)
	 * @see strategies.StrategiesPackage#getStrategy_StrategyType()
	 * @model required="true"
	 * @generated
	 */
	StrategyType getStrategyType();

	/**
	 * Sets the value of the '{@link strategies.Strategy#getStrategyType <em>Strategy Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy Type</em>' reference.
	 * @see #getStrategyType()
	 * @generated
	 */
	void setStrategyType(StrategyType value);

} // Strategy
