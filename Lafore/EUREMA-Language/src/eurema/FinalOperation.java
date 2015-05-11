/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Final Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.FinalOperation#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.FinalOperation#isIsDestructive <em>Is Destructive</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getFinalOperation()
 * @model
 * @generated
 */
public interface FinalOperation extends ControlOperation {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getFinalOperations <em>Final Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' container reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getFinalOperation_Megamodel()
	 * @see eurema.Megamodel#getFinalOperations
	 * @model opposite="finalOperations" required="true" transient="false"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.FinalOperation#getMegamodel <em>Megamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' container reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

	/**
	 * Returns the value of the '<em><b>Is Destructive</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Destructive</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Destructive</em>' attribute.
	 * @see #setIsDestructive(boolean)
	 * @see eurema.EuremaPackage#getFinalOperation_IsDestructive()
	 * @model default="false"
	 * @generated
	 */
	boolean isIsDestructive();

	/**
	 * Sets the value of the '{@link eurema.FinalOperation#isIsDestructive <em>Is Destructive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Destructive</em>' attribute.
	 * @see #isIsDestructive()
	 * @generated
	 */
	void setIsDestructive(boolean value);

} // FinalOperation
