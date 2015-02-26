/**
 */
package org.palladiosimulator.simulizar.monitorrepository.impl;

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
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Monitor</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl#getMeasurementSpecification
 * <em>Measurement Specification</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl#getMeasuringPoint
 * <em>Measuring Point</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorImpl extends EntityImpl implements Monitor {
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
    protected MonitorImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return MonitorrepositoryPackage.Literals.MONITOR;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public EList<MeasurementSpecification> getMeasurementSpecification() {
        if (this.measurementSpecification == null) {
            this.measurementSpecification = new EObjectContainmentEList<MeasurementSpecification>(
                    MeasurementSpecification.class, this, MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION);
        }
        return this.measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public MeasuringPoint getMeasuringPoint() {
        if (this.measuringPoint != null && this.measuringPoint.eIsProxy()) {
            final InternalEObject oldMeasuringPoint = (InternalEObject) this.measuringPoint;
            this.measuringPoint = (MeasuringPoint) this.eResolveProxy(oldMeasuringPoint);
            if (this.measuringPoint != oldMeasuringPoint) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            MonitorrepositoryPackage.MONITOR__MEASURING_POINT, oldMeasuringPoint, this.measuringPoint));
                }
            }
        }
        return this.measuringPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasuringPoint basicGetMeasuringPoint() {
        return this.measuringPoint;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void setMeasuringPoint(final MeasuringPoint newMeasuringPoint) {
        final MeasuringPoint oldMeasuringPoint = this.measuringPoint;
        this.measuringPoint = newMeasuringPoint;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MonitorrepositoryPackage.MONITOR__MEASURING_POINT, oldMeasuringPoint, this.measuringPoint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(final InternalEObject otherEnd, final int featureID,
            final NotificationChain msgs) {
        switch (featureID) {
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION:
            return ((InternalEList<?>) this.getMeasurementSpecification()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION:
            return this.getMeasurementSpecification();
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            if (resolve) {
                return this.getMeasuringPoint();
            }
            return this.basicGetMeasuringPoint();
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
    public void eSet(final int featureID, final Object newValue) {
        switch (featureID) {
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION:
            this.getMeasurementSpecification().clear();
            this.getMeasurementSpecification().addAll((Collection<? extends MeasurementSpecification>) newValue);
            return;
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) newValue);
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION:
            this.getMeasurementSpecification().clear();
            return;
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) null);
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATION:
            return this.measurementSpecification != null && !this.measurementSpecification.isEmpty();
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            return this.measuringPoint != null;
        }
        return super.eIsSet(featureID);
    }

} // MonitorImpl
