package org.palladiosimulator.experimentanalysis.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.measure.Measure;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.measurementframework.TupleMeasurement;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;

public class SlidingWindowUtilizationAggregatorTest extends SlidingWindowAggregatorTest {

	private MeasuringValue emptyUtilizationMeasurement;
	private MeasuringValue expectedNotEmptyUtilizationMeasurement;
	private MetricSetDescription expectedWindowDataMetric;
	private Measure<Long, Dimensionless> idleStateMeasure;
	private Measure<Long, Dimensionless> busyStateMeasure;
	//constant that denotes  the maximum delta between double values for which both numbers are still considered equal
	private static final double DELTA = Math.pow(10, -12);
	
	@Override
    @Before
	public void setUp() throws Exception {
		super.setUp();
		this.aggregatorUnderTest = new SlidingWindowUtilizationAggregator(this.dummyRecorder);
		this.emptyUtilizationMeasurement = createEmptyUtilizationTupleMeasurement();
		this.expectedNotEmptyUtilizationMeasurement = createUtilizationTupleMeasurement(0.7d);
		this.expectedWindowDataMetric = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;
		this.idleStateMeasure = Measure.valueOf(0L, Unit.ONE);
		this.busyStateMeasure = Measure.valueOf(42L, Unit.ONE);
	}

	@Override
    @After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	private MeasuringValue createUtilizationTupleMeasurement(
			double utilization) {

		Measure<Double, Dimensionless> resultUtilizationMeasure = Measure
				.valueOf(utilization, Unit.ONE);
		Measure<Double, Duration> resultPointInTimeMeasure = Measure.valueOf(this.currentLowerBound.getValue() + this.windowLength.getValue(), SI.SECOND);
		return new TupleMeasurement(
				MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE,
				resultPointInTimeMeasure, resultUtilizationMeasure);
	}

	private MeasuringValue createEmptyUtilizationTupleMeasurement() {

		return createUtilizationTupleMeasurement(0d);
	}
	
	private void addStateOfActiveResourceTupleMeasurementsNoUtilization() {
		//window position: [0-10]
		
		Measure<Double, Duration> pointInTimeMeasure = Measure.valueOf(0d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.idleStateMeasure));
		
		
		pointInTimeMeasure = Measure.valueOf(3d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.idleStateMeasure));
		
		pointInTimeMeasure = Measure.valueOf(6d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.idleStateMeasure));
		
		//this setup should yield a utilization of 0 
		//[0-3], [4-6], [7-10]: 10s idleness
	}
	
	private void addStateOfActiveResourceTupleMeasurementsUtilizationSameDurationUnits() {
		//window position: [0-10]
		
		//start with utilization
		Measure<Double, Duration> pointInTimeMeasure = Measure.valueOf(0d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.busyStateMeasure));
		
		//no utilization next
		pointInTimeMeasure = Measure.valueOf(3d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.idleStateMeasure));
		
		//again some processes active
		pointInTimeMeasure = Measure.valueOf(6d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.busyStateMeasure));
		
		//this setup should yield a utilization of 0.70 (70%)
		//[0-2], [6-10]: 7s activity
		//[3-5]: 3s idleness
	}
	
	private void addStateOfActiveResourceTupleMeasurementsUtilizationDifferentDurationUnits() {
		//window position: [0-10]
		
		//start with utilization
		Measure<Double, Duration> pointInTimeMeasure = Measure.valueOf(0d, SI.SECOND);
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.busyStateMeasure));
		
		//no utilization next
		pointInTimeMeasure = Measure.valueOf(3000d, SI.MILLI(SI.SECOND)); //3000ms = 3s
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.idleStateMeasure));
		
		//again some processes active
		pointInTimeMeasure = Measure.valueOf(600d, SI.CENTI(SI.SECOND)); //600 cs = 3s
		this.data.addLast(new TupleMeasurement(this.expectedWindowDataMetric, pointInTimeMeasure, this.busyStateMeasure));
		
		//this setup should yield a utilization of 0.70 (70%)
		//[0-2], [6-10]: 7s activity
		//[3-5]: 3s idleness
	}
	
	@Test
	public void testGetExpectedWindowDataMetric() {
		assertEquals(this.expectedWindowDataMetric, this.aggregatorUnderTest.getExpectedWindowDataMetric());
	}

	@Test
	public void testOnSlidingWindowFullEmptyData() {
		this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
		assertLastRecordedMeasurementEquals(this.emptyUtilizationMeasurement);
	}

	@Test
	public void testOnSlidingWindowFullNoUtilization() {
		addStateOfActiveResourceTupleMeasurementsNoUtilization();
		this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
		assertLastRecordedMeasurementEquals(this.emptyUtilizationMeasurement);
	}
	
	@Test
	public void testOnSlidingWindowFullUtilizationSameDurationUnits() {
		addStateOfActiveResourceTupleMeasurementsUtilizationSameDurationUnits();
		this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
		assertLastRecordedMeasurementEquals(this.expectedNotEmptyUtilizationMeasurement);
	}
	
	@Test
	public void testOnSlidingWindowFullUtilizationDifferentTimeUnits() {
		addStateOfActiveResourceTupleMeasurementsUtilizationDifferentDurationUnits();
		this.aggregatorUnderTest.onSlidingWindowFull(this.data, this.currentLowerBound, this.windowLength);
		assertLastRecordedMeasurementEquals(this.expectedNotEmptyUtilizationMeasurement);
	}
	
	private void assertLastRecordedMeasurementEquals(MeasuringValue expected) {
	    MeasuringValue lastMeasurement = this.dummyRecorder.getLastMeasurement();
		assertNotNull(lastMeasurement);
		assertMeasurementsEqual(expected, lastMeasurement);
	}
	
	private static void assertMeasurementsEqual(MeasuringValue expected, MeasuringValue actual) {
		Measure<Double, Dimensionless> expectedUtilization = expected.getMeasureForMetric(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE);
		Measure<Double, Dimensionless> actualUtilization = actual.getMeasureForMetric(MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE);
		Measure<Double, Duration> expectedPointInTime = expected.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
		Measure<Double, Duration> actualPointInTime = actual.getMeasureForMetric(MetricDescriptionConstants.POINT_IN_TIME_METRIC);
		
		assertEquals(expectedUtilization.getValue(), actualUtilization.getValue(), DELTA);
		assertEquals(expectedPointInTime.getValue(), actualPointInTime.getValue(), DELTA);
		
	}
	
}
