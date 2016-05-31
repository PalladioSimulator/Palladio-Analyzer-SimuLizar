package org.palladiosimulator.simulizar.metrics.aggregators;

import java.util.LinkedList;
import java.util.List;

import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.measurementframework.measureprovider.AbstractMeasureProvider;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;
import org.palladiosimulator.simulizar.simulationevents.PeriodicallyTriggeredSimulationEntity;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * Interval-based aggregator for any double (or integer) values. Concrete metric, e.g., response
 * times, is determined by a given MeasurementSpecification.
 * 
 * Note the strategy for aggregation: only if measurements have been received within the interval,
 * the measurements are aggregated. If measurements from previous intervals have to be taken into
 * account, another aggregator needs to be used. Some metrics may need such a behavior, e.g., when
 * aggregating the number of jobs, the last known number from the previous interval appears to be
 * important.
 * 
 * TODO Implement further aggregators as described above.
 * 
 * @author Sebastian Lehrig, Marcus Hilbrich
 */
public class DoubleIntervalAggregator extends PRMRecorder implements IMeasurementSourceListener {

    /** Measurements taken in a given interval. */
    private final List<Double> measurements;

    /** Aggregation strategy to be used, e.g., arithmetic mean. */
    private final IStatisticalCharacterization aggregator;

    /** Metric description of the measurements to be aggregated. */
    private final NumericalBaseMetricDescription metricDescription;

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
    public DoubleIntervalAggregator(final SimuComModel model, final RuntimeMeasurementModel prmAccess,
            final MeasurementSpecification measurementSpecification) {
        super(prmAccess, measurementSpecification);

        if (!(measurementSpecification.getMetricDescription() instanceof NumericalBaseMetricDescription)) {
            throw new RuntimeException(
                    "measurementSpecification must conform to a NumericalBaseMetricDescription for double aggregation!");
        }
        this.metricDescription = (NumericalBaseMetricDescription) measurementSpecification.getMetricDescription();

        this.measurements = new LinkedList<Double>();
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
                DoubleIntervalAggregator.this.finalizeCurrentIntervall();
            }

        };
    }

    /**
     * Calculate StatisticalCharacterization.
     */
    private void finalizeCurrentIntervall() {
        if (this.measurements.size() > 0) {
            final double statisticalCharacterization = this.aggregator
                    .calculateStatisticalCharaterization(this.measurements);
            this.updateMeasurementValue(statisticalCharacterization);
            this.measurements.clear();
        }
    }

    /**
     * @see org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener#newMeasurementAvailable(AbstractMeasureProvider)
     */
    @Override
    public void newMeasurementAvailable(final MeasuringValue measurement) {
        this.measurements.add(getDoubleMeasurement(measurement));
    }

    private double getDoubleMeasurement(final MeasuringValue measurement) {
        return measurement.getMeasureForMetric(this.metricDescription)
                .doubleValue(this.metricDescription.getDefaultUnit());
    }

    /**
     * Nothing to do here.
     */
    @Override
    public final void preUnregister() {
    }
}
