package org.palladiosimulator.simulizar.action.partitions;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.action.core.AdaptationBehaviorRepository;
import org.palladiosimulator.simulizar.action.core.CorePackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * This class is an implementation of a {@link ResourceSetPartition} specialized on
 * {@link AdaptationBehaviorRepository} models.
 * 
 * @author Christian Stier, Florian Rosenthal
 *
 */
public class AdaptationBehaviorRepositoryResourceSetPartition extends ResourceSetPartition {

    private static final Logger LOGGER = Logger.getLogger(AdaptationBehaviorRepositoryResourceSetPartition.class);
    private AdaptationBehaviorRepository adaptationBehaviorRepositoryModel;

    @Inject
    public AdaptationBehaviorRepositoryResourceSetPartition() {
    }
    
    /**
     * Gets the {@link AdaptationBehaviorRepository} which is currently contained within this
     * partition.
     * 
     * @return The {@link AdaptationBehaviorRepository} that currently resides within is partition,
     *         or {@code null} if none is available.
     */
    public AdaptationBehaviorRepository getAdaptationBehaviorRepositoryModel() {
        if (this.adaptationBehaviorRepositoryModel == null) {
            this.adaptationBehaviorRepositoryModel = loadAdaptationBehaviorRepositoryModel();
        }
        return this.adaptationBehaviorRepositoryModel;
    }

    private AdaptationBehaviorRepository loadAdaptationBehaviorRepositoryModel() {
        try {
            LOGGER.debug("Retrieving Adaptation Behavior Repository Model from blackboard partition");
            List<AdaptationBehaviorRepository> result = getElement(
                    CorePackage.eINSTANCE.getAdaptationBehaviorRepository());
            return result.get(0);
        } catch (Exception e) {
            LOGGER.warn("No Adaptation Behavior Repository found, transient effects cannot be considered.");
            return null;
        }
    }

}
