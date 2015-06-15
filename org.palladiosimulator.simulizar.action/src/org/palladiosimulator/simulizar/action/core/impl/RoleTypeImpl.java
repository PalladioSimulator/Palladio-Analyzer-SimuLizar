/**
 */
package org.palladiosimulator.simulizar.action.core.impl;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

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
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.action.core.impl.RoleTypeImpl#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTypeImpl extends EntityImpl implements RoleType {
	/**
     * The cached value of the '{@link #getType() <em>Type</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getType()
     * @generated
     * @ordered
     */
	protected EClass type;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected RoleTypeImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return CorePackage.Literals.ROLE_TYPE;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass getType() {
        if (type != null && type.eIsProxy()) {
            InternalEObject oldType = (InternalEObject)type;
            type = (EClass)eResolveProxy(oldType);
            if (type != oldType) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE, CorePackage.ROLE_TYPE__TYPE, oldType, type));
            }
        }
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public EClass basicGetType() {
        return type;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setType(EClass newType) {
        EClass oldType = type;
        type = newType;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ROLE_TYPE__TYPE, oldType, type));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public Action getAction() {
        if (eContainerFeatureID() != CorePackage.ROLE_TYPE__ACTION) return null;
        return (Action)eInternalContainer();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public NotificationChain basicSetAction(Action newAction, NotificationChain msgs) {
        msgs = eBasicSetContainer((InternalEObject)newAction, CorePackage.ROLE_TYPE__ACTION, msgs);
        return msgs;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public void setAction(Action newAction) {
        if (newAction != eInternalContainer() || (eContainerFeatureID() != CorePackage.ROLE_TYPE__ACTION && newAction != null)) {
            if (EcoreUtil.isAncestor(this, newAction))
                throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
            NotificationChain msgs = null;
            if (eInternalContainer() != null)
                msgs = eBasicRemoveFromContainer(msgs);
            if (newAction != null)
                msgs = ((InternalEObject)newAction).eInverseAdd(this, CorePackage.ACTION__INVOLVED_ROLES, Action.class, msgs);
            msgs = basicSetAction(newAction, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, CorePackage.ROLE_TYPE__ACTION, newAction, newAction));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case CorePackage.ROLE_TYPE__ACTION:
                if (eInternalContainer() != null)
                    msgs = eBasicRemoveFromContainer(msgs);
                return basicSetAction((Action)otherEnd, msgs);
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
            case CorePackage.ROLE_TYPE__ACTION:
                return basicSetAction(null, msgs);
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
            case CorePackage.ROLE_TYPE__ACTION:
                return eInternalContainer().eInverseRemove(this, CorePackage.ACTION__INVOLVED_ROLES, Action.class, msgs);
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
            case CorePackage.ROLE_TYPE__TYPE:
                if (resolve) return getType();
                return basicGetType();
            case CorePackage.ROLE_TYPE__ACTION:
                return getAction();
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
            case CorePackage.ROLE_TYPE__TYPE:
                setType((EClass)newValue);
                return;
            case CorePackage.ROLE_TYPE__ACTION:
                setAction((Action)newValue);
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
            case CorePackage.ROLE_TYPE__TYPE:
                setType((EClass)null);
                return;
            case CorePackage.ROLE_TYPE__ACTION:
                setAction((Action)null);
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
            case CorePackage.ROLE_TYPE__TYPE:
                return type != null;
            case CorePackage.ROLE_TYPE__ACTION:
                return getAction() != null;
        }
        return super.eIsSet(featureID);
    }

} //RoleTypeImpl
