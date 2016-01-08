/**
 */
package org.palladiosimulator.simulizar.action.parameter;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.palladiosimulator.simulizar.action.parameter.ParameterPackage
 * @generated
 */
public interface ParameterFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    ParameterFactory eINSTANCE = org.palladiosimulator.simulizar.action.parameter.impl.ParameterFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Controller Call Input Variable Usage</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Controller Call Input Variable Usage</em>'.
     * @generated
     */
    ControllerCallInputVariableUsage createControllerCallInputVariableUsage();

    /**
     * Returns a new object of class '<em>Controller Call Input Variable Usage Collection</em>'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Controller Call Input Variable Usage Collection</em>'.
     * @generated
     */
    ControllerCallInputVariableUsageCollection createControllerCallInputVariableUsageCollection();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ParameterPackage getParameterPackage();

} // ParameterFactory
