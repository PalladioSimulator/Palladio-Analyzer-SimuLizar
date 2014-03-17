package org.palladiosimulator.simulizar.ui.configuration;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import de.uka.ipd.sdq.codegen.simucontroller.runconfig.FeatureOptionsTab;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuComConfigurationTab;
import de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuConfigurationTab;
import de.uka.ipd.sdq.codegen.simucontroller.workflow.jobs.WorkflowHooks;
import de.uka.ipd.sdq.workflow.launchconfig.extension.ExtendableTabGroup;
import de.uka.ipd.sdq.workflow.launchconfig.tabs.DebugEnabledCommonTab;

/**
 * Configuration tabs for SimuLizar, only the first one is customized. 
 * The rest is taken from SimuCom launch configuration.
 * 
 * @author Joachim Meyer
 */
public class InterpreterConfigurationTab extends ExtendableTabGroup {

	/**
	 * @see de.uka.ipd.sdq.codegen.simucontroller.runconfig.SimuTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
	 *      java.lang.String)
	 */
	@Override
	public void createTabs(final ILaunchConfigurationDialog dialog,
			final String mode) {
		List<ILaunchConfigurationTab> tabs = new ArrayList<ILaunchConfigurationTab>();
        ILaunchConfigurationTab commonTab = new DebugEnabledCommonTab();
		tabs.add(new InterpreterFileNamesInputTab());
        tabs.add(new SimuComConfigurationTab());
        tabs.add(new SimuConfigurationTab());
        for (String workflowExtensionPointId : WorkflowHooks.getAllWorkflowHookIDs()) {
            tabs.addAll(createExtensionTabs(dialog, mode, workflowExtensionPointId));
        }
        tabs.add(new FeatureOptionsTab());
		tabs.add(commonTab);
        setTabs(tabs.toArray(new ILaunchConfigurationTab[] {}));
	}
}
