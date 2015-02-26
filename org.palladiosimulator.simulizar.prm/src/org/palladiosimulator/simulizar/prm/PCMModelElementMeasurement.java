/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>PCM Model Element Measurement</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Measurement for a pcm model element. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getPcmModelElement <em>
 * Pcm Model Element</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getMonitorRepository
 * <em>Monitor Repository</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getMeasurementValue
 * <em>Measurement Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.prm.PrmPackage#getPCMModelElementMeasurement()
 * @model
 * @generated
 */
public interface PCMModelElementMeasurement extends UniqueElement {
    /**
     * Returns the value of the '<em><b>Pcm Model Element</b></em>' reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Pcm Model Element</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pcm Model Element</em>' reference.
     * @see #setPcmModelElement(EObject)
     * @see org.palladiosimulator.simulizar.prm.PrmPackage#getPCMModelElementMeasurement_PcmModelElement()
     * @model
     * @generated
     */
    EObject getPcmModelElement();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getPcmModelElement
     * <em>Pcm Model Element</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Pcm Model Element</em>' reference.
     * @see #getPcmModelElement()
     * @generated
     */
    void setPcmModelElement(EObject value);

    /**
     * Returns the value of the '<em><b>Monitor Repository</b></em>' reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Monitor Repository</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Monitor Repository</em>' reference.
     * @see #setMonitorRepository(MonitorRepository)
     * @see org.palladiosimulator.simulizar.prm.PrmPackage#getPCMModelElementMeasurement_MonitorRepository()
     * @model
     * @generated
     */
    MonitorRepository getMonitorRepository();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getMonitorRepository
     * <em>Monitor Repository</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Monitor Repository</em>' reference.
     * @see #getMonitorRepository()
     * @generated
     */
    void setMonitorRepository(MonitorRepository value);

    /**
     * Returns the value of the '<em><b>Measurement Value</b></em>' attribute. The default value is
     * <code>"0.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurement Value</em>' attribute isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measurement Value</em>' attribute.
     * @see #setMeasurementValue(double)
     * @see org.palladiosimulator.simulizar.prm.PrmPackage#getPCMModelElementMeasurement_MeasurementValue()
     * @model default="0.0"
     * @generated
     */
    double getMeasurementValue();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement#getMeasurementValue
     * <em>Measurement Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Measurement Value</em>' attribute.
     * @see #getMeasurementValue()
     * @generated
     */
    void setMeasurementValue(double value);

} // PCMModelElementMeasurement
