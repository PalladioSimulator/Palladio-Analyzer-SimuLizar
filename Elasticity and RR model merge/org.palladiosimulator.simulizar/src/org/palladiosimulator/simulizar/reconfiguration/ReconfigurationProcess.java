package org.palladiosimulator.simulizar.reconfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.palladiosimulator.commons.eclipseutils.FileHelper;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.reconfigurationrule.ModelTransformation;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.ModelTransformationCache;
import org.palladiosimulator.simulizar.reconfigurationrule.qvto.QvtoModelTransformation;
import org.storydriven.storydiagrams.activities.Activity;

import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * {@link SimuComSimProcess} implementation which is responsible for executing reconfigurations
 * during Simulizar runs.
 *
 * @author Florian Rosenthal, Sebastian Lehrig
 * @see Reconfigurator
 * @see IReconfigurator
 */
@SuppressWarnings("restriction")
public class ReconfigurationProcess extends SimuComSimProcess {

    private EObject monitoredElement;
    private final Iterable<IReconfigurationEngine> reconfigurators;
    private final ISimulationControl simControl;
    private final List<Notification> currentReconfigNotifications;
    private final Reconfigurator reconfigurator;
    // volatile is sufficient as flag is only set once
    private volatile boolean terminationRequested = false;
    private List<ModelTransformation<Activity>> sdmTransformations;
    private List<ModelTransformation<OperationalTransformation>> qvtoTransformations;
    private static final String QVTO_FILE_EXTENSION = ".qvto";

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
    protected ReconfigurationProcess(final SimuComModel model, final Iterable<IReconfigurationEngine> reconfigurators,
            final Reconfigurator reconfigurator) {
        super(model, "Reconfiguration Process");
        this.reconfigurators = Objects.requireNonNull(reconfigurators, "reconfigurators must not be null");
        this.reconfigurator = Objects.requireNonNull(reconfigurator, "reconfigurator must not be null");
        this.simControl = Objects.requireNonNull(model, "Passed SimuComModel must not be null").getSimulationControl();
        this.currentReconfigNotifications = new ArrayList<>();
        URI[] qvtoFiles = getQvtoFiles(this.reconfigurator.getConfiguration().getReconfigurationRulesFolder());
        ModelTransformationCache transformationCache = new ModelTransformationCache(
                getQvtoFiles(reconfigurator.getConfiguration().getReconfigurationRulesFolder()));
        qvtoTransformations = new ArrayList<ModelTransformation<OperationalTransformation>>();
        for(QvtoModelTransformation mt : transformationCache.getAll()){
        	qvtoTransformations.add(mt);
        }
    }
    
    /**
     * Gets the QVTO files within the specified path.
     * 
     * @param path
     *            Path to reconfiguration rules.
     * @return The QVTO files within the given path. Returns an empty array in case no files are
     *         found.
     */
    private static URI[] getQvtoFiles(final String path) {
        assert path != null;
        if (path.equals("")) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No path to QVTo rules given.");
            }
            return new URI[0];
        }

        final URI[] uris = FileHelper.getURIs(path, QVTO_FILE_EXTENSION);

        if (uris.length == 0) {
            LOGGER.info("No QVTo rules found, QVTo reconfigurations disabled.");
        }

        return uris;
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
        this.reconfigurator.fireReconfigurationEvent(event);
    }

    private void fireEndReconfigurationEvent(final EndReconfigurationEvent event) {
        this.reconfigurator.fireReconfigurationEvent(event);
    }

    private void fireReconfigurationExecutedEvent(final BeginReconfigurationEvent beginEvent,
            final EndReconfigurationEvent endEvent) {
        this.reconfigurator.fireReconfigurationEvent(
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

    @Override
    protected void internalLifeCycle() {
        // execute reconfigurations until termination requested
        while (!this.isTerminationRequested()) {
            final EObject monitoredElement = this.getMonitoredElement();
            if (monitoredElement != null) {
                for (final IReconfigurationEngine reconfigurator : this.reconfigurators) {
                    // Lehrig: I moved from simulation time to System.nanoTime() since
                    // reconfigurations currently execute in 0-simulation time. Nano time made more
                    // sense to me. Inform me if I'm wrong here since there is already some code
                    // depending on the assumption that the duration is greater than 0.
                    final BeginReconfigurationEvent beginReconfigurationEvent = new BeginReconfigurationEvent(
                            System.nanoTime());
                    this.fireBeginReconfigurationEvent(beginReconfigurationEvent);
//                    final boolean reconfigResult = reconfigurator.checkAndExecute(monitoredElement);
                    EList<ModelTransformation<?>> qvtoTr = new BasicEList<ModelTransformation<?>>(qvtoTransformations);
                    final boolean reconfigResult = reconfigurator.runCheck(qvtoTr, monitoredElement);
                    final EndReconfigurationEvent endReconfigurationEvent = new EndReconfigurationEvent(
                            EventResult.fromBoolean(reconfigResult), System.nanoTime());
                    this.fireEndReconfigurationEvent(endReconfigurationEvent);
                    if (reconfigResult) {
                        LOGGER.debug("Successfully executed reconfiguration.");
                        this.fireReconfigurationExecutedEvent(beginReconfigurationEvent, endReconfigurationEvent);
                    }
                    this.clearNotifications();
                }
                // all reconfigurators did their job, so we can go to sleep
                this.passivate();
            }
        }
    }
}
