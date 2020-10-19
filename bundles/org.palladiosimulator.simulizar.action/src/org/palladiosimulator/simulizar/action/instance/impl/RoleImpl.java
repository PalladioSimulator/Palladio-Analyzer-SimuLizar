/**
 */
package org.palladiosimulator.simulizar.action.instance.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.simulizar.action.core.RoleType;
import org.palladiosimulator.simulizar.action.instance.InstancePackage;
import org.palladiosimulator.simulizar.action.instance.Role;
import org.palladiosimulator.simulizar.action.instance.RoleSet;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getRoleSet <em>Role
 * Set</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getRoleType <em>Role
 * Type</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.instance.impl.RoleImpl#getValue
 * <em>Value</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleImpl extends IdentifierImpl implements Role {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RoleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return InstancePackage.Literals.ROLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RoleSet getRoleSet() {
        return (RoleSet) this.eDynamicGet(InstancePackage.ROLE__ROLE_SET, InstancePackage.Literals.ROLE__ROLE_SET, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetRoleSet(final RoleSet newRoleSet, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newRoleSet, InstancePackage.ROLE__ROLE_SET, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRoleSet(final RoleSet newRoleSet) {
        this.eDynamicSet(InstancePackage.ROLE__ROLE_SET, InstancePackage.Literals.ROLE__ROLE_SET, newRoleSet);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public RoleType getRoleType() {
        return (RoleType) this.eDynamicGet(InstancePackage.ROLE__ROLE_TYPE, InstancePackage.Literals.ROLE__ROLE_TYPE,
                true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public RoleType basicGetRoleType() {
        return (RoleType) this.eDynamicGet(InstancePackage.ROLE__ROLE_TYPE, InstancePackage.Literals.ROLE__ROLE_TYPE,
                false, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setRoleType(final RoleType newRoleType) {
        this.eDynamicSet(InstancePackage.ROLE__ROLE_TYPE, InstancePackage.Literals.ROLE__ROLE_TYPE, newRoleType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getValue() {
        return (EObject) this.eDynamicGet(InstancePackage.ROLE__VALUE, InstancePackage.Literals.ROLE__VALUE, true,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetValue() {
        return (EObject) this.eDynamicGet(InstancePackage.ROLE__VALUE, InstancePackage.Literals.ROLE__VALUE, false,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setValue(final EObject newValue) {
        this.eDynamicSet(InstancePackage.ROLE__VALUE, InstancePackage.Literals.ROLE__VALUE, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetRoleSet((RoleSet) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            return this.basicSetRoleSet(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case InstancePackage.ROLE__ROLE_SET:
            return this.eInternalContainer()
                .eInverseRemove(this, InstancePackage.ROLE_SET__ROLES, RoleSet.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            return this.getRoleSet();
        case InstancePackage.ROLE__ROLE_TYPE:
            if (resolve) {
                return this.getRoleType();
            }
            return this.basicGetRoleType();
        case InstancePackage.ROLE__VALUE:
            if (resolve) {
                return this.getValue();
            }
            return this.basicGetValue();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            this.setRoleSet((RoleSet) newValue);
            return;
        case InstancePackage.ROLE__ROLE_TYPE:
            this.setRoleType((RoleType) newValue);
            return;
        case InstancePackage.ROLE__VALUE:
            this.setValue((EObject) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(final int featureID) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            this.setRoleSet((RoleSet) null);
            return;
        case InstancePackage.ROLE__ROLE_TYPE:
            this.setRoleType((RoleType) null);
            return;
        case InstancePackage.ROLE__VALUE:
            this.setValue((EObject) null);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(final int featureID) {
        switch (featureID) {
        case InstancePackage.ROLE__ROLE_SET:
            return this.getRoleSet() != null;
        case InstancePackage.ROLE__ROLE_TYPE:
            return this.basicGetRoleType() != null;
        case InstancePackage.ROLE__VALUE:
            return this.basicGetValue() != null;
        }
        return super.eIsSet(featureID);
    }

} // RoleImpl
