package de.upb.pcm.interpreter.interpreter;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.upb.pcm.interpreter.access.AbstractPCMModelAccess;
import de.upb.pcm.interpreter.interfaces.IModelAccessFactory;
import de.upb.pcm.interpreter.interfaces.IPCMModelSwitch;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.utils.PCMInterpreterProbeSpecUtil;

/**
 * Abstract interpreter base class for a pcm model interpreter.
 * 
 * @author Joachim Meyer
 */
public abstract class AbstractPCMModelInterpreter<ModelType extends EObject> {
	protected static final Logger logger = Logger
			.getLogger(AbstractPCMModelInterpreter.class.getName());

	protected final PCMInterpreterProbeSpecUtil pcmInterpreterProbeSpecUtil;
	protected final IModelAccessFactory modelAccessFactory;
	protected final AbstractPCMModelAccess<ModelType> modelAccess;
	protected final InterpreterDefaultContext context;

	/**
	 * Constructor
	 * 
	 * @param contex
	 *            the interpreter default context for the pcm model interpreter,
	 *            may be null.
	 * @param modelHelper
	 *            the model helper.
	 */
	public AbstractPCMModelInterpreter(
			final IModelAccessFactory modelAccessFactory,
			final InterpreterDefaultContext context) {
		super();
		this.context = context;
		this.modelAccessFactory = modelAccessFactory;
		this.modelAccess = createModelAccess(modelAccessFactory, context);
		this.pcmInterpreterProbeSpecUtil = new PCMInterpreterProbeSpecUtil(
				context.getModel());
	}

	protected abstract AbstractPCMModelAccess<ModelType> createModelAccess(
			IModelAccessFactory modelAccessFactory,
			InterpreterDefaultContext context);

	/**
	 * Gets the pcm model switch for the corresponding pcm model of this pcm
	 * model interpreter.
	 * 
	 * @param <T>
	 *            return type of switch methods.
	 * @return the model switch for this interpreter or null if none exist.
	 */
	protected abstract <T> IPCMModelSwitch<T> getModelSwitch();

	/**
	 * Start interpretation at given model element.
	 * 
	 * @param startElement
	 *            the model element at which the interpretation should be
	 *            started.
	 * @param o
	 *            arbitrary optional parameters.
	 */
	public void interpret(final EObject startElement, final Object... o) {
		this.startInterpretation(startElement, o);
	}

	/**
	 * Template method to start interpretation at given model element.
	 * 
	 * @param startElement
	 *            the model element at which the interpretation should be
	 *            started.
	 * @param o
	 *            arbitrary optional parameters.
	 */
	protected abstract void startInterpretation(EObject startElement,
			Object... o);

	protected AbstractPCMModelAccess<ModelType> getModelAccess() {
		return this.modelAccess;
	}
}
