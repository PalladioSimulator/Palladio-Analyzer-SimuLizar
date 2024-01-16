package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.function.Supplier;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.exceptions.StochasticExpressionEvaluationFailedException;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class MiddlewareCompletionAwareCalculatorTest extends DemandCalculatorTestBase {

    @Override
    protected void validate(TestCases testCase, Double expected, Supplier<Double> result) {
        // In the following cases there is no "stream.BYTESIZE" characteristic present
        // as this points to an error in the previous completion transformation, we expect
        // an exception to be thrown here.
        if (Arrays
            .asList(TestCases.EMPTY, 
                    TestCases.STACKFRAME_WITH_SINGLE_ELEMENT_WITH_BYTESIZE_100,
                    TestCases.STACKFRAME_WITH_THREE_ELEMENTS_WITH_BYTESIZE_100_200_NONE,
                    TestCases.STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES)
            .contains(testCase)) {
            assertThrows(StochasticExpressionEvaluationFailedException.class, () -> result.get());
        } else {
            super.validate(testCase, expected, result);
        }
    }

    @Override
    protected double getExpectedResult(TestCases testCase) {
        /*
         * This switch case has intentionally no default case. Only then will Eclipse suggest
         * missing cases.
         */
        switch (testCase) {
        case EMPTY:
            return 0.0;
        case STACKFRAME_WITH_SINGLE_ELEMENT_WITH_BYTESIZE_100:
            return 0.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_BYTESIZE_100_200_NONE:
            return 0.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES:
            return 0.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES_WITH_STREAM:
            return 100.0;
        }
        throw new UnsupportedOperationException(
                "This case has been added without adding the expected result to " + getClass().getName());
    }

    @Override
    protected ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> createCalculatorUnderTest() {
        return new MiddlewareCompletionAwareDemandCalculator();
    }

}
