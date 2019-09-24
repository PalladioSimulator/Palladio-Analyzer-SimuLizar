package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public abstract class AbstractReconfigurationLoader {

	private static final Logger LOGGER = Logger.getLogger(AbstractReconfigurationLoader.class.getName());

	protected final List<ModelTransformation<? extends Object>> transformations = new ArrayList<>();

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
			LOGGER.info(String.format("No transformations with extension %s was found.", getTransformationFileExtension()));
			return;
		}
		setTransformations(transURIs);
	}

	public List<ModelTransformation<? extends Object>> getTransformations() {
		return this.transformations;
	}

	protected abstract String getTransformationFileExtension();

	protected abstract void setTransformations(URI[] transURIs);
}
