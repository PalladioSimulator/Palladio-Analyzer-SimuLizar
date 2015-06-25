/**
 */
package org.palladiosimulator.simulizar.action.instance;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.action.instance.InstancePackage
 * @generated
 */
public interface InstanceFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    InstanceFactory eINSTANCE = org.palladiosimulator.simulizar.action.instance.impl.InstanceFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Role Set</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Role Set</em>'.
     * @generated
     */
    RoleSet createRoleSet();

    /**
     * Returns a new object of class '<em>Role</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Role</em>'.
     * @generated
     */
    Role createRole();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    InstancePackage getInstancePackage();

} // InstanceFactory
