package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.spi.IRegistryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.probfunction.math.IProbabilityFunctionFactory;
import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.probfunction.math.impl.ProbabilityFunctionFactoryImpl;
import de.uka.ipd.sdq.simucomframework.variables.EvaluationProxy;
import de.uka.ipd.sdq.simucomframework.variables.cache.StoExCache;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

abstract class DemandCalculatorTestBase {
    protected enum TestCases {
        EMPTY(() -> new SimulatedStackframe<>()),

        STACKFRAME_WITH_SINGLE_ELEMENT_WITH_BYTESIZE_100(() -> {
            var res = new SimulatedStackframe<>();
            res.addValue("some.variable.with.BYTESIZE", 100);
            return res;
        }),

        STACKFRAME_WITH_THREE_ELEMENTS_WITH_BYTESIZE_100_200_NONE(() -> {
            var res = new SimulatedStackframe<>();
            res.addValue("some.variable.with.BYTESIZE", 100);
            res.addValue("some.variable.with.another.BYTESIZE", new EvaluationProxy("200", res));
            res.addValue("some.value.without.bytesize.characterization", 300);
            return res;
        }),
        
        STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES(() -> {
            var res = new SimulatedStackframe<>();
            res.addValue("some.variable.with.BYTESIZE", 100);
            res.addValue("some.variable.with.another.INNER.BYTESIZE", 100);
            res.addValue("some.value.without.bytesize.characterization", 100);
            res.addValue("some.value.without.bytesize.characterization.BYTESIZE", 100);
            return res;
        }),
        
        STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES_WITH_STREAM(() -> {
            var res = new SimulatedStackframe<>();
            res.addValue("some.variable.with.BYTESIZE", 100);
            res.addValue("some.variable.with.another.INNER.BYTESIZE", 100);
            res.addValue("some.value.without.bytesize.characterization", 100);
            res.addValue("some.value.without.bytesize.characterization.BYTESIZE", 100);
            res.addValue("stream.BYTESIZE", 100);
            return res;
        });
        

        private SimulatedStackframe<Object> stackFrame;

        private TestCases(Supplier<SimulatedStackframe<Object>> testScenarioProvider) {
            stackFrame = testScenarioProvider.get();
        }
    }

    protected abstract double getExpectedResult(TestCases testCase);

    protected abstract ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> createCalculatorUnderTest();

    @BeforeEach
    void init() throws CoreException {
        var probFunctionFactory = ProbabilityFunctionFactoryImpl.getInstance();
        if (RegistryProviderFactory.getDefault() == null) {
            RegistryProviderFactory.setDefault(new IRegistryProvider() {

                @Override
                public IExtensionRegistry getRegistry() {
                    return new ExtensionRegistry(null, "", "");
                }
            });
        }
        Platform.getExtensionRegistry()
            .getConfigurationElementsFor("de.uka.ipd.sdq.stoex.analyser.StoExTypeInference");
        probFunctionFactory.setRandomGenerator(new IRandomGenerator() {

            @Override
            public double random() {
                return Math.random();
            }

            @Override
            public void dispose() {
            }
        });
        StoExCache.initialiseStoExCache(probFunctionFactory);
    }

    @ParameterizedTest
    @EnumSource(TestCases.class)
    protected void testCase(TestCases testCase) {
        var calculator = createCalculatorUnderTest();
        validate(testCase, getExpectedResult(testCase), () -> calculator.calculatePayloadDemand(testCase.stackFrame));
    }
    
    protected void validate(TestCases testCase, Double expected, Supplier<Double> result) {
        assertEquals(expected, result.get());
    }
}
