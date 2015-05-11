/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Megamodel;
import eurema.ModelUse;
import eurema.OperationBehavior;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Behavior</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.OperationBehaviorImpl#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.impl.OperationBehaviorImpl#getModelUsages <em>Model Usages</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OperationBehaviorImpl extends OperationImpl implements OperationBehavior {
	/**
	 * The cached value of the '{@link #getModelUsages() <em>Model Usages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelUsages()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelUse> modelUsages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationBehaviorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.OPERATION_BEHAVIOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel getMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL) return null;
		return (Megamodel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel basicGetMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL) return null;
		return (Megamodel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMegamodel(Megamodel newMegamodel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMegamodel, EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMegamodel(Megamodel newMegamodel) {
		if (newMegamodel != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL && newMegamodel != null)) {
			if (EcoreUtil.isAncestor(this, newMegamodel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMegamodel != null)
				msgs = ((InternalEObject)newMegamodel).eInverseAdd(this, EuremaPackage.MEGAMODEL__BEHAVIOR, Megamodel.class, msgs);
			msgs = basicSetMegamodel(newMegamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL, newMegamodel, newMegamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelUse> getModelUsages() {
		if (modelUsages == null) {
			modelUsages = new EObjectContainmentWithInverseEList.Resolving<ModelUse>(ModelUse.class, this, EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES, EuremaPackage.MODEL_USE__OPERATION);
		}
		return modelUsages;
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMegamodel((Megamodel)otherEnd, msgs);
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getModelUsages()).basicAdd(otherEnd, msgs);
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				return basicSetMegamodel(null, msgs);
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				return ((InternalEList<?>)getModelUsages()).basicRemove(otherEnd, msgs);
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.MEGAMODEL__BEHAVIOR, Megamodel.class, msgs);
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				if (resolve) return getMegamodel();
				return basicGetMegamodel();
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				return getModelUsages();
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				setMegamodel((Megamodel)newValue);
				return;
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				getModelUsages().clear();
				getModelUsages().addAll((Collection<? extends ModelUse>)newValue);
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				setMegamodel((Megamodel)null);
				return;
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				getModelUsages().clear();
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
			case EuremaPackage.OPERATION_BEHAVIOR__MEGAMODEL:
				return basicGetMegamodel() != null;
			case EuremaPackage.OPERATION_BEHAVIOR__MODEL_USAGES:
				return modelUsages != null && !modelUsages.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationBehaviorImpl
