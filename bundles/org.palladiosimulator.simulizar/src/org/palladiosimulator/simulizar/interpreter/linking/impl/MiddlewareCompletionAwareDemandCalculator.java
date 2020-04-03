package org.palladiosimulator.simulizar.interpreter.linking.impl;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class MiddlewareCompletionAwareDemandCalculator
        implements ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> {

    @Override
    public Double calculatePayloadDemand(SimulatedStackframe<Object> payload) {
        return NumberConverter.toDouble(
                StackContext.evaluateStatic("stream.BYTESIZE", Double.class, payload));
    }

}
