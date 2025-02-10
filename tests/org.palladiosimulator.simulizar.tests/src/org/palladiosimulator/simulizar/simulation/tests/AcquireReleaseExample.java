package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.firstMeasurementMatchesDifferentlyThanTheRest;

import jakarta.measure.unit.SI;
import jakarta.measure.unit.Unit;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class AcquireReleaseExample {

    /**
     * This test runs and validates the results as described in the manual test case SIMULIZAR-109.
     */
    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/Acquire_Example", modelFiles = {
            "AllocationExample.allocation", "AquireExample.usagemodel" })
    @SimulationConfig(maxMeasurements = "10")
    @RunSimuLizar
    void testAcquireReleaseExample(PassiveResource pResource, ProcessingResourceSpecification aCPU, UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        // Test step 5
        // The response time of the first four requests should be 10, 20, 30, and 40. Then, the response time should be constant at 50.
        var usageScenarioMeasurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(), 
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(usageScenarioMeasurement.isPresent());
        
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(usageScenarioMeasurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                firstMeasurementMatchesDifferentlyThanTheRest(closeTo(10.0, 0.001), 
                    firstMeasurementMatchesDifferentlyThanTheRest(closeTo(20.0, 0.001), 
                        firstMeasurementMatchesDifferentlyThanTheRest(closeTo(30.0, 0.001), 
                            firstMeasurementMatchesDifferentlyThanTheRest(closeTo(40.0, 0.001), 
                                    closeTo(50.0, 0.001))))));
        
        // Test step 6
        // The utilization of CPU should be 100% with 1 job.
        var soarMeasurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE, aCPU);
        assertTrue(soarMeasurement.isPresent());
        var buckets = MeasurementTestUtils.calculateIntBucketsBasedOnStateDuration(soarMeasurement.get(),
                MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC, Unit.ONE);
        assertThat(buckets.get(0), is(closeTo(0.0, 0.01)));
        var bucketSum = buckets.entrySet().stream().filter(e -> e.getKey() != 0).mapToDouble(e -> e.getValue()).sum();
        assertThat(bucketSum, is(closeTo(100.0, 0.1)));
        
        // Test step 7
        // The utilization should be 100% with the state "0", meaning no resource is available.
        var soprMeasurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC_TUPLE, pResource);
        assertTrue(soprMeasurement.isPresent());
        buckets = MeasurementTestUtils.calculateIntBucketsBasedOnStateDuration(soprMeasurement.get(),
                MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC, Unit.ONE);
        assertThat(buckets.get(0), is(closeTo(100.0, 0.01)));
        bucketSum = buckets.entrySet().stream().filter(e -> e.getKey() != 0).mapToDouble(e -> e.getValue()).sum();
        assertThat(bucketSum, is(closeTo(0.0, 0.1)));
        
        // Test step 8
        // The wait time of the first four requests should be 0, 10, 20, and 30. Then, the wait time should be constant at 40.
        var waitingTimeMeasurement = expRun.getMeasurement().stream().filter(m -> m.getMeasuringType().getMetric().getId().equals(
                MetricDescriptionConstants.WAITING_TIME_METRIC_TUPLE.getId())).findAny();
        assertTrue(waitingTimeMeasurement.isPresent());
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(waitingTimeMeasurement.get(),
                MetricDescriptionConstants.WAITING_TIME_METRIC, SI.SECOND,
                firstMeasurementMatchesDifferentlyThanTheRest(closeTo(0.0, 0.001), 
                    firstMeasurementMatchesDifferentlyThanTheRest(closeTo(10.0, 0.001), 
                        firstMeasurementMatchesDifferentlyThanTheRest(closeTo(20.0, 0.001), 
                            firstMeasurementMatchesDifferentlyThanTheRest(closeTo(30.0, 0.001), 
                                    closeTo(40.0, 0.001))))));

    }

}
