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
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.PmsPackage;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;

import de.uka.ipd.sdq.identifier.impl.IdentifierImpl;

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
 * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl#getStatisticalCharacterization
 * <em>Statistical Characterization</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.pms.impl.MeasurementSpecificationImpl#getMetricDescription
 * <em>Metric Description</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MeasurementSpecificationImpl extends IdentifierImpl implements MeasurementSpecification {
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
     * The cached value of the '{@link #getMetricDescription() <em>Metric Description</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMetricDescription()
     * @generated
     * @ordered
     */
    protected MetricDescription metricDescription;

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
    @Override
    public TemporalCharacterization getTemporalRestriction() {
        return this.temporalRestriction;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetTemporalRestriction(final TemporalCharacterization newTemporalRestriction,
            NotificationChain msgs) {
        final TemporalCharacterization oldTemporalRestriction = this.temporalRestriction;
        this.temporalRestriction = newTemporalRestriction;
        if (this.eNotificationRequired()) {
            final ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, oldTemporalRestriction,
                    newTemporalRestriction);
            if (msgs == null) {
                msgs = notification;
            } else {
                msgs.add(notification);
            }
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTemporalRestriction(final TemporalCharacterization newTemporalRestriction) {
        if (newTemporalRestriction != this.temporalRestriction) {
            NotificationChain msgs = null;
            if (this.temporalRestriction != null) {
                msgs = ((InternalEObject) this.temporalRestriction).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
                        - PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, null, msgs);
            }
            if (newTemporalRestriction != null) {
                msgs = ((InternalEObject) newTemporalRestriction).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
                        - PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, null, msgs);
            }
            msgs = this.basicSetTemporalRestriction(newTemporalRestriction, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION, newTemporalRestriction,
                    newTemporalRestriction));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StatisticalCharacterizationEnum getStatisticalCharacterization() {
        return this.statisticalCharacterization;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStatisticalCharacterization(final StatisticalCharacterizationEnum newStatisticalCharacterization) {
        final StatisticalCharacterizationEnum oldStatisticalCharacterization = this.statisticalCharacterization;
        this.statisticalCharacterization = newStatisticalCharacterization == null ? STATISTICAL_CHARACTERIZATION_EDEFAULT
                : newStatisticalCharacterization;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION, oldStatisticalCharacterization,
                    this.statisticalCharacterization));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MetricDescription getMetricDescription() {
        if (this.metricDescription != null && this.metricDescription.eIsProxy()) {
            final InternalEObject oldMetricDescription = (InternalEObject) this.metricDescription;
            this.metricDescription = (MetricDescription) this.eResolveProxy(oldMetricDescription);
            if (this.metricDescription != oldMetricDescription) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION, oldMetricDescription,
                            this.metricDescription));
                }
            }
        }
        return this.metricDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MetricDescription basicGetMetricDescription() {
        return this.metricDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMetricDescription(final MetricDescription newMetricDescription) {
        final MetricDescription oldMetricDescription = this.metricDescription;
        this.metricDescription = newMetricDescription;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION, oldMetricDescription,
                    this.metricDescription));
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return this.basicSetTemporalRestriction(null, msgs);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return this.getTemporalRestriction();
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            return this.getStatisticalCharacterization();
        case PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION:
            if (resolve) {
                return this.getMetricDescription();
            }
            return this.basicGetMetricDescription();
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            this.setTemporalRestriction((TemporalCharacterization) newValue);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            this.setStatisticalCharacterization((StatisticalCharacterizationEnum) newValue);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION:
            this.setMetricDescription((MetricDescription) newValue);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            this.setTemporalRestriction((TemporalCharacterization) null);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            this.setStatisticalCharacterization(STATISTICAL_CHARACTERIZATION_EDEFAULT);
            return;
        case PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION:
            this.setMetricDescription((MetricDescription) null);
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
        case PmsPackage.MEASUREMENT_SPECIFICATION__TEMPORAL_RESTRICTION:
            return this.temporalRestriction != null;
        case PmsPackage.MEASUREMENT_SPECIFICATION__STATISTICAL_CHARACTERIZATION:
            return this.statisticalCharacterization != STATISTICAL_CHARACTERIZATION_EDEFAULT;
        case PmsPackage.MEASUREMENT_SPECIFICATION__METRIC_DESCRIPTION:
            return this.metricDescription != null;
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
        result.append(" (statisticalCharacterization: ");
        result.append(this.statisticalCharacterization);
        result.append(')');
        return result.toString();
    }

} // MeasurementSpecificationImpl
