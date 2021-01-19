package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.eclipse.emf.ecore.util.Switch;
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
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.variables.StackContext;
import de.uka.ipd.sdq.simucomframework.variables.converter.NumberConverter;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

public class RDSeffPerformanceSwitch extends SeffPerformanceSwitch<Object> implements IComposableSwitch {
    @AssistedFactory
    public interface Factory {
        RDSeffPerformanceSwitch create(InterpreterDefaultContext context, ComposedSwitch<Object> parentSwitch);
    }

    private final InterpreterDefaultContext context;
    private final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup;
    private final ComposedSwitch<Object> parentSwitch;
    private final ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> rcAccess;
    private final ComposedStructureInnerSwitchFactory composedSwitchFactory;
    

    @AssistedInject
    public RDSeffPerformanceSwitch(@Assisted InterpreterDefaultContext context,
            @Assisted ComposedSwitch<Object> parentSwitch,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> allocationLookup,
            ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> rcAccess,
            ComposedStructureInnerSwitchFactory composedSwitchFactory) {
        this.context = context;
        this.allocationLookup = allocationLookup;
        this.parentSwitch = parentSwitch;
        this.rcAccess = rcAccess;
        this.composedSwitchFactory = composedSwitchFactory;
    }
    
    @Override
    public Object caseParametricResourceDemand(ParametricResourceDemand parametricResourceDemand) {
        final String idRequiredResourceType = parametricResourceDemand
                .getRequiredResource_ParametricResourceDemand().getId();
        final String specification = parametricResourceDemand.getSpecification_ParametericResourceDemand()
                .getSpecification();
        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
        final Double value = StackContext.evaluateStatic(specification, Double.class, currentStackFrame);

        var rcEntity = allocationLookup.getAllocatedEntity(context.computeFQComponentID().getFQIDString());
        rcAccess.getSimulatedEntity(rcEntity.getId()).loadActiveResource(this.context.getThread(), idRequiredResourceType, value);
        
        return RDSeffSwitch.SUCCESS;
    }
    
    @Override
    public Object caseResourceCall(ResourceCall resourceCall) {
        // find the corresponding resource type which was invoked by the resource call
        final ResourceInterface resourceInterface = resourceCall.getSignature__ResourceCall()
                .getResourceInterface__ResourceSignature();
        final ResourceRepository resourceRepository = resourceInterface.getResourceRepository__ResourceInterface();
        ResourceType currentResourceType = null;

        for (final ResourceType resourceType : resourceRepository.getAvailableResourceTypes_ResourceRepository()) {
            for (final ResourceProvidedRole resourceProvidedRole : resourceType
                    .getResourceProvidedRoles__ResourceInterfaceProvidingEntity()) {
                if (resourceProvidedRole.getProvidedResourceInterface__ResourceProvidedRole().getId()
                        .equals(resourceInterface.getId())) {
                    currentResourceType = resourceType;
                    break;
                }
            }
        }

        final ResourceSignature resourceSignature = resourceCall.getSignature__ResourceCall();
        final int resourceServiceId = resourceSignature.getResourceServiceId();

        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
        final Double evaluatedDemand = NumberConverter.toDouble(
                StackContext.evaluateStatic(resourceCall.getNumberOfCalls__ResourceCall().getSpecification(),
                        Double.class, currentStackFrame));
        final String idRequiredResourceType = currentResourceType.getId();

        var rcEntity = allocationLookup.getAllocatedEntity(context.computeFQComponentID()
            .getFQIDString());
        rcAccess.getSimulatedEntity(rcEntity.getId())
            .loadActiveResource(context.getThread(), resourceServiceId, idRequiredResourceType, evaluatedDemand);
        return RDSeffSwitch.SUCCESS;
    }
    
    @Override
    public Object caseInfrastructureCall(InfrastructureCall infrastructureCall) {
        final SimulatedStackframe<Object> currentStackFrame = this.context.getStack().currentStackFrame();
        final int repetitions = StackContext.evaluateStatic(
                infrastructureCall.getNumberOfCalls__InfrastructureCall().getSpecification(), Integer.class,
                currentStackFrame);
        for (int i = 0; i < repetitions; i++) {
            final ComposedStructureInnerSwitch composedStructureSwitch = composedSwitchFactory.create(this.context,
                    infrastructureCall.getSignature__InfrastructureCall(),
                    infrastructureCall.getRequiredRole__InfrastructureCall());
            // create new stack frame for input parameter
            SimulatedStackHelper.createAndPushNewStackFrame(this.context.getStack(),
                    infrastructureCall.getInputVariableUsages__CallAction());
            final AssemblyContext myContext = this.context.getAssemblyContextStack().pop();
            composedStructureSwitch.doSwitch(myContext);
            this.context.getAssemblyContextStack().push(myContext);
            this.context.getStack().removeStackFrame();
        }
        return RDSeffSwitch.SUCCESS;
        
    }
    
    @Override
    public Switch<Object> getParentSwitch() {
        return parentSwitch;
    }
    
}
