/**
 */
package org.palladiosimulator.simulizar.monitorrepository.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.monitorrepository.TimeFrame;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Time Frame</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl#getStart <em>
 * Start</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.impl.TimeFrameImpl#getStop <em>Stop
 * </em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TimeFrameImpl extends TemporalCharacterizationImpl implements TimeFrame {
    /**
     * The default value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected static final double START_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStart()
     * @generated
     * @ordered
     */
    protected double start = START_EDEFAULT;

    /**
     * The default value of the '{@link #getStop() <em>Stop</em>}' attribute. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @see #getStop()
     * @generated
     * @ordered
     */
    protected static final double STOP_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getStop() <em>Stop</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #getStop()
     * @generated
     * @ordered
     */
    protected double stop = STOP_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected TimeFrameImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MonitorrepositoryPackage.Literals.TIME_FRAME;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public double getStart() {
        return this.start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStart(final double newStart) {
        final double oldStart = this.start;
        this.start = newStart;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MonitorrepositoryPackage.TIME_FRAME__START,
                    oldStart, this.start));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public double getStop() {
        return this.stop;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setStop(final double newStop) {
        final double oldStop = this.stop;
        this.stop = newStop;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, MonitorrepositoryPackage.TIME_FRAME__STOP,
                    oldStop, this.stop));
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
        case MonitorrepositoryPackage.TIME_FRAME__START:
            return this.getStart();
        case MonitorrepositoryPackage.TIME_FRAME__STOP:
            return this.getStop();
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
        case MonitorrepositoryPackage.TIME_FRAME__START:
            this.setStart((Double) newValue);
            return;
        case MonitorrepositoryPackage.TIME_FRAME__STOP:
            this.setStop((Double) newValue);
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
        case MonitorrepositoryPackage.TIME_FRAME__START:
            this.setStart(START_EDEFAULT);
            return;
        case MonitorrepositoryPackage.TIME_FRAME__STOP:
            this.setStop(STOP_EDEFAULT);
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
        case MonitorrepositoryPackage.TIME_FRAME__START:
            return this.start != START_EDEFAULT;
        case MonitorrepositoryPackage.TIME_FRAME__STOP:
            return this.stop != STOP_EDEFAULT;
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
        result.append(" (start: ");
        result.append(this.start);
        result.append(", stop: ");
        result.append(this.stop);
        result.append(')');
        return result.toString();
    }

} // TimeFrameImpl
