/**
 */
package org.palladiosimulator.servicelevelobjective;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Service Level Objective Repository</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjectiveRepository#getServicelevelobjectives
 * <em>Servicelevelobjectives</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjectiveRepository()
 * @model
 * @generated
 */
public interface ServiceLevelObjectiveRepository extends EObject {
    /**
     * Returns the value of the '<em><b>Servicelevelobjectives</b></em>' containment reference list.
     * The list contents are of type
     * {@link org.palladiosimulator.servicelevelobjective.ServiceLevelObjective}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Servicelevelobjectives</em>' containment reference list isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Servicelevelobjectives</em>' containment reference list.
     * @see org.palladiosimulator.servicelevelobjective.ServicelevelObjectivePackage#getServiceLevelObjectiveRepository_Servicelevelobjectives()
     * @model containment="true"
     * @generated
     */
    EList<ServiceLevelObjective> getServicelevelobjectives();

} // ServiceLevelObjectiveRepository
