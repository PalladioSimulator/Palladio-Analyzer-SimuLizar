package org.palladiosimulator.simulizar.access;

import java.util.Collection;

import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.scaledl.usageevolution.UsageEvolution;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Interface which allows access to all models at simulation time. Two classes of models exist:
 * global models like the central PCM model, the MonitorRepository model, the PRM model, or all
 * reconfiguration rules provided in various models. The second class of models are read-only copies
 * of a subset of the global models provided to each simulated process/thread when it starts to
 * execute.
 * 
 * @author Steffen Becker, Joachim Meyer
 * 
 */
public interface IModelAccess extends Cloneable {
    // -----
    // Access to simulated processes local models/local copies of the global model
    // -----
    public PCMResourceSetPartition getLocalPCMModel();

    // -----
    // Access to any model which is global and exists only once
    // -----
    public PCMResourceSetPartition getGlobalPCMModel();

    public MonitorRepository getMonitorRepositoryModel();

    public PRMModel getPRMModel();

    public UsageEvolution getUsageEvolutionModel();
    
    public Collection<Activity> getStoryDiagrams();

    public IModelAccess clone();
}
