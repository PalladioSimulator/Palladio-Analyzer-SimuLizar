package org.palladiosimulator.simulizar.action.interpreter.util;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.scopes.RuntimeExtensionScope;

@RuntimeExtensionScope
public class TransientEffectTransformationCacheKeeper implements IModelObserver {
	protected static Map<InterpreterDefaultContext, ModelTransformationCache> CACHE_MAP = new HashMap<>();
    private final InterpreterDefaultContext mainContext;
	
	public static ModelTransformationCache getTransformationCacheForRuntimeState(SimuLizarRuntimeState runtimeState) {
		return TransientEffectTransformationCacheKeeper.CACHE_MAP.get(runtimeState.getMainContext());
	}
	
	@Inject
	public TransientEffectTransformationCacheKeeper(@MainContext InterpreterDefaultContext mainContext) {
        this.mainContext = mainContext;
	}
	
	@Override
	public void initialize() {
		TransientEffectTransformationCacheKeeper.CACHE_MAP.put(mainContext, new ModelTransformationCache());
	}

	@Override
	public void unregister() {
		ModelTransformationCache cache = TransientEffectTransformationCacheKeeper.CACHE_MAP.remove(this.mainContext);
		cache.clear();
	}

}
