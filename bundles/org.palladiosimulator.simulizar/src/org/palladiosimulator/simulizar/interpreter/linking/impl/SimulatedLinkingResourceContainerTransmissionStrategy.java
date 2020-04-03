package org.palladiosimulator.simulizar.interpreter.linking.impl;

import java.util.Collections;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

public class SimulatedLinkingResourceContainerTransmissionStrategy
        implements ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, SimuComSimProcess> {

    @Override
    public void simulateTransmission(SimulatedLinkingResource link, Double demand, SimuComSimProcess process) {
        link.consumeResource(process, 0, Collections.emptyMap(), demand);
    }

}
