/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Architecture</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Architecture#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link eurema.Architecture#getLayers <em>Layers</em>}</li>
 *   <li>{@link eurema.Architecture#getModelResourceSet <em>Model Resource Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getArchitecture()
 * @model
 * @generated
 */
public interface Architecture extends EObject {
	/**
	 * Returns the value of the '<em><b>Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.RuntimeEnvironment#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Environment</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Environment</em>' container reference.
	 * @see #setEnvironment(RuntimeEnvironment)
	 * @see eurema.EuremaPackage#getArchitecture_Environment()
	 * @see eurema.RuntimeEnvironment#getArchitecture
	 * @model opposite="architecture" transient="false"
	 * @generated
	 */
	RuntimeEnvironment getEnvironment();

	/**
	 * Sets the value of the '{@link eurema.Architecture#getEnvironment <em>Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Environment</em>' container reference.
	 * @see #getEnvironment()
	 * @generated
	 */
	void setEnvironment(RuntimeEnvironment value);

	/**
	 * Returns the value of the '<em><b>Layers</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Layer}.
	 * It is bidirectional and its opposite is '{@link eurema.Layer#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layers</em>' containment reference list.
	 * @see eurema.EuremaPackage#getArchitecture_Layers()
	 * @see eurema.Layer#getArchitecture
	 * @model opposite="architecture" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Layer> getLayers();

	/**
	 * Returns the value of the '<em><b>Model Resource Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Model Resource Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Model Resource Set</em>' containment reference.
	 * @see #setModelResourceSet(ModelResourceSet)
	 * @see eurema.EuremaPackage#getArchitecture_ModelResourceSet()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	ModelResourceSet getModelResourceSet();

	/**
	 * Sets the value of the '{@link eurema.Architecture#getModelResourceSet <em>Model Resource Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Resource Set</em>' containment reference.
	 * @see #getModelResourceSet()
	 * @generated
	 */
	void setModelResourceSet(ModelResourceSet value);

} // Architecture
