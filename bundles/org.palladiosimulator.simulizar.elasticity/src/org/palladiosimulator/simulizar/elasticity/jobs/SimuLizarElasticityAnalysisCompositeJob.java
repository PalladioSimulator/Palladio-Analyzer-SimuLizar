package org.palladiosimulator.simulizar.elasticity.jobs;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.di.component.core.DaggerSimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.modules.component.extensions.ExtensionComponentsModule;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.elasticity.di.components.DaggerElasticityRuntimeExtensionComponent;
import org.palladiosimulator.simulizar.elasticity.di.components.ElasticityAnalysisComponent;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import com.google.common.collect.ImmutableSet;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.DynamicSequentialJob;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobProxy;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class SimuLizarElasticityAnalysisCompositeJob extends DynamicSequentialJob {
    private static final Logger LOGGER = Logger.getLogger(SimuLizarElasticityAnalysisCompositeJob.class);

    private final SimuLizarWorkflowConfiguration configuration;
    private final ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence;
    private final Provider<ElasticityAnalysisComponent> rootComponentProvider;

    private static int NUMBER_OF_RUNS_LIMIT = 50;
    private int numberOfRuns = 0;

    /**
     * Constructor
     *
     * @param configuration
     *            the SimuCom workflow configuration.
     */
    @Inject
    public SimuLizarElasticityAnalysisCompositeJob(final SimuLizarWorkflowConfiguration configuration,
            ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence,
            Provider<ElasticityAnalysisComponent> rootComponentProvider) {
        super(true);
        this.configuration = configuration;
        this.aggregatorWithConfidence = aggregatorWithConfidence;
        this.rootComponentProvider = rootComponentProvider;
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {

        LOGGER.info("Elasticity analysis started, running first job");
        this.addJob(new JobProxy(simuLizarJobSupplier()));
        this.add(new IJob() {

            @Override
            public String getName() {
                return "Check if confidence is reached";
            }

            @Override
            public void execute(IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
                if (!(aggregatorWithConfidence == null || !aggregatorWithConfidence.isConfidenceReached())
                        && numberOfRuns++ < NUMBER_OF_RUNS_LIMIT) {
                    LOGGER.info("Elasticity determined necessity to run another job. Run No. " + numberOfRuns);
                    SimuLizarElasticityAnalysisCompositeJob.this.addJob(new JobProxy(simuLizarJobSupplier()));
                    SimuLizarElasticityAnalysisCompositeJob.this.addJob(this);
                }
            }

            @Override
            public void cleanup(IProgressMonitor monitor) throws CleanupFailedException {
            }
        });
        LOGGER.info("finished job: " + this);
    }

    private Supplier<IJob> simuLizarJobSupplier() {
        return () -> DaggerSimuLizarRootComponent.factory()
            .create(configuration,
                    new ExtensionComponentsModule(ImmutableSet.of(DaggerElasticityRuntimeExtensionComponent.factory()),
                            ImmutableSet.of(rootComponentProvider.get())))
            .rootJob();
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#getName()
     */
    @Override
    public String getName() {
        return "Run SimuLizar Elasticity Analysis";
    }

    /**
     * @see de.uka.ipd.sdq.workflow.IJob#rollback(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void cleanup(final IProgressMonitor monitor) throws CleanupFailedException {
    }
}
