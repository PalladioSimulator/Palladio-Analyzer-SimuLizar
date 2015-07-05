package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.PredicateUtils;
import org.apache.commons.collections15.Transformer;
import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.Monitor;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.StatisticalCharacterizationEnum;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.ICalculatorFactory;
import org.palladiosimulator.probeframework.probes.EventProbeList;
import org.palladiosimulator.probeframework.probes.Probe;
import org.palladiosimulator.probeframework.probes.TriggeredProbe;
import org.palladiosimulator.runtimemeasurement.RuntimeMeasurementModel;
import org.palladiosimulator.simulizar.access.IModelAccess;
import org.palladiosimulator.simulizar.metrics.aggregators.ResponseTimeAggregator;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.probes.TakeCurrentSimulationTimeProbe;
import de.uka.ipd.sdq.simucomframework.probes.TakeNumberOfResourceContainersProbe;

/**
 * Class for listening to interpreter events in order to store collected data
 * using the ProbeFramework
 * 
 * @author Steffen Becker, Sebastian Lehrig, Florian Rosenthal
 */
public class ProbeFrameworkListener extends AbstractInterpreterListener
		implements IReconfigurationListener {

	private static final Logger LOGGER = Logger
			.getLogger(ProbeFrameworkListener.class);
	private static final int START_PROBE_INDEX = 0;
	private static final int STOP_PROBE_INDEX = 1;

	private final MonitorRepository monitorRepositoryModel;
	private final RuntimeMeasurementModel runtimeMeasurementsModel;
	private final SimuComModel simuComModel;
	private final ICalculatorFactory calculatorFactory;
	private final Reconfigurator reconfigurator;

	private final Map<String, List<TriggeredProbe>> currentTimeProbes = new HashMap<String, List<TriggeredProbe>>();

	/**
	 * @param modelAccessFactory
	 *            Provides access to simulated models
	 * @param simuComModel
	 *            Provides access to the central simulation
	 */
	public ProbeFrameworkListener(final IModelAccess modelAccessFactory,
			final SimuComModel simuComModel, final Reconfigurator reconfigurator) {
		super();
		this.monitorRepositoryModel = modelAccessFactory
				.getMonitorRepositoryModel();
		this.runtimeMeasurementsModel = modelAccessFactory
				.getRuntimeMeasurementModel();
		this.calculatorFactory = simuComModel.getProbeFrameworkContext()
				.getCalculatorFactory();
		this.simuComModel = simuComModel;
		this.reconfigurator = reconfigurator;

		initReponseTimeMeasurement();
		initReconfigurationTimeMeasurement();
		initNumberOfResourceContainersMeasurements();
		initExtensionMeasurements();
	}

	private void initExtensionMeasurements() {
		Iterable<AbstractRecordingProbeFrameworkListenerDecorator> extensions = ExtensionHelper
				.getExecutableExtensions(
						"org.palladiosimulator.simulizar.interpreter.listener.probeframework",
						"decorator");
		for (AbstractRecordingProbeFrameworkListenerDecorator decorator : extensions) {
			decorator.setProbeFrameworkListener(this);
			decorator.registerMeasurements();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
	 * beginUsageScenarioInterpretation
	 * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginUsageScenarioInterpretation(
			final ModelElementPassedEvent<UsageScenario> event) {
		this.startMeasurement(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
	 * endUsageScenarioInterpretation
	 * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endUsageScenarioInterpretation(
			final ModelElementPassedEvent<UsageScenario> event) {
		this.endMeasurement(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
	 * beginEntryLevelSystemCallInterpretation
	 * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginEntryLevelSystemCallInterpretation(
			final ModelElementPassedEvent<EntryLevelSystemCall> event) {
		this.startMeasurement(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#
	 * endEntryLevelSystemCallInterpretation
	 * (de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endEntryLevelSystemCallInterpretation(
			final ModelElementPassedEvent<EntryLevelSystemCall> event) {
		this.endMeasurement(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
	 * beginExternalCallInterpretation
	 * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginExternalCallInterpretation(
			final RDSEFFElementPassedEvent<ExternalCallAction> event) {
		this.startMeasurement(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.upb.pcm.simulizar.interpreter.listener.AbstractInterpreterListener#
	 * endExternalCallInterpretation
	 * (de.upb.pcm.simulizar.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endExternalCallInterpretation(
			final RDSEFFElementPassedEvent<ExternalCallAction> event) {
		this.endMeasurement(event);
	}

	@Override
	public <T extends EObject> void beginUnknownElementInterpretation(
			ModelElementPassedEvent<T> event) {
	}

	@Override
	public <T extends EObject> void endUnknownElementInterpretation(
			ModelElementPassedEvent<T> event) {
	}

	/**
	 * Gets the {@link SimuComModel} which is related to this instance.
	 * 
	 * @return A reference to the {@link SimuComModel} currently in use.
	 */
	public SimuComModel getSimuComModel() {
		return this.simuComModel;
	}

	/**
	 * Gets the {@link RuntimeMeasurementModel} attached to this instance.
	 * 
	 * @return A reference to the {@code RuntimeMeasurementModel}.
	 */
	public RuntimeMeasurementModel getRuntimeMeasurementModel() {
		return this.runtimeMeasurementsModel;
	}

	/**
	 * Gets the {@link ICalculatorFactory} that is by this instance during the
	 * current simulation run.
	 * 
	 * @return A reference to the {@code ICalculatorFactory}.
	 */
	public ICalculatorFactory getCalculatorFactory() {
		return this.calculatorFactory;
	}

	/**
	 * Gets all associated {@link MeasurementSpecification}s that adhere to the
	 * given metric.
	 * 
	 * @param soughtFor
	 *            A {@link MetricDescription} denoting the target metric to look
	 *            for.
	 * @return An UNMODIFIABLE {@link Collection} containing all found
	 *         measurement Specifications, which might be empty but never
	 *         {@code null}.
	 */
	public Collection<MeasurementSpecification> getMeasurementSpecificationsForMetricDescription(
			final MetricDescription soughtFor) {
		assert soughtFor != null;
		if (this.monitorRepositoryModel != null) {
			Transformer<Monitor, MeasurementSpecification> transformer = new Transformer<Monitor, MeasurementSpecification>() {

				@Override
				public MeasurementSpecification transform(Monitor monitor) {
					for (MeasurementSpecification m : monitor
							.getMeasurementSpecifications()) {
						if (MetricDescriptionUtility.metricDescriptionIdsEqual(
								m.getMetricDescription(), soughtFor)) {
							return m;
						}
					}
					return null;
				}
			};
			return Collections.unmodifiableCollection(CollectionUtils.select(
					CollectionUtils.collect(
							this.monitorRepositoryModel.getMonitors(),
							transformer), PredicateUtils.notNullPredicate()));
		}
		return Collections.emptyList();
	}

	/**
	 * Initializes the <i>response time</i> measurements. First gets the
	 * monitored elements from the monitor repository, then creates
	 * corresponding calculators and aggregators.
	 * 
	 */
	private void initReponseTimeMeasurement() {
		for (MeasurementSpecification responseTimeMeasurementSpec : getMeasurementSpecificationsForMetricDescription(MetricDescriptionConstants.RESPONSE_TIME_METRIC)) {
			final MeasuringPoint measuringPoint = responseTimeMeasurementSpec
					.getMonitor().getMeasuringPoint();
			final List<Probe> probeList = createStartAndStopProbe(
					measuringPoint, this.simuComModel);
			final Calculator calculator = this.calculatorFactory
					.buildResponseTimeCalculator(measuringPoint, probeList);

			if (responseTimeMeasurementSpec.getStatisticalCharacterization() != StatisticalCharacterizationEnum.NONE) {
				try {
					IMeasurementSourceListener aggregator = new ResponseTimeAggregator(
							this.simuComModel, this.runtimeMeasurementsModel,
							responseTimeMeasurementSpec, measuringPoint);
					calculator.addObserver(aggregator);
				} catch (final UnsupportedOperationException e) {
					LOGGER.error(e);
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * Initializes the <i> number of resource containers</i> measurements. First
	 * gets the monitored elements from the monitor repository, then creates
	 * corresponding calculators.
	 * 
	 */
	private void initNumberOfResourceContainersMeasurements() {
		for (MeasurementSpecification numberOfResourceContainersMeasurementSpec : getMeasurementSpecificationsForMetricDescription(MetricDescriptionConstants.NUMBER_OF_RESOURCE_CONTAINERS)) {
			MeasuringPoint measuringPoint = numberOfResourceContainersMeasurementSpec
					.getMonitor().getMeasuringPoint();

			final Probe probe = new EventProbeList(
					new TakeNumberOfResourceContainersProbe(
							simuComModel.getResourceRegistry()),
					Arrays.asList((TriggeredProbe) new TakeCurrentSimulationTimeProbe(
							simuComModel.getSimulationControl())));
			calculatorFactory.buildNumberOfResourceContainersCalculator(
					measuringPoint, probe);
		}
	}

	/**
	 * @param measuringPoint
	 * @param simuComModel
	 * @return list with start and stop probe
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected List<Probe> createStartAndStopProbe(
			final MeasuringPoint measuringPoint, final SimuComModel simuComModel) {
		List probeList = new ArrayList<TriggeredProbe>(2);
		probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel
				.getSimulationControl()));
		probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel
				.getSimulationControl()));
		final EObject modelElement = MonitorRepositoryUtil
				.getMonitoredElement(measuringPoint);
		currentTimeProbes.put(((Entity) modelElement).getId(),
				Collections.unmodifiableList(probeList));
		return probeList;
	}

	/**
	 * @param modelElement
	 * @return
	 */
	protected boolean entityIsAlreadyInstrumented(final EObject modelElement) {
		return this.currentTimeProbes.containsKey(((Entity) modelElement)
				.getId());
	}

	/**
	 * @param <T>
	 * @param event
	 */
	private <T extends Entity> void startMeasurement(
			final ModelElementPassedEvent<T> event) {
		if (this.currentTimeProbes.containsKey(((Entity) event
				.getModelElement()).getId()) && simulationIsRunning()) {
			this.currentTimeProbes
					.get(((Entity) event.getModelElement()).getId())
					.get(START_PROBE_INDEX)
					.takeMeasurement(event.getThread().getRequestContext());
		}
	}

	/**
	 * @param event
	 */
	private <T extends Entity> void endMeasurement(
			final ModelElementPassedEvent<T> event) {
		if (this.currentTimeProbes.containsKey(((Entity) event
				.getModelElement()).getId()) && simulationIsRunning()) {
			this.currentTimeProbes
					.get(((Entity) event.getModelElement()).getId())
					.get(STOP_PROBE_INDEX)
					.takeMeasurement(event.getThread().getRequestContext());
		}
	}

	@Override
	public void beginSystemOperationCallInterpretation(
			ModelElementPassedEvent<OperationSignature> event) {
		if (this.currentTimeProbes.containsKey(((Entity) event
				.getModelElement()).getId()) && simulationIsRunning()) {
			this.currentTimeProbes
					.get(((Entity) event.getModelElement()).getId())
					.get(START_PROBE_INDEX)
					.takeMeasurement(event.getThread().getRequestContext());
		}
	}

	@Override
	public void endSystemOperationCallInterpretation(
			ModelElementPassedEvent<OperationSignature> event) {
		if (this.currentTimeProbes.containsKey(((Entity) event
				.getModelElement()).getId()) && simulationIsRunning()) {
			this.currentTimeProbes
					.get(((Entity) event.getModelElement()).getId())
					.get(STOP_PROBE_INDEX)
					.takeMeasurement(event.getThread().getRequestContext());
		}
	}

	@Override
	public void beginReconfigurationEvent(BeginReconfigurationEvent event) {
		if (this.currentTimeProbes.containsKey("Reconfiguration")
				&& simulationIsRunning()) {
			this.currentTimeProbes.get("Reconfiguration")
					.get(START_PROBE_INDEX).takeMeasurement();
		}
	}

	@Override
	public void endReconfigurationEvent(EndReconfigurationEvent event) {
		if (this.currentTimeProbes.containsKey("Reconfiguration")
				&& simulationIsRunning()) {
			this.currentTimeProbes.get("Reconfiguration").get(STOP_PROBE_INDEX)
					.takeMeasurement();
		}
	}

	@Override
	public void reconfigurationExecuted(ReconfigurationExecutedEvent event) {
		// Nothing to do
	}

	/**
	 * Initializes reconfiguration time measurement.
	 * 
	 */
	@SuppressWarnings({ "unchecked" })
	private void initReconfigurationTimeMeasurement() {

		for (MeasurementSpecification reconfigurationTimeMeasurementSpec : getMeasurementSpecificationsForMetricDescription(MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC)) {
			MeasuringPoint measuringPoint = reconfigurationTimeMeasurementSpec
					.getMonitor().getMeasuringPoint();

			LOGGER.info("Created Reconfiguration Time Measuring Point");

			@SuppressWarnings("rawtypes")
			List probeList = new ArrayList<TriggeredProbe>(2);
			probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel
					.getSimulationControl()));
			probeList.add(new TakeCurrentSimulationTimeProbe(simuComModel
					.getSimulationControl()));

			currentTimeProbes.put("Reconfiguration",
					Collections.unmodifiableList(probeList));

			this.calculatorFactory.buildReconfigurationTimeCalculator(
					measuringPoint, probeList);

			this.reconfigurator.addObserver(this);

		}
	}

	private boolean simulationIsRunning() {
		return this.simuComModel.getSimulationControl().isRunning();
	}
}
