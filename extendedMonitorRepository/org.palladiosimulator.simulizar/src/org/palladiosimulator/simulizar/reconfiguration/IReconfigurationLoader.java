package org.palladiosimulator.simulizar.reconfiguration;

import java.util.List;

import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public interface IReconfigurationLoader {
    public void setConfiguration(SimuLizarWorkflowConfiguration configuration);

    public void setModelAccess(IModelAccess modelAccess);

    public List<ModelTransformation<? extends Object>> getTransformations();
}
