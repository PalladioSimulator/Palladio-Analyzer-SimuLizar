/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.InitialOperation;
import eurema.MegamodelModule;
import eurema.MegamodelModuleTrigger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Megamodel Module Trigger</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.MegamodelModuleTriggerImpl#getInitialOperation <em>Initial Operation</em>}</li>
 *   <li>{@link eurema.impl.MegamodelModuleTriggerImpl#getModule <em>Module</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MegamodelModuleTriggerImpl extends TriggerImpl implements MegamodelModuleTrigger {
	/**
	 * The cached value of the '{@link #getInitialOperation() <em>Initial Operation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitialOperation()
	 * @generated
	 * @ordered
	 */
	protected InitialOperation initialOperation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MegamodelModuleTriggerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MEGAMODEL_MODULE_TRIGGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialOperation getInitialOperation() {
		if (initialOperation != null && initialOperation.eIsProxy()) {
			InternalEObject oldInitialOperation = (InternalEObject)initialOperation;
			initialOperation = (InitialOperation)eResolveProxy(oldInitialOperation);
			if (initialOperation != oldInitialOperation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION, oldInitialOperation, initialOperation));
			}
		}
		return initialOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialOperation basicGetInitialOperation() {
		return initialOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialOperation(InitialOperation newInitialOperation) {
		InitialOperation oldInitialOperation = initialOperation;
		initialOperation = newInitialOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION, oldInitialOperation, initialOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule getModule() {
		if (eContainerFeatureID() != EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE) return null;
		return (MegamodelModule)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule basicGetModule() {
		if (eContainerFeatureID() != EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE) return null;
		return (MegamodelModule)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModule(MegamodelModule newModule, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newModule, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModule(MegamodelModule newModule) {
		if (newModule != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE && newModule != null)) {
			if (EcoreUtil.isAncestor(this, newModule))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newModule != null)
				msgs = ((InternalEObject)newModule).eInverseAdd(this, EuremaPackage.MEGAMODEL_MODULE__TRIGGER, MegamodelModule.class, msgs);
			msgs = basicSetModule(newModule, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE, newModule, newModule));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetModule((MegamodelModule)otherEnd, msgs);
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.MEGAMODEL_MODULE__TRIGGER, MegamodelModule.class, msgs);
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION:
				if (resolve) return getInitialOperation();
				return basicGetInitialOperation();
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION:
				setInitialOperation((InitialOperation)newValue);
				return;
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
				setModule((MegamodelModule)newValue);
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION:
				setInitialOperation((InitialOperation)null);
				return;
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
				setModule((MegamodelModule)null);
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
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION:
				return initialOperation != null;
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER__MODULE:
				return basicGetModule() != null;
		}
		return super.eIsSet(featureID);
	}

} //MegamodelModuleTriggerImpl
