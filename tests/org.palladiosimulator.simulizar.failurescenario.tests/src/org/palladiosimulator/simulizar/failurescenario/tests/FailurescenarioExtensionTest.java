package org.palladiosimulator.simulizar.failurescenario.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.asDoubleIn;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.match;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.withProbability;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.allMeasurementsOfMetric;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.getMeasurementOfAt;

import java.util.List;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.quantity.Quantity;
import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.MeasuringType;
import org.palladiosimulator.edp2.models.ExperimentData.Run;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.failurescenario.di.DaggerFailurescenarioExtensionComponent;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.Named;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class FailurescenarioExtensionTest {
	@Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.failurescenario.tests", basePath = "testmodels/minimalexample", modelFiles = {
            "secondScenario.allocation", "secondScenario.usagemodel", "testScenarioHWCrash.failurescenario" })
    @UseSimuLizarExtension(DaggerFailurescenarioExtensionComponent.class)
    @RunSimuLizar
    void testHWCrash(@Named("callFoo") ExternalCallAction callFooAction, ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, callFooAction);
        assertTrue(measurement.isPresent());
        List<Measure<?, Duration>> yVar = MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC);
        List<Measure<?, Duration>> xVar = MeasurementTestUtils.allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.POINT_IN_TIME_METRIC);
        
        yVar.get(0).doubleValue(SI.SECOND);
        
        
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(allOf(greaterThan(0.0), lessThan(20.0))));
        
//        MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
//                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
//                is(closeTo(8.0, 2.5)));
        
//        assertThat(allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC),
//                match(withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(5, 0.0001)))),
//                      withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(4, 0.0001)))),
//                      withProbability(lessThan(0.01), asDoubleIn(SI.SECOND, is(lessThan(3.999))))));
    }
	
	void testSWCrash(UsageScenario scenario, ExperimentRun expRun) {
		// assert t<50k-->closeTo(9.5,+-1.6)
	}
	
	void testLinkCrash(UsageScenario scenario, ExperimentRun expRun) {
		// assert t<50k-->closeTo(9.5,+-1.6)
	}
	
	void testLinkTransientCrash(UsageScenario scenario, ExperimentRun expRun) {
		// assert t<50k-->closeTo(9.5,+-1.6)
	}
}
