/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Resource Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.ModelResourceSet#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModelResourceSet()
 * @model
 * @generated
 */
public interface ModelResourceSet extends EObject {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.ModelResource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' containment reference list.
	 * @see eurema.EuremaPackage#getModelResourceSet_Resources()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ModelResource> getResources();

} // ModelResourceSet
