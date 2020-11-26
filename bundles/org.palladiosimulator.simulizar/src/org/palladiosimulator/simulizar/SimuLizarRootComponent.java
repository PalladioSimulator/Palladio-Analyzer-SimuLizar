package org.palladiosimulator.simulizar;

import javax.inject.Named;
import javax.inject.Singleton;

import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;
import org.palladiosimulator.simulizar.modules.SimuLizarRootModule;
import org.palladiosimulator.simulizar.modules.eclipse.EclipseIDECompositeModule;

import dagger.Component;
import de.uka.ipd.sdq.workflow.jobs.IJob;

/**
 * This interface constitutes the root element of a SimuLizar dependency graph. A new simulation can
 * be instantiated using the generated builder.
 * 
 * @see PCMStartInterpretationJob
 * 
 */
@Component(modules = { SimuLizarRootModule.class, EclipseIDECompositeModule.class })
@Singleton
public interface SimuLizarRootComponent {
    
    @Named(SimulizarConstants.ROOT_JOB_ID) IJob rootJob();

}
