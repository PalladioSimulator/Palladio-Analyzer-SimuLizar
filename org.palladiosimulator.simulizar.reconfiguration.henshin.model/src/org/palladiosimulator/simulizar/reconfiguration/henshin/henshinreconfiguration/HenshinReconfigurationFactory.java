/**
 */
package org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.HenshinReconfigurationPackage
 * @generated
 */
public interface HenshinReconfigurationFactory extends EFactory {
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    HenshinReconfigurationFactory eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.henshin.henshinreconfiguration.impl.HenshinReconfigurationFactoryImpl.init();

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
    HenshinReconfigurationPackage getHenshinReconfigurationPackage();

} //HenshinReconfigurationFactory
