package org.palladiosimulator.simulizar.slidingwindow.aggregators.tests;

import jakarta.measure.Measure;
import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.SI;

import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.HarmonicMeanAggregator;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;

public class HarmonicMeanAggregatorTest extends SlidingWindowStatisticalCharacterizationAggregatorTest {

    @Override
    protected StatisticalCharacterizationAggregator getStatisticalCharacterizationAggregator(
            NumericalBaseMetricDescription expectedMetric) {
        return new HarmonicMeanAggregator(expectedMetric);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeFirstTest() {
        double sumOfInverses = 1 / 0.1 + 1 / 5.5 + 1 / 2.5 + 1 / 1800;
        return Measure.valueOf(4d / sumOfInverses, SI.SECOND);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeSecondTest() {
        double sumOfInverses = 1 / 0.1 + 2 / 5.5 + 1 / 2.5 + 1 / 1800;
        return Measure.valueOf(5 / sumOfInverses, SI.SECOND);
    }

}
