package org.palladiosimulator.simulizar.metrics.aggregators.tests;

import static org.junit.Assert.*;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.tests.SlidingWindowTest;
import org.palladiosimulator.simulizar.metrics.aggregators.SimulationGovernedSlidingWindow;
import org.palladiosimulator.simulizar.metrics.aggregators.tests.utils.SimuComModelMock;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SimulationGovernedSlidingWindowTest extends SlidingWindowTest {

    private SimuComModel model = null;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.model = SimuComModelMock.obtainMockModel();
        this.slidingWindowUnderTest = new SimulationGovernedSlidingWindow(windowLength, increment, metricDescription,
                dummyStrategy, this.model);
    }

    @After
    public void tearDown() throws Exception {
        SimuComModelMock.releaseMockModel();
        this.model = null;
        super.tearDown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoMetricDefined() {
        new SimulationGovernedSlidingWindow(windowLength, increment, null, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoMoveOnStrategyDefined() {
        new SimulationGovernedSlidingWindow(windowLength, increment, metricDescription, null, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoWindowLengthDefined() {
        new SimulationGovernedSlidingWindow(null, increment, metricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedNegativeValue() {
        new SimulationGovernedSlidingWindow(Measure.valueOf(-10d, SI.SECOND), increment, metricDescription,
                dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedNaNValue() {
        new SimulationGovernedSlidingWindow(Measure.valueOf(Double.NaN, SI.SECOND), increment, metricDescription,
                dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedInfiniteValue() {
        new SimulationGovernedSlidingWindow(Measure.valueOf(Double.NEGATIVE_INFINITY, SI.SECOND), increment,
                metricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoIncrementDefined() {
        new SimulationGovernedSlidingWindow(windowLength, null, metricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalIncrementDefined() {
        new SimulationGovernedSlidingWindow(windowLength, Measure.valueOf(-10d, SI.SECOND), metricDescription,
                dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoModelDefined() {
        new SimulationGovernedSlidingWindow(windowLength, Measure.valueOf(-10d, SI.SECOND), metricDescription,
                dummyStrategy, null);
    }

    @Test
    public void testGetCurrentUpperBound() {
        super.testGetCurrentUpperBound();
        // sim time is Double.MAX_VALUE now according to mocked model
        this.model.getSimulationControl().setMaxSimTime(5); // window is longer than simulation now
        // hence, check if upper bound has been adjusted
        Measure<Double, Duration> expected = Measure.valueOf(this.model.getSimulationControl()
                .getCurrentSimulationTime(), SI.SECOND);
        assertEquals(expected, this.slidingWindowUnderTest.getCurrentUpperBound());
    }

    @Test
    public void testGetEffectiveWindowLength() {
        super.testGetEffectiveWindowLength();
        // sim time is Double.MAX_VALUE now according to mocked model
        this.model.getSimulationControl().setMaxSimTime(5); // window is longer than simulation now
        // thus, check if length has been adjusted
        assertTrue(this.slidingWindowUnderTest.getEffectiveWindowLength().compareTo(this.windowLength) < 0);
        assertEquals(this.model.getSimulationControl().getCurrentSimulationTime(), this.slidingWindowUnderTest
                .getEffectiveWindowLength().getValue(), Double.MIN_NORMAL);
    }

    @Test
    public void testMoveOn() {
        super.testMoveOn();
        // mock periodic window full/move on event controlled by model
        ((SimuComModelMock) this.model)
                .triggerMockWindowMoveOn((SimulationGovernedSlidingWindow) this.slidingWindowUnderTest);
        // assert that the window bounds have been adjusted (moved forward) correctly
        Measure<Double, Duration> expectedNewUpperBound = Measure.valueOf(this.currentLowerBound.getValue()
                + this.increment.getValue() + this.windowLength.getValue(), SI.SECOND);
        assertEquals(expectedNewUpperBound, this.slidingWindowUnderTest.getCurrentUpperBound());

        // test with different units
        SimulationGovernedSlidingWindow window = new SimulationGovernedSlidingWindow(Measure.valueOf(1d, SI.SECOND),
                Measure.valueOf(500d, SI.MILLI(SI.SECOND)), metricDescription, this.dummyStrategy, this.model);
        ((SimuComModelMock) this.model).triggerMockWindowMoveOn(window);
        expectedNewUpperBound = Measure.valueOf(1.5d, SI.SECOND);
        assertEquals(expectedNewUpperBound, window.getCurrentUpperBound());
    }
}
