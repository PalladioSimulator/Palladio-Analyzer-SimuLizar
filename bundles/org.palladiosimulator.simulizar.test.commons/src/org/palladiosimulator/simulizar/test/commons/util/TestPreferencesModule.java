package org.palladiosimulator.simulizar.test.commons.util;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;
import de.uka.ipd.sdq.simulation.abstractsimengine.desmoj.DesmoJSimEngineFactory;

@Module
public interface TestPreferencesModule {
    
    @Binds ISimEngineFactory bindSimEngineFactory(DesmoJSimEngineFactory impl);
    
    @Provides static DesmoJSimEngineFactory provideDesmoJSimEngineFactory() {
        return new DesmoJSimEngineFactory();
    }
    
}
