/**
 */
package org.palladiosimulator.simulizar.monitorrepository;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Time Frame</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> Interval representing a strict time intervall with start and end times.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStart <em>Start</em>}</li>
 * <li>{@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStop <em>Stop</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getTimeFrame()
 * @model
 * @generated
 */
public interface TimeFrame extends TemporalCharacterization {
    /**
     * Returns the value of the '<em><b>Start</b></em>' attribute. The default value is
     * <code>"0.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Start</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Start</em>' attribute.
     * @see #setStart(double)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getTimeFrame_Start()
     * @model default="0.0" required="true"
     * @generated
     */
    double getStart();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStart <em>Start</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Start</em>' attribute.
     * @see #getStart()
     * @generated
     */
    void setStart(double value);

    /**
     * Returns the value of the '<em><b>Stop</b></em>' attribute. The default value is
     * <code>"0.0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Stop</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Stop</em>' attribute.
     * @see #setStop(double)
     * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage#getTimeFrame_Stop()
     * @model default="0.0" required="true"
     * @generated
     */
    double getStop();

    /**
     * Sets the value of the '
     * {@link org.palladiosimulator.simulizar.monitorrepository.TimeFrame#getStop <em>Stop</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Stop</em>' attribute.
     * @see #getStop()
     * @generated
     */
    void setStop(double value);

} // TimeFrame
