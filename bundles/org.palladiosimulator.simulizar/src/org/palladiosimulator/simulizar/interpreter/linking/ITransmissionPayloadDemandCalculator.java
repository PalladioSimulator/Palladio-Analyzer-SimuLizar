package org.palladiosimulator.simulizar.interpreter.linking;

/**
 * 
 * Determines the demand stemming from transmitting a given payload.
 * 
 * @param <PayloadType>
 *            the type of payloads supported by this calculator
 * @param <DemandType>
 *            the type of demand, determined by this calculator
 * 
 */
public interface ITransmissionPayloadDemandCalculator<PayloadType, DemandType> {

    /**
     * Calculate the demand induced by transmitting payload.
     * 
     * @param payload
     *            the payload to transmit
     * @return the induced demand
     */
    DemandType calculatePayloadDemand(PayloadType payload);

}
