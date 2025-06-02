package org.palladiosimulator.simulizar.launcher.jobs;

import org.palladiosimulator.simulizar.di.base.extension.Extension;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

public interface PartitionContribution extends Extension {
    public interface Facade {
        /**
         * Appends a partition to the analysis blackboard
         * 
         * @param partitionId
         *            the id of the partition to append
         * @param partition
         *            the partition implementation to append
         */
        void appendPartition(String partitionId, ResourceSetPartition partition);
    }

    /**
     * The method is called before model loading and allows extension to add additional partitions
     * to the blackboard.
     * 
     * In order to load a custom model, use the provided delegate.
     * 
     * @param delegate
     *            the delegate to partitions into the blackboard.
     * 
     */
    public void contribute(PartitionContribution.Facade delegate);

}
