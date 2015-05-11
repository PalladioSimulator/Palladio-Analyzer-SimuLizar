/**
 */
package vsmappings;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see vsmappings.VsmappingsPackage
 * @generated
 */
public interface VsmappingsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	VsmappingsFactory eINSTANCE = vsmappings.impl.VsmappingsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>VS Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>VS Mapping</em>'.
	 * @generated
	 */
	VSMapping createVSMapping();

	/**
	 * Returns a new object of class '<em>VS Mapping Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>VS Mapping Repository</em>'.
	 * @generated
	 */
	VSMappingRepository createVSMappingRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	VsmappingsPackage getVsmappingsPackage();

} //VsmappingsFactory
