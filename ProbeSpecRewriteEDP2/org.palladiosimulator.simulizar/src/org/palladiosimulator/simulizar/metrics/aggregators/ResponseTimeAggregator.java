package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.quantity.Duration;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;
import de.uka.ipd.sdq.probespec.framework.measurements.BasicMeasurement;
import de.uka.ipd.sdq.probespec.framework.measurements.Measurement;
import de.uka.ipd.sdq.probespec.framework.measurements.MeasurementSet;

/**
 * The aggregator "Response time".
 * 
 * @author Joachim Meyer
 * 
 */
public class ResponseTimeAggregator extends PRMRecorder implements ICalculatorListener {

    private final List<Double> responseTimes;

    private final IStatisticalCharacterization aggregator;

    private double lastSimulationTime;

    private final EObject monitoredElement;

    /**
     * Constructor
     * 
     * @param measurementSpecification
     *            the measurement specification.
     * @param responseTimeCalculator
     *            the response time calculator of the probe specification framework.
     * @param measurementId
     *            id of the measurement.
     * @param monitoredElement
     *            the pcm model element to be monitored.
     * @param modelHelper
     *            the model helper.
     * @param pcmModelElementMeasurement
     *            the PCMModelElementMeasurement from the prm model.
     * @throws UnsupportedDataTypeException
     *             if statistical characterization is not supported.
     * TODO: This class should not know about PRM, it should publish its results to a Recorder, e.g., a PRM Recorder
     */
    public ResponseTimeAggregator(final PRMAccess prmAccess, final MeasurementSpecification measurementSpecification,
            final Calculator responseTimeCalculator, final String measurementId, final EObject monitoredElement,
            final PCMModelElementMeasurement pcmModelElementMeasurement, final double baseSimulationTime)
                    throws UnsupportedDataTypeException {
        super(prmAccess, measurementSpecification, pcmModelElementMeasurement);
        this.responseTimes = new LinkedList<Double>();
        if (measurementSpecification.getStatisticalCharacterization() == StatisticalCharacterizationEnum.ARITHMETIC_MEAN) {
            this.aggregator = new ArithmeticMean();
        } else {
            throw new UnsupportedDataTypeException("Only Arithmetic Mean is currently supported");
        }
        if (!(measurementSpecification.getTemporalRestriction() instanceof Intervall)) {
            throw new UnsupportedDataTypeException("Only Intervall is currently supported");
        }
        this.lastSimulationTime = baseSimulationTime;
        this.monitoredElement = monitoredElement;

        responseTimeCalculator.registerCalculatorListener(this);
    }

    /**
     * @see de.uka.ipd.sdq.probespec.framework.calculator.IMeasurementSourceListener#measurementTaken(Measurement)
     */
    @Override
    public void measurementTaken(final Measurement measurement) {
        final MeasurementSet measurementSet = (MeasurementSet) measurement;
        final BasicMeasurement<Double, Duration> basicMeasurement = (BasicMeasurement<Double, Duration>) measurementSet.getSubsumedMeasurements().get(1);

        final double simulationTime = basicMeasurement.getMeasure().getValue();

        if (this.getMeasurementSpecification().getTemporalRestriction() instanceof Intervall) {
            final Intervall intervall = (Intervall) this.getMeasurementSpecification().getTemporalRestriction();
            if (simulationTime - this.lastSimulationTime >= intervall.getIntervall()) {
                // calculate StatisticalCharacterization
                final double statisticalCharacterization = this.aggregator
                        .calculateStatisticalCharaterization(this.responseTimes);
                this.addToPRM(statisticalCharacterization);

                this.lastSimulationTime = simulationTime;
                this.responseTimes.clear();

            }
        }

        this.responseTimes.add(basicMeasurement.getMeasure().getValue());

    }

    /**
     * @see org.palladiosimulator.simulizar.metrics.PRMRecorder#getMonitoredPCMModellElement()
     */
    @Override
    protected EObject getMonitoredPCMModellElement() {
        return this.monitoredElement;
    }

    @Override
    public void preUnregister() {
        // TODO Auto-generated method stub

    }
}
