/**
 */
package eurema.impl;

import eurema.DecisionOperation;
import eurema.EuremaPackage;
import eurema.Megamodel;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Decision Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.DecisionOperationImpl#getMegamodel <em>Megamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DecisionOperationImpl extends ControlOperationImpl implements DecisionOperation {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DecisionOperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.DECISION_OPERATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel getMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.DECISION_OPERATION__MEGAMODEL) return null;
		return (Megamodel)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel basicGetMegamodel() {
		if (eContainerFeatureID() != EuremaPackage.DECISION_OPERATION__MEGAMODEL) return null;
		return (Megamodel)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMegamodel(Megamodel newMegamodel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newMegamodel, EuremaPackage.DECISION_OPERATION__MEGAMODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMegamodel(Megamodel newMegamodel) {
		if (newMegamodel != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.DECISION_OPERATION__MEGAMODEL && newMegamodel != null)) {
			if (EcoreUtil.isAncestor(this, newMegamodel))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newMegamodel != null)
				msgs = ((InternalEObject)newMegamodel).eInverseAdd(this, EuremaPackage.MEGAMODEL__DECISION_OPERATIONS, Megamodel.class, msgs);
			msgs = basicSetMegamodel(newMegamodel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.DECISION_OPERATION__MEGAMODEL, newMegamodel, newMegamodel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetMegamodel((Megamodel)otherEnd, msgs);
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				return basicSetMegamodel(null, msgs);
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.MEGAMODEL__DECISION_OPERATIONS, Megamodel.class, msgs);
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				if (resolve) return getMegamodel();
				return basicGetMegamodel();
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				setMegamodel((Megamodel)newValue);
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				setMegamodel((Megamodel)null);
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
			case EuremaPackage.DECISION_OPERATION__MEGAMODEL:
				return basicGetMegamodel() != null;
		}
		return super.eIsSet(featureID);
	}

} //DecisionOperationImpl
