package org.palladiosimulator.simulizar.modules;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.runtimestate.SimulationPreferencesSimEngineFactoryProvider;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import dagger.multibindings.ElementsIntoSet;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimEngineFactory;

/**
 * This dagger module binds implementations which determine simulation behavior based on preferences
 * configured within Eclipse IDE.
 */
@Module
public interface EclipseIDEPreferencesModule {
    public static final String RDSEFFSWITCH_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.interpreter.rdseffswitch";
    public static final String RDSEFFSWITCH_EXTENSION_ATTRIBUTE = "rdseffswitch";

    @Provides
    static ISimEngineFactory provideSimEngineFactory(SimulationPreferencesSimEngineFactoryProvider impl) {
        return impl.get();
    }
    
    @Provides
    @ElementsIntoSet
    @Reusable
    static Set<AbstractRDSeffSwitchFactory> provideRDSeffSwitchFactoriesFromExtensionPoint() {
        final List<AbstractRDSeffSwitchFactory> switchFactories = ExtensionHelper
                .getExecutableExtensions(RDSEFFSWITCH_EXTENSION_POINT_ID, RDSEFFSWITCH_EXTENSION_ATTRIBUTE);
        return new HashSet<>(switchFactories);
    }
    

}
