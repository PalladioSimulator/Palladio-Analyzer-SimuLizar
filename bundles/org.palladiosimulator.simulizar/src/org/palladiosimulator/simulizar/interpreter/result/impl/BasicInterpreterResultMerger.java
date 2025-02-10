package org.palladiosimulator.simulizar.interpreter.result.impl;

import jakarta.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;

import com.google.common.collect.Iterables;

public class BasicInterpreterResultMerger implements InterpreterResultMerger {
    @Inject
    public BasicInterpreterResultMerger() {
    }

    /**
     * {@inheritDoc}
     * 
     * This method is not idempotent. If one of the parameter is reusable, it will do so for
     * performance reasons.
     */
    @Override
    public InterpreterResult merge(InterpreterResult previousResult, InterpreterResult newResult) {
        if (previousResult.hasNoIssues()) return newResult;
        if (newResult.hasNoIssues()) return previousResult;
        if (previousResult instanceof BasicInterpreterResult) {
            Iterables.addAll(((BasicInterpreterResult)previousResult).issues, newResult.getIssues());
            return previousResult;
        } else if (newResult instanceof BasicInterpreterResult) {
            Iterables.addAll(((BasicInterpreterResult)newResult).issues, previousResult.getIssues());
            return newResult;
        } else {
            var result = new BasicInterpreterResult();
            Iterables.addAll(result.issues, previousResult.getIssues());
            Iterables.addAll(result.issues, newResult.getIssues());
            return result;
        }
    }

}
