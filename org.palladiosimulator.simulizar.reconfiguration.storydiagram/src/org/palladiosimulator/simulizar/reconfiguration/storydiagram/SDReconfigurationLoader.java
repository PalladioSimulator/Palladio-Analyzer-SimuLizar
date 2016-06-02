package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.util.ArrayList;
import java.util.List;

import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationLoader;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.modelaccess.StoryDiagramModelAccess;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

public class SDReconfigurationLoader implements IReconfigurationLoader {

	private SimuLizarWorkflowConfiguration configuration;
	private StoryDiagramModelAccess modelAccess;
	private List<ModelTransformation<? extends Object>> transformations;
	
	public SDReconfigurationLoader() {}

	@Override
	public void setConfiguration(SimuLizarWorkflowConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public void setModelAccess(IModelAccess modelAccess) {
		this.modelAccess = new StoryDiagramModelAccess(modelAccess, this.configuration);
	}

	@Override
	public List<ModelTransformation<? extends Object>> getTransformations() {
		if(this.transformations == null){
			this.transformations = new ArrayList<ModelTransformation<? extends Object>>();
			this.modelAccess.getStoryDiagrams().forEach(a -> this.transformations.add(new SDModelTransformation(a)));
		}
		return this.transformations;
	}

}
