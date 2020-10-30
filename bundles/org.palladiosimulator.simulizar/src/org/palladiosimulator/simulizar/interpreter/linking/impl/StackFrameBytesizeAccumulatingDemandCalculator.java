package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class StackFrameBytesizeAccumulatingDemandCalculator
        implements ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> {

    private static final Logger LOGGER = Logger.getLogger(StackFrameBytesizeAccumulatingDemandCalculator.class);

    @Inject
    public StackFrameBytesizeAccumulatingDemandCalculator() {
    }
    
    @Override
    public Double calculatePayloadDemand(SimulatedStackframe<Object> payload) {
        double demand = 0.0;
        for (java.util.Map.Entry<String, Object> entry : payload.getContents()) {
            if (entry.getKey().endsWith("BYTESIZE")) {
                if (entry.getKey().contains(".INNER.")) {
                    LOGGER.warn("Network demand cannot be properly determined for INNER BYTESIZE characterizations "
                            + "yet, the simulation will assume that there is just a single element in the collection. "
                            + "Please enable the \"simulate middleware marshalling / demarshalling of remote calls\" "
                            + "in the feature settings tab or directly define the BYTESIZE of the collection.");
                }
                demand += NumberConverter.toDouble(entry.getValue());
            }
        }
        return demand;
    }

}
