package org.palladiosimulator.simulizar.elasticity.jobs;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.palladiosimulator.simulizar.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.elasticity.modules.ElasticityRootModule.NumberOfRunsLimit;

import de.uka.ipd.sdq.workflow.jobs.AbstractJob;
import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;

public class RunElasticityAnalysisJob extends AbstractJob {
	private static final Logger LOGGER = Logger.getLogger(RunElasticityAnalysisJob.class.getName());
	
	private final ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence;
	

    private final int numberOfRunsLimit;

    private final Provider<SimuLizarRootComponent> rootComponentProvider;


	/**
	 * Constructor
	 *
	 * @param configuration
	 *            the SimuCom workflow configuration.
	 */
	@Inject
	public RunElasticityAnalysisJob(Provider<SimuLizarRootComponent> rootComponentProvider,
	        ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence,
	        @NumberOfRunsLimit int numberOfRunsLimit) {
		super();
        this.rootComponentProvider = rootComponentProvider;
		this.aggregatorWithConfidence = aggregatorWithConfidence;
        this.numberOfRunsLimit = numberOfRunsLimit;
	}

	/**
	 * @see de.uka.ipd.sdq.workflow.IJob#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	@Override
	public void execute(final IProgressMonitor monitor) throws JobFailedException, UserCanceledException {
		int numberOfRuns = 0;
		while ((aggregatorWithConfidence == null || !aggregatorWithConfidence.isConfidenceReached()) && numberOfRuns++ < numberOfRunsLimit) {
			LOGGER.info("Elasticity analysis, run No. " + numberOfRuns);

			LOGGER.info("Start job: " + this);

			final var rootJob = rootComponentProvider.get().rootJob(); 
			
			rootJob.execute(monitor);
			
			try {
                rootJob.cleanup(monitor);
            } catch (CleanupFailedException e) {
                throw new JobFailedException("Cleanup of nested simulation failed", e);
            }
			    
			LOGGER.info("finished job: " + this);
		}
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
