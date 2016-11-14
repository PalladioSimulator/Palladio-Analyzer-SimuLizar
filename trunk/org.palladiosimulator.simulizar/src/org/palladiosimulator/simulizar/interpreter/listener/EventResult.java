package org.palladiosimulator.simulizar.interpreter.listener;

/**
 * Enum to represent possible results of an event (e.g., a reconfiguration) that has occurred.
 * Currently supported are: {@code SUCCESS} and {@code FAILURE}.
 *
 * @author Florian Rosenthal
 *
 */
public enum EventResult {
    /**
     * Indicates a successful event.
     */
    SUCCESS, /**
              * Indicates an event that did not terminate as expected.
              */
    FAILURE;

    /**
     * Gets the {@link EventResult} that corresponds to the given boolean value.
     *
     * @param result
     *            A boolean value that denotes an event result.
     * @return {@link #SUCCESS} in case {@code true} was passed, {@link #FAILURE} otherwise.
     */
    public static EventResult fromBoolean(final boolean result) {
        return result ? SUCCESS : FAILURE;
    }
}
