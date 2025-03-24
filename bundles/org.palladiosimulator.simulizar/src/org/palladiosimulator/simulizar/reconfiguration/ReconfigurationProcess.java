package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.core.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;

import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.core.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.core.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

/**
 * {@link SimuComSimProcess} implementation which is responsible for executing reconfigurations
 * during Simulizar runs.
 *
 * @author Florian Rosenthal, Sebastian Lehrig
 * @see Reconfigurator
 * @see IReconfigurator
 */
public class ReconfigurationProcess extends SimuComSimProcess {

    private EObject monitoredElement;
    private final Iterable<IReconfigurationEngine> reconfigurators;
    private final List<Notification> currentReconfigNotifications;
    private final IResourceTableManager resourceTableManager;
    // volatile is sufficient as flag is only set once
    private volatile boolean terminationRequested = false;
    private EList<ModelTransformation<? extends Object>> transformations;
    private final IReconfigurationListener reconfigurationEventDispatcher;
    private final ISimulationTimeProvider simTimeProvider;

    /**
     * Initializes a new instance of the {@link ReconfigurationProcess} class.
     *
     * @param model
     *            The {@link SimuComModel} that is in used during the current simulation run.
     * @param reconfigurators
     *            An {@link Iterable} containing all {@link IReconfigurator} that shall be used.
     * @param reconfigurator
     *            The {@link Reconfigurator} instance that manages and triggers reconfigurations.
     * @throws NullPointerException
     *             In case any of the given parameters is {@code null}.
     */
    @AssistedInject
    protected ReconfigurationProcess(final SimuComModel model, Set<IReconfigurationEngine> reconfigurators,
            Set<AbstractReconfigurationLoader> reconfigurationLoaders, SimuLizarWorkflowConfiguration configuration,
            IReconfigurationListener reconfigurationEventDispatcher, IResourceTableManager resourceTableManager,
            ISimulationTimeProvider simTimeProvider) {
        super(model, "Reconfiguration Process", resourceTableManager);
        this.simTimeProvider = simTimeProvider;
        this.reconfigurationEventDispatcher = Objects.requireNonNull(reconfigurationEventDispatcher,
                "EventDispatcher must not be null");
        this.reconfigurators = Objects.requireNonNull(reconfigurators, "reconfigurators must not be null");
        this.currentReconfigNotifications = new ArrayList<>();
        this.transformations = new BasicEList<>();
        this.resourceTableManager = resourceTableManager;

        reconfigurationLoaders.forEach(l -> {
            this.transformations.addAll(l.getTransformations());
        });
    }

    /**
     * Appends the given notification about model change due to a reconfiguration that takes place.
     *
     * @param notification
     *            A {@link Notification} that describe model changes due to reconfiguration.
     */
    public void appendReconfigurationNotification(final Notification notification) {
        if (notification != null) {
            this.currentReconfigNotifications.add(notification);
        }
    }

    private void fireBeginReconfigurationEvent(final BeginReconfigurationEvent event) {
        reconfigurationEventDispatcher.beginReconfigurationEvent(event);
    }

    private void fireEndReconfigurationEvent(final EndReconfigurationEvent event) {
        reconfigurationEventDispatcher.endReconfigurationEvent(event);
    }

    private void fireReconfigurationExecutedEvent(final BeginReconfigurationEvent beginEvent,
            final EndReconfigurationEvent endEvent) {
        reconfigurationEventDispatcher.reconfigurationExecuted(
                new ReconfigurationExecutedEvent(beginEvent, endEvent, this.currentReconfigNotifications));
    }

    private void setMonitoredElement(final EObject monitoredElement) {
        this.monitoredElement = monitoredElement;
    }

    private EObject getMonitoredElement() {
        return this.monitoredElement;
    }

    private void clearNotifications() {
        this.currentReconfigNotifications.clear();
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("The reconfiguration process is not supposed to be activated manually."
                + "Use executeReconfigurations(EObject) instead.");
    }

    @Override
    public void reschedule(final double d) {
        throw new UnsupportedOperationException("The reconfiguration process is not supposed to be scheduled manually."
                + "Use executeReconfigurations(EObject) instead.");
    }

    /**
     * Indicates whether termination has been requested by a call to {@link #requestTermination()}.
     * <br>
     * Note, that the process may still be running, even if this method returns {@code true}.
     *
     * @return {@code true} if termination has been requested, otherwise {@code false}.
     */
    boolean isTerminationRequested() {
        return this.terminationRequested;
    }

    /**
     * Requests that the process shall terminate properly.
     */
    void requestTermination() {
        if (!this.terminationRequested) {
            // this ensures that flag is only set once
            this.terminationRequested = true;
        }
    }

    /**
     * Executes all reconfigurations that are provided by the attached {@link IReconfigurator}s.
     *
     * @param monitoredElement
     *            The {@link EObject} which is the reconfiguration target.
     * @throws IllegalStateException
     *             In case the process is already either scheduled or finished.
     * @throws NullPointerException
     *             In case the passed EObject is {@code null}.
     */
    void executeReconfigurations(final EObject monitoredElement) {
        if (this.isScheduled()) {
            throw new IllegalStateException("Reconfigurations are already taking place.");
        }
        if (this.isFinished()) {
            throw new IllegalStateException("Reconfiguration process has already terminated.");
        }
        // the process is not scheduled, so it should be passive
        // update of monitored element is safe
        this.setMonitoredElement(Objects.requireNonNull(monitoredElement, "Monitored element must not be null."));
        this.scheduleAt(0);
    }

    private Consumer<IReconfigurationEngine> doReconfiguration(double currentSimulationTime, EObject monitoredElement) {
        return r -> {
            BeginReconfigurationEvent beginReconfigurationEvent = new BeginReconfigurationEvent(currentSimulationTime);
            ReconfigurationProcess.this.fireBeginReconfigurationEvent(beginReconfigurationEvent);
            Map<String, Object> configParams = new HashMap<>();
            final boolean reconfigResult = r.runCheck(transformations, monitoredElement, resourceTableManager,
                    configParams);
            EndReconfigurationEvent endReconfigurationEvent = new EndReconfigurationEvent(
                    EventResult.fromBoolean(reconfigResult), simTimeProvider.getCurrentSimulationTime());
            ReconfigurationProcess.this.fireEndReconfigurationEvent(endReconfigurationEvent);
            if (reconfigResult) {
                LOGGER.debug("Successfully executed reconfiguration.");
                ReconfigurationProcess.this.fireReconfigurationExecutedEvent(beginReconfigurationEvent,
                        endReconfigurationEvent);
            }
            ReconfigurationProcess.this.clearNotifications();
        };
    }

    @Override
    protected void internalLifeCycle() {
        // execute reconfigurations until termination requested
        while (!this.isTerminationRequested()) {
            final EObject monitoredElement = this.getMonitoredElement();
            if (monitoredElement != null) {
                this.reconfigurators
                    .forEach(this.doReconfiguration(simTimeProvider.getCurrentSimulationTime(), monitoredElement));
                // all reconfigurators did their job, so we can go to sleep
                this.passivate();
            }
        }
    }
}
