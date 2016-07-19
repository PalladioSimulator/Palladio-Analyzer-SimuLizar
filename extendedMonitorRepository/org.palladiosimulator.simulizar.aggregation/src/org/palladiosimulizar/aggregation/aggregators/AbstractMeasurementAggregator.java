package org.palladiosimulizar.aggregation.aggregators;

import java.util.Objects;
import java.util.Optional;

import javax.measure.quantity.Duration;

import org.jscience.physics.amount.Amount;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.StatisticalCharacterization;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.metrics.PRMRecorder;

public abstract class AbstractMeasurementAggregator extends PRMRecorder implements IMeasurementSourceListener {

    private final StatisticalCharacterizationAggregator aggregator;
    private final NumericalBaseMetricDescription expectedMetric;

    public AbstractMeasurementAggregator(NumericalBaseMetricDescription expectedMetric,
            RuntimeMeasurementModel prmAccess, MeasurementSpecification measurementSpecification,
            StatisticalCharacterization statisticalCharacterization) {

        super(prmAccess, measurementSpecification);
        this.expectedMetric = Objects.requireNonNull(expectedMetric);
        this.aggregator = Objects.requireNonNull(statisticalCharacterization).getAggregator(expectedMetric);
    }

    @Override
    public final void newMeasurementAvailable(MeasuringValue newMeasurement) {
        if (!Objects.requireNonNull(newMeasurement).isCompatibleWith(this.expectedMetric)
                && !MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(this.expectedMetric,
                        newMeasurement.getMetricDesciption())) {
            throw new IllegalStateException("Somehow a wrong measurement kind was passed.");
        }
        collectMeasurement(newMeasurement);
        if (aggregationRequired()) {
            onPreAggregate();
            aggregate();
            onPostAggregate();
        }
    }

    @Override
    public void preUnregister() {
        super.detachFromPRM();
        clear();

    }

    public abstract void clear();

    protected abstract boolean aggregationRequired();

    protected abstract Amount<Duration> getIntervalStartTime();

    protected abstract Amount<Duration> getIntervalEndTime();

    protected abstract Iterable<MeasuringValue> getDataToAggregate();

    protected abstract void collectMeasurement(MeasuringValue newMeasurement);

    protected void onPreAggregate() {

    }

    protected void onPostAggregate() {
    }

    private void aggregate() {
        Amount<Duration> start = getIntervalStartTime();
        Amount<Duration> end = getIntervalEndTime();

        MeasuringValue aggregatedData = this.aggregator.aggregateData(getDataToAggregate(), start, end,
                Optional.empty());

        // forward aggregated data (expressed as double in default unit of numerical base metric)
        super.updateMeasurementValue(aggregatedData.getMeasureForMetric(this.expectedMetric)
                .doubleValue(this.expectedMetric.getDefaultUnit()));
    }
}
