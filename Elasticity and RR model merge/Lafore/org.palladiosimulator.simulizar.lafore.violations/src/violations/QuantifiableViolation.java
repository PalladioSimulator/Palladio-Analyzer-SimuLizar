/**
 */
package violations;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quantifiable Violation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link violations.QuantifiableViolation#getPercentage <em>Percentage</em>}</li>
 * </ul>
 *
 * @see violations.ViolationsPackage#getQuantifiableViolation()
 * @model
 * @generated
 */
public interface QuantifiableViolation extends Violation {
	/**
	 * Returns the value of the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Percentage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Percentage</em>' attribute.
	 * @see #setPercentage(int)
	 * @see violations.ViolationsPackage#getQuantifiableViolation_Percentage()
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel null='null'"
	 * @generated
	 */
	int getPercentage();

	/**
	 * Sets the value of the '{@link violations.QuantifiableViolation#getPercentage <em>Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Percentage</em>' attribute.
	 * @see #getPercentage()
	 * @generated
	 */
	void setPercentage(int value);

} // QuantifiableViolation
