/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.palladiosimulator.simulizar.reconfiguration.storydiagram.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StorydiagramreconfigurationFactoryImpl extends EFactoryImpl implements StorydiagramreconfigurationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static StorydiagramreconfigurationFactory init() {
		try {
			StorydiagramreconfigurationFactory theStorydiagramreconfigurationFactory = (StorydiagramreconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(StorydiagramreconfigurationPackage.eNS_URI);
			if (theStorydiagramreconfigurationFactory != null) {
				return theStorydiagramreconfigurationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new StorydiagramreconfigurationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StorydiagramreconfigurationFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case StorydiagramreconfigurationPackage.STORY_DIAGRAM_ACTIVITY: return createStoryDiagramActivity();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StoryDiagramActivity createStoryDiagramActivity() {
		StoryDiagramActivityImpl storyDiagramActivity = new StoryDiagramActivityImpl();
		return storyDiagramActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StorydiagramreconfigurationPackage getStorydiagramreconfigurationPackage() {
		return (StorydiagramreconfigurationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static StorydiagramreconfigurationPackage getPackage() {
		return StorydiagramreconfigurationPackage.eINSTANCE;
	}

} //StorydiagramreconfigurationFactoryImpl
