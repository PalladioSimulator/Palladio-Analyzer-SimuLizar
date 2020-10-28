package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
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
class LinkingResourceSimulationTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/LinkingResource_Test", modelFiles = {
            "default.allocation", "default.measuringpoint", "default.monitorrepository", "default.repository",
            "default.resourceenvironment", "default.slo", "default.system", "default.usagemodel" })
    @SimulationConfig(simulateLinkThroughput = true)
    @RunSimuLizar
    void testLinkingResourceThroughputSimulation(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(allOf(greaterThan(184.0), lessThan(185.0))));

    }
    
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/LinkingResource_Test", modelFiles = {
            "default.allocation", "default.measuringpoint", "default.monitorrepository", "default.repository",
            "default.resourceenvironment", "default.slo", "default.system", "default.usagemodel" })
    @SimulationConfig(simulateLinkThroughput = false)
    @RunSimuLizar
    void testNoLinkingResourceSimulation(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(allOf(greaterThan(1.0), lessThan(2.0))));

    }

}
