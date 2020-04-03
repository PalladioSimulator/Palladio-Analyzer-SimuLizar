package org.palladiosimulator.simulizar.interpreter.linking.impl;

import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;
import org.palladiosimulator.simulizar.interpreter.linking.TransmissionType;

public class OrchestratingTransmissionInterpreter<TransmissionContextType, PayloadType, DemandType, ProcessType, NodeType, LinkType>
        implements ITransmissionInterpreter<NodeType, TransmissionContextType> {

    private ILinkingResourceRouter<NodeType, LinkType> router;
    private ITransmissionPayloadDemandCalculator<PayloadType, DemandType> calculator;
    private ITransmissionSimulationStrategy<LinkType, DemandType, ProcessType> transmissionSimulation;
    private PayloadExtractor<TransmissionContextType, PayloadType> payloadExtractor;
    private ProcessExtractor<TransmissionContextType, ProcessType> processExtractor;

    @FunctionalInterface
    public interface PayloadExtractor<TransmissionContextType, PayloadType> {
        PayloadType extract(TransmissionContextType context, TransmissionType type);
    }
    
    @FunctionalInterface
    public interface ProcessExtractor<TransmissionContextType, ProcessType> {
        ProcessType extract(TransmissionContextType context);
    }

    public OrchestratingTransmissionInterpreter(ILinkingResourceRouter<NodeType, LinkType> router,
            ITransmissionPayloadDemandCalculator<PayloadType, DemandType> calculator,
            ITransmissionSimulationStrategy<LinkType, DemandType, ProcessType> transmissionSimulation,
            PayloadExtractor<TransmissionContextType, PayloadType> payloadExtractor,
            ProcessExtractor<TransmissionContextType, ProcessType> processExtractor) {
        this.router = router;
        this.calculator = calculator;
        this.transmissionSimulation = transmissionSimulation;
        this.payloadExtractor = payloadExtractor;
        this.processExtractor = processExtractor;
    }

    @Override
    public void interpretTransmission(NodeType source, NodeType target,
            TransmissionType type, TransmissionContextType context) {
        var payload = payloadExtractor.extract(context, type);
        var demand = calculator.calculatePayloadDemand(payload);
        
        for (var link : router.findRoute(source, target)) {
            transmissionSimulation.simulateTransmission(link, demand, processExtractor.extract(context));
        }
    }

}
