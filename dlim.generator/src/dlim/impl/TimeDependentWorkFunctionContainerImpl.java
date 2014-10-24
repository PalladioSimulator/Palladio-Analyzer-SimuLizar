/**
 */
package dlim.impl;

import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

import dlim.ClockType;
import dlim.DlimPackage;
import dlim.InputParameterCharacterizationContainer;
import dlim.ReferenceClockObject;
import dlim.Sequence;
import dlim.TimeDependentWorkFunctionContainer;

import dlim.WorkLoadSequence;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

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
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getLoadSequence <em>Load Sequence</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getName <em>Name</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getPointOfReferenceClockObject <em>Point Of Reference Clock Object</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getPointOfReferenceClockType <em>Point Of Reference Clock Type</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getParameterCharaterizationContainers <em>Parameter Charaterization Containers</em>}</li>
 *   <li>{@link dlim.impl.TimeDependentWorkFunctionContainerImpl#getWorkLoadSequence_WorkFunctionContainers <em>Work Load Sequence Work Function Containers</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeDependentWorkFunctionContainerImpl extends MinimalEObjectImpl.Container implements TimeDependentWorkFunctionContainer {
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
	protected static final double WORK_DURATION_EDEFAULT = 1.0;

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
	protected TimeDependentWorkFunctionContainer mutualLoadFunction;

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
	 * The cached value of the '{@link #getLoadSequence() <em>Load Sequence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadSequence()
	 * @generated
	 * @ordered
	 */
	protected Sequence loadSequence;

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
	 * The cached value of the '{@link #getPointOfReferenceClockObject() <em>Point Of Reference Clock Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockObject()
	 * @generated
	 * @ordered
	 */
	protected ReferenceClockObject pointOfReferenceClockObject;

	/**
	 * The default value of the '{@link #getPointOfReferenceClockType() <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 * @ordered
	 */
	protected static final ClockType POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT = ClockType.CONTAINER_CLOCK;

	/**
	 * The cached value of the '{@link #getPointOfReferenceClockType() <em>Point Of Reference Clock Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPointOfReferenceClockType()
	 * @generated
	 * @ordered
	 */
	protected ClockType pointOfReferenceClockType = POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getParameterCharaterizationContainers() <em>Parameter Charaterization Containers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterCharaterizationContainers()
	 * @generated
	 * @ordered
	 */
	protected EList<InputParameterCharacterizationContainer> parameterCharaterizationContainers;

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
	public TimeDependentWorkFunctionContainer getMutualLoadFunction() {
		if (mutualLoadFunction != null && mutualLoadFunction.eIsProxy()) {
			InternalEObject oldMutualLoadFunction = (InternalEObject)mutualLoadFunction;
			mutualLoadFunction = (TimeDependentWorkFunctionContainer)eResolveProxy(oldMutualLoadFunction);
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
	public TimeDependentWorkFunctionContainer basicGetMutualLoadFunction() {
		return mutualLoadFunction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMutualLoadFunction(TimeDependentWorkFunctionContainer newMutualLoadFunction) {
		TimeDependentWorkFunctionContainer oldMutualLoadFunction = mutualLoadFunction;
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
	public Sequence getLoadSequence() {
		return loadSequence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLoadSequence(Sequence newLoadSequence, NotificationChain msgs) {
		Sequence oldLoadSequence = loadSequence;
		loadSequence = newLoadSequence;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE, oldLoadSequence, newLoadSequence);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadSequence(Sequence newLoadSequence) {
		if (newLoadSequence != loadSequence) {
			NotificationChain msgs = null;
			if (loadSequence != null)
				msgs = ((InternalEObject)loadSequence).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE, null, msgs);
			if (newLoadSequence != null)
				msgs = ((InternalEObject)newLoadSequence).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE, null, msgs);
			msgs = basicSetLoadSequence(newLoadSequence, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE, newLoadSequence, newLoadSequence));
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
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceClockObject getPointOfReferenceClockObject() {
		if (pointOfReferenceClockObject != null && pointOfReferenceClockObject.eIsProxy()) {
			InternalEObject oldPointOfReferenceClockObject = (InternalEObject)pointOfReferenceClockObject;
			pointOfReferenceClockObject = (ReferenceClockObject)eResolveProxy(oldPointOfReferenceClockObject);
			if (pointOfReferenceClockObject != oldPointOfReferenceClockObject) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT, oldPointOfReferenceClockObject, pointOfReferenceClockObject));
			}
		}
		return pointOfReferenceClockObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceClockObject basicGetPointOfReferenceClockObject() {
		return pointOfReferenceClockObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointOfReferenceClockObject(ReferenceClockObject newPointOfReferenceClockObject) {
		ReferenceClockObject oldPointOfReferenceClockObject = pointOfReferenceClockObject;
		pointOfReferenceClockObject = newPointOfReferenceClockObject;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT, oldPointOfReferenceClockObject, pointOfReferenceClockObject));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClockType getPointOfReferenceClockType() {
		return pointOfReferenceClockType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPointOfReferenceClockType(ClockType newPointOfReferenceClockType) {
		ClockType oldPointOfReferenceClockType = pointOfReferenceClockType;
		pointOfReferenceClockType = newPointOfReferenceClockType == null ? POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT : newPointOfReferenceClockType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE, oldPointOfReferenceClockType, pointOfReferenceClockType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InputParameterCharacterizationContainer> getParameterCharaterizationContainers() {
		if (parameterCharaterizationContainers == null) {
			parameterCharaterizationContainers = new EObjectContainmentWithInverseEList<InputParameterCharacterizationContainer>(InputParameterCharacterizationContainer.class, this, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS, DlimPackage.INPUT_PARAMETER_CHARACTERIZATION_CONTAINER__TDWFC_PARAMETER_CHARACTERIZATION_CONTAINERS);
		}
		return parameterCharaterizationContainers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkLoadSequence getWorkLoadSequence_WorkFunctionContainers() {
		if (eContainerFeatureID() != DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS) return null;
		return (WorkLoadSequence)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkLoadSequence_WorkFunctionContainers(WorkLoadSequence newWorkLoadSequence_WorkFunctionContainers, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWorkLoadSequence_WorkFunctionContainers, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkLoadSequence_WorkFunctionContainers(WorkLoadSequence newWorkLoadSequence_WorkFunctionContainers) {
		if (newWorkLoadSequence_WorkFunctionContainers != eInternalContainer() || (eContainerFeatureID() != DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS && newWorkLoadSequence_WorkFunctionContainers != null)) {
			if (EcoreUtil.isAncestor(this, newWorkLoadSequence_WorkFunctionContainers))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkLoadSequence_WorkFunctionContainers != null)
				msgs = ((InternalEObject)newWorkLoadSequence_WorkFunctionContainers).eInverseAdd(this, DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS, WorkLoadSequence.class, msgs);
			msgs = basicSetWorkLoadSequence_WorkFunctionContainers(newWorkLoadSequence_WorkFunctionContainers, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS, newWorkLoadSequence_WorkFunctionContainers, newWorkLoadSequence_WorkFunctionContainers));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParameterCharaterizationContainers()).basicAdd(otherEnd, msgs);
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWorkLoadSequence_WorkFunctionContainers((WorkLoadSequence)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE:
				return basicSetLoadSequence(null, msgs);
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				return ((InternalEList<?>)getParameterCharaterizationContainers()).basicRemove(otherEnd, msgs);
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				return basicSetWorkLoadSequence_WorkFunctionContainers(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				return eInternalContainer().eInverseRemove(this, DlimPackage.WORK_LOAD_SEQUENCE__WORK_FUNCTION_CONTAINERS, WorkLoadSequence.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE:
				return getLoadSequence();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__NAME:
				return getName();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				if (resolve) return getPointOfReferenceClockObject();
				return basicGetPointOfReferenceClockObject();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				return getPointOfReferenceClockType();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				return getParameterCharaterizationContainers();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				return getWorkLoadSequence_WorkFunctionContainers();
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
				setMutualLoadFunction((TimeDependentWorkFunctionContainer)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				setPriority((Integer)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE:
				setLoadSequence((Sequence)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__NAME:
				setName((String)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				setPointOfReferenceClockObject((ReferenceClockObject)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				setPointOfReferenceClockType((ClockType)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				getParameterCharaterizationContainers().clear();
				getParameterCharaterizationContainers().addAll((Collection<? extends InputParameterCharacterizationContainer>)newValue);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				setWorkLoadSequence_WorkFunctionContainers((WorkLoadSequence)newValue);
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
				setMutualLoadFunction((TimeDependentWorkFunctionContainer)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE:
				setLoadSequence((Sequence)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				setPointOfReferenceClockObject((ReferenceClockObject)null);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				setPointOfReferenceClockType(POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT);
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				getParameterCharaterizationContainers().clear();
				return;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				setWorkLoadSequence_WorkFunctionContainers((WorkLoadSequence)null);
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
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__LOAD_SEQUENCE:
				return loadSequence != null;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_OBJECT:
				return pointOfReferenceClockObject != null;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__POINT_OF_REFERENCE_CLOCK_TYPE:
				return pointOfReferenceClockType != POINT_OF_REFERENCE_CLOCK_TYPE_EDEFAULT;
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__PARAMETER_CHARATERIZATION_CONTAINERS:
				return parameterCharaterizationContainers != null && !parameterCharaterizationContainers.isEmpty();
			case DlimPackage.TIME_DEPENDENT_WORK_FUNCTION_CONTAINER__WORK_LOAD_SEQUENCE_WORK_FUNCTION_CONTAINERS:
				return getWorkLoadSequence_WorkFunctionContainers() != null;
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
		result.append(", name: ");
		result.append(name);
		result.append(", pointOfReferenceClockType: ");
		result.append(pointOfReferenceClockType);
		result.append(')');
		return result.toString();
	}

} //TimeDependentWorkFunctionContainerImpl
