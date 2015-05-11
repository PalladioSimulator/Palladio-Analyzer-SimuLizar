/**
 */
package eurema.impl;

import eurema.EuremaPackage;
import eurema.EventType;
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
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Event Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.EventTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link eurema.impl.EventTypeImpl#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link eurema.impl.EventTypeImpl#getSubTypes <em>Sub Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EventTypeImpl extends MinimalEObjectImpl.Container implements
		EventType {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSubTypes() <em>Sub Types</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSubTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<EventType> subTypes;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected EventTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.EVENT_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EVENT_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EventType getSuperType() {
		if (eContainerFeatureID() != EuremaPackage.EVENT_TYPE__SUPER_TYPE) return null;
		return (EventType)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EventType basicGetSuperType() {
		if (eContainerFeatureID() != EuremaPackage.EVENT_TYPE__SUPER_TYPE) return null;
		return (EventType)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSuperType(EventType newSuperType,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSuperType, EuremaPackage.EVENT_TYPE__SUPER_TYPE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSuperType(EventType newSuperType) {
		if (newSuperType != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.EVENT_TYPE__SUPER_TYPE && newSuperType != null)) {
			if (EcoreUtil.isAncestor(this, newSuperType))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSuperType != null)
				msgs = ((InternalEObject)newSuperType).eInverseAdd(this, EuremaPackage.EVENT_TYPE__SUB_TYPES, EventType.class, msgs);
			msgs = basicSetSuperType(newSuperType, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.EVENT_TYPE__SUPER_TYPE, newSuperType, newSuperType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EventType> getSubTypes() {
		if (subTypes == null) {
			subTypes = new EObjectContainmentWithInverseEList.Resolving<EventType>(EventType.class, this, EuremaPackage.EVENT_TYPE__SUB_TYPES, EuremaPackage.EVENT_TYPE__SUPER_TYPE);
		}
		return subTypes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSuperType((EventType)otherEnd, msgs);
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSubTypes()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				return basicSetSuperType(null, msgs);
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				return ((InternalEList<?>)getSubTypes()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.EVENT_TYPE__SUB_TYPES, EventType.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__TYPE:
				return getType();
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				if (resolve) return getSuperType();
				return basicGetSuperType();
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				return getSubTypes();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__TYPE:
				setType((String)newValue);
				return;
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				setSuperType((EventType)newValue);
				return;
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				getSubTypes().clear();
				getSubTypes().addAll((Collection<? extends EventType>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				setSuperType((EventType)null);
				return;
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				getSubTypes().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EuremaPackage.EVENT_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case EuremaPackage.EVENT_TYPE__SUPER_TYPE:
				return basicGetSuperType() != null;
			case EuremaPackage.EVENT_TYPE__SUB_TYPES:
				return subTypes != null && !subTypes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

	@Override
	public int hashCode() {
		String fqn = this.getType();
		if (fqn == null) {
			fqn = "";
		}
		EventType superType = this.getSuperType();
		while (superType != null) {
			fqn = superType.getType() + "." + fqn;
			superType = superType.getSuperType();
		}
		return fqn.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof EventTypeImpl)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		if (this.hashCode() == ((EventTypeImpl)obj).hashCode()) {
			return true;
		} else {
			return false;
		}
		
	}

} // EventTypeImpl
