/**
 */
package eurema;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see eurema.EuremaFactory
 * @model kind="package"
 * @generated
 */
public interface EuremaPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "eurema";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.mdelab.de/EUREMA";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "eurema";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EuremaPackage eINSTANCE = eurema.impl.EuremaPackageImpl.init();

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelImpl <em>Megamodel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodel()
	 * @generated
	 */
	int MEGAMODEL = 0;

	/**
	 * The feature id for the '<em><b>Initial Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__INITIAL_OPERATIONS = 0;

	/**
	 * The feature id for the '<em><b>Final Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__FINAL_OPERATIONS = 1;

	/**
	 * The feature id for the '<em><b>Decision Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__DECISION_OPERATIONS = 2;

	/**
	 * The feature id for the '<em><b>Behavior</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__BEHAVIOR = 3;

	/**
	 * The feature id for the '<em><b>Models</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__MODELS = 4;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__UID = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__NAME = 6;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__DESCRIPTION = 7;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL__MODULE = 8;

	/**
	 * The number of structural features of the '<em>Megamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_FEATURE_COUNT = 9;

	/**
	 * The number of operations of the '<em>Megamodel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelElementImpl <em>Megamodel Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelElementImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodelElement()
	 * @generated
	 */
	int MEGAMODEL_ELEMENT = 20;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_ELEMENT__UID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_ELEMENT__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_ELEMENT__DESCRIPTION = 2;

	/**
	 * The number of structural features of the '<em>Megamodel Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Megamodel Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ExecutableImpl <em>Executable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ExecutableImpl
	 * @see eurema.impl.EuremaPackageImpl#getExecutable()
	 * @generated
	 */
	int EXECUTABLE = 18;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE__UID = MEGAMODEL_ELEMENT__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE__NAME = MEGAMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE__DESCRIPTION = MEGAMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE__EXEC_INFO = MEGAMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Executable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE_FEATURE_COUNT = MEGAMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Executable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTABLE_OPERATION_COUNT = MEGAMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.OperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 8;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__UID = EXECUTABLE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = EXECUTABLE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__DESCRIPTION = EXECUTABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__EXEC_INFO = EXECUTABLE__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ENTRIES = EXECUTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__EXITS = EXECUTABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = EXECUTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = EXECUTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ControlOperationImpl <em>Control Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ControlOperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getControlOperation()
	 * @generated
	 */
	int CONTROL_OPERATION = 7;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__UID = OPERATION__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__NAME = OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__DESCRIPTION = OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__EXEC_INFO = OPERATION__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__ENTRIES = OPERATION__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION__EXITS = OPERATION__EXITS;

	/**
	 * The number of structural features of the '<em>Control Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Control Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTROL_OPERATION_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.InitialOperationImpl <em>Initial Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.InitialOperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getInitialOperation()
	 * @generated
	 */
	int INITIAL_OPERATION = 1;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__UID = CONTROL_OPERATION__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__NAME = CONTROL_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__DESCRIPTION = CONTROL_OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__EXEC_INFO = CONTROL_OPERATION__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__ENTRIES = CONTROL_OPERATION__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__EXITS = CONTROL_OPERATION__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION__MEGAMODEL = CONTROL_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Initial Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION_FEATURE_COUNT = CONTROL_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Initial Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INITIAL_OPERATION_OPERATION_COUNT = CONTROL_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.DecisionOperationImpl <em>Decision Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.DecisionOperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getDecisionOperation()
	 * @generated
	 */
	int DECISION_OPERATION = 2;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__UID = CONTROL_OPERATION__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__NAME = CONTROL_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__DESCRIPTION = CONTROL_OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__EXEC_INFO = CONTROL_OPERATION__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__ENTRIES = CONTROL_OPERATION__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__EXITS = CONTROL_OPERATION__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION__MEGAMODEL = CONTROL_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Decision Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION_FEATURE_COUNT = CONTROL_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Decision Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_OPERATION_OPERATION_COUNT = CONTROL_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.FinalOperationImpl <em>Final Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.FinalOperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getFinalOperation()
	 * @generated
	 */
	int FINAL_OPERATION = 3;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__UID = CONTROL_OPERATION__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__NAME = CONTROL_OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__DESCRIPTION = CONTROL_OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__EXEC_INFO = CONTROL_OPERATION__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__ENTRIES = CONTROL_OPERATION__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__EXITS = CONTROL_OPERATION__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__MEGAMODEL = CONTROL_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Destructive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION__IS_DESTRUCTIVE = CONTROL_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Final Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION_FEATURE_COUNT = CONTROL_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Final Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FINAL_OPERATION_OPERATION_COUNT = CONTROL_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.OperationBehaviorImpl <em>Operation Behavior</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.OperationBehaviorImpl
	 * @see eurema.impl.EuremaPackageImpl#getOperationBehavior()
	 * @generated
	 */
	int OPERATION_BEHAVIOR = 4;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__UID = OPERATION__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__NAME = OPERATION__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__DESCRIPTION = OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__EXEC_INFO = OPERATION__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__ENTRIES = OPERATION__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__EXITS = OPERATION__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__MEGAMODEL = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model Usages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR__MODEL_USAGES = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Operation Behavior</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_BEHAVIOR_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelCallImpl <em>Megamodel Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelCallImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodelCall()
	 * @generated
	 */
	int MEGAMODEL_CALL = 5;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__UID = OPERATION_BEHAVIOR__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__NAME = OPERATION_BEHAVIOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__DESCRIPTION = OPERATION_BEHAVIOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__EXEC_INFO = OPERATION_BEHAVIOR__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__ENTRIES = OPERATION_BEHAVIOR__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__EXITS = OPERATION_BEHAVIOR__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__MEGAMODEL = OPERATION_BEHAVIOR__MEGAMODEL;

	/**
	 * The feature id for the '<em><b>Model Usages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__MODEL_USAGES = OPERATION_BEHAVIOR__MODEL_USAGES;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL__BINDING = OPERATION_BEHAVIOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Megamodel Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL_FEATURE_COUNT = OPERATION_BEHAVIOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Megamodel Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_CALL_OPERATION_COUNT = OPERATION_BEHAVIOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModelOperationImpl <em>Model Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModelOperationImpl
	 * @see eurema.impl.EuremaPackageImpl#getModelOperation()
	 * @generated
	 */
	int MODEL_OPERATION = 6;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__UID = OPERATION_BEHAVIOR__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__NAME = OPERATION_BEHAVIOR__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__DESCRIPTION = OPERATION_BEHAVIOR__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__EXEC_INFO = OPERATION_BEHAVIOR__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__ENTRIES = OPERATION_BEHAVIOR__ENTRIES;

	/**
	 * The feature id for the '<em><b>Exits</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__EXITS = OPERATION_BEHAVIOR__EXITS;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__MEGAMODEL = OPERATION_BEHAVIOR__MEGAMODEL;

	/**
	 * The feature id for the '<em><b>Model Usages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__MODEL_USAGES = OPERATION_BEHAVIOR__MODEL_USAGES;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION__BINDING = OPERATION_BEHAVIOR_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_FEATURE_COUNT = OPERATION_BEHAVIOR_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Model Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_OPERATION_COUNT = OPERATION_BEHAVIOR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModuleImpl <em>Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModuleImpl
	 * @see eurema.impl.EuremaPackageImpl#getModule()
	 * @generated
	 */
	int MODULE = 10;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__UID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Senses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__SENSES = 2;

	/**
	 * The feature id for the '<em><b>Sensed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__SENSED_BY = 3;

	/**
	 * The feature id for the '<em><b>Effects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__EFFECTS = 4;

	/**
	 * The feature id for the '<em><b>Effected By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__EFFECTED_BY = 5;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE__LAYER = 6;

	/**
	 * The number of structural features of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_FEATURE_COUNT = 7;

	/**
	 * The number of operations of the '<em>Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODULE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.SoftwareModuleImpl <em>Software Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.SoftwareModuleImpl
	 * @see eurema.impl.EuremaPackageImpl#getSoftwareModule()
	 * @generated
	 */
	int SOFTWARE_MODULE = 9;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__UID = MODULE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__NAME = MODULE__NAME;

	/**
	 * The feature id for the '<em><b>Senses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__SENSES = MODULE__SENSES;

	/**
	 * The feature id for the '<em><b>Sensed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__SENSED_BY = MODULE__SENSED_BY;

	/**
	 * The feature id for the '<em><b>Effects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__EFFECTS = MODULE__EFFECTS;

	/**
	 * The feature id for the '<em><b>Effected By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__EFFECTED_BY = MODULE__EFFECTED_BY;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__LAYER = MODULE__LAYER;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__IMPLEMENTATION = MODULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__TRIGGER = MODULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Software</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE__SOFTWARE = MODULE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Software Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_FEATURE_COUNT = MODULE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Software Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_OPERATION_COUNT = MODULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModelImpl
	 * @see eurema.impl.EuremaPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 11;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__UID = MEGAMODEL_ELEMENT__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__NAME = MEGAMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__DESCRIPTION = MEGAMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__MEGAMODEL = MEGAMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__USED_BY = MEGAMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = MEGAMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = MEGAMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.RuntimeModelImpl <em>Runtime Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.RuntimeModelImpl
	 * @see eurema.impl.EuremaPackageImpl#getRuntimeModel()
	 * @generated
	 */
	int RUNTIME_MODEL = 12;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__UID = MODEL__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__NAME = MODEL__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__DESCRIPTION = MODEL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__MEGAMODEL = MODEL__MEGAMODEL;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__USED_BY = MODEL__USED_BY;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL__BINDING = MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Runtime Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL_FEATURE_COUNT = MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Runtime Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_MODEL_OPERATION_COUNT = MODEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelProxyImpl <em>Megamodel Proxy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelProxyImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodelProxy()
	 * @generated
	 */
	int MEGAMODEL_PROXY = 13;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__UID = MODEL__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__NAME = MODEL__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__DESCRIPTION = MODEL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__MEGAMODEL = MODEL__MEGAMODEL;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__USED_BY = MODEL__USED_BY;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY__BINDING = MODEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Megamodel Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY_FEATURE_COUNT = MODEL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Megamodel Proxy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_PROXY_OPERATION_COUNT = MODEL_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModelUseImpl <em>Model Use</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModelUseImpl
	 * @see eurema.impl.EuremaPackageImpl#getModelUse()
	 * @generated
	 */
	int MODEL_USE = 14;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE__UID = MEGAMODEL_ELEMENT__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE__NAME = MEGAMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE__DESCRIPTION = MEGAMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE__OPERATION = MEGAMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE__MODEL = MEGAMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Model Use</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE_FEATURE_COUNT = MEGAMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Model Use</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_USE_OPERATION_COUNT = MEGAMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.InputImpl
	 * @see eurema.impl.EuremaPackageImpl#getInput()
	 * @generated
	 */
	int INPUT = 15;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__UID = MODEL_USE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__NAME = MODEL_USE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__DESCRIPTION = MODEL_USE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__OPERATION = MODEL_USE__OPERATION;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT__MODEL = MODEL_USE__MODEL;

	/**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_FEATURE_COUNT = MODEL_USE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_OPERATION_COUNT = MODEL_USE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.OutputImpl
	 * @see eurema.impl.EuremaPackageImpl#getOutput()
	 * @generated
	 */
	int OUTPUT = 16;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__UID = MODEL_USE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__NAME = MODEL_USE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__DESCRIPTION = MODEL_USE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__OPERATION = MODEL_USE__OPERATION;

	/**
	 * The feature id for the '<em><b>Model</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT__MODEL = MODEL_USE__MODEL;

	/**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_FEATURE_COUNT = MODEL_USE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_OPERATION_COUNT = MODEL_USE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.TransitionImpl
	 * @see eurema.impl.EuremaPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 17;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__UID = EXECUTABLE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = EXECUTABLE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DESCRIPTION = EXECUTABLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Exec Info</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EXEC_INFO = EXECUTABLE__EXEC_INFO;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__CONDITION = EXECUTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET = EXECUTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__SOURCE = EXECUTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = EXECUTABLE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = EXECUTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ConditionImpl <em>Condition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ConditionImpl
	 * @see eurema.impl.EuremaPackageImpl#getCondition()
	 * @generated
	 */
	int CONDITION = 19;

	/**
	 * The feature id for the '<em><b>Expr</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__EXPR = 0;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION__TRANSITION = 1;

	/**
	 * The number of structural features of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Condition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONDITION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ExecutionContextImpl
	 * @see eurema.impl.EuremaPackageImpl#getExecutionContext()
	 * @generated
	 */
	int EXECUTION_CONTEXT = 21;

	/**
	 * The feature id for the '<em><b>Current</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__CURRENT = 0;

	/**
	 * The feature id for the '<em><b>Exec Infos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__EXEC_INFOS = 1;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__ENVIRONMENT = 2;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__UID = 3;

	/**
	 * The feature id for the '<em><b>Executing</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT__EXECUTING = 4;

	/**
	 * The number of structural features of the '<em>Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Execution Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_CONTEXT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ExecutionInformationImpl <em>Execution Information</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ExecutionInformationImpl
	 * @see eurema.impl.EuremaPackageImpl#getExecutionInformation()
	 * @generated
	 */
	int EXECUTION_INFORMATION = 22;

	/**
	 * The feature id for the '<em><b>Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION__COUNT = 0;

	/**
	 * The feature id for the '<em><b>Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION__TIME = 1;

	/**
	 * The feature id for the '<em><b>Executable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION__EXECUTABLE = 2;

	/**
	 * The feature id for the '<em><b>Context</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION__CONTEXT = 3;

	/**
	 * The number of structural features of the '<em>Execution Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Execution Information</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXECUTION_INFORMATION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.SensingImpl <em>Sensing</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.SensingImpl
	 * @see eurema.impl.EuremaPackageImpl#getSensing()
	 * @generated
	 */
	int SENSING = 23;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING__TARGET = 1;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING__TRIGGER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING__NAME = 3;

	/**
	 * The number of structural features of the '<em>Sensing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Sensing</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.EffectingImpl <em>Effecting</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.EffectingImpl
	 * @see eurema.impl.EuremaPackageImpl#getEffecting()
	 * @generated
	 */
	int EFFECTING = 24;

	/**
	 * The feature id for the '<em><b>Source</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFECTING__SOURCE = 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFECTING__TARGET = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFECTING__NAME = 2;

	/**
	 * The number of structural features of the '<em>Effecting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFECTING_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Effecting</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EFFECTING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.RuntimeEnvironmentImpl <em>Runtime Environment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.RuntimeEnvironmentImpl
	 * @see eurema.impl.EuremaPackageImpl#getRuntimeEnvironment()
	 * @generated
	 */
	int RUNTIME_ENVIRONMENT = 25;

	/**
	 * The feature id for the '<em><b>Executions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ENVIRONMENT__EXECUTIONS = 0;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ENVIRONMENT__ARCHITECTURE = 1;

	/**
	 * The number of structural features of the '<em>Runtime Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ENVIRONMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Runtime Environment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_ENVIRONMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.LayerImpl <em>Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.LayerImpl
	 * @see eurema.impl.EuremaPackageImpl#getLayer()
	 * @generated
	 */
	int LAYER = 26;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__MODULES = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__NAME = 2;

	/**
	 * The feature id for the '<em><b>Architecture</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__ARCHITECTURE = 3;

	/**
	 * The number of structural features of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.TriggerImpl <em>Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.TriggerImpl
	 * @see eurema.impl.EuremaPackageImpl#getTrigger()
	 * @generated
	 */
	int TRIGGER = 27;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__PERIOD = 0;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__EVENTS = 1;

	/**
	 * The feature id for the '<em><b>Refers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER__REFERS = 2;

	/**
	 * The number of structural features of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRIGGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.EventImpl <em>Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.EventImpl
	 * @see eurema.impl.EuremaPackageImpl#getEvent()
	 * @generated
	 */
	int EVENT = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT__PAYLOAD = 2;

	/**
	 * The number of structural features of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelModuleTriggerImpl <em>Megamodel Module Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelModuleTriggerImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodelModuleTrigger()
	 * @generated
	 */
	int MEGAMODEL_MODULE_TRIGGER = 29;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER__PERIOD = TRIGGER__PERIOD;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER__EVENTS = TRIGGER__EVENTS;

	/**
	 * The feature id for the '<em><b>Refers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER__REFERS = TRIGGER__REFERS;

	/**
	 * The feature id for the '<em><b>Initial Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER__MODULE = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Megamodel Module Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Megamodel Module Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.SoftwareModuleTriggerImpl <em>Software Module Trigger</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.SoftwareModuleTriggerImpl
	 * @see eurema.impl.EuremaPackageImpl#getSoftwareModuleTrigger()
	 * @generated
	 */
	int SOFTWARE_MODULE_TRIGGER = 30;

	/**
	 * The feature id for the '<em><b>Period</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__PERIOD = TRIGGER__PERIOD;

	/**
	 * The feature id for the '<em><b>Events</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__EVENTS = TRIGGER__EVENTS;

	/**
	 * The feature id for the '<em><b>Refers</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__REFERS = TRIGGER__REFERS;

	/**
	 * The feature id for the '<em><b>Is Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__IS_NATIVE = TRIGGER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Execution Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD = TRIGGER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Module</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER__MODULE = TRIGGER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Software Module Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER_FEATURE_COUNT = TRIGGER_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Software Module Trigger</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_MODULE_TRIGGER_OPERATION_COUNT = TRIGGER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModelResourceImpl <em>Model Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModelResourceImpl
	 * @see eurema.impl.EuremaPackageImpl#getModelResource()
	 * @generated
	 */
	int MODEL_RESOURCE = 31;

	/**
	 * The feature id for the '<em><b>URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__URI = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Bound By Runtime Models</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS = 2;

	/**
	 * The feature id for the '<em><b>Bound By Megamodel Modules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES = 3;

	/**
	 * The number of structural features of the '<em>Model Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Model Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.EventTypeImpl <em>Event Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.EventTypeImpl
	 * @see eurema.impl.EuremaPackageImpl#getEventType()
	 * @generated
	 */
	int EVENT_TYPE = 32;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__SUPER_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Sub Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE__SUB_TYPES = 2;

	/**
	 * The number of structural features of the '<em>Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Event Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EVENT_TYPE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ArchitectureImpl <em>Architecture</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ArchitectureImpl
	 * @see eurema.impl.EuremaPackageImpl#getArchitecture()
	 * @generated
	 */
	int ARCHITECTURE = 33;

	/**
	 * The feature id for the '<em><b>Environment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__ENVIRONMENT = 0;

	/**
	 * The feature id for the '<em><b>Layers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__LAYERS = 1;

	/**
	 * The feature id for the '<em><b>Model Resource Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE__MODEL_RESOURCE_SET = 2;

	/**
	 * The number of structural features of the '<em>Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Architecture</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCHITECTURE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.MegamodelModuleImpl <em>Megamodel Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.MegamodelModuleImpl
	 * @see eurema.impl.EuremaPackageImpl#getMegamodelModule()
	 * @generated
	 */
	int MEGAMODEL_MODULE = 34;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__UID = MODULE__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__NAME = MODULE__NAME;

	/**
	 * The feature id for the '<em><b>Senses</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__SENSES = MODULE__SENSES;

	/**
	 * The feature id for the '<em><b>Sensed By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__SENSED_BY = MODULE__SENSED_BY;

	/**
	 * The feature id for the '<em><b>Effects</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__EFFECTS = MODULE__EFFECTS;

	/**
	 * The feature id for the '<em><b>Effected By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__EFFECTED_BY = MODULE__EFFECTED_BY;

	/**
	 * The feature id for the '<em><b>Layer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__LAYER = MODULE__LAYER;

	/**
	 * The feature id for the '<em><b>Megamodel</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__MEGAMODEL = MODULE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__CONTEXT = MODULE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Trigger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__TRIGGER = MODULE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Binding</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE__BINDING = MODULE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Megamodel Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_FEATURE_COUNT = MODULE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Megamodel Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEGAMODEL_MODULE_OPERATION_COUNT = MODULE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.SoftwareImpl <em>Software</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.SoftwareImpl
	 * @see eurema.impl.EuremaPackageImpl#getSoftware()
	 * @generated
	 */
	int SOFTWARE = 35;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__UID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Modules</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE__MODULES = 3;

	/**
	 * The number of structural features of the '<em>Software</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Software</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.EntryImpl <em>Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.EntryImpl
	 * @see eurema.impl.EuremaPackageImpl#getEntry()
	 * @generated
	 */
	int ENTRY = 36;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__UID = MEGAMODEL_ELEMENT__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__NAME = MEGAMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__DESCRIPTION = MEGAMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__OPERATION = MEGAMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__INCOMING = MEGAMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_FEATURE_COUNT = MEGAMODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_OPERATION_COUNT = MEGAMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ExitImpl <em>Exit</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ExitImpl
	 * @see eurema.impl.EuremaPackageImpl#getExit()
	 * @generated
	 */
	int EXIT = 37;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__UID = MEGAMODEL_ELEMENT__UID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__NAME = MEGAMODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__DESCRIPTION = MEGAMODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Operation</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__OPERATION = MEGAMODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT__OUTGOING = MEGAMODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Exit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_FEATURE_COUNT = MEGAMODEL_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * The number of operations of the '<em>Exit</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_OPERATION_COUNT = MEGAMODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link eurema.impl.RepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.RepositoryImpl
	 * @see eurema.impl.EuremaPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 38;

	/**
	 * The feature id for the '<em><b>Software</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__SOFTWARE = 0;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = 1;


	/**
	 * The number of operations of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link eurema.impl.ModelResourceSetImpl <em>Model Resource Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see eurema.impl.ModelResourceSetImpl
	 * @see eurema.impl.EuremaPackageImpl#getModelResourceSet()
	 * @generated
	 */
	int MODEL_RESOURCE_SET = 39;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_SET__RESOURCES = 0;

	/**
	 * The number of structural features of the '<em>Model Resource Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_SET_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Model Resource Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_RESOURCE_SET_OPERATION_COUNT = 0;

	/**
	 * Returns the meta object for class '{@link eurema.Megamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel</em>'.
	 * @see eurema.Megamodel
	 * @generated
	 */
	EClass getMegamodel();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Megamodel#getInitialOperations <em>Initial Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Initial Operations</em>'.
	 * @see eurema.Megamodel#getInitialOperations()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_InitialOperations();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Megamodel#getFinalOperations <em>Final Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Final Operations</em>'.
	 * @see eurema.Megamodel#getFinalOperations()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_FinalOperations();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Megamodel#getDecisionOperations <em>Decision Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Decision Operations</em>'.
	 * @see eurema.Megamodel#getDecisionOperations()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_DecisionOperations();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Megamodel#getBehavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Behavior</em>'.
	 * @see eurema.Megamodel#getBehavior()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_Behavior();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Megamodel#getModels <em>Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Models</em>'.
	 * @see eurema.Megamodel#getModels()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_Models();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Megamodel#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eurema.Megamodel#getUid()
	 * @see #getMegamodel()
	 * @generated
	 */
	EAttribute getMegamodel_Uid();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Megamodel#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Megamodel#getName()
	 * @see #getMegamodel()
	 * @generated
	 */
	EAttribute getMegamodel_Name();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Megamodel#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eurema.Megamodel#getDescription()
	 * @see #getMegamodel()
	 * @generated
	 */
	EAttribute getMegamodel_Description();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Megamodel#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see eurema.Megamodel#getModule()
	 * @see #getMegamodel()
	 * @generated
	 */
	EReference getMegamodel_Module();

	/**
	 * Returns the meta object for class '{@link eurema.InitialOperation <em>Initial Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Initial Operation</em>'.
	 * @see eurema.InitialOperation
	 * @generated
	 */
	EClass getInitialOperation();

	/**
	 * Returns the meta object for the container reference '{@link eurema.InitialOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Megamodel</em>'.
	 * @see eurema.InitialOperation#getMegamodel()
	 * @see #getInitialOperation()
	 * @generated
	 */
	EReference getInitialOperation_Megamodel();

	/**
	 * Returns the meta object for class '{@link eurema.DecisionOperation <em>Decision Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision Operation</em>'.
	 * @see eurema.DecisionOperation
	 * @generated
	 */
	EClass getDecisionOperation();

	/**
	 * Returns the meta object for the container reference '{@link eurema.DecisionOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Megamodel</em>'.
	 * @see eurema.DecisionOperation#getMegamodel()
	 * @see #getDecisionOperation()
	 * @generated
	 */
	EReference getDecisionOperation_Megamodel();

	/**
	 * Returns the meta object for class '{@link eurema.FinalOperation <em>Final Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final Operation</em>'.
	 * @see eurema.FinalOperation
	 * @generated
	 */
	EClass getFinalOperation();

	/**
	 * Returns the meta object for the container reference '{@link eurema.FinalOperation#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Megamodel</em>'.
	 * @see eurema.FinalOperation#getMegamodel()
	 * @see #getFinalOperation()
	 * @generated
	 */
	EReference getFinalOperation_Megamodel();

	/**
	 * Returns the meta object for the attribute '{@link eurema.FinalOperation#isIsDestructive <em>Is Destructive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Destructive</em>'.
	 * @see eurema.FinalOperation#isIsDestructive()
	 * @see #getFinalOperation()
	 * @generated
	 */
	EAttribute getFinalOperation_IsDestructive();

	/**
	 * Returns the meta object for class '{@link eurema.OperationBehavior <em>Operation Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Behavior</em>'.
	 * @see eurema.OperationBehavior
	 * @generated
	 */
	EClass getOperationBehavior();

	/**
	 * Returns the meta object for the container reference '{@link eurema.OperationBehavior#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Megamodel</em>'.
	 * @see eurema.OperationBehavior#getMegamodel()
	 * @see #getOperationBehavior()
	 * @generated
	 */
	EReference getOperationBehavior_Megamodel();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.OperationBehavior#getModelUsages <em>Model Usages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Model Usages</em>'.
	 * @see eurema.OperationBehavior#getModelUsages()
	 * @see #getOperationBehavior()
	 * @generated
	 */
	EReference getOperationBehavior_ModelUsages();

	/**
	 * Returns the meta object for class '{@link eurema.MegamodelCall <em>Megamodel Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel Call</em>'.
	 * @see eurema.MegamodelCall
	 * @generated
	 */
	EClass getMegamodelCall();

	/**
	 * Returns the meta object for the reference '{@link eurema.MegamodelCall#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see eurema.MegamodelCall#getBinding()
	 * @see #getMegamodelCall()
	 * @generated
	 */
	EReference getMegamodelCall_Binding();

	/**
	 * Returns the meta object for class '{@link eurema.ModelOperation <em>Model Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Operation</em>'.
	 * @see eurema.ModelOperation
	 * @generated
	 */
	EClass getModelOperation();

	/**
	 * Returns the meta object for the reference '{@link eurema.ModelOperation#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see eurema.ModelOperation#getBinding()
	 * @see #getModelOperation()
	 * @generated
	 */
	EReference getModelOperation_Binding();

	/**
	 * Returns the meta object for class '{@link eurema.ControlOperation <em>Control Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Control Operation</em>'.
	 * @see eurema.ControlOperation
	 * @generated
	 */
	EClass getControlOperation();

	/**
	 * Returns the meta object for class '{@link eurema.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see eurema.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Operation#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see eurema.Operation#getEntries()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Entries();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Operation#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exits</em>'.
	 * @see eurema.Operation#getExits()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Exits();

	/**
	 * Returns the meta object for class '{@link eurema.SoftwareModule <em>Software Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Module</em>'.
	 * @see eurema.SoftwareModule
	 * @generated
	 */
	EClass getSoftwareModule();

	/**
	 * Returns the meta object for the attribute '{@link eurema.SoftwareModule#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see eurema.SoftwareModule#getImplementation()
	 * @see #getSoftwareModule()
	 * @generated
	 */
	EAttribute getSoftwareModule_Implementation();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.SoftwareModule#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see eurema.SoftwareModule#getTrigger()
	 * @see #getSoftwareModule()
	 * @generated
	 */
	EReference getSoftwareModule_Trigger();

	/**
	 * Returns the meta object for the reference '{@link eurema.SoftwareModule#getSoftware <em>Software</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Software</em>'.
	 * @see eurema.SoftwareModule#getSoftware()
	 * @see #getSoftwareModule()
	 * @generated
	 */
	EReference getSoftwareModule_Software();

	/**
	 * Returns the meta object for class '{@link eurema.Module <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Module</em>'.
	 * @see eurema.Module
	 * @generated
	 */
	EClass getModule();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Module#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eurema.Module#getUid()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Uid();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Module#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Module#getName()
	 * @see #getModule()
	 * @generated
	 */
	EAttribute getModule_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Module#getSenses <em>Senses</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Senses</em>'.
	 * @see eurema.Module#getSenses()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Senses();

	/**
	 * Returns the meta object for the reference list '{@link eurema.Module#getSensedBy <em>Sensed By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sensed By</em>'.
	 * @see eurema.Module#getSensedBy()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_SensedBy();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Module#getEffects <em>Effects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Effects</em>'.
	 * @see eurema.Module#getEffects()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Effects();

	/**
	 * Returns the meta object for the reference list '{@link eurema.Module#getEffectedBy <em>Effected By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Effected By</em>'.
	 * @see eurema.Module#getEffectedBy()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_EffectedBy();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Module#getLayer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Layer</em>'.
	 * @see eurema.Module#getLayer()
	 * @see #getModule()
	 * @generated
	 */
	EReference getModule_Layer();

	/**
	 * Returns the meta object for class '{@link eurema.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see eurema.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Model#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Megamodel</em>'.
	 * @see eurema.Model#getMegamodel()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Megamodel();

	/**
	 * Returns the meta object for the reference list '{@link eurema.Model#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used By</em>'.
	 * @see eurema.Model#getUsedBy()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_UsedBy();

	/**
	 * Returns the meta object for class '{@link eurema.RuntimeModel <em>Runtime Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Model</em>'.
	 * @see eurema.RuntimeModel
	 * @generated
	 */
	EClass getRuntimeModel();

	/**
	 * Returns the meta object for the reference '{@link eurema.RuntimeModel#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see eurema.RuntimeModel#getBinding()
	 * @see #getRuntimeModel()
	 * @generated
	 */
	EReference getRuntimeModel_Binding();

	/**
	 * Returns the meta object for class '{@link eurema.MegamodelProxy <em>Megamodel Proxy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel Proxy</em>'.
	 * @see eurema.MegamodelProxy
	 * @generated
	 */
	EClass getMegamodelProxy();

	/**
	 * Returns the meta object for the reference '{@link eurema.MegamodelProxy#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see eurema.MegamodelProxy#getBinding()
	 * @see #getMegamodelProxy()
	 * @generated
	 */
	EReference getMegamodelProxy_Binding();

	/**
	 * Returns the meta object for class '{@link eurema.ModelUse <em>Model Use</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Use</em>'.
	 * @see eurema.ModelUse
	 * @generated
	 */
	EClass getModelUse();

	/**
	 * Returns the meta object for the container reference '{@link eurema.ModelUse#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Operation</em>'.
	 * @see eurema.ModelUse#getOperation()
	 * @see #getModelUse()
	 * @generated
	 */
	EReference getModelUse_Operation();

	/**
	 * Returns the meta object for the reference '{@link eurema.ModelUse#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model</em>'.
	 * @see eurema.ModelUse#getModel()
	 * @see #getModelUse()
	 * @generated
	 */
	EReference getModelUse_Model();

	/**
	 * Returns the meta object for class '{@link eurema.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see eurema.Input
	 * @generated
	 */
	EClass getInput();

	/**
	 * Returns the meta object for class '{@link eurema.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see eurema.Output
	 * @generated
	 */
	EClass getOutput();

	/**
	 * Returns the meta object for class '{@link eurema.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see eurema.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.Transition#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see eurema.Transition#getCondition()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Condition();

	/**
	 * Returns the meta object for the reference '{@link eurema.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eurema.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Target();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eurema.Transition#getSource()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Source();

	/**
	 * Returns the meta object for class '{@link eurema.Executable <em>Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Executable</em>'.
	 * @see eurema.Executable
	 * @generated
	 */
	EClass getExecutable();

	/**
	 * Returns the meta object for the reference '{@link eurema.Executable#getExecInfo <em>Exec Info</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Exec Info</em>'.
	 * @see eurema.Executable#getExecInfo()
	 * @see #getExecutable()
	 * @generated
	 */
	EReference getExecutable_ExecInfo();

	/**
	 * Returns the meta object for class '{@link eurema.Condition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Condition</em>'.
	 * @see eurema.Condition
	 * @generated
	 */
	EClass getCondition();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Condition#getExpr <em>Expr</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Expr</em>'.
	 * @see eurema.Condition#getExpr()
	 * @see #getCondition()
	 * @generated
	 */
	EAttribute getCondition_Expr();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Condition#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Transition</em>'.
	 * @see eurema.Condition#getTransition()
	 * @see #getCondition()
	 * @generated
	 */
	EReference getCondition_Transition();

	/**
	 * Returns the meta object for class '{@link eurema.MegamodelElement <em>Megamodel Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel Element</em>'.
	 * @see eurema.MegamodelElement
	 * @generated
	 */
	EClass getMegamodelElement();

	/**
	 * Returns the meta object for the attribute '{@link eurema.MegamodelElement#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eurema.MegamodelElement#getUid()
	 * @see #getMegamodelElement()
	 * @generated
	 */
	EAttribute getMegamodelElement_Uid();

	/**
	 * Returns the meta object for the attribute '{@link eurema.MegamodelElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.MegamodelElement#getName()
	 * @see #getMegamodelElement()
	 * @generated
	 */
	EAttribute getMegamodelElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link eurema.MegamodelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eurema.MegamodelElement#getDescription()
	 * @see #getMegamodelElement()
	 * @generated
	 */
	EAttribute getMegamodelElement_Description();

	/**
	 * Returns the meta object for class '{@link eurema.ExecutionContext <em>Execution Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Context</em>'.
	 * @see eurema.ExecutionContext
	 * @generated
	 */
	EClass getExecutionContext();

	/**
	 * Returns the meta object for the reference '{@link eurema.ExecutionContext#getCurrent <em>Current</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current</em>'.
	 * @see eurema.ExecutionContext#getCurrent()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Current();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.ExecutionContext#getExecInfos <em>Exec Infos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Exec Infos</em>'.
	 * @see eurema.ExecutionContext#getExecInfos()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_ExecInfos();

	/**
	 * Returns the meta object for the container reference '{@link eurema.ExecutionContext#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Environment</em>'.
	 * @see eurema.ExecutionContext#getEnvironment()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Environment();

	/**
	 * Returns the meta object for the attribute '{@link eurema.ExecutionContext#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eurema.ExecutionContext#getUid()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EAttribute getExecutionContext_Uid();

	/**
	 * Returns the meta object for the reference '{@link eurema.ExecutionContext#getExecuting <em>Executing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executing</em>'.
	 * @see eurema.ExecutionContext#getExecuting()
	 * @see #getExecutionContext()
	 * @generated
	 */
	EReference getExecutionContext_Executing();

	/**
	 * Returns the meta object for class '{@link eurema.ExecutionInformation <em>Execution Information</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Execution Information</em>'.
	 * @see eurema.ExecutionInformation
	 * @generated
	 */
	EClass getExecutionInformation();

	/**
	 * Returns the meta object for the attribute '{@link eurema.ExecutionInformation#getCount <em>Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Count</em>'.
	 * @see eurema.ExecutionInformation#getCount()
	 * @see #getExecutionInformation()
	 * @generated
	 */
	EAttribute getExecutionInformation_Count();

	/**
	 * Returns the meta object for the attribute '{@link eurema.ExecutionInformation#getTime <em>Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Time</em>'.
	 * @see eurema.ExecutionInformation#getTime()
	 * @see #getExecutionInformation()
	 * @generated
	 */
	EAttribute getExecutionInformation_Time();

	/**
	 * Returns the meta object for the reference '{@link eurema.ExecutionInformation#getExecutable <em>Executable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Executable</em>'.
	 * @see eurema.ExecutionInformation#getExecutable()
	 * @see #getExecutionInformation()
	 * @generated
	 */
	EReference getExecutionInformation_Executable();

	/**
	 * Returns the meta object for the container reference '{@link eurema.ExecutionInformation#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Context</em>'.
	 * @see eurema.ExecutionInformation#getContext()
	 * @see #getExecutionInformation()
	 * @generated
	 */
	EReference getExecutionInformation_Context();

	/**
	 * Returns the meta object for class '{@link eurema.Sensing <em>Sensing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sensing</em>'.
	 * @see eurema.Sensing
	 * @generated
	 */
	EClass getSensing();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Sensing#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eurema.Sensing#getSource()
	 * @see #getSensing()
	 * @generated
	 */
	EReference getSensing_Source();

	/**
	 * Returns the meta object for the reference '{@link eurema.Sensing#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eurema.Sensing#getTarget()
	 * @see #getSensing()
	 * @generated
	 */
	EReference getSensing_Target();

	/**
	 * Returns the meta object for the reference '{@link eurema.Sensing#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Trigger</em>'.
	 * @see eurema.Sensing#getTrigger()
	 * @see #getSensing()
	 * @generated
	 */
	EReference getSensing_Trigger();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Sensing#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Sensing#getName()
	 * @see #getSensing()
	 * @generated
	 */
	EAttribute getSensing_Name();

	/**
	 * Returns the meta object for class '{@link eurema.Effecting <em>Effecting</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Effecting</em>'.
	 * @see eurema.Effecting
	 * @generated
	 */
	EClass getEffecting();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Effecting#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Source</em>'.
	 * @see eurema.Effecting#getSource()
	 * @see #getEffecting()
	 * @generated
	 */
	EReference getEffecting_Source();

	/**
	 * Returns the meta object for the reference '{@link eurema.Effecting#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see eurema.Effecting#getTarget()
	 * @see #getEffecting()
	 * @generated
	 */
	EReference getEffecting_Target();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Effecting#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Effecting#getName()
	 * @see #getEffecting()
	 * @generated
	 */
	EAttribute getEffecting_Name();

	/**
	 * Returns the meta object for class '{@link eurema.RuntimeEnvironment <em>Runtime Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Environment</em>'.
	 * @see eurema.RuntimeEnvironment
	 * @generated
	 */
	EClass getRuntimeEnvironment();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.RuntimeEnvironment#getExecutions <em>Executions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Executions</em>'.
	 * @see eurema.RuntimeEnvironment#getExecutions()
	 * @see #getRuntimeEnvironment()
	 * @generated
	 */
	EReference getRuntimeEnvironment_Executions();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.RuntimeEnvironment#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Architecture</em>'.
	 * @see eurema.RuntimeEnvironment#getArchitecture()
	 * @see #getRuntimeEnvironment()
	 * @generated
	 */
	EReference getRuntimeEnvironment_Architecture();

	/**
	 * Returns the meta object for class '{@link eurema.Layer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layer</em>'.
	 * @see eurema.Layer
	 * @generated
	 */
	EClass getLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Layer#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Modules</em>'.
	 * @see eurema.Layer#getModules()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_Modules();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Layer#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see eurema.Layer#getNumber()
	 * @see #getLayer()
	 * @generated
	 */
	EAttribute getLayer_Number();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Layer#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Layer#getName()
	 * @see #getLayer()
	 * @generated
	 */
	EAttribute getLayer_Name();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Layer#getArchitecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Architecture</em>'.
	 * @see eurema.Layer#getArchitecture()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_Architecture();

	/**
	 * Returns the meta object for class '{@link eurema.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see eurema.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Trigger#getPeriod <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Period</em>'.
	 * @see eurema.Trigger#getPeriod()
	 * @see #getTrigger()
	 * @generated
	 */
	EAttribute getTrigger_Period();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Trigger#getEvents <em>Events</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Events</em>'.
	 * @see eurema.Trigger#getEvents()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Events();

	/**
	 * Returns the meta object for the reference '{@link eurema.Trigger#getRefers <em>Refers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Refers</em>'.
	 * @see eurema.Trigger#getRefers()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_Refers();

	/**
	 * Returns the meta object for class '{@link eurema.Event <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event</em>'.
	 * @see eurema.Event
	 * @generated
	 */
	EClass getEvent();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Event#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Event#getName()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Name();

	/**
	 * Returns the meta object for the reference '{@link eurema.Event#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see eurema.Event#getType()
	 * @see #getEvent()
	 * @generated
	 */
	EReference getEvent_Type();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Event#getPayload <em>Payload</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Payload</em>'.
	 * @see eurema.Event#getPayload()
	 * @see #getEvent()
	 * @generated
	 */
	EAttribute getEvent_Payload();

	/**
	 * Returns the meta object for class '{@link eurema.MegamodelModuleTrigger <em>Megamodel Module Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel Module Trigger</em>'.
	 * @see eurema.MegamodelModuleTrigger
	 * @generated
	 */
	EClass getMegamodelModuleTrigger();

	/**
	 * Returns the meta object for the reference '{@link eurema.MegamodelModuleTrigger#getInitialOperation <em>Initial Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Initial Operation</em>'.
	 * @see eurema.MegamodelModuleTrigger#getInitialOperation()
	 * @see #getMegamodelModuleTrigger()
	 * @generated
	 */
	EReference getMegamodelModuleTrigger_InitialOperation();

	/**
	 * Returns the meta object for the container reference '{@link eurema.MegamodelModuleTrigger#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see eurema.MegamodelModuleTrigger#getModule()
	 * @see #getMegamodelModuleTrigger()
	 * @generated
	 */
	EReference getMegamodelModuleTrigger_Module();

	/**
	 * Returns the meta object for class '{@link eurema.SoftwareModuleTrigger <em>Software Module Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software Module Trigger</em>'.
	 * @see eurema.SoftwareModuleTrigger
	 * @generated
	 */
	EClass getSoftwareModuleTrigger();

	/**
	 * Returns the meta object for the attribute '{@link eurema.SoftwareModuleTrigger#isIsNative <em>Is Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Native</em>'.
	 * @see eurema.SoftwareModuleTrigger#isIsNative()
	 * @see #getSoftwareModuleTrigger()
	 * @generated
	 */
	EAttribute getSoftwareModuleTrigger_IsNative();

	/**
	 * Returns the meta object for the attribute '{@link eurema.SoftwareModuleTrigger#getExecutionMethod <em>Execution Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Execution Method</em>'.
	 * @see eurema.SoftwareModuleTrigger#getExecutionMethod()
	 * @see #getSoftwareModuleTrigger()
	 * @generated
	 */
	EAttribute getSoftwareModuleTrigger_ExecutionMethod();

	/**
	 * Returns the meta object for the container reference '{@link eurema.SoftwareModuleTrigger#getModule <em>Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Module</em>'.
	 * @see eurema.SoftwareModuleTrigger#getModule()
	 * @see #getSoftwareModuleTrigger()
	 * @generated
	 */
	EReference getSoftwareModuleTrigger_Module();

	/**
	 * Returns the meta object for class '{@link eurema.ModelResource <em>Model Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Resource</em>'.
	 * @see eurema.ModelResource
	 * @generated
	 */
	EClass getModelResource();

	/**
	 * Returns the meta object for the attribute '{@link eurema.ModelResource#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see eurema.ModelResource#getURI()
	 * @see #getModelResource()
	 * @generated
	 */
	EAttribute getModelResource_URI();

	/**
	 * Returns the meta object for the attribute '{@link eurema.ModelResource#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.ModelResource#getName()
	 * @see #getModelResource()
	 * @generated
	 */
	EAttribute getModelResource_Name();

	/**
	 * Returns the meta object for the reference list '{@link eurema.ModelResource#getBoundByRuntimeModels <em>Bound By Runtime Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bound By Runtime Models</em>'.
	 * @see eurema.ModelResource#getBoundByRuntimeModels()
	 * @see #getModelResource()
	 * @generated
	 */
	EReference getModelResource_BoundByRuntimeModels();

	/**
	 * Returns the meta object for the reference list '{@link eurema.ModelResource#getBoundByMegamodelModules <em>Bound By Megamodel Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bound By Megamodel Modules</em>'.
	 * @see eurema.ModelResource#getBoundByMegamodelModules()
	 * @see #getModelResource()
	 * @generated
	 */
	EReference getModelResource_BoundByMegamodelModules();

	/**
	 * Returns the meta object for class '{@link eurema.EventType <em>Event Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Event Type</em>'.
	 * @see eurema.EventType
	 * @generated
	 */
	EClass getEventType();

	/**
	 * Returns the meta object for the attribute '{@link eurema.EventType#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see eurema.EventType#getType()
	 * @see #getEventType()
	 * @generated
	 */
	EAttribute getEventType_Type();

	/**
	 * Returns the meta object for the container reference '{@link eurema.EventType#getSuperType <em>Super Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Super Type</em>'.
	 * @see eurema.EventType#getSuperType()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_SuperType();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.EventType#getSubTypes <em>Sub Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sub Types</em>'.
	 * @see eurema.EventType#getSubTypes()
	 * @see #getEventType()
	 * @generated
	 */
	EReference getEventType_SubTypes();

	/**
	 * Returns the meta object for class '{@link eurema.Architecture <em>Architecture</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Architecture</em>'.
	 * @see eurema.Architecture
	 * @generated
	 */
	EClass getArchitecture();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Architecture#getEnvironment <em>Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Environment</em>'.
	 * @see eurema.Architecture#getEnvironment()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_Environment();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Architecture#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layers</em>'.
	 * @see eurema.Architecture#getLayers()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_Layers();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.Architecture#getModelResourceSet <em>Model Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model Resource Set</em>'.
	 * @see eurema.Architecture#getModelResourceSet()
	 * @see #getArchitecture()
	 * @generated
	 */
	EReference getArchitecture_ModelResourceSet();

	/**
	 * Returns the meta object for class '{@link eurema.MegamodelModule <em>Megamodel Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Megamodel Module</em>'.
	 * @see eurema.MegamodelModule
	 * @generated
	 */
	EClass getMegamodelModule();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.MegamodelModule#getMegamodel <em>Megamodel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Megamodel</em>'.
	 * @see eurema.MegamodelModule#getMegamodel()
	 * @see #getMegamodelModule()
	 * @generated
	 */
	EReference getMegamodelModule_Megamodel();

	/**
	 * Returns the meta object for the reference '{@link eurema.MegamodelModule#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context</em>'.
	 * @see eurema.MegamodelModule#getContext()
	 * @see #getMegamodelModule()
	 * @generated
	 */
	EReference getMegamodelModule_Context();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.MegamodelModule#getTrigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Trigger</em>'.
	 * @see eurema.MegamodelModule#getTrigger()
	 * @see #getMegamodelModule()
	 * @generated
	 */
	EReference getMegamodelModule_Trigger();

	/**
	 * Returns the meta object for the reference '{@link eurema.MegamodelModule#getBinding <em>Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Binding</em>'.
	 * @see eurema.MegamodelModule#getBinding()
	 * @see #getMegamodelModule()
	 * @generated
	 */
	EReference getMegamodelModule_Binding();

	/**
	 * Returns the meta object for class '{@link eurema.Software <em>Software</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software</em>'.
	 * @see eurema.Software
	 * @generated
	 */
	EClass getSoftware();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Software#getUid <em>Uid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uid</em>'.
	 * @see eurema.Software#getUid()
	 * @see #getSoftware()
	 * @generated
	 */
	EAttribute getSoftware_Uid();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Software#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see eurema.Software#getName()
	 * @see #getSoftware()
	 * @generated
	 */
	EAttribute getSoftware_Name();

	/**
	 * Returns the meta object for the attribute '{@link eurema.Software#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see eurema.Software#getDescription()
	 * @see #getSoftware()
	 * @generated
	 */
	EAttribute getSoftware_Description();

	/**
	 * Returns the meta object for the reference list '{@link eurema.Software#getModules <em>Modules</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Modules</em>'.
	 * @see eurema.Software#getModules()
	 * @see #getSoftware()
	 * @generated
	 */
	EReference getSoftware_Modules();

	/**
	 * Returns the meta object for class '{@link eurema.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see eurema.Entry
	 * @generated
	 */
	EClass getEntry();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Entry#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Operation</em>'.
	 * @see eurema.Entry#getOperation()
	 * @see #getEntry()
	 * @generated
	 */
	EReference getEntry_Operation();

	/**
	 * Returns the meta object for the reference list '{@link eurema.Entry#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming</em>'.
	 * @see eurema.Entry#getIncoming()
	 * @see #getEntry()
	 * @generated
	 */
	EReference getEntry_Incoming();

	/**
	 * Returns the meta object for class '{@link eurema.Exit <em>Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exit</em>'.
	 * @see eurema.Exit
	 * @generated
	 */
	EClass getExit();

	/**
	 * Returns the meta object for the container reference '{@link eurema.Exit#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Operation</em>'.
	 * @see eurema.Exit#getOperation()
	 * @see #getExit()
	 * @generated
	 */
	EReference getExit_Operation();

	/**
	 * Returns the meta object for the containment reference '{@link eurema.Exit#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Outgoing</em>'.
	 * @see eurema.Exit#getOutgoing()
	 * @see #getExit()
	 * @generated
	 */
	EReference getExit_Outgoing();

	/**
	 * Returns the meta object for class '{@link eurema.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see eurema.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.Repository#getSoftware <em>Software</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Software</em>'.
	 * @see eurema.Repository#getSoftware()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Software();

	/**
	 * Returns the meta object for class '{@link eurema.ModelResourceSet <em>Model Resource Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Resource Set</em>'.
	 * @see eurema.ModelResourceSet
	 * @generated
	 */
	EClass getModelResourceSet();

	/**
	 * Returns the meta object for the containment reference list '{@link eurema.ModelResourceSet#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resources</em>'.
	 * @see eurema.ModelResourceSet#getResources()
	 * @see #getModelResourceSet()
	 * @generated
	 */
	EReference getModelResourceSet_Resources();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EuremaFactory getEuremaFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelImpl <em>Megamodel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodel()
		 * @generated
		 */
		EClass MEGAMODEL = eINSTANCE.getMegamodel();

		/**
		 * The meta object literal for the '<em><b>Initial Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__INITIAL_OPERATIONS = eINSTANCE.getMegamodel_InitialOperations();

		/**
		 * The meta object literal for the '<em><b>Final Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__FINAL_OPERATIONS = eINSTANCE.getMegamodel_FinalOperations();

		/**
		 * The meta object literal for the '<em><b>Decision Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__DECISION_OPERATIONS = eINSTANCE.getMegamodel_DecisionOperations();

		/**
		 * The meta object literal for the '<em><b>Behavior</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__BEHAVIOR = eINSTANCE.getMegamodel_Behavior();

		/**
		 * The meta object literal for the '<em><b>Models</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__MODELS = eINSTANCE.getMegamodel_Models();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL__UID = eINSTANCE.getMegamodel_Uid();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL__NAME = eINSTANCE.getMegamodel_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL__DESCRIPTION = eINSTANCE.getMegamodel_Description();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL__MODULE = eINSTANCE.getMegamodel_Module();

		/**
		 * The meta object literal for the '{@link eurema.impl.InitialOperationImpl <em>Initial Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.InitialOperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getInitialOperation()
		 * @generated
		 */
		EClass INITIAL_OPERATION = eINSTANCE.getInitialOperation();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INITIAL_OPERATION__MEGAMODEL = eINSTANCE.getInitialOperation_Megamodel();

		/**
		 * The meta object literal for the '{@link eurema.impl.DecisionOperationImpl <em>Decision Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.DecisionOperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getDecisionOperation()
		 * @generated
		 */
		EClass DECISION_OPERATION = eINSTANCE.getDecisionOperation();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DECISION_OPERATION__MEGAMODEL = eINSTANCE.getDecisionOperation_Megamodel();

		/**
		 * The meta object literal for the '{@link eurema.impl.FinalOperationImpl <em>Final Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.FinalOperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getFinalOperation()
		 * @generated
		 */
		EClass FINAL_OPERATION = eINSTANCE.getFinalOperation();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FINAL_OPERATION__MEGAMODEL = eINSTANCE.getFinalOperation_Megamodel();

		/**
		 * The meta object literal for the '<em><b>Is Destructive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FINAL_OPERATION__IS_DESTRUCTIVE = eINSTANCE.getFinalOperation_IsDestructive();

		/**
		 * The meta object literal for the '{@link eurema.impl.OperationBehaviorImpl <em>Operation Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.OperationBehaviorImpl
		 * @see eurema.impl.EuremaPackageImpl#getOperationBehavior()
		 * @generated
		 */
		EClass OPERATION_BEHAVIOR = eINSTANCE.getOperationBehavior();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_BEHAVIOR__MEGAMODEL = eINSTANCE.getOperationBehavior_Megamodel();

		/**
		 * The meta object literal for the '<em><b>Model Usages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_BEHAVIOR__MODEL_USAGES = eINSTANCE.getOperationBehavior_ModelUsages();

		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelCallImpl <em>Megamodel Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelCallImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodelCall()
		 * @generated
		 */
		EClass MEGAMODEL_CALL = eINSTANCE.getMegamodelCall();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_CALL__BINDING = eINSTANCE.getMegamodelCall_Binding();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModelOperationImpl <em>Model Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModelOperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getModelOperation()
		 * @generated
		 */
		EClass MODEL_OPERATION = eINSTANCE.getModelOperation();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_OPERATION__BINDING = eINSTANCE.getModelOperation_Binding();

		/**
		 * The meta object literal for the '{@link eurema.impl.ControlOperationImpl <em>Control Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ControlOperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getControlOperation()
		 * @generated
		 */
		EClass CONTROL_OPERATION = eINSTANCE.getControlOperation();

		/**
		 * The meta object literal for the '{@link eurema.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.OperationImpl
		 * @see eurema.impl.EuremaPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__ENTRIES = eINSTANCE.getOperation_Entries();

		/**
		 * The meta object literal for the '<em><b>Exits</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__EXITS = eINSTANCE.getOperation_Exits();

		/**
		 * The meta object literal for the '{@link eurema.impl.SoftwareModuleImpl <em>Software Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.SoftwareModuleImpl
		 * @see eurema.impl.EuremaPackageImpl#getSoftwareModule()
		 * @generated
		 */
		EClass SOFTWARE_MODULE = eINSTANCE.getSoftwareModule();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_MODULE__IMPLEMENTATION = eINSTANCE.getSoftwareModule_Implementation();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_MODULE__TRIGGER = eINSTANCE.getSoftwareModule_Trigger();

		/**
		 * The meta object literal for the '<em><b>Software</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_MODULE__SOFTWARE = eINSTANCE.getSoftwareModule_Software();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModuleImpl <em>Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModuleImpl
		 * @see eurema.impl.EuremaPackageImpl#getModule()
		 * @generated
		 */
		EClass MODULE = eINSTANCE.getModule();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__UID = eINSTANCE.getModule_Uid();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODULE__NAME = eINSTANCE.getModule_Name();

		/**
		 * The meta object literal for the '<em><b>Senses</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__SENSES = eINSTANCE.getModule_Senses();

		/**
		 * The meta object literal for the '<em><b>Sensed By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__SENSED_BY = eINSTANCE.getModule_SensedBy();

		/**
		 * The meta object literal for the '<em><b>Effects</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__EFFECTS = eINSTANCE.getModule_Effects();

		/**
		 * The meta object literal for the '<em><b>Effected By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__EFFECTED_BY = eINSTANCE.getModule_EffectedBy();

		/**
		 * The meta object literal for the '<em><b>Layer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODULE__LAYER = eINSTANCE.getModule_Layer();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModelImpl
		 * @see eurema.impl.EuremaPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__MEGAMODEL = eINSTANCE.getModel_Megamodel();

		/**
		 * The meta object literal for the '<em><b>Used By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__USED_BY = eINSTANCE.getModel_UsedBy();

		/**
		 * The meta object literal for the '{@link eurema.impl.RuntimeModelImpl <em>Runtime Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.RuntimeModelImpl
		 * @see eurema.impl.EuremaPackageImpl#getRuntimeModel()
		 * @generated
		 */
		EClass RUNTIME_MODEL = eINSTANCE.getRuntimeModel();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_MODEL__BINDING = eINSTANCE.getRuntimeModel_Binding();

		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelProxyImpl <em>Megamodel Proxy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelProxyImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodelProxy()
		 * @generated
		 */
		EClass MEGAMODEL_PROXY = eINSTANCE.getMegamodelProxy();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_PROXY__BINDING = eINSTANCE.getMegamodelProxy_Binding();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModelUseImpl <em>Model Use</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModelUseImpl
		 * @see eurema.impl.EuremaPackageImpl#getModelUse()
		 * @generated
		 */
		EClass MODEL_USE = eINSTANCE.getModelUse();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_USE__OPERATION = eINSTANCE.getModelUse_Operation();

		/**
		 * The meta object literal for the '<em><b>Model</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_USE__MODEL = eINSTANCE.getModelUse_Model();

		/**
		 * The meta object literal for the '{@link eurema.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.InputImpl
		 * @see eurema.impl.EuremaPackageImpl#getInput()
		 * @generated
		 */
		EClass INPUT = eINSTANCE.getInput();

		/**
		 * The meta object literal for the '{@link eurema.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.OutputImpl
		 * @see eurema.impl.EuremaPackageImpl#getOutput()
		 * @generated
		 */
		EClass OUTPUT = eINSTANCE.getOutput();

		/**
		 * The meta object literal for the '{@link eurema.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.TransitionImpl
		 * @see eurema.impl.EuremaPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__CONDITION = eINSTANCE.getTransition_Condition();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__SOURCE = eINSTANCE.getTransition_Source();

		/**
		 * The meta object literal for the '{@link eurema.impl.ExecutableImpl <em>Executable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ExecutableImpl
		 * @see eurema.impl.EuremaPackageImpl#getExecutable()
		 * @generated
		 */
		EClass EXECUTABLE = eINSTANCE.getExecutable();

		/**
		 * The meta object literal for the '<em><b>Exec Info</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTABLE__EXEC_INFO = eINSTANCE.getExecutable_ExecInfo();

		/**
		 * The meta object literal for the '{@link eurema.impl.ConditionImpl <em>Condition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ConditionImpl
		 * @see eurema.impl.EuremaPackageImpl#getCondition()
		 * @generated
		 */
		EClass CONDITION = eINSTANCE.getCondition();

		/**
		 * The meta object literal for the '<em><b>Expr</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONDITION__EXPR = eINSTANCE.getCondition_Expr();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONDITION__TRANSITION = eINSTANCE.getCondition_Transition();

		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelElementImpl <em>Megamodel Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelElementImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodelElement()
		 * @generated
		 */
		EClass MEGAMODEL_ELEMENT = eINSTANCE.getMegamodelElement();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL_ELEMENT__UID = eINSTANCE.getMegamodelElement_Uid();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL_ELEMENT__NAME = eINSTANCE.getMegamodelElement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MEGAMODEL_ELEMENT__DESCRIPTION = eINSTANCE.getMegamodelElement_Description();

		/**
		 * The meta object literal for the '{@link eurema.impl.ExecutionContextImpl <em>Execution Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ExecutionContextImpl
		 * @see eurema.impl.EuremaPackageImpl#getExecutionContext()
		 * @generated
		 */
		EClass EXECUTION_CONTEXT = eINSTANCE.getExecutionContext();

		/**
		 * The meta object literal for the '<em><b>Current</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__CURRENT = eINSTANCE.getExecutionContext_Current();

		/**
		 * The meta object literal for the '<em><b>Exec Infos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__EXEC_INFOS = eINSTANCE.getExecutionContext_ExecInfos();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__ENVIRONMENT = eINSTANCE.getExecutionContext_Environment();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_CONTEXT__UID = eINSTANCE.getExecutionContext_Uid();

		/**
		 * The meta object literal for the '<em><b>Executing</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_CONTEXT__EXECUTING = eINSTANCE.getExecutionContext_Executing();

		/**
		 * The meta object literal for the '{@link eurema.impl.ExecutionInformationImpl <em>Execution Information</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ExecutionInformationImpl
		 * @see eurema.impl.EuremaPackageImpl#getExecutionInformation()
		 * @generated
		 */
		EClass EXECUTION_INFORMATION = eINSTANCE.getExecutionInformation();

		/**
		 * The meta object literal for the '<em><b>Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_INFORMATION__COUNT = eINSTANCE.getExecutionInformation_Count();

		/**
		 * The meta object literal for the '<em><b>Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXECUTION_INFORMATION__TIME = eINSTANCE.getExecutionInformation_Time();

		/**
		 * The meta object literal for the '<em><b>Executable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_INFORMATION__EXECUTABLE = eINSTANCE.getExecutionInformation_Executable();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXECUTION_INFORMATION__CONTEXT = eINSTANCE.getExecutionInformation_Context();

		/**
		 * The meta object literal for the '{@link eurema.impl.SensingImpl <em>Sensing</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.SensingImpl
		 * @see eurema.impl.EuremaPackageImpl#getSensing()
		 * @generated
		 */
		EClass SENSING = eINSTANCE.getSensing();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SENSING__SOURCE = eINSTANCE.getSensing_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SENSING__TARGET = eINSTANCE.getSensing_Target();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SENSING__TRIGGER = eINSTANCE.getSensing_Trigger();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SENSING__NAME = eINSTANCE.getSensing_Name();

		/**
		 * The meta object literal for the '{@link eurema.impl.EffectingImpl <em>Effecting</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.EffectingImpl
		 * @see eurema.impl.EuremaPackageImpl#getEffecting()
		 * @generated
		 */
		EClass EFFECTING = eINSTANCE.getEffecting();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EFFECTING__SOURCE = eINSTANCE.getEffecting_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EFFECTING__TARGET = eINSTANCE.getEffecting_Target();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EFFECTING__NAME = eINSTANCE.getEffecting_Name();

		/**
		 * The meta object literal for the '{@link eurema.impl.RuntimeEnvironmentImpl <em>Runtime Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.RuntimeEnvironmentImpl
		 * @see eurema.impl.EuremaPackageImpl#getRuntimeEnvironment()
		 * @generated
		 */
		EClass RUNTIME_ENVIRONMENT = eINSTANCE.getRuntimeEnvironment();

		/**
		 * The meta object literal for the '<em><b>Executions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_ENVIRONMENT__EXECUTIONS = eINSTANCE.getRuntimeEnvironment_Executions();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_ENVIRONMENT__ARCHITECTURE = eINSTANCE.getRuntimeEnvironment_Architecture();

		/**
		 * The meta object literal for the '{@link eurema.impl.LayerImpl <em>Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.LayerImpl
		 * @see eurema.impl.EuremaPackageImpl#getLayer()
		 * @generated
		 */
		EClass LAYER = eINSTANCE.getLayer();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__MODULES = eINSTANCE.getLayer_Modules();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAYER__NUMBER = eINSTANCE.getLayer_Number();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LAYER__NAME = eINSTANCE.getLayer_Name();

		/**
		 * The meta object literal for the '<em><b>Architecture</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__ARCHITECTURE = eINSTANCE.getLayer_Architecture();

		/**
		 * The meta object literal for the '{@link eurema.impl.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.TriggerImpl
		 * @see eurema.impl.EuremaPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Period</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRIGGER__PERIOD = eINSTANCE.getTrigger_Period();

		/**
		 * The meta object literal for the '<em><b>Events</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__EVENTS = eINSTANCE.getTrigger_Events();

		/**
		 * The meta object literal for the '<em><b>Refers</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__REFERS = eINSTANCE.getTrigger_Refers();

		/**
		 * The meta object literal for the '{@link eurema.impl.EventImpl <em>Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.EventImpl
		 * @see eurema.impl.EuremaPackageImpl#getEvent()
		 * @generated
		 */
		EClass EVENT = eINSTANCE.getEvent();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__NAME = eINSTANCE.getEvent_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT__TYPE = eINSTANCE.getEvent_Type();

		/**
		 * The meta object literal for the '<em><b>Payload</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT__PAYLOAD = eINSTANCE.getEvent_Payload();

		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelModuleTriggerImpl <em>Megamodel Module Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelModuleTriggerImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodelModuleTrigger()
		 * @generated
		 */
		EClass MEGAMODEL_MODULE_TRIGGER = eINSTANCE.getMegamodelModuleTrigger();

		/**
		 * The meta object literal for the '<em><b>Initial Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE_TRIGGER__INITIAL_OPERATION = eINSTANCE.getMegamodelModuleTrigger_InitialOperation();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE_TRIGGER__MODULE = eINSTANCE.getMegamodelModuleTrigger_Module();

		/**
		 * The meta object literal for the '{@link eurema.impl.SoftwareModuleTriggerImpl <em>Software Module Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.SoftwareModuleTriggerImpl
		 * @see eurema.impl.EuremaPackageImpl#getSoftwareModuleTrigger()
		 * @generated
		 */
		EClass SOFTWARE_MODULE_TRIGGER = eINSTANCE.getSoftwareModuleTrigger();

		/**
		 * The meta object literal for the '<em><b>Is Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_MODULE_TRIGGER__IS_NATIVE = eINSTANCE.getSoftwareModuleTrigger_IsNative();

		/**
		 * The meta object literal for the '<em><b>Execution Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_MODULE_TRIGGER__EXECUTION_METHOD = eINSTANCE.getSoftwareModuleTrigger_ExecutionMethod();

		/**
		 * The meta object literal for the '<em><b>Module</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_MODULE_TRIGGER__MODULE = eINSTANCE.getSoftwareModuleTrigger_Module();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModelResourceImpl <em>Model Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModelResourceImpl
		 * @see eurema.impl.EuremaPackageImpl#getModelResource()
		 * @generated
		 */
		EClass MODEL_RESOURCE = eINSTANCE.getModelResource();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_RESOURCE__URI = eINSTANCE.getModelResource_URI();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_RESOURCE__NAME = eINSTANCE.getModelResource_Name();

		/**
		 * The meta object literal for the '<em><b>Bound By Runtime Models</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_RESOURCE__BOUND_BY_RUNTIME_MODELS = eINSTANCE.getModelResource_BoundByRuntimeModels();

		/**
		 * The meta object literal for the '<em><b>Bound By Megamodel Modules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_RESOURCE__BOUND_BY_MEGAMODEL_MODULES = eINSTANCE.getModelResource_BoundByMegamodelModules();

		/**
		 * The meta object literal for the '{@link eurema.impl.EventTypeImpl <em>Event Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.EventTypeImpl
		 * @see eurema.impl.EuremaPackageImpl#getEventType()
		 * @generated
		 */
		EClass EVENT_TYPE = eINSTANCE.getEventType();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EVENT_TYPE__TYPE = eINSTANCE.getEventType_Type();

		/**
		 * The meta object literal for the '<em><b>Super Type</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__SUPER_TYPE = eINSTANCE.getEventType_SuperType();

		/**
		 * The meta object literal for the '<em><b>Sub Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EVENT_TYPE__SUB_TYPES = eINSTANCE.getEventType_SubTypes();

		/**
		 * The meta object literal for the '{@link eurema.impl.ArchitectureImpl <em>Architecture</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ArchitectureImpl
		 * @see eurema.impl.EuremaPackageImpl#getArchitecture()
		 * @generated
		 */
		EClass ARCHITECTURE = eINSTANCE.getArchitecture();

		/**
		 * The meta object literal for the '<em><b>Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__ENVIRONMENT = eINSTANCE.getArchitecture_Environment();

		/**
		 * The meta object literal for the '<em><b>Layers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__LAYERS = eINSTANCE.getArchitecture_Layers();

		/**
		 * The meta object literal for the '<em><b>Model Resource Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCHITECTURE__MODEL_RESOURCE_SET = eINSTANCE.getArchitecture_ModelResourceSet();

		/**
		 * The meta object literal for the '{@link eurema.impl.MegamodelModuleImpl <em>Megamodel Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.MegamodelModuleImpl
		 * @see eurema.impl.EuremaPackageImpl#getMegamodelModule()
		 * @generated
		 */
		EClass MEGAMODEL_MODULE = eINSTANCE.getMegamodelModule();

		/**
		 * The meta object literal for the '<em><b>Megamodel</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE__MEGAMODEL = eINSTANCE.getMegamodelModule_Megamodel();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE__CONTEXT = eINSTANCE.getMegamodelModule_Context();

		/**
		 * The meta object literal for the '<em><b>Trigger</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE__TRIGGER = eINSTANCE.getMegamodelModule_Trigger();

		/**
		 * The meta object literal for the '<em><b>Binding</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEGAMODEL_MODULE__BINDING = eINSTANCE.getMegamodelModule_Binding();

		/**
		 * The meta object literal for the '{@link eurema.impl.SoftwareImpl <em>Software</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.SoftwareImpl
		 * @see eurema.impl.EuremaPackageImpl#getSoftware()
		 * @generated
		 */
		EClass SOFTWARE = eINSTANCE.getSoftware();

		/**
		 * The meta object literal for the '<em><b>Uid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE__UID = eINSTANCE.getSoftware_Uid();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE__NAME = eINSTANCE.getSoftware_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE__DESCRIPTION = eINSTANCE.getSoftware_Description();

		/**
		 * The meta object literal for the '<em><b>Modules</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE__MODULES = eINSTANCE.getSoftware_Modules();

		/**
		 * The meta object literal for the '{@link eurema.impl.EntryImpl <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.EntryImpl
		 * @see eurema.impl.EuremaPackageImpl#getEntry()
		 * @generated
		 */
		EClass ENTRY = eINSTANCE.getEntry();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTRY__OPERATION = eINSTANCE.getEntry_Operation();

		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENTRY__INCOMING = eINSTANCE.getEntry_Incoming();

		/**
		 * The meta object literal for the '{@link eurema.impl.ExitImpl <em>Exit</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ExitImpl
		 * @see eurema.impl.EuremaPackageImpl#getExit()
		 * @generated
		 */
		EClass EXIT = eINSTANCE.getExit();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXIT__OPERATION = eINSTANCE.getExit_Operation();

		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXIT__OUTGOING = eINSTANCE.getExit_Outgoing();

		/**
		 * The meta object literal for the '{@link eurema.impl.RepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.RepositoryImpl
		 * @see eurema.impl.EuremaPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Software</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY__SOFTWARE = eINSTANCE.getRepository_Software();

		/**
		 * The meta object literal for the '{@link eurema.impl.ModelResourceSetImpl <em>Model Resource Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see eurema.impl.ModelResourceSetImpl
		 * @see eurema.impl.EuremaPackageImpl#getModelResourceSet()
		 * @generated
		 */
		EClass MODEL_RESOURCE_SET = eINSTANCE.getModelResourceSet();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_RESOURCE_SET__RESOURCES = eINSTANCE.getModelResourceSet_Resources();

	}

} //EuremaPackage
