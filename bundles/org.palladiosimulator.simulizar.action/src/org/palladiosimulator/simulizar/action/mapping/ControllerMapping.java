/**
 */
package org.palladiosimulator.simulizar.action.mapping;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.repository.OperationProvidedRole;
import org.palladiosimulator.simulizar.action.core.ControllerCall;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Controller
 * Mapping</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMappedCall
 * <em>Mapped Call</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getControllerRole
 * <em>Controller Role</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping
 * <em>Mapping</em>}</li>
 * </ul>
 *
 * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getControllerMapping()
 * @model
 * @generated
 */
public interface ControllerMapping extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Mapped Call</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapped Call</em>' reference isn't clear, there really should be
     * more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapped Call</em>' reference.
     * @see #setMappedCall(ControllerCall)
     * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getControllerMapping_MappedCall()
     * @model required="true"
     * @generated
     */
    ControllerCall getMappedCall();

    /**
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMappedCall
     * <em>Mapped Call</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapped Call</em>' reference.
     * @see #getMappedCall()
     * @generated
     */
    void setMappedCall(ControllerCall value);

    /**
     * Returns the value of the '<em><b>Controller Role</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controller Role</em>' reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controller Role</em>' reference.
     * @see #setControllerRole(OperationProvidedRole)
     * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getControllerMapping_ControllerRole()
     * @model required="true"
     * @generated
     */
    OperationProvidedRole getControllerRole();

    /**
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getControllerRole
     * <em>Controller Role</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Controller Role</em>' reference.
     * @see #getControllerRole()
     * @generated
     */
    void setControllerRole(OperationProvidedRole value);

    /**
     * Returns the value of the '<em><b>Mapping</b></em>' container reference. It is bidirectional
     * and its opposite is
     * '{@link org.palladiosimulator.simulizar.action.mapping.Mapping#getControllerMappings
     * <em>Controller Mappings</em>}'. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping</em>' container reference isn't clear, there really should
     * be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping</em>' container reference.
     * @see #setMapping(Mapping)
     * @see org.palladiosimulator.simulizar.action.mapping.MappingPackage#getControllerMapping_Mapping()
     * @see org.palladiosimulator.simulizar.action.mapping.Mapping#getControllerMappings
     * @model opposite="controllerMappings" transient="false"
     * @generated
     */
    Mapping getMapping();

    /**
     * Sets the value of the
     * '{@link org.palladiosimulator.simulizar.action.mapping.ControllerMapping#getMapping
     * <em>Mapping</em>}' container reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapping</em>' container reference.
     * @see #getMapping()
     * @generated
     */
    void setMapping(Mapping value);

} // ControllerMapping
