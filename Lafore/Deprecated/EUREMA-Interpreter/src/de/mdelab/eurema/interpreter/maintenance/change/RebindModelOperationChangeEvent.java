package de.mdelab.eurema.interpreter.maintenance.change;

/**
 * Change event about the rebinding of a model operation to its implementation,
 * that is, the implementation of the operation has changed from one software
 * module to another.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class RebindModelOperationChangeEvent extends UpdateChangeEvent {

	/**
	 * The model operation whose implementation has changed.
	 */
	private eurema.ModelOperation modelOperation;

	/**
	 * The old implementation of the model operation.
	 */
	private eurema.SoftwareModule oldImplementation;

	/**
	 * The new implementation of the model operation.
	 */
	private eurema.SoftwareModule newImplementation;

	/**
	 * Constructor.
	 * 
	 * @param modelOperation
	 *            The model operation whose implementation has changed.
	 * @param oldImplementation
	 *            The old implementation of the model operation.
	 * @param newImplementation
	 *            The new implementation of the model operation.
	 */
	public RebindModelOperationChangeEvent(
			eurema.ModelOperation modelOperation,
			eurema.SoftwareModule oldImplementation,
			eurema.SoftwareModule newImplementation) {
		super();
		this.modelOperation = modelOperation;
		this.oldImplementation = oldImplementation;
		this.newImplementation = newImplementation;
	}

	/**
	 * @return The model operation whose implementation has changed.
	 */
	public eurema.ModelOperation getModelOperation() {
		return this.modelOperation;
	}

	/**
	 * @return The old implementation of the model operation.
	 */
	public eurema.SoftwareModule getOldImplementation() {
		return this.oldImplementation;
	}

	/**
	 * @return The new implementation of the model operation.
	 */
	public eurema.SoftwareModule getNewImplementation() {
		return this.newImplementation;
	}

}
