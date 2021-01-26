package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent;

/**
 * This interface is deprecated and only continues to exist, until org.palladiosimulator.action is migrated.
 * 
 * If you need to access runtime internals of SimuLizar see {@link SimuLizarRuntimeComponent} and {@link ExtensionComponent}.
 */
@Deprecated
public interface IRuntimeStateAccessor {

    public void setRuntimeStateModel(SimuLizarRuntimeState state);
}
