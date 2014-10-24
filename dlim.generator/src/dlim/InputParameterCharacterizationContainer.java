/**
 */
package dlim;

import de.uka.ipd.sdq.pcm.parameter.VariableUsage;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Parameter Characterization Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.InputParameterCharacterizationContainer#getVariableUsage <em>Variable Usage</em>}</li>
 *   <li>{@link dlim.InputParameterCharacterizationContainer#getParameterValue <em>Parameter Value</em>}</li>
 *   <li>{@link dlim.InputParameterCharacterizationContainer#getTDWFC_ParameterCharacterizationContainers <em>TDWFC Parameter Characterization Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getInputParameterCharacterizationContainer()
 * @model
 * @generated
 */
public interface InputParameterCharacterizationContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Variable Usage</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable Usage</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Usage</em>' containment reference.
	 * @see #setVariableUsage(VariableUsage)
	 * @see dlim.DlimPackage#getInputParameterCharacterizationContainer_VariableUsage()
	 * @model containment="true" required="true"
	 * @generated
	 */
	VariableUsage getVariableUsage();

	/**
	 * Sets the value of the '{@link dlim.InputParameterCharacterizationContainer#getVariableUsage <em>Variable Usage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable Usage</em>' containment reference.
	 * @see #getVariableUsage()
	 * @generated
	 */
	void setVariableUsage(VariableUsage value);

	/**
	 * Returns the value of the '<em><b>Parameter Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Value</em>' containment reference.
	 * @see #setParameterValue(Sequence)
	 * @see dlim.DlimPackage#getInputParameterCharacterizationContainer_ParameterValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Sequence getParameterValue();

	/**
	 * Sets the value of the '{@link dlim.InputParameterCharacterizationContainer#getParameterValue <em>Parameter Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Value</em>' containment reference.
	 * @see #getParameterValue()
	 * @generated
	 */
	void setParameterValue(Sequence value);

	/**
	 * Returns the value of the '<em><b>TDWFC Parameter Characterization Containers</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link dlim.TimeDependentWorkFunctionContainer#getParameterCharaterizationContainers <em>Parameter Charaterization Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TDWFC Parameter Characterization Containers</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TDWFC Parameter Characterization Containers</em>' container reference.
	 * @see #setTDWFC_ParameterCharacterizationContainers(TimeDependentWorkFunctionContainer)
	 * @see dlim.DlimPackage#getInputParameterCharacterizationContainer_TDWFC_ParameterCharacterizationContainers()
	 * @see dlim.TimeDependentWorkFunctionContainer#getParameterCharaterizationContainers
	 * @model opposite="parameterCharaterizationContainers" required="true" transient="false"
	 * @generated
	 */
	TimeDependentWorkFunctionContainer getTDWFC_ParameterCharacterizationContainers();

	/**
	 * Sets the value of the '{@link dlim.InputParameterCharacterizationContainer#getTDWFC_ParameterCharacterizationContainers <em>TDWFC Parameter Characterization Containers</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TDWFC Parameter Characterization Containers</em>' container reference.
	 * @see #getTDWFC_ParameterCharacterizationContainers()
	 * @generated
	 */
	void setTDWFC_ParameterCharacterizationContainers(TimeDependentWorkFunctionContainer value);

} // InputParameterCharacterizationContainer
