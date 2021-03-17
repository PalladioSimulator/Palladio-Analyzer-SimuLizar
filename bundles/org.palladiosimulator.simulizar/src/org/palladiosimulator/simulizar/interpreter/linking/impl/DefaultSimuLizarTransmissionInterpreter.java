package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.adapter.PreInterpretationBehaviorAdapter;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;

import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

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
    private final InterpreterResultHandler issueHandler;
    private final InterpreterResultMerger resultMerger;
    private final PreInterpretationBehaviorManager pibManager;
    //private final ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> linkingResourceAccess;

    @Inject
    public DefaultSimuLizarTransmissionInterpreter(
            ILinkingResourceRouter<NodeType, EntityReference<LinkingResource>> router,
            ITransmissionPayloadDemandCalculator<PayloadType, Double> calculator,
            ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext> transmissionSimulation,
            InterpreterResultHandler issueHandler, InterpreterResultMerger resultMerger,
            PreInterpretationBehaviorManager pibManager
            //ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> linkingResourceAccess
            ) {
        this.router = router;
        this.calculator = calculator;
        this.transmissionSimulation = transmissionSimulation;
        this.issueHandler = issueHandler;
        this.resultMerger = resultMerger;
        this.pibManager = pibManager;
        //this.linkingResourceAccess = linkingResourceAccess;
    }

    @Override
    public InterpreterResult interpretTransmission(NodeType source, NodeType target, PayloadType payload,
            InterpreterDefaultContext context) {
        InterpreterResult result = InterpreterResult.OK;
        var demand = calculator.calculatePayloadDemand(payload);
        var links = router.findRoute(source, target);
        if (links.isEmpty()) {
            throw new RuntimeException(
                    "Could not determine route between nodes. This should be turned into a simulation feature.");
        } else {
            // TODO

            // Search for an adapted pre-interpretation-behavior to execute them before
            // simulateTransmission()
            // For example to stop interpretation through InterpretationIssue of LinkCrashFailure

            for (EntityReference<LinkingResource> l : links.get()) {
                String identifier = l.getId();
                //var link = linkingResourceAccess.getSimulatedEntity(l.getId());
                String linkId = l.getId();
                if (pibManager.hasAdapterForEntityAlreadyBeenCreated(linkId)) {
                    PreInterpretationBehaviorAdapter adapter = pibManager.getAdapterForEntity(linkId);
                    InterpreterResult newResult = adapter.executeBehavior();
                    result = resultMerger.merge(result, newResult);
                }
            }
            if (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE) {
                links.get()
                    .forEach(link -> transmissionSimulation.simulateTransmission(link, demand, context));
            }
        }
        return result;
    }

}
