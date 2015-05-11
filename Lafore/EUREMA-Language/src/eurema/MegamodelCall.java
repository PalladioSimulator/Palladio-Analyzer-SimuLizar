/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Megamodel Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.MegamodelCall#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getMegamodelCall()
 * @model
 * @generated
 */
public interface MegamodelCall extends OperationBehavior {
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
	 * @see eurema.EuremaPackage#getMegamodelCall_Binding()
	 * @model
	 * @generated
	 */
	MegamodelModule getBinding();

	/**
	 * Sets the value of the '{@link eurema.MegamodelCall#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(MegamodelModule value);

} // MegamodelCall
