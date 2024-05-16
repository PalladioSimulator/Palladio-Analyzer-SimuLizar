package org.palladiosimulator.simulizar.interpreter.linking.impl;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class StackFrameBytesizeAccumulatingCalculatorTest extends DemandCalculatorTestBase {

    @Override
    protected double getExpectedResult(TestCases testCase) {
        /* This switch case has intentionally no default case. Only then will Eclipse suggest missing cases. */
        switch(testCase) {
        case EMPTY:
            return 0.0;
        case STACKFRAME_WITH_SINGLE_ELEMENT_WITH_BYTESIZE_100:
            return 100.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_BYTESIZE_100_200_NONE:
            return 300.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES:
            return 300.0;
        case STACKFRAME_WITH_THREE_ELEMENTS_WITH_INNER_BYTESIZES_WITH_STREAM:
            return 400.0;
        }
        throw new UnsupportedOperationException("This case has been added without adding the expected result to " + getClass().getName());
    }

    @Override
    protected ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> createCalculatorUnderTest() {
        return new StackFrameBytesizeAccumulatingDemandCalculator();
    }

}
