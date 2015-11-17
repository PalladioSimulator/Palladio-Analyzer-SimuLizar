package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Special ResourceSetPartition for the MonitorRepository, with the functionality to resolve cross
 * references from the RuntimeMeasurement to PCM.
 *
 * @author Joachim Meyer
 *
 */
public class MonitorRepositoryResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(MonitorRepositoryResourceSetPartition.class);
    private MonitorRepository monitorRepositoryModel;

    /**
     * Constructor
     *
     * @param pcmResourceSetPartition
     *            the pcm resource set partition to resolve cross references from RuntimeMeasurement
     *            to pcm.
     */
    public MonitorRepositoryResourceSetPartition(final PCMResourceSetPartition pcmResourceSetPartition) {
        super();
        this.monitorRepositoryModel = null;
    }

    public MonitorRepository getMonitorRepositoryModel() {
        if (this.monitorRepositoryModel == null) {
            this.monitorRepositoryModel = this.loadMonitorRepositoryModel();
        }
        return this.monitorRepositoryModel;
    }

    /**
     * @return return the MonitorRepository element
     */
    private MonitorRepository loadMonitorRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Monitor Repository Model from blackboard partition");
            final List<MonitorRepository> result = this
                    .getElement(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository());
            return result.get(0);
        } catch (final Exception e) {
            LOGGER.warn("No Monitor Repository found, no requests will be measured.");
            return null;
        }
    }

}
