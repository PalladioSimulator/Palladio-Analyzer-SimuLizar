/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage
 * @generated
 */
public interface MonitorrepositoryFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    MonitorrepositoryFactory eINSTANCE = org.palladiosimulator.simulizar.monitorrepository.impl.MonitorrepositoryFactoryImpl
            .init();

    /**
     * Returns a new object of class '<em>Monitor Repository</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Monitor Repository</em>'.
     * @generated
     */
    MonitorRepository createMonitorRepository();

    /**
     * Returns a new object of class '<em>Monitor</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Monitor</em>'.
     * @generated
     */
    Monitor createMonitor();

    /**
     * Returns a new object of class '<em>Measurement Specification</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Measurement Specification</em>'.
     * @generated
     */
    MeasurementSpecification createMeasurementSpecification();

    /**
     * Returns a new object of class '<em>Intervall</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Intervall</em>'.
     * @generated
     */
    Intervall createIntervall();

    /**
     * Returns a new object of class '<em>Delayed Intervall</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Delayed Intervall</em>'.
     * @generated
     */
    DelayedIntervall createDelayedIntervall();

    /**
     * Returns a new object of class '<em>Time Frame</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Time Frame</em>'.
     * @generated
     */
    TimeFrame createTimeFrame();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    MonitorrepositoryPackage getMonitorrepositoryPackage();

} // MonitorrepositoryFactory
