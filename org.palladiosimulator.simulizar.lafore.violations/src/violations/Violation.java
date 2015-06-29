/**
 */
package violations;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Violation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link violations.Violation#getViolationType <em>Violation Type</em>}</li>
 * </ul>
 *
 * @see violations.ViolationsPackage#getViolation()
 * @model abstract="true"
 * @generated
 */
public interface Violation extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
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

} // Violation
