package org.palladiosimulator.simulizar.action.interpreter;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.palladiosimulator.simulizar.action.context.ExecutionContext;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;

import de.uka.ipd.sdq.simucomframework.core.SimuComSimProcess;

/**
 * Utility class, realized as a singleton, to maintain associations between {@link ExecutionContext}
 * s and {@link SimuComSimProcess}es. More precisely, the {@link ExecutionContext} of any
 * {@link AdaptationBehavior} that is executed asynchronously (i.e., one of its
 * {@link AdaptationBehavior#executeAsync(org.palladiosimulator.simulizar.action.instance.RoleSet)}
 * methods has been called) is maintained here together with the simulation processed which executes
 * the behavior.
 * 
 * @author Florian Rosenthal
 *
 */
public final class ExecutionContextKeeper {

    private static final ExecutionContextKeeper instance = new ExecutionContextKeeper();

    private final Map<String, SimuComSimProcess> contextProcessMapping = new ConcurrentHashMap<>();

    private ExecutionContextKeeper() {
    }

    /**
     * Gets the sole instance of this class.
     * 
     * @return The sole {@link ExecutionContextKeeper} instance.
     */
    public static ExecutionContextKeeper getInstance() {
        return instance;
    }

    /**
     * Associates the given execution context with the given process
     * 
     * @param context
     *            An {@link ExecutionContext} instance.
     * @param process
     *            The {@link SimuComSimProcess} to be associated with the context.
     * @throws NullPointerException
     *             In case either of the parameters is {@code null}.
     * @throws IllegalArgumentException
     *             If the given context is already associated with a process.
     */
    void addContextProcessMapping(ExecutionContext context, SimuComSimProcess process) {
        String key = computeKey(Objects.requireNonNull(context));
        if (!this.contextProcessMapping.containsKey(key)) {
            this.contextProcessMapping.put(computeKey(Objects.requireNonNull(context)),
                    Objects.requireNonNull(process));
        } else {
            throw new IllegalArgumentException("Given ExecutionContext " + context.getId() + " is already "
                    + "associated with a SimuComSmProcess!");

        }
    }

    /**
     * Discards the association between the given context and the given process.
     * 
     * @param context
     *            An {@link ExecutionContext} instance.
     * @param process
     *            An {@link SimuComSimProcess}.
     * @throws NullPointerException
     *             In case either of the parameters is {@code null}.
     */
    void removeContextProcessMapping(ExecutionContext context, SimuComSimProcess process) {
        this.contextProcessMapping.remove(computeKey(Objects.requireNonNull(context)), Objects.requireNonNull(process));
    }

    /**
     * Gets the simulation process that is associated with the given context.
     * 
     * @param context
     *            An {@link ExecutionContext} instance.
     * @return A (potentially empty) {@link Optional} that holds the found {@link SimuComSimProcess}
     *         .
     * @throws NullPointerException
     *             In case {@code context == null}.
     */
    public Optional<SimuComSimProcess> getProcessForContext(ExecutionContext context) {
        return Optional.ofNullable(this.contextProcessMapping
                .get(computeKey(Objects.requireNonNull(context, "Context to lookup must not be null."))));
    }

    private String computeKey(ExecutionContext context) {
        assert context != null;

        return context.getId();
    }
}
