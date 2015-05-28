/**
 */
package violationstrategymappings;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see violationstrategymappings.ViolationstrategymappingsPackage
 * @generated
 */
public interface ViolationstrategymappingsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ViolationstrategymappingsFactory eINSTANCE = violationstrategymappings.impl.ViolationstrategymappingsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Violation Strategy Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Violation Strategy Mapping</em>'.
	 * @generated
	 */
	ViolationStrategyMapping createViolationStrategyMapping();

	/**
	 * Returns a new object of class '<em>Violation Strategy Mapping Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Violation Strategy Mapping Repository</em>'.
	 * @generated
	 */
	ViolationStrategyMappingRepository createViolationStrategyMappingRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ViolationstrategymappingsPackage getViolationstrategymappingsPackage();

} //ViolationstrategymappingsFactory
