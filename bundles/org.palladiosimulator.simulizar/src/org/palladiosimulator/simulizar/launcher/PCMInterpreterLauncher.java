package org.palladiosimulator.simulizar.launcher;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.palladiosimulator.analyzer.workflow.configurations.PCMWorkflowConfigurationBuilder;
import org.palladiosimulator.simulizar.DaggerSimuLizarRootComponent;
import org.palladiosimulator.simulizar.modules.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.runconfig.SimuLizarLaunchConfigurationBasedConfigBuilder;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowLauncher;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowConfigurationBuilder;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.ui.UIBasedWorkflow;

/**
 * Factory for the job for launching the pcm interpreter.
 *
 * @author Joachim Meyer, Matthias Becker
 *
 */
public class PCMInterpreterLauncher extends SimuComWorkflowLauncher {
    
    @Override
    protected UIBasedWorkflow<MDSDBlackboard> createWorkflow(SimuComWorkflowConfiguration workflowConfiguration,
            IProgressMonitor monitor, ILaunch launch) throws CoreException {
        // TODO Auto-generated method stub
        return super.createWorkflow(workflowConfiguration, monitor, launch);
    }

    @Override
    protected IJob createWorkflowJob(final SimuComWorkflowConfiguration config, final ILaunch launch)
            throws CoreException {
        if (!(config instanceof SimuLizarWorkflowConfiguration)) {
            throw new IllegalArgumentException("SimuLizarWorkflowConfiguration expected for PCMInterpreterLauncher");
        }

        return DaggerSimuLizarRootComponent.builder()
            .simuLizarConfigurationModule(new SimuLizarConfigurationModule((SimuLizarWorkflowConfiguration) config))
            .build().rootJob();
    }

    @Override
    protected ArrayList<LoggerAppenderStruct> setupLogging(final org.apache.log4j.Level logLevel) throws CoreException {
        final ArrayList<LoggerAppenderStruct> result = super.setupLogging(logLevel);
        result.add(this.setupLogger("org.palladiosimulator.simulizar", logLevel,
                Level.DEBUG == logLevel ? DETAILED_LOG_PATTERN : SHORT_LOG_PATTERN));
        result.add(this.setupLogger("de.uka.ipd.sdq.probfunction.math.apache.impl", logLevel,
                Level.DEBUG == logLevel ? DETAILED_LOG_PATTERN : SHORT_LOG_PATTERN));

        return result;
    }

    @Override
    protected SimuLizarWorkflowConfiguration deriveConfiguration(final ILaunchConfiguration configuration,
            final String mode) throws CoreException {
        final SimuLizarWorkflowConfiguration config = new SimuLizarWorkflowConfiguration(configuration.getAttributes());

        AbstractWorkflowConfigurationBuilder builder;
        builder = new PCMWorkflowConfigurationBuilder(configuration, mode);
        builder.fillConfiguration(config);

        builder = new SimuLizarLaunchConfigurationBasedConfigBuilder(configuration, mode);
        builder.fillConfiguration(config);

        return config;
    }

}
