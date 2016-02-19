package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.resource.Resource;
import org.storydriven.storydiagrams.activities.ActivitiesPackage;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

/**
 * Special ResourceSetPartition for the sdm models, with the functionality to get all activities of
 * all sdm models in the sdm model folder.
 * 
 * @author Joachim Meyer
 * 
 */
public class SDMResourceSetPartition extends ResourceSetPartition {

    /**
     * Gets all activities of all loaded sdm models.
     * 
     * @return a list of activities.
     */
    public List<Activity> getActivities() {
        final List<Activity> activities = new Vector<Activity>();
        for (final Resource resource : this.rs.getResources()) {
            if (resource != null && resource.getContents().size() > 0
                    && resource.getContents().get(0).eClass() == ActivitiesPackage.eINSTANCE.getActivity()) {
                activities.add((Activity) resource.getContents().get(0));
            }
        }

        return activities;

    }

}
