package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.*;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.*;

import javax.measure.unit.SI;
import org.junit.jupiter.api.Test;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.events.DaggerEventExtensionComponent;
import org.palladiosimulator.simulizar.events.EventsTransformationConfiguration;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SetConfigProperty;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
class EventExtensionTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.examples.package", basePath = "initiatorTemplates/MinimumEvent_Example", modelFiles = {
            "default.allocation", "default.usagemodel" })
    @UseSimuLizarExtension(DaggerEventExtensionComponent.class)
    @SetConfigProperty(id = EventsTransformationConfiguration.SIMULATE_EVENTS, value = "true")
    @SetConfigProperty(id = EventsTransformationConfiguration.EVENT_MIDDLEWARE_FILE, value = ConstantsContainer.DEFAULT_EVENT_MIDDLEWARE_FILE)
    @SetConfigProperty(id = EventsTransformationConfiguration.STORE_TRANSFORMED_MODELS, value = "false")
    @RunSimuLizar
    void testEventExtension(UsageScenario scenario, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());

        assertThat(allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC),
                match(withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(5, 0.0001)))),
                      withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(4, 0.0001)))),
                      withProbability(lessThan(0.01), asDoubleIn(SI.SECOND, is(lessThan(3.999))))));
    }

}
