package org.palladiosimulator.simulizar.extension.facets;

import java.util.function.Consumer;

import org.palladiosimulator.simulizar.SimuLizarModelLoadComponent;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.Component;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@Component(dependencies = SimuLizarModelLoadComponent.class)
@ExtensionScope
public interface ModelLoad  {
    
    @Component.Factory
    interface Factory {
        ModelLoad create(SimuLizarModelLoadComponent component);
    }
    
    default void appendModelLoadJobs(Consumer<IJob> modelLoadJob) {
        
    }

}
