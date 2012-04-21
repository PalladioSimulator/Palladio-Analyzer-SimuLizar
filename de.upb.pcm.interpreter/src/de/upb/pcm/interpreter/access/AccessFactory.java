package de.upb.pcm.interpreter.access;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public final class AccessFactory {
	
	public static IModelAccessFactory createModelAccessFactory(final MDSDBlackboard modelBlackboard) {
		final ModelHelper modelHelper = new ModelHelper(modelBlackboard);
		return new ModelAccessFactory(modelHelper);
	}
	
}
