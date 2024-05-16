package org.palladiosimulator.simulizar.interpreter.linking.impl;

import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionPayloadDemandCalculator;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

class NoDemandCalculatorTest extends DemandCalculatorTestBase {
    @Override
    protected ITransmissionPayloadDemandCalculator<SimulatedStackframe<Object>, Double> createCalculatorUnderTest() {
        return new NoDemandCalculator();
    }

    @Override
    protected double getExpectedResult(TestCases testCase) {
        return 0.0;
    }

}
