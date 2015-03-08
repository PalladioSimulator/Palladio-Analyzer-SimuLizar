/**
 */
package org.palladiosimulator.simulizar.monitorrepository.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.monitorrepository.Intervall;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Intervall</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.impl.IntervallImpl#getIntervall <em>
 * Intervall</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntervallImpl extends TemporalCharacterizationImpl implements Intervall {
    /**
     * The default value of the '{@link #getIntervall() <em>Intervall</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIntervall()
     * @generated
     * @ordered
     */
    protected static final double INTERVALL_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getIntervall() <em>Intervall</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getIntervall()
     * @generated
     * @ordered
     */
    protected double intervall = INTERVALL_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected IntervallImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MonitorrepositoryPackage.Literals.INTERVALL;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getIntervall() {
        return this.intervall;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setIntervall(final double newIntervall) {
        final double oldIntervall = this.intervall;
        this.intervall = newIntervall;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MonitorrepositoryPackage.INTERVALL__INTERVALL,
                    oldIntervall, this.intervall));
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
        case MonitorrepositoryPackage.INTERVALL__INTERVALL:
            return this.getIntervall();
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
        case MonitorrepositoryPackage.INTERVALL__INTERVALL:
            this.setIntervall((Double) newValue);
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
        case MonitorrepositoryPackage.INTERVALL__INTERVALL:
            this.setIntervall(INTERVALL_EDEFAULT);
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
        case MonitorrepositoryPackage.INTERVALL__INTERVALL:
            return this.intervall != INTERVALL_EDEFAULT;
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
        result.append(" (intervall: ");
        result.append(this.intervall);
        result.append(')');
        return result.toString();
    }

} // IntervallImpl
