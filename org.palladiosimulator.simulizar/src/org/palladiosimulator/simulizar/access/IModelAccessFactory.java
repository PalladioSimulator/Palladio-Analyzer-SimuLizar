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
    UsageModelAccess getUsageModelAccess(InterpreterDefaultContext context);

    AllocationAccess getAllocationAccess(InterpreterDefaultContext context);

    SystemAccess getSystemAccess(InterpreterDefaultContext context);

    RepositoryAccess getRepositoryAccess(InterpreterDefaultContext context);

    GlobalPCMAccess getGlobalPCMAccess();

    /**
     * Gets PMS model access.
     * 
     * @return the pms model access.
     */
    PMSAccess getPMSModelAccess();

    PRMAccess getPRMModelAccess();

    SDAccess getSDAccess();
}
