/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.upb.pcm.pms;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Intervall</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Simple intervall.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.upb.pcm.pms.Intervall#getIntervall <em>Intervall</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.upb.pcm.pms.PmsPackage#getIntervall()
 * @model
 * @generated
 */
public interface Intervall extends TemporalCharacterization
{
   /**
    * Returns the value of the '<em><b>Intervall</b></em>' attribute.
    * The default value is <code>"0.0"</code>.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Intervall</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Intervall</em>' attribute.
    * @see #setIntervall(double)
    * @see de.upb.pcm.pms.PmsPackage#getIntervall_Intervall()
    * @model default="0.0" required="true"
    * @generated
    */
   double getIntervall();

   /**
    * Sets the value of the '{@link de.upb.pcm.pms.Intervall#getIntervall <em>Intervall</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Intervall</em>' attribute.
    * @see #getIntervall()
    * @generated
    */
   void setIntervall(double value);

} // Intervall
