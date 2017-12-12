package org.palladiosimulator.simulizar.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.ComposedSwitch;
import org.palladiosimulator.commons.eclipseutils.ExtensionHelper;
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
import org.palladiosimulator.pcm.repository.RepositoryComponent;
import org.palladiosimulator.pcm.repository.RepositoryPackage;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.repository.util.RepositorySwitch;
import org.palladiosimulator.pcm.seff.ResourceDemandingSEFF;
import org.palladiosimulator.pcm.seff.ServiceEffectSpecification;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.interpreter.listener.AssemblyProvidedOperationPassedEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventType;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;
import org.palladiosimulator.simulizar.runtimestate.SimulatedBasicComponentInstance;
import org.palladiosimulator.simulizar.runtimestate.SimulatedCompositeComponentInstance;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * @author snowball
 *
 */
class RepositoryComponentSwitch extends RepositorySwitch<SimulatedStackframe<Object>> {

    private static final Logger LOGGER = Logger.getLogger(RepositoryComponentSwitch.class);
    public static final AssemblyContext SYSTEM_ASSEMBLY_CONTEXT = CompositionFactory.eINSTANCE.createAssemblyContext();
    public static final String RDSEFFSWITCH_EXTENSION_POINT_ID = "org.palladiosimulator.simulizar.interpreter.rdseffswitch";
    public static final String RDSEFFSWITCH_EXTENSION_ATTRIBUTE = "rdseffswitch";

    private final Signature signature;
    private final ProvidedRole providedRole;
    private final InterpreterDefaultContext context;
    private final AssemblyContext instanceAssemblyContext;

    /**
     *
     */
    public RepositoryComponentSwitch(final InterpreterDefaultContext context, final AssemblyContext assemblyContext,
            final Signature signature, final ProvidedRole providedRole) {
        super();
        this.context = context;
        this.instanceAssemblyContext = assemblyContext;
        this.signature = signature;
        this.providedRole = providedRole;
    }

    @Override
    public SimulatedStackframe<Object> caseBasicComponent(final BasicComponent basicComponent) {
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

        final FQComponentID fqID = this.computeFQComponentID();
        if (!this.context.getRuntimeState().getComponentInstanceRegistry().hasComponentInstance(fqID)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Found new basic component component instance, registering it: " + basicComponent);
                LOGGER.debug("FQComponentID is " + fqID);
            }
            this.context.getRuntimeState().getComponentInstanceRegistry()
                    .addComponentInstance(new SimulatedBasicComponentInstance(this.context, fqID,
                            basicComponent.getPassiveResource_BasicComponent()));
        }

        // get seffs for call
        final List<ServiceEffectSpecification> calledSeffs = this
                .getSeffsForCall(basicComponent.getServiceEffectSpecifications__BasicComponent(), this.signature);

        final SimulatedStackframe<Object> result = this.interpretSeffs(calledSeffs);

        /*
         * Remove created stack frame (including stack frame created for the results of an external
         * call in RDSEFF, done in RDSEFF Interpreter).
         */
        stack.removeStackFrame();
        stack.removeStackFrame();

