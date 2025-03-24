package org.palladiosimulator.simulizar.di.component.dependency;

import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseSimEngineModule;
import org.palladiosimulator.simulizar.di.scopes.AnalysisDependencyScope;

import dagger.Component;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

@Component(modules = { EclipseSimEngineModule.class })
@AnalysisDependencyScope
public interface SimEngineComponent {

    ISimEngineFactory simEngineFactory();
    
    ISimEventFactory simEventFactory();
    
    @Component.Factory
    public interface Factory extends ExtensionComponent.Factory {
        SimEngineComponent create();
    }

}
