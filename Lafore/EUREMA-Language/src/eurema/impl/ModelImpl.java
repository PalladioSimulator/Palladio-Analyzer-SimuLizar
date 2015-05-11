/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Megamodel;
import eurema.Model;
import eurema.ModelUse;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ModelImpl#getMegamodel <em>Megamodel</em>}</li>
 *   <li>{@link eurema.impl.ModelImpl#getUsedBy <em>Used By</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ModelImpl extends MegamodelElementImpl implements Model {
	/**
	 * The cached value of the '{@link #getUsedBy() <em>Used By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelUse> usedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel getMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.MODEL__MEGAMODEL) return null;
		return (Megamodel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel basicGetMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.MODEL__MEGAMODEL) return null;
		return (Megamodel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMegamodel(Megamodel newMegamodel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMegamodel, EuremaPackage.MODEL__MEGAMODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMegamodel(Megamodel newMegamodel) {
		if (newMegamodel != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.MODEL__MEGAMODEL && newMegamodel != null)) {
			if (EcoreUtil.isAncestor(this, newMegamodel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMegamodel != null)
				msgs = ((InternalEObject)newMegamodel).eInverseAdd(this, EuremaPackage.MEGAMODEL__MODELS, Megamodel.class, msgs);
			msgs = basicSetMegamodel(newMegamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODEL__MEGAMODEL, newMegamodel, newMegamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModelUse> getUsedBy() {
		if (usedBy == null) {
			usedBy = new EObjectWithInverseResolvingEList<ModelUse>(ModelUse.class, this, EuremaPackage.MODEL__USED_BY, EuremaPackage.MODEL_USE__MODEL);
		}
		return usedBy;
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
			case EuremaPackage.MODEL__MEGAMODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMegamodel((Megamodel)otherEnd, msgs);
			case EuremaPackage.MODEL__USED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsedBy()).basicAdd(otherEnd, msgs);
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
			case EuremaPackage.MODEL__MEGAMODEL:
				return basicSetMegamodel(null, msgs);
			case EuremaPackage.MODEL__USED_BY:
				return ((InternalEList<?>)getUsedBy()).basicRemove(otherEnd, msgs);
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
			case EuremaPackage.MODEL__MEGAMODEL:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.MEGAMODEL__MODELS, Megamodel.class, msgs);
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
			case EuremaPackage.MODEL__MEGAMODEL:
				if (resolve) return getMegamodel();
				return basicGetMegamodel();
			case EuremaPackage.MODEL__USED_BY:
				return getUsedBy();
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
			case EuremaPackage.MODEL__MEGAMODEL:
				setMegamodel((Megamodel)newValue);
				return;
			case EuremaPackage.MODEL__USED_BY:
				getUsedBy().clear();
				getUsedBy().addAll((Collection<? extends ModelUse>)newValue);
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
			case EuremaPackage.MODEL__MEGAMODEL:
				setMegamodel((Megamodel)null);
				return;
			case EuremaPackage.MODEL__USED_BY:
				getUsedBy().clear();
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
			case EuremaPackage.MODEL__MEGAMODEL:
				return basicGetMegamodel() != null;
			case EuremaPackage.MODEL__USED_BY:
				return usedBy != null && !usedBy.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ModelImpl
