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
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class UsageevolutionTest {

    /**
     * Tests Usageevolution with a simple deterministic example. This test only tests the correct
     * responste time evolution.
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/usageevolutionTest", modelFiles = {
            "default.allocation", "default.usagemodel", "default.repository", "default.usageevolution" })
    @SimulationConfig(maxMeasurements = "100") 
    @RunSimuLizar
    void testUsageevolutionResponseTimeVariation(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        List<Measure<?, Duration>> responseTimeMeasurements = MeasurementTestUtils
            .allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        List<Measure<?, Duration>> timeMeasurements = MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertEquals(responseTimeMeasurements.size(), timeMeasurements.size());

        // The simulated test scenario evolves response time and user amount.
        // Population is 1 and Think Time is 99.0
        // It evolves in 3 stages.
        // Stage 1: 2000s with constant 1.0 20 measurements in 20 timesteps
        // Stage 2: 2000s with constant 2.0 (Leads to Response Time 4.0 as both values evolve)
        // 40 Measurements in 20 Timesteps (two parallel)
        // Stage 3: 6000s with constant 1.0 40 measurements in 40 timesteps
        // It is important to know, that this constant evolves the user requests from 1 to 2 aswell
        // as the response time. 
        // ^
        // | ...[20]...
        // |
        // |
        // |...[20].. ....[40]..... 
        // ---------------------------------------->
        // Number in Brackets means total 20 measuring Points
        // In Stage 2 every Point is representing two measurements as both values evolved to 2 they
        // have the exact same
        // resonse time at the same point in time.
        for (int i = 0; i < 100; i++) {
            if ((i < 20)) {
                assertEquals(responseTimeMeasurements.get(i)
                    .doubleValue(SI.SECOND), 1.0, 0.001);
            }
            if (i >= 20 && i < 60) {
                assertEquals(responseTimeMeasurements.get(i)
                    .doubleValue(SI.SECOND), 4.0, 0.001);
            }
            if (i >= 60) {
                assertEquals(responseTimeMeasurements.get(i)
                    .doubleValue(SI.SECOND), 1.0, 0.001);
            }

            // Test Measurement Time Stamps
            assertEquals((Double) timeMeasurements.get(19)
                .doubleValue(SI.SECOND), 1901.0, 0.001);
            assertEquals((Double) timeMeasurements.get(20)
                .doubleValue(SI.SECOND), 2004.0, 0.001);
            assertEquals((Double) timeMeasurements.get(21)
                .doubleValue(SI.SECOND), 2004.0, 0.001);
            assertEquals((Double) timeMeasurements.get(22)
                .doubleValue(SI.SECOND), 2107.0, 0.001);
            // Section 1 -> Section 2 matching evolution of response time
            assertEquals((Double) responseTimeMeasurements.get(19)
                .doubleValue(SI.SECOND), 1.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(20)
                .doubleValue(SI.SECOND), 4.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(21)
                .doubleValue(SI.SECOND), 4.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(22)
                .doubleValue(SI.SECOND), 4.0, 0.001);
            // Stage 2 -> Stage 3 same as above but with Section 3 only having 1 user again.
            assertEquals((Double) timeMeasurements.get(59)
                .doubleValue(SI.SECOND), 3961.0, 0.001);
            assertEquals((Double) timeMeasurements.get(60)
                .doubleValue(SI.SECOND), 4061.0, 0.001);
            assertEquals((Double) timeMeasurements.get(61)
                .doubleValue(SI.SECOND), 4161.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(59)
                .doubleValue(SI.SECOND), 4.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(60)
                .doubleValue(SI.SECOND), 1.0, 0.001);
            assertEquals((Double) responseTimeMeasurements.get(61)
                .doubleValue(SI.SECOND), 1.0, 0.001);
        }
    }

    /**
     * Tests how the values behave if the usageevolution model is not loaded. This means it is
     * tested, whether no evolution happens without usage evolution.
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/usageevolutionTest", modelFiles = {
            "default.allocation", "default.usagemodel", "default.repository" })
    @SimulationConfig(maxMeasurements = "100")
    @RunSimuLizar
    void testNoEvolutionWithoutUsageEvolution(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        List<Measure<?, Duration>> responseTimeMeasurements = MeasurementTestUtils
            .allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        List<Measure<?, Duration>> timeMeasurements = MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(),
                MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        assertEquals(responseTimeMeasurements.size(), timeMeasurements.size());
        // No Evolution means, that the response time stays the same for the all measurements at 1.0
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND, closeTo(1.0, 0.001));
    }
}
