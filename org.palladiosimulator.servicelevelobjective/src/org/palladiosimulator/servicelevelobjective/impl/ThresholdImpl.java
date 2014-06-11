/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import javax.measure.Measure;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Threshold</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl#getThresholdLimit <em>Threshold Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ThresholdImpl extends IdentifierImpl implements Threshold {
    /**
     * The cached value of the '{@link #getThresholdLimit() <em>Threshold Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getThresholdLimit()
     * @generated
     * @ordered
     */
    protected Measure thresholdLimit;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ThresholdImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServicelevelObjectivePackage.Literals.THRESHOLD;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Measure getThresholdLimit() {
        return thresholdLimit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setThresholdLimit(Measure newThresholdLimit) {
        Measure oldThresholdLimit = thresholdLimit;
        thresholdLimit = newThresholdLimit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT, oldThresholdLimit, thresholdLimit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
                return getThresholdLimit();
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
            case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
                setThresholdLimit((Measure)newValue);
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
            case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
                setThresholdLimit((Measure)null);
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
            case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
                return thresholdLimit != null;
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
        result.append(" (thresholdLimit: ");
        result.append(thresholdLimit);
        result.append(')');
        return result.toString();
    }

} //ThresholdImpl
