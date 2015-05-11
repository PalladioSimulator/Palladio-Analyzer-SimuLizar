/**
 */
package eurema;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Effecting</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Effecting#getSource <em>Source</em>}</li>
 *   <li>{@link eurema.Effecting#getTarget <em>Target</em>}</li>
 *   <li>{@link eurema.Effecting#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getEffecting()
 * @model
 * @generated
 */
public interface Effecting extends EObject {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Module#getEffects <em>Effects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' container reference.
	 * @see #setSource(Module)
	 * @see eurema.EuremaPackage#getEffecting_Source()
	 * @see eurema.Module#getEffects
	 * @model opposite="effects" required="true" transient="false"
	 * @generated
	 */
	Module getSource();

	/**
	 * Sets the value of the '{@link eurema.Effecting#getSource <em>Source</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' container reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Module value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Module#getEffectedBy <em>Effected By</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Module)
	 * @see eurema.EuremaPackage#getEffecting_Target()
	 * @see eurema.Module#getEffectedBy
	 * @model opposite="effectedBy" required="true"
	 * @generated
	 */
	Module getTarget();

	/**
	 * Sets the value of the '{@link eurema.Effecting#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Module value);

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
	 * @see eurema.EuremaPackage#getEffecting_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eurema.Effecting#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Effecting
