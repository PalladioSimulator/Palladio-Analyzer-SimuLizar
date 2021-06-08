package org.palladiosimulator.simulizar.interpreter.result.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.palladiosimulator.simulizar.interpreter.result.InterpretationIssue;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

import com.google.common.collect.ImmutableList;

public class BasicInterpreterResult implements InterpreterResult {
    List<InterpretationIssue> issues;

    @Override
    public boolean hasNoIssues() {
        return issues.isEmpty();
    }

    @Override
    public Iterable<InterpretationIssue> getIssues() {
        return ImmutableList.copyOf(issues);
    }
    
    public static BasicInterpreterResult of(InterpretationIssue issue) {
        var result = new BasicInterpreterResult();
        result.issues = new ArrayList<>(Collections.singletonList(issue));
        return result;                
    }
    

}
