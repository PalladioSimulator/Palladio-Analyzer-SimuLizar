package org.palladiosimulator.simulizar.elasticity.aggregator;

import static org.palladiosimulator.metricspec.constants.MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE;

import javax.inject.Inject;

import org.palladiosimulator.probeframework.calculator.Calculator;
import org.palladiosimulator.probeframework.calculator.CalculatorRegistryListener;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.di.core.scopes.RuntimeExtensionScope;
import org.palladiosimulator.simulizar.runtimestate.RuntimeStateEntityObserver;

@RuntimeExtensionScope
public class AggregatorAttachingRuntimeStateObserver implements RuntimeStateEntityObserver, CalculatorRegistryListener {
    
    private final IObservableCalculatorRegistry calculatorRegistry;
    private final ReconfigurationTimeAggregatorWithConfidence aggregator;

    @Inject
    public AggregatorAttachingRuntimeStateObserver(IObservableCalculatorRegistry calculatorRegistry, ReconfigurationTimeAggregatorWithConfidence aggregator) {
        this.calculatorRegistry = calculatorRegistry;
        this.aggregator = aggregator;
    }
    
    @Override
    public void initialize() {
        calculatorRegistry.addObserver(this);
    }

    @Override
    public void notifyCalculatorRegistration(Calculator calculator) {
        if (calculator.isCompatibleWith(RECONFIGURATION_TIME_METRIC_TUPLE)) {
            calculator.addObserver(aggregator);
        }
    }

}
