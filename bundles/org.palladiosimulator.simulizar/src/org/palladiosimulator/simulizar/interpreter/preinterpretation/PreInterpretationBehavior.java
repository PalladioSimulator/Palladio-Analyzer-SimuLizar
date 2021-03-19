package org.palladiosimulator.simulizar.interpreter.preinterpretation;

import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * Behavior for a PreInterpretationBehaviorContainer.
 * 
 * @author Jonas Lehmann
 *
 */
public abstract class PreInterpretationBehavior {
    
    private InterpreterResult result;
    
    public PreInterpretationBehavior(InterpreterResult result) {
        this.result = result;
    }
    
    /**
     * Implementations should override this and execute intern behavior.
     * After that call super.execute(); to return the InterpreterResult.
     * @return the InterpreterResult
     */
    public InterpreterResult execute() {
        return this.result;
    }

    public InterpreterResult getResult() {
        return result;
    }

    public void setResult(InterpreterResult result) {
        this.result = result;
    }
}
