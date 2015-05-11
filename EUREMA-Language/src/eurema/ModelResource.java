/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ModelResource#getURI <em>URI</em>}</li>
 *   <li>{@link eurema.ModelResource#getName <em>Name</em>}</li>
 *   <li>{@link eurema.ModelResource#getBoundByRuntimeModels <em>Bound By Runtime Models</em>}</li>
 *   <li>{@link eurema.ModelResource#getBoundByMegamodelModules <em>Bound By Megamodel Modules</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModelResource()
 * @model
 * @generated
 */
public interface ModelResource extends EObject {
	/**
	 * Returns the value of the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>URI</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>URI</em>' attribute.
	 * @see #setURI(String)
	 * @see eurema.EuremaPackage#getModelResource_URI()
	 * @model required="true"
	 * @generated
	 */
	String getURI();

	/**
	 * Sets the value of the '{@link eurema.ModelResource#getURI <em>URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>URI</em>' attribute.
	 * @see #getURI()
	 * @generated
	 */
	void setURI(String value);

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
	 * @see eurema.EuremaPackage#getModelResource_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eurema.ModelResource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Bound By Runtime Models</b></em>' reference list.
	 * The list contents are of type {@link eurema.RuntimeModel}.
	 * It is bidirectional and its opposite is '{@link eurema.RuntimeModel#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound By Runtime Models</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound By Runtime Models</em>' reference list.
	 * @see eurema.EuremaPackage#getModelResource_BoundByRuntimeModels()
	 * @see eurema.RuntimeModel#getBinding
	 * @model opposite="binding"
	 * @generated
	 */
	EList<RuntimeModel> getBoundByRuntimeModels();

	/**
	 * Returns the value of the '<em><b>Bound By Megamodel Modules</b></em>' reference list.
	 * The list contents are of type {@link eurema.MegamodelModule}.
	 * It is bidirectional and its opposite is '{@link eurema.MegamodelModule#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound By Megamodel Modules</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound By Megamodel Modules</em>' reference list.
	 * @see eurema.EuremaPackage#getModelResource_BoundByMegamodelModules()
	 * @see eurema.MegamodelModule#getBinding
	 * @model opposite="binding"
	 * @generated
	 */
	EList<MegamodelModule> getBoundByMegamodelModules();

} // ModelResource
