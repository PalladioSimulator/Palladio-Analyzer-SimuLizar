/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Measurement Specification</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A measurement specification for a pcm element including the performance metric and the statistical charaterization.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getTemporalRestriction <em>Temporal Restriction</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getPerformanceMetric <em>Performance Metric</em>}</li>
 *   <li>{@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getStatisticalCharacterization <em>Statistical Characterization</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMeasurementSpecification()
 * @model
 * @generated
 */
public interface MeasurementSpecification extends UniqueElement {
    /**
     * Returns the value of the '<em><b>Temporal Restriction</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Temporal Restriction</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Temporal Restriction</em>' containment reference.
     * @see #setTemporalRestriction(TemporalCharacterization)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMeasurementSpecification_TemporalRestriction()
     * @model containment="true" required="true"
     * @generated
     */
    TemporalCharacterization getTemporalRestriction();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getTemporalRestriction <em>Temporal Restriction</em>}' containment reference.
     * <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * @param value the new value of the '<em>Temporal Restriction</em>' containment reference.
     * @see #getTemporalRestriction()
     * @generated
     */
    void setTemporalRestriction(TemporalCharacterization value);

    /**
     * Returns the value of the '<em><b>Performance Metric</b></em>' attribute. The literals are
     * from the enumeration {@link org.palladiosimulator.simulizar.pms.PerformanceMetricEnum}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Performance Metric</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Performance Metric</em>' attribute.
     * @see org.palladiosimulator.simulizar.pms.PerformanceMetricEnum
     * @see #setPerformanceMetric(PerformanceMetricEnum)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMeasurementSpecification_PerformanceMetric()
     * @model required="true"
     * @generated
     */
    PerformanceMetricEnum getPerformanceMetric();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getPerformanceMetric <em>Performance Metric</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * @param value the new value of the '<em>Performance Metric</em>' attribute.
     * @see org.palladiosimulator.simulizar.pms.PerformanceMetricEnum
     * @see #getPerformanceMetric()
     * @generated
     */
    void setPerformanceMetric(PerformanceMetricEnum value);

    /**
     * Returns the value of the '<em><b>Statistical Characterization</b></em>' attribute. The
     * literals are from the enumeration
     * {@link org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Statistical Characterization</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Statistical Characterization</em>' attribute.
     * @see org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * @see #setStatisticalCharacterization(StatisticalCharacterizationEnum)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMeasurementSpecification_StatisticalCharacterization()
     * @model required="true"
     * @generated
     */
    StatisticalCharacterizationEnum getStatisticalCharacterization();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.pms.MeasurementSpecification#getStatisticalCharacterization
     * <em>Statistical Characterization</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     * 
     * @param value
     *            the new value of the '<em>Statistical Characterization</em>' attribute.
     * @see org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum
     * @see #getStatisticalCharacterization()
     * @generated
     */
    void setStatisticalCharacterization(StatisticalCharacterizationEnum value);

} // MeasurementSpecification
