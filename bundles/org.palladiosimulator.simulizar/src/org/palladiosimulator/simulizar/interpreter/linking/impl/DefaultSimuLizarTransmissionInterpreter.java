package org.palladiosimulator.simulizar.interpreter.linking.impl;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

import de.uka.ipd.sdq.simucomframework.Context;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class DefaultSimuLizarTransmissionInterpreter extends
        OrchestratingTransmissionInterpreter<InterpreterDefaultContext, SimulatedStackframe<Object>, Double, SimuComSimProcess, AbstractSimulatedResourceContainer, SimulatedLinkingResource> {

    @Inject
    public DefaultSimuLizarTransmissionInterpreter(
            ILinkingResourceRouter<AbstractSimulatedResourceContainer, SimulatedLinkingResource> router,
            ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> calculator,
            ITransmissionSimulationStrategy<SimulatedLinkingResource, Double, SimuComSimProcess> transmissionSimulation) {
        super(router, calculator, transmissionSimulation, 
                (ctx, t) -> ctx.getStack().currentStackFrame(), 
                Context::getThread);
    }

}
