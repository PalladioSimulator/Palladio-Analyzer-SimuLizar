package org.palladiosimulator.simulizar.test.commons.util;

import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent.Factory;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RootComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.di.components.DaggerTestSimEngineComponent;
import org.palladiosimulator.simulizar.test.commons.di.components.DaggerTestSimuLizarRootComponent;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier implements Supplier<IJob> {
    protected final SimuLizarWorkflowConfiguration configuration;
    protected final MDSDBlackboard blackboard;

    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJobConfiguration.class)
            .filter(SimuLizarWorkflowConfiguration.class::isInstance)
            .map(SimuLizarWorkflowConfiguration.class::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
        blackboard = SimuLizarTestExtensionCommons.getObjectFromStore(context, MDSDBlackboard.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Blackboard initialized. Please make sure to annotate your test accordingly."));
    }

    @Override
    public IJob get() {
        var component = DaggerTestSimuLizarRootComponent.factory()
            .create(configuration, 
                    new RootComponentFactoriesModule() {
                        @Override
                        public Factory providesSimEngineComponentFactory() {
                            return DaggerTestSimEngineComponent.factory();
                        }
                    }, 
                    new ExtensionComponentsModule(), 
                    new MDSDBlackboardProvidingModule(blackboard));

        return component.runtimeComponentFactory().create().runtimeJob();
    }

}
