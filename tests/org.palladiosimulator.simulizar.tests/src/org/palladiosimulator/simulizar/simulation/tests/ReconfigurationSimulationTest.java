package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.asDoubleIn;

import jakarta.measure.quantity.Duration;
import jakarta.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SetConfigProperty;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;
import org.palladiosimulator.simulizar.reconfiguration.qvto.DaggerQVTOReconfigurationComponent;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class ReconfigurationSimulationTest {

    /**
     * Tests fix of https://palladio-simulator.atlassian.net/browse/SIMULIZAR-169
     * 
     * The reconfiguration increments the resource demand of the internal action after each user by
     * 1.
     * 
     * The resulting response time of the usage scenario should be 1 for the first user, 2.0 for the
     * second one, 3 for the 3rd one, and so on.
     * 
     * If the test fails with each scenario taking 1.0 time units, the reconfiguration is not
     * executed.
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/reconfiguration", modelFiles = {
            "default.allocation", "default.usagemodel", "default.monitorrepository" })
    @UseSimuLizarExtension(DaggerQVTOReconfigurationComponent.class)
    @SetConfigProperty(id = SimulizarConstants.RECONFIGURATION_RULES_FOLDER, value = "platform:/plugin/org.palladiosimulator.simulizar.tests/testmodels/reconfiguration/reconfigs")
    @SimulationConfig(maxMeasurements = "10")
    @RunSimuLizar
    void testReconfiguratedModelIsPickedUpByNewUsers(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {

        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        var values = MeasurementTestUtils.<Duration> allMeasurementsOfMetric(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        var counter = 1;
        for (var val : values) {
            assertThat(val, asDoubleIn(SI.SECOND, is(closeTo(counter++, 0.0001))));
        }

    }
}
