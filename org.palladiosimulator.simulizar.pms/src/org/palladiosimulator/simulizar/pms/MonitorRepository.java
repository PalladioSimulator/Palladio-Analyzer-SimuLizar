/**
 */
package org.palladiosimulator.simulizar.pms;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.core.entity.Entity;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Monitor Repository</b></em>
 * '. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Base class representing the pms model. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.palladiosimulator.simulizar.pms.MonitorRepository#getMonitors <em>Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMonitorRepository()
 * @model
 * @generated
 */
public interface MonitorRepository extends EObject, Entity {
    /**
     * Returns the value of the '<em><b>Monitors</b></em>' containment reference list. The list
     * contents are of type {@link org.palladiosimulator.simulizar.pms.Monitor}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Monitors</em>' containment reference list isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Monitors</em>' containment reference list.
     * @see org.palladiosimulator.simulizar.pms.PmsPackage#getMonitorRepository_Monitors()
     * @model containment="true"
     * @generated
     */
    EList<Monitor> getMonitors();

} // MonitorRepository
