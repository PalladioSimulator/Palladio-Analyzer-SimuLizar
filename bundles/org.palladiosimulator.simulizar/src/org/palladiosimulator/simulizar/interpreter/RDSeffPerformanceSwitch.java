package org.palladiosimulator.simulizar.interpreter;

import java.util.Objects;

import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.entity.ResourceProvidedRole;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourcetype.ResourceInterface;
import org.palladiosimulator.pcm.resourcetype.ResourceRepository;
import org.palladiosimulator.pcm.resourcetype.ResourceSignature;
import org.palladiosimulator.pcm.resourcetype.ResourceType;
import org.palladiosimulator.pcm.seff.seff_performance.InfrastructureCall;
import org.palladiosimulator.pcm.seff.seff_performance.ParametricResourceDemand;
import org.palladiosimulator.pcm.seff.seff_performance.ResourceCall;
import org.palladiosimulator.pcm.seff.seff_performance.util.SeffPerformanceSwitch;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.RDSeffSwitchContributionFactory.RDSeffElementDispatcher;
import org.palladiosimulator.simulizar.interpreter.preinterpretation.PreInterpretationBehaviorContainer;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;
import org.palladiosimulator.simulizar.runtimestate.PreInterpretationBehaviorManager;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class RDSeffPerformanceSwitch extends SeffPerformanceSwitch<InterpreterResult> {
    @AssistedFactory
    public interface Factory extends RDSeffSwitchContributionFactory {
        @Override
        public RDSeffPerformanceSwitch createRDSeffSwitch(InterpreterDefaultContext context,
                RDSeffElementDispatcher parentSwitch);
    }

    private final InterpreterDefaultContext context;
    private final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup;
    private final ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> rcAccess;
    private final ComposedStructureInnerSwitch.Factory composedSwitchFactory;
    private final PreInterpretationBehaviorManager pibManager;
    private final InterpreterResultHandler issueHandler;
    private final InterpreterResultMerger resultMerger;

    @AssistedInject
    public RDSeffPerformanceSwitch(@Assisted InterpreterDefaultContext context,
            @Assisted RDSeffElementDispatcher parentSwitch,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> rcAccess,
            ComposedStructureInnerSwitch.Factory composedSwitchFactory, InterpreterResultHandler issueHandler,
            InterpreterResultMerger resultMerger, PreInterpretationBehaviorManager pibManager) {
        this.context = context;
        this.allocationLookup = allocationLookup;
        this.rcAccess = rcAccess;
        this.composedSwitchFactory = composedSwitchFactory;
        this.issueHandler = issueHandler;
        this.resultMerger = resultMerger;
        this.pibManager = pibManager;
    }

    @Override
    public InterpreterResult caseParametricResourceDemand(ParametricResourceDemand parametricResourceDemand) {
        final String idRequiredResourceType = parametricResourceDemand.getRequiredResource_ParametricResourceDemand()
            .getId();
        final String specification = parametricResourceDemand.getSpecification_ParametericResourceDemand()
            .getSpecification();
        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack()
            .currentStackFrame();
        final Double value = StackContext.evaluateStatic(specification, Double.class, currentStackFrame);

        var fqid = context.computeFQComponentID()
            .getFQIDString();
        var rcEntity = Objects.requireNonNull(allocationLookup.getAllocatedEntity(fqid),
                () -> "No allocation found for assembly identified by " + fqid);

        // Search for pre-interpretation-behaviors to execute them before loadActiveResource()
        // For example to stop interpretation through InterpretationIssue of HWCrashFailure
        InterpreterResult result = InterpreterResult.OK;
        for (AbstractScheduledResource r : rcAccess.getSimulatedEntity(rcEntity.getId())
                .getAllActiveResources().values()) {
            String resourceId = r.getName();
            if (pibManager.hasContainerAlreadyBeenRegisteredForEntity(resourceId)) {
                PreInterpretationBehaviorContainer pibContainer = pibManager.getContainerForEntity(resourceId);
                //TODO inject context??
                InterpreterResult newResult = pibContainer.executeBehaviors(null);
                result = resultMerger.merge(result, newResult);
            }
        }
        if (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE) {
            rcAccess.getSimulatedEntity(rcEntity.getId())
                .loadActiveResource(this.context.getThread(), idRequiredResourceType, value);
        }
        return result;
    }

    @Override
    public InterpreterResult caseResourceCall(ResourceCall resourceCall) {
        // find the corresponding resource type which was invoked by the resource call
        final ResourceInterface resourceInterface = resourceCall.getSignature__ResourceCall()
            .getResourceInterface__ResourceSignature();
        final ResourceRepository resourceRepository = resourceInterface.getResourceRepository__ResourceInterface();
        ResourceType currentResourceType = null;

        for (final ResourceType resourceType : resourceRepository.getAvailableResourceTypes_ResourceRepository()) {
            for (final ResourceProvidedRole resourceProvidedRole : resourceType
                .getResourceProvidedRoles__ResourceInterfaceProvidingEntity()) {
                if (resourceProvidedRole.getProvidedResourceInterface__ResourceProvidedRole()
                    .getId()
                    .equals(resourceInterface.getId())) {
                    currentResourceType = resourceType;
                    break;
                }
            }
        }

        final ResourceSignature resourceSignature = resourceCall.getSignature__ResourceCall();
        final int resourceServiceId = resourceSignature.getResourceServiceId();

        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack()
            .currentStackFrame();
        final Double evaluatedDemand = NumberConverter
            .toDouble(StackContext.evaluateStatic(resourceCall.getNumberOfCalls__ResourceCall()
                .getSpecification(), Double.class, currentStackFrame));
        final String idRequiredResourceType = currentResourceType.getId();

        var rcEntity = allocationLookup.getAllocatedEntity(context.computeFQComponentID()
            .getFQIDString());
        rcAccess.getSimulatedEntity(rcEntity.getId())
            .loadActiveResource(context.getThread(), resourceServiceId, idRequiredResourceType, evaluatedDemand);
        return InterpreterResult.OK;
    }

    @Override
    public InterpreterResult caseInfrastructureCall(InfrastructureCall infrastructureCall) {
        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack()
            .currentStackFrame();
        final int repetitions = StackContext.evaluateStatic(infrastructureCall.getNumberOfCalls__InfrastructureCall()
            .getSpecification(), Integer.class, currentStackFrame);
        for (int i = 0; i < repetitions; i++) {
            final ComposedStructureInnerSwitch composedStructureSwitch = composedSwitchFactory.create(this.context,
                    infrastructureCall.getSignature__InfrastructureCall(),
                    infrastructureCall.getRequiredRole__InfrastructureCall());
            // create new stack frame for input parameter
            SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                    infrastructureCall.getInputVariableUsages__CallAction());
            final AssemblyContext myContext = this.context.getAssemblyContextStack()
                .pop();
            composedStructureSwitch.doSwitch(myContext);
            this.context.getAssemblyContextStack()
                .push(myContext);
            this.context.getStack()
                .removeStackFrame();
        }
        return InterpreterResult.OK;
    }
}
