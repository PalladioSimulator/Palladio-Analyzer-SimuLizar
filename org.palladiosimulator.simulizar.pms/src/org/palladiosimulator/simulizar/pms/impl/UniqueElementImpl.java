/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.UniqueElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Unique Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.impl.UniqueElementImpl#getGuid <em>Guid</em>}</li>
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
        return PmsPackage.Literals.UNIQUE_ELEMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getGuid() {
        return guid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setGuid(String newGuid) {
        String oldGuid = guid;
        guid = newGuid;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.UNIQUE_ELEMENT__GUID, oldGuid, guid));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createAndSetGuid() {
        if (this.getGuid() == null)
            this.setGuid(org.eclipse.emf.ecore.util.EcoreUtil.generateUUID());
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PmsPackage.UNIQUE_ELEMENT__GUID:
            return getGuid();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PmsPackage.UNIQUE_ELEMENT__GUID:
            setGuid((String) newValue);
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
    public void eUnset(int featureID) {
        switch (featureID) {
        case PmsPackage.UNIQUE_ELEMENT__GUID:
            setGuid(GUID_EDEFAULT);
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
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case PmsPackage.UNIQUE_ELEMENT__GUID:
            return GUID_EDEFAULT == null ? guid != null : !GUID_EDEFAULT.equals(guid);
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (guid: ");
        result.append(guid);
        result.append(')');
        return result.toString();
    }

} // UniqueElementImpl
