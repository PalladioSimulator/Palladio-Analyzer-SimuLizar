package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;

import org.apache.log4j.Logger;
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

    private final static Logger LOG = Logger.getLogger(PMSResourceSetPartition.class);
    private final PMSModel pmsModel;

    /**
     * Constructor
     * 
     * @param pcmResourceSetPartition
     *            the pcm resource set partition to resolve cross references from prm to pcm.
     */
    public PMSResourceSetPartition(final PCMResourceSetPartition pcmResourceSetPartition) {
        super();
        this.pmsModel = loadPMSModel();
    }

    public PMSModel getPMSModel() {
        return this.pmsModel;
    }

    /**
     * @return return the PMSModel element
     */
    private PMSModel loadPMSModel() {
        try {
            List<PMSModel> result = getElement(PmsPackage.eINSTANCE.getPMSModel());
            LOG.debug("Retrieved PMS Model into blackboard partition");
            return result.get(0);
        } catch (Exception e) {
            LOG.warn("No PMS found, no requests will be measured.");
            return null;
        }
    }

}
