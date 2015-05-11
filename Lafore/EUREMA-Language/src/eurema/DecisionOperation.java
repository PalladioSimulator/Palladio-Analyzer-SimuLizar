/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Decision Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.DecisionOperation#getMegamodel <em>Megamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getDecisionOperation()
 * @model
 * @generated
 */
public interface DecisionOperation extends ControlOperation {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getDecisionOperations <em>Decision Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' container reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getDecisionOperation_Megamodel()
	 * @see eurema.Megamodel#getDecisionOperations
	 * @model opposite="decisionOperations" required="true" transient="false"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.DecisionOperation#getMegamodel <em>Megamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' container reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

} // DecisionOperation
