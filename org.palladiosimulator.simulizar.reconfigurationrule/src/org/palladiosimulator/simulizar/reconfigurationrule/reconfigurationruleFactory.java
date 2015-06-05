/**
 */
package org.palladiosimulator.simulizar.reconfigurationrule;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfigurationrule.reconfigurationrulePackage
 * @generated
 */
public interface reconfigurationruleFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    reconfigurationruleFactory eINSTANCE = org.palladiosimulator.simulizar.reconfigurationrule.impl.reconfigurationruleFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Reconfiguration Rule Set</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Reconfiguration Rule Set</em>'.
     * @generated
     */
    ReconfigurationRuleSet createReconfigurationRuleSet();

    /**
     * Returns a new object of class '<em>Reconfiguration Rule</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Reconfiguration Rule</em>'.
     * @generated
     */
    ReconfigurationRule createReconfigurationRule();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    reconfigurationrulePackage getreconfigurationrulePackage();

} //reconfigurationruleFactory
