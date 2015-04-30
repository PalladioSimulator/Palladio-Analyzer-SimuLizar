package org.palladiosimulator.simulizar.power.evaluationscope;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.measure.Measure;
import javax.measure.quantity.Duration;

import org.apache.commons.collections15.IteratorUtils;
import org.palladiosimulator.commons.designpatterns.AbstractObservable;
import org.palladiosimulator.edp2.datastream.IDataStream;
import org.palladiosimulator.experimentanalysis.KeepLastElementPriorToLowerBoundStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindow;
import org.palladiosimulator.experimentanalysis.SlidingWindow.ISlidingWindowMoveOnStrategy;
import org.palladiosimulator.experimentanalysis.SlidingWindowRecorder;
import org.palladiosimulator.experimentanalysis.SlidingWindowUtilizationAggregator;
import org.palladiosimulator.measurementframework.MeasuringValue;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcmmeasuringpoint.ActiveResourceMeasuringPoint;
import org.palladiosimulator.pcmmeasuringpoint.PcmmeasuringpointFactory;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.AbstractRecorder;
import org.palladiosimulator.recorderframework.config.IRecorderConfiguration;
import org.palladiosimulator.simulizar.power.calculators.SimulationTimePowerCalculator;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;

import de.fzi.power.infrastructure.PowerProvidingEntity;
import de.fzi.power.interpreter.AbstractEvaluationScope;
import de.fzi.power.interpreter.InterpreterUtils;
import de.uka.ipd.sdq.pcm.resourceenvironment.ProcessingResourceSpecification;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;

/**
 * This class is an implementation of an evaluation scope to gather utilization
 * measurements required for power consumption measurements at runtime, i.e.,
 * during runs of SimuLizar.<br>
 * In the current implementation, the utilization is evaluated for all
 * {@link ProcessingResourceSpecification}s that are supplied by a given
 * {@link PowerProvidingEntity}. That is, the utilization of a
 * {@code ProcessingResourceSpecification} is evaluated if it corresponds to
 * {@code PowerConsumingResource} subsumed by the given
 * {@code PowerProvidingEntity}. In order to keep track of new measurements
 * (i.e., to obtain a new power consumption measurement) client have to attach
 * themselves by calling<br>
 * {@link #addListener(ISimulationEvaluationScopeListener)}.
 * 
 * @author Florian Rosenthal
 * @see SimulationTimePowerCalculator
 * @see ISimulationEvaluationScopeListener
 */
public class SimulationTimeEvaluationScope extends AbstractEvaluationScope {

	private final Collection<ProcessingResourceSpecification> processingResourceSpecs;
	private final SimuComModel simModel;
	private final UtilizationMeasurementsCollector collector;

	private static final MetricDescription UTILIZATION_METRIC = MetricDescriptionConstants.UTILIZATION_OF_ACTIVE_RESOURCE_TUPLE;
	private static final MetricDescription RESOURCE_STATE_METRIC = MetricDescriptionConstants.STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE;

	/**
	 * Gets a {@link SimulationTimeEvaluationScope} instance initialized with
	 * the given parameters.
	 * 
	 * @param entityUnderMeasurement
	 *            The {@link PowerProvidingEntity} that shall be evaluated.
	 * @param model
	 *            A reference indicating the {@link SimuComModel} that is used
	 *            for the current simulation run.
	 * @param windowLength
	 *            The length of the underlying sliding window, given in any
	 *            arbitrary {@link Duration}.
	 * @param windowIncrement
	 *            This {@link Measure} indicates the increment by what the
	 *            underlying sliding window is moved on, given in any arbitrary
	 *            {@link Duration}.
	 * @return A valid {@link SimulationTimeEvaluationScope} instance with the
	 *         given properties.
	 * @throws IllegalArgumentException
	 *             In one of the following cases:
	 *             <ul>
	 *             <li>{@code windowLength} or {@code windowIncrement} are
	 *             {@code null} or denote a negative duration</li>
	 *             <li>{@code entityUnderMeasurement} or {@code model} are
	 *             {@code null}</li>
	 *             </ul>
	 * @throws IllegalStateException
	 *             This exception is thrown, if any of the
	 *             {@link ProcessingResourceSpecification}s subsumed by the
	 *             given {@code entityUnderMeasurement} is not associated with
	 *             {@link MetricDescriptionConstants#STATE_OF_ACTIVE_RESOURCE_METRIC_TUPLE}
	 *             measurements.
	 */
	public static SimulationTimeEvaluationScope createScope(
			PowerProvidingEntity entityUnderMeasurement, SimuComModel model,
			Measure<Double, Duration> windowLength,
			Measure<Double, Duration> windowIncrement) {

		SimulationTimeEvaluationScope scope = new SimulationTimeEvaluationScope(
				entityUnderMeasurement, model);
		scope.initialize(windowLength, windowIncrement);

		return scope;
	}

