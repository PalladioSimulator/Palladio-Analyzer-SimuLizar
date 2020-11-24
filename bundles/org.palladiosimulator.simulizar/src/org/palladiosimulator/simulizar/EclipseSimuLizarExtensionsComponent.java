package org.palladiosimulator.simulizar;

import org.palladiosimulator.simulizar.modules.EclipseSimuLizarExtensionModule;
import org.palladiosimulator.simulizar.scopes.ExtensionManagementScope;

import dagger.Component;

/**
 *
 */
@ExtensionManagementScope
@Component(dependencies = SimuLizarCoreComponent.class, modules = EclipseSimuLizarExtensionModule.class)
public interface EclipseSimuLizarExtensionsComponent extends SimuLizarExtensionsComponent {

}
