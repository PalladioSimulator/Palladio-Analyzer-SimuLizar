package org.palladiosimulator.simulizar.interpreter.result.impl;

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
    

}
