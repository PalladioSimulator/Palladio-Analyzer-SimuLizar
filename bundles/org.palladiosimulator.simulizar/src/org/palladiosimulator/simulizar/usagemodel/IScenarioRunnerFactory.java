package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.simulizar.entity.EntityReference;

import de.uka.ipd.sdq.simucomframework.core.usage.IScenarioRunner;

public interface IScenarioRunnerFactory<T extends Entity> {
    IScenarioRunner createScenarioRunner(EntityReference<? extends T> reference);

}
