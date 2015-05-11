/**
 */
package eurema;

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
 *   <li>{@link eurema.Repository#getSoftware <em>Software</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getRepository()
 * @model
 * @generated
 */
public interface Repository extends EObject {
	/**
	 * Returns the value of the '<em><b>Software</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Software}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software</em>' containment reference list.
	 * @see eurema.EuremaPackage#getRepository_Software()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Software> getSoftware();

} // Repository
