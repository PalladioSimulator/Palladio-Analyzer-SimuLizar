package org.palladiosimulator.simulizar.reconfiguration;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * Interface for a component that is able to reconfigure the PCM model@runtime.
 * 
 * @author snowball
 * 
 */
public interface IReconfigurationEngine {

	/**
	 * Trigger a condition check of the model@runtime. Engines should check
	 * whether they should reconfigure.
	 * 
	 * @param monitoredElement
	 *            PCM model element for which a new sensor measurement arrived.
	 * @return <code>true</code> if the check was positive/matched,
	 *         <code>false</code> if it was negative/did not match
	 */
	public boolean runCheck(EList<ModelTransformation<?>> checks, EObject monitoredElement);

	/**
	 * Trigger a reconfiguration of the model@runtime. This method should only
	 * be called if the prior check was positive, i.e., engines should only do
	 * the reconfiguration if the prior check was positive.
	 * 
	 * @param monitoredElement
	 *            PCM model element for which a new sensor measurement arrived.
	 * @return <code>true</code> if the reconfiguration was executed,
	 *         <code>false</code> if it was not executed or did not succeed.
	 */
	public boolean runExecute(EList<ModelTransformation<?>> actions, EObject monitoredElement);

	public void setModelAccess(final IModelAccess modelAccess);

	public void setConfiguration(final SimuLizarWorkflowConfiguration configuration);

	/**
	 * Configures the reconfiguration framework in which <code>this</code> is
	 * used.
	 * 
	 * @param reconfigurator
	 *            The reconfiguration framework in which <code>this</code> is
	 *            used.
	 */
	public void setReconfigurator(Reconfigurator reconfigurator);

}