/**
 */
package eurema;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Software Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link eurema.SoftwareModule#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link eurema.SoftwareModule#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link eurema.SoftwareModule#getSoftware <em>Software</em>}</li>
 * </ul>
 * </p>
 *
 * @see eurema.EuremaPackage#getSoftwareModule()
 * @model
 * @generated
 */
public interface SoftwareModule extends Module {
	/**
	 * Returns the value of the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implementation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implementation</em>' attribute.
	 * @see #setImplementation(String)
	 * @see eurema.EuremaPackage#getSoftwareModule_Implementation()
	 * @model
	 * @generated
	 */
	String getImplementation();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModule#getImplementation <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implementation</em>' attribute.
	 * @see #getImplementation()
	 * @generated
	 */
	void setImplementation(String value);

	/**
	 * Returns the value of the '<em><b>Trigger</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link eurema.SoftwareModuleTrigger#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Trigger</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trigger</em>' containment reference.
	 * @see #setTrigger(SoftwareModuleTrigger)
	 * @see eurema.EuremaPackage#getSoftwareModule_Trigger()
	 * @see eurema.SoftwareModuleTrigger#getModule
	 * @model opposite="module" containment="true" resolveProxies="true"
	 * @generated
	 */
	SoftwareModuleTrigger getTrigger();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModule#getTrigger <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trigger</em>' containment reference.
	 * @see #getTrigger()
	 * @generated
	 */
	void setTrigger(SoftwareModuleTrigger value);

	/**
	 * Returns the value of the '<em><b>Software</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link eurema.Software#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Software</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Software</em>' reference.
	 * @see #setSoftware(Software)
	 * @see eurema.EuremaPackage#getSoftwareModule_Software()
	 * @see eurema.Software#getModules
	 * @model opposite="modules" required="true"
	 * @generated
	 */
	Software getSoftware();

	/**
	 * Sets the value of the '{@link eurema.SoftwareModule#getSoftware <em>Software</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Software</em>' reference.
	 * @see #getSoftware()
	 * @generated
	 */
	void setSoftware(Software value);

} // SoftwareModule
