package org.palladiosimulator.simulizar.access;

import java.util.Collection;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.pms.PMSModel;
import org.palladiosimulator.simulizar.prm.PRMModel;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.workflow.pcm.blackboard.PCMResourceSetPartition;

/**
 * Factory for pcm and pms model accesses and pcm model interpreters.
 * 
 * @author Joachim Meyer
 * 
 */
public class ModelAccessFactory implements IModelAccess {
    private final ModelHelper modelHelper;

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public ModelAccessFactory(final ModelHelper modelHelper) {
        super();
        this.modelHelper = modelHelper;
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
    public PCMResourceSetPartition getGlobalPCMAccess() {
        return this.modelHelper.getPCMResourceSetPartition();
    }

    @Override
    public Collection<Activity> getStoryDiagrams() {
        return this.modelHelper.getSDMModels();
    }
}
