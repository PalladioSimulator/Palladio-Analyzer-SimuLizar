package org.palladiosimulator.simulizar.di.modules.component.core;

import dagger.Binds;
import dagger.Module;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEventFactory;

@Module
public interface SimEngineModule {
    
    @Binds
    ISimEventFactory bindSimEventFactory(ISimEngineFactory engineFactory);
    
}
