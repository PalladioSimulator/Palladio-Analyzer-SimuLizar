package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.scaledl.usageevolution.UsageEvolution;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

/**
 * Interface which allows access to all models at simulation time. Two classes of models exist:
 * global models like the central PCM model, the MonitorRepository model, the RuntimeMeasurement
 * model, or all reconfiguration rules provided in various models. The second class of models are
 * read-only copies of a subset of the global models provided to each simulated process/thread when
 * it starts to execute.
 * 
 * @author Steffen Becker, Joachim Meyer
 * 
 */
public interface IModelAccess extends Cloneable {

    public MDSDBlackboard getBlackboard();

    // -----
    // Access to simulated processes local models/local copies of the global model
    // -----
    public PCMResourceSetPartition getLocalPCMModel();

    // -----
    // Access to any model which is global and exists only once
    // -----
    public PCMResourceSetPartition getGlobalPCMModel();

    public MonitorRepository getMonitorRepositoryModel();

    public RuntimeMeasurementModel getRuntimeMeasurementModel();

    public UsageEvolution getUsageEvolutionModel();

    // public Collection<Activity> getStoryDiagrams();

    public IModelAccess clone();
}
