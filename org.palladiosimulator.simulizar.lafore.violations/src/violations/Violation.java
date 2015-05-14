/**
 */
package violations;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Violation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link violations.Violation#getViolationType <em>Violation Type</em>}</li>
 *   <li>{@link violations.Violation#getViolatedPercentage <em>Violated Percentage</em>}</li>
 * </ul>
 * </p>
 *
 * @see violations.ViolationsPackage#getViolation()
 * @model
 * @generated
 */
public interface Violation extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Violation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Violation Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Violation Type</em>' reference.
	 * @see #setViolationType(ViolationType)
	 * @see violations.ViolationsPackage#getViolation_ViolationType()
	 * @model required="true"
	 * @generated
	 */
	ViolationType getViolationType();

	/**
	 * Sets the value of the '{@link violations.Violation#getViolationType <em>Violation Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Violation Type</em>' reference.
	 * @see #getViolationType()
	 * @generated
	 */
	void setViolationType(ViolationType value);

	/**
	 * Returns the value of the '<em><b>Violated Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Violated Percentage</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Violated Percentage</em>' attribute.
	 * @see #setViolatedPercentage(double)
	 * @see violations.ViolationsPackage#getViolation_ViolatedPercentage()
	 * @model
	 * @generated
	 */
	double getViolatedPercentage();

	/**
	 * Sets the value of the '{@link violations.Violation#getViolatedPercentage <em>Violated Percentage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Violated Percentage</em>' attribute.
	 * @see #getViolatedPercentage()
	 * @generated
	 */
	void setViolatedPercentage(double value);

} // Violation
