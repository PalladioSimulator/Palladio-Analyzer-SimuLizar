package de.upb.pcm.interpreter.access;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.upb.pcm.interpreter.access.internal.ModelHelper;
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

	InterpreterDefaultContext context;

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
	 * @return returns the context, may be null
	 */
	public InterpreterDefaultContext getContext() {
		return this.context;
	}

	/**
	 * Gets the sim process for this model access class. Not every model access
	 * class is executed within a sim process. In this case, methods return
	 * null.
	 * 
	 * @return returns the simProcess, may be null
	 */
	public SimuComSimProcess getSimProcess() {
		if (this.context != null) {
			return this.context.getThread();
		}
		return null;
	}

	public void setContext(final InterpreterDefaultContext context) {
		this.context = context;
	}

	/**
	 * @see de.upb.pcm.interpreter.access.AbstractPCMModelAccess#getModel()
	 */
	@Override
	public final ModelType getModel() {
		PCMModels models;
		if (this.getSimProcess() != null) {
			models = getModelHelper().getLocalPCMModels(this.getSimProcess());
		} else {
			models = getModelHelper().getGlobalPCMModels();
		}
		return getSpecificModel(models);
	}

	protected abstract ModelType getSpecificModel(PCMModels models);
}
