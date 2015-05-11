/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Entry#getOperation <em>Operation</em>}</li>
 *   <li>{@link eurema.Entry#getIncoming <em>Incoming</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getEntry()
 * @model
 * @generated
 */
public interface Entry extends MegamodelElement {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Operation#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' container reference.
	 * @see #setOperation(Operation)
	 * @see eurema.EuremaPackage#getEntry_Operation()
	 * @see eurema.Operation#getEntries
	 * @model opposite="entries" required="true" transient="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link eurema.Entry#getOperation <em>Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' container reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Incoming</b></em>' reference list.
	 * The list contents are of type {@link eurema.Transition}.
	 * It is bidirectional and its opposite is '{@link eurema.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Incoming</em>' reference list.
	 * @see eurema.EuremaPackage#getEntry_Incoming()
	 * @see eurema.Transition#getTarget
	 * @model opposite="target" required="true"
	 * @generated
	 */
	EList<Transition> getIncoming();

} // Entry
