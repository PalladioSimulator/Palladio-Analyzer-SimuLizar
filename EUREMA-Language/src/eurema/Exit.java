/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Exit#getOperation <em>Operation</em>}</li>
 *   <li>{@link eurema.Exit#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getExit()
 * @model
 * @generated
 */
public interface Exit extends MegamodelElement {
	/**
	 * Returns the value of the '<em><b>Operation</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Operation#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' container reference.
	 * @see #setOperation(Operation)
	 * @see eurema.EuremaPackage#getExit_Operation()
	 * @see eurema.Operation#getExits
	 * @model opposite="exits" required="true" transient="false"
	 * @generated
	 */
	Operation getOperation();

	/**
	 * Sets the value of the '{@link eurema.Exit#getOperation <em>Operation</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' container reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(Operation value);

	/**
	 * Returns the value of the '<em><b>Outgoing</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outgoing</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outgoing</em>' containment reference.
	 * @see #setOutgoing(Transition)
	 * @see eurema.EuremaPackage#getExit_Outgoing()
	 * @see eurema.Transition#getSource
	 * @model opposite="source" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	Transition getOutgoing();

	/**
	 * Sets the value of the '{@link eurema.Exit#getOutgoing <em>Outgoing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outgoing</em>' containment reference.
	 * @see #getOutgoing()
	 * @generated
	 */
	void setOutgoing(Transition value);

} // Exit
