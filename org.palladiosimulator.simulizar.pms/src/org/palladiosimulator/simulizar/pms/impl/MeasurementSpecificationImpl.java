/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PerformanceMetricEnum;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Measurement Specification</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl#getTemporalRestriction
 * <em>Temporal Restriction</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl#getPerformanceMetric
 * <em>Performance Metric</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl#getStatisticalCharacterization
 * <em>Statistical Characterization</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementSpecificationImpl extends UniqueElementImpl implements MeasurementSpecification {
    /**
     * The cached value of the '{@link #getTemporalRestriction() <em>Temporal Restriction</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getTemporalRestriction()
     * @generated
     * @ordered
     */
    protected TemporalCharacterization temporalRestriction;

    /**
     * The default value of the '{@link #getPerformanceMetric() <em>Performance Metric</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPerformanceMetric()
     * @generated
     * @ordered
     */
    protected static final PerformanceMetricEnum PERFORMANCE_METRIC_EDEFAULT = PerformanceMetricEnum.WAITING_TIME;

    /**
     * The cached value of the '{@link #getPerformanceMetric() <em>Performance Metric</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getPerformanceMetric()
     * @generated
     * @ordered
     */
    protected PerformanceMetricEnum performanceMetric = PERFORMANCE_METRIC_EDEFAULT;

    /**
     * The default value of the '{@link #getStatisticalCharacterization()
     * <em>Statistical Characterization</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getStatisticalCharacterization()
     * @generated
     * @ordered
     */
    protected static final StatisticalCharacterizationEnum STATISTICAL_CHARACTERIZATION_EDEFAULT = StatisticalCharacterizationEnum.NONE;

    /**
     * The cached value of the '{@link #getStatisticalCharacterization()
     * <em>Statistical Characterization</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @see #getStatisticalCharacterization()
     * @generated
     * @ordered
     */
    protected StatisticalCharacterizationEnum statisticalCharacterization = STATISTICAL_CHARACTERIZATION_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected MeasurementSpecificationImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PmsPackage.Literals.MEASUREMENT_SPECIFICATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public TemporalCharacterization getTemporalRestriction() {
        return temporalRestriction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public NotificationChain basicSetTemporalRestriction(TemporalCharacterization newTemporalRestriction,
            NotificationChain msgs) {
        TemporalCharacterization oldTemporalRestriction = temporalRestriction;
        temporalRestriction = newTemporalRestriction;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, oldTemporalRestriction,
                    newTemporalRestriction);
            if (msgs == null)
                msgs = notification;
            else
                msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTemporalRestriction(TemporalCharacterization newTemporalRestriction) {
        if (newTemporalRestriction != temporalRestriction) {
            NotificationChain msgs = null;
            if (temporalRestriction != null)
                msgs = ((InternalEObject) temporalRestriction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, null, msgs);
            if (newTemporalRestriction != null)
                msgs = ((InternalEObject) newTemporalRestriction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, null, msgs);
            msgs = basicSetTemporalRestriction(newTemporalRestriction, msgs);
            if (msgs != null)
                msgs.dispatch();
        } else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, newTemporalRestriction,
                    newTemporalRestriction));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public PerformanceMetricEnum getPerformanceMetric() {
        return performanceMetric;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPerformanceMetric(PerformanceMetricEnum newPerformanceMetric) {
        PerformanceMetricEnum oldPerformanceMetric = performanceMetric;
        performanceMetric = newPerformanceMetric == null ? PERFORMANCE_METRIC_EDEFAULT : newPerformanceMetric;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC, oldPerformanceMetric, performanceMetric));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public StatisticalCharacterizationEnum getStatisticalCharacterization() {
        return statisticalCharacterization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setStatisticalCharacterization(StatisticalCharacterizationEnum newStatisticalCharacterization) {
        StatisticalCharacterizationEnum oldStatisticalCharacterization = statisticalCharacterization;
        statisticalCharacterization = newStatisticalCharacterization == null ? STATISTICAL_CHARACTERIZATION_EDEFAULT
                : newStatisticalCharacterization;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION, oldStatisticalCharacterization,
                    statisticalCharacterization));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return basicSetTemporalRestriction(null, msgs);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return getTemporalRestriction();
        case PmsPackage.MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC:
            return getPerformanceMetric();
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            return getStatisticalCharacterization();
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            setTemporalRestriction((TemporalCharacterization) newValue);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC:
            setPerformanceMetric((PerformanceMetricEnum) newValue);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            setStatisticalCharacterization((StatisticalCharacterizationEnum) newValue);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            setTemporalRestriction((TemporalCharacterization) null);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC:
            setPerformanceMetric(PERFORMANCE_METRIC_EDEFAULT);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            setStatisticalCharacterization(STATISTICAL_CHARACTERIZATION_EDEFAULT);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return temporalRestriction != null;
        case PmsPackage.MEASUREMENT_SPECIFICATION__PERFORMANCE_METRIC:
            return performanceMetric != PERFORMANCE_METRIC_EDEFAULT;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            return statisticalCharacterization != STATISTICAL_CHARACTERIZATION_EDEFAULT;
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
        result.append(" (performanceMetric: ");
        result.append(performanceMetric);
        result.append(", statisticalCharacterization: ");
        result.append(statisticalCharacterization);
        result.append(')');
        return result.toString();
    }

} // MeasurementSpecificationImpl
