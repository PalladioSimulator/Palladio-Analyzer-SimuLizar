package org.palladiosimulator.simulizar.reconfiguration.probes;

import java.util.Objects;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.probeframework.probes.BasicEventProbe;
import org.palladiosimulator.probeframework.probes.EventProbe;
import org.palladiosimulator.simulizar.interpreter.listener.BeginReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EndReconfigurationEvent;
import org.palladiosimulator.simulizar.interpreter.listener.EventResult;
import org.palladiosimulator.simulizar.interpreter.listener.ReconfigurationExecutedEvent;
import org.palladiosimulator.simulizar.reconfiguration.IReconfigurationListener;
import org.palladiosimulator.simulizar.reconfiguration.Reconfigurator;

/**
 * {@link EventProbe} implementation that is associated with reconfiguration events, in particular
 * the {@link ReconfigurationExecutedEvent}.
 *
 * @author Florian Rosenthal
 *
 */
public class TakeReconfigurationDurationProbe extends BasicEventProbe<Reconfigurator, Double, Duration> {

    /**
     * Initializes a new instance of the {@link TakeReconfigurationDurationProbe} class.
     *
     * @param reconfigurator
     *            The {@link Reconfigurator} to observe.
     *
     * @throws NullPointerException
     *             In case the given reconfigurator is {@code null}.
     */
    public TakeReconfigurationDurationProbe(final Reconfigurator reconfigurator) {
        super(Objects.requireNonNull(reconfigurator), MetricDescriptionConstants.RECONFIGURATION_TIME_METRIC);
    }

    @Override
    protected void registerListener() {
        this.eventSource.addObserver(new IReconfigurationListener() {

            @Override
            public void reconfigurationExecuted(final ReconfigurationExecutedEvent reconfExecutedEvent) {
                if (reconfExecutedEvent.getReconfigurationResult() == EventResult.SUCCESS) {
                    TakeReconfigurationDurationProbe.this
                            .notify(Measure.valueOf(reconfExecutedEvent.getDuration(), SI.SECOND));
                }
            }

            @Override
            public void endReconfigurationEvent(final EndReconfigurationEvent event) {
                // nothing to do
            }

            @Override
            public void beginReconfigurationEvent(final BeginReconfigurationEvent event) {
                // nothing to do
            }
        });
    }
}
