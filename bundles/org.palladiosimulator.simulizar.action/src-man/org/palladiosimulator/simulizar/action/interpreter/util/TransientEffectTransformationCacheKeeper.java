package org.palladiosimulator.simulizar.action.interpreter.util;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.action.di.ActionRuntimeComponent;
import org.palladiosimulator.simulizar.modelobserver.IModelObserver;
import org.palladiosimulator.simulizar.reconfiguration.qvto.util.ModelTransformationCache;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public class TransientEffectTransformationCacheKeeper implements IModelObserver {
	protected static Map<SimuLizarRuntimeState, ModelTransformationCache> CACHE_MAP = new HashMap<>();
	protected SimuLizarRuntimeState runtimeState = null;

	public static ModelTransformationCache getTransformationCacheForRuntimeState(SimuLizarRuntimeState state) {
		return TransientEffectTransformationCacheKeeper.CACHE_MAP.get(state);
	}
	
	@Inject
	public TransientEffectTransformationCacheKeeper(SimuLizarRuntimeState runtimeState) {
	    this.runtimeState = runtimeState;
	}
	
	@Override
	public void initialize() {
		TransientEffectTransformationCacheKeeper.CACHE_MAP.put(runtimeState, new ModelTransformationCache());
	}

	@Override
	public void unregister() {
		ModelTransformationCache cache = TransientEffectTransformationCacheKeeper.CACHE_MAP.remove(this.runtimeState);
		cache.clear();
	}

}
