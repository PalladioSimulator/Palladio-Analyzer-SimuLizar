package org.palladiosimulator.simulizar.extension;

public interface SimuLizarExtension {
    /** Before initialization of SimuLizarRuntimeState **/
    default void preInitialize() {
    }
    
    /** After SimuLizarRuntimeState has been initialized **/
    default void initialize() {
    }

    /** Once simulation terminated **/
    default void shutdown() {
    }
    
    /** After SimuLizarRuntimeState has been cleaned up. **/
    default void cleanUp() {
    }
}
