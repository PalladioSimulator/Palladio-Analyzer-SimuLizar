/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Megamodel Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.MegamodelModule#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.MegamodelModule#getContext <em>Context</em>}</li>
 *   <li>{@link eurema.MegamodelModule#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link eurema.MegamodelModule#getBinding <em>Binding</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getMegamodelModule()
 * @model
 * @generated
 */
public interface MegamodelModule extends Module {
	/**
	 * Returns the value of the '<em><b>Megamodel</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.Megamodel#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Megamodel</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Megamodel</em>' containment reference.
	 * @see #setMegamodel(Megamodel)
	 * @see eurema.EuremaPackage#getMegamodelModule_Megamodel()
	 * @see eurema.Megamodel#getModule
	 * @model opposite="module" containment="true" resolveProxies="true" required="true"
	 * @generated
	 */
	Megamodel getMegamodel();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModule#getMegamodel <em>Megamodel</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Megamodel</em>' containment reference.
	 * @see #getMegamodel()
	 * @generated
	 */
	void setMegamodel(Megamodel value);

	/**
	 * Returns the value of the '<em><b>Context</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.ExecutionContext#getExecuting <em>Executing</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' reference.
	 * @see #setContext(ExecutionContext)
	 * @see eurema.EuremaPackage#getMegamodelModule_Context()
	 * @see eurema.ExecutionContext#getExecuting
	 * @model opposite="executing"
	 * @generated
	 */
	ExecutionContext getContext();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModule#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' reference.
	 * @see #getContext()
	 * @generated
	 */
	void setContext(ExecutionContext value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.MegamodelModuleTrigger#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(MegamodelModuleTrigger)
	 * @see eurema.EuremaPackage#getMegamodelModule_Trigger()
	 * @see eurema.MegamodelModuleTrigger#getModule
	 * @model opposite="module" containment="true" resolveProxies="true"
	 * @generated
	 */
	MegamodelModuleTrigger getTrigger();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModule#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(MegamodelModuleTrigger value);

	/**
	 * Returns the value of the '<em><b>Binding</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.ModelResource#getBoundByMegamodelModules <em>Bound By Megamodel Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Binding</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Binding</em>' reference.
	 * @see #setBinding(ModelResource)
	 * @see eurema.EuremaPackage#getMegamodelModule_Binding()
	 * @see eurema.ModelResource#getBoundByMegamodelModules
	 * @model opposite="boundByMegamodelModules"
	 * @generated
	 */
	ModelResource getBinding();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModule#getBinding <em>Binding</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Binding</em>' reference.
	 * @see #getBinding()
	 * @generated
	 */
	void setBinding(ModelResource value);

} // MegamodelModule
