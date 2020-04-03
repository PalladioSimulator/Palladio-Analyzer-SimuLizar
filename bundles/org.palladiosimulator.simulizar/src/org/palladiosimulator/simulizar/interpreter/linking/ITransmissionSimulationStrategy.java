package org.palladiosimulator.simulizar.interpreter.linking;

public interface ITransmissionSimulationStrategy<LinkType, DemandType, ProcessType> {
    void simulateTransmission(LinkType link, DemandType demand, ProcessType process);
}
