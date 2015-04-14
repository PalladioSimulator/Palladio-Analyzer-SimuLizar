package org.palladiosimulator.simulizar.metrics;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PRMMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;

/**
 * Recorder for saving measurement of a measurement specification and pcm model element in prm
 * model. Can be used as base class for aggregators or performance metrics.
 * 
 * @author Joachim Meyer TODO: Should be a _Real_ Recorder, i.e., Recorder from ProbeFramework
 */
public abstract class PRMRecorder {

    private static final PrmFactory PRM_FACTORY = PrmFactory.eINSTANCE;

    private final PRMMeasurement measurement;
    private final PRMModel prmAccess;

    /**
     * Constructor
     * 
     * @param prmModel
     *            the model helper.
     * @param measurementSpecification
     *            the measurement specification.
     * @param monitoredElement
     *            The model object under consideration, e.g., the {@link EObject} associated with
     *            the given measurement specification.
     * 
     */
    public PRMRecorder(final PRMModel prmAccess, final MeasurementSpecification measurementSpecification,
            MeasuringPoint measuringPoint) {
        super();
        this.measurement = PrmFactory.eINSTANCE.createPRMMeasurement();
        this.measurement.setMeasuringPoint(measuringPoint);
        this.measurement.setMeasurementSpecification(measurementSpecification);
        this.prmAccess = prmAccess;
        attachToPRM();
    }

    private void attachToPRM() {
        if (!this.prmAccess.getMeasurements().contains(this.measurement)) {
            this.prmAccess.getMeasurements().add(this.measurement);
        }
    }

    protected final void detachFromPRM() {
        if (this.prmAccess.getMeasurements().contains(this.measurement)) {
            this.prmAccess.getMeasurements().remove(this.measurement);
        }
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
        return getMeasurementSpecification().getTemporalRestriction();
    }

}
