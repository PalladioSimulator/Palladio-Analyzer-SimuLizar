package org.palladiosimulator.simulizar.elasticity;

import java.util.Set;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.extension.AbstractSimuLizarExtension;
import org.palladiosimulator.simulizar.extension.facets.InterpreterExtension;

import com.google.common.collect.ImmutableSet;

public class ElasticityAnalysisExtension extends AbstractSimuLizarExtension {
    private final InterpreterExtension.Factory interpreterExtensionFactory;

    @Inject
    public ElasticityAnalysisExtension(RegisterCalculatorListenerExtension.Factory calculatorListenerFactory,
            ReconfigurationTimeAggregatorWithConfidence aggregator) {
        this.interpreterExtensionFactory = simulationComponent -> DaggerRegisterCalculatorListenerExtension.factory()
                .create(simulationComponent, aggregator);
    }

    @Override
    public Set<org.palladiosimulator.simulizar.extension.facets.InterpreterExtension.Factory> getInterpreterExtensions() {
        return ImmutableSet.of(interpreterExtensionFactory);
    }
}
