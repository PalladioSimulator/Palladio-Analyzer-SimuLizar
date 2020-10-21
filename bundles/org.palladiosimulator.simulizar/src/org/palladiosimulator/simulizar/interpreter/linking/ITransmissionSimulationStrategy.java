package org.palladiosimulator.simulizar.interpreter.linking;

public interface ITransmissionSimulationStrategy<LinkType, DemandType, ContextType> {
    
    void simulateTransmission(LinkType link, DemandType demand, ContextType context);
    
}
