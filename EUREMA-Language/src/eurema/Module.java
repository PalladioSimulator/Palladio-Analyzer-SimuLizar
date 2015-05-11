/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Module#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.Module#getName <em>Name</em>}</li>
 *   <li>{@link eurema.Module#getSenses <em>Senses</em>}</li>
 *   <li>{@link eurema.Module#getSensedBy <em>Sensed By</em>}</li>
 *   <li>{@link eurema.Module#getEffects <em>Effects</em>}</li>
 *   <li>{@link eurema.Module#getEffectedBy <em>Effected By</em>}</li>
 *   <li>{@link eurema.Module#getLayer <em>Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getModule()
 * @model abstract="true"
 * @generated
 */
public interface Module extends EObject {
	/**
	 * Returns the value of the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uid</em>' attribute.
	 * @see #setUid(String)
	 * @see eurema.EuremaPackage#getModule_Uid()
	 * @model required="true"
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eurema.Module#getUid <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uid</em>' attribute.
	 * @see #getUid()
	 * @generated
	 */
	void setUid(String value);

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
	 * @see eurema.EuremaPackage#getModule_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eurema.Module#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Senses</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Sensing}.
	 * It is bidirectional and its opposite is '{@link eurema.Sensing#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Senses</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Senses</em>' containment reference list.
	 * @see eurema.EuremaPackage#getModule_Senses()
	 * @see eurema.Sensing#getSource
	 * @model opposite="source" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Sensing> getSenses();

	/**
	 * Returns the value of the '<em><b>Sensed By</b></em>' reference list.
	 * The list contents are of type {@link eurema.Sensing}.
	 * It is bidirectional and its opposite is '{@link eurema.Sensing#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sensed By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sensed By</em>' reference list.
	 * @see eurema.EuremaPackage#getModule_SensedBy()
	 * @see eurema.Sensing#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Sensing> getSensedBy();

	/**
	 * Returns the value of the '<em><b>Effects</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Effecting}.
	 * It is bidirectional and its opposite is '{@link eurema.Effecting#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Effects</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effects</em>' containment reference list.
	 * @see eurema.EuremaPackage#getModule_Effects()
	 * @see eurema.Effecting#getSource
	 * @model opposite="source" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Effecting> getEffects();

	/**
	 * Returns the value of the '<em><b>Effected By</b></em>' reference list.
	 * The list contents are of type {@link eurema.Effecting}.
	 * It is bidirectional and its opposite is '{@link eurema.Effecting#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Effected By</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Effected By</em>' reference list.
	 * @see eurema.EuremaPackage#getModule_EffectedBy()
	 * @see eurema.Effecting#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Effecting> getEffectedBy();

	/**
	 * Returns the value of the '<em><b>Layer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.Layer#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Layer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Layer</em>' container reference.
	 * @see #setLayer(Layer)
	 * @see eurema.EuremaPackage#getModule_Layer()
	 * @see eurema.Layer#getModules
	 * @model opposite="modules" transient="false"
	 * @generated
	 */
	Layer getLayer();

	/**
	 * Sets the value of the '{@link eurema.Module#getLayer <em>Layer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Layer</em>' container reference.
	 * @see #getLayer()
	 * @generated
	 */
	void setLayer(Layer value);

} // Module
