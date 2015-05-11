/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.RuntimeModel#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getRuntimeModel()
 * @model
 * @generated
 */
public interface RuntimeModel extends Model {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.ModelResource#getBoundByRuntimeModels <em>Bound By Runtime Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(ModelResource)
	 * @see eurema.EuremaPackage#getRuntimeModel_Binding()
	 * @see eurema.ModelResource#getBoundByRuntimeModels
	 * @model opposite="boundByRuntimeModels"
	 * @generated
	 */
	ModelResource getBinding();

	/**
	 * Sets the value of the '{@link eurema.RuntimeModel#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(ModelResource value);

} // RuntimeModel
