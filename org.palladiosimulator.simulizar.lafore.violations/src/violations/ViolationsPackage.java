/**
 */
package violations;

import de.uka.ipd.sdq.pcm.core.entity.EntityPackage;

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
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see violations.ViolationsFactory
 * @model kind="package"
 * @generated
 */
public interface ViolationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "violations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://simulizar.palladiosimulator.org/lafore/violations/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "violations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViolationsPackage eINSTANCE = violations.impl.ViolationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link violations.impl.ViolationTypeImpl <em>Violation Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.ViolationTypeImpl
	 * @see violations.impl.ViolationsPackageImpl#getViolationType()
	 * @generated
	 */
	int VIOLATION_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_TYPE__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_TYPE__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_TYPE__DESCRIPTION = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Slo</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_TYPE__SLO = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Violation Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_TYPE_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link violations.impl.ViolationImpl <em>Violation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.ViolationImpl
	 * @see violations.impl.ViolationsPackageImpl#getViolation()
	 * @generated
	 */
	int VIOLATION = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION__VIOLATION_TYPE = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATION_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link violations.impl.ViolationsRepositoryImpl <em>Repository</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.ViolationsRepositoryImpl
	 * @see violations.impl.ViolationsPackageImpl#getViolationsRepository()
	 * @generated
	 */
	int VIOLATIONS_REPOSITORY = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATIONS_REPOSITORY__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATIONS_REPOSITORY__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation Types</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATIONS_REPOSITORY__VIOLATION_TYPES = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VIOLATIONS_REPOSITORY_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link violations.impl.RuntimeViolationsModelImpl <em>Runtime Violations Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.RuntimeViolationsModelImpl
	 * @see violations.impl.ViolationsPackageImpl#getRuntimeViolationsModel()
	 * @generated
	 */
	int RUNTIME_VIOLATIONS_MODEL = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_VIOLATIONS_MODEL__ID = EntityPackage.ENTITY__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_VIOLATIONS_MODEL__ENTITY_NAME = EntityPackage.ENTITY__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_VIOLATIONS_MODEL__VIOLATIONS = EntityPackage.ENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Runtime Violations Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUNTIME_VIOLATIONS_MODEL_FEATURE_COUNT = EntityPackage.ENTITY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link violations.impl.QuantifiableViolationImpl <em>Quantifiable Violation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.QuantifiableViolationImpl
	 * @see violations.impl.ViolationsPackageImpl#getQuantifiableViolation()
	 * @generated
	 */
	int QUANTIFIABLE_VIOLATION = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_VIOLATION__ID = VIOLATION__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_VIOLATION__ENTITY_NAME = VIOLATION__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_VIOLATION__VIOLATION_TYPE = VIOLATION__VIOLATION_TYPE;

	/**
	 * The feature id for the '<em><b>Percentage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_VIOLATION__PERCENTAGE = VIOLATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Quantifiable Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QUANTIFIABLE_VIOLATION_FEATURE_COUNT = VIOLATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link violations.impl.NonQuantifiableViolationImpl <em>Non Quantifiable Violation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see violations.impl.NonQuantifiableViolationImpl
	 * @see violations.impl.ViolationsPackageImpl#getNonQuantifiableViolation()
	 * @generated
	 */
	int NON_QUANTIFIABLE_VIOLATION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_QUANTIFIABLE_VIOLATION__ID = VIOLATION__ID;

	/**
	 * The feature id for the '<em><b>Entity Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_QUANTIFIABLE_VIOLATION__ENTITY_NAME = VIOLATION__ENTITY_NAME;

	/**
	 * The feature id for the '<em><b>Violation Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_QUANTIFIABLE_VIOLATION__VIOLATION_TYPE = VIOLATION__VIOLATION_TYPE;

	/**
	 * The number of structural features of the '<em>Non Quantifiable Violation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NON_QUANTIFIABLE_VIOLATION_FEATURE_COUNT = VIOLATION_FEATURE_COUNT + 0;

	/**
	 * Returns the meta object for class '{@link violations.ViolationType <em>Violation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Violation Type</em>'.
	 * @see violations.ViolationType
	 * @generated
	 */
	EClass getViolationType();

	/**
	 * Returns the meta object for the attribute '{@link violations.ViolationType#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see violations.ViolationType#getDescription()
	 * @see #getViolationType()
	 * @generated
	 */
	EAttribute getViolationType_Description();

	/**
	 * Returns the meta object for the reference '{@link violations.ViolationType#getSlo <em>Slo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Slo</em>'.
	 * @see violations.ViolationType#getSlo()
	 * @see #getViolationType()
	 * @generated
	 */
	EReference getViolationType_Slo();

	/**
	 * Returns the meta object for class '{@link violations.Violation <em>Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Violation</em>'.
	 * @see violations.Violation
	 * @generated
	 */
	EClass getViolation();

	/**
	 * Returns the meta object for the reference '{@link violations.Violation#getViolationType <em>Violation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Violation Type</em>'.
	 * @see violations.Violation#getViolationType()
	 * @see #getViolation()
	 * @generated
	 */
	EReference getViolation_ViolationType();

	/**
	 * Returns the meta object for class '{@link violations.ViolationsRepository <em>Repository</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see violations.ViolationsRepository
	 * @generated
	 */
	EClass getViolationsRepository();

	/**
	 * Returns the meta object for the containment reference list '{@link violations.ViolationsRepository#getViolationTypes <em>Violation Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Violation Types</em>'.
	 * @see violations.ViolationsRepository#getViolationTypes()
	 * @see #getViolationsRepository()
	 * @generated
	 */
	EReference getViolationsRepository_ViolationTypes();

	/**
	 * Returns the meta object for class '{@link violations.RuntimeViolationsModel <em>Runtime Violations Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Runtime Violations Model</em>'.
	 * @see violations.RuntimeViolationsModel
	 * @generated
	 */
	EClass getRuntimeViolationsModel();

	/**
	 * Returns the meta object for the containment reference list '{@link violations.RuntimeViolationsModel#getViolations <em>Violations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Violations</em>'.
	 * @see violations.RuntimeViolationsModel#getViolations()
	 * @see #getRuntimeViolationsModel()
	 * @generated
	 */
	EReference getRuntimeViolationsModel_Violations();

	/**
	 * Returns the meta object for class '{@link violations.QuantifiableViolation <em>Quantifiable Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Quantifiable Violation</em>'.
	 * @see violations.QuantifiableViolation
	 * @generated
	 */
	EClass getQuantifiableViolation();

	/**
	 * Returns the meta object for the attribute '{@link violations.QuantifiableViolation#getPercentage <em>Percentage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Percentage</em>'.
	 * @see violations.QuantifiableViolation#getPercentage()
	 * @see #getQuantifiableViolation()
	 * @generated
	 */
	EAttribute getQuantifiableViolation_Percentage();

	/**
	 * Returns the meta object for class '{@link violations.NonQuantifiableViolation <em>Non Quantifiable Violation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Non Quantifiable Violation</em>'.
	 * @see violations.NonQuantifiableViolation
	 * @generated
	 */
	EClass getNonQuantifiableViolation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ViolationsFactory getViolationsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link violations.impl.ViolationTypeImpl <em>Violation Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.ViolationTypeImpl
		 * @see violations.impl.ViolationsPackageImpl#getViolationType()
		 * @generated
		 */
		EClass VIOLATION_TYPE = eINSTANCE.getViolationType();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VIOLATION_TYPE__DESCRIPTION = eINSTANCE.getViolationType_Description();

		/**
		 * The meta object literal for the '<em><b>Slo</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION_TYPE__SLO = eINSTANCE.getViolationType_Slo();

		/**
		 * The meta object literal for the '{@link violations.impl.ViolationImpl <em>Violation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.ViolationImpl
		 * @see violations.impl.ViolationsPackageImpl#getViolation()
		 * @generated
		 */
		EClass VIOLATION = eINSTANCE.getViolation();

		/**
		 * The meta object literal for the '<em><b>Violation Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATION__VIOLATION_TYPE = eINSTANCE.getViolation_ViolationType();

		/**
		 * The meta object literal for the '{@link violations.impl.ViolationsRepositoryImpl <em>Repository</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.ViolationsRepositoryImpl
		 * @see violations.impl.ViolationsPackageImpl#getViolationsRepository()
		 * @generated
		 */
		EClass VIOLATIONS_REPOSITORY = eINSTANCE.getViolationsRepository();

		/**
		 * The meta object literal for the '<em><b>Violation Types</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VIOLATIONS_REPOSITORY__VIOLATION_TYPES = eINSTANCE.getViolationsRepository_ViolationTypes();

		/**
		 * The meta object literal for the '{@link violations.impl.RuntimeViolationsModelImpl <em>Runtime Violations Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.RuntimeViolationsModelImpl
		 * @see violations.impl.ViolationsPackageImpl#getRuntimeViolationsModel()
		 * @generated
		 */
		EClass RUNTIME_VIOLATIONS_MODEL = eINSTANCE.getRuntimeViolationsModel();

		/**
		 * The meta object literal for the '<em><b>Violations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUNTIME_VIOLATIONS_MODEL__VIOLATIONS = eINSTANCE.getRuntimeViolationsModel_Violations();

		/**
		 * The meta object literal for the '{@link violations.impl.QuantifiableViolationImpl <em>Quantifiable Violation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.QuantifiableViolationImpl
		 * @see violations.impl.ViolationsPackageImpl#getQuantifiableViolation()
		 * @generated
		 */
		EClass QUANTIFIABLE_VIOLATION = eINSTANCE.getQuantifiableViolation();

		/**
		 * The meta object literal for the '<em><b>Percentage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QUANTIFIABLE_VIOLATION__PERCENTAGE = eINSTANCE.getQuantifiableViolation_Percentage();

		/**
		 * The meta object literal for the '{@link violations.impl.NonQuantifiableViolationImpl <em>Non Quantifiable Violation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see violations.impl.NonQuantifiableViolationImpl
		 * @see violations.impl.ViolationsPackageImpl#getNonQuantifiableViolation()
		 * @generated
		 */
		EClass NON_QUANTIFIABLE_VIOLATION = eINSTANCE.getNonQuantifiableViolation();

	}

} //ViolationsPackage
