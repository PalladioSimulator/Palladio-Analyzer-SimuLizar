/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.provider;

import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.provider.EcoreEditPlugin;

import org.palladiosimulator.simulizar.reconfigurationrule.provider.ReconfigurationRuleEditPlugin;

import org.storydriven.core.provider.CoreEditPlugin;

import org.storydriven.storydiagrams.provider.StorydiagramsEditPlugin;

/**
 * This is the central singleton for the StorydiagramReconfiguration edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class StorydiagramReconfigurationEditPlugin extends EMFPlugin {
    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final StorydiagramReconfigurationEditPlugin INSTANCE = new StorydiagramReconfigurationEditPlugin();

    /**
     * Keep track of the singleton.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static Implementation plugin;

    /**
     * Create the instance.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StorydiagramReconfigurationEditPlugin() {
        super
          (new ResourceLocator [] {
             CoreEditPlugin.INSTANCE,
             EcoreEditPlugin.INSTANCE,
             ReconfigurationRuleEditPlugin.INSTANCE,
             StorydiagramsEditPlugin.INSTANCE,
           });
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    @Override
    public ResourceLocator getPluginResourceLocator() {
        return plugin;
    }

    /**
     * Returns the singleton instance of the Eclipse plugin.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the singleton instance.
     * @generated
     */
    public static Implementation getPlugin() {
        return plugin;
    }

    /**
     * The actual implementation of the Eclipse <b>Plugin</b>.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class Implementation extends EclipsePlugin {
        /**
         * Creates an instance.
         * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         * @generated
         */
        public Implementation() {
            super();

            // Remember the static instance.
            //
            plugin = this;
        }
    }

}