	/**
	 * Initializes a new instance of the {@link SimulationTimeEvaluationScope}
	 * with the given properties.
	 * 
	 * @param entityUnderMeasurement
	 *            The {@link PowerProvidingEntity} that shall be evaluated.
	 * @param model
	 *            A reference indicating the {@link SimuComModel} that is used
	 *            for the current simulation run.
	 * @throws IllegalArgumentException
	 *             If either of the arguments is {@code null}, an
	 *             {@link IllegalArgumentException} is thrown.
	 * @see #createScope(PowerProvidingEntity, SimuComModel, Measure, Measure)
	 * @see #initialize(Measure, Measure)
	 */
	protected SimulationTimeEvaluationScope(
			PowerProvidingEntity entityUnderMeasurement, SimuComModel model) {
		if (entityUnderMeasurement == null) {
			throw new IllegalArgumentException(
					"Given PowerProvidingEntity must not be null.");
		}
		if (model == null) {
			throw new IllegalArgumentException(
					"Given SimuComModel must not be null.");
		}
		this.simModel = model;
		this.processingResourceSpecs = InterpreterUtils
				.getProcessingResourceSpecsFromInfrastructureElement(entityUnderMeasurement);
		this.collector = new UtilizationMeasurementsCollector(
				this.processingResourceSpecs.size());

		for (ProcessingResourceSpecification spec : this.processingResourceSpecs) {
			IDataStream<MeasuringValue> stream = new SingletonDataStream();
			this.resourceMeasurements.put(spec, Collections.singleton(stream));
		}
	}

	/**
	 * Initializes the current instance by instantiating the underlying
	 * {@link SlidingWindow} and respective aggregator and recorder.
	 * 
	 * @param windowLength
	 *            The length of the underlying sliding window, given in any
	 *            arbitrary {@link Duration}.
	 * @param windowIncrement
	 *            This {@link Measure} indicates the increment by what the
	 *            underlying sliding window is moved on, given in any arbitrary
	 *            {@link Duration}.
	 * @see #createScope(PowerProvidingEntity, SimuComModel, Measure, Measure)
	 * @see #SimulationTimeEvaluationScope(PowerProvidingEntity, SimuComModel)
	 */
	private void initialize(Measure<Double, Duration> windowLength,
			Measure<Double, Duration> windowIncrement) {
		ISlidingWindowMoveOnStrategy moveOnStrategy = new KeepLastElementPriorToLowerBoundStrategy();
		PcmmeasuringpointFactory pcmMeasuringpointFactory = PcmmeasuringpointFactory.eINSTANCE;

		RegisterCalculatorFactoryDecorator actualCalculatorFactory = RegisterCalculatorFactoryDecorator.class
				.cast(this.simModel.getProbeFrameworkContext()
						.getCalculatorFactory());

		for (ProcessingResourceSpecification proc : this.processingResourceSpecs) {
			ActiveResourceMeasuringPoint mp = pcmMeasuringpointFactory
					.createActiveResourceMeasuringPoint();
			mp.setActiveResource(proc);
			Calculator resourceStateCalculator = actualCalculatorFactory
					.getCalculatorByMeasuringPointAndMetricDescription(mp,
							RESOURCE_STATE_METRIC);

			if (resourceStateCalculator == null) {
				throw new IllegalStateException(
						"Simulation time evaluation scope (sliding window based) cannot be initialized.\n"
								+ "No state of active resource calculator available for: "
								+ mp.getStringRepresentation()
								+ "\n"
								+ "Ensure that initializeModelSyncers() in SimulizarRuntimeState is called prior "
								+ "to initializeInterpreterListeners()!");
			}

			SlidingWindow slidingWindow = new SimulizarSlidingWindow(
					windowLength, windowIncrement, RESOURCE_STATE_METRIC,
					moveOnStrategy, this.simModel);
			SlidingWindowRecorder windowRecorder = new SlidingWindowRecorder(
					slidingWindow, new SlidingWindowUtilizationAggregator(
							new ScopeRecorder(proc)));

			resourceStateCalculator.addObserver(windowRecorder);
		}
	}

