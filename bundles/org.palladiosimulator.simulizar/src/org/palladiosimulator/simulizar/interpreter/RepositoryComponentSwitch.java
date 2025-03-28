package org.palladiosimulator.simulizar.interpreter;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.composition.CompositionFactory;
import org.palladiosimulator.pcm.core.composition.CompositionPackage;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.ProvidedDelegationConnector;
import org.palladiosimulator.pcm.core.entity.ComposedProvidingRequiringEntity;
import org.palladiosimulator.pcm.core.entity.EntityPackage;
import org.palladiosimulator.pcm.core.entity.InterfaceProvidingEntity;
import org.palladiosimulator.pcm.repository.BasicComponent;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.util.RepositorySwitch;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.AssemblyProvidedOperationPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.runtimestate.ComponentInstanceRegistry;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstanceFactory;
import org.palladiosimulator.simulizar.runtimestate.SimulatedCompositeComponentInstance;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * @author snowball
 *
 */
public class RepositoryComponentSwitch extends RepositorySwitch<InterpreterResult> {
    @AssistedFactory
    public static interface Factory {
        RepositoryComponentSwitch create(final InterpreterDefaultContext context, final AssemblyContext assemblyContext,
                final Signature signature, final ProvidedRole providedRole);
    }

    private static final Logger LOGGER = Logger.getLogger(RepositoryComponentSwitch.class);
    public static final AssemblyContext SYSTEM_ASSEMBLY_CONTEXT = CompositionFactory.eINSTANCE.createAssemblyContext();

    private final Signature signature;
    private final ProvidedRole providedRole;
    private final InterpreterDefaultContext context;
    private final AssemblyContext instanceAssemblyContext;
    private final IResourceTableManager resourceTableManager;
    private final ComposedRDSeffSwitchFactory rdseffSwitchFactory;
    private final RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory;
    private final ComponentInstanceRegistry componentRegistry;
    private final EventDispatcher eventHelper;
    private final SimulatedBasicComponentInstanceFactory simComponentFactory;

    /**
     * @see RepositoryComponentSwitchFactory#create(InterpreterDefaultContext, AssemblyContext, Signature, ProvidedRole)
     */
    @AssistedInject
    RepositoryComponentSwitch(@Assisted final InterpreterDefaultContext context, @Assisted final AssemblyContext assemblyContext,
            @Assisted final Signature signature, @Assisted final ProvidedRole providedRole, IResourceTableManager resourceTableManager,
            RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory,
            ComponentInstanceRegistry componentRegistry,
            ComposedRDSeffSwitchFactory rdseffSwitchFactory,
            EventDispatcher eventHelper,
            SimulatedBasicComponentInstanceFactory simComponentFactory) {
        super();
        this.context = context;
        this.instanceAssemblyContext = assemblyContext;
        this.signature = signature;
        this.providedRole = providedRole;
        this.resourceTableManager = resourceTableManager;
        this.componentRegistry = componentRegistry;
        this.rdseffSwitchFactory = rdseffSwitchFactory;
        this.repositoryComponentSwitchFactory = repositoryComponentSwitchFactory;
        this.eventHelper = eventHelper;
        this.simComponentFactory = simComponentFactory;
    }

