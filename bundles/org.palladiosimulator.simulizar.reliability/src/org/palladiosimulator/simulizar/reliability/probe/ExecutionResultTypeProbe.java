package org.palladiosimulator.simulizar.reliability.probe;

import javax.measure.unit.Unit;

import org.palladiosimulator.measurementframework.BasicMeasurement;
import org.palladiosimulator.measurementframework.measure.IdentifierMeasure;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.measurement.ProbeMeasurement;
import org.palladiosimulator.probeframework.measurement.RequestContext;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class ExecutionResultTypeProbe extends Probe {

    public ExecutionResultTypeProbe() {
        super(MetricDescriptionConstants.EXECUTION_RESULT_TYPE);
    }
    
    public void measureExecutionResultType(InterpreterResult result, RequestContext requestContext) {
        var resultLiteral = result.hasNoIssues() ? MetricDescriptionConstants.EXECUTION_RESULT_TYPE_SUCCESS : 
            MetricDescriptionConstants.EXECUTION_RESULT_TYPE_FAILURE;
        
        var resultMeasure = new IdentifierMeasure<>(resultLiteral, Unit.ONE);
        var measurement = new BasicMeasurement<>(resultMeasure, MetricDescriptionConstants.EXECUTION_RESULT_TYPE);
        var probeMeasurement = new ProbeMeasurement(measurement, this, requestContext);
        
        notifyMeasurementSourceListener(probeMeasurement);
    }
    
}
