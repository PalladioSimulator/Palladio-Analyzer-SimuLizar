package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

/**
 * Model access factory interface for creating pcm and prm model access, as well as pcm model
 * interpreter.
 * 
 * @author Steffen Becker, Joachim Meyer
 * 
 */
public interface IModelAccessFactory {
    // -----
    // Access to simlated processes local models/local copies of the global model
    // -----
    public UsageModelAccess getUsageModelAccess(InterpreterDefaultContext context);

    public AllocationAccess getAllocationAccess(InterpreterDefaultContext context);

    // -----
    // Access to any model which is global and exists only once
    // -----
    public GlobalPCMAccess getGlobalPCMAccess();

    public PMSAccess getPMSModelAccess();

    public PRMAccess getPRMModelAccess();

    public SDAccess getSDAccess();
}
