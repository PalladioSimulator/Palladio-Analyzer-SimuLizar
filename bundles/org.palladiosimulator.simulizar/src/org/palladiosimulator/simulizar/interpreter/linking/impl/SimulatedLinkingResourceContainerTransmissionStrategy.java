package org.palladiosimulator.simulizar.interpreter.linking.impl;

import java.util.Collections;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

public class SimulatedLinkingResourceContainerTransmissionStrategy
        implements ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, InterpreterDefaultContext> {

    @Override
    public void simulateTransmission(SimulatedLinkingResource link, Double demand, InterpreterDefaultContext context) {
        link.consumeResource(context.getThread(), 0, Collections.emptyMap(), demand);
    }

}
