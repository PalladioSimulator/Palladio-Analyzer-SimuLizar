package de.mdelab.eurema.interpreter.execution.consistency;

/**
 * Components who are interested in observing changes in the quiescence state
 * must implement this interface.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface QuiescenceListener {

	/**
	 * Notifies the listener that the quiescence state has changed from
	 * <code>oldState</code> to <code>newState</code>.
	 * 
	 * @param oldState
	 *            the old state
	 * @param newState
	 *            the new state
	 */
	public void notifyStateChange(QuiescenceState oldState,
			QuiescenceState newState);

}
