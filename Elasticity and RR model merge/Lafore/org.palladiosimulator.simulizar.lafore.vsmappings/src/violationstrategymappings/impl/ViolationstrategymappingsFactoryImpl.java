/**
 */
package violationstrategymappings.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import violationstrategymappings.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ViolationstrategymappingsFactoryImpl extends EFactoryImpl implements ViolationstrategymappingsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ViolationstrategymappingsFactory init() {
		try {
			ViolationstrategymappingsFactory theViolationstrategymappingsFactory = (ViolationstrategymappingsFactory)EPackage.Registry.INSTANCE.getEFactory(ViolationstrategymappingsPackage.eNS_URI);
			if (theViolationstrategymappingsFactory != null) {
				return theViolationstrategymappingsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ViolationstrategymappingsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationstrategymappingsFactoryImpl() {
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
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING: return createViolationStrategyMapping();
			case ViolationstrategymappingsPackage.VIOLATION_STRATEGY_MAPPING_REPOSITORY: return createViolationStrategyMappingRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationStrategyMapping createViolationStrategyMapping() {
		ViolationStrategyMappingImpl violationStrategyMapping = new ViolationStrategyMappingImpl();
		return violationStrategyMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationStrategyMappingRepository createViolationStrategyMappingRepository() {
		ViolationStrategyMappingRepositoryImpl violationStrategyMappingRepository = new ViolationStrategyMappingRepositoryImpl();
		return violationStrategyMappingRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ViolationstrategymappingsPackage getViolationstrategymappingsPackage() {
		return (ViolationstrategymappingsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ViolationstrategymappingsPackage getPackage() {
		return ViolationstrategymappingsPackage.eINSTANCE;
	}

} //ViolationstrategymappingsFactoryImpl
