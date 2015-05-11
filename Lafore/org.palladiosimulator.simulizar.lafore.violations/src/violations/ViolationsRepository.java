/**
 */
package violations;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Repository</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link violations.ViolationsRepository#getViolationTypes <em>Violation Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see violations.ViolationsPackage#getViolationsRepository()
 * @model
 * @generated
 */
public interface ViolationsRepository extends EObject, Entity {
	/**
	 * Returns the value of the '<em><b>Violation Types</b></em>' containment reference list.
	 * The list contents are of type {@link violations.ViolationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Violation Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Violation Types</em>' containment reference list.
	 * @see violations.ViolationsPackage#getViolationsRepository_ViolationTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<ViolationType> getViolationTypes();

} // ViolationsRepository
