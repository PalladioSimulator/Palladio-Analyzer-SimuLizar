package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.utils.PCMModels;

public class GlobalPCMAccess extends AbstractModelAccess<PCMModels> {

    public GlobalPCMAccess(final ModelHelper modelHelper) {
        super(modelHelper);
    }

    @Override
    public PCMModels getModel() {
        return this.getModelHelper().getGlobalPCMModels();
    }
}
