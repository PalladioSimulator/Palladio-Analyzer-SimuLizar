package org.palladiosimulator.simulizar.elasticity.launcher;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.palladiosimulator.simulizar.component.core.DaggerSimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent.Factory;
import org.palladiosimulator.simulizar.di.modules.component.extensions.RootComponentComponentFactoriesModule;
import org.palladiosimulator.simulizar.elasticity.jobs.SimuLizarElasticityAnalysisCompositeJob;
import org.palladiosimulator.simulizar.launcher.PCMInterpreterLauncher;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.workflow.jobs.IJob;

public class SimuLizarElasticityAnalysisLauncher extends PCMInterpreterLauncher {
	@Override
    protected IJob createWorkflowJob(final SimuComWorkflowConfiguration config, final ILaunch launch)
            throws CoreException {
        if (!(config instanceof SimuLizarWorkflowConfiguration)) {
            throw new IllegalArgumentException("SimuLizarWorkflowConfiguration expected for PCMInterpreterLauncher");
        }
        
        var root = DaggerSimuLizarRootComponent.factory().create((SimuLizarWorkflowConfiguration) config, 
                new RootComponentComponentFactoriesModule() {
            @Override
            public Factory providesRuntimeComponentFactory() {
                return DaggerElasticityRuntimeComponent.factory();
            }
            
        });

        return new SimuLizarElasticityAnalysisCompositeJob((SimuLizarWorkflowConfiguration) config);
    }
}
