package org.palladiosimulator.simulizar.reconfiguration.henshin;

import org.eclipse.emf.henshin.model.Module;
import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;

public class HenshinModelTransformation extends ModelTransformationImpl<Module> {
	public HenshinModelTransformation(Module module){
		this.modelTransformation = module;
	}
}
