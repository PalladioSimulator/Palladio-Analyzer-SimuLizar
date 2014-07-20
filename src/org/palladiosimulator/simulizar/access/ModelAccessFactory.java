package org.palladiosimulator.simulizar.access;

import java.util.Collection;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.storydriven.storydiagrams.activities.Activity;

/**
 * Factory for pcm and pms model accesses and pcm model interpreters.
 * 
 * @author Joachim Meyer
 * 
 */
public class ModelAccessFactory implements IModelAccess {
    private final ModelHelper modelHelper;
    private final GlobalPCMAccess globalPCMAccess;

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public ModelAccessFactory(final ModelHelper modelHelper) {
        super();
        this.modelHelper = modelHelper;
        this.globalPCMAccess = new GlobalPCMAccess(modelHelper);
    }

    @Override
    public AllocationAccess getAllocationAccess(final InterpreterDefaultContext context) {
        return new AllocationAccess(context, this.modelHelper);
    }

    @Override
    public UsageModelAccess getUsageModelAccess(final InterpreterDefaultContext context) {
        return new UsageModelAccess(context, this.modelHelper);
    }

    @Override
    public PMSModel getPMSModel() {
        return this.modelHelper.getGlobalPMSModel();
    }

    @Override
    public PRMModel getPRMModel() {
        return this.modelHelper.getGlobalPRMModel();
    }

    @Override
    public GlobalPCMAccess getGlobalPCMAccess() {
        return this.globalPCMAccess;
    }

    @Override
    public Collection<Activity> getStoryDiagrams() {
        return this.modelHelper.getSDMModels();
    }
}
