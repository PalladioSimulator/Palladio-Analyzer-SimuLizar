/**
 */
package org.palladiosimulator.servicelevelobjective;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.metricspec.MetricDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Service Level Objective</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getDescription <em>
 * Description</em>}</li>
 * <li>{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getLowerThreshold
 * <em>Lower Threshold</em>}</li>
 * <li>{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getUpperThreshold
 * <em>Upper Threshold</em>}</li>
 * <li>{@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getMeasuringPoint
 * <em>Measuring Point</em>}</li>
 * <li>
 * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getMetricDescription
 * <em>Metric Description</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective()
 * @model
 * @generated
 */
public interface ServiceLevelObjective extends NamedElement {
    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getDescription
     * <em>Description</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Lower Threshold</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Lower Threshold</em>' containment reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Lower Threshold</em>' containment reference.
     * @see #setLowerThreshold(Threshold)
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective_LowerThreshold()
     * @model containment="true"
     * @generated
     */
    Threshold getLowerThreshold();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getLowerThreshold
     * <em>Lower Threshold</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Lower Threshold</em>' containment reference.
     * @see #getLowerThreshold()
     * @generated
     */
    void setLowerThreshold(Threshold value);

    /**
     * Returns the value of the '<em><b>Upper Threshold</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Upper Threshold</em>' containment reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Upper Threshold</em>' containment reference.
     * @see #setUpperThreshold(Threshold)
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective_UpperThreshold()
     * @model containment="true"
     * @generated
     */
    Threshold getUpperThreshold();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getUpperThreshold
     * <em>Upper Threshold</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Upper Threshold</em>' containment reference.
     * @see #getUpperThreshold()
     * @generated
     */
    void setUpperThreshold(Threshold value);

    /**
     * Returns the value of the '<em><b>Measuring Point</b></em>' containment reference. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measuring Point</em>' containment reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measuring Point</em>' containment reference.
     * @see #setMeasuringPoint(MeasuringPoint)
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective_MeasuringPoint()
     * @model containment="true" required="true"
     * @generated
     */
    MeasuringPoint getMeasuringPoint();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getMeasuringPoint
     * <em>Measuring Point</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Measuring Point</em>' containment reference.
     * @see #getMeasuringPoint()
     * @generated
     */
    void setMeasuringPoint(MeasuringPoint value);

    /**
     * Returns the value of the '<em><b>Metric Description</b></em>' reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Metric Description</em>' reference isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Metric Description</em>' reference.
     * @see #setMetricDescription(MetricDescription)
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjective_MetricDescription()
     * @model required="true"
     * @generated
     */
    MetricDescription getMetricDescription();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective#getMetricDescription
     * <em>Metric Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Metric Description</em>' reference.
     * @see #getMetricDescription()
     * @generated
     */
    void setMetricDescription(MetricDescription value);

} // ServiceLevelObjective
