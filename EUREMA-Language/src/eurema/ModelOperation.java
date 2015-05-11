/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ModelOperation#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModelOperation()
 * @model
 * @generated
 */
public interface ModelOperation extends OperationBehavior {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(SoftwareModule)
	 * @see eurema.EuremaPackage#getModelOperation_Binding()
	 * @model
	 * @generated
	 */
	SoftwareModule getBinding();

	/**
	 * Sets the value of the '{@link eurema.ModelOperation#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(SoftwareModule value);

} // ModelOperation
