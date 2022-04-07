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
class QVTOExecutionTest {

    /**
     * Tests the execution of a simple qvto reconfiguration.
     * The reconfiguration changes the Usage Model's Population Parameter from 1 -> 2
     * In the beginning until the 2000s Simulation Time is reached, Population == 1
     * Between 2000s and 4000s Population == 2
     * After 4000 until the end of the simulation the Population is 1 again.
     * This results into two consecutive measurements between 2000 and 4000.
     * The ThinkTime is 99.0, which results into one measurement every 100s.
     * The responseTime itself doesn't change due to the reconfiguration.
     * Only the amount of measurements every 100s changes from 1 -> 2 -> 1
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/transformationQVTTest", modelFiles = {
            "default.allocation", "default.usagemodel", "default.repository", "defaultMonitor.monitorrepository" })
    @UseSimuLizarExtension(DaggerQVTOReconfigurationComponent.class)
    @SimulationConfig(maxMeasurements = "100")
    @SetConfigProperty(id = SimulizarConstants.RECONFIGURATION_RULES_FOLDER, value = "platform:/plugin/org.palladiosimulator.simulizar.tests/testmodels/transformationQVTTest/reconfigurations")
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
        
        double expectedMeasurementTime = 1.0;
        
        assertEquals(1901.0, timeMeasurements.get(19)
                .doubleValue(SI.SECOND), 0.001);
        assertEquals(2001.0, timeMeasurements.get(20)
                .doubleValue(SI.SECOND), 0.001);
        assertEquals(2002.0, timeMeasurements.get(21)
                .doubleValue(SI.SECOND), 0.001);
        assertEquals(2101.0, timeMeasurements.get(22)
                .doubleValue(SI.SECOND), 0.001);
        
        
//        for (int i = 0; i < 100; i++) {
//            if ((i < 100)) {
//                assertEquals(expectedMeasurementTime, timeMeasurements.get(i)
//                    .doubleValue(SI.SECOND), 0.001);
//                expectedMeasurementTime += 100.0;
//            }
//            if (i >= 20 && i < 60) {
//                assertEquals(expectedMeasurementTime, timeMeasurements.get(i)
//                    .doubleValue(SI.SECOND), 0.001);
//                expectedMeasurementTime += 1.0;
//                System.out.println("expectedMeaurementTime: " + expectedMeasurementTime);
//                System.out.println("Measurement: " + timeMeasurements.get(i)
//                        .doubleValue(SI.SECOND));
//                assertEquals(expectedMeasurementTime, timeMeasurements.get(i + 1)
//                        .doubleValue(SI.SECOND), 0.001);
//                expectedMeasurementTime += 99.0;
//                i++;
//            }
//            // From here as the second phase with Population = 2.0 ends with value 4002,
//            // The following measurements are off by 1.0s
//            if (i >= 60) {
//                assertEquals(expectedMeasurementTime + 1.0, timeMeasurements.get(i)
//                    .doubleValue(SI.SECOND), 0.001);
//                expectedMeasurementTime += 100.0;
//            }
//        }
        // Check whether all the response time stay at 1.0
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND, closeTo(1.0, 0.001));
    }
}

