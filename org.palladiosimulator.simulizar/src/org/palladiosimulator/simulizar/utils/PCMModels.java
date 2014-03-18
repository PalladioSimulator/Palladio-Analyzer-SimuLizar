package org.palladiosimulator.simulizar.utils;

import de.uka.ipd.sdq.pcm.allocation.Allocation;
import de.uka.ipd.sdq.pcm.repository.Repository;
import de.uka.ipd.sdq.pcm.resourceenvironment.ResourceEnvironment;
import de.uka.ipd.sdq.pcm.system.System;
import de.uka.ipd.sdq.pcm.usagemodel.UsageModel;

/**
 * Class combining all pcm models.
 * 
 * @author Joachim Meyer
 * 
 */
public class PCMModels {
    private final UsageModel usageModel;

    private final de.uka.ipd.sdq.pcm.system.System system;

    private final Repository repository;

    private final Allocation allocation;

    private final ResourceEnvironment resourceEnvironment;

    /**
     * @return returns the allocation model.
     */
    public Allocation getAllocation() {
        return this.allocation;
    }

    /**
     * @return returns the repository model.
     */
    public Repository getRepository() {
        return this.repository;
    }

    /**
     * @return returns the resource environment model.
     */
    public ResourceEnvironment getResourceEnvironment() {
        return this.resourceEnvironment;
    }

    /**
     * @return returns the system model.
     */
    public de.uka.ipd.sdq.pcm.system.System getSystem() {
        return this.system;
    }

    /**
     * @return returns the usage model.
     */
    public UsageModel getUsageModel() {
        return this.usageModel;
    }

    public PCMModels(final Repository repository, final System system, final ResourceEnvironment resourceEnvironment,
            final Allocation allocation, final UsageModel usageModel) {
        super();
        this.repository = repository;
        this.system = system;
        this.resourceEnvironment = resourceEnvironment;
        this.allocation = allocation;
        this.usageModel = usageModel;
    }
}
