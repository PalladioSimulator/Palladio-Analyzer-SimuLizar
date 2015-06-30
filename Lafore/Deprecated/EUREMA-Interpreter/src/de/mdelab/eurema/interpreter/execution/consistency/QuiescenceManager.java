package de.mdelab.eurema.interpreter.execution.consistency;

/**
 * Ensures the consistent execution of modules in the context of <i>Layer
 * Diagram</> changes due to off-line adaptation (maintenance) enacted through
 * the {@code MaintenanceManager}
 * 
 * <b>Quiescence.</b> To support dynamic changes of the self-adaptive software
 * through off-line adaptation, the software's architecture as specified by an
 * EUREMA <i>Layer Diagram (LD)</i> has to be modified. Modules can be adapted
 * or added/removed while the affected modules or other modules are running.
 * This requires a quiescent state for safe adaptation. The quiescence manager
 * realizes a quiescent state by letting already running modules terminate while
 * properly blocking the start of new executions of modules.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface QuiescenceManager {

	/**
	 * Activates quiescence.
	 */
	public void activateQuiescence();

	/**
	 * Deactivates quiescence.
	 */
	public void deactivateQuiescence();

	/**
	 * Returns the current state of quiescence.
	 * 
	 * @return the current state of quiescence
	 */
	public QuiescenceState getCurrentState();

	/**
	 * Adds the listener to be notified about changes in the quiescence state.
	 * 
	 * @param listener
	 *            the listener to be added
	 */
	public void addQuiescenceListener(QuiescenceListener listener);

	/**
	 * Removes the listener to be notified about changes in the quiescence
	 * state.
	 * 
	 * @param listener
	 *            the listener to be removed
	 */
	public void removeQuiescenceListener(QuiescenceListener listener);
}
