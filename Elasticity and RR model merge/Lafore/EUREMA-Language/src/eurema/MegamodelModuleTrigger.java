/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Megamodel Module Trigger</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.MegamodelModuleTrigger#getInitialOperation <em>Initial Operation</em>}</li>
 *   <li>{@link eurema.MegamodelModuleTrigger#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getMegamodelModuleTrigger()
 * @model
 * @generated
 */
public interface MegamodelModuleTrigger extends Trigger {
	/**
	 * Returns the value of the '<em><b>Initial Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Initial Operation</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Initial Operation</em>' reference.
	 * @see #setInitialOperation(InitialOperation)
	 * @see eurema.EuremaPackage#getMegamodelModuleTrigger_InitialOperation()
	 * @model required="true"
	 * @generated
	 */
	InitialOperation getInitialOperation();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModuleTrigger#getInitialOperation <em>Initial Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Initial Operation</em>' reference.
	 * @see #getInitialOperation()
	 * @generated
	 */
	void setInitialOperation(InitialOperation value);

	/**
	 * Returns the value of the '<em><b>Module</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link eurema.MegamodelModule#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Module</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Module</em>' container reference.
	 * @see #setModule(MegamodelModule)
	 * @see eurema.EuremaPackage#getMegamodelModuleTrigger_Module()
	 * @see eurema.MegamodelModule#getTrigger
	 * @model opposite="trigger" required="true" transient="false"
	 * @generated
	 */
	MegamodelModule getModule();

	/**
	 * Sets the value of the '{@link eurema.MegamodelModuleTrigger#getModule <em>Module</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Module</em>' container reference.
	 * @see #getModule()
	 * @generated
	 */
	void setModule(MegamodelModule value);

} // MegamodelModuleTrigger
