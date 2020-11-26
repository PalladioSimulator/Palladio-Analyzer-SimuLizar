package org.palladiosimulator.simulizar.modules;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarModelCompletionComponent;
import org.palladiosimulator.simulizar.SimuLizarModelLoadComponent;
import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.extension.SimuLizarExtension;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.jobs.LoadSimuLizarModelsIntoBlackboardJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMInterpreterRootCompositeJob;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.modules.core.ExtensionFactoriesModule;
import org.palladiosimulator.simulizar.modules.custom.CustomMDSDBlackboardProvidingModule;
import org.palladiosimulator.simulizar.modules.custom.CustomSimuLizarExtensionsProvidingModule;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@Module(subcomponents = { SimuLizarModelLoadComponent.class, SimuLizarModelCompletionComponent.class,
        SimuLizarSimulationComponent.class }, includes = { CustomSimuLizarExtensionsProvidingModule.class,
                CustomMDSDBlackboardProvidingModule.class, SimuLizarConfigurationModule.class,
                ExtensionFactoriesModule.class })
public interface SimuLizarRootModule {

    @Binds
    @Named(SimulizarConstants.ROOT_JOB_ID)
    IJob bindRootJob(PCMInterpreterRootCompositeJob impl);

    @Binds
    @Named(SimulizarConstants.MODEL_LOAD_JOB_ID)
    IJob bindModelLoadJob(LoadSimuLizarModelsIntoBlackboardJob impl);

    @Binds
    @Named(SimulizarConstants.INTERPRETER_JOB_ID)
    IJob bindInterpretationJob(PCMStartInterpretationJob impl);

    @Provides
    @Singleton
    public static Set<SimuLizarExtension> provideExtensions(Set<SimuLizarExtension.Builder<?>> builders) {
        return Collections.unmodifiableSet(builders.stream()
            .map(SimuLizarExtension.Builder::build)
            .collect(Collectors.toSet()));
    }

    @SuppressWarnings("unchecked")
    @Provides
    @Singleton
    public static Map<Class<SimuLizarExtension>, SimuLizarExtension> provideExtensionMap(
            Set<SimuLizarExtension> extensions) {
        return Collections.unmodifiableMap(extensions.stream()
            .collect(Collectors.toMap(ext -> (Class<SimuLizarExtension>) ext.getClass(),
                    ext -> (SimuLizarExtension) ext)));
    }

}
