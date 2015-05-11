package de.mdelab.eurema.interpreter.execution.consistency;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.mdelab.eurema.interpreter.execution.module.executor.ModuleExecutable;

/**
 * Implements the quiescence life cycle for the adaptation engine, that is, for
 * the layers 1..n in the layered architecture of the self-adaptive system.
 * Modules at layer 0 are not the target of quiescence here since they are part
 * of the adaptable software, not the adaptation engine. Quiescence is required
 * to enact safe updates developed offline for maintaining the self-adaptive
 * software. For an update, the whole adaptation engine is set to an quiescent
 * state, hence the region that is the target of quiescence is always the whole
 * engine and there is at most one such region with a life cycle for quiescence.
 * 
 * Quiescence life cycle:
 * <ul>
 * <li>{@link QuiescenceState#OFF}: No quiescence and any module can be
 * executed.</li>
 * <li>{@link QuiescenceState#BLOCKING}: Quiescence has been initialed, that is,
 * currently running modules should terminate execution, new executions should
 * be blocked except of those that are required to let already running modules
 * terminate.</li>
 * <li>{@link QuiescenceState#QUIESCENT}: Quiescence has been established. No
 * modules are currently running, any executions are blocked, the architecture
 * can be safely reconfigured by off-line adaptation.</li>
 * </ul>
 * Initially, the state is OFF. Activating quiescence sets the state to BLOCKING
 * (cf. {@link #activateQuiescence()}). If the quiescence manager detects that
 * all modules are quiescent, it sets the state to QUIESCENT. A BLOCKING or
 * QUIESCENT state can be deactivated resulting in the state OFF (cf.
 * {@link #deactivateQuiescence()}).
 * 
 * Since a module at layer <tt>i</tt> (<tt>i</tt> is greater than <tt>1</tt>) is
 * only executed by interrupting a module at layer <tt>i-1</tt> (i.e., the
 * lower-layer module triggers the higher-layer module), quiescence is realized
 * by blocking new execution of layer 1 modules. If all layer 1 modules have
 * terminated, quiescence is reached since all other modules at layers 2..n must
 * have terminated since they are executed in the scope/context of layer 1
 * modules.
 * 
 * In general, to reach quiescence by blocking the execution of a module
 * <tt>m</tt> , three cases have to be considered:
 * 
 * (1) The module <tt>m</tt> at any layer 1..n is invoked by another module of
 * the same layer that is currently running. Thus, this module should be
 * executed to let the invoking module terminate execution.
 * 
 * (2) The (higher-layer) module <tt>m</tt> (at layer <tt>i</tt> with <tt>i</tt>
 * greater than <tt>1</tt>) is triggered by an interrupt of a (lower-layer)
 * module (at layer <tt>i-1</tt>) that is currently running. Thus, this module
 * <tt>m</tt> should be executed to let the interrupted module terminate
 * execution.
 * 
 * (3) The module <tt>m</tt> is directly triggered by events emitted by the
 * adaptable software or by time. Thus, <tt>m</tt> is located at layer 1. In
 * this case, initiating the execution of the module should be blocked.
 * 
 * 
 * Since higher-layer modules, that are, modules at layer 2..n, are only
 * triggered by interrupting lower-layer modules, these modules must not be
 * blocked to let the lower-layer modules terminate execution. Thus, (2) can be
 * realized by just blocking modules at layer 1. According to (1), layer 1
 * modules should be executed if they are invoked by other modules. Thus, to
 * reach quiescence by the <tt>BLOCKING</tt> phase, only (3) has to be
 * considered for blocking layer 1 modules triggered by events or time.
 * 
 * Each layer 1 module must only have one triggering condition that determines
 * the events or time for initiating the execution. Therefore, each layer 1
 * module has exactly and only one scheduler that uses this condition to
 * schedule the execution of this module. Each scheduler has its own thread of
 * control. Thus, in the <tt>BLOCKING</tt> phase, any scheduler thread is
 * blocked to initiate an execution of its layer 1 module.
 * 
 * The quiescence manager observes if all layer 1 modules triggered by events or
 * time have terminated execution. If this is the case, the quiescence state
 * moves from <tt>BLOCKING</tt> to <tt>QUIESCENT</tt>. In the <tt>QUIESCENT</tt>
 * any scheduler thread is further blocked to initiate an execution of its layer
 * 1 module until the quiescent state is set to <tt>OFF</tt>. In the state
 * <tt>OFF</tt> all blocks are removed and all schedulers may initiate the
 * execution of its individual module.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class QuiescenceManagerImpl implements QuiescenceManager,
		ExecutionPermissionManager {

	/**
	 * Logging.
	 */
	private static Logger logger = LogManager
			.getLogger(QuiescenceManagerImpl.class);

	/**
	 * The current state of quiescence.
	 */
	private QuiescenceState quiescenceState;

	/**
	 * List of currently executing modules. The modules are the keys of the map,
	 * the executing threads are the values of the map.
	 */
	private Map<ModuleExecutable<?, ?, ?, ?>, Thread> executingModules;

	/**
	 * List of listeners to be notified about changes in the quiescent state.
	 */
	private List<QuiescenceListener> listeners;

	/**
	 * Constructor.
	 */
	QuiescenceManagerImpl() {
		this.quiescenceState = QuiescenceState.OFF;
		this.listeners = new LinkedList<>();
		this.executingModules = new HashMap<ModuleExecutable<?, ?, ?, ?>, Thread>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void waitForExecutionPermission(
			ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		// the current thread, that is, the scheduler thread
		Thread currentSchedulerThread = Thread.currentThread();
		synchronized (this) {
			// assert that the module is not part of the list of currently
			// executing modules.
			assert !this.executingModules.containsKey(moduleExecutable);

			// check whether the scheduler thread is allowed to execute the
			// module with respect to quiescence: as long as the quiescent state
			// is BLOCKED or QUIESCENT, that is, not OFF, execution should be
			// blocked.
			while (this.getCurrentState() != QuiescenceState.OFF) {
				try {
					wait();
				} catch (InterruptedException e) {

				}
			}
			// quiescent state is OFF

			// the scheduler thread is allowed to execute the module; add
			// the module to this list of executing modules
			this.executingModules.put(moduleExecutable, currentSchedulerThread);
			// the scheduler thread exits this method to run the module
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void releaseExecutionPermission(
			ModuleExecutable<?, ?, ?, ?> moduleExecutable) {
		Thread currentSchedulerThread = Thread.currentThread();
		synchronized (this) {
			// assert that the module is part of the list of currently
			// executing modules.
			assert this.executingModules.containsKey(moduleExecutable);

			// only the executing thread can release the permission
			Thread executingThread = this.executingModules
					.get(moduleExecutable);
			assert currentSchedulerThread.equals(executingThread);

			// release the permission and notify about the termination of the
			// module by removing it from the list of executing modules
			this.executingModules.remove(moduleExecutable);

			// check whether quiescence has been reached if the current state is
			// BLOCKING
			if (this.getCurrentState() == QuiescenceState.BLOCKING) {
				if (this.executingModules.size() == 0) {
					// no modules are running, quiescence has been reached, move
					// state from BLOCKING to QUIESCENT
					this.setCurrentState(QuiescenceState.QUIESCENT);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void activateQuiescence() {
		// from OFF to BLOCKING
		synchronized (this) {
			if (this.getCurrentState() == QuiescenceState.OFF) {
				this.setCurrentState(QuiescenceState.BLOCKING);
			} else {
				throw new QuiescenceManagmentException(
						"Cannot switch Quiescence state from "
								+ this.getCurrentState() + " to "
								+ QuiescenceState.BLOCKING);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deactivateQuiescence() {
		// from BLOCKING or QUIESCENT to OFF
		synchronized (this) {
			if (this.getCurrentState() != QuiescenceState.OFF) {
				this.setCurrentState(QuiescenceState.OFF);
				// notify waiting threads to resume execution
				notifyAll();
			} else {
				throw new QuiescenceManagmentException(
						"Cannot switch Quiescence state from "
								+ this.getCurrentState() + " to "
								+ QuiescenceState.OFF);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public QuiescenceState getCurrentState() {
		return this.quiescenceState;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addQuiescenceListener(QuiescenceListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeQuiescenceListener(QuiescenceListener listener) {
		this.listeners.remove(listener);
	}

	/**
	 * Sets the current quiescence state to <code>newState</code> and notifies
	 * all listeners for quiescent state changes.
	 * 
	 * @param newState
	 *            the new quiescence state
	 */
	private void setCurrentState(QuiescenceState newState) {
		// set state
		QuiescenceState oldState = this.getCurrentState();
		if (newState == oldState) {
			throw new QuiescenceManagmentException(
					"Cannot set quiescence state from " + oldState + " to "
							+ newState
							+ " because there is no change in the state.");
		}
		this.quiescenceState = newState;
		logger.debug("Quiescence state has changed from " + oldState + " to "
				+ newState);
		// notify listeners
		for (QuiescenceListener listener : this.listeners) {
			listener.notifyStateChange(oldState, newState);
		}
	}
}
