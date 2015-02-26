/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.palladiosimulator.simulizar.prm.PrmPackage;
import org.palladiosimulator.simulizar.prm.UniqueElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Unique Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.prm.impl.UniqueElementImpl#getGuid <em>Guid</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniqueElementImpl extends EObjectImpl implements UniqueElement {
    /**
     * The default value of the '{@link #getGuid() <em>Guid</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #getGuid()
     * @generated
     * @ordered
     */
    protected static final String GUID_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getGuid() <em>Guid</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getGuid()
     * @generated
     * @ordered
     */
    protected String guid = GUID_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    protected UniqueElementImpl() {
        super();
        this.createAndSetGuid();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrmPackage.Literals.UNIQUE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getGuid() {
        return this.guid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setGuid(final String newGuid) {
        final String oldGuid = this.guid;
        this.guid = newGuid;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, PrmPackage.UNIQUE_ELEMENT__GUID, oldGuid,
                    this.guid));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void createAndSetGuid() {
        if (this.getGuid() == null) {
            this.setGuid(org.eclipse.emf.ecore.util.EcoreUtil.generateUUID());
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case PrmPackage.UNIQUE_ELEMENT__GUID:
            return this.getGuid();
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
        case PrmPackage.UNIQUE_ELEMENT__GUID:
            this.setGuid((String) newValue);
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
        case PrmPackage.UNIQUE_ELEMENT__GUID:
            this.setGuid(GUID_EDEFAULT);
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
        case PrmPackage.UNIQUE_ELEMENT__GUID:
            return GUID_EDEFAULT == null ? this.guid != null : !GUID_EDEFAULT.equals(this.guid);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy()) {
            return super.toString();
        }

        final StringBuffer result = new StringBuffer(super.toString());
        result.append(" (guid: ");
        result.append(this.guid);
        result.append(')');
        return result.toString();
    }

} // UniqueElementImpl
