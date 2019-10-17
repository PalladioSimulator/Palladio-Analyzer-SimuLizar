package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.edp2.util.MetricDescriptionUtility;
import org.palladiosimulator.measurementframework.listener.IMeasurementSourceListener;
import org.palladiosimulator.metricspec.BaseMetricDescription;
import org.palladiosimulator.metricspec.MetricDescription;
import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.CalculatorRegistryListener;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;

/**
 * This facade allows to defer registrations for measurements which are
 * initialized lazily during simulation.
 * 
 * Currently it depends on the existence of a
 * {@code RegisterCalculatorFactoryDecorator} to which a listener for new
 * calculators is attached.
 * 
 * 
 * @author Sebastian Krach
 *
 */
public abstract class DeferredMeasurementInitialization {
    /**
     * This internal class contains the registry listener logic and is not build to
     * be extensible.
     */
    private static final class DeferredMeasurementInitializationImpl extends DeferredMeasurementInitialization
            implements CalculatorRegistryListener {

        private final RegisterCalculatorFactoryDecorator factory;
        private final Map<String, Map<MetricDescription, Set<Supplier<IMeasurementSourceListener>>>> deferredInitializations = new HashMap<>();

        /**
         * This constructor is private as it is not supposed to be used directly.
         */
        private DeferredMeasurementInitializationImpl(final RegisterCalculatorFactoryDecorator factory) {
            this.factory = factory;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void notifyCalculatorRegistration(Calculator calculator) {
            synchronized (deferredInitializations) {
                if (deferredInitializations.containsKey(calculator.getMeasuringPoint().getStringRepresentation())) {
                    Map<MetricDescription, Set<Supplier<IMeasurementSourceListener>>> callbacks = deferredInitializations
                            .get(calculator.getMeasuringPoint().getStringRepresentation());
                    callbacks.keySet().stream().filter(metricDesc -> {
                        if (metricDesc.getId().equals(calculator.getMetricDesciption().getId())
                                || (metricDesc instanceof BaseMetricDescription
                                        && MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                                                (BaseMetricDescription) metricDesc,
                                                calculator.getMetricDesciption()))) {
                            deferredInitializations.get(calculator.getMeasuringPoint().getStringRepresentation())
                                    .get(metricDesc).stream().map(Supplier::get).forEach(calculator::addObserver);
                            return true;
                        }
                        return false;
                    }).collect(Collectors.toList()).forEach(callbacks::remove);
                    if (callbacks.isEmpty()) {
                        deferredInitializations.remove(calculator.getMeasuringPoint().getStringRepresentation());
                    }
                    if (deferredInitializations.isEmpty()) {
                        factory.removeObserver(this);
                    }
                }
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void onMetricDescriptionAndMeasuringPoint(final MetricDescription desc, final MeasuringPoint mp,
                Supplier<IMeasurementSourceListener> supplier) {
            Objects.requireNonNull(desc);
            Objects.requireNonNull(mp);
            Objects.requireNonNull(supplier);

            synchronized (deferredInitializations) {
                if (deferredInitializations.isEmpty()) {
                    factory.addObserver(this);
                }
                Optional<Calculator> baseCalculator = getBaseCalculator(desc, mp);
                if (baseCalculator.isPresent()) {
                    baseCalculator.get().addObserver(supplier.get());
                } else {
                    deferredInitializations
                            .computeIfAbsent(mp.getStringRepresentation(),
                                    (s) -> new HashMap<MetricDescription, Set<Supplier<IMeasurementSourceListener>>>())
                            .computeIfAbsent(desc, (d) -> new HashSet<Supplier<IMeasurementSourceListener>>())
                            .add(supplier);
                }
            }
        }

        private Optional<Calculator> getBaseCalculator(final MetricDescription metric,
                final MeasuringPoint measuringPoint) {
            Calculator baseCalculator = factory.getCalculatorByMeasuringPointAndMetricDescription(measuringPoint,
                    metric);
            if (baseCalculator == null && metric instanceof BaseMetricDescription) {
                return factory.getCalculatorsForMeasuringPoint(measuringPoint).stream()
                        .filter(calc -> MetricDescriptionUtility.isBaseMetricDescriptionSubsumedByMetricDescription(
                                (BaseMetricDescription) metric, calc.getMetricDesciption()))
                        .findAny();

            }
            return Optional.ofNullable(baseCalculator);
        }
    }

    /**
     * Returns the facade to be used to defer recorder registrations. This operation
     * ensures, that there is only one registration facade per
     * {@code RegisterCalculatorFactoryDecorator}.
     * 
     * @param the decorated calculator factory.
     * @return the appropriate registration facade.
     */
    public static DeferredMeasurementInitialization forCalculatorFactoryDecorator(
            RegisterCalculatorFactoryDecorator factory) {
        return factory.getObservers().stream().filter(DeferredMeasurementInitializationImpl.class::isInstance)
                .map(DeferredMeasurementInitializationImpl.class::cast).filter(dmi -> dmi.factory.equals(factory))
                .findAny().orElseGet(() -> new DeferredMeasurementInitializationImpl(factory));
    }

    /**
     * Registers a provider of an {@code IMeasurementSourceListener}. The listener
     * is requested once a calculator which fits to {@code desc} and {@code mp} is
     * registered. If the calculator is already registered, the provider is
     * requested directly.
     * 
     * If {@code desc} is of type {@code BaseMetricDescription}, the provided
     * listener will also be registered if a calculator provides measurements which
     * contain the {@code BaseMetricDescription}.
     * 
     * @param desc     The metric description of the measurements produced by the
     *                 required calculator.
     * @param mp       The measuring point of the measurements produced by the
     *                 required calculator
     * @param supplier A provider of the listener, e. g. a chained recorder.
     */
    public abstract void onMetricDescriptionAndMeasuringPoint(final MetricDescription desc, final MeasuringPoint mp,
            Supplier<IMeasurementSourceListener> supplier);

}
