package org.palladiosimulator.simulizar.slidingwindow.aggregators;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.experimentanalysis.windowaggregators.SlidingWindowAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.StatisticalCharacterization;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;
import org.palladiosimulator.recorderframework.IRecorder;

/**
 * Specialization of the {@link SlidingWindowAggregator} which is devoted to aggregate the
 * measurements collected by a sliding window (usually at simulation-time) in a statistical manner.
 * 
 * @see StatisticalCharacterization
 * 
 * @author Florian Rosenthal
 *
 */
public class SlidingWindowStatisticalCharacterizationAggregator extends SlidingWindowAggregator {

    private final StatisticalCharacterizationAggregator aggregator;

    /**
     * Initializes a new instance of the {@link SlidingWindowStatisticalCharacterizationAggregator}
     * class with the given parameter.
     * 
     * @param aggregator
     *            The {@link StatisticalCharacterizationAggregator} that shall be used for
     *            aggregation of the collected window data.
     * @throws NullPointerException
     *             In case {@code aggregator == null}.
     */
    public SlidingWindowStatisticalCharacterizationAggregator(final StatisticalCharacterizationAggregator aggregator) {
        this.aggregator = Objects.requireNonNull(aggregator);
    }

    /**
     * Initializes a new instance of the {@link SlidingWindowStatisticalCharacterizationAggregator}
     * class with the given parameters.
     * 
     * @param recorderToWriteInto
     *            An {@link IRecorder} implementation to write the aggregated data into.
     * @param aggregator
     *            The {@link StatisticalCharacterizationAggregator} that shall be used for
     *            aggregation of the collected window data.
     * @throws NullPointerException
     *             In case {@code aggregator == null || recorderToWriteInto == null}.
     */
    public SlidingWindowStatisticalCharacterizationAggregator(final IRecorder recorderToWriteInto,
            final StatisticalCharacterizationAggregator aggregator) {
        super(recorderToWriteInto);

        this.aggregator = Objects.requireNonNull(aggregator);
    }

    /**
     * Initializes a new instance of the {@link SlidingWindowStatisticalCharacterizationAggregator}
     * class with the given parameters.
     * 
     * @param recordersToWriteInto
     *            A Collection of {@link IRecorder} implementations to write the aggregated data
     *            into.
     * @param aggregator
     *            The {@link StatisticalCharacterizationAggregator} that shall be used for
     *            aggregation of the collected window data.
     * @throws NullPointerException
     *             In case {@code aggregator == null || recordersToWriteInto == null}.
     * @throws IllegalArgumentException
     *             In case the passed recorder collection is empty.
     */
    public SlidingWindowStatisticalCharacterizationAggregator(final Collection<IRecorder> recordersToWriteInto,
            final StatisticalCharacterizationAggregator aggregator) {
        super(recordersToWriteInto);
        this.aggregator = Objects.requireNonNull(aggregator);
    }

    /**
     * @return A {@link NumericalBaseMetricDescription} indicating the type of measurements this
     *         aggregator can process.
     * @see StatisticalCharacterizationAggregator#getDataMetric()
     */
    @Override
    public final NumericalBaseMetricDescription getExpectedWindowDataMetric() {
        return this.aggregator.getDataMetric();
    }

    @Override
    protected final MeasuringValue processWindowData(final Iterable<MeasuringValue> windowData,
            final Measure<Double, Duration> windowLeftBound, final Measure<Double, Duration> windowLength) {

        Amount<Duration> leftBound = Amount.valueOf(windowLeftBound.getValue(), windowLeftBound.getUnit());
        Amount<Duration> length = Amount.valueOf(windowLength.getValue(), windowLength.getUnit());
        Amount<Duration> rightBound = leftBound.plus(length);

        return this.aggregator.aggregateData(windowData, leftBound, rightBound, Optional.of(length));
    }
}
