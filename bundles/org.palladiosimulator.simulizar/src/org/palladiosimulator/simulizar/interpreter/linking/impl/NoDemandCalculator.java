package org.palladiosimulator.simulizar.interpreter.linking.impl;

import jakarta.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This calculator realizes the "No throughput simulation" logic.
 */
public class NoDemandCalculator
        implements ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> {
    
    @Inject
    public NoDemandCalculator() {}

    @Override
    public Double calculatePayloadDemand(SimulatedStackframe<Object> payload) {
        return 0.0d;
    }

}
