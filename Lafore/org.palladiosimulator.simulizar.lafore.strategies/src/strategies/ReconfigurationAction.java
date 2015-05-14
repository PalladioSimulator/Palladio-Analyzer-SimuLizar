/**
 */
package strategies;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

import de.uka.ipd.sdq.pcm.seff.InternalAction;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reconfiguration Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link strategies.ReconfigurationAction#getCodeRef <em>Code Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see strategies.StrategiesPackage#getReconfigurationAction()
 * @model
 * @generated
 */
public interface ReconfigurationAction extends EObject, InternalAction, Entity {
	/**
	 * Returns the value of the '<em><b>Code Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Code Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Code Ref</em>' attribute.
	 * @see #setCodeRef(String)
	 * @see strategies.StrategiesPackage#getReconfigurationAction_CodeRef()
	 * @model
	 * @generated
	 */
	String getCodeRef();

	/**
	 * Sets the value of the '{@link strategies.ReconfigurationAction#getCodeRef <em>Code Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Code Ref</em>' attribute.
	 * @see #getCodeRef()
	 * @generated
	 */
	void setCodeRef(String value);

} // ReconfigurationAction
