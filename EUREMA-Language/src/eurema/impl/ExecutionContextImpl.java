/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Executable;
import eurema.ExecutionContext;
import eurema.ExecutionInformation;
import eurema.MegamodelModule;
import eurema.RuntimeEnvironment;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Execution Context</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ExecutionContextImpl#getCurrent <em>Current</em>}</li>
 *   <li>{@link eurema.impl.ExecutionContextImpl#getExecInfos <em>Exec Infos</em>}</li>
 *   <li>{@link eurema.impl.ExecutionContextImpl#getEnvironment <em>Environment</em>}</li>
 *   <li>{@link eurema.impl.ExecutionContextImpl#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.impl.ExecutionContextImpl#getExecuting <em>Executing</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExecutionContextImpl extends MinimalEObjectImpl.Container implements ExecutionContext {
	/**
	 * The cached value of the '{@link #getCurrent() <em>Current</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCurrent()
	 * @generated
	 * @ordered
	 */
	protected Executable current;

	/**
	 * The cached value of the '{@link #getExecInfos() <em>Exec Infos</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecInfos()
	 * @generated
	 * @ordered
	 */
	protected EList<ExecutionInformation> execInfos;

	/**
	 * The default value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected static final String UID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUid() <em>Uid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUid()
	 * @generated
	 * @ordered
	 */
	protected String uid = UID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getExecuting() <em>Executing</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecuting()
	 * @generated
	 * @ordered
	 */
	protected MegamodelModule executing;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutionContextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.EXECUTION_CONTEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executable getCurrent() {
		if (current != null && current.eIsProxy()) {
			InternalEObject oldCurrent = (InternalEObject)current;
			current = (Executable)eResolveProxy(oldCurrent);
			if (current != oldCurrent) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.EXECUTION_CONTEXT__CURRENT, oldCurrent, current));
			}
		}
		return current;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Executable basicGetCurrent() {
		return current;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCurrent(Executable newCurrent) {
		Executable oldCurrent = current;
		current = newCurrent;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_CONTEXT__CURRENT, oldCurrent, current));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExecutionInformation> getExecInfos() {
		if (execInfos == null) {
			execInfos = new EObjectContainmentWithInverseEList.Resolving<ExecutionInformation>(ExecutionInformation.class, this, EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS, EuremaPackage.EXECUTION_INFORMATION__CONTEXT);
		}
		return execInfos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeEnvironment getEnvironment() {
		if (eContainerFeatureID() != EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT) return null;
		return (RuntimeEnvironment)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeEnvironment basicGetEnvironment() {
		if (eContainerFeatureID() != EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT) return null;
		return (RuntimeEnvironment)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnvironment(RuntimeEnvironment newEnvironment, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newEnvironment, EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvironment(RuntimeEnvironment newEnvironment) {
		if (newEnvironment != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT && newEnvironment != null)) {
			if (EcoreUtil.isAncestor(this, newEnvironment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newEnvironment != null)
				msgs = ((InternalEObject)newEnvironment).eInverseAdd(this, EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS, RuntimeEnvironment.class, msgs);
			msgs = basicSetEnvironment(newEnvironment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT, newEnvironment, newEnvironment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUid(String newUid) {
		String oldUid = uid;
		uid = newUid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_CONTEXT__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule getExecuting() {
		if (executing != null && executing.eIsProxy()) {
			InternalEObject oldExecuting = (InternalEObject)executing;
			executing = (MegamodelModule)eResolveProxy(oldExecuting);
			if (executing != oldExecuting) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, oldExecuting, executing));
			}
		}
		return executing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule basicGetExecuting() {
		return executing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecuting(MegamodelModule newExecuting, NotificationChain msgs) {
		MegamodelModule oldExecuting = executing;
		executing = newExecuting;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, oldExecuting, newExecuting);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecuting(MegamodelModule newExecuting) {
		if (newExecuting != executing) {
			NotificationChain msgs = null;
			if (executing != null)
				msgs = ((InternalEObject)executing).eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, MegamodelModule.class, msgs);
			if (newExecuting != null)
				msgs = ((InternalEObject)newExecuting).eInverseAdd(this, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, MegamodelModule.class, msgs);
			msgs = basicSetExecuting(newExecuting, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTION_CONTEXT__EXECUTING, newExecuting, newExecuting));
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
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExecInfos()).basicAdd(otherEnd, msgs);
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetEnvironment((RuntimeEnvironment)otherEnd, msgs);
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				if (executing != null)
					msgs = ((InternalEObject)executing).eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE__CONTEXT, MegamodelModule.class, msgs);
				return basicSetExecuting((MegamodelModule)otherEnd, msgs);
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
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				return ((InternalEList<?>)getExecInfos()).basicRemove(otherEnd, msgs);
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				return basicSetEnvironment(null, msgs);
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				return basicSetExecuting(null, msgs);
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
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.RUNTIME_ENVIRONMENT__EXECUTIONS, RuntimeEnvironment.class, msgs);
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
			case EuremaPackage.EXECUTION_CONTEXT__CURRENT:
				if (resolve) return getCurrent();
				return basicGetCurrent();
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				return getExecInfos();
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				if (resolve) return getEnvironment();
				return basicGetEnvironment();
			case EuremaPackage.EXECUTION_CONTEXT__UID:
				return getUid();
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				if (resolve) return getExecuting();
				return basicGetExecuting();
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
			case EuremaPackage.EXECUTION_CONTEXT__CURRENT:
				setCurrent((Executable)newValue);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				getExecInfos().clear();
				getExecInfos().addAll((Collection<? extends ExecutionInformation>)newValue);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				setEnvironment((RuntimeEnvironment)newValue);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__UID:
				setUid((String)newValue);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				setExecuting((MegamodelModule)newValue);
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
			case EuremaPackage.EXECUTION_CONTEXT__CURRENT:
				setCurrent((Executable)null);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				getExecInfos().clear();
				return;
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				setEnvironment((RuntimeEnvironment)null);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__UID:
				setUid(UID_EDEFAULT);
				return;
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				setExecuting((MegamodelModule)null);
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
			case EuremaPackage.EXECUTION_CONTEXT__CURRENT:
				return current != null;
			case EuremaPackage.EXECUTION_CONTEXT__EXEC_INFOS:
				return execInfos != null && !execInfos.isEmpty();
			case EuremaPackage.EXECUTION_CONTEXT__ENVIRONMENT:
				return basicGetEnvironment() != null;
			case EuremaPackage.EXECUTION_CONTEXT__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
			case EuremaPackage.EXECUTION_CONTEXT__EXECUTING:
				return executing != null;
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
		result.append(" (uid: ");
		result.append(uid);
		result.append(')');
		return result.toString();
	}

} //ExecutionContextImpl
