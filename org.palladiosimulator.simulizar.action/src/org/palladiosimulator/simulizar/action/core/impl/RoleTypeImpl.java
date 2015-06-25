/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.palladiosimulator.simulizar.action.core.Action;
import org.palladiosimulator.simulizar.action.core.CorePackage;
import org.palladiosimulator.simulizar.action.core.RoleType;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Role Type</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getType <em>Type</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getAction <em>Action
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTypeImpl extends org.palladiosimulator.pcm.core.entity.impl.EntityImpl implements RoleType {
    /**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getType()
     * @generated
     * @ordered
     */
    protected EClass type;

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
        if (this.type != null && this.type.eIsProxy())
        {
            final InternalEObject oldType = (InternalEObject) this.type;
            this.type = (EClass) this.eResolveProxy(oldType);
            if (this.type != oldType)
            {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.ROLE_TYPE__TYPE,
                            oldType,
                            this.type));
                }
            }
        }
        return this.type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EClass basicGetType() {
        return this.type;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setType(final EClass newType) {
        final EClass oldType = this.type;
        this.type = newType;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ROLE_TYPE__TYPE, oldType, this.type));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Action getAction() {
        if (this.eContainerFeatureID() != CorePackage.ROLE_TYPE__ACTION) {
            return null;
        }
        return (Action) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetAction(final Action newAction, NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newAction, CorePackage.ROLE_TYPE__ACTION, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setAction(final Action newAction) {
        if (newAction != this.eInternalContainer()
                || (this.eContainerFeatureID() != CorePackage.ROLE_TYPE__ACTION && newAction != null))
        {
            if (EcoreUtil.isAncestor(this, newAction)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newAction != null) {
                msgs = ((InternalEObject) newAction).eInverseAdd(this, CorePackage.ACTION__INVOLVED_ROLES,
                        Action.class, msgs);
            }
            msgs = this.basicSetAction(newAction, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        }
        else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ROLE_TYPE__ACTION, newAction,
                    newAction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID)
        {
        case CorePackage.ROLE_TYPE__ACTION:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetAction((Action) otherEnd, msgs);
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
        switch (featureID)
        {
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
        switch (this.eContainerFeatureID())
        {
        case CorePackage.ROLE_TYPE__ACTION:
            return this.eInternalContainer().eInverseRemove(this, CorePackage.ACTION__INVOLVED_ROLES, Action.class,
                    msgs);
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
        switch (featureID)
        {
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
        switch (featureID)
        {
        case CorePackage.ROLE_TYPE__TYPE:
            this.setType((EClass) newValue);
            return;
        case CorePackage.ROLE_TYPE__ACTION:
            this.setAction((Action) newValue);
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
        switch (featureID)
        {
        case CorePackage.ROLE_TYPE__TYPE:
            this.setType((EClass) null);
            return;
        case CorePackage.ROLE_TYPE__ACTION:
            this.setAction((Action) null);
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
        switch (featureID)
        {
        case CorePackage.ROLE_TYPE__TYPE:
            return this.type != null;
        case CorePackage.ROLE_TYPE__ACTION:
            return this.getAction() != null;
        }
        return super.eIsSet(featureID);
    }

} // RoleTypeImpl
