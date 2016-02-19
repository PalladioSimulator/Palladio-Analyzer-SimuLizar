/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Trigger#getPeriod <em>Period</em>}</li>
 *   <li>{@link eurema.Trigger#getEvents <em>Events</em>}</li>
 *   <li>{@link eurema.Trigger#getRefers <em>Refers</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getTrigger()
 * @model abstract="true"
 * @generated
 */
public interface Trigger extends EObject {
	/**
	 * Returns the value of the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Period</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Period</em>' attribute.
	 * @see #setPeriod(int)
	 * @see eurema.EuremaPackage#getTrigger_Period()
	 * @model
	 * @generated
	 */
	int getPeriod();

	/**
	 * Sets the value of the '{@link eurema.Trigger#getPeriod <em>Period</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Period</em>' attribute.
	 * @see #getPeriod()
	 * @generated
	 */
	void setPeriod(int value);

	/**
	 * Returns the value of the '<em><b>Events</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Event}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Events</em>' containment reference list.
	 * @see eurema.EuremaPackage#getTrigger_Events()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Event> getEvents();

	/**
	 * Returns the value of the '<em><b>Refers</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Sensing#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Refers</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Refers</em>' reference.
	 * @see #setRefers(Sensing)
	 * @see eurema.EuremaPackage#getTrigger_Refers()
	 * @see eurema.Sensing#getTrigger
	 * @model opposite="trigger"
	 * @generated
	 */
	Sensing getRefers();

	/**
	 * Sets the value of the '{@link eurema.Trigger#getRefers <em>Refers</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Refers</em>' reference.
	 * @see #getRefers()
	 * @generated
	 */
	void setRefers(Sensing value);

} // Trigger
