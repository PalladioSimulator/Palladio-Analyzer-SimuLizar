package org.palladiosimulator.simulizar.metrics;

import java.util.Objects;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurement;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementFactory;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;

/**
 * Recorder for saving measurement of a measurement specification and pcm model element in prm
 * model. Can be used as base class for aggregators or performance metrics.
 *
 * @author Joachim Meyer TODO: Should be a _Real_ Recorder, i.e., Recorder from ProbeFramework
 */
public abstract class PRMRecorder {

    private final RuntimeMeasurement measurement;
    private final RuntimeMeasurementModel prmAccess;

    /**
     * Constructor
     *
     * @param prmModel
     *            the model helper.
     * @param measurementSpecification
     *            the measurement specification.
     * @param measuringPoint
     *            The measuring point to be used.
     *
     */
    public PRMRecorder(final RuntimeMeasurementModel prmAccess, final MeasurementSpecification measurementSpecification,
            final MeasuringPoint measuringPoint) {
        super();
        if (!measurementSpecification.isTriggersSelfAdaptations()) {
            throw new RuntimeException("PRM recordering only allowed when self-adaptations shall be triggered");
        }
        this.measurement = RuntimeMeasurementFactory.eINSTANCE.createRuntimeMeasurement();
        this.measurement.setMeasuringPoint(measuringPoint);
        this.measurement.setMeasurementSpecification(measurementSpecification);
        this.prmAccess = prmAccess;
        this.attachToPRM();
    }

    /**
     * Constructor
     *
     * @param prmModel
     *            the model helper.
     * @param measurementSpecification
     *            the measurement specification.
     *
     */
    public PRMRecorder(final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification) {
        this(Objects.requireNonNull(prmAccess), Objects.requireNonNull(measurementSpecification),
                measurementSpecification.getMonitor().getMeasuringPoint());
    }

    private void attachToPRM() {
        if (!this.prmAccess.getMeasurements().contains(this.measurement)) {
            this.prmAccess.getMeasurements().add(this.measurement);
        }
    }

    protected final void detachFromPRM() {
        this.prmAccess.getMeasurements().remove(this.measurement);
    }

    /**
     * Add measurement for measurement specification and PCMModelElementMeasurement to prm model.
     *
     * @param value
     *            the measurement value.
     */
    protected void updateMeasurementValue(final double value) {
        // this has the corresponding PRM instance trigger a notification
        // all attached adapters (such as the Reconfigurator class) are informed
        // event type of notification: Notification.SET
        this.measurement.setMeasuringValue(value);
    }

    /**
     * @return returns the measurementSpecification.
     */
    protected MeasurementSpecification getMeasurementSpecification() {
        return this.measurement.getMeasurementSpecification();
    }

    /**
     * @return returns the pcmModelElementMeasurement.
     */
    protected final RuntimeMeasurement getPRMMeasurement() {
        return this.measurement;
    }

    /**
     * @return returns the prmModel.
     */
    protected RuntimeMeasurementModel getPrmModel() {
        return this.prmAccess;
    }
}
