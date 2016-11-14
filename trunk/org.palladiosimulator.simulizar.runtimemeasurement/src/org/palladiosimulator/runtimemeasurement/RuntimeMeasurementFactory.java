/**
 */
package org.palladiosimulator.runtimemeasurement;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.runtimemeasurement.RuntimeMeasurementPackage
 * @generated
 */
public interface RuntimeMeasurementFactory extends EFactory {

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    RuntimeMeasurementFactory eINSTANCE = org.palladiosimulator.runtimemeasurement.impl.RuntimeMeasurementFactoryImpl
            .init();

    /**
     * Returns a new object of class '<em>Model</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Model</em>'.
     * @generated
     */
    RuntimeMeasurementModel createRuntimeMeasurementModel();

    /**
     * Returns a new object of class '<em>Runtime Measurement</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Runtime Measurement</em>'.
     * @generated
     */
    RuntimeMeasurement createRuntimeMeasurement();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    RuntimeMeasurementPackage getRuntimeMeasurementPackage();

} // RuntimeMeasurementFactory
