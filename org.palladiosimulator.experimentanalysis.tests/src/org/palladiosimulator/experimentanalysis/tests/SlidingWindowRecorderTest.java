package org.palladiosimulator.experimentanalysis.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.ISlidingWindowListener;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

public class SlidingWindowRecorderTest {

	private SlidingWindowRecorder recorderUnderTest;
	private SlidingWindow window;
	private Measure<Double, Duration> windowLength;
	private MeasuringValue measurement;
	private ISlidingWindowListener windowListener;
	
	@Before
	public void setUp() throws Exception {
		this.windowLength = Measure.valueOf(10d, SI.SECOND);
				
		this.measurement = new TupleMeasurement(MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, 
				Measure.valueOf(3.5d, SI.SECOND), Measure.valueOf(1337L, Unit.ONE));
		this.window = new SlidingWindowMock(this.windowLength, MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, 
				new SlidingWindowTest.DummyMoveOnStrategy());
				
		this.windowListener = new SlidingWindowUtilizationAggregator(new SlidingWindowAggregatorTest.StoreLastMeasurementRecorder());
		this.recorderUnderTest = new SlidingWindowRecorder(this.window, this.windowListener);
	}

	@After
	public void tearDown() throws Exception {
			}

	@Test(expected=IllegalArgumentException.class)
	public void testSlidingWindowRecorderCtorNoWindow() {
		new SlidingWindowRecorder(null, this.windowListener);
	}

	@Test
	public void testWriteData() {
		assertTrue(this.window.isEmpty());
		this.recorderUnderTest.writeData(measurement);
		assertFalse(this.window.isEmpty());
		assertEquals(1, this.window.getNumberOfElements());
	}

	@Test
	public void testFlush() {
		this.recorderUnderTest.writeData(measurement);
		this.recorderUnderTest.flush();
		assertTrue(this.window.isEmpty());
	}
	
	private static final class SlidingWindowMock extends SlidingWindow {

	    public SlidingWindowMock(Measure<Double, Duration> windowLength, MetricDescription acceptedMetrics, ISlidingWindowMoveOnStrategy moveOnStrategy) {
            super(windowLength, acceptedMetrics, moveOnStrategy);
            // TODO Auto-generated constructor stub
        }
	    
        public SlidingWindowMock(Measure<Double, Duration> windowLength, Measure<Double, Duration> increment,
                MetricDescription acceptedMetrics, ISlidingWindowMoveOnStrategy moveOnStrategy) {
            super(windowLength, increment, acceptedMetrics, moveOnStrategy);
            // TODO Auto-generated constructor stub
        }
	}
}
