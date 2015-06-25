/**
 */
package org.palladiosimulator.simulizar.action.core;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Controller Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent <em>Component
 * </em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature <em>
 * Called Signature</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep
 * <em>Resource Demanding Step</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getControllerCall()
 * @model
 * @generated
 */
public interface ControllerCall extends EObject, org.palladiosimulator.pcm.core.entity.Entity {
    /**
     * Returns the value of the '<em><b>Component</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Component</em>' reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Component</em>' reference.
     * @see #setComponent(org.palladiosimulator.pcm.repository.BasicComponent)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getControllerCall_Component()
     * @model required="true"
     * @generated
     */
    org.palladiosimulator.pcm.repository.BasicComponent getComponent();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getComponent
     * <em>Component</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Component</em>' reference.
     * @see #getComponent()
     * @generated
     */
    void setComponent(org.palladiosimulator.pcm.repository.BasicComponent value);

    /**
     * Returns the value of the '<em><b>Called Signature</b></em>' reference. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Called Signature</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Called Signature</em>' reference.
     * @see #setCalledSignature(org.palladiosimulator.pcm.repository.OperationSignature)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getControllerCall_CalledSignature()
     * @model required="true"
     * @generated
     */
    org.palladiosimulator.pcm.repository.OperationSignature getCalledSignature();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getCalledSignature
     * <em>Called Signature</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Called Signature</em>' reference.
     * @see #getCalledSignature()
     * @generated
     */
    void setCalledSignature(org.palladiosimulator.pcm.repository.OperationSignature value);

    /**
     * Returns the value of the '<em><b>Resource Demanding Step</b></em>' container reference. It is
     * bidirectional and its opposite is '
     * {@link org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls
     * <em>Controller Calls</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Demanding Step</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Resource Demanding Step</em>' container reference.
     * @see #setResourceDemandingStep(ResourceDemandingStep)
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getControllerCall_ResourceDemandingStep()
     * @see org.palladiosimulator.simulizar.action.core.ResourceDemandingStep#getControllerCalls
     * @model opposite="controllerCalls" transient="false"
     * @generated
     */
    ResourceDemandingStep getResourceDemandingStep();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.ControllerCall#getResourceDemandingStep
     * <em>Resource Demanding Step</em>}' container reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Resource Demanding Step</em>' container reference.
     * @see #getResourceDemandingStep()
     * @generated
     */
    void setResourceDemandingStep(ResourceDemandingStep value);

} // ControllerCall
