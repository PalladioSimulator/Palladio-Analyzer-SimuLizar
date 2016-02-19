/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Runtime Environment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.RuntimeEnvironment#getExecutions <em>Executions</em>}</li>
 *   <li>{@link eurema.RuntimeEnvironment#getArchitecture <em>Architecture</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getRuntimeEnvironment()
 * @model
 * @generated
 */
public interface RuntimeEnvironment extends EObject {
	/**
	 * Returns the value of the '<em><b>Executions</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.ExecutionContext}.
	 * It is bidirectional and its opposite is '{@link eurema.ExecutionContext#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Executions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Executions</em>' containment reference list.
	 * @see eurema.EuremaPackage#getRuntimeEnvironment_Executions()
	 * @see eurema.ExecutionContext#getEnvironment
	 * @model opposite="environment" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ExecutionContext> getExecutions();

	/**
	 * Returns the value of the '<em><b>Architecture</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.Architecture#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Architecture</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Architecture</em>' containment reference.
	 * @see #setArchitecture(Architecture)
	 * @see eurema.EuremaPackage#getRuntimeEnvironment_Architecture()
	 * @see eurema.Architecture#getEnvironment
	 * @model opposite="environment" containment="true" resolveProxies="true"
	 * @generated
	 */
	Architecture getArchitecture();

	/**
	 * Sets the value of the '{@link eurema.RuntimeEnvironment#getArchitecture <em>Architecture</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Architecture</em>' containment reference.
	 * @see #getArchitecture()
	 * @generated
	 */
	void setArchitecture(Architecture value);

} // RuntimeEnvironment
