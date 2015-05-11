/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Megamodel Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.MegamodelProxy#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getMegamodelProxy()
 * @model
 * @generated
 */
public interface MegamodelProxy extends Model {
	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(MegamodelModule)
	 * @see eurema.EuremaPackage#getMegamodelProxy_Binding()
	 * @model
	 * @generated
	 */
	MegamodelModule getBinding();

	/**
	 * Sets the value of the '{@link eurema.MegamodelProxy#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(MegamodelModule value);

} // MegamodelProxy
