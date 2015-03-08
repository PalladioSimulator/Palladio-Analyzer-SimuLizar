/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Monitor</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> A performance measurement for a pcm element (type level). <!--
 * end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasurementSpecifications
 * <em>Measurement Specifications</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasuringPoint <em>
 * Measuring Point</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMonitorRepository <em>
 * Monitor Repository</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMonitor()
 * @model
 * @generated
 */
public interface Monitor extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Measurement Specifications</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification}. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor
     * <em>Monitor</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measurement Specifications</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measurement Specifications</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMonitor_MeasurementSpecifications()
     * @see org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification#getMonitor
     * @model opposite="monitor" containment="true" required="true"
     * @generated
     */
    EList<MeasurementSpecification> getMeasurementSpecifications();

    /**
     * Returns the value of the '<em><b>Measuring Point</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Measuring Point</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Measuring Point</em>' reference.
     * @see #setMeasuringPoint(MeasuringPoint)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMonitor_MeasuringPoint()
     * @model required="true"
     * @generated
     */
    MeasuringPoint getMeasuringPoint();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMeasuringPoint
     * <em>Measuring Point</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Measuring Point</em>' reference.
     * @see #getMeasuringPoint()
     * @generated
     */
    void setMeasuringPoint(MeasuringPoint value);

    /**
     * Returns the value of the '<em><b>Monitor Repository</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.monitorrepository.MonitorRepository#getMonitors
     * <em>Monitors</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Monitor Repository</em>' container reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Monitor Repository</em>' container reference.
     * @see #setMonitorRepository(MonitorRepository)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getMonitor_MonitorRepository()
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorRepository#getMonitors
     * @model opposite="monitors" required="true" transient="false"
     * @generated
     */
    MonitorRepository getMonitorRepository();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.Monitor#getMonitorRepository
     * <em>Monitor Repository</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Monitor Repository</em>' container reference.
     * @see #getMonitorRepository()
     * @generated
     */
    void setMonitorRepository(MonitorRepository value);

} // Monitor
