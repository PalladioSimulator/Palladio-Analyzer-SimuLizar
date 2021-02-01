package org.palladiosimulator.simulizar.interpreter.result;

/**
 * The interpreter result merger encapsulates the logic required to combine the issues of multiple
 * {@link InterpreterResult} instances.
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpreterResultMerger {

    /**
     * Merges two interpreter result instances.
     * 
     * Attention: this method is not required to be idempotent. The passed result instances should
     * not be used any further after the call to merge, as implementors are allowed to reuse one of
     * the objects for the final result.
     * 
     */
    InterpreterResult merge(InterpreterResult previousResult, InterpreterResult newResult);

}
