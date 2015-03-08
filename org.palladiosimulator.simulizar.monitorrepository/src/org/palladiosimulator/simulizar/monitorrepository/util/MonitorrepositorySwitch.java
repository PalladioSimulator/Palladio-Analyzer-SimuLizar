/**
 */
package org.palladiosimulator.simulizar.monitorrepository.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.monitorrepository.DelayedIntervall;
import org.palladiosimulator.simulizar.monitorrepository.Intervall;
import org.palladiosimulator.simulizar.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.simulizar.monitorrepository.Monitor;
import org.palladiosimulator.simulizar.monitorrepository.MonitorRepository;
import org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage;
import org.palladiosimulator.simulizar.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.simulizar.monitorrepository.TimeFrame;

import de.uka.ipd.sdq.identifier.Identifier;
import de.uka.ipd.sdq.pcm.core.entity.Entity;
import de.uka.ipd.sdq.pcm.core.entity.NamedElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the
 * call {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for
 * each class of the model, starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the result of the switch.
 * <!-- end-user-doc -->
 *
 * @see org.palladiosimulator.simulizar.monitorrepository.MonitorrepositoryPackage
 * @generated
 */
public class MonitorrepositorySwitch<T> extends Switch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static MonitorrepositoryPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public MonitorrepositorySwitch() {
        if (modelPackage == null) {
            modelPackage = MonitorrepositoryPackage.eINSTANCE;
        }
    }

    /**
     * Checks whether this is a switch for the given package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @parameter ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
    @Override
    protected boolean isSwitchFor(final EPackage ePackage) {
        return ePackage == modelPackage;
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result;
     * it yields that result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    @Override
    protected T doSwitch(final int classifierID, final EObject theEObject) {
        switch (classifierID) {
        case MonitorrepositoryPackage.MONITOR_REPOSITORY: {
            final MonitorRepository monitorRepository = (MonitorRepository) theEObject;
            T result = this.caseMonitorRepository(monitorRepository);
            if (result == null) {
                result = this.caseEntity(monitorRepository);
            }
            if (result == null) {
                result = this.caseIdentifier(monitorRepository);
            }
            if (result == null) {
                result = this.caseNamedElement(monitorRepository);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.MONITOR: {
            final Monitor monitor = (Monitor) theEObject;
            T result = this.caseMonitor(monitor);
            if (result == null) {
                result = this.caseEntity(monitor);
            }
            if (result == null) {
                result = this.caseIdentifier(monitor);
            }
            if (result == null) {
                result = this.caseNamedElement(monitor);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.MEASUREMENT_SPECIFICATION: {
            final MeasurementSpecification measurementSpecification = (MeasurementSpecification) theEObject;
            T result = this.caseMeasurementSpecification(measurementSpecification);
            if (result == null) {
                result = this.caseIdentifier(measurementSpecification);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.TEMPORAL_CHARACTERIZATION: {
            final TemporalCharacterization temporalCharacterization = (TemporalCharacterization) theEObject;
            T result = this.caseTemporalCharacterization(temporalCharacterization);
            if (result == null) {
                result = this.caseIdentifier(temporalCharacterization);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.INTERVALL: {
            final Intervall intervall = (Intervall) theEObject;
            T result = this.caseIntervall(intervall);
            if (result == null) {
                result = this.caseTemporalCharacterization(intervall);
            }
            if (result == null) {
                result = this.caseIdentifier(intervall);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.DELAYED_INTERVALL: {
            final DelayedIntervall delayedIntervall = (DelayedIntervall) theEObject;
            T result = this.caseDelayedIntervall(delayedIntervall);
            if (result == null) {
                result = this.caseIntervall(delayedIntervall);
            }
            if (result == null) {
                result = this.caseTemporalCharacterization(delayedIntervall);
            }
            if (result == null) {
                result = this.caseIdentifier(delayedIntervall);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        case MonitorrepositoryPackage.TIME_FRAME: {
            final TimeFrame timeFrame = (TimeFrame) theEObject;
            T result = this.caseTimeFrame(timeFrame);
            if (result == null) {
                result = this.caseTemporalCharacterization(timeFrame);
            }
            if (result == null) {
                result = this.caseIdentifier(timeFrame);
            }
            if (result == null) {
                result = this.defaultCase(theEObject);
            }
            return result;
        }
        default:
            return this.defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Monitor Repository</em>
     * '. <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Monitor Repository</em>
     *         '.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMonitorRepository(final MonitorRepository object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Monitor</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Monitor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMonitor(final Monitor object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Measurement Specification</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Measurement Specification</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMeasurementSpecification(final MeasurementSpecification object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Temporal Characterization</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Temporal Characterization</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTemporalCharacterization(final TemporalCharacterization object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Intervall</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intervall</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIntervall(final Intervall object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Delayed Intervall</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Delayed Intervall</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDelayedIntervall(final DelayedIntervall object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Time Frame</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Time Frame</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTimeFrame(final TimeFrame object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identifier</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identifier</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifier(final Identifier object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNamedElement(final NamedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Entity</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Entity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEntity(final Entity object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will
     * terminate the switch, but this is the last case anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    @Override
    public T defaultCase(final EObject object) {
        return null;
    }

} // MonitorrepositorySwitch
