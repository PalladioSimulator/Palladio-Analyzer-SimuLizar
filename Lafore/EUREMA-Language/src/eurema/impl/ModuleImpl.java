/**
 */
package eurema.impl;

import eurema.Effecting;
import eurema.EuremaPackage;
import eurema.Layer;
import eurema.Module;
import eurema.Sensing;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link eurema.impl.ModuleImpl#getUid <em>Uid</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getName <em>Name</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getSenses <em>Senses</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getSensedBy <em>Sensed By</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getEffects <em>Effects</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getEffectedBy <em>Effected By</em>}</li>
 *   <li>{@link eurema.impl.ModuleImpl#getLayer <em>Layer</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ModuleImpl extends MinimalEObjectImpl.Container implements Module {
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSenses() <em>Senses</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSenses()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensing> senses;

	/**
	 * The cached value of the '{@link #getSensedBy() <em>Sensed By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSensedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensing> sensedBy;

	/**
	 * The cached value of the '{@link #getEffects() <em>Effects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffects()
	 * @generated
	 * @ordered
	 */
	protected EList<Effecting> effects;

	/**
	 * The cached value of the '{@link #getEffectedBy() <em>Effected By</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEffectedBy()
	 * @generated
	 * @ordered
	 */
	protected EList<Effecting> effectedBy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EuremaPackage.Literals.MODULE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODULE__UID, oldUid, uid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODULE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sensing> getSenses() {
		if (senses == null) {
			senses = new EObjectContainmentWithInverseEList.Resolving<Sensing>(Sensing.class, this, EuremaPackage.MODULE__SENSES, EuremaPackage.SENSING__SOURCE);
		}
		return senses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Sensing> getSensedBy() {
		if (sensedBy == null) {
			sensedBy = new EObjectWithInverseResolvingEList<Sensing>(Sensing.class, this, EuremaPackage.MODULE__SENSED_BY, EuremaPackage.SENSING__TARGET);
		}
		return sensedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Effecting> getEffects() {
		if (effects == null) {
			effects = new EObjectContainmentWithInverseEList.Resolving<Effecting>(Effecting.class, this, EuremaPackage.MODULE__EFFECTS, EuremaPackage.EFFECTING__SOURCE);
		}
		return effects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Effecting> getEffectedBy() {
		if (effectedBy == null) {
			effectedBy = new EObjectWithInverseResolvingEList<Effecting>(Effecting.class, this, EuremaPackage.MODULE__EFFECTED_BY, EuremaPackage.EFFECTING__TARGET);
		}
		return effectedBy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Layer getLayer() {
		if (eContainerFeatureID() != EuremaPackage.MODULE__LAYER) return null;
		return (Layer)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Layer basicGetLayer() {
		if (eContainerFeatureID() != EuremaPackage.MODULE__LAYER) return null;
		return (Layer)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLayer(Layer newLayer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newLayer, EuremaPackage.MODULE__LAYER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLayer(Layer newLayer) {
		if (newLayer != eInternalContainer() || (eContainerFeatureID() != EuremaPackage.MODULE__LAYER && newLayer != null)) {
			if (EcoreUtil.isAncestor(this, newLayer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newLayer != null)
				msgs = ((InternalEObject)newLayer).eInverseAdd(this, EuremaPackage.LAYER__MODULES, Layer.class, msgs);
			msgs = basicSetLayer(newLayer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EuremaPackage.MODULE__LAYER, newLayer, newLayer));
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
			case EuremaPackage.MODULE__SENSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSenses()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MODULE__SENSED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSensedBy()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MODULE__EFFECTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEffects()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MODULE__EFFECTED_BY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEffectedBy()).basicAdd(otherEnd, msgs);
			case EuremaPackage.MODULE__LAYER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetLayer((Layer)otherEnd, msgs);
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
			case EuremaPackage.MODULE__SENSES:
				return ((InternalEList<?>)getSenses()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MODULE__SENSED_BY:
				return ((InternalEList<?>)getSensedBy()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MODULE__EFFECTS:
				return ((InternalEList<?>)getEffects()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MODULE__EFFECTED_BY:
				return ((InternalEList<?>)getEffectedBy()).basicRemove(otherEnd, msgs);
			case EuremaPackage.MODULE__LAYER:
				return basicSetLayer(null, msgs);
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
			case EuremaPackage.MODULE__LAYER:
				return eInternalContainer().eInverseRemove(this, EuremaPackage.LAYER__MODULES, Layer.class, msgs);
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
			case EuremaPackage.MODULE__UID:
				return getUid();
			case EuremaPackage.MODULE__NAME:
				return getName();
			case EuremaPackage.MODULE__SENSES:
				return getSenses();
			case EuremaPackage.MODULE__SENSED_BY:
				return getSensedBy();
			case EuremaPackage.MODULE__EFFECTS:
				return getEffects();
			case EuremaPackage.MODULE__EFFECTED_BY:
				return getEffectedBy();
			case EuremaPackage.MODULE__LAYER:
				if (resolve) return getLayer();
				return basicGetLayer();
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
			case EuremaPackage.MODULE__UID:
				setUid((String)newValue);
				return;
			case EuremaPackage.MODULE__NAME:
				setName((String)newValue);
				return;
			case EuremaPackage.MODULE__SENSES:
				getSenses().clear();
				getSenses().addAll((Collection<? extends Sensing>)newValue);
				return;
			case EuremaPackage.MODULE__SENSED_BY:
				getSensedBy().clear();
				getSensedBy().addAll((Collection<? extends Sensing>)newValue);
				return;
			case EuremaPackage.MODULE__EFFECTS:
				getEffects().clear();
				getEffects().addAll((Collection<? extends Effecting>)newValue);
				return;
			case EuremaPackage.MODULE__EFFECTED_BY:
				getEffectedBy().clear();
				getEffectedBy().addAll((Collection<? extends Effecting>)newValue);
				return;
			case EuremaPackage.MODULE__LAYER:
				setLayer((Layer)newValue);
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
			case EuremaPackage.MODULE__UID:
				setUid(UID_EDEFAULT);
				return;
			case EuremaPackage.MODULE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EuremaPackage.MODULE__SENSES:
				getSenses().clear();
				return;
			case EuremaPackage.MODULE__SENSED_BY:
				getSensedBy().clear();
				return;
			case EuremaPackage.MODULE__EFFECTS:
				getEffects().clear();
				return;
			case EuremaPackage.MODULE__EFFECTED_BY:
				getEffectedBy().clear();
				return;
			case EuremaPackage.MODULE__LAYER:
				setLayer((Layer)null);
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
			case EuremaPackage.MODULE__UID:
				return UID_EDEFAULT == null ? uid != null : !UID_EDEFAULT.equals(uid);
			case EuremaPackage.MODULE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EuremaPackage.MODULE__SENSES:
				return senses != null && !senses.isEmpty();
			case EuremaPackage.MODULE__SENSED_BY:
				return sensedBy != null && !sensedBy.isEmpty();
			case EuremaPackage.MODULE__EFFECTS:
				return effects != null && !effects.isEmpty();
			case EuremaPackage.MODULE__EFFECTED_BY:
				return effectedBy != null && !effectedBy.isEmpty();
			case EuremaPackage.MODULE__LAYER:
				return basicGetLayer() != null;
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
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ModuleImpl
