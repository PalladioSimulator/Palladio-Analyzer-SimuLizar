package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Special ResourceSetPartition for the Usage Evolution, with the functionality to resolve cross
 * references from the UsageEvolution to PCM.
 * 
 * @author Erlend Stav
 * 
 */
public class UEResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(UEResourceSetPartition.class);
    private UsageEvolution ueModel;

    /**
     * Constructor
     * 
     * @param pcmResourceSetPartition
     *            the pcm resource set partition to resolve cross references from prm to pcm.
     */
    public UEResourceSetPartition(final PCMResourceSetPartition pcmResourceSetPartition) {
        super();
        this.ueModel = null;
    }

    public UsageEvolution getUsageEvolution() {
        if (this.ueModel == null) {
            this.ueModel = loadUEModel();
        }
        return this.ueModel;
    }

    /**
     * @return return the usage evolution element
     */
    private UsageEvolution loadUEModel() {
        try {
            LOGGER.debug("Retrieving Usage Evolution model from blackboard partition");
            List<UsageEvolution> result = getElement(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.info("No Usage Evolution model found, so evolution will not be simulated.");
            return null;
        }
    }

}
