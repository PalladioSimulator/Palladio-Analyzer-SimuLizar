/**
 * 
 */
package org.palladiosimulator.simulizar.access;

import org.apache.log4j.Logger;

/**
 * Abstract base class for model access classes.
 * 
 * @author Joachim Meyer, Steffen Becker
 * 
 */
abstract class AbstractModelAccess<ModelType> {
    protected static final Logger logger = Logger.getLogger(AbstractModelAccess.class.getName());

    private final ModelHelper modelHelper;

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public AbstractModelAccess(final ModelHelper modelHelper) {
        this.modelHelper = modelHelper;
    }

    /**
     * @return returns the modelHelper.
     */
    protected ModelHelper getModelHelper() {
        return this.modelHelper;
    }

    /**
     * Gets the model to be accessed by this class. If sim process is set, this method return a
     * local copy of the corresponding model, otherwise the global model.
     * 
     * @return returns the model to be accessed by this class.
     */
    public abstract ModelType getModel();
}
