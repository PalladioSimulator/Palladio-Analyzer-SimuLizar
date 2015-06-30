package de.mdelab.eurema.interpreter.execution.consistency;

/**
 * Representing the state to reach quiescence. If no quiescence is required, the
 * state is <code>OFF</code>. If the system should be quiescent, the state
 * changes to <code>BLOCKING</code>, that is, currently running modules may
 * terminate but other modules are not allowed to execute (they are blocked)
 * except if their executions are required to terminate the execution of already
 * running modules. If no modules are currently running, the state is
 * <code>QUIESCENT</code>, that is, the system can be safely reconfigured. After
 * a reconfiguration, the state should be <code>OFF</code> such that modules may
 * start execution again.
 * 
 * Quiescence is required for safely adjusting the architecture of the
 * self-adaptive software by means of the <i>Layer Diagram</i>. Such adjustments
 * are done by off-line adaptation.
 * 
 * @author thomas vogel
 * @version 0.02
 */
public enum QuiescenceState {

	/**
	 * No quiescence required.
	 */
	OFF("OFF"),
	/**
	 * Quiescence required, current executions finish, new executions are
	 * blocked.
	 */
	BLOCKING("BLOCKING"),
	/**
	 * Quiescence reached, no module is running.
	 */
	QUIESCENT("QUIESCENT");

	/**
	 * Event type as a String.
	 */
	private String type;

	/**
	 * Constructor.
	 * 
	 * @param type
	 *            event type as a String.
	 */
	private QuiescenceState(String type) {
		this.type = type;
	}

	/**
	 * Returns the event type as a String.
	 * 
	 * @return the event type as a String.
	 */
	public String getType() {
		return this.type;
	}

}
