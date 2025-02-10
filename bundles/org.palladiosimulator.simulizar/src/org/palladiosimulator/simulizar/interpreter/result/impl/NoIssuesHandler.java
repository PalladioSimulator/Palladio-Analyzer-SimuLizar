package org.palladiosimulator.simulizar.interpreter.result.impl;

import jakarta.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultHandler;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResumptionPolicy;

/**
 * The no issues handler implements a rather trivial issue handling principle: As long as no
 * interpreter reports an issue normal excution continues. As soon as there is an issue, normal
 * execution flow is aborted.
 * 
 * @author Sebastian Krach
 *
 */
public class NoIssuesHandler implements InterpreterResultHandler {
    @Inject
    public NoIssuesHandler() {
    }

    @Override
    public InterpreterResumptionPolicy handleIssues(InterpreterResult result) {
        return result.hasNoIssues() ? InterpreterResumptionPolicy.CONTINUE : InterpreterResumptionPolicy.ABORT;
    }

}
