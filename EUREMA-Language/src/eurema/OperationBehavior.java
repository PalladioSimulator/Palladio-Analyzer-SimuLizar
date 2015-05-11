/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation Behavior</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.OperationBehavior#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.OperationBehavior#getModelUsages <em>Model Usages</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getOperationBehavior()
 * @model abstract="true"
 * @generated
 */
public interface OperationBehavior extends Operation {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' container reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getOperationBehavior_Megamodel()
	 * @see eurema.Megamodel#getBehavior
	 * @model opposite="behavior" required="true" transient="false"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.OperationBehavior#getMegamodel <em>Megamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' container reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

	/**
	 * Returns the value of the '<em><b>Model Usages</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.ModelUse}.
	 * It is bidirectional and its opposite is '{@link eurema.ModelUse#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Usages</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Usages</em>' containment reference list.
	 * @see eurema.EuremaPackage#getOperationBehavior_ModelUsages()
	 * @see eurema.ModelUse#getOperation
	 * @model opposite="operation" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ModelUse> getModelUsages();

} // OperationBehavior
