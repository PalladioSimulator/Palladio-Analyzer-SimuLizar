/** */
package org.palladiosimulator.simulizar.core.reconfiguration;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager;
import org.palladiosimulator.simulizar.di.base.extension.Extension;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;

/**
 * Interface for a component that is able to reconfigure the PCM model@runtime.
 * 
 * @author snowball
 * 
 */
public interface IReconfigurationEngine extends Extension {

    public static int RECONFIGURATION_CHECK_SUCCEEDED = 0;
    public static int RECONFIGURATION_CHECK_FAILED = 1;
    public static int RECONFIGURATION_CHECK_NOT_APPLICABLE_BY_ENGINE = 2;
    public static int RECONFIGURATION_EXECUTION_SUCCEEDED = 3;
    public static int RECONFIGURATION_EXECUTION_FAILED = 4;
    public static int RECONFIGURATION_EXECUTION_NOT_APPLICABLE_BY_ENGINE = 5;

    /**
     * Trigger a condition check of the model@runtime. Engines should check whether they should
     * reconfigure.
     * 
     * @param <T>
     * 
     * @param monitoredElement
     *            PCM model element for which a new sensor measurement arrived.
     * @return <code>true</code> if the check was positive/matched, <code>false</code> if it was
     *         negative/did not match
     */
    boolean runCheck(List<? extends ModelTransformation<? extends Object>> checks, EObject monitoredElement,
            IResourceTableManager resourceTableManager, Map<String, Object> configParams);

    /**
     * Trigger a reconfiguration of the model@runtime. This method should only be called if the
     * prior check was positive, i.e., engines should only do the reconfiguration if the prior check
     * was positive.
     * 
     * @param monitoredElement
     *            PCM model element for which a new sensor measurement arrived.
     * @return <code>true</code> if the reconfiguration was executed, <code>false</code> if it was
     *         not executed or did not succeed.
     */
    boolean runExecute(List<? extends ModelTransformation<? extends Object>> actions, EObject monitoredElement,
            IResourceTableManager resourceTableManager);

    boolean runExecute(List<? extends ModelTransformation<? extends Object>> actions, EObject monitoredElement,
            IResourceTableManager resourceTableManager, Map<String, Object> configParams);

    @Inject
    default void setConfiguration(final SimuLizarWorkflowConfiguration configuration) {
        // This needs to be an empty default implementations for dagger to recognize it properly
    }

    @Inject
    default void setPCMPartitionManager(final PCMPartitionManager pcmPartitionManager) {
        // This needs to be an empty default implementations for dagger to recognize it properly
    }

}
