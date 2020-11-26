package org.palladiosimulator.simulizar.middlewarecompletions;

import javax.inject.Inject;

import org.palladiosimulator.analyzer.completions.DelegatingExternalCallAction;
import org.palladiosimulator.analyzer.completions.util.CompletionsSwitch;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ExplicitDispatchComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

import com.google.auto.factory.AutoFactory;

/**
 * This is a temporary dummy implementation for SIMULIZAR-118.
 */
@AutoFactory(extending = AbstractRDSeffSwitchFactory.class)
public class CompletionsRDSeffSwitch extends CompletionsSwitch<Object> {
    
    private final ExplicitDispatchComposedSwitch<Object> parentSwitch;

    @Inject
    public CompletionsRDSeffSwitch(InterpreterDefaultContext context,
            ExplicitDispatchComposedSwitch<Object> parentSwitch) {
                this.parentSwitch = parentSwitch;
    }
    
    @Override
    public Object caseDelegatingExternalCallAction(DelegatingExternalCallAction object) {
        return parentSwitch.doSwitch(SeffPackage.Literals.EXTERNAL_CALL_ACTION, object);
    }

}
