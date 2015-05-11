/**
 */
package org.palladiosimulator.runtimemeasurement.impl;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;

import org.palladiosimulator.monitorrepository.MeasurementSpecification;

import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Runtime Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl#getMeasuringPoint <em>Measuring Point</em>}</li>
 *   <li>{@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl#getMeasurementSpecification <em>Measurement Specification</em>}</li>
 *   <li>{@link org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementImpl#getMeasuringValue <em>Measuring Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RuntimeMeasurementImpl extends IdentifierImpl implements RuntimeMeasurement {
    /**
     * The cached value of the '{@link #getMeasuringPoint() <em>Measuring Point</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeasuringPoint()
     * @generated
     * @ordered
     */
    protected MeasuringPoint measuringPoint;

    /**
     * The cached value of the '{@link #getMeasurementSpecification() <em>Measurement Specification</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeasurementSpecification()
     * @generated
     * @ordered
     */
    protected MeasurementSpecification measurementSpecification;

    /**
     * The default value of the '{@link #getMeasuringValue() <em>Measuring Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeasuringValue()
     * @generated
     * @ordered
     */
    protected static final double MEASURING_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMeasuringValue() <em>Measuring Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getMeasuringValue()
     * @generated
     * @ordered
     */
    protected double measuringValue = MEASURING_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected RuntimeMeasurementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return RuntimeMeasurementPackage.Literals.RUNTIME_MEASUREMENT;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MeasuringPoint getMeasuringPoint() {
        if (measuringPoint != null && measuringPoint.eIsProxy()) {
            InternalEObject oldMeasuringPoint = (InternalEObject) measuringPoint;
            measuringPoint = (MeasuringPoint) eResolveProxy(oldMeasuringPoint);
            if (measuringPoint != oldMeasuringPoint) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT, oldMeasuringPoint,
                            measuringPoint));
            }
        }
        return measuringPoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MeasuringPoint basicGetMeasuringPoint() {
        return measuringPoint;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMeasuringPoint(MeasuringPoint newMeasuringPoint) {
        MeasuringPoint oldMeasuringPoint = measuringPoint;
        measuringPoint = newMeasuringPoint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT, oldMeasuringPoint, measuringPoint));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MeasurementSpecification getMeasurementSpecification() {
        if (measurementSpecification != null && measurementSpecification.eIsProxy()) {
            InternalEObject oldMeasurementSpecification = (InternalEObject) measurementSpecification;
            measurementSpecification = (MeasurementSpecification) eResolveProxy(oldMeasurementSpecification);
            if (measurementSpecification != oldMeasurementSpecification) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION,
                            oldMeasurementSpecification, measurementSpecification));
            }
        }
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public MeasurementSpecification basicGetMeasurementSpecification() {
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMeasurementSpecification(MeasurementSpecification newMeasurementSpecification) {
        MeasurementSpecification oldMeasurementSpecification = measurementSpecification;
        measurementSpecification = newMeasurementSpecification;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION,
                    oldMeasurementSpecification, measurementSpecification));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double getMeasuringValue() {
        return measuringValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setMeasuringValue(double newMeasuringValue) {
        double oldMeasuringValue = measuringValue;
        measuringValue = newMeasuringValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE, oldMeasuringValue, measuringValue));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT:
            if (resolve)
                return getMeasuringPoint();
            return basicGetMeasuringPoint();
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            if (resolve)
                return getMeasurementSpecification();
            return basicGetMeasurementSpecification();
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE:
            return getMeasuringValue();
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT:
            setMeasuringPoint((MeasuringPoint) newValue);
            return;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            setMeasurementSpecification((MeasurementSpecification) newValue);
            return;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE:
            setMeasuringValue((Double) newValue);
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT:
            setMeasuringPoint((MeasuringPoint) null);
            return;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            setMeasurementSpecification((MeasurementSpecification) null);
            return;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE:
            setMeasuringValue(MEASURING_VALUE_EDEFAULT);
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
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_POINT:
            return measuringPoint != null;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return measurementSpecification != null;
        case RuntimeMeasurementPackage.RUNTIME_MEASUREMENT__MEASURING_VALUE:
            return measuringValue != MEASURING_VALUE_EDEFAULT;
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
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (measuringValue: ");
        result.append(measuringValue);
        result.append(')');
        return result.toString();
    }

} //RuntimeMeasurementImpl
