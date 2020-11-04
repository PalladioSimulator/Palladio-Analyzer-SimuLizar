package org.palladiosimulator.simulizar.interpreter.linking;

/**
 * The transmission interpreter encapsulates the logic how a transmission between a source and a
 * target node impacts the current simulation w. r. t. performance relevant side effects.
 *
 * @param <NodeType>
 *            The type of interconnected nodes, e. g. resource containers.
 * @param <PayloadType>
 *            The type of payload that is transmitted between source and target.
 * @param <TransmissionContext>
 *            The simulation context, representing the current user thread of activity
 */
public interface ITransmissionInterpreter<NodeType, PayloadType, TransmissionContext> {

    /**
     * Simulate a transmission of a payload between source and target node in the context of the
     * current user. This method blocks until the transmission is fulfilled.
     * 
     * @param source
     *            the node where the transmission is originating
     * @param target
     *            the target node of the transmission
     * @param payload
     *            the payload which is transmitted
     * @param transmissionContext
     *            the context representing the current user thread of activity
     */
    void interpretTransmission(NodeType source, NodeType target, PayloadType payload,
            TransmissionContext transmissionContext);

}
