package org.palladiosimulator.simulizar.interpreter.listener;

import java.util.Set;

import javax.inject.Inject;

import org.palladiosimulator.simulizar.entity.InterpretableLocationReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class InterpreterResultListenerDispatch implements InterpreterResultEventEmitter {

    private Set<InterpreterResultListener> resultListeners;

    @Inject
    public InterpreterResultListenerDispatch(Set<InterpreterResultListener> resultListeners) {
        this.resultListeners = resultListeners;
    }

    @Override
    public void emitInterpretationFinished(InterpretableLocationReference interpretableLocation,
            InterpreterResult result, InterpreterDefaultContext context) {
        if (resultListeners != null) {
            resultListeners.forEach(l -> l.interpretationFinished(interpretableLocation, result, context));
        }
    }

}
