package org.palladiosimulator.simulizar.simulation.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.closeTo;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.*;

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
class SimuLizar154Test {

    /**
     * Tests fix of https://palladio-simulator.atlassian.net/browse/SIMULIZAR-154
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/simulizar154", modelFiles = {
            "default.allocation", "default.usagemodel", "default.monitorrepository" })
    @RunSimuLizar
    void testSimulizar154Fix(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        // The simulated scenario starts runs a closed workload with one user. The user first issues
        // a demand of 1 Units to the CPU with a processing rate of 2. Hence,
        // it takes 0.5 time units to finish. Thereafter a async thread is spun of, in which again a
        // demand of 1 is issued.
        // Result: We expect the first user to finish within 0.5s. Due to the processing of
        // the asynchronously issues demand, all of the remaining users only receive 50% of the cpu
        // processing capability. Cosequently, the remaining users take 1.0s to finish.
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                firstMeasurementMatchesDifferentlyThanTheRest(closeTo(0.5, 0.001), closeTo(1.0, 0.001)));
    }

}
