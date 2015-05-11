/**
 */
package vsmappings.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import vsmappings.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class VsmappingsFactoryImpl extends EFactoryImpl implements VsmappingsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static VsmappingsFactory init() {
		try {
			VsmappingsFactory theVsmappingsFactory = (VsmappingsFactory)EPackage.Registry.INSTANCE.getEFactory(VsmappingsPackage.eNS_URI);
			if (theVsmappingsFactory != null) {
				return theVsmappingsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new VsmappingsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VsmappingsFactoryImpl() {
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
			case VsmappingsPackage.VS_MAPPING: return createVSMapping();
			case VsmappingsPackage.VS_MAPPING_REPOSITORY: return createVSMappingRepository();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VSMapping createVSMapping() {
		VSMappingImpl vsMapping = new VSMappingImpl();
		return vsMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VSMappingRepository createVSMappingRepository() {
		VSMappingRepositoryImpl vsMappingRepository = new VSMappingRepositoryImpl();
		return vsMappingRepository;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VsmappingsPackage getVsmappingsPackage() {
		return (VsmappingsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static VsmappingsPackage getPackage() {
		return VsmappingsPackage.eINSTANCE;
	}

} //VsmappingsFactoryImpl
