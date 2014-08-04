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
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.TimeFrame;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Time Frame</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl#getStart <em>Start</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.pms.impl.TimeFrameImpl#getStop <em>Stop</em>}</li>
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
        return PmsPackage.Literals.TIME_FRAME;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getStart() {
        return start;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStart(double newStart) {
        double oldStart = start;
        start = newStart;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.TIME_FRAME__START, oldStart, start));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getStop() {
        return stop;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStop(double newStop) {
        double oldStop = stop;
        stop = newStop;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.TIME_FRAME__STOP, oldStop, stop));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PmsPackage.TIME_FRAME__START:
            return getStart();
        case PmsPackage.TIME_FRAME__STOP:
            return getStop();
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
        case PmsPackage.TIME_FRAME__START:
            setStart((Double) newValue);
            return;
        case PmsPackage.TIME_FRAME__STOP:
            setStop((Double) newValue);
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
        case PmsPackage.TIME_FRAME__START:
            setStart(START_EDEFAULT);
            return;
        case PmsPackage.TIME_FRAME__STOP:
            setStop(STOP_EDEFAULT);
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
        case PmsPackage.TIME_FRAME__START:
            return start != START_EDEFAULT;
        case PmsPackage.TIME_FRAME__STOP:
            return stop != STOP_EDEFAULT;
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
        result.append(" (start: ");
        result.append(start);
        result.append(", stop: ");
        result.append(stop);
        result.append(')');
        return result.toString();
    }

} // TimeFrameImpl
