package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.activation.UnsupportedDataTypeException;
import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.measurementspec.Measurement;
import org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.pms.Intervall;
import org.palladiosimulator.simulizar.pms.MeasurementSpecification;
import org.palladiosimulator.simulizar.pms.StatisticalCharacterizationEnum;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;

/**
 * The aggregator "Response time".
 * 
 * @author Joachim Meyer
 * 
 */
public class ResponseTimeAggregator extends PRMRecorder implements IMeasurementSourceListener {

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
     *            the response time calculator of ProbeFramework.
     * @param measurementId
     *            id of the measurement.
     * @param monitoredElement
     *            the pcm model element to be monitored.
     * @param modelHelper
     *            the model helper.
     * @param pcmModelElementMeasurement
     *            the PCMModelElementMeasurement from the prm model.
     * @throws UnsupportedDataTypeException
     *             if statistical characterization is not supported. TODO: This class should not
     *             know about PRM, it should publish its results to a Recorder, e.g., a PRM Recorder
     */
    public ResponseTimeAggregator(final PRMAccess prmAccess, final MeasurementSpecification measurementSpecification,
            final Calculator responseTimeCalculator, final String measurementId, final EObject monitoredElement,
            final PCMModelElementMeasurement pcmModelElementMeasurement, final double baseSimulationTime)
            throws UnsupportedDataTypeException {
        super(prmAccess, measurementSpecification, pcmModelElementMeasurement);
        this.responseTimes = new LinkedList<Double>();
        switch (measurementSpecification.getStatisticalCharacterization()) {
        case ARITHMETIC_MEAN:
            this.aggregator = new ArithmeticMean();
            break;
        case MEDIAN:
            this.aggregator = new Median();
            break;
        case GEOMETRIC_MEAN:
            this.aggregator = new GeometricMean();
            break;
        case HARMONIC_MEAN:
            this.aggregator = new HarmonicMean();
            break;
        default:
            throw new UnsupportedDataTypeException("This aggregator is currently not supported");
        }
        if (!(measurementSpecification.getTemporalRestriction() instanceof Intervall)) {
            throw new UnsupportedDataTypeException("Only Intervall is currently supported");
        }
        this.lastSimulationTime = baseSimulationTime;
        this.monitoredElement = monitoredElement;

        responseTimeCalculator.addObserver(this);
    }

    /**
     * @see org.palladiosimulator.measurementspec.listener.IMeasurementSourceListener#newMeasurementAvailable(Measurement)
     */
    @Override
    public void newMeasurementAvailable(final Measurement measurement) {
        final Measure<Double, Duration> currentSimulationTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        final Measure<Double, Duration> responseTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        final double simulationTime = currentSimulationTimeMeasure.doubleValue(SI.SECOND);

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
        this.responseTimes.add(responseTimeMeasure.doubleValue(SI.SECOND));
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
