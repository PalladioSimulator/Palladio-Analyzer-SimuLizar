package org.palladiosimulator.simulizar.test.commons.util;

import static org.junit.platform.commons.support.AnnotationSupport.findRepeatableAnnotations;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.di.component.dependency.SimEngineComponent.Factory;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.di.modules.stateless.core.RootComponentFactoriesModule;
import org.palladiosimulator.simulizar.di.modules.stateless.mdsd.MDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.test.commons.annotation.UseSimuLizarExtension;
import org.palladiosimulator.simulizar.test.commons.di.components.DaggerTestSimEngineComponent;
import org.palladiosimulator.simulizar.test.commons.di.components.DaggerTestSimuLizarRootComponent;
import org.palladiosimulator.simulizar.test.commons.di.components.TestSimuLizarRootComponent.TestConfigurationModule;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestExtensionCommons;

import com.google.common.collect.ImmutableSet;

import de.uka.ipd.sdq.workflow.configuration.IJobConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class RunSimuLizarSimulationJobSupplier implements Supplier<IJob> {
    protected final SimuLizarWorkflowConfiguration configuration;
    protected final MDSDBlackboard blackboard;
    private List<UseSimuLizarExtension> extensions;

    public RunSimuLizarSimulationJobSupplier(ExtensionContext context) {
        configuration = SimuLizarTestExtensionCommons.getObjectFromStore(context, IJobConfiguration.class)
            .filter(SimuLizarWorkflowConfiguration.class::isInstance)
            .map(SimuLizarWorkflowConfiguration.class::cast)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Configuration present repository initialized. Please make sure to annotate your test accordingly."));
        blackboard = SimuLizarTestExtensionCommons.getObjectFromStore(context, MDSDBlackboard.class)
            .orElseThrow(() -> new IllegalArgumentException(
                    "No SimuLizar Blackboard initialized. Please make sure to annotate your test accordingly."));
        extensions = findRepeatableAnnotations(context.getElement(), UseSimuLizarExtension.class);
    }

    @Override
    public IJob get() {
        var extensionFactories = new HashSet<ExtensionComponent.Factory>();
        for (var extension : extensions) {
            var extCls = extension.value();
            try {
                var factory = (ExtensionComponent.Factory) extCls.getMethod("factory").invoke(null);
                extensionFactories.add(factory);
                
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                throw new RuntimeException(
                        "Could not create extension factory. Make sure to reference the Dagger generated component class");
            }
        }
        var component = DaggerTestSimuLizarRootComponent.factory()
            .create(configuration, 
                    new RootComponentFactoriesModule() {
                        @Override
                        public Factory providesSimEngineComponentFactory() {
                            return DaggerTestSimEngineComponent.factory();
                        }
                    }, 
                    new ExtensionComponentsModule(extensionFactories, ImmutableSet.of()), 
                    new MDSDBlackboardProvidingModule(blackboard),
                    new TestConfigurationModule() {
                        @Override
                        public boolean activateModelLoading() {
                            return false;
                        }
                    });

        return component.rootJob();
    }

}
