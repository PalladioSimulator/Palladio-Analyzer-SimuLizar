package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.common.notify.Notification;

/**
 * Notifier class that indicates that a reconfiguration was executed.
 *
 * @author Florian Rosenthal
 *
 */
public class ReconfigurationExecutedEvent {

    private final BeginReconfigurationEvent beginReconfEvent;
    private final EndReconfigurationEvent endReconfEvent;
    private final Iterable<Notification> modelChanges;

    /**
     * Initializes a new instance of the {@link ReconfigurationExecutedEvent} class with the given
     * arguments.
     *
     * @param beginReconfigurationEvent
     *            The {@link BeginReconfigurationEvent} notification that was created when the
     *            corresponding reconfiguration had started.
     * @param endReconfigurationEvent
     *            The {@link EndReconfigurationEvent} notification that was created upon finish of
     *            the corresponding reconfiguration.
     * @param modelChanges
     *            An {@link Iterable} of {@link Notification}s related to this reconfiguration.
     */
    public ReconfigurationExecutedEvent(final BeginReconfigurationEvent beginReconfigurationEvent,
            final EndReconfigurationEvent endReconfigurationEvent, final Iterable<Notification> modelChanges) {
        if (beginReconfigurationEvent == null || endReconfigurationEvent == null || modelChanges == null) {
            throw new IllegalArgumentException("None of the parameters must be null.");
        }
        this.beginReconfEvent = beginReconfigurationEvent;
        this.endReconfEvent = endReconfigurationEvent;
        this.modelChanges = modelChanges;
    }

    /**
     * Gets the result of the associated reconfiguration.
     *
     * @return An {@link EventType} constant which the result of the reconfiguration.
     * @see EndReconfigurationEvent#getReconfigurationEventResult()
     */
    public EventResult getReconfigurationResult() {
        return this.endReconfEvent.getReconfigurationEventResult();
    }

    /**
     * Gets the point in time (expressed in simulation time units) at which the reconfiguration
     * terminated.<br>
     * It always holds that {@code getFinishTime()  -getStartTime() >= 0}.
     *
     * @return A non-negative double denoting the finish time.
     */
    public double getFinishTime() {
        return this.endReconfEvent.getPassageTime();
    }

    /**
     * Gets the point in time (expressed in simulation time units) at which the reconfiguration
     * started. <br>
     * It always holds that {@code getFinishTime() - getStartTime() >= 0}.
     *
     * @return A non-negative double denoting the start time.
     */
    public double getStartTime() {
        return this.beginReconfEvent.getPassageTime();
    }

    /**
     * Gets the duration (expressed in simulation time units) of the reconfiguration.
     *
     * @return A non-negative double denoting the duration.
     */
    public double getDuration() {
        return this.getFinishTime() - this.getStartTime();
    }

    /**
     * Gets the {@link Notification}s that are associated with this reconfiguration. <br>
     * In general, a notification is concerned with a particular model change.
     *
     * @return AN {@link Iterable} consisting of all the notifications.
     */
    public Iterable<Notification> getModelChanges() {
        return this.modelChanges;
    }
}
