package org.palladiosimulator.simulizar.action.interpreter.util;

import java.util.HashMap;
import java.util.Map;

import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public class TransientEffectTransformationCacheKeeper implements IModelObserver {
	protected static Map<AbstractSimuLizarRuntimeState, ModelTransformationCache> CACHE_MAP = new HashMap<>();
	protected AbstractSimuLizarRuntimeState runtimeState = null;

	public static ModelTransformationCache getTransformationCacheForRuntimeState(AbstractSimuLizarRuntimeState state) {
		return TransientEffectTransformationCacheKeeper.CACHE_MAP.get(state);
	}
	
	@Override
	public void initialize(AbstractSimuLizarRuntimeState runtimeState) {
		this.runtimeState = runtimeState;
		TransientEffectTransformationCacheKeeper.CACHE_MAP.put(runtimeState, new ModelTransformationCache());
	}

	@Override
	public void unregister() {
		ModelTransformationCache cache = TransientEffectTransformationCacheKeeper.CACHE_MAP.remove(this.runtimeState);
		cache.clear();
	}

}
