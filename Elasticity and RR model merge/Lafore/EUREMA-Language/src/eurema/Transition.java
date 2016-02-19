/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Transition#getCondition <em>Condition</em>}</li>
 *   <li>{@link eurema.Transition#getTarget <em>Target</em>}</li>
 *   <li>{@link eurema.Transition#getSource <em>Source</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getTransition()
 * @model
 * @generated
 */
public interface Transition extends Executable {
	/**
	 * Returns the value of the '<em><b>Condition</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.Condition#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Condition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Condition</em>' containment reference.
	 * @see #setCondition(Condition)
	 * @see eurema.EuremaPackage#getTransition_Condition()
	 * @see eurema.Condition#getTransition
	 * @model opposite="transition" containment="true" resolveProxies="true"
	 * @generated
	 */
	Condition getCondition();

	/**
	 * Sets the value of the '{@link eurema.Transition#getCondition <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Condition</em>' containment reference.
	 * @see #getCondition()
	 * @generated
	 */
	void setCondition(Condition value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Entry#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Entry)
	 * @see eurema.EuremaPackage#getTransition_Target()
	 * @see eurema.Entry#getIncoming
	 * @model opposite="incoming" required="true"
	 * @generated
	 */
	Entry getTarget();

	/**
	 * Sets the value of the '{@link eurema.Transition#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Entry value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Exit#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Exit)
	 * @see eurema.EuremaPackage#getTransition_Source()
	 * @see eurema.Exit#getOutgoing
	 * @model opposite="outgoing" required="true" transient="false"
	 * @generated
	 */
	Exit getSource();

	/**
	 * Sets the value of the '{@link eurema.Transition#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Exit value);

} // Transition
