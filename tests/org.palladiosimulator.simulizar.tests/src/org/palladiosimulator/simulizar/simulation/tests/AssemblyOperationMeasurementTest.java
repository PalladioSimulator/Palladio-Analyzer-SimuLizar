package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.closeTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.asDoubleIn;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.match;
import static org.palladiosimulator.simulizar.test.commons.hamcrest.Matchers.withProbability;
import static org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils.allMeasurementsOfMetric;

import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.simulizar.test.commons.annotation.Identified;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.Named;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.annotation.SimulationConfig;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

/**
 * This test validats the functionality of response time measurements at Assembly Operation Measuring Points.
 * 
 * The feature was requested by SIMULIZAR-111.
 * 
 * @author Sebastian Krach
 *
 */
@PluginTestOnly
class AssemblyOperationMeasurementTest {

    @Test
    @LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodels/simulizar111", modelFiles = {
            "default.allocation", "default.monitorrepository", "default.usagemodel" })
    @SimulationConfig(maxMeasurements = "1000")
    @RunSimuLizar
    void testAssemblyOperationMeasurements(UsageScenario scenario, 
            @Named("AComponent1") AssemblyContext acomponent1,
            @Named("AComponent2") AssemblyContext acomponent2,
            @Named("do") OperationSignature signature,
            @Identified("_qZKs0CHbEd62GabW1zGSBw") OperationProvidedRole role,
            ExperimentRun expRun)
            throws JobFailedException, UserCanceledException {
        var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, scenario);
        assertTrue(measurement.isPresent());
        
        /* First validate, that the simulated load balancer distributed the load evenly */
        assertThat(allMeasurementsOfMetric(measurement.get(), MetricDescriptionConstants.RESPONSE_TIME_METRIC),
                match(withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(1, 0.0001)))),
                      withProbability(closeTo(0.5, 0.1), asDoubleIn(SI.SECOND, is(closeTo(2, 0.0001))))));
        
        var aCom1MPt = PcmmeasuringpointFactory.eINSTANCE.createAssemblyOperationMeasuringPoint();
        aCom1MPt.setAssembly(acomponent1);
        aCom1MPt.setOperationSignature(signature);
        aCom1MPt.setRole(role);
        
        var comp1measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, aCom1MPt);
        assertTrue(comp1measurement.isPresent());
        
        /* All user requests processed by the first assembly take 1 unit to execute */
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(comp1measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(closeTo(1, 0.0001)));
        
        var aCom2MPt = PcmmeasuringpointFactory.eINSTANCE.createAssemblyOperationMeasuringPoint();
        aCom2MPt.setAssembly(acomponent2);
        aCom2MPt.setOperationSignature(signature);
        aCom2MPt.setRole(role);
        
        var comp2measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE, aCom2MPt);
        assertTrue(comp2measurement.isPresent());

        /* All user requests processed by the second assembly take 2 time units to execute, 
         * as the server is half as fast as the first on */
        MeasurementTestUtils.allDoubleMeasurementValuesMatch(comp2measurement.get(),
                MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND,
                is(closeTo(2, 0.0001)));

    }
    

}
