/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.palladiosimulator.simulizar.pms;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Delayed Intervall</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Simple intervall starting after a specified delay. <!-- end-model-doc
 * -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.DelayedIntervall#getDelay <em>Delay</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getDelayedIntervall()
 * @model
 * @generated
 */
public interface DelayedIntervall extends Intervall {
    /**
     * Returns the value of the '<em><b>Delay</b></em>' attribute. The default value is
     * <code>"0.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Delay</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Delay</em>' attribute.
     * @see #setDelay(double)
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getDelayedIntervall_Delay()
     * @model default="0.0" required="true"
     * @generated
     */
    double getDelay();

    /**
     * Sets the value of the '{@link org.palladiosimulator.simulizar.pms.DelayedIntervall#getDelay
     * <em>Delay</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Delay</em>' attribute.
     * @see #getDelay()
     * @generated
     */
    void setDelay(double value);

} // DelayedIntervall
