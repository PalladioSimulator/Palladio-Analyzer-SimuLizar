package org.palladiosimulator.simulizar;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.modules.DefaultQUALModule;
import org.palladiosimulator.simulizar.modules.EclipseIDEPreferencesModule;
import org.palladiosimulator.simulizar.modules.SimuLizarCoreAggregateModule;

import dagger.Component;

/**
 * This interface constitutes the root element of a SimuLizar dependency graph. A new simulation can
 * be instantiated using the generated builder.
 * 
 * @see PCMStartInterpretationJob
 * 
 */
@Component(modules = { SimuLizarCoreAggregateModule.class, DefaultQUALModule.class, EclipseIDEPreferencesModule.class })
@Singleton
public interface SimuLizarComponent extends SimuLizarCoreComponent {

    /**
     * This interface declaration is required, as Dagger will not look into parent interfaces for
     * suitable builders.
     */
    @Component.Builder
    interface Builder extends SimuLizarCoreComponent.Builder {
    }

}
