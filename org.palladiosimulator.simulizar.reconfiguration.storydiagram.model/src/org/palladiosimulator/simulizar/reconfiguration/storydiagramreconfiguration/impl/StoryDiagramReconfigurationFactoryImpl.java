/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class StoryDiagramReconfigurationFactoryImpl extends EFactoryImpl implements StoryDiagramReconfigurationFactory {
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static StoryDiagramReconfigurationFactory init() {
        try {
            StoryDiagramReconfigurationFactory theStoryDiagramReconfigurationFactory = (StoryDiagramReconfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(StoryDiagramReconfigurationPackage.eNS_URI);
            if (theStoryDiagramReconfigurationFactory != null) {
                return theStoryDiagramReconfigurationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new StoryDiagramReconfigurationFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public StoryDiagramReconfigurationFactoryImpl() {
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
            case StoryDiagramReconfigurationPackage.STORY_DIAGRAM_ACTIVITY: return createStoryDiagramActivity();
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
    public StoryDiagramReconfigurationPackage getStoryDiagramReconfigurationPackage() {
        return (StoryDiagramReconfigurationPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static StoryDiagramReconfigurationPackage getPackage() {
        return StoryDiagramReconfigurationPackage.eINSTANCE;
    }

} //StoryDiagramReconfigurationFactoryImpl
