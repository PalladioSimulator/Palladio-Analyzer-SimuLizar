package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.ProxyCrossReferencer;
import org.palladiosimulator.simulizar.exceptions.PMSModelLoadException;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.pms.PmsPackage;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Special ResourceSetPartition for the PMS, with the functionality to resolve cross references from
 * the PRM to PCM.
 * 
 * @author Joachim Meyer
 * 
 */
public class PMSResourceSetPartition extends ResourceSetPartition {

    private final PCMResourceSetPartition pcmResourceSetPartition;

    /**
     * Constructor
     * 
     * @param pcmResourceSetPartition
     *            the pcm resource set partition to resolve cross references from prm to pcm.
     */
    public PMSResourceSetPartition(final PCMResourceSetPartition pcmResourceSetPartition) {
        super();
        this.pcmResourceSetPartition = pcmResourceSetPartition;
    }

    /**
     * @return return the PMSModel element
     */
    public PMSModel getPMSModel() {
        return this.getRootElement();
    }

    /**
     * @return Returns the pcmResourceSetPartition.
     */
    private PCMResourceSetPartition getPcmResourceSetPartition() {
        return this.pcmResourceSetPartition;
    }

    /**
     * Gets the root element of the PMS, the PMSModel
     * 
     * @return the PMSModel
     */
    private PMSModel getRootElement() {
        for (final Resource resource : this.rs.getResources()) {
            if (resource != null && resource.getContents().size() > 0
                    && resource.getContents().get(0).eClass() == PmsPackage.eINSTANCE.getPMSModel()) {
                return (PMSModel) resource.getContents().get(0);
            }
        }

        throw new RuntimeException("Failed to retrieve PMS model element "
                + PmsPackage.eINSTANCE.getPMSModel().getName());

    }

    /**
     * Resolves all cross references from PMS to PCM
     */
    public void resolveAllProxiesToPCM() {
        /*
         * Note: the prm resource set should only have one resource, but maybe we need more in the
         * future
         */

        for (final Resource resource : this.rs.getResources()) {
            final Map<EObject, Collection<Setting>> proxiesToBeResolved = ProxyCrossReferencer.find(resource);
            for (final EObject element : proxiesToBeResolved.keySet()) {
                // resolve
                final EObject resolved = EcoreUtil.resolve(element, this.getPcmResourceSetPartition().getResourceSet());
                if (resolved.eIsProxy()) {
                    throw new PMSModelLoadException("Unable to resolve proxy " + resolved);
                }
                // now proxy is resolved, replace proxy in prm model (for each setting)
                for (final Setting setting : proxiesToBeResolved.get(element)) {
                    EcoreUtil.replace(setting, element, resolved);
                }

            }
        }

    }

}
