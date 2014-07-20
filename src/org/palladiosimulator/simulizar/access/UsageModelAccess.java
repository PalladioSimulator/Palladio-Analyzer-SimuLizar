package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

/**
 * Access class for usage model.
 * 
 * Note: This class is actually not a model access class, it needs to be an interpreter, to avoid
 * "instance of" for the workloads.
 * 
 * @author Joachim Meyer
 */
public class UsageModelAccess extends AbstractPCMModelAccess<UsageModel> {
    /**
     * Constructor.
     * 
     * @param context
     *            the interpreter default context.
     * @param modelHelper
     *            the model helper.
     */
    public UsageModelAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(context, modelHelper);
    }

    @Override
    protected UsageModel getSpecificModel(final PCMModels models) {
        return models.getUsageModel();
    }
}
