package org.palladiosimulator.simulizar.di.modules.scoped.thread;

import java.util.Set;

import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.RDSeffPerformanceSwitch;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitch;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory;
import org.palladiosimulator.simulizar.interpreter.impl.ExtensibleComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.legacy.NOPReliabilityInterpreter;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import com.google.common.collect.ImmutableSet;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

@Module
public interface CoreSimulatedThreadBindings {
    @Binds
    @SimulatedThreadScope
    ComposedRDSeffSwitchFactory bindComposedRDSeffSwitchFactory(ExtensibleComposedRDSeffSwitchFactory impl);

    @Provides
    @ElementsIntoSet
    static Set<RDSeffSwitchContributionFactory> provideCoreRDSeffSwitchFactories(
            RDSeffPerformanceSwitch.Factory performanceSwitchFactory, RDSeffSwitch.Factory rdseffSwitchFactory) {
        return ImmutableSet.of(rdseffSwitchFactory, performanceSwitchFactory);
    }
    
    @Provides
    @ElementsIntoSet
    static Set<RDSeffSwitchContributionFactory> provideNOPSwitches(
            NOPReliabilityInterpreter.Factory factory, SimuLizarWorkflowConfiguration config) {
        // The entire binding is only required to provide support to "deactivate" simulation of reliability elements in models which contain them.
        if (config.getSimulateFailures()) {
            //The reliability interpreter is provided by an extension not by the core
            return ImmutableSet.of();
        } else {
            return ImmutableSet.of(factory);
        }
    }

}
