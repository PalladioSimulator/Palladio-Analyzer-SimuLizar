/**
 */
package org.palladiosimulator.simulizar.action.instance.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.action.core.RoleType;

import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.Role;
import org.palladiosimulator.simulizar.action.instance.RoleSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getRoleSet <em>Role Set</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getRoleType <em>Role Type</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends IdentifierImpl implements Role {
	/**
     * The cached value of the '{@link #getRoleType() <em>Role Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getRoleType()
     * @generated
     * @ordered
     */
	protected RoleType roleType;

	/**
     * The cached value of the '{@link #getValue() <em>Value</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getValue()
     * @generated
     * @ordered
     */
	protected EObject value;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected RoleImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return InstancePackage.Literals.ROLE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public RoleSet getRoleSet() {
        if (eContainerFeatureID() != InstancePackage.ROLE__ROLE_SET) return null;
        return (RoleSet)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetRoleSet(RoleSet newRoleSet, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newRoleSet, InstancePackage.ROLE__ROLE_SET, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRoleSet(RoleSet newRoleSet) {
        if (newRoleSet != eInternalContainer() || (eContainerFeatureID() != InstancePackage.ROLE__ROLE_SET && newRoleSet != null)) {
            if (EcoreUtil.isAncestor(this, newRoleSet))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newRoleSet != null)
                msgs = ((InternalEObject)newRoleSet).eInverseAdd(this, InstancePackage.ROLE_SET__ROLES, RoleSet.class, msgs);
            msgs = basicSetRoleSet(newRoleSet, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE__ROLE_SET, newRoleSet, newRoleSet));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public RoleType getRoleType() {
        if (roleType != null && roleType.eIsProxy()) {
            InternalEObject oldRoleType = (InternalEObject)roleType;
            roleType = (RoleType)eResolveProxy(oldRoleType);
            if (roleType != oldRoleType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstancePackage.ROLE__ROLE_TYPE, oldRoleType, roleType));
            }
        }
        return roleType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public RoleType basicGetRoleType() {
        return roleType;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setRoleType(RoleType newRoleType) {
        RoleType oldRoleType = roleType;
        roleType = newRoleType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE__ROLE_TYPE, oldRoleType, roleType));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject getValue() {
        if (value != null && value.eIsProxy()) {
            InternalEObject oldValue = (InternalEObject)value;
            value = eResolveProxy(oldValue);
            if (value != oldValue) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, InstancePackage.ROLE__VALUE, oldValue, value));
            }
        }
        return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EObject basicGetValue() {
        return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setValue(EObject newValue) {
        EObject oldValue = value;
        value = newValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, InstancePackage.ROLE__VALUE, oldValue, value));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case InstancePackage.ROLE__ROLE_SET:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetRoleSet((RoleSet)otherEnd, msgs);
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
            case InstancePackage.ROLE__ROLE_SET:
                return basicSetRoleSet(null, msgs);
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
            case InstancePackage.ROLE__ROLE_SET:
                return eInternalContainer().eInverseRemove(this, InstancePackage.ROLE_SET__ROLES, RoleSet.class, msgs);
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
            case InstancePackage.ROLE__ROLE_SET:
                return getRoleSet();
            case InstancePackage.ROLE__ROLE_TYPE:
                if (resolve) return getRoleType();
                return basicGetRoleType();
            case InstancePackage.ROLE__VALUE:
                if (resolve) return getValue();
                return basicGetValue();
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
            case InstancePackage.ROLE__ROLE_SET:
                setRoleSet((RoleSet)newValue);
                return;
            case InstancePackage.ROLE__ROLE_TYPE:
                setRoleType((RoleType)newValue);
                return;
            case InstancePackage.ROLE__VALUE:
                setValue((EObject)newValue);
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
            case InstancePackage.ROLE__ROLE_SET:
                setRoleSet((RoleSet)null);
                return;
            case InstancePackage.ROLE__ROLE_TYPE:
                setRoleType((RoleType)null);
                return;
            case InstancePackage.ROLE__VALUE:
                setValue((EObject)null);
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
            case InstancePackage.ROLE__ROLE_SET:
                return getRoleSet() != null;
            case InstancePackage.ROLE__ROLE_TYPE:
                return roleType != null;
            case InstancePackage.ROLE__VALUE:
                return value != null;
        }
        return super.eIsSet(featureID);
    }

} //RoleImpl
