/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Model#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.Model#getUsedBy <em>Used By</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModel()
 * @model abstract="true"
 * @generated
 */
public interface Model extends MegamodelElement {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getModels <em>Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' container reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getModel_Megamodel()
	 * @see eurema.Megamodel#getModels
	 * @model opposite="models" required="true" transient="false"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.Model#getMegamodel <em>Megamodel</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' container reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

	/**
	 * Returns the value of the '<em><b>Used By</b></em>' reference list.
	 * The list contents are of type {@link eurema.ModelUse}.
	 * It is bidirectional and its opposite is '{@link eurema.ModelUse#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Used By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Used By</em>' reference list.
	 * @see eurema.EuremaPackage#getModel_UsedBy()
	 * @see eurema.ModelUse#getModel
	 * @model opposite="model"
	 * @generated
	 */
	EList<ModelUse> getUsedBy();

} // Model
