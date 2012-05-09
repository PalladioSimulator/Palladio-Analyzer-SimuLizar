package de.upb.pcm.simulizar.metrics.aggregators;

import java.util.Vector;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.Measure;
import javax.measure.quantity.Quantity;

import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.probespec.framework.calculator.Calculator;
import de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener;
import de.upb.pcm.pms.Intervall;
import de.upb.pcm.pms.MeasurementSpecification;
import de.upb.pcm.pms.StatisticalCharacterizationEnum;
import de.upb.pcm.prm.PCMModelElementMeasurement;
import de.upb.pcm.simulizar.access.PRMAccess;
import de.upb.pcm.simulizar.metrics.PRMRecorder;

/**
 * The aggregator "Response time".
 * 
 * @author Joachim Meyer
 * 
 */
public class ResponseTimeAggregator extends PRMRecorder implements ICalculatorListener {

    private final Vector<Double> responseTimes;

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
     */
    public ResponseTimeAggregator(final PRMAccess prmAccess, final MeasurementSpecification measurementSpecification,
            final Calculator responseTimeCalculator, final String measurementId, final EObject monitoredElement,
            final PCMModelElementMeasurement pcmModelElementMeasurement, final double baseSimulationTime)
            throws UnsupportedDataTypeException {
        super(prmAccess, measurementSpecification, pcmModelElementMeasurement);
        this.responseTimes = new Vector<Double>();
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

        responseTimeCalculator.addCalculatorListener(this);
    }

    /**
     * @see de.uka.ipd.sdq.probespec.framework.calculator.ICalculatorListener#calculated(java.util.Vector)
     */
    @Override
    public void calculated(final Vector<Measure<?, ? extends Quantity>> resultTuple) {

        final double simulationTime = (Double) resultTuple.get(1).getValue();
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

        this.responseTimes.add((Double) resultTuple.get(0).getValue());

    }

    /**
     * @see de.upb.pcm.simulizar.metrics.PRMRecorder#getMonitoredPCMModellElement()
     */
    @Override
    protected EObject getMonitoredPCMModellElement() {
        return this.monitoredElement;
    }

}
