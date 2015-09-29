package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Special ResourceSetPartition for the Service Level Objective Repository.
 *
 * @author Matthias Becker
 *
 */
public class ServiceLevelObjectiveResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(ServiceLevelObjectiveResourceSetPartition.class);
    private ServiceLevelObjectiveRepository serviveLevelObjectiveRepository;

    /**
     * Constructor
     *
     * @param pcmResourceSetPartition
     *            the pcm resource set partition to resolve cross references from prm to pcm.
     */
    public ServiceLevelObjectiveResourceSetPartition() {
        super();
        this.serviveLevelObjectiveRepository = null;
    }

    public ServiceLevelObjectiveRepository getServiceLevelObjectiveRepository() {
        if (this.serviveLevelObjectiveRepository == null) {
            this.serviveLevelObjectiveRepository = this.loadSLOModel();
        }
        return this.serviveLevelObjectiveRepository;
    }

    /**
     * @return return the usage evolution element
     */
    private ServiceLevelObjectiveRepository loadSLOModel() {
        try {
            LOGGER.debug("Retrieving Service Level Objective repository from blackboard partition");
            final List<ServiceLevelObjectiveRepository> result = this
                    .getElement(ServicelevelObjectivePackage.eINSTANCE.getServiceLevelObjectiveRepository());
            return result.get(0);
        } catch (final Exception e) {
            LOGGER.info("No Service Level Objectives found.");
            return null;
        }
    }

}
