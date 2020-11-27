package org.palladiosimulator.simulizar.extension.adapter;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.simulizar.extension.AbstractSimuLizarExtension;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.extension.facets.ModelCompletion;
import org.palladiosimulator.simulizar.extension.facets.ModelLoad;
import org.palladiosimulator.simulizar.launcher.IConfigurator;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.runtimestate.IRuntimeStateAccessor;

import com.google.common.collect.ImmutableSet;

import de.uka.ipd.sdq.workflow.extension.AbstractWorkflowExtensionJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class EclipseExtensionPointExtensionAdapter extends AbstractSimuLizarExtension {

    private ModelLoadJobAdapterFactory loadJobAdapterFactory;

    @Inject
    public EclipseExtensionPointExtensionAdapter(ModelLoadJobAdapterFactory loadJobAdapterFactory) {
        this.loadJobAdapterFactory = loadJobAdapterFactory;
    }

    @Override
    public Set<ModelLoad.Factory> getModelLoaders() {
        var result = new HashSet<ModelLoad.Factory>();
        final Iterable<AbstractWorkflowExtensionJob<MDSDBlackboard>> loadJobs = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE,
                SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE);
        for (final AbstractWorkflowExtensionJob<MDSDBlackboard> loadJob : loadJobs) {
            result.add(
                    comp -> loadJobAdapterFactory.create(loadJob,
                            Optional.ofNullable(ExtensionHelper.getExecutableExtension(
                                    SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_ID,
                                    SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_CONFIG_BUILDER_ATTRIBUTE,
                                    SimulizarConstants.MODEL_LOAD_EXTENSION_POINT_JOB_ATTRIBUTE, loadJob.getClass()
                                        .getName()))));

        }
        return ImmutableSet.copyOf(result);
    }
    
    @Override
    public Set<ModelCompletion.Factory> getModelCompletions() {
        var result = new HashSet<ModelCompletion.Factory>();
        
        final List<IConfigurator> configurators = ExtensionHelper.getExecutableExtensions(
                SimulizarConstants.CONFIGURATOR_EXTENSION_POINT_ID,
                SimulizarConstants.CONFIGURATOR_EXTENSION_POINT_ATTRIBUTE);

        for (final IConfigurator configurator : configurators) {
            result.add(comp -> new IConfiguratorAdapter(configurator, comp.configuration(), comp.blackboard()));
        }
        
        return ImmutableSet.copyOf(result);
    }
    
    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.InterpreterExtension.Factory> getInterpreterExtensions() {
        var result = new HashSet<InterpreterExtension.Factory>();

        final Iterable<IRuntimeStateAccessor> stateAccessors = ExtensionHelper.getExecutableExtensions(
            SimulizarConstants.RUNTIME_STATE_ACCESS_EXTENSION_POINT_ID,
            SimulizarConstants.RUNTIME_STATE_ACCESS_EXTENSION_POINT_ACCESSOR_ATTRIBUTE);

        for (var accessor : stateAccessors) {
            result.add(simComp -> new RuntimeStateAccessorAdapter(accessor, simComp.runtimeState()));
        }
        
        return ImmutableSet.copyOf(result);
    }

}
