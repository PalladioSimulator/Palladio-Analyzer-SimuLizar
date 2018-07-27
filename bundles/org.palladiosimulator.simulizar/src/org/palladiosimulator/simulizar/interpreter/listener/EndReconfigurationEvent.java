package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.Objects;

import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * Class to notify that an reconfiguration event terminated.
 *
 * @see IReconfigurationListener#endReconfigurationEvent(EndReconfigurationEvent)
 * @author Florian Rosenthal
 *
 */
public final class EndReconfigurationEvent extends ReconfigurationEvent {

    private final EventResult result;

    /**
     * Initializes a new instance with the given parameters.
     *
     * @param result
     *            An {@link EventType} constant to denote the result of the reconfiguration.
     * @param simulationTime
     *            A double to denote the simulation time.
     */
    public EndReconfigurationEvent(final EventResult result, final double simulationTime) {
        super(EventType.END, simulationTime);
        this.result = result;
    }

    /**
     * Initializes a new instance with the given parameters.
     *
     * @param result
     *            An {@link EventType} constant to denote the result of the reconfiguration.
     * @param simulationControl
     *            The {@link ISimulationControl} that is used in the current simulation run.
     * @throws NullPointerException
     *             In case the given {@code simulationControl} is {@code null}.
     */
    public EndReconfigurationEvent(final EventResult result, final ISimulationControl simulationControl) {
        this(result, Objects.requireNonNull(simulationControl).getCurrentSimulationTime());
    }

    /**
     * Gets the result of the finished reconfiguration event.
     *
     * @return An {@link EventType} constant which the result of the reconfiguration.
     */
    public EventResult getReconfigurationEventResult() {
        return this.result;
    }
}
