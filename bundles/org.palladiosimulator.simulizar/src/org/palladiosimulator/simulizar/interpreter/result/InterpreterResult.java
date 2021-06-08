package org.palladiosimulator.simulizar.interpreter.result;

import org.palladiosimulator.simulizar.interpreter.result.impl.BasicInterpreterResult;

import com.google.common.collect.ImmutableSet;

/**
 * The InterpreterResult captures the status of an interpreter execution.
 * 
 * If the execution occured as expected, the result should not report any issues.
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpreterResult {
    public static final InterpreterResult OK = new OkResult();
    
    public static class OkResult implements InterpreterResult {
        @Override
        final public boolean hasNoIssues() {
            return true;
        }
        
        @Override
        final public Iterable<InterpretationIssue> getIssues() {
            return ImmutableSet.of();
        }
    }
    
    boolean hasNoIssues();
    
    Iterable<InterpretationIssue> getIssues();
    
    public static InterpreterResult of(InterpretationIssue issue) {
        return BasicInterpreterResult.of(issue);
    }
}
