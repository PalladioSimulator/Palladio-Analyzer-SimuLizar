package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.PCMModels;

import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;

/**
 * Access class for resource environment model.
 * 
 * @author Joachim Meyer
 */
public class ResourceEnvironmentAccess extends AbstractPCMModelAccess<ResourceEnvironment> {

    /**
     * Constructor
     * 
     * @param context
     *            the interpreter default context.
     * @param modelHelper
     *            the model helper.
     */
    public ResourceEnvironmentAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(context, modelHelper);
    }

    @Override
    protected ResourceEnvironment getSpecificModel(final PCMModels models) {
        return models.getResourceEnvironment();
    }

}
