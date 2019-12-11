package org.palladiosimulator.simulizar.launcher.jobs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.measure.Measure;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.analyzer.workflow.jobs.LoadPCMModelsIntoBlackboardJob;
import org.palladiosimulator.edp2.datastream.IDataSource;
import org.palladiosimulator.edp2.datastream.edp2source.Edp2DataTupleDataSource;
import org.palladiosimulator.edp2.impl.RepositoryManager;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentGroup;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentSetting;
import org.palladiosimulator.edp2.models.ExperimentData.Measurement;
import org.palladiosimulator.edp2.models.ExperimentData.RawMeasurements;
import org.palladiosimulator.edp2.models.Repository.Repository;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.MetricSetDescription;
import org.palladiosimulator.recorderframework.edp2.config.AbstractEDP2RecorderConfigurationFactory;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjective;
import org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository;
import org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage;
import org.palladiosimulator.servicelevelobjective.edp2.mappers.SLOViolationEDP2DatasourceMapper;
import org.palladiosimulator.servicelevelobjective.edp2.mappers.SLOViolationEDP2DatasourceMapperConfiguration;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;

import de.uka.ipd.sdq.workflow.jobs.CleanupFailedException;
import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.SequentialBlackboardInteractingJob;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class EvaluateResultsJob extends SequentialBlackboardInteractingJob<MDSDBlackboard> {

	private final Logger LOGGER = Logger.getLogger(EvaluateResultsJob.class);
	private final SimuLizarWorkflowConfiguration configuration;
	private ExperimentSetting experimentSetting;
	private EList<ServiceLevelObjective> serviceLevelObjectives;

	public EvaluateResultsJob(final SimuLizarWorkflowConfiguration configuration) {
		super();
		this.configuration = configuration;
	}

	@Override
	public void cleanup(final IProgressMonitor arg0) throws CleanupFailedException {
		// TODO Auto-generated method stub
	}

	@Override
	public void execute(final IProgressMonitor progressMonitor) throws JobFailedException, UserCanceledException {

		this.serviceLevelObjectives = filterSloRepo().getServicelevelobjectives();
        if (this.serviceLevelObjectives == null) {
			this.LOGGER.info("No Service level objectives provided. Skipping evaluation of experiment data");
		} else {
			final String repositoryId = (String) this.configuration.getAttributes()
					.get(AbstractEDP2RecorderConfigurationFactory.REPOSITORY_ID);
			final String basename = this.configuration.getSimulationConfiguration().getNameBase();
			final String variation = this.configuration.getSimulationConfiguration().getVariationId();

			final Repository repository = RepositoryManager.getRepositoryFromUUID(repositoryId);
			final ExperimentGroup experimentGroup = this.getExperimentGroup(repository, basename);
			this.experimentSetting = this.getExperimentSetting(experimentGroup, variation);

			this.LOGGER.info("Evaluating data in repository " + repository.getId() + " in experiment run " + basename);

			final int lastExperiment = this.experimentSetting.getExperimentRuns().size() - 1;
			this.experimentSetting.getExperimentRuns().get(lastExperiment);

			final double[] sloViolations = this.computeSloViolations();
			if (sloViolations[1] == 0) {
				this.LOGGER.info("THE STATE WITH NO SLO VIOLATIONS WAS REACHED.");
				progressMonitor.setCanceled(true);
				progressMonitor.done();
			}
			this.LOGGER.info(
					sloViolations[0] + " Measurements evaluated for " + sloViolations[1] + " Service Level Objectives. "
							+ sloViolations[2] + " Failed." + " Average fulfillment grade:  " + sloViolations[3]);
		}
	}

	private ServiceLevelObjectiveRepository filterSloRepo() {
    	final PCMResourceSetPartition partition = (PCMResourceSetPartition) this.getBlackboard()
                .getPartition(LoadPCMModelsIntoBlackboardJob.PCM_MODELS_PARTITION_ID);
    	if (partition == null) {
    		return null;
    	}
    	
    	EClass targetType = ServicelevelObjectivePackage.eINSTANCE.getServiceLevelObjectiveRepository();
    	List<ServiceLevelObjectiveRepository> sloRepos = partition.getElement(targetType);
    	return sloRepos.isEmpty() ? null : sloRepos.get(0);
	}
	
	@Override
	public String getName() {
		return "Evaluating Analysis Results";
	}

	/**
	 * Computes the number of SLO violations and the average SLO fulfillment grade
	 *
	 * @return In Order: number of Measurements, number of SLOs, number of Failures, average fulfillment Grade
	 */
	private double[] computeSloViolations() {
		final int lastExperiment = this.experimentSetting.getExperimentRuns().size() - 1;
		final ExperimentRun experimentRun = this.experimentSetting.getExperimentRuns().get(lastExperiment);

		double totalMeasurements = 0;
		double sloViolations = 0;
		double sloGradeSum = 0;
		double numberOfSlos = 0;

		for (final ServiceLevelObjective serviceLevelObjective : this.serviceLevelObjectives) {
			numberOfSlos++;
			final Measurement measurement = this.findMeasurement(experimentRun.getMeasurement(), serviceLevelObjective);
			final RawMeasurements rawMeasurements = measurement.getMeasurementRanges().get(0).getRawMeasurements();

			final Map<String, Object> properties = new HashMap<String, Object>(1);
			properties.put(SLOViolationEDP2DatasourceMapperConfiguration.SLO_KEY, serviceLevelObjective);

			final IDataSource dataSource = new Edp2DataTupleDataSource(rawMeasurements);
			final MetricDescription metricDescription = dataSource.getMetricDesciption();
			final SLOViolationEDP2DatasourceMapper sloMapper = new SLOViolationEDP2DatasourceMapper(dataSource,
					metricDescription);
			sloMapper.setProperties(properties);
			totalMeasurements += dataSource.getDataStream().size();
			HashMap<Measure, Double> evaluatedGrades = sloMapper.getMapping();
			for (Map.Entry<Measure, Double> entry : evaluatedGrades.entrySet()) {
				sloGradeSum += entry.getValue();
				if (entry.getValue() == 0) {
					sloViolations += 1;
				}
			}
		}

		final double[] result = new double[4];

		if (numberOfSlos > 0) {
			result[0] = totalMeasurements/numberOfSlos;
		}
		result[1] = numberOfSlos;
		result[2] = sloViolations;
		if (totalMeasurements > 0) {
			result[3] = sloGradeSum / totalMeasurements;
		} else {
			result[3] = 0;
		}

		return result;
	}

	/**
	 * Finds the measurements referenced by the SLO in the given measurements lists.
	 * For identification, this methods tries to match metric IDs and measuring
	 * point names.
	 *
	 * @param measurementList       the list of measurements to be investigated for
	 *                              a match.
	 * @param serviceLevelObjective the SLO providing metric and measuring point for
	 *                              matching.
	 * @return the found measurements object.
	 * @throws RuntimeException if no measurements object can be found.
	 */
	private Measurement findMeasurement(final List<Measurement> measurementList,
			final ServiceLevelObjective serviceLevelObjective) {
		for (final Measurement measurement : measurementList) {
			if (this.containsMetric(measurement.getMeasuringType().getMetric(),
					serviceLevelObjective.getMeasurementSpecification().getMetricDescription())) {
				final String measureMeasuringPoint = measurement.getMeasuringType().getMeasuringPoint()
						.getStringRepresentation();
				final String sloMeasuringPoint = serviceLevelObjective.getMeasurementSpecification().getMonitor()
						.getMeasuringPoint().getStringRepresentation();

				// TODO Comparing the name of Measuring points is not the best solution (as the
				// name
				// is generally not unique). I see three options, all requiring some
				// architectural
				// refactoring at several places: (1) Require that every measuring point is an
				// URI
				// measuring point -- then, the URI could be used to uniquely identify the
				// measuring
				// point; (2) just use 1 measuring point for the same entity at a time -- then
				// two
				// equal measuring points objects literally point to the same entity. For the
				// latter, we should attach measuring points directly to PCM elements and make
				// them
				// intrinsic part of such elements; (3) create a MeasuringPointUtility method
				// for
				// generating a measuring points ID based on the type of measuring point; URI
				// measuring points should generate the same ID as dedicated PCM measuring
				// points if
				// they reference the same entity. Options should be discussed if this becomes
				// an
				// important issue. [Lehrig]
				if (measureMeasuringPoint.equals(sloMeasuringPoint)) {
					return measurement;
				}
			}
		}
		throw new RuntimeException("Measurement for SLO \"" + serviceLevelObjective.getName() + "\" not found");
	}

	private boolean containsMetric(final MetricDescription metric, final MetricDescription metricToCheckFor) {
		if (metric == metricToCheckFor || metric.getId().equals(metricToCheckFor.getId())) {
			return true;
		}

		if (metric instanceof MetricSetDescription) {
			for (final MetricDescription subMetric : ((MetricSetDescription) metric).getSubsumedMetrics()) {
				if (this.containsMetric(subMetric, metricToCheckFor)) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Returns an experiment group object from the given repository, based on the
	 * experiment group purpose member variable.
	 *
	 * @param repository the repository containing the experiment group.
	 * @param purpose    the unique name of an experiment run; used to identify the
	 *                   experiment group of the last analysis run.
	 * @return the experiment group of interest.
	 */
	private ExperimentGroup getExperimentGroup(final Repository repository, final String purpose) {
		for (final ExperimentGroup experimentGroup : repository.getExperimentGroups()) {
			if (experimentGroup.getPurpose().equals(purpose)) {
				return experimentGroup;
			}
		}

		throw new IllegalArgumentException("Could not find experiment group with purpose \"" + purpose + "\"");
	}

	/**
	 * Returns the experiment setting from the given experiment group that is
	 * identified by the unique experiment setting description string.
	 *
	 * @param experimentGroup              the experiment group to be investigated.
	 * @param experimentSettingDescription the unique experiment setting description
	 *                                     identifier.
	 * @return the experiment setting whose description matches the given identifier
	 *         string.
	 */
	private ExperimentSetting getExperimentSetting(final ExperimentGroup experimentGroup,
			final String experimentSettingDescription) {
		for (final ExperimentSetting expSetting : experimentGroup.getExperimentSettings()) {
			if (expSetting.getDescription().equals(experimentSettingDescription)) {
				return expSetting;
			}
		}

		throw new IllegalArgumentException(
				"Could not find experiment setting for variation \"" + experimentSettingDescription + "\"");
	}
	
}
