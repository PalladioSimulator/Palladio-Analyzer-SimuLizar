/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.palladiosimulator.pcm.core.entity.impl.EntityImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.RoleType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role Type</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getType
 * <em>Type</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getAction
 * <em>Action</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RoleTypeImpl extends EntityImpl implements RoleType {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected RoleTypeImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return CorePackage.Literals.ROLE_TYPE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getType() {
        return (EClass) this.eDynamicGet(CorePackage.ROLE_TYPE__TYPE, CorePackage.Literals.ROLE_TYPE__TYPE, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EClass basicGetType() {
        return (EClass) this.eDynamicGet(CorePackage.ROLE_TYPE__TYPE, CorePackage.Literals.ROLE_TYPE__TYPE, false,
                true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(final EClass newType) {
        this.eDynamicSet(CorePackage.ROLE_TYPE__TYPE, CorePackage.Literals.ROLE_TYPE__TYPE, newType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AdaptationBehavior getAction() {
        return (AdaptationBehavior) this.eDynamicGet(CorePackage.ROLE_TYPE__ACTION,
                CorePackage.Literals.ROLE_TYPE__ACTION, true, true);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAction(final AdaptationBehavior newAction, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAction, CorePackage.ROLE_TYPE__ACTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAction(final AdaptationBehavior newAction) {
        this.eDynamicSet(CorePackage.ROLE_TYPE__ACTION, CorePackage.Literals.ROLE_TYPE__ACTION, newAction);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case CorePackage.ROLE_TYPE__ACTION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAction((AdaptationBehavior) otherEnd, msgs);
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
        case CorePackage.ROLE_TYPE__ACTION:
            return this.basicSetAction(null, msgs);
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
        case CorePackage.ROLE_TYPE__ACTION:
            return this.eInternalContainer()
                .eInverseRemove(this, CorePackage.ADAPTATION_BEHAVIOR__INVOLVED_ROLES, AdaptationBehavior.class, msgs);
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
        case CorePackage.ROLE_TYPE__TYPE:
            if (resolve) {
                return this.getType();
            }
            return this.basicGetType();
        case CorePackage.ROLE_TYPE__ACTION:
            return this.getAction();
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
        case CorePackage.ROLE_TYPE__TYPE:
            this.setType((EClass) newValue);
            return;
        case CorePackage.ROLE_TYPE__ACTION:
            this.setAction((AdaptationBehavior) newValue);
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
        case CorePackage.ROLE_TYPE__TYPE:
            this.setType((EClass) null);
            return;
        case CorePackage.ROLE_TYPE__ACTION:
            this.setAction((AdaptationBehavior) null);
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
        case CorePackage.ROLE_TYPE__TYPE:
            return this.basicGetType() != null;
        case CorePackage.ROLE_TYPE__ACTION:
            return this.getAction() != null;
        }
        return super.eIsSet(featureID);
    }

} // RoleTypeImpl
