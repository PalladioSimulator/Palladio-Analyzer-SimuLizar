package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.scheduler.IPassiveResource;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.SimSimpleFairPassiveResource;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

public class SimulatedBasicComponentInstance extends SimulatedComponentInstance {

    private final List<PassiveResource> passiveResourcesList;
    private final Map<String, IPassiveResource> passiveResourcesMap;

    public SimulatedBasicComponentInstance(final SimuLizarRuntimeState runtimeState, final FQComponentID fqID,
            final List<PassiveResource> passiveResources) {
        super(runtimeState, fqID);
        this.passiveResourcesList = passiveResources;

        this.passiveResourcesMap = new HashMap<String, IPassiveResource>();
        final AssemblyContext myAssCtx = fqID.getAssembyContextPath().get(fqID.getAssembyContextPath().size() - 1);
        for (PassiveResource passiveResource : passiveResources) {
            final long initialCount = StackContext.evaluateStatic(passiveResource.getCapacity_PassiveResource()
                    .getSpecification(), Integer.class);
            final IPassiveResource simulatedResource = new SimSimpleFairPassiveResource(passiveResource, myAssCtx,
                    getRuntimeState().getModel(), initialCount);
            this.passiveResourcesMap.put(passiveResource.getId(), simulatedResource);
            CalculatorHelper.setupPassiveResourceStateCalculator(simulatedResource, getRuntimeState().getModel());
            CalculatorHelper.setupWaitingTimeCalculator(simulatedResource, getRuntimeState().getModel());
            CalculatorHelper.setupHoldTimeCalculator(simulatedResource, getRuntimeState().getModel());
        }
    }

    public void acquirePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context,
            boolean timeout, double timeoutValue) {
        checkAcquireReleasePrecondition(passiveResource);

        passiveResourcesMap.get(passiveResource.getId()).acquire(context.getThread(), 1, timeout, timeoutValue);
    }

    public void releasePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context) {
        checkAcquireReleasePrecondition(passiveResource);

        passiveResourcesMap.get(passiveResource.getId()).release(context.getThread(), 1);
    }

    /**
     * @param passiveResource
     */
    private void checkAcquireReleasePrecondition(final PassiveResource passiveResource) {
        if (passiveResource == null || !passiveResourcesList.contains(passiveResource)) {
            throw new IllegalArgumentException("Illegal passive resource for this basic component instance passed");
        }
    }
}
