package org.palladiosimulator.simulizar.di.component.dependency;

import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.core.scopes.AnalysisDependencyScope;
import org.palladiosimulator.simulizar.di.modules.component.eclipse.EclipseSimEngineModule;

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
