/**
 */
package eurema;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Megamodel</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.Megamodel#getInitialOperations <em>Initial Operations</em>}</li>
 *   <li>{@link eurema.Megamodel#getFinalOperations <em>Final Operations</em>}</li>
 *   <li>{@link eurema.Megamodel#getDecisionOperations <em>Decision Operations</em>}</li>
 *   <li>{@link eurema.Megamodel#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link eurema.Megamodel#getModels <em>Models</em>}</li>
 *   <li>{@link eurema.Megamodel#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.Megamodel#getName <em>Name</em>}</li>
 *   <li>{@link eurema.Megamodel#getDescription <em>Description</em>}</li>
 *   <li>{@link eurema.Megamodel#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getMegamodel()
 * @model
 * @generated
 */
public interface Megamodel extends EObject {
	/**
	 * Returns the value of the '<em><b>Initial Operations</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.InitialOperation}.
	 * It is bidirectional and its opposite is '{@link eurema.InitialOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Operations</em>' containment reference list.
	 * @see eurema.EuremaPackage#getMegamodel_InitialOperations()
	 * @see eurema.InitialOperation#getMegamodel
	 * @model opposite="megamodel" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	EList<InitialOperation> getInitialOperations();

	/**
	 * Returns the value of the '<em><b>Final Operations</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.FinalOperation}.
	 * It is bidirectional and its opposite is '{@link eurema.FinalOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Final Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Final Operations</em>' containment reference list.
	 * @see eurema.EuremaPackage#getMegamodel_FinalOperations()
	 * @see eurema.FinalOperation#getMegamodel
	 * @model opposite="megamodel" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	EList<FinalOperation> getFinalOperations();

	/**
	 * Returns the value of the '<em><b>Decision Operations</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.DecisionOperation}.
	 * It is bidirectional and its opposite is '{@link eurema.DecisionOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Decision Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Decision Operations</em>' containment reference list.
	 * @see eurema.EuremaPackage#getMegamodel_DecisionOperations()
	 * @see eurema.DecisionOperation#getMegamodel
	 * @model opposite="megamodel" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<DecisionOperation> getDecisionOperations();

	/**
	 * Returns the value of the '<em><b>Behavior</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.OperationBehavior}.
	 * It is bidirectional and its opposite is '{@link eurema.OperationBehavior#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior</em>' containment reference list.
	 * @see eurema.EuremaPackage#getMegamodel_Behavior()
	 * @see eurema.OperationBehavior#getMegamodel
	 * @model opposite="megamodel" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<OperationBehavior> getBehavior();

	/**
	 * Returns the value of the '<em><b>Models</b></em>' containment reference list.
	 * The list contents are of type {@link eurema.Model}.
	 * It is bidirectional and its opposite is '{@link eurema.Model#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Models</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Models</em>' containment reference list.
	 * @see eurema.EuremaPackage#getMegamodel_Models()
	 * @see eurema.Model#getMegamodel
	 * @model opposite="megamodel" containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<Model> getModels();

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
	 * @see eurema.EuremaPackage#getMegamodel_Uid()
	 * @model required="true"
	 * @generated
	 */
	String getUid();

	/**
	 * Sets the value of the '{@link eurema.Megamodel#getUid <em>Uid</em>}' attribute.
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
	 * @see eurema.EuremaPackage#getMegamodel_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link eurema.Megamodel#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see eurema.EuremaPackage#getMegamodel_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link eurema.Megamodel#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Module</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.MegamodelModule#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module</em>' container reference.
	 * @see #setModule(MegamodelModule)
	 * @see eurema.EuremaPackage#getMegamodel_Module()
	 * @see eurema.MegamodelModule#getMegamodel
	 * @model opposite="megamodel" transient="false"
	 * @generated
	 */
	MegamodelModule getModule();

	/**
	 * Sets the value of the '{@link eurema.Megamodel#getModule <em>Module</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module</em>' container reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(MegamodelModule value);

} // Megamodel
