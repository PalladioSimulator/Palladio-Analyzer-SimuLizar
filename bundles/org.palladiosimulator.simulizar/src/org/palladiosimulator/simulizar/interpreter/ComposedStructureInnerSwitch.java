package org.palladiosimulator.simulizar.interpreter;

import javax.inject.Inject;

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
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionInterpreter;

import com.google.inject.Key;
import com.google.inject.assistedinject.Assisted;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.resources.IAssemblyAllocationLookup;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This visitor is used to follow assembly connectors inside of composed structures. It is called
 * from an RDSEFF visitor when the RDSEFF visitor tries to resolve the target of an external call.
 *
 * @author Steffen Becker
 *
 */
public class ComposedStructureInnerSwitch extends CompositionSwitch<SimulatedStackframe<Object>> {

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
    private final IResourceTableManager resourceTableManager;

    private final ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> transmissionInterpreter;
    private final IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup;
    private final ComposedStructureInnerSwitchFactory composedStructureSwitchFactory;

    /**
     * Constructor
     *
     * @param modelInterpreter
     *            the corresponding pcm model interpreter holding this switch..
     */
    @Inject
    public ComposedStructureInnerSwitch(
            @Assisted final InterpreterDefaultContext context,
            @Assisted final Signature operationSignature,
            @Assisted final RequiredRole requiredRole,
            ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext> transmissionInterpreter,
            IAssemblyAllocationLookup<EntityReference<ResourceContainer>> resourceContainerLookup,
            ComposedStructureInnerSwitchFactory composedStructureSwitchFactory,
            IResourceTableManager resourceTableManager) {
        super();
        this.context = context;
        this.signature = operationSignature;
        this.requiredRole = requiredRole;
        this.transmissionInterpreter = transmissionInterpreter;
        this.resourceContainerLookup = resourceContainerLookup;
        this.composedStructureSwitchFactory = composedStructureSwitchFactory;
        this.resourceTableManager = resourceTableManager;
    }

    @Deprecated
    public ComposedStructureInnerSwitch(final InterpreterDefaultContext context, final Signature operationSignature,
			final RequiredRole requiredRole, IResourceTableManager resourceTableManager) {
    	this(context, operationSignature, requiredRole, 
    			context.getRuntimeState().getInjector().getInstance(new Key<ITransmissionInterpreter<EntityReference<ResourceContainer>, SimulatedStackframe<Object>, InterpreterDefaultContext>>() {}),
    			context.getRuntimeState().getInjector().getInstance(new Key<IAssemblyAllocationLookup<EntityReference<ResourceContainer>>>() {}),
    			context.getRuntimeState().getInjector().getInstance(ComposedStructureInnerSwitchFactory.class),
    			resourceTableManager);
    }

    @Override
    public SimulatedStackframe<Object> caseAssemblyConnector(final AssemblyConnector assemblyConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                assemblyConnector.getProvidingAssemblyContext_AssemblyConnector(), this.signature,
                assemblyConnector.getProvidedRole_AssemblyConnector(), resourceTableManager);
        var source = resourceContainerLookup
            .getAllocatedEntity(assemblyConnector.getRequiringAssemblyContext_AssemblyConnector());
        var target = resourceContainerLookup
            .getAllocatedEntity(assemblyConnector.getProvidingAssemblyContext_AssemblyConnector());

        transmissionInterpreter.interpretTransmission(source, target, context.getStack().currentStackFrame(), context);
        var result = repositoryComponentSwitch.doSwitch(assemblyConnector.getProvidedRole_AssemblyConnector());
        transmissionInterpreter.interpretTransmission(source, target, result, context);
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.palladiosimulator.pcm.core.composition.util.CompositionSwitch#
     * caseAssemblyInfrastructureConnector
     * (org.palladiosimulator.pcm.core.composition.AssemblyInfrastructureConnector)
     */
    @Override
    public SimulatedStackframe<Object> caseAssemblyInfrastructureConnector(
            final AssemblyInfrastructureConnector assemblyInfrastructureConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                assemblyInfrastructureConnector.getProvidingAssemblyContext__AssemblyInfrastructureConnector(),
                this.signature, assemblyInfrastructureConnector.getProvidedRole__AssemblyInfrastructureConnector(),
                resourceTableManager);
        return repositoryComponentSwitch
            .doSwitch(assemblyInfrastructureConnector.getProvidedRole__AssemblyInfrastructureConnector());
    }

    @Override
    public SimulatedStackframe<Object> caseRequiredDelegationConnector(
            final RequiredDelegationConnector requiredDelegationConnector) {
        final AssemblyContext parentContext = this.context.getAssemblyContextStack().pop();
		final ComposedStructureInnerSwitch composedStructureInnerSwitch = composedStructureSwitchFactory
				.createComposedStructureInnerSwitch(this.context, this.signature,
						requiredDelegationConnector.getOuterRequiredRole_RequiredDelegationConnector());
        final SimulatedStackframe<Object> result = composedStructureInnerSwitch.doSwitch(parentContext);
        this.context.getAssemblyContextStack().push(parentContext);
        return result;
    }

    @Override
    public SimulatedStackframe<Object> caseRequiredInfrastructureDelegationConnector(
            final RequiredInfrastructureDelegationConnector requiredInfrastructureDelegationConnector) {
        final AssemblyContext parentContext = this.context.getAssemblyContextStack().pop();
		final ComposedStructureInnerSwitch composedStructureInnerSwitch = composedStructureSwitchFactory
				.createComposedStructureInnerSwitch(this.context, this.signature,
						requiredInfrastructureDelegationConnector
								.getOuterRequiredRole__RequiredInfrastructureDelegationConnector());
		final SimulatedStackframe<Object> result = composedStructureInnerSwitch.doSwitch(parentContext);
        this.context.getAssemblyContextStack().push(parentContext);
        return result;
    }

    @Override
    public SimulatedStackframe<Object> caseAssemblyContext(final AssemblyContext assemblyContext) {
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
        for (final Connector connector : myContext.getParentStructure__AssemblyContext()
            .getConnectors__ComposedStructure()) {
            final Connector result = connectorSelector.doSwitch(connector);
            if (result != null) {
                return result;
            }
        }
        throw new PCMModelInterpreterException("Found unbound provided role. PCM model is invalid.");
    }
}
