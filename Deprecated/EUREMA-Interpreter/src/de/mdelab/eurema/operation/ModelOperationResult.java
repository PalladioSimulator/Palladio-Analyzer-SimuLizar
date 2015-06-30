package de.mdelab.eurema.operation;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Result of executing a model operation implementation.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public class ModelOperationResult {

	/**
	 * The return status of executing the operation implementation. This status
	 * is the name of model operation's exit compartment that should be taken.
	 */
	private String returnStatus;

	/**
	 * The output models of executing the operation implementation.
	 */
	private List<Resource> resources;

	/**
	 * Default constructor.
	 */
	public ModelOperationResult() {

	}

	/**
	 * Constructor.
	 * 
	 * @param returnStatus
	 *            return status of executing the model operation implementation.
	 *            This status is the name of model operation's exit compartment
	 *            that should be taken.
	 * @param resources
	 *            the list of output models
	 */
	public ModelOperationResult(String returnStatus, List<Resource> resources) {
		this.returnStatus = returnStatus;
		this.resources = resources;
	}

	/**
	 * Returns the return status of executing the model operation
	 * implementation. This status is the name of model operation's exit
	 * compartment that should be taken.
	 * 
	 * @return the return status of executing the model operation
	 *         implementation.
	 */
	public String getReturnStatus() {
		return this.returnStatus;
	}

	/**
	 * Sets the return status of executing the model operation implementation.
	 * This status is the name of model operation's exit compartment that should
	 * be taken.
	 * 
	 * @param returnStatus
	 *            the return status to be set
	 */
	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	/**
	 * Returns the list of models specified as the model operation's output.
	 * 
	 * @return the list of output models.
	 */
	public List<Resource> getResources() {
		return this.resources;
	}

	/**
	 * Sets the list of models specified as the model operation's output.
	 * 
	 * @param resources
	 *            the list of output models.
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

}
