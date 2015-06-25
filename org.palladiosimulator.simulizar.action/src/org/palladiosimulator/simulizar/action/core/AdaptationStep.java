/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Adaptation Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getControllerCompletionURI
 * <em>Controller Completion URI</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationStepURI <em>
 * Adaptation Step URI</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getPreconditionURI <em>
 * Precondition URI</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAction <em>Action</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationStep()
 * @model abstract="true"
 * @generated
 */
public interface AdaptationStep extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
    /**
     * Returns the value of the '<em><b>Controller Completion URI</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controller Completion URI</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controller Completion URI</em>' attribute.
     * @see #setControllerCompletionURI(String)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationStep_ControllerCompletionURI()
     * @model
     * @generated
     */
    String getControllerCompletionURI();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getControllerCompletionURI
     * <em>Controller Completion URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Controller Completion URI</em>' attribute.
     * @see #getControllerCompletionURI()
     * @generated
     */
    void setControllerCompletionURI(String value);

    /**
     * Returns the value of the '<em><b>Adaptation Step URI</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Adaptation Step URI</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Adaptation Step URI</em>' attribute.
     * @see #setAdaptationStepURI(String)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationStep_AdaptationStepURI()
     * @model required="true"
     * @generated
     */
    String getAdaptationStepURI();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAdaptationStepURI
     * <em>Adaptation Step URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Adaptation Step URI</em>' attribute.
     * @see #getAdaptationStepURI()
     * @generated
     */
    void setAdaptationStepURI(String value);

    /**
     * Returns the value of the '<em><b>Precondition URI</b></em>' attribute. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Precondition URI</em>' attribute isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Precondition URI</em>' attribute.
     * @see #setPreconditionURI(String)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationStep_PreconditionURI()
     * @model
     * @generated
     */
    String getPreconditionURI();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getPreconditionURI
     * <em>Precondition URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition URI</em>' attribute.
     * @see #getPreconditionURI()
     * @generated
     */
    void setPreconditionURI(String value);

    /**
     * Returns the value of the '<em><b>Action</b></em>' container reference. It is bidirectional
     * and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.Action#getAdaptationSteps
     * <em>Adaptation Steps</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Action</em>' container reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Action</em>' container reference.
     * @see #setAction(Action)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getAdaptationStep_Action()
     * @see org.palladiosimulator.simulizar.action.core.Action#getAdaptationSteps
     * @model opposite="adaptationSteps" transient="false"
     * @generated
     */
    Action getAction();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.AdaptationStep#getAction <em>Action</em>}'
     * container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Action</em>' container reference.
     * @see #getAction()
     * @generated
     */
    void setAction(Action value);

} // AdaptationStep
