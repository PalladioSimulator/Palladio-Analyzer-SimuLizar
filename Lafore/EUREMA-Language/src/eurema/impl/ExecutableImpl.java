/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Executable;
import eurema.ExecutionInformation;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Executable</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ExecutableImpl#getExecInfo <em>Exec Info</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ExecutableImpl extends MegamodelElementImpl implements Executable {
	/**
	 * The cached value of the '{@link #getExecInfo() <em>Exec Info</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecInfo()
	 * @generated
	 * @ordered
	 */
	protected ExecutionInformation execInfo;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExecutableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.EXECUTABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionInformation getExecInfo() {
		if (execInfo != null && execInfo.eIsProxy()) {
			InternalEObject oldExecInfo = (InternalEObject)execInfo;
			execInfo = (ExecutionInformation)eResolveProxy(oldExecInfo);
			if (execInfo != oldExecInfo) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.EXECUTABLE__EXEC_INFO, oldExecInfo, execInfo));
			}
		}
		return execInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionInformation basicGetExecInfo() {
		return execInfo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExecInfo(ExecutionInformation newExecInfo, NotificationChain msgs) {
		ExecutionInformation oldExecInfo = execInfo;
		execInfo = newExecInfo;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTABLE__EXEC_INFO, oldExecInfo, newExecInfo);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecInfo(ExecutionInformation newExecInfo) {
		if (newExecInfo != execInfo) {
			NotificationChain msgs = null;
			if (execInfo != null)
				msgs = ((InternalEObject)execInfo).eInverseRemove(this, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, ExecutionInformation.class, msgs);
			if (newExecInfo != null)
				msgs = ((InternalEObject)newExecInfo).eInverseAdd(this, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, ExecutionInformation.class, msgs);
			msgs = basicSetExecInfo(newExecInfo, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXECUTABLE__EXEC_INFO, newExecInfo, newExecInfo));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				if (execInfo != null)
					msgs = ((InternalEObject)execInfo).eInverseRemove(this, EuremaPackage.EXECUTION_INFORMATION__EXECUTABLE, ExecutionInformation.class, msgs);
				return basicSetExecInfo((ExecutionInformation)otherEnd, msgs);
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
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				return basicSetExecInfo(null, msgs);
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
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				if (resolve) return getExecInfo();
				return basicGetExecInfo();
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
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				setExecInfo((ExecutionInformation)newValue);
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
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				setExecInfo((ExecutionInformation)null);
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
			case EuremaPackage.EXECUTABLE__EXEC_INFO:
				return execInfo != null;
		}
		return super.eIsSet(featureID);
	}

} //ExecutableImpl
