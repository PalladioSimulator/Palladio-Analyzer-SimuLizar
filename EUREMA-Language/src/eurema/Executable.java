/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Executable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Executable#getExecInfo <em>Exec Info</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getExecutable()
 * @model abstract="true"
 * @generated
 */
public interface Executable extends MegamodelElement {
	/**
	 * Returns the value of the '<em><b>Exec Info</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.ExecutionInformation#getExecutable <em>Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Info</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Info</em>' reference.
	 * @see #setExecInfo(ExecutionInformation)
	 * @see eurema.EuremaPackage#getExecutable_ExecInfo()
	 * @see eurema.ExecutionInformation#getExecutable
	 * @model opposite="executable"
	 * @generated
	 */
	ExecutionInformation getExecInfo();

	/**
	 * Sets the value of the '{@link eurema.Executable#getExecInfo <em>Exec Info</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exec Info</em>' reference.
	 * @see #getExecInfo()
	 * @generated
	 */
	void setExecInfo(ExecutionInformation value);

} // Executable
