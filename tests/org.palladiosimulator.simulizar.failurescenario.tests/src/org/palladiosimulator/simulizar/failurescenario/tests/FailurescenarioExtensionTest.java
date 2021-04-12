package org.palladiosimulator.simulizar.failurescenario.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.getMeasurementOfAt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.simulizar.failurescenario.di.DaggerFailurescenarioExtensionComponent;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.Named;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class FailurescenarioExtensionTest {
	private final static double EPSILON = 0.00001;

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testHwCrash.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testHwCrash(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// HW Crash at t = 300
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> Missing Processing Time of 0.0-3.0sec
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(8.0, 8.0, getMeasuredValuesOfInterval(300.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testSwCrash.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testSwCrash(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// SW Crash at t = 300
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> Missing Processing Time of 0.0-3.0sec
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(8.0, 8.0, getMeasuredValuesOfInterval(300.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testLinkCrash.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testLinkCrash(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Link Crash at t = 300
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> Missing Processing Time of 0.0-3.0sec
		// and Missing Transmission Time of 8.0sec
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(0.0, 0.0, getMeasuredValuesOfInterval(350.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testHwTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testHwTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// HW Timing at t = 300, delay +3.0, demand scaling *2
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000,
		// after t = 300 --> y = (transmission) + 3.0 + (processing) * 2
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(11.0, 17.0, getMeasuredValuesOfInterval(300.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testSwTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testSwTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// SW Timing at t = 300, delay +3.0
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> y = (transmission) + 3.0 + (processing)
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(11.0, 14.0, getMeasuredValuesOfInterval(300.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testLinkTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testLinkTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Link Timing at t = 300, delay +3.0, demand scaling *2
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> y = (transmission) * 2 + 6.0[latencyCall & latencyReturn]
		// + (processing)
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(22.0, 25.0, getMeasuredValuesOfInterval(350.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testMultipleTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testMultipleTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// HW Timing at t = 300, delay +3.0, demand scaling *2
		// SW Timing at t = 300, delay +3.0
		// Link Timing at t = 300, delay +3.0, demand scaling *2
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// after t = 300 --> y =
		// (transmission) * 2 + 6.0 + (processing) * 2 + 3.0[HW delay] + 3.0[SW delay] =
		// {28, 34.0}
		// ---------------------------------------------------------------------------------------------
		// Same 3 Failures are added at t = 800 again
		// after t = 800 --> y =
		// (transmission) * 4 + 12.0 + (processing) * 4 + 6.0[HW delay] + 6.0[SW delay]
		// {56, 68.0}
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(28.0, 34.0, getMeasuredValuesOfInterval(350.0, 800.0, dataOfQuery)));
		assertTrue(allBetween(56.0, 68.0, getMeasuredValuesOfInterval(850.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testMultipleTransientCrashesOneAfterAnother.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testMultipleTransientCrashesOneAfterAnother(@Named("callQuery") ExternalCallAction callQueryAction,
			ExperimentRun expRun) throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient HW Crash at t = 300 -> t = 600
		// Transient SW Crash at t = 700 -> t = 1000
		// Transient Link Crash at t = 1100 -> t = 1400
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 600} --> Missing Processing Time of 0.0-3.0sec
		// t = {700, 1000} --> "
		// t = {1100, 1400} --> " and Missing Transmission Time of 8.0sec
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(8.0, 8.0, getMeasuredValuesOfInterval(300.0, 600.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(600.0, 700.0, dataOfQuery)));
		assertTrue(allBetween(8.0, 8.0, getMeasuredValuesOfInterval(700.0, 1000.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(1000.0, 1100.0, dataOfQuery)));
		assertTrue(allBetween(0.0, 0.0, getMeasuredValuesOfInterval(1150.0, 1400.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(1450.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testMultipleTransientTimingOneAfterAnother.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testMultipleTransientTimingOneAfterAnother(@Named("callQuery") ExternalCallAction callQueryAction,
			ExperimentRun expRun) throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient HW Timing at t = 300 -> t = 600
		// Transient SW Timing at t = 700 -> t = 1000
		// Transient Link Timing at t = 1100 -> t = 1400
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 600} --> y = (transmission) + 3.0 + (processing) * 2
		// t = {700, 1000} --> y = (transmission) + 3.0 + (processing)
		// t = {1100, 1400} --> y = (transmission) * 2 + 6.0 + (processing)
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));
		assertTrue(allBetween(11.0, 17.0, getMeasuredValuesOfInterval(300.0, 600.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(650.0, 700.0, dataOfQuery)));
		assertTrue(allBetween(11.0, 14.0, getMeasuredValuesOfInterval(700.0, 1000.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(1050.0, 1100.0, dataOfQuery)));
		assertTrue(allBetween(22.0, 25.0, getMeasuredValuesOfInterval(1150.0, 1400.0, dataOfQuery)));
		assertTrue(normal(getMeasuredValuesOfInterval(1450.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testLinkTransientByzantineCrash.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testLinkTransientByzantineCrash(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient Byzantine Link Crash at t = 300 -> t = 1300
		// with a CrashProbability of 50%
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 1300} --> y = 0.0 or normal
		// --> check if at least one 0.0-Value and one normal-Value exists in interval
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));

		assertTrue(atLeastOneBetween(0.0, 0.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));

		assertTrue(normal(getMeasuredValuesOfInterval(1350.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testHwTransientByzantineTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testHwTransientByzantineTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient Byzantine HW Timing at t = 300 -> t = 1300
		// with a CrashProbability of 50%
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 1300} --> y = {11.0, 17.0} or normal
		// --> check if at least one scaled-Value and one normal-Value exists in
		// interval
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));

		assertTrue(atLeastOneBetween(11.0, 17.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));

		assertTrue(normal(getMeasuredValuesOfInterval(1350.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testSwTransientByzantineTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testSwTransientByzantineTiming(@Named("callQuery") ExternalCallAction callQueryAction, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient Byzantine SW Timing at t = 300 -> t = 1300
		// with a CrashProbability of 50%
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 1300} --> y = {11.0, 14.0} or normal
		// --> check if at least one scaled-Value and one normal-Value exists in
		// interval
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));

		assertTrue(atLeastOneBetween(11.0, 14.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(300.0, 1300.0, dataOfQuery)));

		assertTrue(normal(getMeasuredValuesOfInterval(1350.0, 2100.0, dataOfQuery)));
	}

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/dbQueryExample", modelFiles = {
			"fstest.allocation", "fstest.usagemodel", "testMultipleSwTransientByzantineTiming.failurescenario" })
	@UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
	@SimulationConfig(simulateLinkThroughput = true)
	@RunSimuLizar
	void testMultipleSwTransientByzantineTiming(@Named("callQuery") ExternalCallAction callQueryAction,
			ExperimentRun expRun) throws JobFailedException, UserCanceledException {

		var ResponseTimeMeasurementOfQuery = getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callQueryAction);
		assertTrue(ResponseTimeMeasurementOfQuery.isPresent());
		List<Double[]> dataOfQuery = parseMeasurement(ResponseTimeMeasurementOfQuery);

		// Transient Byzantine SW Timing at t = 300 -> t = 1300
		// with a CrashProbability of 50%
		// Same failure is added at at t = 700 -> t = 1700 again
		// ---------------------------------------------------------------------------------------------
		// simulation time 0 -> 2000
		// t = {300, 700} --> y = {11.0, 14.0} or normal
		// t = {700, 1300} --> y = {11.0, 14.0} = scaled two times, or one time, or normal
		// t = {1300, 1700} --> y = {11.0, 14.0} or normal
		// --> check if at least one scaled-Value and one normal-Value exists in
		// interval
		// ---------------------------------------------------------------------------------------------
		assertTrue(normal(getMeasuredValuesOfInterval(0.0, 300.0, dataOfQuery)));

		assertTrue(atLeastOneBetween(11.0, 14.0, getMeasuredValuesOfInterval(300.0, 700.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(300.0, 700.0, dataOfQuery)));
		
		assertTrue(atLeastOneBetween(14.0, 17.0, getMeasuredValuesOfInterval(700.0, 1300.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(11.0, 14.0, getMeasuredValuesOfInterval(700.0, 1300.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(700.0, 1300.0, dataOfQuery)));
		
		assertTrue(atLeastOneBetween(11.0, 14.0, getMeasuredValuesOfInterval(1350.0, 1700.0, dataOfQuery)));
		assertTrue(atLeastOneBetween(8.0, 11.0, getMeasuredValuesOfInterval(1350.0, 1700.0, dataOfQuery)));

		assertTrue(normal(getMeasuredValuesOfInterval(1750.0, 2100.0, dataOfQuery)));
	}
	
	/**
	 * Checks if all values of the interval are normal.
	 * (Without failure scaling: between 8.0 and 11.0)
	 * 
	 * @param measuredValuesOfInterval
	 * @return
	 */
	private boolean normal(List<Double> measuredValuesOfInterval) {
		return allBetween(8.0, 11.0, measuredValuesOfInterval);
	}

	/**
	 * Returns the y values of all x, where xMin <= x(+-epsilon) <= xMax.
	 * 
	 * @param xMin   Lower bound
	 * @param xMax   Upper bound
	 * @param source ArrayList of measuring tuples
	 * @return List of the y values
	 */
	private List<Double> getMeasuredValuesOfInterval(double xMin, double xMax, List<Double[]> source) {
		List<Double> result = new ArrayList<>();
		for (int i = 0; i < source.size(); i++) {
			if (source.get(i)[0] >= xMin - EPSILON && source.get(i)[0] <= xMax + EPSILON) {
				result.add(source.get(i)[1]);
			}
		}
		return result;
	}

	/**
	 * Checks if yMin <= y(+-epsilon) <= yMax for all y.
	 * 
	 * @param yMin   Lower bound
	 * @param yMax   Upper bound
	 * @param source ArrayList of values
	 * @return true if all values are between
	 */
	private boolean allBetween(double yMin, double yMax, List<Double> source) {
		boolean allBetween = true;
		for (Double y : source) {
			allBetween = allBetween && (y >= yMin - EPSILON && y <= yMax + EPSILON);
		}
		return allBetween;
	}

	/**
	 * Checks if yMin <= y(+-epsilon) <= yMax for at least one y.
	 * 
	 * @param yMin   Lower bound
	 * @param yMax   Upper bound
	 * @param source ArrayList of values
	 * @return true if at least one value is between
	 */
	private boolean atLeastOneBetween(double yMin, double yMax, List<Double> source) {
		boolean atLeastOneBetween = false;
		for (Double y : source) {
			atLeastOneBetween = atLeastOneBetween || (y >= yMin - EPSILON && y <= yMax + EPSILON);
		}
		return atLeastOneBetween;
	}

	/**
	 * Parses a Measurement to an ArrayList of measuring tuples.
	 * 
	 * @param m The Optional of Measurement
	 * @return ArrayList of measuring tuples
	 */
	private List<Double[]> parseMeasurement(Optional<Measurement> m) {
		List<Measure<?, Duration>> yVars = MeasurementTestUtils.allMeasurementsOfMetric(m.get(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC);
		List<Measure<?, Duration>> xVars = MeasurementTestUtils.allMeasurementsOfMetric(m.get(),
				MetricDescriptionConstants.POINT_IN_TIME_METRIC);
		List<Double[]> result = new ArrayList<>();
		for (int i = 0; i < yVars.size(); i++) {
			double x = xVars.get(i).doubleValue(SI.SECOND);
			double y = yVars.get(i).doubleValue(SI.SECOND);
			result.add(new Double[] { x, y });
		}

		return result;
	}
}
