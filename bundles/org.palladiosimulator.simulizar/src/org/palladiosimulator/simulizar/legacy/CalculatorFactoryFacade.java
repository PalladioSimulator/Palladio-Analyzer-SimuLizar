package org.palladiosimulator.simulizar.legacy;

import javax.inject.Inject;
import javax.inject.Provider;

import org.palladiosimulator.edp2.models.measuringpoint.MeasuringPoint;
import org.palladiosimulator.probeframework.calculator.Calculator;

import de.uka.ipd.sdq.scheduler.IPassiveResource;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.core.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.core.resources.CalculatorHelper;

public class CalculatorFactoryFacade {

    private final Provider<SimuComModel> simuComModelProvider;

    @Inject
    public CalculatorFactoryFacade(Provider<SimuComModel> simuComModelProvider) {
        this.simuComModelProvider = simuComModelProvider;
    }

    public Calculator setupActiveResourceStateCalculator(final AbstractScheduledResource scheduledResource,
            final MeasuringPoint measuringPoint, final int replicaID) {
        return CalculatorHelper.setupActiveResourceStateCalculator(scheduledResource, simuComModelProvider.get(),
                measuringPoint, replicaID);
    }

    public Calculator setupDemandCalculator(final AbstractScheduledResource scheduledResource,
            final MeasuringPoint measuringPoint) {
        return CalculatorHelper.setupDemandCalculator(scheduledResource, simuComModelProvider.get(), measuringPoint);
    }

    public Calculator setupOverallUtilizationCalculator(final AbstractScheduledResource scheduledResource,
            final MeasuringPoint measuringPoint) {
        return CalculatorHelper.setupOverallUtilizationCalculator(scheduledResource, simuComModelProvider.get(),
                measuringPoint);
    }

    public Calculator setupPassiveResourceStateCalculator(IPassiveResource simulatedResource) {
        return CalculatorHelper.setupPassiveResourceStateCalculator(simulatedResource, simuComModelProvider.get());
    }

    public Calculator setupWaitingTimeCalculator(IPassiveResource simulatedResource) {
        return CalculatorHelper.setupWaitingTimeCalculator(simulatedResource, simuComModelProvider.get());
    }

    public Calculator setupHoldTimeCalculator(IPassiveResource simulatedResource) {
        return CalculatorHelper.setupHoldTimeCalculator(simulatedResource, simuComModelProvider.get());
    }

}
