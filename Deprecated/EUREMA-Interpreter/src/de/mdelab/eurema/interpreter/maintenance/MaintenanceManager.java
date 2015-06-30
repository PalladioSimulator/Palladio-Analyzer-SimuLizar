package de.mdelab.eurema.interpreter.maintenance;

import java.util.List;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * 
 * @author thomas vogel
 * @version 0.02
 * 
 */
public interface MaintenanceManager {

	/**
	 * Loads up and executes an update developed offline by engineers. The
	 * execution is performed asynchronously, that is, this method returns after
	 * the upload but likely before the execution of the update.
	 * 
	 * @param updateName
	 *            an arbitrary name of the update
	 * @param integrationRule
	 *            The adaptation rule changing the <i>Layer Diagram (LD)</i> to
	 *            integrate the instance of the <code>fld</code> defining the
	 *            update.
	 * @param fld
	 *            The <i>Feedback Loop Diagram (FLD)</i> defining the update.
	 * @param runtimeModels
	 *            The runtime models used within the <code>fld</code>.
	 * @return <code>true</code> if the update has been accepted for execution,
	 *         else <code>false</code>. If <code>false</code> is returned, the
	 *         update must be loaded up again until eventually <code>true</code>
	 *         is returned.
	 */
	public boolean uploadAndExecuteUpdate(String updateName,
			Resource integrationRule, Resource fld, List<Resource> runtimeModels);

	/**
	 * Returns <code>true</code> if an update is still in progress, else
	 * <code>false</code>. At one point in time, only one update can be in
	 * progress. While one update is in progress, another update cannot be
	 * loaded up and executed.
	 * 
	 * @return <code>true</code> if an update is still in progress, else
	 *         <code>false</code>.
	 */
	public boolean isUpdateInProgress();

}
