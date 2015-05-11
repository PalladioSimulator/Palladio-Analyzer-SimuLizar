package org.palladiosimulator.experimentanalysis.tests;

import static org.junit.Assert.assertEquals;

import java.util.Deque;
import java.util.LinkedList;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

public class KeepLastElementPriorToLowerBoundStrategyTest {

    private Deque<MeasuringValue> data;
    private KeepLastElementPriorToLowerBoundStrategy strategyUnderTest;
    private Measure<Double, Duration> currentLowerBound;
    private Measure<Double, Duration> increment;
    private MeasuringValue expectedMeasurement;
    private final MetricSetDescription metricDescription = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

    @Before
    public void setUp() throws Exception {
        this.strategyUnderTest = new KeepLastElementPriorToLowerBoundStrategy();
        this.data = new LinkedList<>();

        this.currentLowerBound = Measure.valueOf(0d, SI.SECOND);
        this.increment = Measure.valueOf(5d, SI.SECOND);
        this.expectedMeasurement = new TupleMeasurement(metricDescription, Measure.valueOf(4.75, SI.SECOND),
                Measure.valueOf(42L, Unit.ONE));
    }

    @After
    public void tearDown() {
        this.data.clear();
    }

    private void initiallyFillWindowWithMeasurements() {
        // window position: [0-10]
        Measure<Double, Duration> pointInTimeMeasure;
        Measure<Long, Dimensionless> stateMeasure;
        for (int i = 0; i < 10; ++i) {
            if (i == 4) {
                this.data.addLast(this.expectedMeasurement);
            } else {
                if (i % 2 == 0) {
                    // provide point in time in s
                    pointInTimeMeasure = Measure.valueOf(i + 0.75, SI.SECOND);

                } else {
                    // provide point in time in ms
                    pointInTimeMeasure = Measure.valueOf((i + 0.75) * 1000, SI.MILLI(SI.SECOND));
                }
                stateMeasure = Measure.valueOf(42L, Unit.ONE);
                this.data.addLast(new TupleMeasurement(metricDescription, pointInTimeMeasure, stateMeasure));
            }
        }
        // 10 elements in window: (0.75s, 42), (1.75s, 42), ..., (9.75s, 42)
        // the last 6 of which should be in window after a move on (increment 5s): (4.75s, 42), ...
        // , (9.75s, 42)
    }

    private void initiallyFillWindowWithMeasurementsOneElement() {
        // window position: [0-10]
        this.data.addLast(this.expectedMeasurement);
        // 1 element in window: (4.75s, 42)
        // should be in window kept after move on
    }

    private void initiallyFillWindowWithMeasurementsNoSmallerElementToKeep() {
        // window position: [0-10]
        Measure<Double, Duration> pointInTimeMeasure;
        Measure<Long, Dimensionless> stateMeasure;
        for (int i = 5; i < 10; ++i) {
            pointInTimeMeasure = Measure.valueOf(i + 0.75, SI.SECOND);
            stateMeasure = Measure.valueOf(42L, Unit.ONE);
            this.data.addLast(new TupleMeasurement(metricDescription, pointInTimeMeasure, stateMeasure));
        }
        // 5 elements in window: (5.75s, 42), (6.75s, 42), ..., (9.75s, 42)
        // all of which should be in window after a move on (increment 5s)
    }

    private void mockMoveOn() {
        this.currentLowerBound = Measure.valueOf(this.currentLowerBound.getValue() + this.increment.getValue(),
                SI.SECOND);
        // window position now: [5-15]
    }

    @Test
    public void testAdjustDataOneElementAvailable() {
        initiallyFillWindowWithMeasurementsOneElement();
        mockMoveOn();
        this.strategyUnderTest.adjustData(this.data, this.currentLowerBound, this.increment);
        assertEquals(1, this.data.size());
        assertEquals(this.expectedMeasurement, this.data.peekFirst());
    }

    @Test
    public void testAdjustDataNoDataAvailable() {
        mockMoveOn();
        this.strategyUnderTest.adjustData(this.data, this.currentLowerBound, this.increment);
        assertEquals(0, this.data.size());
    }

    @Test
    public void testAdjustDataNoSmallerElementToKeep() {
        initiallyFillWindowWithMeasurementsNoSmallerElementToKeep();
        mockMoveOn();
        this.strategyUnderTest.adjustData(this.data, this.currentLowerBound, this.increment);
        assertEquals(5, this.data.size());
    }

    @Test
    public void testAdjustData() {
        initiallyFillWindowWithMeasurements();
        mockMoveOn();
        this.strategyUnderTest.adjustData(this.data, this.currentLowerBound, this.increment);
        assertEquals(6, this.data.size());
        assertEquals(this.expectedMeasurement, this.data.peekFirst());
    }

}
