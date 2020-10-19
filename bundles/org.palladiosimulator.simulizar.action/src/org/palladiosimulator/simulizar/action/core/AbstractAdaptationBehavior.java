/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Adaptation
 * Behavior</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior#getAdaptationSteps
 * <em>Adaptation Steps</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAbstractAdaptationBehavior()
 * @model abstract="true"
 * @generated
 */
public interface AbstractAdaptationBehavior extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Adaptation Steps</b></em>' containment reference list. The
     * list contents are of type {@link org.palladiosimulator.simulizar.action.core.AdaptationStep}.
     * It is bidirectional and its opposite is
     * '{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationBehavior
     * <em>Adaptation Behavior</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Adaptation Steps</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Adaptation Steps</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAbstractAdaptationBehavior_AdaptationSteps()
     * @see org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationBehavior
     * @model opposite="adaptationBehavior" containment="true" required="true"
     * @generated
     */
    EList<AdaptationStep> getAdaptationSteps();

} // AbstractAdaptationBehavior
