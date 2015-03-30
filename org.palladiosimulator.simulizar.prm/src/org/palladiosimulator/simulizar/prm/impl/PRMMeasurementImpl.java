/**
 */
package org.palladiosimulator.simulizar.prm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.prm.PrmPackage;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>PRM Measurement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl#getMeasuringPoint <em>
 * Measuring Point</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl#getMeasurementSpecification
 * <em>Measurement Specification</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.prm.impl.PRMMeasurementImpl#getMeasuringValue <em>
 * Measuring Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PRMMeasurementImpl extends IdentifierImpl implements PRMMeasurement {
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
     * The cached value of the '{@link #getMeasurementSpecification()
     * <em>Measurement Specification</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasurementSpecification()
     * @generated
     * @ordered
     */
    protected MeasurementSpecification measurementSpecification;

    /**
     * The default value of the '{@link #getMeasuringValue() <em>Measuring Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasuringValue()
     * @generated
     * @ordered
     */
    protected static final double MEASURING_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMeasuringValue() <em>Measuring Value</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasuringValue()
     * @generated
     * @ordered
     */
    protected double measuringValue = MEASURING_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PRMMeasurementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrmPackage.Literals.PRM_MEASUREMENT;
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
                            PrmPackage.PRM_MEASUREMENT__MEASURING_POINT, oldMeasuringPoint, this.measuringPoint));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, PrmPackage.PRM_MEASUREMENT__MEASURING_POINT,
                    oldMeasuringPoint, this.measuringPoint));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MeasurementSpecification getMeasurementSpecification() {
        if (this.measurementSpecification != null && this.measurementSpecification.eIsProxy()) {
            final InternalEObject oldMeasurementSpecification = (InternalEObject) this.measurementSpecification;
            this.measurementSpecification = (MeasurementSpecification) this.eResolveProxy(oldMeasurementSpecification);
            if (this.measurementSpecification != oldMeasurementSpecification) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION, oldMeasurementSpecification,
                            this.measurementSpecification));
                }
            }
        }
        return this.measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MeasurementSpecification basicGetMeasurementSpecification() {
        return this.measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMeasurementSpecification(final MeasurementSpecification newMeasurementSpecification) {
        final MeasurementSpecification oldMeasurementSpecification = this.measurementSpecification;
        this.measurementSpecification = newMeasurementSpecification;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION, oldMeasurementSpecification,
                    this.measurementSpecification));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMeasuringValue() {
        return this.measuringValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMeasuringValue(final double newMeasuringValue) {
        final double oldMeasuringValue = this.measuringValue;
        this.measuringValue = newMeasuringValue;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET, PrmPackage.PRM_MEASUREMENT__MEASURING_VALUE,
                    oldMeasuringValue, this.measuringValue));
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
        case PrmPackage.PRM_MEASUREMENT__MEASURING_POINT:
            if (resolve) {
                return this.getMeasuringPoint();
            }
            return this.basicGetMeasuringPoint();
        case PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            if (resolve) {
                return this.getMeasurementSpecification();
            }
            return this.basicGetMeasurementSpecification();
        case PrmPackage.PRM_MEASUREMENT__MEASURING_VALUE:
            return this.getMeasuringValue();
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
        case PrmPackage.PRM_MEASUREMENT__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) newValue);
            return;
        case PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            this.setMeasurementSpecification((MeasurementSpecification) newValue);
            return;
        case PrmPackage.PRM_MEASUREMENT__MEASURING_VALUE:
            this.setMeasuringValue((Double) newValue);
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
        case PrmPackage.PRM_MEASUREMENT__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) null);
            return;
        case PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            this.setMeasurementSpecification((MeasurementSpecification) null);
            return;
        case PrmPackage.PRM_MEASUREMENT__MEASURING_VALUE:
            this.setMeasuringValue(MEASURING_VALUE_EDEFAULT);
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
        case PrmPackage.PRM_MEASUREMENT__MEASURING_POINT:
            return this.measuringPoint != null;
        case PrmPackage.PRM_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return this.measurementSpecification != null;
        case PrmPackage.PRM_MEASUREMENT__MEASURING_VALUE:
            return this.measuringValue != MEASURING_VALUE_EDEFAULT;
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
        result.append(" (measuringValue: ");
        result.append(this.measuringValue);
        result.append(')');
        return result.toString();
    }

} // PRMMeasurementImpl
