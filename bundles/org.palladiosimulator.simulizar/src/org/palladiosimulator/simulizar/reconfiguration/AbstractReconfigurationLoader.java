package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

/**
 * This class constitutes the main extension point to load new reconfigurations.
 * It has to be extended in order to add new loaders by specifying
 * reconfiguration specific informations.
 * 
 * @author scheerer
 *
 */
public abstract class AbstractReconfigurationLoader {

	private static final Logger LOGGER = Logger.getLogger(AbstractReconfigurationLoader.class.getName());

	protected final List<ModelTransformation<? extends Object>> transformations = new ArrayList<>();

	/**
	 * The method loads all transformation that are stored in the reconfiguration
	 * rules folder.
	 * 
	 * @param configuration The configuration object contains the information of the
	 *                      reconfiguration rules location.
	 */
	public void load(SimuLizarWorkflowConfiguration configuration) {
		String reconfFolder = configuration.getReconfigurationRulesFolder();
		if (reconfFolder == null || reconfFolder.isEmpty()) {
			LOGGER.info("No reconfiguration rules folder specified, reconfigurations disabled.");
			return;
		}
		load(reconfFolder);
	}

	private void load(String reconfFolder) {
		URI[] transURIs = FileHelper.getURIs(reconfFolder, getTransformationFileExtension());
		if (transURIs.length == 0) {
			LOGGER.info(
					String.format("No transformations with extension %s was found.", getTransformationFileExtension()));
			return;
		}
		setTransformations(transURIs);
	}

	/**
	 * @return the set of reconfigurations.
	 */
	public List<ModelTransformation<? extends Object>> getTransformations() {
		return this.transformations;
	}

	/**
	 * The method specifies the file extension of the reconfigurations files that
	 * are supposed to be loaded.
	 * 
	 * @return the file extension.
	 */
	protected abstract String getTransformationFileExtension();

	/**
	 * The method sets an array of URIs, specifying the location of
	 * reconfigurations.
	 * 
	 * @param transURIs Reconfiguration locations.
	 */
	protected abstract void setTransformations(URI[] transURIs);
}
