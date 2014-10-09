package org.palladiosimulator.simulizar.launcher.partitions;

import java.util.List;

import org.apache.log4j.Logger;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;
import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;
import dlim.DlimPackage;
import dlim.Sequence;

/**
 * Special ResourceSetPartition for the Dynamic Environment Model.
 * 
 * @author Vinay Akkasetty Gopal
 * 
 */

public class DEMResourceSetPartition extends ResourceSetPartition {

	private static final Logger LOGGER = Logger
			.getLogger(DEMResourceSetPartition.class);
	private Sequence deModel;

	/**
	 * Constructor
	 * 
	 * @param pcmResourceSetPartition
	 *            the pcm resource set partition to resolve cross references
	 *            from prm to pcm.
	 */
	public DEMResourceSetPartition(
			final PCMResourceSetPartition pcmResourceSetPartition) {
		super();
		this.deModel = null;
	}

	public Sequence getDEModel() {
		if (this.deModel == null) {
			this.deModel = loadDEModel();
		}
		return this.deModel;
	}

	/**
	 * @return return the PMSModel element
	 */
	private Sequence loadDEModel() {
		try {
			LOGGER.debug("Retrieving Dynamic Environment Model from blackboard partition");
			List<Sequence> result = getElement(DlimPackage.eINSTANCE
					.getSequence());
			return result.get(0);
		} catch (Exception e) {
			LOGGER.warn("No DEM found, no requests will be measured.");
			return null;
		}
	}

}