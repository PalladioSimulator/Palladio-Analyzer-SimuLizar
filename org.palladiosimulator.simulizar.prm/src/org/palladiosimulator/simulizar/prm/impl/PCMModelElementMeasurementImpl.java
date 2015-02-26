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
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
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
 * {@link org.palladiosimulator.simulizar.prm.impl.PCMModelElementMeasurementImpl#getMeasurementSpecification
 * <em>Measurement Specification</em>}</li>
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
     * The cached value of the '{@link #getMeasurementSpecification()
     * <em>Measurement Specification</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getMeasurementSpecification()
     * @generated
     * @ordered
     */
    protected MeasurementSpecification measurementSpecification;

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
    public MeasurementSpecification getMeasurementSpecification() {
        if (this.measurementSpecification != null && this.measurementSpecification.eIsProxy()) {
            final InternalEObject oldMeasurementSpecification = (InternalEObject) this.measurementSpecification;
            this.measurementSpecification = (MeasurementSpecification) this.eResolveProxy(oldMeasurementSpecification);
            if (this.measurementSpecification != oldMeasurementSpecification) {
                if (this.eNotificationRequired()) {
                    this.eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION,
                            oldMeasurementSpecification, this.measurementSpecification));
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
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION, oldMeasurementSpecification,
                    this.measurementSpecification));
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            if (resolve) {
                return this.getMeasurementSpecification();
            }
            return this.basicGetMeasurementSpecification();
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            this.setMeasurementSpecification((MeasurementSpecification) newValue);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            this.setMeasurementSpecification((MeasurementSpecification) null);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return this.measurementSpecification != null;
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
