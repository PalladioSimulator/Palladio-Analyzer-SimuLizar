package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.prm.PRMModel;

/**
 * Access class for pms model.
 * 
 * @author Joachim Meyer
 */
public class PRMAccess extends AbstractModelAccess<PRMModel> {
    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public PRMAccess(final ModelHelper modelHelper) {
        super(modelHelper);
    }

    /**
     * @return the prm model.
     */
    @Override
    public PRMModel getModel() {
        return this.getModelHelper().getGlobalPRMModel();
    }
}
