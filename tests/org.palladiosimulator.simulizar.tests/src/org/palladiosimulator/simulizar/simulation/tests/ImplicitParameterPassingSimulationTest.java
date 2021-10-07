package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class ImplicitParameterPassingSimulationTest {
	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/implicit-parameters", modelFiles = {
			"default.allocation", "default.repository", "My.resourceenvironment", "default.system", "default.usagemodel" })
	@SimulationConfig(maxMeasurements = "100")
	@RunSimuLizar
	void testImplicitParameterPassingSimulation(UsageScenario scenario, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {
		var metric = MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE;

		var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(), metric, scenario);
		
		assertTrue(measurement.isPresent());
		
		assertThat(MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC), not(empty()));

		MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND, is(equalTo(11.0)));

	}
}
