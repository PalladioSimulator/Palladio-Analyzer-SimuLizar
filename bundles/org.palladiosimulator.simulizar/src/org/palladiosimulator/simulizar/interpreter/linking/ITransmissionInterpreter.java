package org.palladiosimulator.simulizar.interpreter.linking;

public interface ITransmissionInterpreter<NodeType, TransmissionContext> {

    void interpretTransmission(NodeType source, NodeType target, TransmissionType type, TransmissionContext context);

}
