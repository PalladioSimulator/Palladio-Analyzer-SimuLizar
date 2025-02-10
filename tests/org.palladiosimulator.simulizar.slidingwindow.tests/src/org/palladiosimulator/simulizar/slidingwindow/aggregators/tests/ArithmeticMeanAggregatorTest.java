package org.palladiosimulator.simulizar.slidingwindow.aggregators.tests;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.SI;

import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.ArithmeticMeanAggregator;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;

public class ArithmeticMeanAggregatorTest extends SlidingWindowStatisticalCharacterizationAggregatorTest {

    @Override
    protected StatisticalCharacterizationAggregator getStatisticalCharacterizationAggregator(
            NumericalBaseMetricDescription expectedMetric) {
        return new ArithmeticMeanAggregator(expectedMetric);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeFirstTest() {
        return Measure.valueOf(452.025, SI.SECOND);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeSecondTest() {
        return Measure.valueOf(362.72, SI.SECOND);
    }

}
