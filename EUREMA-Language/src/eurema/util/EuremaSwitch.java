/**
 */
package eurema.util;

import eurema.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see eurema.EuremaPackage
 * @generated
 */
public class EuremaSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EuremaPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EuremaSwitch() {
		if (modelPackage == null) {
			modelPackage = EuremaPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case EuremaPackage.MEGAMODEL: {
				Megamodel megamodel = (Megamodel)theEObject;
				T result = caseMegamodel(megamodel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.INITIAL_OPERATION: {
				InitialOperation initialOperation = (InitialOperation)theEObject;
				T result = caseInitialOperation(initialOperation);
				if (result == null) result = caseControlOperation(initialOperation);
				if (result == null) result = caseOperation(initialOperation);
				if (result == null) result = caseExecutable(initialOperation);
				if (result == null) result = caseMegamodelElement(initialOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.DECISION_OPERATION: {
				DecisionOperation decisionOperation = (DecisionOperation)theEObject;
				T result = caseDecisionOperation(decisionOperation);
				if (result == null) result = caseControlOperation(decisionOperation);
				if (result == null) result = caseOperation(decisionOperation);
				if (result == null) result = caseExecutable(decisionOperation);
				if (result == null) result = caseMegamodelElement(decisionOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.FINAL_OPERATION: {
				FinalOperation finalOperation = (FinalOperation)theEObject;
				T result = caseFinalOperation(finalOperation);
				if (result == null) result = caseControlOperation(finalOperation);
				if (result == null) result = caseOperation(finalOperation);
				if (result == null) result = caseExecutable(finalOperation);
				if (result == null) result = caseMegamodelElement(finalOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.OPERATION_BEHAVIOR: {
				OperationBehavior operationBehavior = (OperationBehavior)theEObject;
				T result = caseOperationBehavior(operationBehavior);
				if (result == null) result = caseOperation(operationBehavior);
				if (result == null) result = caseExecutable(operationBehavior);
				if (result == null) result = caseMegamodelElement(operationBehavior);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MEGAMODEL_CALL: {
				MegamodelCall megamodelCall = (MegamodelCall)theEObject;
				T result = caseMegamodelCall(megamodelCall);
				if (result == null) result = caseOperationBehavior(megamodelCall);
				if (result == null) result = caseOperation(megamodelCall);
				if (result == null) result = caseExecutable(megamodelCall);
				if (result == null) result = caseMegamodelElement(megamodelCall);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODEL_OPERATION: {
				ModelOperation modelOperation = (ModelOperation)theEObject;
				T result = caseModelOperation(modelOperation);
				if (result == null) result = caseOperationBehavior(modelOperation);
				if (result == null) result = caseOperation(modelOperation);
				if (result == null) result = caseExecutable(modelOperation);
				if (result == null) result = caseMegamodelElement(modelOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.CONTROL_OPERATION: {
				ControlOperation controlOperation = (ControlOperation)theEObject;
				T result = caseControlOperation(controlOperation);
				if (result == null) result = caseOperation(controlOperation);
				if (result == null) result = caseExecutable(controlOperation);
				if (result == null) result = caseMegamodelElement(controlOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.OPERATION: {
				Operation operation = (Operation)theEObject;
				T result = caseOperation(operation);
				if (result == null) result = caseExecutable(operation);
				if (result == null) result = caseMegamodelElement(operation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.SOFTWARE_MODULE: {
				SoftwareModule softwareModule = (SoftwareModule)theEObject;
				T result = caseSoftwareModule(softwareModule);
				if (result == null) result = caseModule(softwareModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODULE: {
				Module module = (Module)theEObject;
				T result = caseModule(module);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODEL: {
				Model model = (Model)theEObject;
				T result = caseModel(model);
				if (result == null) result = caseMegamodelElement(model);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.RUNTIME_MODEL: {
				RuntimeModel runtimeModel = (RuntimeModel)theEObject;
				T result = caseRuntimeModel(runtimeModel);
				if (result == null) result = caseModel(runtimeModel);
				if (result == null) result = caseMegamodelElement(runtimeModel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MEGAMODEL_PROXY: {
				MegamodelProxy megamodelProxy = (MegamodelProxy)theEObject;
				T result = caseMegamodelProxy(megamodelProxy);
				if (result == null) result = caseModel(megamodelProxy);
				if (result == null) result = caseMegamodelElement(megamodelProxy);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODEL_USE: {
				ModelUse modelUse = (ModelUse)theEObject;
				T result = caseModelUse(modelUse);
				if (result == null) result = caseMegamodelElement(modelUse);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.INPUT: {
				Input input = (Input)theEObject;
				T result = caseInput(input);
				if (result == null) result = caseModelUse(input);
				if (result == null) result = caseMegamodelElement(input);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.OUTPUT: {
				Output output = (Output)theEObject;
				T result = caseOutput(output);
				if (result == null) result = caseModelUse(output);
				if (result == null) result = caseMegamodelElement(output);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.TRANSITION: {
				Transition transition = (Transition)theEObject;
				T result = caseTransition(transition);
				if (result == null) result = caseExecutable(transition);
				if (result == null) result = caseMegamodelElement(transition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EXECUTABLE: {
				Executable executable = (Executable)theEObject;
				T result = caseExecutable(executable);
				if (result == null) result = caseMegamodelElement(executable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.CONDITION: {
				Condition condition = (Condition)theEObject;
				T result = caseCondition(condition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MEGAMODEL_ELEMENT: {
				MegamodelElement megamodelElement = (MegamodelElement)theEObject;
				T result = caseMegamodelElement(megamodelElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EXECUTION_CONTEXT: {
				ExecutionContext executionContext = (ExecutionContext)theEObject;
				T result = caseExecutionContext(executionContext);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EXECUTION_INFORMATION: {
				ExecutionInformation executionInformation = (ExecutionInformation)theEObject;
				T result = caseExecutionInformation(executionInformation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.SENSING: {
				Sensing sensing = (Sensing)theEObject;
				T result = caseSensing(sensing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EFFECTING: {
				Effecting effecting = (Effecting)theEObject;
				T result = caseEffecting(effecting);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.RUNTIME_ENVIRONMENT: {
				RuntimeEnvironment runtimeEnvironment = (RuntimeEnvironment)theEObject;
				T result = caseRuntimeEnvironment(runtimeEnvironment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.LAYER: {
				Layer layer = (Layer)theEObject;
				T result = caseLayer(layer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.TRIGGER: {
				Trigger trigger = (Trigger)theEObject;
				T result = caseTrigger(trigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EVENT: {
				Event event = (Event)theEObject;
				T result = caseEvent(event);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MEGAMODEL_MODULE_TRIGGER: {
				MegamodelModuleTrigger megamodelModuleTrigger = (MegamodelModuleTrigger)theEObject;
				T result = caseMegamodelModuleTrigger(megamodelModuleTrigger);
				if (result == null) result = caseTrigger(megamodelModuleTrigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.SOFTWARE_MODULE_TRIGGER: {
				SoftwareModuleTrigger softwareModuleTrigger = (SoftwareModuleTrigger)theEObject;
				T result = caseSoftwareModuleTrigger(softwareModuleTrigger);
				if (result == null) result = caseTrigger(softwareModuleTrigger);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODEL_RESOURCE: {
				ModelResource modelResource = (ModelResource)theEObject;
				T result = caseModelResource(modelResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EVENT_TYPE: {
				EventType eventType = (EventType)theEObject;
				T result = caseEventType(eventType);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.ARCHITECTURE: {
				Architecture architecture = (Architecture)theEObject;
				T result = caseArchitecture(architecture);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MEGAMODEL_MODULE: {
				MegamodelModule megamodelModule = (MegamodelModule)theEObject;
				T result = caseMegamodelModule(megamodelModule);
				if (result == null) result = caseModule(megamodelModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.SOFTWARE: {
				Software software = (Software)theEObject;
				T result = caseSoftware(software);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.ENTRY: {
				Entry entry = (Entry)theEObject;
				T result = caseEntry(entry);
				if (result == null) result = caseMegamodelElement(entry);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.EXIT: {
				Exit exit = (Exit)theEObject;
				T result = caseExit(exit);
				if (result == null) result = caseMegamodelElement(exit);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.REPOSITORY: {
				Repository repository = (Repository)theEObject;
				T result = caseRepository(repository);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EuremaPackage.MODEL_RESOURCE_SET: {
				ModelResourceSet modelResourceSet = (ModelResourceSet)theEObject;
				T result = caseModelResourceSet(modelResourceSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodel(Megamodel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Initial Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Initial Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInitialOperation(InitialOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Decision Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Decision Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDecisionOperation(DecisionOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Final Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Final Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFinalOperation(FinalOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation Behavior</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation Behavior</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationBehavior(OperationBehavior object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel Call</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel Call</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodelCall(MegamodelCall object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelOperation(ModelOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Control Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Control Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseControlOperation(ControlOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperation(Operation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareModule(SoftwareModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModule(Module object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModel(Model object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeModel(RuntimeModel object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel Proxy</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodelProxy(MegamodelProxy object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Use</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Use</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelUse(ModelUse object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInput(Input object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOutput(Output object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Transition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTransition(Transition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Executable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Executable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutable(Executable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Condition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCondition(Condition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodelElement(MegamodelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Context</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionContext(ExecutionContext object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Execution Information</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Execution Information</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExecutionInformation(ExecutionInformation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sensing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sensing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSensing(Sensing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Effecting</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Effecting</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEffecting(Effecting object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Runtime Environment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Runtime Environment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRuntimeEnvironment(RuntimeEnvironment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Layer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Layer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLayer(Layer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTrigger(Trigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEvent(Event object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel Module Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel Module Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodelModuleTrigger(MegamodelModuleTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software Module Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software Module Trigger</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftwareModuleTrigger(SoftwareModuleTrigger object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Resource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Resource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelResource(ModelResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Event Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEventType(EventType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Architecture</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchitecture(Architecture object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Megamodel Module</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Megamodel Module</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMegamodelModule(MegamodelModule object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Software</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Software</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSoftware(Software object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntry(Entry object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Exit</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Exit</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseExit(Exit object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Repository</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRepository(Repository object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Resource Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Resource Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelResourceSet(ModelResourceSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //EuremaSwitch
