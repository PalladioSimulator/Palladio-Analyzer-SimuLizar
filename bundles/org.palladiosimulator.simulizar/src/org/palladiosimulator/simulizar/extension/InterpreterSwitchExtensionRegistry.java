package org.palladiosimulator.simulizar.extension;

import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;

@FunctionalInterface
public interface InterpreterSwitchExtensionRegistry {
    
    void registerRDSeffSwitchFactory(AbstractRDSeffSwitchFactory factory);

}
