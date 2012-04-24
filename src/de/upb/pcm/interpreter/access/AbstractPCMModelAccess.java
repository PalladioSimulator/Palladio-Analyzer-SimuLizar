package de.upb.pcm.interpreter.access;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.PCMModels;

/**
 * Abstract base class for model access classes.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class AbstractPCMModelAccess<ModelType extends EObject> extends
		AbstractModelAccess<ModelType> {
	protected static final Logger logger = Logger
			.getLogger(AbstractPCMModelAccess.class.getName());

	protected final InterpreterDefaultContext context;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the interpreter default context for this model access class
	 * @param modelHelper
	 *            the model helper
	 */
	public AbstractPCMModelAccess(final InterpreterDefaultContext context,
			final ModelHelper modelHelper) {
		super(modelHelper);
		if (context == null)
			throw new IllegalArgumentException("Context must be set!");
		this.context = context;
	}

	/**
	 * @return returns the context
	 */
	public InterpreterDefaultContext getContext() {
		return this.context;
	}

	/**
	 * @see de.upb.pcm.interpreter.access.AbstractPCMModelAccess#getModel()
	 */
	@Override
	public final ModelType getModel() {
		PCMModels models;
		if (this.context.getThread() != null) {
			models = getModelHelper().getLocalPCMModels(this.context.getThread());
		} else {
			models = getModelHelper().getGlobalPCMModels();
		}
		return getSpecificModel(models);
	}

	protected abstract ModelType getSpecificModel(PCMModels models);
}
