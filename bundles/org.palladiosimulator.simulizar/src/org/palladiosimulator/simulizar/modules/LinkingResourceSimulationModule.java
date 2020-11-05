package org.palladiosimulator.simulizar.modules;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.access.SimulatedLinkingResourceAccess;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;
import org.palladiosimulator.simulizar.interpreter.linking.impl.DefaultSimuLizarTransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.linking.impl.MiddlewareCompletionAwareDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.impl.NoDemandCalculator;
import org.palladiosimulator.simulizar.interpreter.linking.impl.ResourceEnvironmentObservingLegacyRouter;
import org.palladiosimulator.simulizar.interpreter.linking.impl.SimulatedLinkingResourceContainerTransmissionStrategy;
import org.palladiosimulator.simulizar.interpreter.linking.impl.StackFrameBytesizeAccumulatingDemandCalculator;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import dagger.Binds;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

@Module
public interface LinkingResourceSimulationModule {

    @Provides
    static ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> provideCalculator(
            SimuLizarWorkflowConfiguration config, Lazy<NoDemandCalculator> noDemand,
            Lazy<MiddlewareCompletionAwareDemandCalculator> middlewareAware,
            Lazy<StackFrameBytesizeAccumulatingDemandCalculator> stackAccumulating) {
        if (config.getSimulateFailures())
            return middlewareAware.get();
        else if (config.getSimulateThroughputOfLinkingResources())
            return stackAccumulating.get();
        else
            return noDemand.get();
    }

    @Binds
    @Reusable
    ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> bindLinkingResourceAccess(
            SimulatedLinkingResourceAccess impl);

    @Binds
    ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>> bindLinkingResourceRouter(
            ResourceEnvironmentObservingLegacyRouter impl);

    @Binds
    @Reusable
    ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext> bindSimulationStrategy(
            SimulatedLinkingResourceContainerTransmissionStrategy impl);

    @Binds
    @Reusable
    ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> bindTransmissionInterpreter(
            DefaultSimuLizarTransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>> impl);
}
