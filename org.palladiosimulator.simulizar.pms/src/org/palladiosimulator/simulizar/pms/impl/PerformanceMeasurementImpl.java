/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMeasurement;
import org.palladiosimulator.simulizar.pms.PmsPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Performance Measurement</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.pms.impl.PerformanceMeasurementImpl#getMeasurementSpecification
 * <em>Measurement Specification</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.pms.impl.PerformanceMeasurementImpl#getMeasuringPoint
 * <em>Measuring Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PerformanceMeasurementImpl extends UniqueElementImpl implements PerformanceMeasurement {
    /**
     * The cached value of the '{@link #getMeasurementSpecification()
     * <em>Measurement Specification</em>}' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #getMeasurementSpecification()
     * @generated
     * @ordered
     */
    protected EList<MeasurementSpecification> measurementSpecification;

    /**
     * The cached value of the '{@link #getMeasuringPoint() <em>Measuring Point</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getMeasuringPoint()
     * @generated
     * @ordered
     */
    protected MeasuringPoint measuringPoint;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected PerformanceMeasurementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PmsPackage.Literals.PERFORMANCE_MEASUREMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EList<MeasurementSpecification> getMeasurementSpecification() {
        if (measurementSpecification == null) {
            measurementSpecification = new EObjectContainmentEList<MeasurementSpecification>(
                    MeasurementSpecification.class, this, PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION);
        }
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasuringPoint getMeasuringPoint() {
        if (measuringPoint != null && measuringPoint.eIsProxy()) {
            InternalEObject oldMeasuringPoint = (InternalEObject) measuringPoint;
            measuringPoint = (MeasuringPoint) eResolveProxy(oldMeasuringPoint);
            if (measuringPoint != oldMeasuringPoint) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT, oldMeasuringPoint, measuringPoint));
            }
        }
        return measuringPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasuringPoint basicGetMeasuringPoint() {
        return measuringPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMeasuringPoint(MeasuringPoint newMeasuringPoint) {
        MeasuringPoint oldMeasuringPoint = measuringPoint;
        measuringPoint = newMeasuringPoint;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT,
                    oldMeasuringPoint, measuringPoint));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return ((InternalEList<?>) getMeasurementSpecification()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return getMeasurementSpecification();
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT:
            if (resolve)
                return getMeasuringPoint();
            return basicGetMeasuringPoint();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            getMeasurementSpecification().clear();
            getMeasurementSpecification().addAll((Collection<? extends MeasurementSpecification>) newValue);
            return;
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT:
            setMeasuringPoint((MeasuringPoint) newValue);
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
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            getMeasurementSpecification().clear();
            return;
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT:
            setMeasuringPoint((MeasuringPoint) null);
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
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return measurementSpecification != null && !measurementSpecification.isEmpty();
        case PmsPackage.PERFORMANCE_MEASUREMENT__MEASURING_POINT:
            return measuringPoint != null;
        }
        return super.eIsSet(featureID);
    }

} // PerformanceMeasurementImpl
