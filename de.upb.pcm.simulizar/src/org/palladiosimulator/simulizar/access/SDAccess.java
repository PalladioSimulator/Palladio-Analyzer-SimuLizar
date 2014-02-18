package org.palladiosimulator.simulizar.access;

import java.util.List;

import org.storydriven.storydiagrams.activities.Activity;

public class SDAccess extends AbstractModelAccess<List<Activity>> {

    public SDAccess(final ModelHelper modelHelper) {
        super(modelHelper);
    }

    @Override
    public List<Activity> getModel() {
        return this.getModelHelper().getSDMModels();
    }

    public boolean sdModelsExist() {
        return this.getModelHelper().sdmModelsExists();
    }

}
