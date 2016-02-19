package de.mdelab.eurema.operation;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Interface that has to be implemented by realizations of
 * <code>ModelOperation</code>s used in <i>Feedback Loop Diagrams</i>. The
 * implementation must provide a default constructor.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface IModelOperation {

	/**
	 * Method that implements a model operation declared in a <i>Feedback Loop
	 * Diagram</i> (FLD).
	 * 
	 * @param models
	 *            The list of models used as input by the model operation as
	 *            specified in the FLD.
	 * @return The result of executing the model operation implementation.
	 */
	public ModelOperationResult run(List<Resource> models);
}
