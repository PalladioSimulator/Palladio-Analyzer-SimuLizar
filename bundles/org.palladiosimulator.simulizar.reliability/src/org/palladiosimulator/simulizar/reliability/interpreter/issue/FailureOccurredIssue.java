package org.palladiosimulator.simulizar.reliability.interpreter.issue;

import org.palladiosimulator.pcm.reliability.FailureType;
import org.palladiosimulator.simulizar.interpreter.result.InterpretationIssue;

public class FailureOccurredIssue implements InterpretationIssue {

    private final FailureType failureType;
    private boolean handled = false;

    public FailureOccurredIssue(FailureType failureType) {
        this.failureType = failureType;
    }
    
    public FailureType getFailureType() {
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
