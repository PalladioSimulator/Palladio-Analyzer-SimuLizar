package org.palladiosimulator.simulizar.launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Level;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.palladiosimulator.analyzer.workflow.core.configurations.PCMWorkflowConfigurationBuilder;
import org.palladiosimulator.simulizar.SimuLizarPlatform;
import org.palladiosimulator.simulizar.runconfig.SimuLizarLaunchConfigurationBasedConfigBuilder;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowConfiguration;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComWorkflowLauncher;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.launchconfig.core.configbuilder.AbstractWorkflowConfigurationBuilder;
import de.uka.ipd.sdq.workflow.logging.console.LoggerAppenderStruct;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Factory for the job for launching the pcm interpreter.
 *
 * @author Joachim Meyer, Matthias Becker
 *
 */
public class PCMInterpreterLauncher extends SimuComWorkflowLauncher {

    private Optional<MDSDBlackboard> blackboard = Optional.empty();

    @Override
    protected MDSDBlackboard createBlackboard() {
        return blackboard.orElseGet(super::createBlackboard);
    }

    @Override
    protected IJob createWorkflowJob(final SimuComWorkflowConfiguration config, final ILaunch launch)
            throws CoreException {
        if (!(config instanceof SimuLizarWorkflowConfiguration)) {
            throw new IllegalArgumentException("SimuLizarWorkflowConfiguration expected for PCMInterpreterLauncher");
        }

        var rootComponent = SimuLizarPlatform.getPlatformComponent()
            .analysisFactory()
            .create((SimuLizarWorkflowConfiguration) config);

        blackboard = Optional.of(rootComponent.blackboard());
        return rootComponent.rootJob();
    }

    @Override
    protected List<LoggerAppenderStruct> setupLogging(final org.apache.log4j.Level logLevel) throws CoreException {
        final List<LoggerAppenderStruct> result = new ArrayList<>(super.setupLogging(logLevel));
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
