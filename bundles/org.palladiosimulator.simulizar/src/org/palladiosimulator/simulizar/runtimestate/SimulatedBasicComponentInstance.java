package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.inject.Inject;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import com.google.inject.assistedinject.Assisted;

import de.uka.ipd.sdq.scheduler.IPassiveResource;
import de.uka.ipd.sdq.scheduler.ISchedulableProcess;
import de.uka.ipd.sdq.scheduler.processes.IWaitingProcess;
import de.uka.ipd.sdq.simucomframework.resources.CalculatorHelper;
import de.uka.ipd.sdq.simucomframework.resources.SimSimpleFairPassiveResource;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

public class SimulatedBasicComponentInstance extends SimulatedComponentInstance {

    private final Map<String, IPassiveResource> passiveResourcesMap;
    @Inject
    public SimulatedBasicComponentInstance(@Assisted final InterpreterDefaultContext context,@Assisted final FQComponentID fqID,
            @Assisted final List<PassiveResource> passiveResources, final PCMPartitionManager pcmPartitionManager) {
        super(fqID.getFQIDString());

        this.passiveResourcesMap = new HashMap<String, IPassiveResource>();
        final AssemblyContext myAssCtx = fqID.getAssembyContextPath().get(fqID.getAssembyContextPath().size() - 1);
        for (final PassiveResource passiveResource : passiveResources) {
            final long initialCount = (long) StackContext.evaluateStatic(
                    passiveResource.getCapacity_PassiveResource().getSpecification(), Long.class,
                    context.getStack().currentStackFrame());
            final IPassiveResource simulatedResource = new SimSimpleFairPassiveResource(passiveResource, myAssCtx,
                    context.getModel(), initialCount);
            this.passiveResourcesMap.put(passiveResource.getId(), simulatedResource);

           
            MonitorRepository monitorRepo = pcmPartitionManager.findModel(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository()); 
            
            MeasurementSpecification measurementSpecification = MonitorRepositoryUtil.isMonitored(
                    monitorRepo, passiveResource, MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC);
            if (this.isMonitored(measurementSpecification)) {
                CalculatorHelper.setupPassiveResourceStateCalculator(simulatedResource,
                        context.getModel());
            }

            measurementSpecification = MonitorRepositoryUtil.isMonitored(
                    monitorRepo, passiveResource, MetricDescriptionConstants.WAITING_TIME_METRIC);
            if (this.isMonitored(measurementSpecification)) {
                CalculatorHelper.setupWaitingTimeCalculator(simulatedResource, context.getModel());
            }

            measurementSpecification = MonitorRepositoryUtil.isMonitored(
                    monitorRepo, passiveResource, MetricDescriptionConstants.HOLDING_TIME_METRIC);
            if (this.isMonitored(measurementSpecification)) {
                CalculatorHelper.setupHoldTimeCalculator(simulatedResource, context.getModel());
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
            final boolean timeout, final double timeoutValue) {
        this.checkAcquireReleasePrecondition(passiveResource);

        this.passiveResourcesMap.get(passiveResource.getId()).acquire(context.getThread(), 1, timeout, timeoutValue);
    }

    public void releasePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context) {
        this.checkAcquireReleasePrecondition(passiveResource);

        this.passiveResourcesMap.get(passiveResource.getId()).release(context.getThread(), 1);
    }

    public long getAvailablePassiveResource(final PassiveResource passiveResource, final InterpreterDefaultContext context) {
        this.checkAcquireReleasePrecondition(passiveResource);

        return this.passiveResourcesMap.get(passiveResource.getId()).getAvailable();
    }

    /**
     * @param passiveResource
     */
    private void checkAcquireReleasePrecondition(final PassiveResource passiveResource) {
        if (passiveResource == null || !this.passiveResourcesMap.containsKey(passiveResource.getId())) {
            throw new IllegalArgumentException("Illegal passive resource for this basic component instance passed");
        }
    }

    @Override
    public void cleanUp() {
    	this.passiveResourcesMap.values().stream().map(IPassiveResource::getWaitingProcesses)
    		.flatMap(Queue::stream).map(IWaitingProcess::getProcess).forEach(ISchedulableProcess::activate);
    	super.cleanUp();
    }
}
