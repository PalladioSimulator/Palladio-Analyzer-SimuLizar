/**
 */
package eurema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Information</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ExecutionInformation#getCount <em>Count</em>}</li>
 *   <li>{@link eurema.ExecutionInformation#getTime <em>Time</em>}</li>
 *   <li>{@link eurema.ExecutionInformation#getExecutable <em>Executable</em>}</li>
 *   <li>{@link eurema.ExecutionInformation#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getExecutionInformation()
 * @model
 * @generated
 */
public interface ExecutionInformation extends EObject {
	/**
	 * Returns the value of the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Count</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Count</em>' attribute.
	 * @see #setCount(int)
	 * @see eurema.EuremaPackage#getExecutionInformation_Count()
	 * @model
	 * @generated
	 */
	int getCount();

	/**
	 * Sets the value of the '{@link eurema.ExecutionInformation#getCount <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Count</em>' attribute.
	 * @see #getCount()
	 * @generated
	 */
	void setCount(int value);

	/**
	 * Returns the value of the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time</em>' attribute.
	 * @see #setTime(long)
	 * @see eurema.EuremaPackage#getExecutionInformation_Time()
	 * @model
	 * @generated
	 */
	long getTime();

	/**
	 * Sets the value of the '{@link eurema.ExecutionInformation#getTime <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time</em>' attribute.
	 * @see #getTime()
	 * @generated
	 */
	void setTime(long value);

	/**
	 * Returns the value of the '<em><b>Executable</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Executable#getExecInfo <em>Exec Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executable</em>' reference.
	 * @see #setExecutable(Executable)
	 * @see eurema.EuremaPackage#getExecutionInformation_Executable()
	 * @see eurema.Executable#getExecInfo
	 * @model opposite="execInfo" required="true"
	 * @generated
	 */
	Executable getExecutable();

	/**
	 * Sets the value of the '{@link eurema.ExecutionInformation#getExecutable <em>Executable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executable</em>' reference.
	 * @see #getExecutable()
	 * @generated
	 */
	void setExecutable(Executable value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.ExecutionContext#getExecInfos <em>Exec Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' container reference.
	 * @see #setContext(ExecutionContext)
	 * @see eurema.EuremaPackage#getExecutionInformation_Context()
	 * @see eurema.ExecutionContext#getExecInfos
	 * @model opposite="execInfos" required="true" transient="false"
	 * @generated
	 */
	ExecutionContext getContext();

	/**
	 * Sets the value of the '{@link eurema.ExecutionInformation#getContext <em>Context</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' container reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(ExecutionContext value);

} // ExecutionInformation
