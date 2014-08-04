package org.palladiosimulator.simulizar.interpreter;

import org.apache.log4j.Logger;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyConnector;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.AssemblyInfrastructureConnector;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.composition.RequiredDelegationConnector;
import de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch;
import de.uka.ipd.sdq.pcm.repository.RequiredRole;
import de.uka.ipd.sdq.pcm.repository.Signature;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * This visitor is used to follow assembly connectors inside of composed structures. It is called
 * from an RDSEFF visitor when the RDSEFF visitor tries to resolve the target of an external call.
 * 
 * @author Steffen Becker
 * 
 */
class ComposedStructureInnerSwitch extends CompositionSwitch<SimulatedStackframe<Object>> {
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

    /**
     * Constructor
     * 
     * @param modelInterpreter
     *            the corresponding pcm model interpreter holding this switch..
     */
    public ComposedStructureInnerSwitch(final InterpreterDefaultContext context, final Signature operationSignature,
            final RequiredRole requiredRole) {
        super();
        this.context = context;
        this.signature = operationSignature;
        this.requiredRole = requiredRole;
    }

    @Override
    public SimulatedStackframe<Object> caseAssemblyConnector(final AssemblyConnector assemblyConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                assemblyConnector.getProvidingAssemblyContext_AssemblyConnector(), this.signature,
                assemblyConnector.getProvidedRole_AssemblyConnector());
        return repositoryComponentSwitch.doSwitch(assemblyConnector.getProvidedRole_AssemblyConnector());
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch#caseAssemblyInfrastructureConnector
     * (de.uka.ipd.sdq.pcm.core.composition.AssemblyInfrastructureConnector)
     */
    @Override
    public SimulatedStackframe<Object> caseAssemblyInfrastructureConnector(
            AssemblyInfrastructureConnector assemblyInfrastructureConnector) {
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                assemblyInfrastructureConnector.getProvidingAssemblyContext__AssemblyInfrastructureConnector(),
                this.signature, assemblyInfrastructureConnector.getProvidedRole__AssemblyInfrastructureConnector());
        return repositoryComponentSwitch.doSwitch(assemblyInfrastructureConnector
                .getProvidedRole__AssemblyInfrastructureConnector());
    }

    @Override
    public SimulatedStackframe<Object> caseRequiredDelegationConnector(
            final RequiredDelegationConnector requiredDelegationConnector) {
        final AssemblyContext parentContext = this.context.getAssemblyContextStack().pop();
        final ComposedStructureInnerSwitch composedStructureInnerSwitch = new ComposedStructureInnerSwitch(
                this.context, this.signature,
                requiredDelegationConnector.getOuterRequiredRole_RequiredDelegationConnector());
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
             * @see de.uka.ipd.sdq.pcm.core.composition.util.CompositionSwitch#
             * caseAssemblyInfrastructureConnector
             * (de.uka.ipd.sdq.pcm.core.composition.AssemblyInfrastructureConnector)
             */
            @Override
            public Connector caseAssemblyInfrastructureConnector(
                    AssemblyInfrastructureConnector assemblyInfrastructureConnector) {
                if (assemblyInfrastructureConnector.getRequiringAssemblyContext__AssemblyInfrastructureConnector() == myContext
                        && assemblyInfrastructureConnector.getRequiredRole__AssemblyInfrastructureConnector() == requiredRole) {
                    return assemblyInfrastructureConnector;
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
