package org.palladiosimulator.simulizar.runconfig;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComExtensionConfigurationBuilder;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComLaunchConfigurationBasedConfigBuilder;
import de.uka.ipd.sdq.codegen.simucontroller.workflow.jobs.WorkflowHooks;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.SimuComConfigExtension;
import de.uka.ipd.sdq.workflow.extension.ExtensionHelper;
import de.uka.ipd.sdq.workflow.extension.WorkflowExtension;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedRunConfiguration;

public class SimuLizarLaunchConfigurationBasedConfigBuilder extends SimuComLaunchConfigurationBasedConfigBuilder {

    public SimuLizarLaunchConfigurationBasedConfigBuilder(final ILaunchConfiguration configuration, final String mode)
            throws CoreException {
        super(configuration, mode);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fillConfiguration(final AbstractWorkflowBasedRunConfiguration configuration) throws CoreException {
        final SimuLizarWorkflowConfiguration config = (SimuLizarWorkflowConfiguration) configuration;
        config.setSimulateFailures(this.getBooleanAttribute(SimuComConfig.SIMULATE_FAILURES));

        // accuracy analysis
        config.setAccuracyInfluenceAnalysisEnabled(this.getBooleanAttribute(ConstantsContainer.ANALYSE_ACCURACY));
        config.setAccuracyInformationModelFile(
                this.getStringAttribute(ConstantsContainer.ACCURACY_QUALITY_ANNOTATION_FILE));

        config.setMonitorRepositoryFile(this.getStringAttribute(SimulizarConstants.MONITOR_REPOSITORY_FILE));
        config.setReconfigurationRulesFolder(this.getStringAttribute(SimulizarConstants.RECONFIGURATION_RULES_FOLDER));
        config.setServiceLevelObjectivesFile(
                this.getStringAttribute(SimulizarConstants.SERVICELEVELOBJECTIVEREPOSITORY_FILE));
        config.setUsageEvolutionFile(this.getStringAttribute(SimulizarConstants.USAGEEVOLUTION_FILE));

        final SimuComConfig simuComConfig = new SimuComConfig(this.properties, config.isDebug());

        // Set SimuCom config extensions based on registered extensions
        for (final String workflowHookId : WorkflowHooks.getAllWorkflowHookIDs()) {
            for (final WorkflowExtension<?> workflowExtension : ExtensionHelper.getWorkflowExtensions(workflowHookId)) {
                if ((workflowExtension.getExtensionConfigurationBuilder() != null) && (workflowExtension
                        .getExtensionConfigurationBuilder() instanceof SimuComExtensionConfigurationBuilder)) {
                    final SimuComConfigExtension simuComConfigExtension = ((SimuComExtensionConfigurationBuilder) workflowExtension
                            .getExtensionConfigurationBuilder()).deriveSimuComConfigExtension(this.properties);
                    if (simuComConfigExtension != null) {
                        simuComConfig.addSimuComConfigExtension(workflowExtension.getId(), simuComConfigExtension);
                    }
                }
            }
        }

        config.setSimuComConfiguration(simuComConfig);
    }
}
