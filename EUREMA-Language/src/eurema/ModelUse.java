/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Use</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ModelUse#getOperation <em>Operation</em>}</li>
 *   <li>{@link eurema.ModelUse#getModel <em>Model</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModelUse()
 * @model abstract="true"
 * @generated
 */
public interface ModelUse extends MegamodelElement {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.OperationBehavior#getModelUsages <em>Model Usages</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' container reference.
	 * @see #setOperation(OperationBehavior)
	 * @see eurema.EuremaPackage#getModelUse_Operation()
	 * @see eurema.OperationBehavior#getModelUsages
	 * @model opposite="modelUsages" required="true" transient="false"
	 * @generated
	 */
	OperationBehavior getOperation();

	/**
	 * Sets the value of the '{@link eurema.ModelUse#getOperation <em>Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' container reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(OperationBehavior value);

	/**
	 * Returns the value of the '<em><b>Model</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Model#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model</em>' reference.
	 * @see #setModel(Model)
	 * @see eurema.EuremaPackage#getModelUse_Model()
	 * @see eurema.Model#getUsedBy
	 * @model opposite="usedBy" required="true"
	 * @generated
	 */
	Model getModel();

	/**
	 * Sets the value of the '{@link eurema.ModelUse#getModel <em>Model</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model</em>' reference.
	 * @see #getModel()
	 * @generated
	 */
	void setModel(Model value);

} // ModelUse
