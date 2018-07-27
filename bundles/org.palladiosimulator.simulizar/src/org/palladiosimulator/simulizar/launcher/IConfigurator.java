package org.palladiosimulator.simulizar.launcher;

import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Interface to modify workflow configurations.
 * 
 * @author Sebastian Lehrig
 */
public interface IConfigurator {

    /**
     * Configures a workflow configuration.
     * 
     * @param configuration
     *            the workflow configuration to be configured.
     * @param blackboard
     *            the blackboard the workflow can access.
     */
    void configure(final SimuLizarWorkflowConfiguration configuration, final MDSDBlackboard blackboard);

}
