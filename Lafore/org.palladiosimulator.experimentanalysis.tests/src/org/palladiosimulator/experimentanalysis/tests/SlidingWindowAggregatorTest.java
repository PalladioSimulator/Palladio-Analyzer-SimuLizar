package org.palladiosimulator.experimentanalysis.tests;

import java.util.Deque;
import java.util.LinkedList;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.SlidingWindowAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.recorderframework.IRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;

public abstract class SlidingWindowAggregatorTest {

	protected SlidingWindowAggregator aggregatorUnderTest;
	protected StoreLastMeasurementRecorder dummyRecorder;
	protected Deque<MeasuringValue> data;
	protected Measure<Double, Duration> windowLength;
	protected Measure<Double, Duration> currentLowerBound;
	
	
	@Before
	public void setUp() throws Exception {
		this.data = new LinkedList<>();
		this.windowLength = Measure.valueOf(10d, SI.SECOND);
		this.currentLowerBound = Measure.valueOf(0d, SI.SECOND);
		this.dummyRecorder = new StoreLastMeasurementRecorder();
	}
	
	@After
	public void tearDown() throws Exception {
		this.data.clear();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public final void testSlidingWindowAggregatorCtorNullRecorder() {
		new DummySlidingWindowAggregator(null);
	}
	
	private static class DummySlidingWindowAggregator extends SlidingWindowAggregator {
		
		private DummySlidingWindowAggregator(IRecorder recorderToWriteInto) {
			super(recorderToWriteInto);
		}

		@Override
		public MetricDescription getExpectedWindowDataMetric() {
			return null;
		}
		
		@Override
		protected MeasuringValue processWindowData(Iterable<MeasuringValue> windowData,
				Measure<Double, Duration> windowLeftBound,
				Measure<Double, Duration> windowLength) {
			return null;
		}
	}
	
	static class StoreLastMeasurementRecorder implements IRecorder {

		private MeasuringValue lastMeasurement = null;
		
		MeasuringValue getLastMeasurement() {
			return this.lastMeasurement;
		}
		
		@Override
		public void writeData(MeasuringValue measurement) {
			this.lastMeasurement = measurement;
		}
		
		@Override
		public void initialize(IRecorderConfiguration recorderConfiguration) {
		}
		
		@Override
		public void flush() {
		}

        @Override
        public void newMeasurementAvailable(MeasuringValue arg0) {
        }

        @Override
        public void preUnregister() {
        }
	}
}

