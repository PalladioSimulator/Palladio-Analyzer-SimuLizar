package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.Objects;

import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

/**
 * Class to notify that a reconfiguration started.
 *
 * @see IReconfigurationListener#endReconfigurationEvent(EndReconfigurationEvent)
 * @author Florian Rosenthal
 *
 */
public class BeginReconfigurationEvent extends ReconfigurationEvent {

    /**
     * Initializes a new instance with the given parameters.
     *
     * @param simulationTime
     *            A double to denote the point in time at which the reconfiguration started..
     */
    public BeginReconfigurationEvent(final double simulationTime) {
        super(EventType.BEGIN, simulationTime);
    }

    /**
     * Initializes a new instance with the given parameters.
     *
     * @param simulationControl
     *            The {@link ISimulationControl} that is used in the current simulation run.
     * @throws NullPointerException
     *             In case the given {@code simulationControl} is {@code null}.
     */
    public BeginReconfigurationEvent(final ISimulationControl simulationControl) {
        this(Objects.requireNonNull(simulationControl).getCurrentSimulationTime());
    }

}
