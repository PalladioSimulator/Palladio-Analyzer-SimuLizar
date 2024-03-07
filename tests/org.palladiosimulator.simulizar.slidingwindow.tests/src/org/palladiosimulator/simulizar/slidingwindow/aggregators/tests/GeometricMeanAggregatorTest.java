package org.palladiosimulator.simulizar.slidingwindow.aggregators.tests;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.GeometricMeanAggregator;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;

public class GeometricMeanAggregatorTest extends SlidingWindowStatisticalCharacterizationAggregatorTest {

    @Override
    protected StatisticalCharacterizationAggregator getStatisticalCharacterizationAggregator(
            NumericalBaseMetricDescription expectedMetric) {
        return new GeometricMeanAggregator(expectedMetric);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeFirstTest() {
        return Measure.valueOf(7.0533234616974002588733413490183, SI.SECOND);
    }

    @Override
    protected Measure<Double, Duration> getExpectedAggregatedResponseTimeSecondTest() {
        return Measure.valueOf(6.7110052498303912116958243874521, SI.SECOND);
    }

}
