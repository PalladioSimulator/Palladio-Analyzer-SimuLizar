/**
 */
package dlim;

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

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
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer()
 * @model
 * @generated
 */
public interface TimeDependentWorkFunctionContainer extends TimeDependentFunctionContainer {
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
	 * @model required="true"
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Start Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Start Time</em>' attribute.
	 * @see #setWorkStartTime(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_WorkStartTime()
	 * @model
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
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Work Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Work Duration</em>' attribute.
	 * @see #setWorkDuration(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_WorkDuration()
	 * @model
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
	 * @see #setMutualLoadFunction(Sequence)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_MutualLoadFunction()
	 * @model
	 * @generated
	 */
	Sequence getMutualLoadFunction();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getMutualLoadFunction <em>Mutual Load Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Mutual Load Function</em>' reference.
	 * @see #getMutualLoadFunction()
	 * @generated
	 */
	void setMutualLoadFunction(Sequence value);

	/**
	 * Returns the value of the '<em><b>Priority</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Priority</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Priority</em>' attribute.
	 * @see #setPriority(int)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_Priority()
	 * @model
	 * @generated
	 */
	int getPriority();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getPriority <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Priority</em>' attribute.
	 * @see #getPriority()
	 * @generated
	 */
	void setPriority(int value);

} // TimeDependentWorkFunctionContainer
