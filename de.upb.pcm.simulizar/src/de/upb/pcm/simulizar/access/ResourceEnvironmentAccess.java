package de.upb.pcm.simulizar.access;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.upb.pcm.simulizar.interpreter.InterpreterDefaultContext;
import de.upb.pcm.simulizar.utils.PCMModels;

/**
 * Access class for resource environment model.
 * 
 * @author Joachim Meyer
 */
public class ResourceEnvironmentAccess extends
		AbstractPCMModelAccess<ResourceEnvironment> {

	/**
	 * Constructor
	 * 
	 * @param context
	 *            the interpreter default context.
	 * @param modelHelper
	 *            the model helper.
	 */
	public ResourceEnvironmentAccess(final InterpreterDefaultContext context,
			final ModelHelper modelHelper) {
		super(context, modelHelper);
	}

	@Override
	protected ResourceEnvironment getSpecificModel(PCMModels models) {
		return models.getResourceEnvironment();
	}

}
