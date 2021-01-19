package org.palladiosimulator.simulizar.component.core;

import org.palladiosimulator.simulizar.modules.component.eclipse.EclipseSimEngineModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.Component;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

@Component(modules = { EclipseSimEngineModule.class })
@AnalysisDependencyScope
public interface SimEngineComponent {

    ISimEngineFactory simEngineFactory();
    
    ISimEventFactory simEventFactory();
    
    @Component.Factory
    public interface Factory {
        SimEngineComponent create();
    }

}
