package org.palladiosimulator.simulizar.elasticity;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.calculator.IObservableCalculatorRegistry;
import org.palladiosimulator.simulizar.SimuLizarSimulationComponent;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;
import org.palladiosimulator.simulizar.scopes.ExtensionScope;

import dagger.BindsInstance;
import dagger.Component;

@Component(dependencies = { SimuLizarSimulationComponent.class })
@ExtensionScope
public abstract class RegisterCalculatorListenerExtension implements InterpreterExtension {

    abstract IObservableCalculatorRegistry calculatorRegistry();

    abstract ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence();

    @Override
    public void initialized() {
        calculatorRegistry().getRegisteredCalculators()
            .stream()
            .filter(calc -> calc.isCompatibleWith(MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC_TUPLE))
            .forEach(calc -> calc.addObserver(aggregatorWithConfidence()));
    }

    @Component.Factory
    public interface Factory {
        RegisterCalculatorListenerExtension create(SimuLizarSimulationComponent simComponent,
                @BindsInstance ReconfigurationTimeAggregatorWithConfidence aggregator);
    }

}
