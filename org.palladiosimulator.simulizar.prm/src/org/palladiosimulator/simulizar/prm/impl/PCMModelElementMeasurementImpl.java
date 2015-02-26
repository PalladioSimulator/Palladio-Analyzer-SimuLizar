/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PrmPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PCM Model Element Measurement</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.prm.impl.PCMModelElementMeasurementImpl#getPcmModelElement
 * <em>Pcm Model Element</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.prm.impl.PCMModelElementMeasurementImpl#getMonitorRepository
 * <em>Monitor Repository</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.prm.impl.PCMModelElementMeasurementImpl#getMeasurementValue
 * <em>Measurement Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PCMModelElementMeasurementImpl extends UniqueElementImpl implements PCMModelElementMeasurement {
    /**
     * The cached value of the '{@link #getPcmModelElement() <em>Pcm Model Element</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getPcmModelElement()
     * @generated
     * @ordered
     */
    protected EObject pcmModelElement;

    /**
     * The cached value of the '{@link #getMonitorRepository() <em>Monitor Repository</em>}'
     * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMonitorRepository()
     * @generated
     * @ordered
     */
    protected MonitorRepository monitorRepository;

    /**
     * The default value of the '{@link #getMeasurementValue() <em>Measurement Value</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasurementValue()
     * @generated
     * @ordered
     */
    protected static final double MEASUREMENT_VALUE_EDEFAULT = 0.0;

    /**
     * The cached value of the '{@link #getMeasurementValue() <em>Measurement Value</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasurementValue()
     * @generated
     * @ordered
     */
    protected double measurementValue = MEASUREMENT_VALUE_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected PCMModelElementMeasurementImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrmPackage.Literals.PCM_MODEL_ELEMENT_MEASUREMENT;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObject getPcmModelElement() {
        if (this.pcmModelElement != null && this.pcmModelElement.eIsProxy()) {
            final InternalEObject oldPcmModelElement = (InternalEObject) this.pcmModelElement;
            this.pcmModelElement = this.eResolveProxy(oldPcmModelElement);
            if (this.pcmModelElement != oldPcmModelElement) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT, oldPcmModelElement,
                            this.pcmModelElement));
                }
            }
        }
        return this.pcmModelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public EObject basicGetPcmModelElement() {
        return this.pcmModelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setPcmModelElement(final EObject newPcmModelElement) {
        final EObject oldPcmModelElement = this.pcmModelElement;
        this.pcmModelElement = newPcmModelElement;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT, oldPcmModelElement,
                    this.pcmModelElement));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public MonitorRepository getMonitorRepository() {
        if (this.monitorRepository != null && this.monitorRepository.eIsProxy()) {
            final InternalEObject oldMonitorRepository = (InternalEObject) this.monitorRepository;
            this.monitorRepository = (MonitorRepository) this.eResolveProxy(oldMonitorRepository);
            if (this.monitorRepository != oldMonitorRepository) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY, oldMonitorRepository,
                            this.monitorRepository));
                }
            }
        }
        return this.monitorRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MonitorRepository basicGetMonitorRepository() {
        return this.monitorRepository;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMonitorRepository(final MonitorRepository newMonitorRepository) {
        final MonitorRepository oldMonitorRepository = this.monitorRepository;
        this.monitorRepository = newMonitorRepository;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY, oldMonitorRepository,
                    this.monitorRepository));
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public double getMeasurementValue() {
        return this.measurementValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setMeasurementValue(final double newMeasurementValue) {
        final double oldMeasurementValue = this.measurementValue;
        this.measurementValue = newMeasurementValue;
        if (this.eNotificationRequired()) {
            this.eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE, oldMeasurementValue,
                    this.measurementValue));
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            if (resolve) {
                return this.getPcmModelElement();
            }
            return this.basicGetPcmModelElement();
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY:
            if (resolve) {
                return this.getMonitorRepository();
            }
            return this.basicGetMonitorRepository();
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            return this.getMeasurementValue();
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            this.setPcmModelElement((EObject) newValue);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY:
            this.setMonitorRepository((MonitorRepository) newValue);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            this.setMeasurementValue((Double) newValue);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            this.setPcmModelElement((EObject) null);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY:
            this.setMonitorRepository((MonitorRepository) null);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            this.setMeasurementValue(MEASUREMENT_VALUE_EDEFAULT);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            return this.pcmModelElement != null;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MONITOR_REPOSITORY:
            return this.monitorRepository != null;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            return this.measurementValue != MEASUREMENT_VALUE_EDEFAULT;
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
        result.append(" (measurementValue: ");
        result.append(this.measurementValue);
        result.append(')');
        return result.toString();
    }

} // PCMModelElementMeasurementImpl
