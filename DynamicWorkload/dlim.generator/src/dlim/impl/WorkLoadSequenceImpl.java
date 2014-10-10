/**
 */
package dlim.impl;

import dlim.DlimPackage;
import dlim.ReferenceClockObject;
import dlim.TimeDependentWorkFunctionContainer;
import dlim.WorkLoadSequence;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Work Load Sequence</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getWorkFunctionContainers <em>Work Function Containers</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getName <em>Name</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getTerminateAfterTime <em>Terminate After Time</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getReferenceClock <em>Reference Clock</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getTerminateAfterLoops <em>Terminate After Loops</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getFirstIterationStart <em>First Iteration Start</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getFirstIterationEnd <em>First Iteration End</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getLoopDuration <em>Loop Duration</em>}</li>
 *   <li>{@link dlim.impl.WorkLoadSequenceImpl#getFinalDuration <em>Final Duration</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkLoadSequenceImpl extends MinimalEObjectImpl.Container implements WorkLoadSequence {
	/**
	 * The cached value of the '{@link #getWorkFunctionContainers() <em>Work Function Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkFunctionContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<TimeDependentWorkFunctionContainer> workFunctionContainers;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTerminateAfterTime() <em>Terminate After Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminateAfterTime()
	 * @generated
	 * @ordered
	 */
	protected static final double TERMINATE_AFTER_TIME_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getTerminateAfterTime() <em>Terminate After Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminateAfterTime()
	 * @generated
	 * @ordered
	 */
	protected double terminateAfterTime = TERMINATE_AFTER_TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferenceClock() <em>Reference Clock</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferenceClock()
	 * @generated
	 * @ordered
	 */
	protected ReferenceClockObject referenceClock;

	/**
	 * The default value of the '{@link #getTerminateAfterLoops() <em>Terminate After Loops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminateAfterLoops()
	 * @generated
	 * @ordered
	 */
	protected static final int TERMINATE_AFTER_LOOPS_EDEFAULT = -1;

	/**
	 * The cached value of the '{@link #getTerminateAfterLoops() <em>Terminate After Loops</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTerminateAfterLoops()
	 * @generated
	 * @ordered
	 */
	protected int terminateAfterLoops = TERMINATE_AFTER_LOOPS_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstIterationStart() <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationStart()
	 * @generated
	 * @ordered
	 */
	protected static final double FIRST_ITERATION_START_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFirstIterationStart() <em>First Iteration Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationStart()
	 * @generated
	 * @ordered
	 */
	protected double firstIterationStart = FIRST_ITERATION_START_EDEFAULT;

	/**
	 * The default value of the '{@link #getFirstIterationEnd() <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationEnd()
	 * @generated
	 * @ordered
	 */
	protected static final double FIRST_ITERATION_END_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFirstIterationEnd() <em>First Iteration End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstIterationEnd()
	 * @generated
	 * @ordered
	 */
	protected double firstIterationEnd = FIRST_ITERATION_END_EDEFAULT;

	/**
	 * The default value of the '{@link #getLoopDuration() <em>Loop Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopDuration()
	 * @generated
	 * @ordered
	 */
	protected static final double LOOP_DURATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getLoopDuration() <em>Loop Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoopDuration()
	 * @generated
	 * @ordered
	 */
	protected double loopDuration = LOOP_DURATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getFinalDuration() <em>Final Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinalDuration()
	 * @generated
	 * @ordered
	 */
	protected static final double FINAL_DURATION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getFinalDuration() <em>Final Duration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinalDuration()
	 * @generated
	 * @ordered
	 */
	protected double finalDuration = FINAL_DURATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorkLoadSequenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DlimPackage.Literals.WORK_LOAD_SEQUENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TimeDependentWorkFunctionContainer> getWorkFunctionContainers() {
		if (workFunctionContainers == null) {
			workFunctionContainers = new EObjectContainmentEList<TimeDependentWorkFunctionContainer>(TimeDependentWorkFunctionContainer.class, this, DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS);
		}
		return workFunctionContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getTerminateAfterTime() {
		return terminateAfterTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminateAfterTime(double newTerminateAfterTime) {
		double oldTerminateAfterTime = terminateAfterTime;
		terminateAfterTime = newTerminateAfterTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME, oldTerminateAfterTime, terminateAfterTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceClockObject getReferenceClock() {
		return referenceClock;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReferenceClock(ReferenceClockObject newReferenceClock, NotificationChain msgs) {
		ReferenceClockObject oldReferenceClock = referenceClock;
		referenceClock = newReferenceClock;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK, oldReferenceClock, newReferenceClock);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferenceClock(ReferenceClockObject newReferenceClock) {
		if (newReferenceClock != referenceClock) {
			NotificationChain msgs = null;
			if (referenceClock != null)
				msgs = ((InternalEObject)referenceClock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK, null, msgs);
			if (newReferenceClock != null)
				msgs = ((InternalEObject)newReferenceClock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK, null, msgs);
			msgs = basicSetReferenceClock(newReferenceClock, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK, newReferenceClock, newReferenceClock));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getTerminateAfterLoops() {
		return terminateAfterLoops;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTerminateAfterLoops(int newTerminateAfterLoops) {
		int oldTerminateAfterLoops = terminateAfterLoops;
		terminateAfterLoops = newTerminateAfterLoops;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS, oldTerminateAfterLoops, terminateAfterLoops));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFirstIterationStart() {
		return firstIterationStart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstIterationStart(double newFirstIterationStart) {
		double oldFirstIterationStart = firstIterationStart;
		firstIterationStart = newFirstIterationStart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START, oldFirstIterationStart, firstIterationStart));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFirstIterationEnd() {
		return firstIterationEnd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstIterationEnd(double newFirstIterationEnd) {
		double oldFirstIterationEnd = firstIterationEnd;
		firstIterationEnd = newFirstIterationEnd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END, oldFirstIterationEnd, firstIterationEnd));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getLoopDuration() {
		return loopDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoopDuration(double newLoopDuration) {
		double oldLoopDuration = loopDuration;
		loopDuration = newLoopDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION, oldLoopDuration, loopDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getFinalDuration() {
		return finalDuration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinalDuration(double newFinalDuration) {
		double oldFinalDuration = finalDuration;
		finalDuration = newFinalDuration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION, oldFinalDuration, finalDuration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS:
				return ((InternalEList<?>)getWorkFunctionContainers()).basicRemove(otherEnd, msgs);
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				return basicSetReferenceClock(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS:
				return getWorkFunctionContainers();
			case DlimPackage.WORK_LOAD_SEQUENCE__NAME:
				return getName();
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME:
				return getTerminateAfterTime();
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				return getReferenceClock();
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS:
				return getTerminateAfterLoops();
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START:
				return getFirstIterationStart();
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END:
				return getFirstIterationEnd();
			case DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION:
				return getLoopDuration();
			case DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION:
				return getFinalDuration();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS:
				getWorkFunctionContainers().clear();
				getWorkFunctionContainers().addAll((Collection<? extends TimeDependentWorkFunctionContainer>)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__NAME:
				setName((String)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME:
				setTerminateAfterTime((Double)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				setReferenceClock((ReferenceClockObject)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS:
				setTerminateAfterLoops((Integer)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START:
				setFirstIterationStart((Double)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END:
				setFirstIterationEnd((Double)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION:
				setLoopDuration((Double)newValue);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION:
				setFinalDuration((Double)newValue);
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
			case DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS:
				getWorkFunctionContainers().clear();
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME:
				setTerminateAfterTime(TERMINATE_AFTER_TIME_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				setReferenceClock((ReferenceClockObject)null);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS:
				setTerminateAfterLoops(TERMINATE_AFTER_LOOPS_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START:
				setFirstIterationStart(FIRST_ITERATION_START_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END:
				setFirstIterationEnd(FIRST_ITERATION_END_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION:
				setLoopDuration(LOOP_DURATION_EDEFAULT);
				return;
			case DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION:
				setFinalDuration(FINAL_DURATION_EDEFAULT);
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
			case DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS:
				return workFunctionContainers != null && !workFunctionContainers.isEmpty();
			case DlimPackage.WORK_LOAD_SEQUENCE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_TIME:
				return terminateAfterTime != TERMINATE_AFTER_TIME_EDEFAULT;
			case DlimPackage.WORK_LOAD_SEQUENCE__REFERENCE_CLOCK:
				return referenceClock != null;
			case DlimPackage.WORK_LOAD_SEQUENCE__TERMINATE_AFTER_LOOPS:
				return terminateAfterLoops != TERMINATE_AFTER_LOOPS_EDEFAULT;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_START:
				return firstIterationStart != FIRST_ITERATION_START_EDEFAULT;
			case DlimPackage.WORK_LOAD_SEQUENCE__FIRST_ITERATION_END:
				return firstIterationEnd != FIRST_ITERATION_END_EDEFAULT;
			case DlimPackage.WORK_LOAD_SEQUENCE__LOOP_DURATION:
				return loopDuration != LOOP_DURATION_EDEFAULT;
			case DlimPackage.WORK_LOAD_SEQUENCE__FINAL_DURATION:
				return finalDuration != FINAL_DURATION_EDEFAULT;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", terminateAfterTime: ");
		result.append(terminateAfterTime);
		result.append(", terminateAfterLoops: ");
		result.append(terminateAfterLoops);
		result.append(", firstIterationStart: ");
		result.append(firstIterationStart);
		result.append(", firstIterationEnd: ");
		result.append(firstIterationEnd);
		result.append(", loopDuration: ");
		result.append(loopDuration);
		result.append(", finalDuration: ");
		result.append(finalDuration);
		result.append(')');
		return result.toString();
	}

} //WorkLoadSequenceImpl
