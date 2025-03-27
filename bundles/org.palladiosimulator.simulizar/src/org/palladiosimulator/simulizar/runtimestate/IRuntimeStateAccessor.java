package org.palladiosimulator.simulizar.runtimestate;

import org.palladiosimulator.simulizar.di.base.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.component.core.SimuLizarRuntimeComponent;

/**
 * This interface is deprecated and only continues to exist, until org.palladiosimulator.action is migrated.
 * 
 * If you need to access runtime internals of SimuLizar see {@link SimuLizarRuntimeComponent} and {@link ExtensionComponent}.
 */
@Deprecated
public interface IRuntimeStateAccessor {

    public void setRuntimeStateModel(SimuLizarRuntimeState state);
}
