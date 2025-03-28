package org.palladiosimulator.simulizar.interpreter;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.core.composition.AssemblyConnector;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector;
import org.palladiosimulator.pcm.core.composition.Connector;
import org.palladiosimulator.pcm.core.composition.RequiredDelegationConnector;
import org.palladiosimulator.pcm.core.composition.RequiredInfrastructureDelegationConnector;
import org.palladiosimulator.pcm.core.composition.util.CompositionSwitch;
import org.palladiosimulator.pcm.repository.RequiredRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.core.entity.EntityReference;
import org.palladiosimulator.simulizar.core.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.simucomframework.core.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This visitor is used to follow assembly connectors inside of composed structures. It is called
 * from an RDSEFF visitor when the RDSEFF visitor tries to resolve the target of an external call.
 *
 * @author Steffen Becker
 *
 */
public class ComposedStructureInnerSwitch extends CompositionSwitch<InterpreterResult> {
    @AssistedFactory
    public static interface Factory {
        ComposedStructureInnerSwitch create(final InterpreterDefaultContext context, final Signature operationSignature,
                final RequiredRole requiredRole);
    }

    /**
     * Logger of this class
     */
    protected static final Logger LOGGER = Logger.getLogger(ComposedStructureInnerSwitch.class.getName());

    /**
     * Context of the simulated thread which resolves an external call
     */
    private final InterpreterDefaultContext context;
    private final Signature signature;
    private final RequiredRole requiredRole;

    private final ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> transmissionInterpreter;
    private final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup;
    private final ComposedStructureInnerSwitch.Factory composedStructureSwitchFactory;

    private final RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory;
    
    private final InterpreterResultMerger resultMerger;

    private final InterpreterResultHandler issueHandler;


    /**
     * @see ComposedStructureInnerSwitchFactory#create(InterpreterDefaultContext, Signature,
     *      RequiredRole)
     */
    @AssistedInject
    ComposedStructureInnerSwitch(@Assisted final InterpreterDefaultContext context,
            @Assisted final Signature operationSignature, @Assisted final RequiredRole requiredRole,
            final ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> transmissionInterpreter,
            final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup,
            final ComposedStructureInnerSwitch.Factory composedStructureSwitchFactory,
            final RepositoryComponentSwitch.Factory repositoryComponentSwitchFactory, InterpreterResultHandler issueHandler,
            InterpreterResultMerger resultMerger) {
        super();
        this.context = context;
        this.signature = operationSignature;
        this.requiredRole = requiredRole;
        this.transmissionInterpreter = transmissionInterpreter;
        this.resourceContainerLookup = resourceContainerLookup;
        this.composedStructureSwitchFactory = composedStructureSwitchFactory;
        this.repositoryComponentSwitchFactory = repositoryComponentSwitchFactory;
        this.issueHandler = issueHandler;
        this.resultMerger = resultMerger;
    }

    @Override
    public InterpreterResult caseAssemblyConnector(final AssemblyConnector assemblyConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = this.repositoryComponentSwitchFactory.create(
                this.context, assemblyConnector.getProvidingAssemblyContext_AssemblyConnector(), this.signature,
                assemblyConnector.getProvidedRole_AssemblyConnector());
        final var source = this.getAllocationTarget(assemblyConnector.getRequiringAssemblyContext_AssemblyConnector());
        final var target = this.getAllocationTarget(assemblyConnector.getProvidingAssemblyContext_AssemblyConnector());

        InterpreterResult result = transmissionInterpreter.interpretTransmission(source, target, context.getStack()
            .currentStackFrame(), context);
        if (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE) {
            result = resultMerger.merge(result,
                    repositoryComponentSwitch.doSwitch(assemblyConnector.getProvidedRole_AssemblyConnector()));
        }
        if (issueHandler.handleIssues(result) == InterpreterResumptionPolicy.CONTINUE) {
            result = resultMerger.merge(result,
                    transmissionInterpreter.interpretTransmission(target, source, context.getResultFrameStack()
                        .peek(), context));
        }
        return result;
    }

    protected EntityReference<ResourceContainer> getAllocationTarget(final AssemblyContext ctx) {
        final var fqid = this.getFQComponentID(ctx);
        return Objects.requireNonNull(this.resourceContainerLookup.getAllocatedEntity(fqid),
                "No allocation registered for assembly context " + fqid);
    }

    protected String getFQComponentID(final AssemblyContext ctx) {
        final var contextStack = this.context.getAssemblyContextStack();

        if (contextStack.size() < 2) {
            return ctx.getId();
        }

        // We have to strip the system context created by simulizar
        return Stream.concat(contextStack.subList(1, contextStack.size())
            .stream(), Stream.of(ctx))
            .map(Identifier::getId)
            .collect(Collectors.joining(FQComponentID.SEPARATOR));
    }

    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.pcm.core.composition.util.CompositionSwitch#
     * caseAssemblyInfrastructureConnector
     * (org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector)
     */
    @Override
    public InterpreterResult caseAssemblyInfrastructureConnector(
            final AssemblyInfrastructureConnector assemblyInfrastructureConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = this.repositoryComponentSwitchFactory.create(
                this.context,
                assemblyInfrastructureConnector.getProvidingAssemblyContext__AssemblyInfrastructureConnector(),
                this.signature, assemblyInfrastructureConnector.getProvidedRole__AssemblyInfrastructureConnector());
        return repositoryComponentSwitch
            .doSwitch(assemblyInfrastructureConnector.getProvidedRole__AssemblyInfrastructureConnector());
    }

