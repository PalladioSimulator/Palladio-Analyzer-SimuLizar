package org.palladiosimulator.simulizar.access;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

/**
 * Class combining all pcm models.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMModels {
    private final UsageModel usageModel;

    private final Allocation allocation;

    /**
     * @return returns the allocation model.
     */
    public Allocation getAllocation() {
        return this.allocation;
    }

    /**
     * @return returns the usage model.
     */
    public UsageModel getUsageModel() {
        return this.usageModel;
    }

    public PCMModels(final Allocation allocation, final UsageModel usageModel) {
        super();
        this.allocation = allocation;
        this.usageModel = usageModel;
    }
}
