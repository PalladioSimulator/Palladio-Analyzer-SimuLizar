package de.mdelab.eurema.interpreter;

import org.palladiosimulator.simulizar.access.IModelAccess;

import strategies.RuntimeStrategiesModel;
import violations.RuntimeViolationsModel;

/**
 * Interface to use the EUREMA interpreter.
 * 
 * @author thomas vogel
 * @version 0.01
 * 
 */
public interface EuremaInterpreter {

	/**
	 * Executes an EUREMA model.
	 * 
	 * @param eArchitecture
	 *            the EUREMA architecture to be executed as defined by the
	 *            EUREMA model (<i>Layer Diagram</i> and <i>Feedback Loop
	 *            Diagrams</i>).
	 * @return The queue to which sensor events from the adaptable software have
	 *         to be added.
	 */

	public EventQueue execute(eurema.Architecture eArchitecture, IModelAccess access, RuntimeViolationsModel v,
			RuntimeStrategiesModel s);

	/**
	 * Executes an EUREMA model.
	 * 
	 * @param euremaArchitectureModelUri
	 *            the URI of the EUREMA architecture to be executed as defined
	 *            by the EUREMA model (<i>Layer Diagram</i> and <i>Feedback Loop
	 *            Diagrams</i>). The root object of the model with the specified
	 *            URI must be an instance of {@code eurema.Architecture}.
	 * @return The queue to which sensor events from the adaptable software have
	 *         to be added.
	 */
	public EventQueue execute(String euremaArchitectureModelUri, IModelAccess accesss, RuntimeViolationsModel v,
			RuntimeStrategiesModel s);

	public IModelAccess getModelAccess();

	public RuntimeViolationsModel getRuntimeViolationsModel();

	public RuntimeStrategiesModel getRuntimeStrategiesModel();

}
