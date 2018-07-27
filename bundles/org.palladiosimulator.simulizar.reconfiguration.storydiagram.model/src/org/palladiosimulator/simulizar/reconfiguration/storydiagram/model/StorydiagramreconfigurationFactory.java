/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagram.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.StorydiagramreconfigurationPackage
 * @generated
 */
public interface StorydiagramreconfigurationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	StorydiagramreconfigurationFactory eINSTANCE = org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl.StorydiagramreconfigurationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Story Diagram Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Story Diagram Activity</em>'.
	 * @generated
	 */
	StoryDiagramActivity createStoryDiagramActivity();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	StorydiagramreconfigurationPackage getStorydiagramreconfigurationPackage();

} //StorydiagramreconfigurationFactory
