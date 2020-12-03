package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Subcomponent;

@Subcomponent
public interface SimuLizarRootExtensionComponent {
    
    SimuLizarWorkflowConfiguration configuration();

    @Subcomponent.Builder
    public interface Builder {
        SimuLizarRootExtensionComponent build();
    }
}
