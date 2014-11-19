/**
 */
package dlim;

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Time Dependent Work Function Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getWork <em>Work</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getWorkStartTime <em>Work Start Time</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getWorkDuration <em>Work Duration</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getTimeSynchronization <em>Time Synchronization</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getMutualLoadFunction <em>Mutual Load Function</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getLoadSequence <em>Load Sequence</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getName <em>Name</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getParameterCharaterizationContainers <em>Parameter Charaterization Containers</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getWorkLoadSequence_WorkFunctionContainers <em>Work Load Sequence Work Function Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer()
 * @model
 * @generated
 */
public interface TimeDependentWorkFunctionContainer extends EObject {
	/**
	 * Returns the value of the '<em><b>Work</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work</em>' reference.
	 * @see #setWork(UsageScenario)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_Work()
	 * @model required="true" ordered="false"
	 * @generated
	 */
	UsageScenario getWork();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getWork <em>Work</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work</em>' reference.
	 * @see #getWork()
	 * @generated
	 */
	void setWork(UsageScenario value);

	/**
	 * Returns the value of the '<em><b>Work Start Time</b></em>' attribute.
	 * The default value is <code>"0.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Start Time</em>' attribute.
	 * @see #setWorkStartTime(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_WorkStartTime()
	 * @model default="0.0"
	 * @generated
	 */
	double getWorkStartTime();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getWorkStartTime <em>Work Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Start Time</em>' attribute.
	 * @see #getWorkStartTime()
	 * @generated
	 */
	void setWorkStartTime(double value);

	/**
	 * Returns the value of the '<em><b>Work Duration</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Duration</em>' attribute.
	 * @see #setWorkDuration(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_WorkDuration()
	 * @model default="1.0"
	 * @generated
	 */
	double getWorkDuration();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getWorkDuration <em>Work Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Duration</em>' attribute.
	 * @see #getWorkDuration()
	 * @generated
	 */
	void setWorkDuration(double value);

	/**
	 * Returns the value of the '<em><b>Time Synchronization</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Synchronization</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Synchronization</em>' reference.
	 * @see #setTimeSynchronization(TimeDependentWorkFunctionContainer)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_TimeSynchronization()
	 * @model
	 * @generated
	 */
	TimeDependentWorkFunctionContainer getTimeSynchronization();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getTimeSynchronization <em>Time Synchronization</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Synchronization</em>' reference.
	 * @see #getTimeSynchronization()
	 * @generated
	 */
	void setTimeSynchronization(TimeDependentWorkFunctionContainer value);

	/**
	 * Returns the value of the '<em><b>Mutual Load Function</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mutual Load Function</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mutual Load Function</em>' reference.
	 * @see #setMutualLoadFunction(TimeDependentWorkFunctionContainer)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_MutualLoadFunction()
	 * @model
	 * @generated
	 */
	TimeDependentWorkFunctionContainer getMutualLoadFunction();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getMutualLoadFunction <em>Mutual Load Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mutual Load Function</em>' reference.
	 * @see #getMutualLoadFunction()
	 * @generated
	 */
	void setMutualLoadFunction(TimeDependentWorkFunctionContainer value);

	/**
	 * Returns the value of the '<em><b>Load Sequence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Load Sequence</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Load Sequence</em>' containment reference.
	 * @see #setLoadSequence(Sequence)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_LoadSequence()
	 * @model containment="true"
	 * @generated
	 */
	Sequence getLoadSequence();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getLoadSequence <em>Load Sequence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Load Sequence</em>' containment reference.
	 * @see #getLoadSequence()
	 * @generated
	 */
	void setLoadSequence(Sequence value);

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
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Parameter Charaterization Containers</b></em>' containment reference list.
	 * The list contents are of type {@link dlim.InputParameterCharacterizationContainer}.
	 * It is bidirectional and its opposite is '{@link dlim.InputParameterCharacterizationContainer#getTDWFC_ParameterCharacterizationContainers <em>TDWFC Parameter Characterization Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Charaterization Containers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Charaterization Containers</em>' containment reference list.
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_ParameterCharaterizationContainers()
	 * @see dlim.InputParameterCharacterizationContainer#getTDWFC_ParameterCharacterizationContainers
	 * @model opposite="tDWFC_ParameterCharacterizationContainers" containment="true"
	 * @generated
	 */
	EList<InputParameterCharacterizationContainer> getParameterCharaterizationContainers();

	/**
	 * Returns the value of the '<em><b>Work Load Sequence Work Function Containers</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link dlim.WorkLoadSequence#getWorkFunctionContainers <em>Work Function Containers</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Load Sequence Work Function Containers</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Load Sequence Work Function Containers</em>' container reference.
	 * @see #setWorkLoadSequence_WorkFunctionContainers(WorkLoadSequence)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_WorkLoadSequence_WorkFunctionContainers()
	 * @see dlim.WorkLoadSequence#getWorkFunctionContainers
	 * @model opposite="workFunctionContainers" required="true" transient="false"
	 * @generated
	 */
	WorkLoadSequence getWorkLoadSequence_WorkFunctionContainers();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getWorkLoadSequence_WorkFunctionContainers <em>Work Load Sequence Work Function Containers</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Work Load Sequence Work Function Containers</em>' container reference.
	 * @see #getWorkLoadSequence_WorkFunctionContainers()
	 * @generated
	 */
	void setWorkLoadSequence_WorkFunctionContainers(WorkLoadSequence value);

} // TimeDependentWorkFunctionContainer
