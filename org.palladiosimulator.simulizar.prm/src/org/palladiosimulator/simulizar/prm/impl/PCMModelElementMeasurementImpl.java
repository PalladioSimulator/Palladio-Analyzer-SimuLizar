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
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
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
    public EObject getPcmModelElement() {
        if (pcmModelElement != null && pcmModelElement.eIsProxy()) {
            InternalEObject oldPcmModelElement = (InternalEObject) pcmModelElement;
            pcmModelElement = eResolveProxy(oldPcmModelElement);
            if (pcmModelElement != oldPcmModelElement) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT, oldPcmModelElement,
                            pcmModelElement));
            }
        }
        return pcmModelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EObject basicGetPcmModelElement() {
        return pcmModelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setPcmModelElement(EObject newPcmModelElement) {
        EObject oldPcmModelElement = pcmModelElement;
        pcmModelElement = newPcmModelElement;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT, oldPcmModelElement, pcmModelElement));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasurementSpecification getMeasurementSpecification() {
        if (measurementSpecification != null && measurementSpecification.eIsProxy()) {
            InternalEObject oldMeasurementSpecification = (InternalEObject) measurementSpecification;
            measurementSpecification = (MeasurementSpecification) eResolveProxy(oldMeasurementSpecification);
            if (measurementSpecification != oldMeasurementSpecification) {
                if (eNotificationRequired())
                    eNotify(new ENotificationImpl(this, Notification.RESOLVE,
                            PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION,
                            oldMeasurementSpecification, measurementSpecification));
            }
        }
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MeasurementSpecification basicGetMeasurementSpecification() {
        return measurementSpecification;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMeasurementSpecification(MeasurementSpecification newMeasurementSpecification) {
        MeasurementSpecification oldMeasurementSpecification = measurementSpecification;
        measurementSpecification = newMeasurementSpecification;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION, oldMeasurementSpecification,
                    measurementSpecification));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public double getMeasurementValue() {
        return measurementValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setMeasurementValue(double newMeasurementValue) {
        double oldMeasurementValue = measurementValue;
        measurementValue = newMeasurementValue;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET,
                    PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE, oldMeasurementValue, measurementValue));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            if (resolve)
                return getPcmModelElement();
            return basicGetPcmModelElement();
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            if (resolve)
                return getMeasurementSpecification();
            return basicGetMeasurementSpecification();
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            return getMeasurementValue();
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            setPcmModelElement((EObject) newValue);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            setMeasurementSpecification((MeasurementSpecification) newValue);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            setMeasurementValue((Double) newValue);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            setPcmModelElement((EObject) null);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            setMeasurementSpecification((MeasurementSpecification) null);
            return;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            setMeasurementValue(MEASUREMENT_VALUE_EDEFAULT);
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
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__PCM_MODEL_ELEMENT:
            return pcmModelElement != null;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_SPECIFICATION:
            return measurementSpecification != null;
        case PrmPackage.PCM_MODEL_ELEMENT_MEASUREMENT__MEASUREMENT_VALUE:
            return measurementValue != MEASUREMENT_VALUE_EDEFAULT;
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
        result.append(" (measurementValue: ");
        result.append(measurementValue);
        result.append(')');
        return result.toString();
    }

} // PCMModelElementMeasurementImpl