    @Override
    public InterpreterResult caseRequiredDelegationConnector(
            final RequiredDelegationConnector requiredDelegationConnector) {
        final AssemblyContext parentContext = this.context.getAssemblyContextStack()
            .pop();
        final ComposedStructureInnerSwitch composedStructureInnerSwitch = this.composedStructureSwitchFactory.create(
                this.context, this.signature,
                requiredDelegationConnector.getOuterRequiredRole_RequiredDelegationConnector());
        final var result = composedStructureInnerSwitch.doSwitch(parentContext);
        this.context.getAssemblyContextStack()
            .push(parentContext);
        return result;
    }

    @Override
    public InterpreterResult caseRequiredInfrastructureDelegationConnector(
            final RequiredInfrastructureDelegationConnector requiredInfrastructureDelegationConnector) {
        final AssemblyContext parentContext = this.context.getAssemblyContextStack()
            .pop();
        final ComposedStructureInnerSwitch composedStructureInnerSwitch = this.composedStructureSwitchFactory
            .create(this.context, this.signature, requiredInfrastructureDelegationConnector
                .getOuterRequiredRole__RequiredInfrastructureDelegationConnector());
        final var result = composedStructureInnerSwitch.doSwitch(parentContext);
        this.context.getAssemblyContextStack()
            .push(parentContext);
        return result;
    }

    @Override
    public InterpreterResult caseAssemblyContext(final AssemblyContext assemblyContext) {
        final Connector connector = getConnectedConnector(assemblyContext, this.requiredRole);
        return this.doSwitch(connector);
    }

    /**
     * Determines the assembly connector which is connected with the required role.
     *
     * @param requiredRole
     *            the required role.
     * @return the determined assembly connector, null otherwise.
     */
    private static Connector getConnectedConnector(final AssemblyContext myContext, final RequiredRole requiredRole) {
        if (myContext == null) {
            throw new IllegalArgumentException("Assembly context must not be null");
        }
        if (requiredRole == null) {
            throw new IllegalArgumentException("Required role must not be null");
        }
        final CompositionSwitch<Connector> connectorSelector = new CompositionSwitch<Connector>() {

            @Override
            public Connector caseRequiredDelegationConnector(final RequiredDelegationConnector delegationConnector) {
                if (delegationConnector.getAssemblyContext_RequiredDelegationConnector() == myContext
                        && delegationConnector.getInnerRequiredRole_RequiredDelegationConnector() == requiredRole) {
                    return delegationConnector;
                }
                return null;
            }

            @Override
            public Connector caseAssemblyConnector(final AssemblyConnector assemblyConnector) {
                if (assemblyConnector.getRequiringAssemblyContext_AssemblyConnector() == myContext
                        && assemblyConnector.getRequiredRole_AssemblyConnector() == requiredRole) {
                    return assemblyConnector;
                }
                return null;
            }

            /*
             * (non-Javadoc)
             *
             * @see org.palladiosimulator.pcm.core.composition.util.CompositionSwitch#
             * caseAssemblyInfrastructureConnector
             * (org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector)
             */
            @Override
            public Connector caseAssemblyInfrastructureConnector(
                    final AssemblyInfrastructureConnector assemblyInfrastructureConnector) {
                if (assemblyInfrastructureConnector
                    .getRequiringAssemblyContext__AssemblyInfrastructureConnector() == myContext
                        && assemblyInfrastructureConnector
                            .getRequiredRole__AssemblyInfrastructureConnector() == requiredRole) {
                    return assemblyInfrastructureConnector;
                }
                return null;
            }

            @Override
            public Connector caseRequiredInfrastructureDelegationConnector(
                    final RequiredInfrastructureDelegationConnector requiredInfrastructureDelegationConnector) {
                if (requiredInfrastructureDelegationConnector
                    .getAssemblyContext__RequiredInfrastructureDelegationConnector() == myContext
                        && requiredInfrastructureDelegationConnector
                            .getInnerRequiredRole__RequiredInfrastructureDelegationConnector() == requiredRole) {
                    return requiredInfrastructureDelegationConnector;
                }
                return null;
            }
        };
        if (myContext.getParentStructure__AssemblyContext() == null) {
            throw new PCMModelInterpreterException("Required delegation of the system cannot be simulated");
        } else {
            for (final Connector connector : myContext.getParentStructure__AssemblyContext()
                .getConnectors__ComposedStructure()) {
                final Connector result = connectorSelector.doSwitch(connector);
                if (result != null) {
                    return result;
                }
            }
        }
        throw new PCMModelInterpreterException("Found unbound provided role. PCM model is invalid.");
    }
}
