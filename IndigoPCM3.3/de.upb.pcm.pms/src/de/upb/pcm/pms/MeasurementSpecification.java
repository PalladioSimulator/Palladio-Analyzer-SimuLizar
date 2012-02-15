/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A measurement specification for a pcm element including the performance metric and the statistical charaterization.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.pms.MeasurementSpecification#getTemporalRestriction <em>Temporal Restriction</em>}</li>
 *   <li>{@link de.upb.pcm.pms.MeasurementSpecification#getPerformanceMetric <em>Performance Metric</em>}</li>
 *   <li>{@link de.upb.pcm.pms.MeasurementSpecification#getStatisticalCharacterization <em>Statistical Characterization</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.pms.PmsPackage#getMeasurementSpecification()
 * @model
 * @generated
 */
public interface MeasurementSpecification extends UniqueElement
{
   /**
    * Returns the value of the '<em><b>Temporal Restriction</b></em>' containment reference.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Temporal Restriction</em>' containment reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Temporal Restriction</em>' containment reference.
    * @see #setTemporalRestriction(TemporalCharacterization)
    * @see de.upb.pcm.pms.PmsPackage#getMeasurementSpecification_TemporalRestriction()
    * @model containment="true" required="true"
    * @generated
    */
   TemporalCharacterization getTemporalRestriction();

   /**
    * Sets the value of the '{@link de.upb.pcm.pms.MeasurementSpecification#getTemporalRestriction <em>Temporal Restriction</em>}' containment reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Temporal Restriction</em>' containment reference.
    * @see #getTemporalRestriction()
    * @generated
    */
   void setTemporalRestriction(TemporalCharacterization value);

   /**
    * Returns the value of the '<em><b>Performance Metric</b></em>' attribute.
    * The literals are from the enumeration {@link de.upb.pcm.pms.PerformanceMetricEnum}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Performance Metric</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Performance Metric</em>' attribute.
    * @see de.upb.pcm.pms.PerformanceMetricEnum
    * @see #setPerformanceMetric(PerformanceMetricEnum)
    * @see de.upb.pcm.pms.PmsPackage#getMeasurementSpecification_PerformanceMetric()
    * @model required="true"
    * @generated
    */
   PerformanceMetricEnum getPerformanceMetric();

   /**
    * Sets the value of the '{@link de.upb.pcm.pms.MeasurementSpecification#getPerformanceMetric <em>Performance Metric</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Performance Metric</em>' attribute.
    * @see de.upb.pcm.pms.PerformanceMetricEnum
    * @see #getPerformanceMetric()
    * @generated
    */
   void setPerformanceMetric(PerformanceMetricEnum value);

   /**
    * Returns the value of the '<em><b>Statistical Characterization</b></em>' attribute.
    * The literals are from the enumeration {@link de.upb.pcm.pms.StatisticalCharacterizationEnum}.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Statistical Characterization</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Statistical Characterization</em>' attribute.
    * @see de.upb.pcm.pms.StatisticalCharacterizationEnum
    * @see #setStatisticalCharacterization(StatisticalCharacterizationEnum)
    * @see de.upb.pcm.pms.PmsPackage#getMeasurementSpecification_StatisticalCharacterization()
    * @model required="true"
    * @generated
    */
   StatisticalCharacterizationEnum getStatisticalCharacterization();

   /**
    * Sets the value of the '{@link de.upb.pcm.pms.MeasurementSpecification#getStatisticalCharacterization <em>Statistical Characterization</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Statistical Characterization</em>' attribute.
    * @see de.upb.pcm.pms.StatisticalCharacterizationEnum
    * @see #getStatisticalCharacterization()
    * @generated
    */
   void setStatisticalCharacterization(StatisticalCharacterizationEnum value);

} // MeasurementSpecification
