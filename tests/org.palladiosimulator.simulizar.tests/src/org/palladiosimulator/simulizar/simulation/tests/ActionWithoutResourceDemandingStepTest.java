package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.Matchers.closeTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.action.di.DaggerActionRuntimeComponent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.reconfiguration.qvto.DaggerQVTOReconfigurationComponent;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SetConfigProperty;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class ActionWithoutResourceDemandingStepTest {

	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/actionTestWithoutResourceDemand", modelFiles = {
			"default.allocation", "default.usagemodel", "default.repository", "defaultMonitor.monitorrepository",
			"org.palladiosimulator.simulizar.action.repository/model/repository.actions" })
	@UseSimuLizarExtension(DaggerQVTOReconfigurationComponent.class)
	@SimulationConfig(maxMeasurements = "100")
	@SetConfigProperty(id = SimulizarConstants.RECONFIGURATION_RULES_FOLDER, value = "platform:/plugin/org.palladiosimulator.simulizar.tests/testmodels/actionTestWithoutResourceDemand/reconfigurations")
	@RunSimuLizar
	@UseSimuLizarExtension(DaggerActionRuntimeComponent.class)
	void testPopulationScaleAction(UsageScenario scenario, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {
		var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
		assertTrue(measurement.isPresent());

		List<Measure<?, Duration>> responseTimeMeasurements = MeasurementTestUtils
				.allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
		List<Measure<?, Duration>> timeMeasurements = MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(),
				MetricDescriptionConstants.POINT_IN_TIME_METRIC);
		assertEquals(responseTimeMeasurements.size(), timeMeasurements.size());

		double expectedMeasurementTime = 1.0;

		for (int i = 0; i < 100; i++) {
			if (i < 20) {
				assertEquals(expectedMeasurementTime, timeMeasurements.get(i).doubleValue(SI.SECOND), 0.001);
				expectedMeasurementTime += 100.0;
			} else {
				assertEquals(expectedMeasurementTime, timeMeasurements.get(i).doubleValue(SI.SECOND), 0.001);
				expectedMeasurementTime += 1.0;
				assertEquals(expectedMeasurementTime, timeMeasurements.get(i + 1).doubleValue(SI.SECOND), 0.001);
				expectedMeasurementTime += 99.0;
				i++;
			}
		}
		// Check whether all the response time stay at 1.0
		MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND, closeTo(1.0, 0.001));
	}

}
