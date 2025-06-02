package org.palladiosimulator.simulizar.core.entity;

import java.util.Iterator;

import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;

/**
 * This implementation provides a more specialized usage scenario lookup strategy. It is fully
 * compliant to the contract of {@link EntityReference}.
 * 
 * @see SimuLizarEntityReferenceFactories.UsageScenario
 */
public class UsageScenarioReference extends EntityReference<UsageScenario> {

    UsageScenarioReference(String id) {
        super(id, UsageScenario.class);
    }

    @Override
    protected Iterator<UsageScenario> retrieveModelElements(PCMResourceSetPartition partition) {
        return partition.getUsageModel()
            .getUsageScenario_UsageModel()
            .stream()
            .filter(it -> it.getId()
                .equals(getId()))
            .iterator();
    }

}
