/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Software;
import eurema.SoftwareModule;
import eurema.SoftwareModuleTrigger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Software Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.SoftwareModuleImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link eurema.impl.SoftwareModuleImpl#getTrigger <em>Trigger</em>}</li>
 *   <li>{@link eurema.impl.SoftwareModuleImpl#getSoftware <em>Software</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SoftwareModuleImpl extends ModuleImpl implements SoftwareModule {
	/**
	 * The default value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected String implementation = IMPLEMENTATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTrigger() <em>Trigger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTrigger()
	 * @generated
	 * @ordered
	 */
	protected SoftwareModuleTrigger trigger;

	/**
	 * The cached value of the '{@link #getSoftware() <em>Software</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSoftware()
	 * @generated
	 * @ordered
	 */
	protected Software software;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftwareModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.SOFTWARE_MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(String newImplementation) {
		String oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE__IMPLEMENTATION, oldImplementation, implementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModuleTrigger getTrigger() {
		if (trigger != null && trigger.eIsProxy()) {
			InternalEObject oldTrigger = (InternalEObject)trigger;
			trigger = (SoftwareModuleTrigger)eResolveProxy(oldTrigger);
			if (trigger != oldTrigger) {
				InternalEObject newTrigger = (InternalEObject)trigger;
				NotificationChain msgs =  oldTrigger.eInverseRemove(this, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, SoftwareModuleTrigger.class, null);
				if (newTrigger.eInternalContainer() == null) {
					msgs =  newTrigger.eInverseAdd(this, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, SoftwareModuleTrigger.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.SOFTWARE_MODULE__TRIGGER, oldTrigger, trigger));
			}
		}
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModuleTrigger basicGetTrigger() {
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTrigger(SoftwareModuleTrigger newTrigger, NotificationChain msgs) {
		SoftwareModuleTrigger oldTrigger = trigger;
		trigger = newTrigger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE__TRIGGER, oldTrigger, newTrigger);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTrigger(SoftwareModuleTrigger newTrigger) {
		if (newTrigger != trigger) {
			NotificationChain msgs = null;
			if (trigger != null)
				msgs = ((InternalEObject)trigger).eInverseRemove(this, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, SoftwareModuleTrigger.class, msgs);
			if (newTrigger != null)
				msgs = ((InternalEObject)newTrigger).eInverseAdd(this, EuremaPackage.SOFTWARE_MODULE_TRIGGER__MODULE, SoftwareModuleTrigger.class, msgs);
			msgs = basicSetTrigger(newTrigger, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE__TRIGGER, newTrigger, newTrigger));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Software getSoftware() {
		if (software != null && software.eIsProxy()) {
			InternalEObject oldSoftware = (InternalEObject)software;
			software = (Software)eResolveProxy(oldSoftware);
			if (software != oldSoftware) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.SOFTWARE_MODULE__SOFTWARE, oldSoftware, software));
			}
		}
		return software;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Software basicGetSoftware() {
		return software;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSoftware(Software newSoftware, NotificationChain msgs) {
		Software oldSoftware = software;
		software = newSoftware;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE__SOFTWARE, oldSoftware, newSoftware);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSoftware(Software newSoftware) {
		if (newSoftware != software) {
			NotificationChain msgs = null;
			if (software != null)
				msgs = ((InternalEObject)software).eInverseRemove(this, EuremaPackage.SOFTWARE__MODULES, Software.class, msgs);
			if (newSoftware != null)
				msgs = ((InternalEObject)newSoftware).eInverseAdd(this, EuremaPackage.SOFTWARE__MODULES, Software.class, msgs);
			msgs = basicSetSoftware(newSoftware, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.SOFTWARE_MODULE__SOFTWARE, newSoftware, newSoftware));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				if (trigger != null)
					msgs = ((InternalEObject)trigger).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.SOFTWARE_MODULE__TRIGGER, null, msgs);
				return basicSetTrigger((SoftwareModuleTrigger)otherEnd, msgs);
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				if (software != null)
					msgs = ((InternalEObject)software).eInverseRemove(this, EuremaPackage.SOFTWARE__MODULES, Software.class, msgs);
				return basicSetSoftware((Software)otherEnd, msgs);
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
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				return basicSetTrigger(null, msgs);
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				return basicSetSoftware(null, msgs);
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
			case EuremaPackage.SOFTWARE_MODULE__IMPLEMENTATION:
				return getImplementation();
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				if (resolve) return getTrigger();
				return basicGetTrigger();
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				if (resolve) return getSoftware();
				return basicGetSoftware();
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
			case EuremaPackage.SOFTWARE_MODULE__IMPLEMENTATION:
				setImplementation((String)newValue);
				return;
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				setTrigger((SoftwareModuleTrigger)newValue);
				return;
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				setSoftware((Software)newValue);
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
			case EuremaPackage.SOFTWARE_MODULE__IMPLEMENTATION:
				setImplementation(IMPLEMENTATION_EDEFAULT);
				return;
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				setTrigger((SoftwareModuleTrigger)null);
				return;
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				setSoftware((Software)null);
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
			case EuremaPackage.SOFTWARE_MODULE__IMPLEMENTATION:
				return IMPLEMENTATION_EDEFAULT == null ? implementation != null : !IMPLEMENTATION_EDEFAULT.equals(implementation);
			case EuremaPackage.SOFTWARE_MODULE__TRIGGER:
				return trigger != null;
			case EuremaPackage.SOFTWARE_MODULE__SOFTWARE:
				return software != null;
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
		result.append(" (implementation: ");
		result.append(implementation);
		result.append(')');
		return result.toString();
	}

} //SoftwareModuleImpl
