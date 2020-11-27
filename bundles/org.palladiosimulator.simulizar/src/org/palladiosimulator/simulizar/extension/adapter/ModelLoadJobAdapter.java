package org.palladiosimulator.simulizar.extension.adapter;

import java.util.Optional;
import java.util.function.Consumer;

import org.palladiosimulator.simulizar.extension.facets.ModelLoad;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import com.google.auto.factory.AutoFactory;
import com.google.auto.factory.Provided;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionConfigurationBuilder;
import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@AutoFactory
public class ModelLoadJobAdapter implements ModelLoad {
    private final AbstractWorkflowExtensionJob<MDSDBlackboard> extensionJob;
    private final Optional<AbstractWorkflowExtensionConfigurationBuilder> configBuilder;
    private final SimuLizarWorkflowConfiguration workflowConfiguration;

    public ModelLoadJobAdapter(AbstractWorkflowExtensionJob<MDSDBlackboard> extensionJob,
            Optional<AbstractWorkflowExtensionConfigurationBuilder> configBuilder,
            @Provided SimuLizarWorkflowConfiguration workflowConfiguration) {
        this.extensionJob = extensionJob;
        this.configBuilder = configBuilder;
        this.workflowConfiguration = workflowConfiguration;
    }

    @Override
    public void appendModelLoadJobs(Consumer<IJob> modelLoadJob) {

        
        if (configBuilder.isPresent()) {
            extensionJob.setJobConfiguration(configBuilder.get().buildConfiguration(workflowConfiguration.getAttributes()));
        }
        modelLoadJob.accept(extensionJob);
        

        
    }

}