	@Override
	public void reset() {
		super.reset();
		this.iterator = iterator();
	}

	/**
	 * Adds the given listener to collection of scope observers.
	 * 
	 * @param listener
	 *            The {@link ISimulationEvaluationScopeListener} to observe this
	 *            scope.
	 * @throws IllegalArgumentException
	 *             In case the given listener is {@code null} or already
	 *             attached.
	 * @see #removeListener(ISimulationEvaluationScopeListener)
	 * @see #removeAllListeners()
	 */
	public void addListener(ISimulationEvaluationScopeListener listener) {
		this.collector.addObserver(listener);
	}

	/**
	 * Detaches the given listener from the scope. Prior to that, the listener's
	 * {@link ISimulationEvaluationScopeListener#preUnregister()} callback
	 * implementation is invoked.
	 * 
	 * @param listener
	 *            The {@link ISimulationEvaluationScopeListener} to detach.
	 * @throws IllegalArgumentException
	 *             In case the given listener has not been attached or is
	 *             {@code null}.
	 * @see #addListener(ISimulationEvaluationScopeListener)
	 * @see #removeAllListeners()
	 */
	public void removeListener(ISimulationEvaluationScopeListener listener) {
		listener.preUnregister();
		this.collector.removeObserver(listener);
	}

	/**
	 * Removes all currently attached listeners, i.e., call is equivalent to
	 * invocation of {@link #removeListener(ISimulationEvaluationScopeListener)}
	 * once per attached listener.
	 */
	public void removeAllListeners() {
		for (ISimulationEvaluationScopeListener listener : this.collector
				.getObservers()) {
			removeListener(listener);
		}
	}

	/**
	 * This implementation does nothing.
	 */
	@Override
	public void setResourceMetricsToEvaluate(
			Map<ProcessingResourceSpecification, Set<MetricDescription>> metricsMap) {
		// implementation is not required here
	}

	/**
	 * Implementation of the {@link IDataStream} interface that is internally
	 * used to manage the collected output data per resource. This stream is
	 * exceptional in that it does contain at most one element at a time.
	 * 
	 * @author Florian Rosenthal
	 *
	 */
	private static final class SingletonDataStream implements
			IDataStream<MeasuringValue> {
		private MeasuringValue innerElement;
		private boolean isClosed;

		private static final Iterator<MeasuringValue> EMPTY_ITERATOR = Collections
				.emptyIterator();

		/**
		 * Initializes a new instance of the class.
		 */
		private SingletonDataStream() {
			this.isClosed = false;
			this.innerElement = null;
		}

		@Override
		public Iterator<MeasuringValue> iterator() {
			throwExceptionIfClosed();
			if (this.innerElement == null) {
				return EMPTY_ITERATOR;
			}
			return IteratorUtils.singletonListIterator(this.innerElement);
		}

		@Override
		public MetricDescription getMetricDesciption() {
			return UTILIZATION_METRIC;
		}

		@Override
		public boolean isCompatibleWith(MetricDescription other) {
			return getMetricDesciption().equals(other);
		}

		@Override
		public void close() {
			throwExceptionIfClosed();
			this.isClosed = true;
			this.innerElement = null;
		}

		@Override
		public int size() {
			throwExceptionIfClosed();
			if (this.innerElement == null) {
				return 0;
			}
			return 1;
		}

		/**
		 * Exchanges the currently contained sole element by the given
		 * {@link MeasuringValue}.
		 * 
		 * @param m
		 *            The {@link MeasuringValue} to be stored in the stream.
		 */
		public void exchangeElement(MeasuringValue m) {
			assert m != null;

			throwExceptionIfClosed();
			this.innerElement = m;
		}

		/**
		 * Convenience method to check whether instance is closed (i.e., close()
		 * was called) and throw exception if so.
		 */
		private void throwExceptionIfClosed() {
			if (this.isClosed) {
				throw new IllegalStateException(
						"This stream is already closed.");
			}
		}
	}

