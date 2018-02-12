package org.palladiosimulator.simulizar.interpreter.listener;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

public abstract class SimulationEvent {

	protected final double passageTime;
	protected final SimuComSimProcess thread;

	public SimulationEvent(SimuComSimProcess thread) {
		super();
        this.passageTime = thread.getModel().getSimulationControl().getCurrentSimulationTime();
		this.thread = thread;
	}

	/**
	 * @return the passageTime
	 */
	public double getPassageTime() {
	    return this.passageTime;
	}

	/**
	 * @return the thread
	 */
	public SimuComSimProcess getThread() {
	    return this.thread;
	}

}