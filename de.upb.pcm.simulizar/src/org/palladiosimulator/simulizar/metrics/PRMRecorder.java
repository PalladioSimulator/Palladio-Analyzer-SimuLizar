package org.palladiosimulator.simulizar.metrics;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;

/**
 * Recorder for saving measurement of a measurement specification and pcm model element in prm
 * model. Can be used as base class for aggregators or performance metrics.
 * 
 * @author Joachim Meyer
 * TODO: Should be a _Real_ Recorder, i.e., Recorder from ProbeSpec
 */
public abstract class PRMRecorder {
    private PCMModelElementMeasurement pcmModelElementMeasurement;

    private final MeasurementSpecification measurementSpecification;

    private final TemporalCharacterization temporalCharacterization;

    private final PRMAccess prmAccess;

    /**
     * Constructor
     * 
     * @param measurementSpecification
     *            the measurement specification.
     * @param modelHelper
     *            the model helper.
     * @param pcmModelElementMeasurement
     *            the prm PCMModelElementMeasurement.
     */
    public PRMRecorder(final PRMAccess prmAccess, final MeasurementSpecification measurementSpecification,
            final PCMModelElementMeasurement pcmModelElementMeasurement) {
        super();
        this.pcmModelElementMeasurement = this.getPCMModelElementMeasurement();
        this.measurementSpecification = measurementSpecification;
        this.temporalCharacterization = measurementSpecification.getTemporalRestriction();
        this.pcmModelElementMeasurement = pcmModelElementMeasurement;
        this.prmAccess = prmAccess;
    }

    /**
     * Add measurement for measurement specification and PCMModelElementMeasurement to prm model.
     * 
     * @param value
     *            the measurement value.
     */
    protected void addToPRM(final double value) {
        if (this.pcmModelElementMeasurement.getPcmModelElement() == null) {
            this.pcmModelElementMeasurement.setPcmModelElement(this.getMonitoredPCMModellElement());
        }
        this.getPrmModel().getPcmModelElementMeasurements().remove(this.pcmModelElementMeasurement);
        this.pcmModelElementMeasurement.setMeasurementValue(value);
        this.pcmModelElementMeasurement.setMeasurementSpecification(this.measurementSpecification);
        this.getPrmModel().getPcmModelElementMeasurements().add(this.pcmModelElementMeasurement);
    }

    /**
     * @return returns the measurementSpecification.
     */
    protected MeasurementSpecification getMeasurementSpecification() {
        return this.measurementSpecification;
    }

    /**
     * Gets the monitored pcm model element.
     * 
     * @return the monitored pcm model element.
     */
    protected abstract EObject getMonitoredPCMModellElement();

    /**
     * @return returns the pcmModelElementMeasurement.
     */
    protected final PCMModelElementMeasurement getPCMModelElementMeasurement() {
        return this.pcmModelElementMeasurement;
    }

    /**
     * @return returns the prmModel.
     */
    protected PRMModel getPrmModel() {
        return this.prmAccess.getModel();
    }

    /**
     * @return returns the temporalCharacterization.
     */
    protected TemporalCharacterization getTemporalCharacterization() {
        return this.temporalCharacterization;
    }

}
