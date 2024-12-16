package org.palladiosimulator.simulizar.reliability.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.allMeasurementsOfMetric;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.getMeasurementOfAt;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.measurementframework.measure.IdentifierMeasure;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.reliability.di.DaggerReliabilityExtensionComponent;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class ExecutionResultTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.reliability.tests", basePath = "testmodels/simulizar151", modelFiles = {
            "default.allocation", "default.usagemodel", "My.monitorrepository" })
    @UseSimuLizarExtension(DaggerReliabilityExtensionComponent.class)
    @SimulationConfig(simulateReliability = true)
    @RunSimuLizar
    void testExecutionResultRecording(UsageScenario usageScenarion,ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var resultMeasurement = getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.EXECUTION_RESULT_TYPE_TUPLE, usageScenarion);
        assertTrue(resultMeasurement.isPresent());
                
        var results = allMeasurementsOfMetric(resultMeasurement.get(), MetricDescriptionConstants.EXECUTION_RESULT_TYPE);
        
        var noSuccesses = results.stream().map(IdentifierMeasure.class::cast).map(m -> m.getValue().getLiteral())
                .filter(lit -> lit.equals(MetricDescriptionConstants.EXECUTION_RESULT_TYPE_SUCCESS.getLiteral())).count();
        assertThat(noSuccesses * 1.0 / results.size(), is(closeTo(0.7d, 0.1)));
    }

}
