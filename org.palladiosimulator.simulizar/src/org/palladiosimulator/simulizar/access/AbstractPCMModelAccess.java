package org.palladiosimulator.simulizar.access;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.PCMModels;

/**
 * Abstract base class for model access classes.
 * 
 * @author Joachim Meyer
 * 
 */
public abstract class AbstractPCMModelAccess<ModelType extends EObject> extends AbstractModelAccess<ModelType> {
    protected static final Logger logger = Logger.getLogger(AbstractPCMModelAccess.class.getName());

    protected final InterpreterDefaultContext context;

    /**
     * Constructor
     * 
     * @param context
     *            the interpreter default context for this model access class
     * @param modelHelper
     *            the model helper
     */
    public AbstractPCMModelAccess(final InterpreterDefaultContext context, final ModelHelper modelHelper) {
        super(modelHelper);
        if (context == null) {
            throw new IllegalArgumentException("Context must be set!");
        }
        this.context = context;
    }

    /**
     * @return returns the context
     */
    public InterpreterDefaultContext getContext() {
        return this.context;
    }

    /**
     * @see org.palladiosimulator.simulizar.access.AbstractPCMModelAccess#getModel()
     */
    @Override
    public final ModelType getModel() {
        PCMModels models;
        if (this.context.getThread() != null) {
            models = this.getModelHelper().getLocalPCMModels(this.context.getThread());
        } else {
            models = this.getModelHelper().getGlobalPCMModels();
        }
        return this.getSpecificModel(models);
    }

    protected abstract ModelType getSpecificModel(PCMModels models);
}
