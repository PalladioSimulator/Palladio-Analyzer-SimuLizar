package de.upb.pcm.interpreter.access;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.upb.pcm.interpreter.access.internal.ModelHelper;

public final class AccessFactory {
	
	public static IModelAccessFactory createModelAccessFactory(final MDSDBlackboard modelBlackboard) {
		final ModelHelper modelHelper = new ModelHelper(modelBlackboard);
		return new ModelAccessFactory(modelHelper);
	}
	
}
