package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.InterpretableLocationReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * This interface can be used to inform interpreter listeners about events which occur during
 * simulation. If events are to be emitted, an implementation of this emitter should be provided by
 * the DI framework as parameter to the interpreter constructor.
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpreterResultEventEmitter {

    /**
     * Inform the listeners, that the interpretation of the referenced element has finished and
     * yielded a particular result.
     * 
     * @param interpretableLocation
     *            an {@link EntityReference} which represents the interpreted simulation element.
     * @param result
     *            the result of the interpretation
     * @param context
     *            the current interpretation context
     */
    void emitInterpretationFinished(InterpretableLocationReference interpretableLocation, InterpreterResult result,
            InterpreterDefaultContext context);

}
