package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

public class DefaultSimuLizarTransmissionInterpreter<NodeType, PayloadType>
        implements ITransmissionInterpreter<NodeType, PayloadType, InterpreterDefaultContext> {

    private ILinkingResourceRouter<NodeType, SimulatedLinkingResource> router;
    private ITransmissionPayloadDemandCalculator<PayloadType, Double> calculator;
    private ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, InterpreterDefaultContext> transmissionSimulation;

    @Inject
    public DefaultSimuLizarTransmissionInterpreter(ILinkingResourceRouter<NodeType, SimulatedLinkingResource> router,
            ITransmissionPayloadDemandCalculator<PayloadType, Double> calculator,
            ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, InterpreterDefaultContext> transmissionSimulation) {
        this.router = router;
        this.calculator = calculator;
        this.transmissionSimulation = transmissionSimulation;
    }

    @Override
    public void interpretTransmission(NodeType source, NodeType target, PayloadType payload,
            InterpreterDefaultContext context) {
        var demand = calculator.calculatePayloadDemand(payload);
        var links = router.findRoute(source, target);
        if (links.isEmpty()) {
            throw new RuntimeException(
                    "Could not determine route between nodes. This should be turned into a simulation feature.");
        } else {
            links.get()
                .forEach(link -> transmissionSimulation.simulateTransmission(link, demand, context));
        }
    }

}
