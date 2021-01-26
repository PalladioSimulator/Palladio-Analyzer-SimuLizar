package org.palladiosimulator.simulizar.launcher.jobs;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.di.extension.Extension;

public interface ModelContribution extends Extension {
    public interface Facade {
        /**
         * Load a model into the default analysis partition
         * 
         * @param modelURI
         *            the URI to load the model from
         */
        void loadModel(URI modelURI);

        /**
         * Load model into dedicated partition
         * 
         * @param modelURI
         *            the URI to load the model from
         * @param partitionId
         *            the partition id of the partition to load the model into
         */
        void loadModel(URI modelURI, String partitionId);
    }

    /**
     * The method is called during model loading and allows extension to add additional models to
     * the blackboard.
     * 
     * In order to load a custom model, use the provided delegate.
     * 
     * @param delegate
     *            the delegate to load models into the blackboard.
     * 
     */
    public void loadModel(ModelContribution.Facade delegate);

}