    @Override
    public InterpreterResult caseBasicComponent(final BasicComponent basicComponent) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entering BasicComponent: " + basicComponent);
        }

        // create new stack frame for component parameters
        final SimulatedStack<Object> stack = this.context.getStack();
        final SimulatedStackframe<Object> componentParameterStackFrame = SimulatedStackHelper
                .createAndPushNewStackFrame(stack,
                        basicComponent.getComponentParameterUsage_ImplementationComponentType(),
                        stack.currentStackFrame());

        // create new stack frame for assembly context component parameters
        SimulatedStackHelper.createAndPushNewStackFrame(stack,
                this.instanceAssemblyContext.getConfigParameterUsages__AssemblyContext(), componentParameterStackFrame);

        final FQComponentID fqID = context.computeFQComponentID();
        if (!componentRegistry.hasComponentInstance(fqID)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Found new basic component component instance, registering it: " + basicComponent);
                LOGGER.debug("FQComponentID is " + fqID);
            }
            componentRegistry.addComponentInstance(simComponentFactory.create(this.context, fqID,
                            basicComponent.getPassiveResource_BasicComponent()));
        }

        // get seffs for call
        final List<ServiceEffectSpecification> calledSeffs = this
                .getSeffsForCall(basicComponent.getServiceEffectSpecifications__BasicComponent(), this.signature);

        final var result = this.interpretSeffs(calledSeffs, resourceTableManager);

        // Remove stack frame which contains assembly context parameters
        stack.removeStackFrame();
        // Remove stack frame which contains component parameters
        stack.removeStackFrame();

        return result;
    }

    @Override
    public InterpreterResult caseComposedProvidingRequiringEntity(
            final ComposedProvidingRequiringEntity entity) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entering ComposedProvidingRequiringEntity: " + entity);
        }
        final FQComponentID fqID = context.computeFQComponentID();
        if (!componentRegistry.hasComponentInstance(fqID)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Found new composed component instance, registering it: " + entity);
                LOGGER.debug("FQComponentID is " + fqID);
            }
            componentRegistry.addComponentInstance(new SimulatedCompositeComponentInstance(fqID.getFQIDString()));
        }

        if (entity != this.providedRole.getProvidingEntity_ProvidedRole()) {
            throw new PCMModelInterpreterException("Interpret entity of provided role only");
        }
        final ProvidedDelegationConnector connectedProvidedDelegationConnector = getConnectedProvidedDelegationConnector(
                this.providedRole);
        final RepositoryComponentSwitch repositoryComponentSwitch = repositoryComponentSwitchFactory.create(
                this.context, connectedProvidedDelegationConnector.getAssemblyContext_ProvidedDelegationConnector(),
                this.signature,
                connectedProvidedDelegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
        return Objects.requireNonNull(repositoryComponentSwitch
                .doSwitch(connectedProvidedDelegationConnector.getInnerProvidedRole_ProvidedDelegationConnector()));
    }

    /**
     * @see org.palladiosimulator.pcm.repository.util.CompositionSwitch#caseProvidedRole(org.palladiosimulator.pcm.repository.ProvidedRole)
     */
    @Override
    public InterpreterResult caseProvidedRole(final ProvidedRole providedRole) {
        this.context.getAssemblyContextStack().push(this.instanceAssemblyContext == SYSTEM_ASSEMBLY_CONTEXT
                ? this.generateSystemAssemblyContext(providedRole) : this.instanceAssemblyContext);
        
        eventHelper.firePassedEvent(
        	new AssemblyProvidedOperationPassedEvent<AssemblyContext, ProvidedRole, Signature>(providedRole, 
        			EventType.BEGIN, this.context, this.signature, this.instanceAssemblyContext));

        final var result = this.doSwitch(providedRole.getProvidingEntity_ProvidedRole());

        this.context.getAssemblyContextStack().pop();
        
        eventHelper.firePassedEvent(
            	new AssemblyProvidedOperationPassedEvent<AssemblyContext, ProvidedRole, Signature>(providedRole, 
            			EventType.END, this.context, this.signature, this.instanceAssemblyContext));
        
        return result;
    }

    private AssemblyContext generateSystemAssemblyContext(final ProvidedRole providedRole2) {
        final AssemblyContext result = CompositionFactory.eINSTANCE.createAssemblyContext();
        result.setEntityName(this.providedRole.getProvidingEntity_ProvidedRole().getEntityName());
        result.setId(SYSTEM_ASSEMBLY_CONTEXT.getId());
        return result;
    }

    /**
     * Return the seffs (of different types) from a list of seffs which are affected by the call.
     * Different types are currently not supported by the meta model.
     *
     * @param serviceEffectSpecifications
     *            a list of service effect specifications.
     * @param operationSignature
     *            the operation signature.
     * @return a list of seffs corresponding to the operation signature.
     */
    private List<ServiceEffectSpecification> getSeffsForCall(
            EList<ServiceEffectSpecification> serviceEffectSpecifications, Signature operationSignature) {

        assert serviceEffectSpecifications != null && operationSignature != null;

        // use equality of ids as filter criterion, do not rely on signature equality
        return serviceEffectSpecifications.stream()
                .filter(seff -> seff.getDescribedService__SEFF().getId().equals(operationSignature.getId()))
                .collect(Collectors.toList());
    }

    /**
     * Interpret the given Seffs.
     *
     * @param calledSeffs
     *            a list of seffs.
     */
    private InterpreterResult interpretSeffs(final List<ServiceEffectSpecification> calledSeffs
            , IResourceTableManager resourceTableManager) {
        /*
         * we assume exactly one seff per call, the meta model also allows no seffs, but we omit
         * that in this interpreter
         */
        if (calledSeffs.size() != 1) {
            throw new PCMModelInterpreterException("Only exactly one SEFF is currently supported.");
        }
        if (!(calledSeffs.get(0) instanceof ResourceDemandingSEFF)) {
            throw new PCMModelInterpreterException("Only ResourceDemandingSEFFs are currently supported.");
        } else {
            var interpreter = rdseffSwitchFactory.createRDSeffSwitch(context);
            return Objects.requireNonNull(interpreter.doSwitch(calledSeffs.get(0)));
        }
    }

    /**
     * Determines the provided delegation connector which is connected with the provided role.
     *
     * @param providedRole
     *            the provided role.
     * @return the determined provided delegation connector, null otherwise.
     */
    private static ProvidedDelegationConnector getConnectedProvidedDelegationConnector(
            final ProvidedRole providedRole) {
        final InterfaceProvidingEntity implementingEntity = providedRole.getProvidingEntity_ProvidedRole();
        if (!CompositionPackage.eINSTANCE.getComposedStructure().isSuperTypeOf(implementingEntity.eClass())) {
            throw new PCMModelInterpreterException("Structure used for connector search must be a composed structure");
        }
        for (final Connector connector : ((ComposedStructure) implementingEntity).getConnectors__ComposedStructure()) {
            if (connector.eClass() == CompositionPackage.eINSTANCE.getProvidedDelegationConnector()) {

                if (((ProvidedDelegationConnector) connector).getOuterProvidedRole_ProvidedDelegationConnector()
                        .equals(providedRole)) {
                    return (ProvidedDelegationConnector) connector;
                }
            }
        }
        throw new PCMModelInterpreterException("Found unbound provided role. PCM model is invalid.");
    }

    @Override
    protected InterpreterResult doSwitch(final EClass theEClass, final EObject theEObject) {
        if (EntityPackage.eINSTANCE.getComposedProvidingRequiringEntity().isSuperTypeOf(theEClass)) {
            return this.caseComposedProvidingRequiringEntity((ComposedProvidingRequiringEntity) theEObject);
        }
        return super.doSwitch(theEClass, theEObject);
    }

}
