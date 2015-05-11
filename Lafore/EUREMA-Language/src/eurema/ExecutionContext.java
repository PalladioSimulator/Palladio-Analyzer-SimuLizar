/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ExecutionContext#getCurrent <em>Current</em>}</li>
 *   <li>{@link eurema.ExecutionContext#getExecInfos <em>Exec Infos</em>}</li>
 *   <li>{@link eurema.ExecutionContext#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link eurema.ExecutionContext#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.ExecutionContext#getExecuting <em>Executing</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getExecutionContext()
 * @model
 * @generated
 */
public interface ExecutionContext extends EObject {
	/**
	 * Returns the value of the '<em><b>Current</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Current</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Current</em>' reference.
	 * @see #setCurrent(Executable)
	 * @see eurema.EuremaPackage#getExecutionContext_Current()
	 * @model
	 * @generated
	 */
	Executable getCurrent();

	/**
	 * Sets the value of the '{@link eurema.ExecutionContext#getCurrent <em>Current</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Current</em>' reference.
	 * @see #getCurrent()
	 * @generated
	 */
	void setCurrent(Executable value);

	/**
	 * Returns the value of the '<em><b>Exec Infos</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.ExecutionInformation}.
	 * It is bidirectional and its opposite is '{@link eurema.ExecutionInformation#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exec Infos</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exec Infos</em>' containment reference list.
	 * @see eurema.EuremaPackage#getExecutionContext_ExecInfos()
	 * @see eurema.ExecutionInformation#getContext
	 * @model opposite="context" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ExecutionInformation> getExecInfos();

	/**
	 * Returns the value of the '<em><b>Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.RuntimeEnvironment#getExecutions <em>Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' container reference.
	 * @see #setEnvironment(RuntimeEnvironment)
	 * @see eurema.EuremaPackage#getExecutionContext_Environment()
	 * @see eurema.RuntimeEnvironment#getExecutions
	 * @model opposite="executions" required="true" transient="false"
	 * @generated
	 */
	RuntimeEnvironment getEnvironment();

	/**
	 * Sets the value of the '{@link eurema.ExecutionContext#getEnvironment <em>Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' container reference.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(RuntimeEnvironment value);

	/**
	 * Returns the value of the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uid</em>' attribute.
	 * @see #setUid(String)
	 * @see eurema.EuremaPackage#getExecutionContext_Uid()
	 * @model required="true"
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eurema.ExecutionContext#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

	/**
	 * Returns the value of the '<em><b>Executing</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.MegamodelModule#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executing</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executing</em>' reference.
	 * @see #setExecuting(MegamodelModule)
	 * @see eurema.EuremaPackage#getExecutionContext_Executing()
	 * @see eurema.MegamodelModule#getContext
	 * @model opposite="context" required="true"
	 * @generated
	 */
	MegamodelModule getExecuting();

	/**
	 * Sets the value of the '{@link eurema.ExecutionContext#getExecuting <em>Executing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Executing</em>' reference.
	 * @see #getExecuting()
	 * @generated
	 */
	void setExecuting(MegamodelModule value);

} // ExecutionContext
