package org.palladiosimulator.simulizar.metrics;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.TemporalCharacterization;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.palladiosimulator.simulizar.prm.PrmFactory;

/**
 * Recorder for saving measurement of a measurement specification and pcm model element in prm
 * model. Can be used as base class for aggregators or performance metrics.
 * 
 * @author Joachim Meyer TODO: Should be a _Real_ Recorder, i.e., Recorder from ProbeFramework
 */
public abstract class PRMRecorder {
    private final PCMModelElementMeasurement pcmModelElementMeasurement;

    private final MeasurementSpecification measurementSpecification;

    private final TemporalCharacterization temporalCharacterization;

    private final PRMModel prmAccess;

    /**
     * Constructor
     * 
     * @param measurementSpecification
     *            the measurement specification.
     * @param monitoredElement
     * @param modelHelper
     *            the model helper.
     * @param pcmModelElementMeasurement
     *            the prm PCMModelElementMeasurement.
     */
    public PRMRecorder(final PRMModel prmAccess2, final MeasurementSpecification measurementSpecification,
            EObject monitoredElement) {
        super();
        this.pcmModelElementMeasurement = PrmFactory.eINSTANCE.createPCMModelElementMeasurement();
        this.pcmModelElementMeasurement.setPcmModelElement(monitoredElement);
        this.measurementSpecification = measurementSpecification;
        this.temporalCharacterization = measurementSpecification.getTemporalRestriction();
        this.prmAccess = prmAccess2;
    }

    /**
     * Add measurement for measurement specification and PCMModelElementMeasurement to prm model.
     * 
     * @param value
     *            the measurement value.
     */
    protected void addToPRM(final double value) {
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
     * @return returns the pcmModelElementMeasurement.
     */
    protected final PCMModelElementMeasurement getPCMModelElementMeasurement() {
        return this.pcmModelElementMeasurement;
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
        return this.temporalCharacterization;
    }

}
