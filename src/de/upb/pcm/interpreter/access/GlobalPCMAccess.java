package de.upb.pcm.interpreter.access;

import de.upb.pcm.interpreter.access.internal.ModelHelper;
import de.upb.pcm.interpreter.utils.PCMModels;

public class GlobalPCMAccess extends AbstractModelAccess<PCMModels> {

	public GlobalPCMAccess(ModelHelper modelHelper) {
		super(modelHelper);
	}

	@Override
	public PCMModels getModel() {
		return this.getModelHelper().getGlobalPCMModels();
	}
}
