/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.common.util.EList;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Performance Measurement</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A performance measurement for a pcm element (type level).
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.pms.PerformanceMeasurement#getMeasurementSpecification <em>Measurement Specification</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.pms.PerformanceMeasurement#getMeasuringPoint <em>Measuring Point</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPerformanceMeasurement()
 * @model
 * @generated
 */
public interface PerformanceMeasurement extends UniqueElement {
    /**
     * Returns the value of the '<em><b>Measurement Specification</b></em>' containment reference list.
     * The list contents are of type {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurement Specification</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Measurement Specification</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPerformanceMeasurement_MeasurementSpecification()
     * @model containment="true" required="true"
     * @generated
     */
    EList<MeasurementSpecification> getMeasurementSpecification();

    /**
     * Returns the value of the '<em><b>Measuring Point</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measuring Point</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Measuring Point</em>' reference.
     * @see #setMeasuringPoint(MeasuringPoint)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getPerformanceMeasurement_MeasuringPoint()
     * @model required="true"
     * @generated
     */
    MeasuringPoint getMeasuringPoint();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.pms.PerformanceMeasurement#getMeasuringPoint <em>Measuring Point</em>}' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Measuring Point</em>' reference.
     * @see #getMeasuringPoint()
     * @generated
     */
    void setMeasuringPoint(MeasuringPoint value);

} // PerformanceMeasurement
