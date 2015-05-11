/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Operation#getEntries <em>Entries</em>}</li>
 *   <li>{@link eurema.Operation#getExits <em>Exits</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getOperation()
 * @model abstract="true"
 * @generated
 */
public interface Operation extends Executable {
	/**
	 * Returns the value of the '<em><b>Entries</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Entry}.
	 * It is bidirectional and its opposite is '{@link eurema.Entry#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entries</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entries</em>' containment reference list.
	 * @see eurema.EuremaPackage#getOperation_Entries()
	 * @see eurema.Entry#getOperation
	 * @model opposite="operation" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Entry> getEntries();

	/**
	 * Returns the value of the '<em><b>Exits</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Exit}.
	 * It is bidirectional and its opposite is '{@link eurema.Exit#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exits</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exits</em>' containment reference list.
	 * @see eurema.EuremaPackage#getOperation_Exits()
	 * @see eurema.Exit#getOperation
	 * @model opposite="operation" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Exit> getExits();

} // Operation
