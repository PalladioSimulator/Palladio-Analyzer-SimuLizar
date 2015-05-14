/**
 */
package violations.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import violations.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViolationsFactoryImpl extends EFactoryImpl implements ViolationsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViolationsFactory init() {
		try {
			ViolationsFactory theViolationsFactory = (ViolationsFactory)EPackage.Registry.INSTANCE.getEFactory(ViolationsPackage.eNS_URI);
			if (theViolationsFactory != null) {
				return theViolationsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ViolationsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationsFactoryImpl() {
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
			case ViolationsPackage.VIOLATION_TYPE: return createViolationType();
			case ViolationsPackage.VIOLATION: return createViolation();
			case ViolationsPackage.VIOLATIONS_REPOSITORY: return createViolationsRepository();
			case ViolationsPackage.RUNTIME_VIOLATIONS_MODEL: return createRuntimeViolationsModel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationType createViolationType() {
		ViolationTypeImpl violationType = new ViolationTypeImpl();
		return violationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Violation createViolation() {
		ViolationImpl violation = new ViolationImpl();
		return violation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationsRepository createViolationsRepository() {
		ViolationsRepositoryImpl violationsRepository = new ViolationsRepositoryImpl();
		return violationsRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RuntimeViolationsModel createRuntimeViolationsModel() {
		RuntimeViolationsModelImpl runtimeViolationsModel = new RuntimeViolationsModelImpl();
		return runtimeViolationsModel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationsPackage getViolationsPackage() {
		return (ViolationsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ViolationsPackage getPackage() {
		return ViolationsPackage.eINSTANCE;
	}

} //ViolationsFactoryImpl
