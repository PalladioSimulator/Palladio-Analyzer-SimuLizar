package org.palladiosimulator.simulizar.interpreter.linking;

public interface ITransmissionInterpreter<NodeType, PayloadType, TransmissionContext> {

    void interpretTransmission(NodeType source, NodeType target, PayloadType payloadProvider,
            TransmissionContext transmissionContext);
    
}
