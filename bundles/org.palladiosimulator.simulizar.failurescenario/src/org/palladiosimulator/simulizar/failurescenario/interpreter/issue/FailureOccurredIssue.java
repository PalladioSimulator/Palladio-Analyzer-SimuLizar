package org.palladiosimulator.simulizar.failurescenario.interpreter.issue;

import org.palladiosimulator.failuremodel.failuretype.Failure;
import org.palladiosimulator.simulizar.interpreter.result.InterpretationIssue;

public class FailureOccurredIssue implements InterpretationIssue {

	private final Failure failureType;
    private boolean handled = false;

    public FailureOccurredIssue(Failure failureType) {
        this.failureType = failureType;
    }
    
    public Failure getFailureType() {
        return failureType;
    }
    
    public void setRecovered() {
        this.handled = true;
    }

    @Override
    public boolean isHandled() {
        return handled;
    }

}
