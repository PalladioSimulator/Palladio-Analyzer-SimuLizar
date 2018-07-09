/**
 */
package org.palladiosimulator.simulizar.action.mapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage
 * @generated
 */
public interface MappingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	MappingFactory eINSTANCE = org.palladiosimulator.simulizar.action.mapping.impl.MappingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Mapping</em>'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 *
	 * @return a new object of class '<em>Mapping</em>'.
	 * @generated
	 */
	Mapping createMapping();

	/**
	 * Returns a new object of class '<em>Controller Mapping</em>'.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @return a new object of class '<em>Controller Mapping</em>'.
	 * @generated
	 */
	ControllerMapping createControllerMapping();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	MappingPackage getMappingPackage();

} // MappingFactory
