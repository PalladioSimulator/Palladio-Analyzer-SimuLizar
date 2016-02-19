/**
 */
package dlim;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Work Load Sequence</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.WorkLoadSequence#getWorkFunctionContainers <em>Work Function Containers</em>}</li>
 *   <li>{@link dlim.WorkLoadSequence#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getWorkLoadSequence()
 * @model
 * @generated
 */
public interface WorkLoadSequence extends EObject {
	/**
	 * Returns the value of the '<em><b>Work Function Containers</b></em>' containment reference list.
	 * The list contents are of type {@link dlim.TimeDependentWorkFunctionContainer}.
	 * It is bidirectional and its opposite is '{@link dlim.TimeDependentWorkFunctionContainer#getWorkLoadSequence_WorkFunctionContainers <em>Work Load Sequence Work Function Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Function Containers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Function Containers</em>' containment reference list.
	 * @see dlim.DlimPackage#getWorkLoadSequence_WorkFunctionContainers()
	 * @see dlim.TimeDependentWorkFunctionContainer#getWorkLoadSequence_WorkFunctionContainers
	 * @model opposite="workLoadSequence_WorkFunctionContainers" containment="true" required="true"
	 * @generated
	 */
	EList<TimeDependentWorkFunctionContainer> getWorkFunctionContainers();

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
	 * @see dlim.DlimPackage#getWorkLoadSequence_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dlim.WorkLoadSequence#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // WorkLoadSequence
