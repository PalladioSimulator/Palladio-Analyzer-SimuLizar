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
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;

import de.uka.ipd.sdq.pcm.core.entity.impl.EntityImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Monitor</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl#getMeasurementSpecifications
 * <em>Measurement Specifications</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl#getMeasuringPoint
 * <em>Measuring Point</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.impl.MonitorImpl#getMonitorRepository
 * <em>Monitor Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MonitorImpl extends EntityImpl implements Monitor {
    /**
     * The cached value of the '{@link #getMeasurementSpecifications()
     * <em>Measurement Specifications</em>}' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getMeasurementSpecifications()
     * @generated
     * @ordered
     */
    protected EList<MeasurementSpecification> measurementSpecifications;

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
    public EList<MeasurementSpecification> getMeasurementSpecifications() {
        if (this.measurementSpecifications == null) {
            this.measurementSpecifications = new EObjectContainmentWithInverseEList<MeasurementSpecification>(
                    MeasurementSpecification.class, this, MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS,
                    MonitorrepositoryPackage.MEASUREMENT_SPECIFICATION__MONITOR);
        }
        return this.measurementSpecifications;
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
    public MonitorRepository getMonitorRepository() {
        if (this.eContainerFeatureID() != MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY) {
            return null;
        }
        return (MonitorRepository) this.eInternalContainer();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public NotificationChain basicSetMonitorRepository(final MonitorRepository newMonitorRepository,
            NotificationChain msgs) {
        msgs = this.eBasicSetContainer((InternalEObject) newMonitorRepository,
                MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY, msgs);
        return msgs;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMonitorRepository(final MonitorRepository newMonitorRepository) {
        if (newMonitorRepository != this.eInternalContainer()
                || (this.eContainerFeatureID() != MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY && newMonitorRepository != null)) {
            if (EcoreUtil.isAncestor(this, newMonitorRepository)) {
                throw new IllegalArgumentException("Recursive containment not allowed for " + this.toString());
            }
            NotificationChain msgs = null;
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            if (newMonitorRepository != null) {
                msgs = ((InternalEObject) newMonitorRepository).eInverseAdd(this,
                        MonitorrepositoryPackage.MONITOR_REPOSITORY__MONITORS, MonitorRepository.class, msgs);
            }
            msgs = this.basicSetMonitorRepository(newMonitorRepository, msgs);
            if (msgs != null) {
                msgs.dispatch();
            }
        } else if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY, newMonitorRepository, newMonitorRepository));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public NotificationChain eInverseAdd(final InternalEObject otherEnd, final int featureID, NotificationChain msgs) {
        switch (featureID) {
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            return ((InternalEList<InternalEObject>) (InternalEList<?>) this.getMeasurementSpecifications()).basicAdd(
                    otherEnd, msgs);
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            if (this.eInternalContainer() != null) {
                msgs = this.eBasicRemoveFromContainer(msgs);
            }
            return this.basicSetMonitorRepository((MonitorRepository) otherEnd, msgs);
        }
        return super.eInverseAdd(otherEnd, featureID, msgs);
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            return ((InternalEList<?>) this.getMeasurementSpecifications()).basicRemove(otherEnd, msgs);
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            return this.basicSetMonitorRepository(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public NotificationChain eBasicRemoveFromContainerFeature(final NotificationChain msgs) {
        switch (this.eContainerFeatureID()) {
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            return this.eInternalContainer().eInverseRemove(this,
                    MonitorrepositoryPackage.MONITOR_REPOSITORY__MONITORS, MonitorRepository.class, msgs);
        }
        return super.eBasicRemoveFromContainerFeature(msgs);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(final int featureID, final boolean resolve, final boolean coreType) {
        switch (featureID) {
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            return this.getMeasurementSpecifications();
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            if (resolve) {
                return this.getMeasuringPoint();
            }
            return this.basicGetMeasuringPoint();
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            return this.getMonitorRepository();
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            this.getMeasurementSpecifications().clear();
            this.getMeasurementSpecifications().addAll((Collection<? extends MeasurementSpecification>) newValue);
            return;
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) newValue);
            return;
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            this.setMonitorRepository((MonitorRepository) newValue);
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            this.getMeasurementSpecifications().clear();
            return;
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            this.setMeasuringPoint((MeasuringPoint) null);
            return;
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            this.setMonitorRepository((MonitorRepository) null);
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
        case MonitorrepositoryPackage.MONITOR__MEASUREMENT_SPECIFICATIONS:
            return this.measurementSpecifications != null && !this.measurementSpecifications.isEmpty();
        case MonitorrepositoryPackage.MONITOR__MEASURING_POINT:
            return this.measuringPoint != null;
        case MonitorrepositoryPackage.MONITOR__MONITOR_REPOSITORY:
            return this.getMonitorRepository() != null;
        }
        return super.eIsSet(featureID);
    }

} // MonitorImpl
