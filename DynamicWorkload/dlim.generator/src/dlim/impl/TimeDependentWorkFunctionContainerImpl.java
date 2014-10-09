/**
 */
package dlim.impl;

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

import dlim.DlimPackage;
import dlim.Sequence;
import dlim.TimeDependentWorkFunctionContainer;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Time Dependent Work Function Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getWork <em>Work</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getWorkStartTime <em>Work Start Time</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getWorkDuration <em>Work Duration</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getTimeSynchronization <em>Time Synchronization</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getMutualLoadFunction <em>Mutual Load Function</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getPriority <em>Priority</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeDependentWorkFunctionContainerImpl extends TimeDependentFunctionContainerImpl implements TimeDependentWorkFunctionContainer {
	/**
	 * The cached value of the '{@link #getWork() <em>Work</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWork()
	 * @generated
	 * @ordered
	 */
	protected UsageScenario work;

	/**
	 * The default value of the '{@link #getWorkStartTime() <em>Work Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkStartTime()
	 * @generated
	 * @ordered
	 */
	protected static final double WORK_START_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWorkStartTime() <em>Work Start Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkStartTime()
	 * @generated
	 * @ordered
	 */
	protected double workStartTime = WORK_START_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getWorkDuration() <em>Work Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkDuration()
	 * @generated
	 * @ordered
	 */
	protected static final double WORK_DURATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getWorkDuration() <em>Work Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkDuration()
	 * @generated
	 * @ordered
	 */
	protected double workDuration = WORK_DURATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTimeSynchronization() <em>Time Synchronization</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTimeSynchronization()
	 * @generated
	 * @ordered
	 */
	protected TimeDependentWorkFunctionContainer timeSynchronization;

	/**
	 * The cached value of the '{@link #getMutualLoadFunction() <em>Mutual Load Function</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMutualLoadFunction()
	 * @generated
	 * @ordered
	 */
	protected Sequence mutualLoadFunction;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final int PRIORITY_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected int priority = PRIORITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TimeDependentWorkFunctionContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageScenario getWork() {
		if (work != null && ((EObject)work).eIsProxy()) {
			InternalEObject oldWork = (InternalEObject)work;
			work = (UsageScenario)eResolveProxy(oldWork);
			if (work != oldWork) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK, oldWork, work));
			}
		}
		return work;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UsageScenario basicGetWork() {
		return work;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWork(UsageScenario newWork) {
		UsageScenario oldWork = work;
		work = newWork;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK, oldWork, work));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWorkStartTime() {
		return workStartTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkStartTime(double newWorkStartTime) {
		double oldWorkStartTime = workStartTime;
		workStartTime = newWorkStartTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME, oldWorkStartTime, workStartTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getWorkDuration() {
		return workDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkDuration(double newWorkDuration) {
		double oldWorkDuration = workDuration;
		workDuration = newWorkDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION, oldWorkDuration, workDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeDependentWorkFunctionContainer getTimeSynchronization() {
		if (timeSynchronization != null && timeSynchronization.eIsProxy()) {
			InternalEObject oldTimeSynchronization = (InternalEObject)timeSynchronization;
			timeSynchronization = (TimeDependentWorkFunctionContainer)eResolveProxy(oldTimeSynchronization);
			if (timeSynchronization != oldTimeSynchronization) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION, oldTimeSynchronization, timeSynchronization));
			}
		}
		return timeSynchronization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TimeDependentWorkFunctionContainer basicGetTimeSynchronization() {
		return timeSynchronization;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTimeSynchronization(TimeDependentWorkFunctionContainer newTimeSynchronization) {
		TimeDependentWorkFunctionContainer oldTimeSynchronization = timeSynchronization;
		timeSynchronization = newTimeSynchronization;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION, oldTimeSynchronization, timeSynchronization));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence getMutualLoadFunction() {
		if (mutualLoadFunction != null && mutualLoadFunction.eIsProxy()) {
			InternalEObject oldMutualLoadFunction = (InternalEObject)mutualLoadFunction;
			mutualLoadFunction = (Sequence)eResolveProxy(oldMutualLoadFunction);
			if (mutualLoadFunction != oldMutualLoadFunction) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION, oldMutualLoadFunction, mutualLoadFunction));
			}
		}
		return mutualLoadFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sequence basicGetMutualLoadFunction() {
		return mutualLoadFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMutualLoadFunction(Sequence newMutualLoadFunction) {
		Sequence oldMutualLoadFunction = mutualLoadFunction;
		mutualLoadFunction = newMutualLoadFunction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION, oldMutualLoadFunction, mutualLoadFunction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(int newPriority) {
		int oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK:
				if (resolve) return getWork();
				return basicGetWork();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME:
				return getWorkStartTime();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION:
				return getWorkDuration();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION:
				if (resolve) return getTimeSynchronization();
				return basicGetTimeSynchronization();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION:
				if (resolve) return getMutualLoadFunction();
				return basicGetMutualLoadFunction();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				return getPriority();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK:
				setWork((UsageScenario)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME:
				setWorkStartTime((Double)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION:
				setWorkDuration((Double)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION:
				setTimeSynchronization((TimeDependentWorkFunctionContainer)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION:
				setMutualLoadFunction((Sequence)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				setPriority((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK:
				setWork((UsageScenario)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME:
				setWorkStartTime(WORK_START_TIME_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION:
				setWorkDuration(WORK_DURATION_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION:
				setTimeSynchronization((TimeDependentWorkFunctionContainer)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION:
				setMutualLoadFunction((Sequence)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK:
				return work != null;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_START_TIME:
				return workStartTime != WORK_START_TIME_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_DURATION:
				return workDuration != WORK_DURATION_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__TIME_SYNCHRONIZATION:
				return timeSynchronization != null;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__MUTUAL_LOAD_FUNCTION:
				return mutualLoadFunction != null;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				return priority != PRIORITY_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (workStartTime: ");
		result.append(workStartTime);
		result.append(", workDuration: ");
		result.append(workDuration);
		result.append(", priority: ");
		result.append(priority);
		result.append(')');
		return result.toString();
	}

} //TimeDependentWorkFunctionContainerImpl
