package org.palladiosimulator.simulizar.interpreter.linking.impl;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class NoDemandCalculator
        implements ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> {

    @Override
    public Double calculatePayloadDemand(SimulatedStackframe<Object> payload) {
        return 0.0d;
    }

}
