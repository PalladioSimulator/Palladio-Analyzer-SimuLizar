package de.mdelab.eurema.interpreter.execution.module.executor.megamodel;

import java.util.List;

import de.mdelab.eurema.interpreter.condition.QueryExecutionInformation;

/**
 * The local execution context of an {@code eurema.MegamodelModule}.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface MegamodelModuleExecutionContext extends
		QueryExecutionInformation {

	/**
	 * Returns all {@code eurema.ExecutionInformation} for executable megamodel
	 * elements of the megamodel module to be executed.
	 * 
	 * @return all {@code eurema.ExecutionInformation} for executable megamodel
	 *         elements of the megamodel module to be executed.
	 */
	public List<eurema.ExecutionInformation> getExecutionInformation();

	/**
	 * Returns the megamodel module of this context.
	 * 
	 * @return the megamodel module of this context.
	 */
	public eurema.MegamodelModule getMegamodelModule();

}
