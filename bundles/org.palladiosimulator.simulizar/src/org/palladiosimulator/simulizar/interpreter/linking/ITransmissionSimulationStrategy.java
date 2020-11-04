package org.palladiosimulator.simulizar.interpreter.linking;

/**
 * The transmission simulation strategy encapsultes the concrete behavior, how a given demand for a
 * given link is actually simulated.
 *
 * @param <LinkType>
 *            the type of linking resource
 * @param <DemandType>
 *            the type of issued demand
 * @param <ContextType>
 *            the type of the context of current user activity
 */
public interface ITransmissionSimulationStrategy<LinkType, DemandType, ContextType> {

    /**
     * Simulate a transmission of {@link demand} through {@link link} in the user context of
     * {@link context}. This method blocks until the transmission is concluded.
     * 
     * @param link
     *            the link
     * @param demand
     *            the calculated demand
     * @param context
     *            the context of current user activity
     */
    void simulateTransmission(LinkType link, DemandType demand, ContextType context);

}
