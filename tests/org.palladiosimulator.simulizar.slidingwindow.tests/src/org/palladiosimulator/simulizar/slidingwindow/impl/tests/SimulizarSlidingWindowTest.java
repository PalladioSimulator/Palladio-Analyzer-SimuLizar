package org.palladiosimulator.simulizar.slidingwindow.impl.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.palladiosimulator.experimentanalysis.tests.SlidingWindowTest;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;
import org.palladiosimulator.simulizar.slidingwindow.tests.utils.SimuComModelMock;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

public class SimulizarSlidingWindowTest extends SlidingWindowTest {

    private SimuComModel model = null;
    private IResourceTableManager resourceTableManager;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.resourceTableManager = new ResourceTableManager();
        this.model = SimuComModelMock.obtainMockModel(this.tempFolder, resourceTableManager);
        this.slidingWindowUnderTest = new SimulizarSlidingWindow(windowLength, increment, windowMetricDescription,
                dummyStrategy, this.model);
    }

    @Override
    @After
    public void tearDown() throws Exception {
        SimuComModelMock.releaseMockModel();
        this.model = null;
        super.tearDown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoMetricDefined() {
        new SimulizarSlidingWindow(windowLength, increment, null, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoMoveOnStrategyDefined() {
        new SimulizarSlidingWindow(windowLength, increment, windowMetricDescription, null, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoWindowLengthDefined() {
        new SimulizarSlidingWindow(null, increment, windowMetricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedNegativeValue() {
        new SimulizarSlidingWindow(Measure.valueOf(-10d, SI.SECOND), increment, windowMetricDescription, dummyStrategy,
                this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedNaNValue() {
        new SimulizarSlidingWindow(Measure.valueOf(Double.NaN, SI.SECOND), increment, measurementsMetricDescription,
                dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalWindowLengthDefinedInfiniteValue() {
        new SimulizarSlidingWindow(Measure.valueOf(Double.NEGATIVE_INFINITY, SI.SECOND), increment,
                measurementsMetricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoIncrementDefined() {
        new SimulizarSlidingWindow(windowLength, null, measurementsMetricDescription, dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorIllegalIncrementDefined() {
        new SimulizarSlidingWindow(windowLength, Measure.valueOf(-10d, SI.SECOND), measurementsMetricDescription,
                dummyStrategy, this.model);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCtorNoModelDefined() {
        new SimulizarSlidingWindow(windowLength, Measure.valueOf(10d, SI.SECOND), measurementsMetricDescription,
                dummyStrategy, null);
    }

    @Override
    @Test
    public void testGetCurrentUpperBound() {
        super.testGetCurrentUpperBound();
        // sim time is Double.MAX_VALUE now according to mocked model
        this.model.getSimulationControl().setMaxSimTime(5); // window is longer than simulation now
        // hence, check if upper bound has been adjusted
        Measure<Double, Duration> expected = Measure
                .valueOf(this.model.getSimulationControl().getCurrentSimulationTime(), SI.SECOND);
        assertEquals(expected, this.slidingWindowUnderTest.getCurrentUpperBound());
    }

    @Override
    @Test
    public void testGetEffectiveWindowLength() {
        super.testGetEffectiveWindowLength();
        // sim time is Double.MAX_VALUE now according to mocked model
        this.model.getSimulationControl().setMaxSimTime(5); // window is longer than simulation now
        // thus, check if length has been adjusted
        assertTrue(this.slidingWindowUnderTest.getEffectiveWindowLength().compareTo(this.windowLength) < 0);
        assertEquals(this.model.getSimulationControl().getCurrentSimulationTime(),
                this.slidingWindowUnderTest.getEffectiveWindowLength().getValue(), Double.MIN_NORMAL);
    }

    @Override
    @Test
    public void testMoveOn() {
        super.testMoveOn();
        // mock periodic window full/move on event controlled by model
        ((SimuComModelMock) this.model).triggerMockWindowMoveOn((SimulizarSlidingWindow) this.slidingWindowUnderTest);
        // assert that the window bounds have been adjusted (moved forward) correctly
        Measure<Double, Duration> expectedNewUpperBound = Measure.valueOf(
                this.currentLowerBound.getValue() + this.increment.getValue() + this.windowLength.getValue(),
                SI.SECOND);
        assertEquals(expectedNewUpperBound, this.slidingWindowUnderTest.getCurrentUpperBound());

        // test with different units
        SimulizarSlidingWindow window = new SimulizarSlidingWindow(Measure.valueOf(1d, SI.SECOND),
                Measure.valueOf(500d, SI.MILLI(SI.SECOND)), measurementsMetricDescription, this.dummyStrategy,
                this.model);
        ((SimuComModelMock) this.model).triggerMockWindowMoveOn(window);
        expectedNewUpperBound = Measure.valueOf(1.5d, SI.SECOND);
        assertEquals(expectedNewUpperBound, window.getCurrentUpperBound());
    }
}
