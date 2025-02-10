package org.palladiosimulator.simulizar.slidingwindow.aggregators.tests;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.SI;

import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.MedianAggregator;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;

public class MedianAggregatorTest extends SlidingWindowStatisticalCharacterizationAggregatorTest {

    @Override
    protected StatisticalCharacterizationAggregator getStatisticalCharacterizationAggregator(
            NumericalBaseMetricDescription expectedMetric) {
        return new MedianAggregator(expectedMetric);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeFirstTest() {
        return Measure.valueOf(4d, SI.SECOND);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeSecondTest() {
        return Measure.valueOf(5.5, SI.SECOND);
    }

}
