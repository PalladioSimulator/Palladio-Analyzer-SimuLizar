package org.palladiosimulator.simulizar.metrics;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;
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

    private static final PrmFactory PRM_FACTORY = PrmFactory.eINSTANCE;

    private final PCMModelElementMeasurement pcmModelElementMeasurement;
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
    public PRMRecorder(PRMModel prmModel, MeasurementSpecification measurementSpecification, EObject monitoredElement) {
        super();
        this.pcmModelElementMeasurement = PRM_FACTORY.createPCMModelElementMeasurement();
        this.pcmModelElementMeasurement.setPcmModelElement(monitoredElement);
        this.pcmModelElementMeasurement.setMeasurementSpecification(measurementSpecification);
        this.prmAccess = prmModel;
        attachToPRM();
    }

    private void attachToPRM() {
        if (!this.prmAccess.getPcmModelElementMeasurements().contains(this.pcmModelElementMeasurement)) {
            this.prmAccess.getPcmModelElementMeasurements().add(this.pcmModelElementMeasurement);
        }
    }

    protected final void detachFromPRM() {
        if (this.prmAccess.getPcmModelElementMeasurements().contains(this.pcmModelElementMeasurement)) {
            this.prmAccess.getPcmModelElementMeasurements().remove(this.pcmModelElementMeasurement);
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
        this.pcmModelElementMeasurement.setMeasurementValue(value);
    }

    /**
     * @return returns the measurementSpecification.
     */
    protected MeasurementSpecification getMeasurementSpecification() {
        return this.pcmModelElementMeasurement.getMeasurementSpecification();
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
        return getMeasurementSpecification().getTemporalRestriction();
    }

}
