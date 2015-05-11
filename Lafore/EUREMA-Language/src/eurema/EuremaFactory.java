/**
 */
package eurema;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see eurema.EuremaPackage
 * @generated
 */
public interface EuremaFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EuremaFactory eINSTANCE = eurema.impl.EuremaFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Megamodel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Megamodel</em>'.
	 * @generated
	 */
	Megamodel createMegamodel();

	/**
	 * Returns a new object of class '<em>Initial Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Initial Operation</em>'.
	 * @generated
	 */
	InitialOperation createInitialOperation();

	/**
	 * Returns a new object of class '<em>Decision Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision Operation</em>'.
	 * @generated
	 */
	DecisionOperation createDecisionOperation();

	/**
	 * Returns a new object of class '<em>Final Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Final Operation</em>'.
	 * @generated
	 */
	FinalOperation createFinalOperation();

	/**
	 * Returns a new object of class '<em>Megamodel Call</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Megamodel Call</em>'.
	 * @generated
	 */
	MegamodelCall createMegamodelCall();

	/**
	 * Returns a new object of class '<em>Model Operation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Operation</em>'.
	 * @generated
	 */
	ModelOperation createModelOperation();

	/**
	 * Returns a new object of class '<em>Software Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Module</em>'.
	 * @generated
	 */
	SoftwareModule createSoftwareModule();

	/**
	 * Returns a new object of class '<em>Runtime Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Model</em>'.
	 * @generated
	 */
	RuntimeModel createRuntimeModel();

	/**
	 * Returns a new object of class '<em>Megamodel Proxy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Megamodel Proxy</em>'.
	 * @generated
	 */
	MegamodelProxy createMegamodelProxy();

	/**
	 * Returns a new object of class '<em>Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input</em>'.
	 * @generated
	 */
	Input createInput();

	/**
	 * Returns a new object of class '<em>Output</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output</em>'.
	 * @generated
	 */
	Output createOutput();

	/**
	 * Returns a new object of class '<em>Transition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transition</em>'.
	 * @generated
	 */
	Transition createTransition();

	/**
	 * Returns a new object of class '<em>Condition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Condition</em>'.
	 * @generated
	 */
	Condition createCondition();

	/**
	 * Returns a new object of class '<em>Execution Context</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Context</em>'.
	 * @generated
	 */
	ExecutionContext createExecutionContext();

	/**
	 * Returns a new object of class '<em>Execution Information</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Execution Information</em>'.
	 * @generated
	 */
	ExecutionInformation createExecutionInformation();

	/**
	 * Returns a new object of class '<em>Sensing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sensing</em>'.
	 * @generated
	 */
	Sensing createSensing();

	/**
	 * Returns a new object of class '<em>Effecting</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Effecting</em>'.
	 * @generated
	 */
	Effecting createEffecting();

	/**
	 * Returns a new object of class '<em>Runtime Environment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Environment</em>'.
	 * @generated
	 */
	RuntimeEnvironment createRuntimeEnvironment();

	/**
	 * Returns a new object of class '<em>Layer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Layer</em>'.
	 * @generated
	 */
	Layer createLayer();

	/**
	 * Returns a new object of class '<em>Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event</em>'.
	 * @generated
	 */
	Event createEvent();

	/**
	 * Returns a new object of class '<em>Megamodel Module Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Megamodel Module Trigger</em>'.
	 * @generated
	 */
	MegamodelModuleTrigger createMegamodelModuleTrigger();

	/**
	 * Returns a new object of class '<em>Software Module Trigger</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software Module Trigger</em>'.
	 * @generated
	 */
	SoftwareModuleTrigger createSoftwareModuleTrigger();

	/**
	 * Returns a new object of class '<em>Model Resource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Resource</em>'.
	 * @generated
	 */
	ModelResource createModelResource();

	/**
	 * Returns a new object of class '<em>Event Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Event Type</em>'.
	 * @generated
	 */
	EventType createEventType();

	/**
	 * Returns a new object of class '<em>Architecture</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Architecture</em>'.
	 * @generated
	 */
	Architecture createArchitecture();

	/**
	 * Returns a new object of class '<em>Megamodel Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Megamodel Module</em>'.
	 * @generated
	 */
	MegamodelModule createMegamodelModule();

	/**
	 * Returns a new object of class '<em>Software</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software</em>'.
	 * @generated
	 */
	Software createSoftware();

	/**
	 * Returns a new object of class '<em>Entry</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Entry</em>'.
	 * @generated
	 */
	Entry createEntry();

	/**
	 * Returns a new object of class '<em>Exit</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Exit</em>'.
	 * @generated
	 */
	Exit createExit();

	/**
	 * Returns a new object of class '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository</em>'.
	 * @generated
	 */
	Repository createRepository();

	/**
	 * Returns a new object of class '<em>Model Resource Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Model Resource Set</em>'.
	 * @generated
	 */
	ModelResourceSet createModelResourceSet();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EuremaPackage getEuremaPackage();

} //EuremaFactory
