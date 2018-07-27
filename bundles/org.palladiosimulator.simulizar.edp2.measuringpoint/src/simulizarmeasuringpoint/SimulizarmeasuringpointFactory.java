/**
 */
package simulizarmeasuringpoint;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see simulizarmeasuringpoint.SimulizarmeasuringpointPackage
 * @generated
 */
public interface SimulizarmeasuringpointFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    SimulizarmeasuringpointFactory eINSTANCE = simulizarmeasuringpoint.impl.SimulizarmeasuringpointFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Reconfiguration Measuring Point</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Reconfiguration Measuring Point</em>'.
     * @generated
     */
    ReconfigurationMeasuringPoint createReconfigurationMeasuringPoint();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    SimulizarmeasuringpointPackage getSimulizarmeasuringpointPackage();

} // SimulizarmeasuringpointFactory
