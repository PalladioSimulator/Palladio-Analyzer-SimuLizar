package org.palladiosimulator.simulizar.interpreter.result;

/**
 * This enum captures the effect an interpretation issue has on the interpreter control flow.
 * 
 * The underlying assumption is that models are traversed in an hierarchical fashion.
 * 
 * @author Sebastian Krach
 *
 */
public enum InterpreterResumptionPolicy {
    
    /**
     * Interpretation continues normal.
     */
    CONTINUE,
    
    /**
     * Interpretation should stop and return control to the invoking interpreter.
     */
    ABORT

}
