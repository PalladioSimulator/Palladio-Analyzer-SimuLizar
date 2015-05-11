/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.SoftwareModule;
import eurema.SoftwareModuleTrigger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Software Module Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.SoftwareModuleTriggerImpl#isIsNative <em>Is Native</em>}</li>
 *   <li>{@link eurema.impl.SoftwareModuleTriggerImpl#getExecutionMethod <em>Execution Method</em>}</li>
 *   <li>{@link eurema.impl.SoftwareModuleTriggerImpl#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoftwareModuleTriggerImpl extends TriggerImpl implements SoftwareModuleTrigger {
	/**
	 * The default value of the '{@link #isIsNative() <em>Is Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_NATIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsNative() <em>Is Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsNative()
	 * @generated
	 * @ordered
	 */
	protected boolean isNative = IS_NATIVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getExecutionMethod() <em>Execution Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMethod()
	 * @generated
	 * @ordered
	 */
	protected static final String EXECUTION_METHOD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getExecutionMethod() <em>Execution Method</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExecutionMethod()
	 * @generated
	 * @ordered
	 */
	protected String executionMethod = EXECUTION_METHOD_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareModuleTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.SOFTWARE_MODULE_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsNative() {
		return isNative;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsNative(boolean newIsNative) {
		boolean oldIsNative = isNative;
		isNative = newIsNative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE_TRIGGER__IS_NATIVE, oldIsNative, isNative));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getExecutionMethod() {
		return executionMethod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExecutionMethod(String newExecutionMethod) {
		String oldExecutionMethod = executionMethod;
		executionMethod = newExecutionMethod;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD, oldExecutionMethod, executionMethod));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModule getModule() {
		if (eContainerFeatureID() != EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE) return null;
		return (SoftwareModule)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModule basicGetModule() {
		if (eContainerFeatureID() != EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE) return null;
		return (SoftwareModule)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModule(SoftwareModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModule(SoftwareModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, EuremaPackage.SOFTWARE_MODULE__TRIGGER, SoftwareModule.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, newModule, newModule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((SoftwareModule)otherEnd, msgs);
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				return basicSetModule(null, msgs);
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.SOFTWARE_MODULE__TRIGGER, SoftwareModule.class, msgs);
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__IS_NATIVE:
				return isIsNative();
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD:
				return getExecutionMethod();
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				if (resolve) return getModule();
				return basicGetModule();
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__IS_NATIVE:
				setIsNative((Boolean)newValue);
				return;
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD:
				setExecutionMethod((String)newValue);
				return;
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				setModule((SoftwareModule)newValue);
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__IS_NATIVE:
				setIsNative(IS_NATIVE_EDEFAULT);
				return;
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD:
				setExecutionMethod(EXECUTION_METHOD_EDEFAULT);
				return;
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				setModule((SoftwareModule)null);
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
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__IS_NATIVE:
				return isNative != IS_NATIVE_EDEFAULT;
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD:
				return EXECUTION_METHOD_EDEFAULT == null ? executionMethod != null : !EXECUTION_METHOD_EDEFAULT.equals(executionMethod);
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE:
				return basicGetModule() != null;
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
		result.append(" (isNative: ");
		result.append(isNative);
		result.append(", executionMethod: ");
		result.append(executionMethod);
		result.append(')');
		return result.toString();
	}

} //SoftwareModuleTriggerImpl
