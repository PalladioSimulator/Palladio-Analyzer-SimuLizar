/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Executable;
import eurema.ExecutionContext;
import eurema.ExecutionInformation;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Information</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ExecutionInformationImpl#getCount <em>Count</em>}</li>
 *   <li>{@link eurema.impl.ExecutionInformationImpl#getTime <em>Time</em>}</li>
 *   <li>{@link eurema.impl.ExecutionInformationImpl#getExecutable <em>Executable</em>}</li>
 *   <li>{@link eurema.impl.ExecutionInformationImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionInformationImpl extends MinimalEObjectImpl.Container implements ExecutionInformation {
	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final int COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected int count = COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected static final long TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected long time = TIME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecutable() <em>Executable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutable()
	 * @generated
	 * @ordered
	 */
	protected Executable executable;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionInformationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.EXECUTION_INFORMATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCount(int newCount) {
		int oldCount = count;
		count = newCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_INFORMATION__COUNT, oldCount, count));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getTime() {
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(long newTime) {
		long oldTime = time;
		time = newTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_INFORMATION__TIME, oldTime, time));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executable getExecutable() {
		if (executable != null && executable.eIsProxy()) {
			InternalEObject oldExecutable = (InternalEObject)executable;
			executable = (Executable)eResolveProxy(oldExecutable);
			if (executable != oldExecutable) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, oldExecutable, executable));
			}
		}
		return executable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executable basicGetExecutable() {
		return executable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecutable(Executable newExecutable, NotificationChain msgs) {
		Executable oldExecutable = executable;
		executable = newExecutable;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, oldExecutable, newExecutable);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutable(Executable newExecutable) {
		if (newExecutable != executable) {
			NotificationChain msgs = null;
			if (executable != null)
				msgs = ((InternalEObject)executable).eInverseRemove(this, EuremaPackage.EXECUTABLE__EXEC_INFO, Executable.class, msgs);
			if (newExecutable != null)
				msgs = ((InternalEObject)newExecutable).eInverseAdd(this, EuremaPackage.EXECUTABLE__EXEC_INFO, Executable.class, msgs);
			msgs = basicSetExecutable(newExecutable, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, newExecutable, newExecutable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext getContext() {
		if (eContainerFeatureID() != EuremaPackage.EXECUTION_INFORMATION__CONTEXT) return null;
		return (ExecutionContext)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext basicGetContext() {
		if (eContainerFeatureID() != EuremaPackage.EXECUTION_INFORMATION__CONTEXT) return null;
		return (ExecutionContext)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContext(ExecutionContext newContext, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContext, EuremaPackage.EXECUTION_INFORMATION__CONTEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setContext(ExecutionContext newContext) {
		if (newContext != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.EXECUTION_INFORMATION__CONTEXT && newContext != null)) {
			if (EcoreUtil.isAncestor(this, newContext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContext != null)
				msgs = ((InternalEObject)newContext).eInverseAdd(this, EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS, ExecutionContext.class, msgs);
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_INFORMATION__CONTEXT, newContext, newContext));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				if (executable != null)
					msgs = ((InternalEObject)executable).eInverseRemove(this, EuremaPackage.EXECUTABLE__EXEC_INFO, Executable.class, msgs);
				return basicSetExecutable((Executable)otherEnd, msgs);
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContext((ExecutionContext)otherEnd, msgs);
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
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				return basicSetExecutable(null, msgs);
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				return basicSetContext(null, msgs);
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
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS, ExecutionContext.class, msgs);
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
			case EuremaPackage.EXECUTION_INFORMATION__COUNT:
				return getCount();
			case EuremaPackage.EXECUTION_INFORMATION__TIME:
				return getTime();
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				if (resolve) return getExecutable();
				return basicGetExecutable();
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				if (resolve) return getContext();
				return basicGetContext();
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
			case EuremaPackage.EXECUTION_INFORMATION__COUNT:
				setCount((Integer)newValue);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__TIME:
				setTime((Long)newValue);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				setExecutable((Executable)newValue);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				setContext((ExecutionContext)newValue);
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
			case EuremaPackage.EXECUTION_INFORMATION__COUNT:
				setCount(COUNT_EDEFAULT);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__TIME:
				setTime(TIME_EDEFAULT);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				setExecutable((Executable)null);
				return;
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				setContext((ExecutionContext)null);
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
			case EuremaPackage.EXECUTION_INFORMATION__COUNT:
				return count != COUNT_EDEFAULT;
			case EuremaPackage.EXECUTION_INFORMATION__TIME:
				return time != TIME_EDEFAULT;
			case EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE:
				return executable != null;
			case EuremaPackage.EXECUTION_INFORMATION__CONTEXT:
				return basicGetContext() != null;
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
		result.append(" (count: ");
		result.append(count);
		result.append(", time: ");
		result.append(time);
		result.append(')');
		return result.toString();
	}

} //ExecutionInformationImpl
