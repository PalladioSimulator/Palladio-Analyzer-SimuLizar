package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This calculator realizes a demand calculation compatible with the middleware marshalling palladio
 * model completion. It assumes, that the completion ensures, that the amount of data to be
 * transmitted is already precalculated and given as "BYTESIZE" characteristic of an element
 * "stream" on the stack frame.
 */
public class MiddlewareCompletionAwareDemandCalculator
        implements ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> {

    @Inject
    public MiddlewareCompletionAwareDemandCalculator() {
    }

    @Override
    public Double calculatePayloadDemand(SimulatedStackframe<Object> payload) {
        return NumberConverter.toDouble(StackContext.evaluateStatic("stream.BYTESIZE", Double.class, payload));
    }

}
