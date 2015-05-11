/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Module Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.SoftwareModuleTrigger#isIsNative <em>Is Native</em>}</li>
 *   <li>{@link eurema.SoftwareModuleTrigger#getExecutionMethod <em>Execution Method</em>}</li>
 *   <li>{@link eurema.SoftwareModuleTrigger#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getSoftwareModuleTrigger()
 * @model
 * @generated
 */
public interface SoftwareModuleTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Is Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Native</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Native</em>' attribute.
	 * @see #setIsNative(boolean)
	 * @see eurema.EuremaPackage#getSoftwareModuleTrigger_IsNative()
	 * @model required="true"
	 * @generated
	 */
	boolean isIsNative();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModuleTrigger#isIsNative <em>Is Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Native</em>' attribute.
	 * @see #isIsNative()
	 * @generated
	 */
	void setIsNative(boolean value);

	/**
	 * Returns the value of the '<em><b>Execution Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Execution Method</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Execution Method</em>' attribute.
	 * @see #setExecutionMethod(String)
	 * @see eurema.EuremaPackage#getSoftwareModuleTrigger_ExecutionMethod()
	 * @model required="true"
	 * @generated
	 */
	String getExecutionMethod();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModuleTrigger#getExecutionMethod <em>Execution Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Execution Method</em>' attribute.
	 * @see #getExecutionMethod()
	 * @generated
	 */
	void setExecutionMethod(String value);

	/**
	 * Returns the value of the '<em><b>Module</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.SoftwareModule#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module</em>' container reference.
	 * @see #setModule(SoftwareModule)
	 * @see eurema.EuremaPackage#getSoftwareModuleTrigger_Module()
	 * @see eurema.SoftwareModule#getTrigger
	 * @model opposite="trigger" required="true" transient="false"
	 * @generated
	 */
	SoftwareModule getModule();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModuleTrigger#getModule <em>Module</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module</em>' container reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(SoftwareModule value);

} // SoftwareModuleTrigger
