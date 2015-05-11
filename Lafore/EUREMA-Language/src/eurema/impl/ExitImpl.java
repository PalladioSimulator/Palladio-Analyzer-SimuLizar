/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.Exit;
import eurema.Operation;
import eurema.Transition;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ExitImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link eurema.impl.ExitImpl#getOutgoing <em>Outgoing</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExitImpl extends MegamodelElementImpl implements Exit {
	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected Transition outgoing;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.EXIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {
		if (eContainerFeatureID() != EuremaPackage.EXIT__OPERATION) return null;
		return (Operation)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation basicGetOperation() {
		if (eContainerFeatureID() != EuremaPackage.EXIT__OPERATION) return null;
		return (Operation)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(Operation newOperation, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newOperation, EuremaPackage.EXIT__OPERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {
		if (newOperation != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.EXIT__OPERATION && newOperation != null)) {
			if (EcoreUtil.isAncestor(this, newOperation))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOperation != null)
				msgs = ((InternalEObject)newOperation).eInverseAdd(this, EuremaPackage.OPERATION__EXITS, Operation.class, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXIT__OPERATION, newOperation, newOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition getOutgoing() {
		if (outgoing != null && outgoing.eIsProxy()) {
			InternalEObject oldOutgoing = (InternalEObject)outgoing;
			outgoing = (Transition)eResolveProxy(oldOutgoing);
			if (outgoing != oldOutgoing) {
				InternalEObject newOutgoing = (InternalEObject)outgoing;
				NotificationChain msgs =  oldOutgoing.eInverseRemove(this, EuremaPackage.TRANSITION__SOURCE, Transition.class, null);
				if (newOutgoing.eInternalContainer() == null) {
					msgs =  newOutgoing.eInverseAdd(this, EuremaPackage.TRANSITION__SOURCE, Transition.class, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EuremaPackage.EXIT__OUTGOING, oldOutgoing, outgoing));
			}
		}
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition basicGetOutgoing() {
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOutgoing(Transition newOutgoing, NotificationChain msgs) {
		Transition oldOutgoing = outgoing;
		outgoing = newOutgoing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EuremaPackage.EXIT__OUTGOING, oldOutgoing, newOutgoing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOutgoing(Transition newOutgoing) {
		if (newOutgoing != outgoing) {
			NotificationChain msgs = null;
			if (outgoing != null)
				msgs = ((InternalEObject)outgoing).eInverseRemove(this, EuremaPackage.TRANSITION__SOURCE, Transition.class, msgs);
			if (newOutgoing != null)
				msgs = ((InternalEObject)newOutgoing).eInverseAdd(this, EuremaPackage.TRANSITION__SOURCE, Transition.class, msgs);
			msgs = basicSetOutgoing(newOutgoing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EXIT__OUTGOING, newOutgoing, newOutgoing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.EXIT__OPERATION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOperation((Operation)otherEnd, msgs);
			case EuremaPackage.EXIT__OUTGOING:
				if (outgoing != null)
					msgs = ((InternalEObject)outgoing).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EuremaPackage.EXIT__OUTGOING, null, msgs);
				return basicSetOutgoing((Transition)otherEnd, msgs);
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
			case EuremaPackage.EXIT__OPERATION:
				return basicSetOperation(null, msgs);
			case EuremaPackage.EXIT__OUTGOING:
				return basicSetOutgoing(null, msgs);
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
			case EuremaPackage.EXIT__OPERATION:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.OPERATION__EXITS, Operation.class, msgs);
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
			case EuremaPackage.EXIT__OPERATION:
				if (resolve) return getOperation();
				return basicGetOperation();
			case EuremaPackage.EXIT__OUTGOING:
				if (resolve) return getOutgoing();
				return basicGetOutgoing();
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
			case EuremaPackage.EXIT__OPERATION:
				setOperation((Operation)newValue);
				return;
			case EuremaPackage.EXIT__OUTGOING:
				setOutgoing((Transition)newValue);
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
			case EuremaPackage.EXIT__OPERATION:
				setOperation((Operation)null);
				return;
			case EuremaPackage.EXIT__OUTGOING:
				setOutgoing((Transition)null);
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
			case EuremaPackage.EXIT__OPERATION:
				return basicGetOperation() != null;
			case EuremaPackage.EXIT__OUTGOING:
				return outgoing != null;
		}
		return super.eIsSet(featureID);
	}

} //ExitImpl
