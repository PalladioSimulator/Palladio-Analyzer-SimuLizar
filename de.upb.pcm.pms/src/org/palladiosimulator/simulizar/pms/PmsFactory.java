/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.pms.PmsPackage
 * @generated
 */
public interface PmsFactory extends EFactory
{
   /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @generated
     */
   PmsFactory eINSTANCE = org.palladiosimulator.simulizar.pms.impl.PmsFactoryImpl.init();

   /**
     * Returns a new object of class '<em>PMS Model</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>PMS Model</em>'.
     * @generated
     */
   PMSModel createPMSModel();

   /**
     * Returns a new object of class '<em>Performance Measurement</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Performance Measurement</em>'.
     * @generated
     */
   PerformanceMeasurement createPerformanceMeasurement();

   /**
     * Returns a new object of class '<em>Measurement Specification</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Measurement Specification</em>'.
     * @generated
     */
   MeasurementSpecification createMeasurementSpecification();

   /**
     * Returns a new object of class '<em>Intervall</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Intervall</em>'.
     * @generated
     */
   Intervall createIntervall();

   /**
     * Returns a new object of class '<em>Delayed Intervall</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Delayed Intervall</em>'.
     * @generated
     */
   DelayedIntervall createDelayedIntervall();

   /**
     * Returns a new object of class '<em>Time Frame</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Time Frame</em>'.
     * @generated
     */
   TimeFrame createTimeFrame();

   /**
     * Returns a new object of class '<em>Unique Element</em>'.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return a new object of class '<em>Unique Element</em>'.
     * @generated
     */
   UniqueElement createUniqueElement();

   /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
   PmsPackage getPmsPackage();

} //PmsFactory