	private class UtilizationMeasurementsCollector extends
			AbstractObservable<ISimulationEvaluationScopeListener> {

		private final Map<ProcessingResourceSpecification, MeasuringValue> collectedMeasurements;
		private final int measurementsToCollect;

		public UtilizationMeasurementsCollector(int measurementsToCollect) {
			assert measurementsToCollect > 0;
			this.collectedMeasurements = new HashMap<ProcessingResourceSpecification, MeasuringValue>(
					measurementsToCollect);
			this.measurementsToCollect = measurementsToCollect;
		}

		private void addUtilizationMeasurementForProcessingResource(
				ProcessingResourceSpecification spec,
				MeasuringValue utilMeasurement) {
			assert spec != null && utilMeasurement != null;

			if (this.collectedMeasurements.put(spec, utilMeasurement) == null
					|| !SimulationTimeEvaluationScope.this.simModel
							.getSimulationControl().isRunning()) {
				if (this.collectedMeasurements.size() == this.measurementsToCollect) {
					// one "round" is complete: windows of all specs have
					// produced their utilization measurement
					// so forward data to listeners (e.g., power calculators,
					// consumption contexts), then clear
					for (ProcessingResourceSpecification proc : SimulationTimeEvaluationScope.this.processingResourceSpecs) {
						Set<IDataStream<MeasuringValue>> dataset = SimulationTimeEvaluationScope.this.resourceMeasurements
								.get(proc);
						assert dataset.size() == 1;
						// this cast is safe as we insert only
						// SingletonDataStream instances (cf. ctor)
						SingletonDataStream procMeasurements = (SingletonDataStream) dataset
								.iterator().next();
						procMeasurements
								.exchangeElement(this.collectedMeasurements
										.get(proc));
					}
					resetScope();
					informScopeListeners();
					// start anew
					this.collectedMeasurements.clear();
				}
			} else {
				throw new AssertionError("This should not happen");
			}
		}

		private void resetScope() {
			SimulationTimeEvaluationScope.this.reset();
		}

		private void informScopeListeners() {
			this.getEventDispatcher().newElementAvailable();
		}
	}

	private class ScopeRecorder extends AbstractRecorder {

		private final ProcessingResourceSpecification spec;

		public ScopeRecorder(ProcessingResourceSpecification spec) {
			this.spec = spec;
		}

		@Override
		public void initialize(IRecorderConfiguration recorderConfiguration) {
			// implementation is not required
		}

		@Override
		public void writeData(MeasuringValue measurement) {
			if (measurement == null) {
				throw new IllegalStateException(
						"Somehow 'null' measurement was passed to recorder.");
			}
			// we receive a new utilization measurement now
			if (measurement.isCompatibleWith(UTILIZATION_METRIC)) {
				SimulationTimeEvaluationScope.this.collector
						.addUtilizationMeasurementForProcessingResource(spec,
								measurement);
			}
		}

		@Override
		public void flush() {
			// implementation is not required
		}
	}
}
