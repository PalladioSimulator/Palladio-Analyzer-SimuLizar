/**
 */
package org.palladiosimulator.simulizar.action.core;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Guarded Adaptation Behavior</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior#getPreconditionURI
 * <em>Precondition URI</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedAdaptationBehavior()
 * @model
 * @generated
 */
public interface GuardedAdaptationBehavior extends AdaptationAction {
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
     * @see org.palladiosimulator.simulizar.action.core.CorePackage#getGuardedAdaptationBehavior_PreconditionURI()
     * @model
     * @generated
     */
    String getPreconditionURI();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.action.core.GuardedAdaptationBehavior#getPreconditionURI
     * <em>Precondition URI</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Precondition URI</em>' attribute.
     * @see #getPreconditionURI()
     * @generated
     */
    void setPreconditionURI(String value);

} // GuardedAdaptationBehavior
