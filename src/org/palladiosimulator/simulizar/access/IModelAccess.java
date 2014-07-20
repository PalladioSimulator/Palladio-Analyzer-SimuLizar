package org.palladiosimulator.simulizar.access;

import java.util.Collection;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.storydriven.storydiagrams.activities.Activity;

/**
 * Model access factory interface for creating pcm and prm model access, as well as pcm model
 * interpreter.
 * 
 * @author Steffen Becker, Joachim Meyer
 * 
 */
public interface IModelAccess {
    // -----
    // Access to simlated processes local models/local copies of the global model
    // -----
    public UsageModelAccess getUsageModelAccess(InterpreterDefaultContext context);

    public AllocationAccess getAllocationAccess(InterpreterDefaultContext context);

    // -----
    // Access to any model which is global and exists only once
    // -----
    public GlobalPCMAccess getGlobalPCMAccess();

    public PMSModel getPMSModel();

    public PRMModel getPRMModel();

    public Collection<Activity> getStoryDiagrams();
}
