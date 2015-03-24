package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.repository.PassiveResource;
import de.uka.ipd.sdq.scheduler.IPassiveResource;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.SimSimpleFairPassiveResource;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

public class SimulatedBasicComponentInstance extends SimulatedComponentInstance {

    private final List<PassiveResource> passiveResourcesList;
    private final Map<String, IPassiveResource> passiveResourcesMap;

    public SimulatedBasicComponentInstance(final InterpreterDefaultContext context, final FQComponentID fqID,
            final List<PassiveResource> passiveResources) {
        super(context.getRuntimeState(), fqID);
        this.passiveResourcesList = passiveResources;

        this.passiveResourcesMap = new HashMap<String, IPassiveResource>();
        final AssemblyContext myAssCtx = fqID.getAssembyContextPath().get(fqID.getAssembyContextPath().size() - 1);
        for (PassiveResource passiveResource : passiveResources) {
            final long initialCount = StackContext.evaluateStatic(passiveResource.getCapacity_PassiveResource()
                    .getSpecification(), Integer.class, context.getStack().currentStackFrame());
            final IPassiveResource simulatedResource = new SimSimpleFairPassiveResource(passiveResource, myAssCtx,
                    getRuntimeState().getModel(), initialCount);
            this.passiveResourcesMap.put(passiveResource.getId(), simulatedResource);

            MeasurementSpecification measurementSpecification = MonitorRepositoryUtil.isMonitored(context
                    .getRuntimeState().getModelAccess().getMonitorRepositoryModel(), passiveResource,
                    MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC);
            if (isMonitored(measurementSpecification)) {
                CalculatorHelper.setupPassiveResourceStateCalculator(simulatedResource, getRuntimeState().getModel());
            }

            measurementSpecification = MonitorRepositoryUtil.isMonitored(context.getRuntimeState().getModelAccess()
                    .getMonitorRepositoryModel(), passiveResource, MetricDescriptionConstants.WAITING_TIME_METRIC);
            if (isMonitored(measurementSpecification)) {
                CalculatorHelper.setupWaitingTimeCalculator(simulatedResource, getRuntimeState().getModel());
            }

            measurementSpecification = MonitorRepositoryUtil.isMonitored(context.getRuntimeState().getModelAccess()
                    .getMonitorRepositoryModel(), passiveResource, MetricDescriptionConstants.HOLDING_TIME_METRIC);
            if (isMonitored(measurementSpecification)) {
                CalculatorHelper.setupHoldTimeCalculator(simulatedResource, getRuntimeState().getModel());
            }
        }
    }

    /**
     * @param measurementSpecification
     *            the measurement specification to check
     * @return true if it is monitored
     */
    private boolean isMonitored(final MeasurementSpecification measurementSpecification) {
        return measurementSpecification != null;
    }

    public void acquirePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context,
            boolean timeout, double timeoutValue) {
        checkAcquireReleasePrecondition(passiveResource);

        passiveResourcesMap.get(passiveResource.getId()).acquire(context.getThread(), 1, timeout, timeoutValue);
    }

    public void releasePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context) {
        checkAcquireReleasePrecondition(passiveResource);

        passiveResourcesMap.get(passiveResource.getId()).release(context.getThread(), 1);
    }

    /**
     * @param passiveResource
     */
    private void checkAcquireReleasePrecondition(final PassiveResource passiveResource) {
        if (passiveResource == null || !passiveResourcesList.contains(passiveResource)) {
            throw new IllegalArgumentException("Illegal passive resource for this basic component instance passed");
        }
    }
}
