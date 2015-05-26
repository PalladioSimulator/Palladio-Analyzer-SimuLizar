package org.palladiosimulator.simulizar.action.partitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.action.core.ActionRepository;
import org.palladiosimulator.simulizar.action.core.CorePackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * This class is an implementation of a {@link ResourceSetPartition} specialized on 
 * {@link ActionRepository} models.
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class ActionRepositoryResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(ActionRepositoryResourceSetPartition.class);
    private ActionRepository actionRepositoryModel;    
    
    /**
     * Gets the {@link ActionRepository} which is currently contained within this partition.
     * @return The {@link ActionRepository} that currently resides within is partition,
     * or {@code null} if none is available.
     */
    public ActionRepository getActionRepositoryModel() {
        if (this.actionRepositoryModel == null) {
            this.actionRepositoryModel = loadActionRepositoryModel();
        }
        return this.actionRepositoryModel;
    }
    
    private ActionRepository loadActionRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Action Repository Model from blackboard partition");
            List<ActionRepository> result = getElement(CorePackage.eINSTANCE
                    .getActionRepository());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.warn("No Action Repository found, transient effects cannot be considered.");
            return null;
        }
    }
    
}
