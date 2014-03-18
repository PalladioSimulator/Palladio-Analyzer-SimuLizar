package org.palladiosimulator.simulizar.access;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

/**
 * Factory for pcm and pms model accesses and pcm model interpreters.
 * 
 * @author Joachim Meyer
 * 
 */
class ModelAccessFactory implements IModelAccessFactory {
    private final ModelHelper modelHelper;
    private final PMSAccess pmsAccess;
    private final PRMAccess prmAccess;
    private final GlobalPCMAccess globalPCMAccess;
    private final SDAccess sdAccess;

    /**
     * Constructor
     * 
     * @param modelHelper
     *            the model helper.
     */
    public ModelAccessFactory(final ModelHelper modelHelper) {
        super();
        this.modelHelper = modelHelper;
        this.pmsAccess = new PMSAccess(modelHelper);
        this.prmAccess = new PRMAccess(modelHelper);
        this.globalPCMAccess = new GlobalPCMAccess(modelHelper);
        this.sdAccess = new SDAccess(modelHelper);
    }

    @Override
    public AllocationAccess getAllocationAccess(final InterpreterDefaultContext context) {
        return new AllocationAccess(context, this.getModelHelper());
    }

    @Override
    public SystemAccess getSystemAccess(final InterpreterDefaultContext context) {
        return new SystemAccess(context, this.getModelHelper());
    }

    /**
     * 
     * @see org.palladiosimulator.simulizar.access.IModelAccessFactory#getPCMModelAccess(int,
     *      org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext)
     */
    @Override
    public UsageModelAccess getUsageModelAccess(final InterpreterDefaultContext context) {
        return new UsageModelAccess(context, this.getModelHelper());
    }

    @Override
    public RepositoryAccess getRepositoryAccess(final InterpreterDefaultContext context) {
        return new RepositoryAccess(context, this.getModelHelper());
    }

    /**
     * 
     * @see org.palladiosimulator.simulizar.access.IModelAccessFactory#getPMSModelAccess()
     */
    @Override
    public PMSAccess getPMSModelAccess() {
        return this.pmsAccess;
    }

    @Override
    public PRMAccess getPRMModelAccess() {
        return this.prmAccess;
    }

    @Override
    public GlobalPCMAccess getGlobalPCMAccess() {
        return this.globalPCMAccess;
    }

    @Override
    public SDAccess getSDAccess() {
        return this.sdAccess;
    }

    /**
     * @return the modelHelper.
     */
    private ModelHelper getModelHelper() {
        return this.modelHelper;
    }

}
