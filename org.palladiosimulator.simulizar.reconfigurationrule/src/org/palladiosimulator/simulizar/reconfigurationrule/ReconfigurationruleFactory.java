/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfigurationrule.ReconfigurationrulePackage
 * @generated
 */
public interface ReconfigurationruleFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ReconfigurationruleFactory eINSTANCE = org.palladiosimulator.simulizar.reconfigurationrule.impl.ReconfigurationruleFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Reconfiguration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reconfiguration</em>'.
	 * @generated
	 */
	Reconfiguration createReconfiguration();

	/**
	 * Returns a new object of class '<em>Tactic</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tactic</em>'.
	 * @generated
	 */
	Tactic createTactic();

	/**
	 * Returns a new object of class '<em>Strategy</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strategy</em>'.
	 * @generated
	 */
	Strategy createStrategy();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ReconfigurationrulePackage getReconfigurationrulePackage();

} //ReconfigurationruleFactory
