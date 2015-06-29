/**
 */
package violationstrategymappings;

import org.eclipse.emf.ecore.EObject;

import strategies.StrategyType;

import violations.ViolationType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Violation Strategy Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link violationstrategymappings.ViolationStrategyMapping#getViolation <em>Violation</em>}</li>
 *   <li>{@link violationstrategymappings.ViolationStrategyMapping#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link violationstrategymappings.ViolationStrategyMapping#getStrategyPriority <em>Strategy Priority</em>}</li>
 * </ul>
 *
 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMapping()
 * @model
 * @generated
 */
public interface ViolationStrategyMapping extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
	/**
	 * Returns the value of the '<em><b>Violation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Violation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Violation</em>' reference.
	 * @see #setViolation(ViolationType)
	 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMapping_Violation()
	 * @model required="true"
	 * @generated
	 */
	ViolationType getViolation();

	/**
	 * Sets the value of the '{@link violationstrategymappings.ViolationStrategyMapping#getViolation <em>Violation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Violation</em>' reference.
	 * @see #getViolation()
	 * @generated
	 */
	void setViolation(ViolationType value);

	/**
	 * Returns the value of the '<em><b>Strategies</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategies</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategies</em>' reference.
	 * @see #setStrategies(StrategyType)
	 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMapping_Strategies()
	 * @model required="true"
	 * @generated
	 */
	StrategyType getStrategies();

	/**
	 * Sets the value of the '{@link violationstrategymappings.ViolationStrategyMapping#getStrategies <em>Strategies</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategies</em>' reference.
	 * @see #getStrategies()
	 * @generated
	 */
	void setStrategies(StrategyType value);

	/**
	 * Returns the value of the '<em><b>Strategy Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy Priority</em>' attribute.
	 * @see #setStrategyPriority(int)
	 * @see violationstrategymappings.ViolationstrategymappingsPackage#getViolationStrategyMapping_StrategyPriority()
	 * @model
	 * @generated
	 */
	int getStrategyPriority();

	/**
	 * Sets the value of the '{@link violationstrategymappings.ViolationStrategyMapping#getStrategyPriority <em>Strategy Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy Priority</em>' attribute.
	 * @see #getStrategyPriority()
	 * @generated
	 */
	void setStrategyPriority(int value);

} // ViolationStrategyMapping
