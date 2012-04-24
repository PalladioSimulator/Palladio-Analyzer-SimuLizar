/**
 * 
 */
package de.upb.pcm.interpreter.switches;

import java.util.List;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

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
import de.uka.ipd.sdq.pcm.repository.util.CompositionSwitch;
import de.uka.ipd.sdq.pcm.seff.ResourceDemandingSEFF;
import de.uka.ipd.sdq.pcm.seff.ServiceEffectSpecification;
import de.uka.ipd.sdq.simucomframework.variables.stackframe.SimulatedStackframe;
import de.upb.pcm.interpreter.exceptions.PCMModelInterpreterException;
import de.upb.pcm.interpreter.interpreter.IInterpreterFactory;
import de.upb.pcm.interpreter.interpreter.RDSeffInterpreter;
import de.upb.pcm.interpreter.simulation.InterpreterDefaultContext;
import de.upb.pcm.interpreter.simulation.InterpreterSimulatedStack;

/**
 * @author snowball
 *
 */
public class RepositoryComponentSwitch<T> extends CompositionSwitch<T> {

	public static final AssemblyContext SYSTEM_ASSEMBLY_CONTEXT = CompositionFactory.eINSTANCE.createAssemblyContext();
	
	private final OperationSignature signature;
	private final ProvidedRole providedRole;
	private final InterpreterDefaultContext context;
	private final AssemblyContext instanceAssemblyContext;
	private final IInterpreterFactory interpreterFactory;
	
	/**
	 * 
	 */
	public RepositoryComponentSwitch(
			final InterpreterDefaultContext context,
			final IInterpreterFactory interpreterFactory,
			final AssemblyContext assemblyContext,
			final OperationSignature signature, 
			final ProvidedRole providedRole) {
		super();
		this.context = context;
		this.interpreterFactory = interpreterFactory;
		this.instanceAssemblyContext = assemblyContext;
		this.signature = signature;
		this.providedRole = providedRole;
	}

	@Override
	public T caseBasicComponent(BasicComponent basicComponent) {
		// create new stack frame for component parameters
		final InterpreterSimulatedStack stack = context.getStack();
		final SimulatedStackframe<Object> componentParameterStackFrame = stack
				.createAndPushNewStackFrame(
						basicComponent
								.getComponentParameterUsage_ImplementationComponentType(),
						stack.currentStackFrame());

		// create new stack frame for assembly context component parameters
		stack.createAndPushNewStackFrame(instanceAssemblyContext
				.getConfigParameterUsages__AssemblyContext(),
				componentParameterStackFrame);

		// get seffs for call
		final List<ServiceEffectSpecification> calledSeffs = getSeffsForCall(
				basicComponent.getServiceEffectSpecifications__BasicComponent(),
				this.signature);

		interpretSeffs(calledSeffs);

		/*
		 * Remove created stack frame (including stack frame created for the
		 * results of an external call in RDSEFF, done in RDSEFF Interpreter).
		 */
		stack.removeStackFrame();
		stack.removeStackFrame();

		return super.caseBasicComponent(basicComponent);
	}

	@Override
	public T caseComposedProvidingRequiringEntity(
			ComposedProvidingRequiringEntity entity) {
    	if (entity != providedRole.getProvidingEntity_ProvidedRole())
    		throw new PCMModelInterpreterException("Interpret entity of provided role only");
		final ProvidedDelegationConnector connectedProvidedDelegationConnector = getConnectedProvidedDelegationConnector(providedRole);
		ProvidedDelegationSwitch<T> composedStructureSwitch = new ProvidedDelegationSwitch<T>(context, interpreterFactory, signature);
		composedStructureSwitch.doSwitch(connectedProvidedDelegationConnector);
    	return super.caseComposedProvidingRequiringEntity(entity);
	}

	/**
	 * @see de.uka.ipd.sdq.pcm.repository.util.CompositionSwitch#caseProvidedRole(de.uka.ipd.sdq.pcm.repository.ProvidedRole)
	 */
	@Override
	public T caseProvidedRole(final ProvidedRole providedRole) {		
		this.context.getAssemblyContextStack().push(this.instanceAssemblyContext);
		
		this.doSwitch(providedRole.getProvidingEntity_ProvidedRole());
		
		this.context.getAssemblyContextStack().pop();
		
		return super.caseProvidedRole(providedRole);
	}	
	
	/**
	 * Return the seffs (of different types) from a list of seffs which are
	 * affected by the call. Different types are currently not supported by the
	 * meta model.
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
			if (serviceEffectSpecification.getDescribedService__SEFF().equals(
					operationSignature)) {
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
	private void interpretSeffs(
			final List<ServiceEffectSpecification> calledSeffs) {
		/*
		 * we assume exactly one seff per call, the meta model also allows no
		 * seffs, but we omit that in this interpreter
		 */
		if (calledSeffs.size() != 1) {
			throw new PCMModelInterpreterException(
					"Only exactly one SEFF is currently supported.");
		}
		if (!(calledSeffs.get(0) instanceof ResourceDemandingSEFF)) {
			throw new PCMModelInterpreterException(
					"Only ResourceDemandingSEFFs are currently supported.");
		} else {
			for (final ServiceEffectSpecification serviceEffectSpecification : calledSeffs) {

				final RDSeffInterpreter rdSeffInterpreter = interpreterFactory
						.getRDSEFFInterpreter(context, instanceAssemblyContext);

				// interpret called seff
				rdSeffInterpreter
						.interpret((ResourceDemandingSEFF) serviceEffectSpecification);
			}
		}
	}
	
	/**
	 * Determines the provided delegation connector which is connected with the
	 * provided role.
	 * 
	 * @param providedRole
	 *            the provided role.
	 * @return the determined provided delegation connector, null otherwise.
	 */
	private static ProvidedDelegationConnector getConnectedProvidedDelegationConnector(
			final ProvidedRole providedRole) {
		final InterfaceProvidingEntity implementingEntity = providedRole.getProvidingEntity_ProvidedRole();
		if (!CompositionPackage.eINSTANCE.getComposedStructure().isSuperTypeOf(implementingEntity.eClass()))
			throw new PCMModelInterpreterException("Structure used for connector search must be a composed structure");
		for (final Connector connector : ((ComposedStructure)implementingEntity).getConnectors__ComposedStructure() ) {
			if (connector.eClass() == CompositionPackage.eINSTANCE
					.getProvidedDelegationConnector()) {

				if (((ProvidedDelegationConnector) connector)
						.getOuterProvidedRole_ProvidedDelegationConnector()
						.equals(providedRole)) {
					return (ProvidedDelegationConnector) connector;
				}
			}
		}
		throw new PCMModelInterpreterException("Found unbound provided role. PCM model is invalid.");
	}

	@Override
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (EntityPackage.eINSTANCE.getComposedProvidingRequiringEntity().isSuperTypeOf(theEClass)) {
			return this.caseComposedProvidingRequiringEntity((ComposedProvidingRequiringEntity) theEObject);
		}
		return super.doSwitch(theEClass, theEObject);
	}
	
	
}
