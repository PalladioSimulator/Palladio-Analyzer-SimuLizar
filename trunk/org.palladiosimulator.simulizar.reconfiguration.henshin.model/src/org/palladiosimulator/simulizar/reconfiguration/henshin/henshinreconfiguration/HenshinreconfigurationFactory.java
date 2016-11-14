/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinreconfigurationPackage
 * @generated
 */
public interface HenshinreconfigurationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	HenshinreconfigurationFactory eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinreconfigurationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Henshin Transformation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Henshin Transformation</em>'.
	 * @generated
	 */
	HenshinTransformation createHenshinTransformation();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	HenshinreconfigurationPackage getHenshinreconfigurationPackage();

} //HenshinreconfigurationFactory
