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
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAdaptationBehavior
 * <em>Adaptation Behavior</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationAction()
 * @model abstract="true"
 * @generated
 */
public interface AdaptationAction extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Adaptation Behavior</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior#getAdaptationActions
     * <em>Adaptation Actions</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Adaptation Behavior</em>' container reference isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Adaptation Behavior</em>' container reference.
     * @see #setAdaptationBehavior(AbstractAdaptationBehavior)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationAction_AdaptationBehavior()
     * @see org.palladiosimulator.simulizar.action.core.AbstractAdaptationBehavior#getAdaptationActions
     * @model opposite="adaptationActions" transient="false"
     * @generated
     */
    AbstractAdaptationBehavior getAdaptationBehavior();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationAction#getAdaptationBehavior
     * <em>Adaptation Behavior</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Adaptation Behavior</em>' container reference.
     * @see #getAdaptationBehavior()
     * @generated
     */
    void setAdaptationBehavior(AbstractAdaptationBehavior value);

} // AdaptationAction
