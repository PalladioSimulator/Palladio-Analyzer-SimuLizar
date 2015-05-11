package org.palladiosimulator.experimentanalysis.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Deque;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.ISlidingWindowListener;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.measurementframework.BasicMeasurement;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

public abstract class SlidingWindowTest {

    protected SlidingWindow slidingWindowUnderTest;
    protected Measure<Double, Duration> windowLength;
    protected Measure<Double, Duration> currentLowerBound;
    protected Measure<Double, Duration> increment;
    protected MeasuringValue measurement;
    protected MeasuringValue wrongMetricMeasurement;
    protected Measure<Double, Duration> pointInTimeMeasure;
    protected Measure<Long, Dimensionless> stateMeasure;
    protected ISlidingWindowMoveOnStrategy dummyStrategy;
    protected ISlidingWindowListener windowListener;
    protected ISlidingWindowListener windowListenerWrongMetric;

    protected static final MetricSetDescription metricDescription = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
    protected static final BaseMetricDescription wrongMetricDescription = MetricDescriptionConstants.POINT_IN_TIME_METRIC;

    @Before
    public void setUp() throws Exception {

        this.windowLength = Measure.valueOf(10d, SI.SECOND);
        this.currentLowerBound = Measure.valueOf(0d, SI.SECOND);
        this.increment = Measure.valueOf(5d, SI.SECOND);
        this.pointInTimeMeasure = Measure.valueOf(1d, SI.SECOND);
        this.stateMeasure = Measure.valueOf(42L, Unit.ONE);
        this.measurement = new TupleMeasurement(metricDescription, this.pointInTimeMeasure, this.stateMeasure);

        this.wrongMetricMeasurement = new BasicMeasurement<Double, Duration>(this.pointInTimeMeasure,
                wrongMetricDescription);
        this.dummyStrategy = new DummyMoveOnStrategy();
        this.windowListener = new DummySlidingWindowListener(metricDescription);
        this.windowListenerWrongMetric = new DummySlidingWindowListener(wrongMetricDescription);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeasurementWrongMetric() {
        this.slidingWindowUnderTest.addMeasurement(wrongMetricMeasurement);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMeasurementNull() {
        this.slidingWindowUnderTest.addMeasurement(null);
    }

    @Test
    public void testFlush() {
        this.slidingWindowUnderTest.addMeasurement(measurement);
        assertFalse(this.slidingWindowUnderTest.isEmpty());
        this.slidingWindowUnderTest.flush();
        assertTrue(this.slidingWindowUnderTest.isEmpty());
        assertEquals(0, this.slidingWindowUnderTest.getNumberOfElements());
    }

    @Test
    public void testGetCurrentUpperBound() {
        Measure<Double, Duration> expected = Measure.valueOf(
                this.currentLowerBound.getValue() + this.windowLength.getValue(), SI.SECOND);
        assertEquals(expected, this.slidingWindowUnderTest.getCurrentUpperBound());
    }

    @Test
    public void testGetEffectiveWindowLength() {
        assertEquals(this.windowLength, this.slidingWindowUnderTest.getEffectiveWindowLength());
    }

    @Test
    public void testGetIncrement() {
        assertEquals(this.increment, this.slidingWindowUnderTest.getIncrement());
    }

    @Test
    public void testGetAcceptedMetric() {
        assertEquals(metricDescription, this.slidingWindowUnderTest.getAcceptedMetric());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(this.slidingWindowUnderTest.isEmpty());
        this.slidingWindowUnderTest.addMeasurement(this.measurement);
        assertFalse(this.slidingWindowUnderTest.isEmpty());
    }

    @Test
    public void testGetNumberOfElements() {
        assertEquals(0, this.slidingWindowUnderTest.getNumberOfElements());
        this.slidingWindowUnderTest.addMeasurement(measurement);
        assertEquals(1, this.slidingWindowUnderTest.getNumberOfElements());
    }

    @Test
    public void testAddObserver() {
        this.slidingWindowUnderTest.addObserver(this.windowListener);
        assertEquals(1, this.slidingWindowUnderTest.getAttachedObservers().size());
        assertTrue(this.slidingWindowUnderTest.getAttachedObservers().contains(this.windowListener));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddObserverNull() {
        this.slidingWindowUnderTest.addObserver(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddObserverWrongMetric() {
        this.slidingWindowUnderTest.addObserver(this.windowListenerWrongMetric);
    }

    @Test
    public void testRemoveObserver() {
        this.slidingWindowUnderTest.addObserver(this.windowListener);
        this.slidingWindowUnderTest.removeObserver(this.windowListener);
        assertEquals(0, this.slidingWindowUnderTest.getAttachedObservers().size());
    }

    @Test
    public void testMoveOn() {
        // mock window full/move on event
        SlidingWindowOnWindowFullEventMock window = new SlidingWindowOnWindowFullEventMock();
        window.mockOnWindowFullEvent();
        // assert that the window bounds have been adjusted (moved forward) correctly
        Measure<Double, Duration> expectedNewUpperBound = Measure.valueOf(this.currentLowerBound.getValue()
                + this.increment.getValue() + this.windowLength.getValue(), SI.SECOND);
        assertEquals(expectedNewUpperBound, window.getCurrentUpperBound());
    }

    private final class SlidingWindowOnWindowFullEventMock extends SlidingWindow {

        public SlidingWindowOnWindowFullEventMock() {
            super(windowLength, increment, metricDescription, dummyStrategy);
            // TODO Auto-generated constructor stub
        }

        public void mockOnWindowFullEvent() {
            super.onWindowFullEvent();
        }

    }

    private static final class DummySlidingWindowListener implements ISlidingWindowListener {

        private final MetricDescription expectedWindowDataMetric;

        private DummySlidingWindowListener(MetricDescription expectedWindowDataMetric) {
            this.expectedWindowDataMetric = expectedWindowDataMetric;
        }

        @Override
        public void onSlidingWindowFull(Iterable<MeasuringValue> windowData, Measure<Double, Duration> windowLeftBound,
                Measure<Double, Duration> windowLength) {
            // dummy, do nothing here

        }

        @Override
        public MetricDescription getExpectedWindowDataMetric() {
            return this.expectedWindowDataMetric;
        }

    }

    static final class DummyMoveOnStrategy implements ISlidingWindowMoveOnStrategy {
        @Override
        public void adjustData(Deque<MeasuringValue> currentData, Measure<Double, Duration> newLowerBound,
                Measure<Double, Duration> increment) {
            // dummy
        }
    }
}
