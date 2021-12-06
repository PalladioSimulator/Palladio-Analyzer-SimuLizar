package org.palladiosimulator.simulizar.interpreter.listener;

import org.palladiosimulator.simulizar.di.extension.Extension;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.InterpretableLocationReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

/**
 * This interface allows extension to register listeners to simulation events.
 * 
 * The events are emitted through a suitable {@link InterpreterResultEventEmitter} implementation.
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpreterResultListener extends Extension {

    /**
     * @param interpretableLocation
     *            an {@link EntityReference} which represents the interpreted simulation element.
     * @param result
     *            the result of the interpretation
     * @param context
     *            the current interpretation context
     */
    void interpretationFinished(InterpretableLocationReference interpretableLocation, InterpreterResult result,
            InterpreterDefaultContext context);

}
