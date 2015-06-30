package de.mdelab.eurema.interpreter.execution.consistency;

/**
 * Factory to obtain the quiescence manager and the execution permission
 * manager.
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public class ConsistencyManagementFactory {

	/**
	 * Instance of the Consistency Management Factory.
	 */
	public final static ConsistencyManagementFactory INSTANCE =
			new ConsistencyManagementFactory();

	/**
	 * The quiescence and execution permission manager
	 */
	private QuiescenceManagerImpl manager;

	/**
	 * Constructor.
	 */
	private ConsistencyManagementFactory() {
		this.manager = new QuiescenceManagerImpl();
	}

	/**
	 * Returns the quiescence manager.
	 * 
	 * @return the quiescence manager.
	 */
	public QuiescenceManager getQuiescenceManager() {
		return this.manager;
	}

	/**
	 * Returns the execution permission manager.
	 * 
	 * @return the execution permission manager.
	 */
	public ExecutionPermissionManager getExecutionPermissionManager() {
		return this.manager;
	}

}
