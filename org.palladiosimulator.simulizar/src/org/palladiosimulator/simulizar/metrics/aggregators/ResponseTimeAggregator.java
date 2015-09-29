package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.measureprovider.AbstractMeasureProvider;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * The aggregator "Response time".
 *
 * @author Joachim Meyer
 *
 */
public class ResponseTimeAggregator extends PRMRecorder implements IMeasurementSourceListener {

    private final List<Double> responseTimes;

    private final IStatisticalCharacterization aggregator;

    /**
     * Constructor
     *
     * @param measurementSpecification
     *            the measurement specification. id of the measurement.
     * @param monitoredElement
     *            the pcm model element to be monitored.
     * @throws UnsupportedOperationException
     *             if temporal characterization is not supported. TODO: This class should not know
     *             about PRM, it should publish its results to a Recorder, e.g., a PRM Recorder
     */
    public ResponseTimeAggregator(final SimuComModel model, final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification, final MeasuringPoint measuringPoint) {
        super(prmAccess, measurementSpecification, measuringPoint);
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
            throw new UnsupportedOperationException("This aggregator is currently not supported");
        }
        if (!(measurementSpecification.getTemporalRestriction() instanceof Intervall)) {
            throw new UnsupportedOperationException("Only Intervall is currently supported");
        }
        new PeriodicallyTriggeredSimulationEntity(model, 0.0,
                ((Intervall) measurementSpecification.getTemporalRestriction()).getIntervall()) {

            @Override
            protected void triggerInternal() {
                ResponseTimeAggregator.this.finalizeCurrentIntervall();
            }
        };
    }

    /**
     *
     */
    private void finalizeCurrentIntervall() {
        if (this.responseTimes.size() > 0) {
            // calculate StatisticalCharacterization
            final double statisticalCharacterization = this.aggregator
                    .calculateStatisticalCharaterization(this.responseTimes);
            this.updateMeasurementValue(statisticalCharacterization);
            this.responseTimes.clear();
        }
    }

    /**
     * @see org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener#newMeasurementAvailable(AbstractMeasureProvider)
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue measurement) {
        final Measure<Double, Duration> responseTimeMeasure = measurement
                .getMeasureForMetric(MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        this.responseTimes.add(responseTimeMeasure.doubleValue(SI.SECOND));
    }

    @Override
    public void preUnregister() {
    }
}
