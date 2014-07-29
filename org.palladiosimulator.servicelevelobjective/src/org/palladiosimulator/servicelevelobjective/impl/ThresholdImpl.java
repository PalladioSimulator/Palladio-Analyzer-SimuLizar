/**
 */
package org.palladiosimulator.servicelevelobjective.impl;

import javax.measure.Measure;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.Threshold;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Threshold</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.servicelevelobjective.impl.ThresholdImpl#getThresholdLimit <em>
 * Threshold Limit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ThresholdImpl extends IdentifierImpl implements Threshold {
    /**
     * The cached value of the '{@link #getThresholdLimit() <em>Threshold Limit</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getThresholdLimit()
     * @generated
     * @ordered
     */
    protected Measure thresholdLimit;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ThresholdImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ServicelevelObjectivePackage.Literals.THRESHOLD;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Measure getThresholdLimit() {
        return this.thresholdLimit;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setThresholdLimit(final Measure newThresholdLimit) {
        final Measure oldThresholdLimit = this.thresholdLimit;
        this.thresholdLimit = newThresholdLimit;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT, oldThresholdLimit, this.thresholdLimit));
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
        case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
            return this.getThresholdLimit();
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
        case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
            this.setThresholdLimit((Measure) newValue);
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
        case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
            this.setThresholdLimit((Measure) null);
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
        case ServicelevelObjectivePackage.THRESHOLD__THRESHOLD_LIMIT:
            return this.thresholdLimit != null;
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
        result.append(" (thresholdLimit: ");
        result.append(this.thresholdLimit);
        result.append(')');
        return result.toString();
    }

} // ThresholdImpl
