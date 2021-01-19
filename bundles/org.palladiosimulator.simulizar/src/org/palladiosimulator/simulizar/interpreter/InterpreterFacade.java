package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.simulizar.entity.EntityReference;

public interface InterpreterFacade {
    
    void submit(EntityReference<?> object);

}
