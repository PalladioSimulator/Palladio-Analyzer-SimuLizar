package org.palladiosimulator.simulizar.runconfig;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComExtensionConfigurationBuilder;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComLaunchConfigurationBasedConfigBuilder;
import de.uka.ipd.sdq.codegen.simucontroller.workflow.jobs.WorkflowHooks;
import de.uka.ipd.sdq.simucomframework.SimuComConfig;
import de.uka.ipd.sdq.simucomframework.SimuComConfigExtension;
import de.uka.ipd.sdq.workflow.extension.ExtensionHelper;
import de.uka.ipd.sdq.workflow.extension.WorkflowExtension;
import de.uka.ipd.sdq.workflow.launchconfig.AbstractWorkflowBasedRunConfiguration;
import de.uka.ipd.sdq.workflow.pcm.ConstantsContainer;

public class SimuLizarLaunchConfigurationBasedConfigBuilder extends SimuComLaunchConfigurationBasedConfigBuilder {

    public SimuLizarLaunchConfigurationBasedConfigBuilder(ILaunchConfiguration configuration, String mode)
            throws CoreException {
        super(configuration, mode);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void fillConfiguration(AbstractWorkflowBasedRunConfiguration configuration) throws CoreException {
        SimuLizarWorkflowConfiguration config = (SimuLizarWorkflowConfiguration) configuration;
        config.setSimulateFailures(getBooleanAttribute(ConstantsContainer.SIMULATE_FAILURES));

        // accuracy analysis
        config.setAccuracyInfluenceAnalysisEnabled(getBooleanAttribute(ConstantsContainer.ANALYSE_ACCURACY));
        config.setAccuracyInformationModelFile(getStringAttribute(ConstantsContainer.ACCURACY_QUALITY_ANNOTATION_FILE));

        config.setPmsFile(getStringAttribute(SimulizarConstants.PMS_FILE));
        config.setReconfigurationRulesFolder(getStringAttribute(SimulizarConstants.RECONFIGURATION_RULES_FOLDER));

        SimuComConfig simuComConfig = new SimuComConfig(properties, config.isDebug());

        // Set SimuCom config extensions based on registered extensions
        for (String workflowHookId : WorkflowHooks.getAllWorkflowHookIDs()) {
            for (WorkflowExtension<?> workflowExtension : ExtensionHelper.getWorkflowExtensions(workflowHookId)) {
                if ((workflowExtension.getExtensionConfigurationBuilder() != null)
                        && (workflowExtension.getExtensionConfigurationBuilder() instanceof SimuComExtensionConfigurationBuilder)) {
                    SimuComConfigExtension simuComConfigExtension = ((SimuComExtensionConfigurationBuilder) workflowExtension
                            .getExtensionConfigurationBuilder()).deriveSimuComConfigExtension(properties);
                    if (simuComConfigExtension != null) {
                        simuComConfig.addSimuComConfigExtension(workflowExtension.getId(), simuComConfigExtension);
                    }
                }
            }
        }

        config.setSimuComConfiguration(simuComConfig);
    }

}
