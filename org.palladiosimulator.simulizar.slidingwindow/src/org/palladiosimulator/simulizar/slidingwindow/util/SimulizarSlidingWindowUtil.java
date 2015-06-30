package org.palladiosimulator.simulizar.slidingwindow.util;

import javax.measure.Measure;
import javax.measure.quantity.Duration;
import javax.measure.unit.SI;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.monitorrepository.DelayedIntervall;
import org.palladiosimulator.monitorrepository.Intervall;
import org.palladiosimulator.monitorrepository.MeasurementSpecification;
import org.palladiosimulator.monitorrepository.TemporalCharacterization;
import org.palladiosimulator.monitorrepository.util.MonitorRepositorySwitch;
import org.palladiosimulator.simulizar.slidingwindow.impl.SimulizarSlidingWindow;

/**
 * Utility class for dealing with {@link SimulizarSlidingWindow}s and their properties.
 * @author Florian Rosenthal
 *
 */
public final class SimulizarSlidingWindowUtil {

    /**
     * The ctor is private as this is a utility class.
     */
    private SimulizarSlidingWindowUtil() {
        //private ctor as this is a utility class
    }
    
    /**
     * returns a two-element array: sliding window length is returned at index 0, window increment
     * at index 1
     */
    private static final MonitorRepositorySwitch<Measure<Double, Duration>[]> WINDOW_PROPERTIES_SWITCH =
            new MonitorRepositorySwitch<Measure<Double, Duration>[]>() {

        @Override
        public Measure<Double, Duration>[] caseDelayedIntervall(DelayedIntervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = Measure.valueOf(interval.getDelay(), SI.SECOND);
            return result;
        }

        @Override
        public Measure<Double, Duration>[] caseIntervall(Intervall interval) {
            @SuppressWarnings("unchecked")
            Measure<Double, Duration>[] result = (Measure<Double, Duration>[]) new Measure<?, ?>[2];
            result[0] = Measure.valueOf(interval.getIntervall(), SI.SECOND);
            result[1] = result[0];

            return result;
        }

        @Override
        public Measure<Double, Duration>[] defaultCase(EObject obj) {
            throw new IllegalStateException(
                    "Temporal characterization for sliding window"
                            + "must be either Intervall or DelayedIntervall.");
        }
    };
    
    /**
     * Retrieves the {@link SimulizarSlidingWindow} properties {@code window length} and {@code window increment} 
     * from the given {@link TemporalCharacterization}.
     * @param tempCharacterization A {@link TemporalCharacterization} belonging to a {@link MeasurementSpecification}.
     * @return A two-element array containing the window length at position 0 and the window increment at position 1.<br>
     * Both values are expressed in terms of a {@link Measure}.
     * @throws IllegalArgumentException In case the given temporal characterization is {@code null}.
     * @throws IllegalStateException In case the temporal characterization is neither of type {@link Intervall} 
     * nor of type {@link DelayedIntervall}.
     */
    public static Measure<Double, Duration>[] getWindowPropertiesFromTemporalCharacterization(
            TemporalCharacterization tempCharacterization) {
        if (tempCharacterization == null) {
            throw new IllegalArgumentException("Given tempCharacterization must not be null.");
        }
        return WINDOW_PROPERTIES_SWITCH.doSwitch(tempCharacterization);
    }
}