        return result;
    }

    @Override
    public SimulatedStackframe<Object> caseComposedProvidingRequiringEntity(
            final ComposedProvidingRequiringEntity entity) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Entering ComposedProvidingRequiringEntity: " + entity);
        }
        final FQComponentID fqID = this.computeFQComponentID();
        if (!this.context.getRuntimeState().getComponentInstanceRegistry().hasComponentInstance(fqID)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Found new composed component instance, registering it: " + entity);
                LOGGER.debug("FQComponentID is " + fqID);
            }
            this.context.getRuntimeState().getComponentInstanceRegistry().addComponentInstance(
                    new SimulatedCompositeComponentInstance(this.context.getRuntimeState(), fqID.getFQIDString()));
        }

        if (entity != this.providedRole.getProvidingEntity_ProvidedRole()) {
            throw new PCMModelInterpreterException("Interpret entity of provided role only");
        }
        final ProvidedDelegationConnector connectedProvidedDelegationConnector = getConnectedProvidedDelegationConnector(
                this.providedRole);
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                connectedProvidedDelegationConnector.getAssemblyContext_ProvidedDelegationConnector(), this.signature,
                connectedProvidedDelegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
        return repositoryComponentSwitch
                .doSwitch(connectedProvidedDelegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
    }

    /**
     * @see org.palladiosimulator.pcm.repository.util.CompositionSwitch#caseProvidedRole(org.palladiosimulator.pcm.repository.ProvidedRole)
     */
    @Override
    public SimulatedStackframe<Object> caseProvidedRole(final ProvidedRole providedRole) {
        this.context.getAssemblyContextStack().push(this.instanceAssemblyContext == SYSTEM_ASSEMBLY_CONTEXT
                ? this.generateSystemAssemblyContext(providedRole) : this.instanceAssemblyContext);
        
        this.context.getRuntimeState().getEventNotificationHelper().firePassedEvent(
        	new AssemblyProvidedOperationPassedEvent<ProvidedRole, Signature>(providedRole, 
        			EventType.BEGIN, this.context.getThread(), this.signature, this.instanceAssemblyContext));

        final SimulatedStackframe<Object> result = this.doSwitch(providedRole.getProvidingEntity_ProvidedRole());

        this.context.getAssemblyContextStack().pop();
        
        this.context.getRuntimeState().getEventNotificationHelper().firePassedEvent(
            	new AssemblyProvidedOperationPassedEvent<ProvidedRole, Signature>(providedRole, 
            			EventType.END, this.context.getThread(), this.signature, this.instanceAssemblyContext));
        
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
    @SuppressWarnings("unchecked")
    private SimulatedStackframe<Object> interpretSeffs(final List<ServiceEffectSpecification> calledSeffs) {
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
            final FQComponentID componentID = this.computeFQComponentID();
            final SimulatedBasicComponentInstance basicComponentInstance = (SimulatedBasicComponentInstance) this.context
                    .getRuntimeState().getComponentInstanceRegistry().getComponentInstance(componentID);
            
            final List<AbstractRDSeffSwitchFactory> switchFactories = ExtensionHelper
            		.getExecutableExtensions(RDSEFFSWITCH_EXTENSION_POINT_ID, RDSEFFSWITCH_EXTENSION_ATTRIBUTE);
            final  ExplicitDispatchComposedSwitch<Object> interpreter = new ExplicitDispatchComposedSwitch<Object>();
            switchFactories.stream().forEach(s -> interpreter.addSwitch(
            		s.createRDSeffSwitch(this.context, basicComponentInstance, interpreter)));
            // add default RDSeffSwitch
            interpreter.addSwitch(new RDSeffSwitch(this.context, basicComponentInstance, interpreter));
            // interpret called seff
            return (SimulatedStackframe<Object>) interpreter.doSwitch(calledSeffs.get(0));
        }
    }

    private FQComponentID computeFQComponentID() {
        return new FQComponentID(this.computeAssemblyContextPath());
    }

    private List<AssemblyContext> computeAssemblyContextPath() {
        final Stack<AssemblyContext> stack = this.context.getAssemblyContextStack();
        final ArrayList<AssemblyContext> result = new ArrayList<AssemblyContext>(stack.size());
        for (int i = 0; i < stack.size(); i++) {
            result.add(stack.get(i));
        }
        return result;
    }
    
    private Optional<AssemblyContext> getNestedComponentInstanceFromAssembly(AssemblyContext contextToFind, AssemblyContext compositeAssembly) {
    	RepositoryComponent comp = compositeAssembly.getEncapsulatedComponent__AssemblyContext();
    	if (!RepositoryPackage.eINSTANCE.getCompositeComponent().isInstance(comp)) {
    		throw new IllegalArgumentException("Nested component instance only available for instances of composite components.");
    	}
    	return ((ComposedStructure)comp).getAssemblyContexts__ComposedStructure().stream()
    			.filter(ctx -> ctx.getId().equals(contextToFind.getId())).findAny();
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
    protected SimulatedStackframe<Object> doSwitch(final EClass theEClass, final EObject theEObject) {
        if (EntityPackage.eINSTANCE.getComposedProvidingRequiringEntity().isSuperTypeOf(theEClass)) {
            return this.caseComposedProvidingRequiringEntity((ComposedProvidingRequiringEntity) theEObject);
        }
        return super.doSwitch(theEClass, theEObject);
    }

}
