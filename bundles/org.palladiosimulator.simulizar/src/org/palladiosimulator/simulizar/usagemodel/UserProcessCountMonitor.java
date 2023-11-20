package org.palladiosimulator.simulizar.usagemodel;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimProcessListener;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

public class UserProcessCountMonitor implements IUserProcessCountMonitor, ISimProcessListener {

    private static final Logger LOGGER = Logger.getLogger(UserProcessCountMonitor.class.getName());

    private final ISimulationControl simulation;
    private final int maxProcessCount;

    private int processCounter;

    public UserProcessCountMonitor(ISimulationControl simulation, int maxProcessCount) {
        this.simulation = simulation;
        this.maxProcessCount = maxProcessCount;
    }

    @Override
    public void registerProcess(ISimProcess process) {
        process.addProcessListener(this);
    }

    @Override
    public void notifyStarted(ISimProcess process) {
        final int currentCounter;
        synchronized (this) {
            currentCounter = ++processCounter;
        }

        if (maxProcessCount > 0) {
            if (currentCounter >= maxProcessCount) {
                LOGGER.info(String.format("Reached max user process count %d -> stop simulation", maxProcessCount));
                simulation.stop();
            }
        }
    }

    @Override
    public void notifyTerminated(ISimProcess process) {
        synchronized (this) {
            processCounter--;
        }
        process.removeProcessListener(this);
    }

    @Override
    public void notifyResuming(ISimProcess process) {
    }

    @Override
    public void notifySuspending(ISimProcess process) {
    }
}
