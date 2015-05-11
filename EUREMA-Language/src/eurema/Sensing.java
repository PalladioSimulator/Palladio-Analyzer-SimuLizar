/**
 */
package eurema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sensing</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Sensing#getSource <em>Source</em>}</li>
 *   <li>{@link eurema.Sensing#getTarget <em>Target</em>}</li>
 *   <li>{@link eurema.Sensing#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link eurema.Sensing#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getSensing()
 * @model
 * @generated
 */
public interface Sensing extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Module#getSenses <em>Senses</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Module)
	 * @see eurema.EuremaPackage#getSensing_Source()
	 * @see eurema.Module#getSenses
	 * @model opposite="senses" required="true" transient="false"
	 * @generated
	 */
	Module getSource();

	/**
	 * Sets the value of the '{@link eurema.Sensing#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Module value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Module#getSensedBy <em>Sensed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Module)
	 * @see eurema.EuremaPackage#getSensing_Target()
	 * @see eurema.Module#getSensedBy
	 * @model opposite="sensedBy" required="true"
	 * @generated
	 */
	Module getTarget();

	/**
	 * Sets the value of the '{@link eurema.Sensing#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Module value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Trigger#getRefers <em>Refers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' reference.
	 * @see #setTrigger(Trigger)
	 * @see eurema.EuremaPackage#getSensing_Trigger()
	 * @see eurema.Trigger#getRefers
	 * @model opposite="refers"
	 * @generated
	 */
	Trigger getTrigger();

	/**
	 * Sets the value of the '{@link eurema.Sensing#getTrigger <em>Trigger</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(Trigger value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see eurema.EuremaPackage#getSensing_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eurema.Sensing#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Sensing
