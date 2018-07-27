package org.palladiosimulator.simulizar.reconfiguration.qvto;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.utils.FileUtil;

public class QvtoReconfigurationLoader implements IReconfigurationLoader {

	private SimuLizarWorkflowConfiguration configuration;
	private List<ModelTransformation<? extends Object>> transformations;

	public QvtoReconfigurationLoader() {}

	@Override
	public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void setModelAccess(IModelAccess modelAccess) {
		// not needed for loading Qvto models
	}

	@Override
	public List<ModelTransformation<? extends Object>> getTransformations() {
		if (transformations == null) {
			this.transformations = new ArrayList<ModelTransformation<? extends Object>>();
			URI[] qvtoFiles = FileUtil.getQvtoFiles(this.configuration.getReconfigurationRulesFolder());
			ModelTransformationCache transformationCache = new ModelTransformationCache(qvtoFiles);
			transformationCache.getAll().forEach(t -> this.transformations.add(t));
		}
		return this.transformations;
	}

}
