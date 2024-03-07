package org.palladiosimulator.simulizar.simulation.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.*;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.allMeasurementsOfMetric;

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
class BaseMediaStoreSimulationTest {

    /**
     * Tests simulation of case: https://palladio-simulator.atlassian.net/browse/SIMULIZAR-110
     * Tests fix of https://palladio-simulator.atlassian.net/browse/SIMULIZAR-166
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/MediaStore3_Model", modelFiles = {
            "ms_cache.allocation", "ms_cache_usage_all.usagemodel" })
    @SimulationConfig(maxMeasurements = "1000", simulateReliability = false)
    @RunSimuLizar
    void testMSCacheAll1k(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        assertThat(allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC),
                match(withProbability(lessThan(0.1), asDoubleIn(SI.SECOND, is(anyOf(lessThan(14000.0), greaterThan(32000.0))))),
                      withProbability(closeTo(1.0, 0.1), asDoubleIn(SI.SECOND, is(both(greaterThan(14000.0)).and(lessThan(32000.0)))))));
    }

}
