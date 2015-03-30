package org.palladiosimulator.simulizar.metrics;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;

/**
 * AbstractRecorder for saving measurement of a measurement specification and pcm model element in
 * prm model. Can be used as base class for aggregators or performance metrics.
 * 
 * TODO: Should be a _Real_ AbstractRecorder, i.e., AbstractRecorder from ProbeFramework
 * 
 * @author Joachim Meyer, Sebastian Lehrig
 */
public abstract class PRMRecorder {
    private final PRMMeasurement measurement;

    private final PRMModel prmAccess;

    /**
     * Constructor
     * 
     * @param measurementSpecification
     *            the measurement specification.
     * @param monitoredElement
     * @param modelHelper
     *            the model helper.
     * @param measuringPoint
     *            the measuring point.
     */
    public PRMRecorder(final PRMModel prmAccess, final MeasurementSpecification measurementSpecification,
            MeasuringPoint measuringPoint) {
        super();
        this.measurement = PrmFactory.eINSTANCE.createPRMMeasurement();
        this.measurement.setMeasuringPoint(measuringPoint);
        this.measurement.setMeasurementSpecification(measurementSpecification);
        this.prmAccess = prmAccess;
    }

    /**
     * Add measurement for measurement specification and PRMMeasurement to prm model.
     * 
     * @param value
     *            the measurement value.
     */
    protected void addToPRM(final double value) {
        this.getPrmModel().getMeasurements().remove(this.measurement);
        this.measurement.setMeasuringValue(value);
        this.getPrmModel().getMeasurements().add(this.measurement);
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
    protected final PRMMeasurement getPRMMeasurement() {
        return this.measurement;
    }

    /**
     * @return returns the prmModel.
     */
    protected PRMModel getPrmModel() {
        return this.prmAccess;
    }

    /**
     * @return returns the temporalCharacterization.
     */
    protected TemporalCharacterization getTemporalCharacterization() {
        return this.measurement.getMeasurementSpecification().getTemporalRestriction();
    }

}
