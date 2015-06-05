/**
 */
package org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.impl;

import org.eclipse.emf.ecore.EClass;

import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramActivity;
import org.palladiosimulator.simulizar.reconfiguration.storydiagramreconfiguration.StoryDiagramReconfigurationPackage;

import org.palladiosimulator.simulizar.reconfigurationrule.impl.ModelTransformationImpl;

import org.storydriven.storydiagrams.activities.Activity;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Story Diagram Activity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class StoryDiagramActivityImpl extends ModelTransformationImpl<Activity> implements StoryDiagramActivity {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected StoryDiagramActivityImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return StoryDiagramReconfigurationPackage.Literals.STORY_DIAGRAM_ACTIVITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * This is specialized for the more specific type known in this context.
     * @generated
     */
    @Override
    public void setModelTransformation(Activity newModelTransformation) {
        super.setModelTransformation(newModelTransformation);
    }

} //StoryDiagramActivityImpl
