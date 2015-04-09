/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.metricspec.MetricDescription;

import de.uka.ipd.sdq.identifier.Identifier;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Measurement Specification</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> A measurement specification for a pcm element including the performance
 * metric and the statistical charaterization. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getTemporalRestriction
 * <em>Temporal Restriction</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getStatisticalCharacterization
 * <em>Statistical Characterization</em>}</li>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMetricDescription
 * <em>Metric Description</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor
 * <em>Monitor</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getName
 * <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification()
 * @model
 * @generated
 */
public interface MeasurementSpecification extends EObject, Identifier {
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
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification_TemporalRestriction()
     * @model containment="true" required="true"
     * @generated
     */
    TemporalCharacterization getTemporalRestriction();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getTemporalRestriction
     * <em>Temporal Restriction</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Temporal Restriction</em>' containment reference.
     * @see #getTemporalRestriction()
     * @generated
     */
    void setTemporalRestriction(TemporalCharacterization value);

    /**
     * Returns the value of the '<em><b>Statistical Characterization</b></em>' attribute. The
     * default value is <code>"None"</code>. The literals are from the enumeration
     * {@link org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Statistical Characterization</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Statistical Characterization</em>' attribute.
     * @see org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
     * @see #setStatisticalCharacterization(StatisticalCharacterizationEnum)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification_StatisticalCharacterization()
     * @model default="None" required="true"
     * @generated
     */
    StatisticalCharacterizationEnum getStatisticalCharacterization();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getStatisticalCharacterization
     * <em>Statistical Characterization</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Statistical Characterization</em>' attribute.
     * @see org.palladiosimulator.simulizar.monitorrepository.StatisticalCharacterizationEnum
     * @see #getStatisticalCharacterization()
     * @generated
     */
    void setStatisticalCharacterization(StatisticalCharacterizationEnum value);

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
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification_MetricDescription()
     * @model required="true"
     * @generated
     */
    MetricDescription getMetricDescription();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMetricDescription
     * <em>Metric Description</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Metric Description</em>' reference.
     * @see #getMetricDescription()
     * @generated
     */
    void setMetricDescription(MetricDescription value);

    /**
     * Returns the value of the '<em><b>Monitor</b></em>' container reference. It is bidirectional
     * and its opposite is '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasurementSpecifications
     * <em>Measurement Specifications</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Monitor</em>' container reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Monitor</em>' container reference.
     * @see #setMonitor(Monitor)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification_Monitor()
     * @see org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasurementSpecifications
     * @model opposite="measurementSpecifications" required="true" transient="false"
     * @generated
     */
    Monitor getMonitor();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor
     * <em>Monitor</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Monitor</em>' container reference.
     * @see #getMonitor()
     * @generated
     */
    void setMonitor(Monitor value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is
     * <code>""</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMeasurementSpecification_Name()
     * @model default="" volatile="true" derived="true" annotation=
     *        "http://www.eclipse.org/emf/2002/Ecore/OCL derivation='if self.temporalRestriction->notEmpty() then self.monitor.entityName + \': \' + self.statisticalCharacterization.toString() + \' of \' + self.temporalRestriction.oclAsType(ecore::EObject).eClass().name else self.monitor.entityName + \': \' + self.statisticalCharacterization.toString() endif'"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // MeasurementSpecification
