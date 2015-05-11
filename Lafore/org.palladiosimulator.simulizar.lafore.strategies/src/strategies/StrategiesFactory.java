/**
 */
package strategies;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see strategies.StrategiesPackage
 * @generated
 */
public interface StrategiesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StrategiesFactory eINSTANCE = strategies.impl.StrategiesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Runtime Strategies Model</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Runtime Strategies Model</em>'.
	 * @generated
	 */
	RuntimeStrategiesModel createRuntimeStrategiesModel();

	/**
	 * Returns a new object of class '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strategy</em>'.
	 * @generated
	 */
	Strategy createStrategy();

	/**
	 * Returns a new object of class '<em>Strategy Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strategy Type</em>'.
	 * @generated
	 */
	StrategyType createStrategyType();

	/**
	 * Returns a new object of class '<em>Reconfiguration Action</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reconfiguration Action</em>'.
	 * @generated
	 */
	ReconfigurationAction createReconfigurationAction();

	/**
	 * Returns a new object of class '<em>Repository</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Repository</em>'.
	 * @generated
	 */
	StrategiesRepository createStrategiesRepository();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StrategiesPackage getStrategiesPackage();

} //StrategiesFactory
