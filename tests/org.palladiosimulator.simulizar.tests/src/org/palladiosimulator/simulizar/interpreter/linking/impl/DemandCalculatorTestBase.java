package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.probfunction.math.IRandomGenerator;
import de.uka.ipd.sdq.probfunction.math.impl.ProbabilityFunctionFactoryImpl;
import de.uka.ipd.sdq.simucomframework.variables.EvaluationProxy;
import de.uka.ipd.sdq.simucomframework.variables.cache.StoExCache;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import tools.mdsd.junit5utils.extensions.PlatformStandaloneExtension;

@ExtendWith(PlatformStandaloneExtension.class)
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

    @BeforeAll
    static void initStoExCache() {
                
        var probFunctionFactory = ProbabilityFunctionFactoryImpl.getInstance();
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
