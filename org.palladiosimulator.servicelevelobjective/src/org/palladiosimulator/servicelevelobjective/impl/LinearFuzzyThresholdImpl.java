/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import javax.measure.Measure;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.servicelevelobjective.LinearFuzzyThreshold;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Linear Fuzzy Threshold</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.servicelevelobjective.impl.LinearFuzzyThresholdImpl#getSoftLimit <em>Soft Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinearFuzzyThresholdImpl extends ThresholdImpl implements LinearFuzzyThreshold {
    /**
     * The cached value of the '{@link #getSoftLimit() <em>Soft Limit</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getSoftLimit()
     * @generated
     * @ordered
     */
    protected Measure softLimit;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected LinearFuzzyThresholdImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServicelevelObjectivePackage.Literals.LINEAR_FUZZY_THRESHOLD;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Measure getSoftLimit() {
        return softLimit;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setSoftLimit(Measure newSoftLimit) {
        Measure oldSoftLimit = softLimit;
        softLimit = newSoftLimit;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT, oldSoftLimit, softLimit));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT:
                return getSoftLimit();
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
            case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT:
                setSoftLimit((Measure)newValue);
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
            case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT:
                setSoftLimit((Measure)null);
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
            case ServicelevelObjectivePackage.LINEAR_FUZZY_THRESHOLD__SOFT_LIMIT:
                return softLimit != null;
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
        result.append(" (softLimit: ");
        result.append(softLimit);
        result.append(')');
        return result.toString();
    }

} //LinearFuzzyThresholdImpl
