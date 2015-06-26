/**
 */
package violations;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Violations Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link violations.RuntimeViolationsModel#getViolations <em>Violations</em>}</li>
 * </ul>
 *
 * @see violations.ViolationsPackage#getRuntimeViolationsModel()
 * @model
 * @generated
 */
public interface RuntimeViolationsModel extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Violations</b></em>' containment reference list.
	 * The list contents are of type {@link violations.Violation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Violations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Violations</em>' containment reference list.
	 * @see violations.ViolationsPackage#getRuntimeViolationsModel_Violations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Violation> getViolations();

} // RuntimeViolationsModel
