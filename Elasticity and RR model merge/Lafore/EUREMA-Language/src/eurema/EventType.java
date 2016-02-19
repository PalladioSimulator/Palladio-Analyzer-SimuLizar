/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.EventType#getType <em>Type</em>}</li>
 *   <li>{@link eurema.EventType#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link eurema.EventType#getSubTypes <em>Sub Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getEventType()
 * @model
 * @generated
 */
public interface EventType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see eurema.EuremaPackage#getEventType_Type()
	 * @model required="true"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link eurema.EventType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Super Type</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.EventType#getSubTypes <em>Sub Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Type</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Type</em>' container reference.
	 * @see #setSuperType(EventType)
	 * @see eurema.EuremaPackage#getEventType_SuperType()
	 * @see eurema.EventType#getSubTypes
	 * @model opposite="subTypes" transient="false"
	 * @generated
	 */
	EventType getSuperType();

	/**
	 * Sets the value of the '{@link eurema.EventType#getSuperType <em>Super Type</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Super Type</em>' container reference.
	 * @see #getSuperType()
	 * @generated
	 */
	void setSuperType(EventType value);

	/**
	 * Returns the value of the '<em><b>Sub Types</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.EventType}.
	 * It is bidirectional and its opposite is '{@link eurema.EventType#getSuperType <em>Super Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Types</em>' containment reference list.
	 * @see eurema.EuremaPackage#getEventType_SubTypes()
	 * @see eurema.EventType#getSuperType
	 * @model opposite="superType" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<EventType> getSubTypes();

} // EventType
