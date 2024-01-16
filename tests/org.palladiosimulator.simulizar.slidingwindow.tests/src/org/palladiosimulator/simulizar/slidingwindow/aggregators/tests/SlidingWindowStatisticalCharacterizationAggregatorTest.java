package org.palladiosimulator.simulizar.slidingwindow.aggregators.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.NonSI;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.windowaggregators.tests.SlidingWindowAggregatorTest;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.NumericalBaseMetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.statisticalcharacterization.StatisticalCharacterizationAggregator;
import org.palladiosimulator.simulizar.slidingwindow.aggregators.SlidingWindowStatisticalCharacterizationAggregator;

public abstract class SlidingWindowStatisticalCharacterizationAggregatorTest extends SlidingWindowAggregatorTest {

    private static final MetricSetDescription WINDOW_DATA_METRIC = MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE;
    private static final NumericalBaseMetricDescription RESULT_METRIC = (NumericalBaseMetricDescription) MetricDescriptionConstants.RESPONSE_TIME_METRIC;

    private Measure<Double, Duration> almostZeroResponseTime;
    private Measure<Double, Duration> secondsResponseTime;
    private Measure<Double, Duration> milliSecondsResponseTime;
    private Measure<Double, Duration> minsResponseTime;

    protected MeasuringValue exepectedAggregatedResponseTimeMeasuringValueFirstTest;
    protected MeasuringValue exepectedAggregatedResponseTimeMeasuringValueSecondTest;

    // constant that denotes the maximum delta between double values for which both numbers are
    // still considered equal
    private static final double DELTA = Math.pow(10, -4);

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        this.aggregatorUnderTest = new SlidingWindowStatisticalCharacterizationAggregator(this.dummyRecorder,
                getStatisticalCharacterizationAggregator(RESULT_METRIC));
        this.almostZeroResponseTime = Measure.valueOf(0.1d, SI.SECOND);
        this.secondsResponseTime = Measure.valueOf(5.5d, SI.SECOND);
        this.milliSecondsResponseTime = Measure.valueOf(2500d, SI.MILLI(SI.SECOND));
        this.minsResponseTime = Measure.valueOf(30d, NonSI.MINUTE);

        this.exepectedAggregatedResponseTimeMeasuringValueFirstTest = createResponseTimeTupleMeasurement(
                getExpectedAggregatedResponseTimeFirstTest());
        this.exepectedAggregatedResponseTimeMeasuringValueSecondTest = createResponseTimeTupleMeasurement(
                getExpectedAggregatedResponseTimeSecondTest());
    }

    protected abstract StatisticalCharacterizationAggregator getStatisticalCharacterizationAggregator(
            NumericalBaseMetricDescription expectedMetric);

    /**
     * Response times to aggregate: 0.1s, 5.5s, 2500ms, 30min
     * 
     * @return
     */
    protected abstract Measure<Double, Duration> getExpectedAggregatedResponseTimeFirstTest();

    /**
     * Response times to aggregate: 0.1s, 5.5s, 5.5s 2500ms, 30min
     * 
     * @return
     */
    protected abstract Measure<Double, Duration> getExpectedAggregatedResponseTimeSecondTest();

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    private MeasuringValue createResponseTimeTupleMeasurement(final Measure<Double, Duration> responseTime) {

        Measure<Double, Duration> pointInTimeMeasure = Measure
                .valueOf(this.currentLowerBound.getValue() + this.windowLength.getValue(), SI.SECOND);
        return new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, responseTime);
    }

    private void addResponseTimeTupleMeasurements() {
        // window position: [0-10]

        Measure<Double, Duration> pointInTimeMeasure = Measure.valueOf(0d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.almostZeroResponseTime));

        pointInTimeMeasure = Measure.valueOf(3d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.milliSecondsResponseTime));

        pointInTimeMeasure = Measure.valueOf(4d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.minsResponseTime));

        pointInTimeMeasure = Measure.valueOf(9d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.secondsResponseTime));
    }

    private void addResponseTimeTupleMeasurementsOddNumber() {
        // window position: [0-10]

        Measure<Double, Duration> pointInTimeMeasure = Measure.valueOf(0d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.almostZeroResponseTime));

        pointInTimeMeasure = Measure.valueOf(3d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.milliSecondsResponseTime));

        pointInTimeMeasure = Measure.valueOf(4d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.minsResponseTime));

        pointInTimeMeasure = Measure.valueOf(5d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.secondsResponseTime));

        pointInTimeMeasure = Measure.valueOf(8d, SI.SECOND);
        this.data.addLast(new TupleMeasurement(WINDOW_DATA_METRIC, pointInTimeMeasure, this.secondsResponseTime));
    }

    private void assertLastRecordedMeasurementEquals(final MeasuringValue expected) {
        MeasuringValue lastMeasurement = this.dummyRecorder.getLastMeasurement();
        assertNotNull(lastMeasurement);
        assertMeasurementsEqual(expected, lastMeasurement);
    }

    private static void assertMeasurementsEqual(final MeasuringValue expected, final MeasuringValue actual) {
        Measure<Double, Dimensionless> expectedResponseTime = expected.getMeasureForMetric(RESULT_METRIC);
        Measure<Double, Dimensionless> actualResponseTime = actual.getMeasureForMetric(RESULT_METRIC);

        assertEquals(expectedResponseTime.getValue(), actualResponseTime.getValue(), DELTA);
    }

    @Test
    public void testOnSlidingWindowFullEmptyData() {
        this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
        assertLastRecordedMeasurementEquals(createResponseTimeTupleMeasurement(Measure.valueOf(0d, SI.SECOND)));
    }

    @Test
    public void testOnSlidingWindowFull() {
        addResponseTimeTupleMeasurements();
        this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
        assertLastRecordedMeasurementEquals(this.exepectedAggregatedResponseTimeMeasuringValueFirstTest);
    }

    @Test
    public void testOnSlidingWindowFullOddNumber() {
        addResponseTimeTupleMeasurementsOddNumber();
        this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
        assertLastRecordedMeasurementEquals(this.exepectedAggregatedResponseTimeMeasuringValueSecondTest);
    }

    @Test
    public final void testGetExpectedWindowDataMetric() {
        assertEquals(RESULT_METRIC, this.aggregatorUnderTest.getExpectedWindowDataMetric());
    }

}
