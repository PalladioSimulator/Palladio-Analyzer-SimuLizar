package de.mdelab.eurema.interpreter.maintenance;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * Represents an update developed offline to adjust the running self-adaptive
 * software.
 * 
 * @author thomas vogel
 * @version 0.2
 * 
 */
public class Update {

	/**
	 * The name of the update.
	 */
	private String name;

	/**
	 * The adaptation rule changing the <i>Layer Diagram (LD)</i> to integrate
	 * the instance of the <code>fld</code> defining the update.
	 */
	private Resource integrationRule;

	/**
	 * The <i>Feedback Loop Diagram (FLD)</i> defining the update.
	 */
	private Resource fld;

	/**
	 * The runtime models used within the <code>fld</code>.
	 */
	private List<Resource> runtimeModels;

	/**
	 * Constructor to create an update.
	 * 
	 * @param name
	 *            The name of the update.
	 * @param integrationRule
	 *            The adaptation rule changing the <i>Layer Diagram (LD)</i> to
	 *            integrate the instance of the <code>fld</code> defining the
	 *            update.
	 * @param fld
	 *            The <i>Feedback Loop Diagram (FLD)</i> defining the update.
	 * @param runtimeModels
	 *            The runtime models used within the <code>fld</code>.
	 */
	public Update(String name, Resource integrationRule, Resource fld,
			List<Resource> runtimeModels) {
		super();
		this.integrationRule = integrationRule;
		this.fld = fld;
		this.runtimeModels = runtimeModels;
	}

	/**
	 * @return the integrationRule to integrate an instance of the
	 *         <code>fld</code> into the LD.
	 */
	public Resource getIntegrationRule() {
		return this.integrationRule;
	}

	/**
	 * @return the fld
	 */
	public Resource getFld() {
		return this.fld;
	}

	/**
	 * @return the runtimeModels used in the <code>fld</code>
	 */
	public List<Resource> getRuntimeModels() {
		return this.runtimeModels;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
