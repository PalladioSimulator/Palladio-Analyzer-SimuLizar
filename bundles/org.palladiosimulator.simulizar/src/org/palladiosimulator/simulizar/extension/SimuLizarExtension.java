package org.palladiosimulator.simulizar.extension;

import org.palladiosimulator.simulizar.SimuLizarCoreComponent;

public interface SimuLizarExtension {
    public void initialize(SimuLizarCoreComponent simuLizarComponent);

    public void destroy();
}
