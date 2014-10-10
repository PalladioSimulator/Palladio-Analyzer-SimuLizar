/**
 */
package dlim;

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;
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
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getPriority <em>Priority</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getLoadSequence <em>Load Sequence</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getName <em>Name</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getDuration <em>Duration</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getFirstIterationStart <em>First Iteration Start</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getFirstIterationEnd <em>First Iteration End</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}</li>
 *   <li>{@link dlim.TimeDependentWorkFunctionContainer#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}</li>
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
	 * @model transient="true"
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
	 * @model containment="true" required="true"
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
	 * Returns the value of the '<em><b>Duration</b></em>' attribute.
	 * The default value is <code>"1.0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duration</em>' attribute.
	 * @see #setDuration(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_Duration()
	 * @model default="1.0"
	 * @generated
	 */
	double getDuration();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getDuration <em>Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duration</em>' attribute.
	 * @see #getDuration()
	 * @generated
	 */
	void setDuration(double value);

	/**
	 * Returns the value of the '<em><b>First Iteration Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Iteration Start</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Iteration Start</em>' attribute.
	 * @see #setFirstIterationStart(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_FirstIterationStart()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationStart();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getFirstIterationStart <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Iteration Start</em>' attribute.
	 * @see #getFirstIterationStart()
	 * @generated
	 */
	void setFirstIterationStart(double value);

	/**
	 * Returns the value of the '<em><b>First Iteration End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>First Iteration End</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>First Iteration End</em>' attribute.
	 * @see #setFirstIterationEnd(double)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_FirstIterationEnd()
	 * @model derived="true"
	 * @generated
	 */
	double getFirstIterationEnd();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getFirstIterationEnd <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>First Iteration End</em>' attribute.
	 * @see #getFirstIterationEnd()
	 * @generated
	 */
	void setFirstIterationEnd(double value);

	/**
	 * Returns the value of the '<em><b>Point Of Reference Clock Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point Of Reference Clock Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Point Of Reference Clock Object</em>' reference.
	 * @see #setPointOfReferenceClockObject(ReferenceClockObject)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_PointOfReferenceClockObject()
	 * @model
	 * @generated
	 */
	ReferenceClockObject getPointOfReferenceClockObject();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Of Reference Clock Object</em>' reference.
	 * @see #getPointOfReferenceClockObject()
	 * @generated
	 */
	void setPointOfReferenceClockObject(ReferenceClockObject value);

	/**
	 * Returns the value of the '<em><b>Point Of Reference Clock Type</b></em>' attribute.
	 * The default value is <code>"CONTAINERCLOCK"</code>.
	 * The literals are from the enumeration {@link dlim.ClockType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point Of Reference Clock Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Point Of Reference Clock Type</em>' attribute.
	 * @see dlim.ClockType
	 * @see #setPointOfReferenceClockType(ClockType)
	 * @see dlim.DlimPackage#getTimeDependentWorkFunctionContainer_PointOfReferenceClockType()
	 * @model default="CONTAINERCLOCK"
	 * @generated
	 */
	ClockType getPointOfReferenceClockType();

	/**
	 * Sets the value of the '{@link dlim.TimeDependentWorkFunctionContainer#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Point Of Reference Clock Type</em>' attribute.
	 * @see dlim.ClockType
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 */
	void setPointOfReferenceClockType(ClockType value);

} // TimeDependentWorkFunctionContainer
