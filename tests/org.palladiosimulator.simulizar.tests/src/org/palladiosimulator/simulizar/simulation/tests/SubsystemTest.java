package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class SubsystemTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/Subsystem_Test", modelFiles = {
            "nestedSubsystems.allocation", "nestedSubsystems.monitorrepository", "nestedSubSystem.usagemodel" })
    @RunSimuLizar
    void testNestedSubsystemAllocation(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(anyOf(closeTo(0.1, 0.0001), closeTo(0.2, 0.0001))));

    }
    

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/Subsystem_Test", modelFiles = {
            "default.allocation", "default.monitorrepository", "default.usagemodel" })
    @RunSimuLizar
    void testDefault(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(anyOf(closeTo(0.1, 0.0001), closeTo(0.2, 0.0001))));

    }

}
