/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.prm;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each
 * non-abstract class of the model. <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.prm.PrmPackage
 * @generated
 */
public interface PrmFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PrmFactory eINSTANCE = org.palladiosimulator.simulizar.prm.impl.PrmFactoryImpl.init();

    /**
     * Returns a new object of class '<em>PRM Model</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>PRM Model</em>'.
     * @generated
     */
    PRMModel createPRMModel();

    /**
     * Returns a new object of class '<em>PCM Model Element Measurement</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>PCM Model Element Measurement</em>'.
     * @generated
     */
    PCMModelElementMeasurement createPCMModelElementMeasurement();

    /**
     * Returns a new object of class '<em>Unique Element</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Unique Element</em>'.
     * @generated
     */
    UniqueElement createUniqueElement();

    /**
     * Returns a new object of class '<em>Resource Container Measurement</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Resource Container Measurement</em>'.
     * @generated
     */
    ResourceContainerMeasurement createResourceContainerMeasurement();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    PrmPackage getPrmPackage();

} // PrmFactory
