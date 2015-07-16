package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * {@link SimuComSimProcess} implementation which is responsible for executing reconfigurations
 * during Simulizar runs.
 * 
 * @author Florian Rosenthal
 * @see Reconfigurator
 * @see IReconfigurator
 */
public class ReconfigurationProcess extends SimuComSimProcess {

    private final EObject monitoredElement;
    private final Iterable<IReconfigurator> reconfigurators;
    private final ISimulationControl simControl;
    private final List<Notification> currentReconfigNotifications;
    private final Reconfigurator reconfigurator;

    /**
     * Initializes a new instance of the {@link ReconfigurationProcess} class.
     * 
     * @param model
     *            The {@link SimuComModel} that is in used during the current simulation run.
     * @param name
     *            The name of the new process.
     * @param reconfigurators
     *            An {@link Iterable} containing all {@link IReconfigurator} that shall be used.
     * @param monitoredElement
     * @param reconfigurator
     *            The {@link Reconfigurator} instance that manages and triggers reconfigurations.
     */
    protected ReconfigurationProcess(SimuComModel model, String name, Iterable<IReconfigurator> reconfigurators,
            EObject monitoredElement, Reconfigurator reconfigurator) {
        super(model, name);
        this.reconfigurators = Objects.requireNonNull(reconfigurators, "reconfigurators must not be null");
        this.reconfigurator = Objects.requireNonNull(reconfigurator, "reconfigurator must not be null");
        this.monitoredElement = Objects.requireNonNull(monitoredElement, "Monitored EObject must not be null.");
        this.simControl = Objects.requireNonNull(model, "Passed SimuComModel must not be null").getSimulationControl();
        this.currentReconfigNotifications = new ArrayList<>();
    }

    /**
     * Appends the given notification about model change due to a reconfiguration that takes place.
     * 
     * @param notification
     *            A {@link Notification} that describe model changes due to reconfiguration.
     */
    public void appendReconfigurationNotification(Notification notification) {
        if (notification != null) {
            this.currentReconfigNotifications.add(notification);
        }
    }

    private void fireBeginReconfigurationEvent(BeginReconfigurationEvent event) {
        this.reconfigurator.fireReconfigurationEvent(event);
    }

    private void fireEndReconfigurationEvent(EndReconfigurationEvent event) {
        this.reconfigurator.fireReconfigurationEvent(event);
    }

    private void fireReconfigurationExecutedEvent(BeginReconfigurationEvent beginEvent, EndReconfigurationEvent endEvent) {
        this.reconfigurator.fireReconfigurationEvent(new ReconfigurationExecutedEvent(beginEvent, endEvent,
                this.currentReconfigNotifications));
    }

    private void clearNotifications() {
        this.currentReconfigNotifications.clear();
    }

    @Override
    protected void internalLifeCycle() {
        for (IReconfigurator reconfigurator : this.reconfigurators) {
            BeginReconfigurationEvent beginReconfigurationEvent = new BeginReconfigurationEvent(
                    this.simControl.getCurrentSimulationTime());
            fireBeginReconfigurationEvent(beginReconfigurationEvent);
            boolean reconfigResult = reconfigurator.checkAndExecute(monitoredElement);
            EndReconfigurationEvent endReconfigurationEvent = new EndReconfigurationEvent(
                    EventResult.fromBoolean(reconfigResult), this.simControl.getCurrentSimulationTime());
            fireEndReconfigurationEvent(endReconfigurationEvent);
            if (reconfigResult) {
                LOGGER.debug("Successfully executed reconfiguration.");
                fireReconfigurationExecutedEvent(beginReconfigurationEvent, endReconfigurationEvent);
            }
            clearNotifications();
        }
    }
}
