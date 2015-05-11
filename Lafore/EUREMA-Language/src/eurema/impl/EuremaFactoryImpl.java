/**
 */
package eurema.impl;

import eurema.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EuremaFactoryImpl extends EFactoryImpl implements EuremaFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EuremaFactory init() {
		try {
			EuremaFactory theEuremaFactory = (EuremaFactory)EPackage.Registry.INSTANCE.getEFactory(EuremaPackage.eNS_URI);
			if (theEuremaFactory != null) {
				return theEuremaFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EuremaFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EuremaFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EuremaPackage.MEGAMODEL: return createMegamodel();
			case EuremaPackage.INITIAL_OPERATION: return createInitialOperation();
			case EuremaPackage.DECISION_OPERATION: return createDecisionOperation();
			case EuremaPackage.FINAL_OPERATION: return createFinalOperation();
			case EuremaPackage.MEGAMODEL_CALL: return createMegamodelCall();
			case EuremaPackage.MODEL_OPERATION: return createModelOperation();
			case EuremaPackage.SOFTWARE_MODULE: return createSoftwareModule();
			case EuremaPackage.RUNTIME_MODEL: return createRuntimeModel();
			case EuremaPackage.MEGAMODEL_PROXY: return createMegamodelProxy();
			case EuremaPackage.INPUT: return createInput();
			case EuremaPackage.OUTPUT: return createOutput();
			case EuremaPackage.TRANSITION: return createTransition();
			case EuremaPackage.CONDITION: return createCondition();
			case EuremaPackage.EXECUTION_CONTEXT: return createExecutionContext();
			case EuremaPackage.EXECUTION_INFORMATION: return createExecutionInformation();
			case EuremaPackage.SENSING: return createSensing();
			case EuremaPackage.EFFECTING: return createEffecting();
			case EuremaPackage.RUNTIME_ENVIRONMENT: return createRuntimeEnvironment();
			case EuremaPackage.LAYER: return createLayer();
			case EuremaPackage.EVENT: return createEvent();
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER: return createMegamodelModuleTrigger();
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER: return createSoftwareModuleTrigger();
			case EuremaPackage.MODEL_RESOURCE: return createModelResource();
			case EuremaPackage.EVENT_TYPE: return createEventType();
			case EuremaPackage.ARCHITECTURE: return createArchitecture();
			case EuremaPackage.MEGAMODEL_MODULE: return createMegamodelModule();
			case EuremaPackage.SOFTWARE: return createSoftware();
			case EuremaPackage.ENTRY: return createEntry();
			case EuremaPackage.EXIT: return createExit();
			case EuremaPackage.REPOSITORY: return createRepository();
			case EuremaPackage.MODEL_RESOURCE_SET: return createModelResourceSet();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Megamodel createMegamodel() {
		MegamodelImpl megamodel = new MegamodelImpl();
		return megamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialOperation createInitialOperation() {
		InitialOperationImpl initialOperation = new InitialOperationImpl();
		return initialOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DecisionOperation createDecisionOperation() {
		DecisionOperationImpl decisionOperation = new DecisionOperationImpl();
		return decisionOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinalOperation createFinalOperation() {
		FinalOperationImpl finalOperation = new FinalOperationImpl();
		return finalOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelCall createMegamodelCall() {
		MegamodelCallImpl megamodelCall = new MegamodelCallImpl();
		return megamodelCall;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelOperation createModelOperation() {
		ModelOperationImpl modelOperation = new ModelOperationImpl();
		return modelOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModule createSoftwareModule() {
		SoftwareModuleImpl softwareModule = new SoftwareModuleImpl();
		return softwareModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeModel createRuntimeModel() {
		RuntimeModelImpl runtimeModel = new RuntimeModelImpl();
		return runtimeModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelProxy createMegamodelProxy() {
		MegamodelProxyImpl megamodelProxy = new MegamodelProxyImpl();
		return megamodelProxy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Input createInput() {
		InputImpl input = new InputImpl();
		return input;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Output createOutput() {
		OutputImpl output = new OutputImpl();
		return output;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition createCondition() {
		ConditionImpl condition = new ConditionImpl();
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionContext createExecutionContext() {
		ExecutionContextImpl executionContext = new ExecutionContextImpl();
		return executionContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecutionInformation createExecutionInformation() {
		ExecutionInformationImpl executionInformation = new ExecutionInformationImpl();
		return executionInformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sensing createSensing() {
		SensingImpl sensing = new SensingImpl();
		return sensing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Effecting createEffecting() {
		EffectingImpl effecting = new EffectingImpl();
		return effecting;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeEnvironment createRuntimeEnvironment() {
		RuntimeEnvironmentImpl runtimeEnvironment = new RuntimeEnvironmentImpl();
		return runtimeEnvironment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Layer createLayer() {
		LayerImpl layer = new LayerImpl();
		return layer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModuleTrigger createMegamodelModuleTrigger() {
		MegamodelModuleTriggerImpl megamodelModuleTrigger = new MegamodelModuleTriggerImpl();
		return megamodelModuleTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareModuleTrigger createSoftwareModuleTrigger() {
		SoftwareModuleTriggerImpl softwareModuleTrigger = new SoftwareModuleTriggerImpl();
		return softwareModuleTrigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResource createModelResource() {
		ModelResourceImpl modelResource = new ModelResourceImpl();
		return modelResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventType createEventType() {
		EventTypeImpl eventType = new EventTypeImpl();
		return eventType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Architecture createArchitecture() {
		ArchitectureImpl architecture = new ArchitectureImpl();
		return architecture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MegamodelModule createMegamodelModule() {
		MegamodelModuleImpl megamodelModule = new MegamodelModuleImpl();
		return megamodelModule;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Software createSoftware() {
		SoftwareImpl software = new SoftwareImpl();
		return software;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Entry createEntry() {
		EntryImpl entry = new EntryImpl();
		return entry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Exit createExit() {
		ExitImpl exit = new ExitImpl();
		return exit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Repository createRepository() {
		RepositoryImpl repository = new RepositoryImpl();
		return repository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelResourceSet createModelResourceSet() {
		ModelResourceSetImpl modelResourceSet = new ModelResourceSetImpl();
		return modelResourceSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EuremaPackage getEuremaPackage() {
		return (EuremaPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EuremaPackage getPackage() {
		return EuremaPackage.eINSTANCE;
	}

} //EuremaFactoryImpl
