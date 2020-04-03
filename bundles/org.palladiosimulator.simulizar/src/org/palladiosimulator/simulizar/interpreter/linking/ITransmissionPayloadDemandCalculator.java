package org.palladiosimulator.simulizar.interpreter.linking;

public interface ITransmissionPayloadDemandCalculator<PayloadType, DemandType> {
    DemandType calculatePayloadDemand(PayloadType payload);
}
