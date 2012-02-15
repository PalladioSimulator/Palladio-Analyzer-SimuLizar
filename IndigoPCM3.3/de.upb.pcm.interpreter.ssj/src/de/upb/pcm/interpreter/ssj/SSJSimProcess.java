/**
 * 
 */
package de.upb.pcm.interpreter.ssj;

import org.apache.log4j.Logger;

import umontreal.iro.lecuyer.simevents.Event;
import umontreal.iro.lecuyer.simevents.Simulator;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.ISimProcessDelegate;
import de.uka.ipd.sdq.simucomframework.abstractSimEngine.SimProcess;

enum ProcessState {
	READY, SUSPENDED, RUNNING, TERMINATED
}

/**
 * @author Snowball, Michael H. & Jens (bugfixing, refactorings, cleanup)
 * 
 */
public class SSJSimProcess implements ISimProcessDelegate {

	private static Logger logger = Logger.getLogger(SSJSimProcess.class.getName());

	private SimProcess myAbstractProcess = null;
	private ProcessState myProcessState = ProcessState.READY;
	private ISimProcessStrategy processStrategy = new SimProcessThreadingStrategy();

	private Simulator sim;

	public SSJSimProcess(SimProcess myProcess, String name) {
		this.sim = ((SSJExperiment) myProcess.getModel().getSimulationControl()).getSimulator();
		this.myAbstractProcess = myProcess;
		Runnable myRunnable = new Runnable() {
			public void run() {
				logger.debug("Starting sim process [ID: " + SSJSimProcess.this.myAbstractProcess.getId() + "]");
				SSJSimProcess.this.actions();
				SSJSimProcess.this.processStrategy = null;
			}
		};

		processStrategy.startProcess(myRunnable);
	}

	public void scheduleAt(double delay) {
		if (this.myProcessState != ProcessState.SUSPENDED
				&& !isTerminated())
			throw new IllegalStateException("Tried to schedule thread which was not suspended [" + this.myAbstractProcess.getId() + "]");

		// Resume process immediately to force process cleanup
		if (!simIsRunning() && !isTerminated()) {
			resume();
		}
		
		if (!isTerminated()){
			new Event(sim) {
				@Override
				public void actions() {
					if (myProcessState != ProcessState.TERMINATED) {
						resume();
					}
				}
			}.schedule(delay);
		}
	}

	private boolean simIsRunning() {
		return ((SSJExperiment) myAbstractProcess.getModel().getSimulationControl()).isRunning();
	}

	public void actions() {
		// set state to running and suspended, i.e., return to constructor
		this.myProcessState = ProcessState.RUNNING;
		suspend();

		// execute process's behavior
		myAbstractProcess.lifeCycle();

		// terminate process
		this.myProcessState = ProcessState.TERMINATED;
		processStrategy.finishProcess();
	}

	public boolean isTerminated() {
		return this.myProcessState == ProcessState.TERMINATED;
	}

	public void passivate() {
		suspend();
	}

	private void suspend() {
		if (this.myProcessState != ProcessState.RUNNING)
			throw new IllegalStateException("Tried to suspend non-running process [" + this.myAbstractProcess.getId() + "]");

		logger.debug("Suspending thread [" + this.myAbstractProcess.getId() + "]");
		this.myProcessState = ProcessState.SUSPENDED;
		processStrategy.suspendProcess();
	}

	private void resume() {
		if (this.myProcessState != ProcessState.SUSPENDED)
			throw new IllegalStateException("Tried to resume thread which was not suspended [" + this.myAbstractProcess.getId() + "]");
		logger.debug("Resuming thread [" + this.myAbstractProcess.getId() + "]");

		this.myProcessState = ProcessState.RUNNING;
		processStrategy.resumeProcess();
	}

}
