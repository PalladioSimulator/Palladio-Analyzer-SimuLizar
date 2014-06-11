/**
 */
package org.palladiosimulator.servicelevelobjective;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage
 * @generated
 */
public interface ServicelevelObjectiveFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ServicelevelObjectiveFactory eINSTANCE = org.palladiosimulator.servicelevelobjective.impl.ServicelevelObjectiveFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Service Level Objective Repository</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Service Level Objective Repository</em>'.
     * @generated
     */
    ServiceLevelObjectiveRepository createServiceLevelObjectiveRepository();

    /**
     * Returns a new object of class '<em>Service Level Objective</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Service Level Objective</em>'.
     * @generated
     */
    ServiceLevelObjective createServiceLevelObjective();

    /**
     * Returns a new object of class '<em>Hard Threshold</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Hard Threshold</em>'.
     * @generated
     */
    HardThreshold createHardThreshold();

    /**
     * Returns a new object of class '<em>Linear Fuzzy Threshold</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Linear Fuzzy Threshold</em>'.
     * @generated
     */
    LinearFuzzyThreshold createLinearFuzzyThreshold();

    /**
     * Returns a new object of class '<em>Named Element</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Named Element</em>'.
     * @generated
     */
    NamedElement createNamedElement();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ServicelevelObjectivePackage getServicelevelObjectivePackage();

} //ServicelevelObjectiveFactory
