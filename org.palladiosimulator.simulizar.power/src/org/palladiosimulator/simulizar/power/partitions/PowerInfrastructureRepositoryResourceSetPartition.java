package org.palladiosimulator.simulizar.power.partitions;

import java.util.List;

import org.apache.log4j.Logger;

import de.fzi.power.infrastructure.InfrastructurePackage;
import de.fzi.power.infrastructure.PowerInfrastructureRepository;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

public final class PowerInfrastructureRepositoryResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(PowerInfrastructureRepositoryResourceSetPartition.class);
    private PowerInfrastructureRepository powerInfrastructureRepositoryModel;

    public PowerInfrastructureRepositoryResourceSetPartition() {
    }

    public PowerInfrastructureRepository getPowerInfrastructureRepositoryModel() {
        if (this.powerInfrastructureRepositoryModel == null) {
            this.powerInfrastructureRepositoryModel = loadPowerInfrastructureRepositoryModel();
        }
        return this.powerInfrastructureRepositoryModel;
    }

    /**
     * @return return the MonitorRepository element
     */
    private PowerInfrastructureRepository loadPowerInfrastructureRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Power Infrastructure Repository Model from blackboard partition");
            List<PowerInfrastructureRepository> result = getElement(InfrastructurePackage.eINSTANCE
                    .getPowerInfrastructureRepository());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.warn("No Infrastructure Repository found, no requests will be measured.");
            return null;
        }
    }
}
