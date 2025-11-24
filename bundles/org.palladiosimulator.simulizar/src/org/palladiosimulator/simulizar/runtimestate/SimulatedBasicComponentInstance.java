package org.palladiosimulator.simulizar.runtimestate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.MonitorRepository;
import org.palladiosimulator.monitorrepository.MonitorRepositoryPackage;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.PassiveResource;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager.Global;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.legacy.CalculatorFactoryFacade;
import org.palladiosimulator.simulizar.utils.MonitorRepositoryUtil;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.scheduler.IPassiveResource;
import de.uka.ipd.sdq.scheduler.ISchedulableProcess;
import de.uka.ipd.sdq.scheduler.processes.IWaitingProcess;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.core.resources.SimSimpleFairPassiveResource;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;

public class SimulatedBasicComponentInstance extends SimulatedComponentInstance {
    
    private final Map<String, IPassiveResource> passiveResourcesMap;

    @AssistedInject
    public SimulatedBasicComponentInstance(@Assisted final InterpreterDefaultContext context, @Assisted final FQComponentID fqID,
            @Assisted final List<PassiveResource> passiveResources, SimuComModel simuComModel, @Global PCMResourceSetPartition partition,
            CalculatorFactoryFacade calcFactory) {
        super(fqID.getFQIDString());

        this.passiveResourcesMap = new HashMap<String, IPassiveResource>();
        final AssemblyContext myAssCtx = fqID.getAssembyContextPath().get(fqID.getAssembyContextPath().size() - 1);
        for (final PassiveResource passiveResource : passiveResources) {
            final long initialCount = (long) StackContext.evaluateStatic(
                    passiveResource.getCapacity_PassiveResource().getSpecification(), Long.class,
                    context.getStack().currentStackFrame());
            final IPassiveResource simulatedResource = new SimSimpleFairPassiveResource(passiveResource, myAssCtx,
                    simuComModel, initialCount);
            this.passiveResourcesMap.put(passiveResource.getId(), simulatedResource);

            partition.<MonitorRepository> getElement(MonitorRepositoryPackage.eINSTANCE.getMonitorRepository())
                .forEach(monitorRepo -> {
                MeasurementSpecification measurementSpecification = MonitorRepositoryUtil.isMonitored(
                        monitorRepo, passiveResource, MetricDescriptionConstants.STATE_OF_PASSIVE_RESOURCE_METRIC);
                if (this.isMonitored(measurementSpecification)) {
                    calcFactory.setupPassiveResourceStateCalculator(simulatedResource);
                }

                measurementSpecification = MonitorRepositoryUtil.isMonitored(
                        monitorRepo, passiveResource, MetricDescriptionConstants.WAITING_TIME_METRIC);
                if (this.isMonitored(measurementSpecification)) {
                    calcFactory.setupWaitingTimeCalculator(simulatedResource);
                }

                measurementSpecification = MonitorRepositoryUtil.isMonitored(
                        monitorRepo, passiveResource, MetricDescriptionConstants.HOLDING_TIME_METRIC);
                if (this.isMonitored(measurementSpecification)) {
                    calcFactory.setupHoldTimeCalculator(simulatedResource);
                }
            });
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
