/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Unique Element</b></em>'.
 * <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> Superclass of all classes to store unique identifier. <!-- end-model-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.UniqueElement#getGuid <em>Guid</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getUniqueElement()
 * @model
 * @generated
 */
public interface UniqueElement extends EObject {
    /**
     * Returns the value of the '<em><b>Guid</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Guid</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Guid</em>' attribute.
     * @see #setGuid(String)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getUniqueElement_Guid()
     * @model
     * @generated
     */
    String getGuid();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.pms.UniqueElement#getGuid
     * <em>Guid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Guid</em>' attribute.
     * @see #getGuid()
     * @generated
     */
    void setGuid(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @model annotation=
     *        "http://www.eclipse.org/emf/2002/GenModel body='if(this.getGuid() == null) this.setGuid(org.eclipse.emf.ecore.util.EcoreUtil.generateUUID());'"
     * @generated
     */
    void createAndSetGuid();

} // UniqueElement
