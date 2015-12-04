/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Adaptation Action</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAction <em>Action</em>
 * }</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationAction()
 * @model abstract="true"
 * @generated
 */
public interface AdaptationAction extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Action</b></em>' container reference. It is bidirectional
     * and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getAdaptationSteps
     * <em>Adaptation Steps</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Action</em>' container reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Action</em>' container reference.
     * @see #setAction(AdaptationBehavior)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationAction_Action()
     * @see org.palladiosimulator.simulizar.action.core.AdaptationBehavior#getAdaptationSteps
     * @model opposite="adaptationSteps" transient="false"
     * @generated
     */
    AdaptationBehavior getAction();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAction <em>Action</em>
     * }' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Action</em>' container reference.
     * @see #getAction()
     * @generated
     */
    void setAction(AdaptationBehavior value);

} // AdaptationAction
