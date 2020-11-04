package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

/**
 * The {@link DefaultSimuLizarTransmissionInterpreter} encapsulated the default behavior of how link
 * transmissions are simulated in SimuLizar. The behavior encapsulated three stages:
 * <p>
 * <ul>
 * <li>calculate the demand of the given payload using an
 * {@link ITransmissionPayloadDemandCalculator}.
 * <li>determine a route of links using a {@link ILinkingResourceRouter}
 * <li>For each of the links of the route use a {@link ITransmissionSimulationStrategy} to issue the
 * calculated demand.
 * </ul>
 * <p>
 *
 * @param <NodeType>
 *            the type of nodes supported by the transmission interpreter. For this implementation
 *            the supported node type is determined by the used {@link ILinkingResourceRouter}.
 * @param <PayloadType>
 *            the type of supported payloads. For this implementation the supported payload type is
 *            determined by the used {@link ITransmissionPayloadDemandCalculator} and the
 *            {@link ITransmissionSimulationStrategy}.
 */
public class DefaultSimuLizarTransmissionInterpreter<NodeType, PayloadType>
        implements ITransmissionInterpreter<NodeType, PayloadType, InterpreterDefaultContext> {

    private ILinkingResourceRouter<NodeType, EntityReference<LinkingResource>> router;
    private ITransmissionPayloadDemandCalculator<PayloadType, Double> calculator;
    private ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext> transmissionSimulation;

    @Inject
    public DefaultSimuLizarTransmissionInterpreter(
            ILinkingResourceRouter<NodeType, EntityReference<LinkingResource>> router,
            ITransmissionPayloadDemandCalculator<PayloadType, Double> calculator,
            ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext> transmissionSimulation) {
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
