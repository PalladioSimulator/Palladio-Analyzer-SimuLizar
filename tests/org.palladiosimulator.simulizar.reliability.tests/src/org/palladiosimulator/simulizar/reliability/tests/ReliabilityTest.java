package org.palladiosimulator.simulizar.reliability.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.allMeasurementsOfMetric;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.getMeasurementOfAt;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.simulizar.reliability.di.DaggerReliabilityExtensionComponent;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.Named;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class ReliabilityTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.reliability.tests", basePath = "testmodels/simulizar148", modelFiles = {
            "default.allocation", "default.usagemodel", "default.monitorrepository" })
    @UseSimuLizarExtension(DaggerReliabilityExtensionComponent.class)
    @SimulationConfig(simulateReliability = true)
    @RunSimuLizar
    void testReliabilityExtension(@Named("ExternalCallActionFoo") ExternalCallAction fooCallAction, 
            @Named("ExternalCallActionBar") ExternalCallAction barCallAction, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var fooMeasurement = getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, fooCallAction);
        assertTrue(fooMeasurement.isPresent());
        
        var barMeasurement = getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, barCallAction);
        assertTrue(barMeasurement.isPresent());
        
        var fooCalls = allMeasurementsOfMetric(fooMeasurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        var barCalls = allMeasurementsOfMetric(barMeasurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        
        assertThat(fooCalls, hasSize(greaterThan(100)));
        assertThat(barCalls, hasSize(greaterThan(100)));
        assertThat(barCalls.size() * 1.0 / fooCalls.size(), is(closeTo(0.5d, 0.1)));
    }

}
