/**
 * 
 */
package org.palladiosimulator.simulizar.interpreter;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.exceptions.PCMModelInterpreterException;
import org.palladiosimulator.simulizar.utils.SimulatedStackHelper;

import de.uka.ipd.sdq.pcm.core.composition.AssemblyContext;
import de.uka.ipd.sdq.pcm.core.composition.ComposedStructure;
import de.uka.ipd.sdq.pcm.core.composition.CompositionFactory;
import de.uka.ipd.sdq.pcm.core.composition.CompositionPackage;
import de.uka.ipd.sdq.pcm.core.composition.Connector;
import de.uka.ipd.sdq.pcm.core.composition.ProvidedDelegationConnector;
import de.uka.ipd.sdq.pcm.core.entity.ComposedProvidingRequiringEntity;
import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;
import de.uka.ipd.sdq.pcm.core.entity.InterfaceProvidingEntity;
import de.uka.ipd.sdq.pcm.repository.BasicComponent;
import de.uka.ipd.sdq.pcm.repository.OperationSignature;
import de.uka.ipd.sdq.pcm.repository.ProvidedRole;
import de.uka.ipd.sdq.pcm.repository.util.RepositorySwitch;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStack;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;

/**
 * @author snowball
 * 
 */
class RepositoryComponentSwitch extends RepositorySwitch<SimulatedStackframe<Object>> {

    public static final AssemblyContext SYSTEM_ASSEMBLY_CONTEXT = CompositionFactory.eINSTANCE.createAssemblyContext();

    private final OperationSignature signature;
    private final ProvidedRole providedRole;
    private final InterpreterDefaultContext context;
    private final AssemblyContext instanceAssemblyContext;
    private final IModelAccessFactory modelAccessFactory;

    /**
	 * 
	 */
    public RepositoryComponentSwitch(final InterpreterDefaultContext context,
            final IModelAccessFactory interpreterFactory, final AssemblyContext assemblyContext,
            final OperationSignature signature, final ProvidedRole providedRole) {
        super();
        this.context = context;
        this.modelAccessFactory = interpreterFactory;
        this.instanceAssemblyContext = assemblyContext;
        this.signature = signature;
        this.providedRole = providedRole;
    }

    @Override
    public SimulatedStackframe<Object> caseBasicComponent(final BasicComponent basicComponent) {
        // create new stack frame for component parameters
        final SimulatedStack<Object> stack = this.context.getStack();
        final SimulatedStackframe<Object> componentParameterStackFrame = SimulatedStackHelper
                .createAndPushNewStackFrame(stack,
                        basicComponent.getComponentParameterUsage_ImplementationComponentType(),
                        stack.currentStackFrame());

        // create new stack frame for assembly context component parameters
        SimulatedStackHelper.createAndPushNewStackFrame(stack,
                this.instanceAssemblyContext.getConfigParameterUsages__AssemblyContext(), componentParameterStackFrame);

        // get seffs for call
        final List<ServiceEffectSpecification> calledSeffs = this.getSeffsForCall(
                basicComponent.getServiceEffectSpecifications__BasicComponent(), this.signature);

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
        if (entity != this.providedRole.getProvidingEntity_ProvidedRole()) {
            throw new PCMModelInterpreterException("Interpret entity of provided role only");
        }
        final ProvidedDelegationConnector connectedProvidedDelegationConnector = getConnectedProvidedDelegationConnector(this.providedRole);
        final RepositoryComponentSwitch repositoryComponentSwitch = new RepositoryComponentSwitch(this.context,
                this.modelAccessFactory,
                connectedProvidedDelegationConnector.getAssemblyContext_ProvidedDelegationConnector(), this.signature,
                connectedProvidedDelegationConnector.getInnerProvidedRole_ProvidedDelegationConnector());
        return repositoryComponentSwitch.doSwitch(connectedProvidedDelegationConnector
                .getInnerProvidedRole_ProvidedDelegationConnector());
    }

    /**
     * @see de.uka.ipd.sdq.pcm.repository.util.CompositionSwitch#caseProvidedRole(de.uka.ipd.sdq.pcm.repository.ProvidedRole)
     */
    @Override
    public SimulatedStackframe<Object> caseProvidedRole(final ProvidedRole providedRole) {
        this.context.getAssemblyContextStack().push(this.instanceAssemblyContext);

        final SimulatedStackframe<Object> result = this.doSwitch(providedRole.getProvidingEntity_ProvidedRole());

        this.context.getAssemblyContextStack().pop();

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
            final EList<ServiceEffectSpecification> serviceEffectSpecifications,
            final OperationSignature operationSignature) {
        final List<ServiceEffectSpecification> seffs = new Vector<ServiceEffectSpecification>();

        for (final ServiceEffectSpecification serviceEffectSpecification : serviceEffectSpecifications) {
            if (serviceEffectSpecification.getDescribedService__SEFF().equals(operationSignature)) {
                seffs.add(serviceEffectSpecification);
            }
        }
        return seffs;
    }

    /**
     * Interpret the given Seffs.
     * 
     * @param calledSeffs
     *            a list of seffs.
     */
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
            final RDSeffSwitch rdSeffInterpreter = new RDSeffSwitch(this.context, this.modelAccessFactory,
                    this.instanceAssemblyContext);

            // interpret called seff
            return rdSeffInterpreter.doSwitch(calledSeffs.get(0));
        }
    }

    /**
     * Determines the provided delegation connector which is connected with the provided role.
     * 
     * @param providedRole
     *            the provided role.
     * @return the determined provided delegation connector, null otherwise.
     */
    private static ProvidedDelegationConnector getConnectedProvidedDelegationConnector(final ProvidedRole providedRole) {
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
