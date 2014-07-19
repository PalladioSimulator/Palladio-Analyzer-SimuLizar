package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

public class SimulatedBasicComponentInstance extends SimulatedComponentInstance {

    private final List<PassiveResource> myPassiveResources;
    private final Map<String, Integer> passiveResourceCount;
    private final Map<String, Queue<SimuComSimProcess>> passiveResourceWaitingQueue;

    public SimulatedBasicComponentInstance(FQComponentID fqID,
            List<PassiveResource> passiveResources) {
        super(fqID);
        this.myPassiveResources = passiveResources;
        this.passiveResourceCount = new HashMap<String, Integer>();
        this.passiveResourceWaitingQueue = new HashMap<String, Queue<SimuComSimProcess>>();
        for (PassiveResource passiveResource : passiveResources) {
            Integer initialCount = StackContext.evaluateStatic(
                    passiveResource.getCapacity_PassiveResource().getSpecification(),
                    Integer.class);
            passiveResourceCount.put(passiveResource.getId(), initialCount);
            passiveResourceWaitingQueue.put(passiveResource.getId(), new LinkedList<SimuComSimProcess>());
        }
    }

    public void acquirePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context) {
        checkAcquireReleasePrecondition(passiveResource);

        int current = passiveResourceCount.get(passiveResource.getId());
        if (current == 0) {
            passiveResourceWaitingQueue.get(passiveResource.getId()).offer(context.getThread());
            context.getThread().passivate();
        }
        current--;
        passiveResourceCount.put(passiveResource.getId(), current);
    }

    public void releasePassiveResource(final PassiveResource passiveResource) {
        checkAcquireReleasePrecondition(passiveResource);

        int current = passiveResourceCount.get(passiveResource.getId());
        if (current == 0) {
            if (passiveResourceWaitingQueue.get(passiveResource.getId()).size() > 0) {
                passiveResourceWaitingQueue.get(passiveResource.getId()).poll().activate();
            } else {
                current = 1;
            }
        } else {
            current++;
        }
        passiveResourceCount.put(passiveResource.getId(), current);
    }

    /**
     * @param passiveResource
     */
    private void checkAcquireReleasePrecondition(final PassiveResource passiveResource) {
        if (passiveResource == null || !myPassiveResources.contains(passiveResource)) {
            throw new IllegalArgumentException("Illegal passive resource for this basic component instance passed");
        }
    }
}
