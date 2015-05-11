/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Initial Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.InitialOperation#getMegamodel <em>Megamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getInitialOperation()
 * @model
 * @generated
 */
public interface InitialOperation extends ControlOperation {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getInitialOperations <em>Initial Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' container reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getInitialOperation_Megamodel()
	 * @see eurema.Megamodel#getInitialOperations
	 * @model opposite="initialOperations" required="true" transient="false"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.InitialOperation#getMegamodel <em>Megamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' container reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

} // InitialOperation
