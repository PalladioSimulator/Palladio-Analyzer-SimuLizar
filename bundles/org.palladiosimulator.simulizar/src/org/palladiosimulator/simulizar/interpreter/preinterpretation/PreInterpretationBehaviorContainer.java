package org.palladiosimulator.simulizar.interpreter.preinterpretation;

import java.util.ArrayList;
import java.util.List;

import jakarta.inject.Inject;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;

import com.google.common.collect.ImmutableList;

/**
 * Container to collect PreInterpretationBehaviors and execute them all together.
 * 
 * @author Jonas Lehmann
 *
 */
public class PreInterpretationBehaviorContainer {

    private List<PreInterpretationBehavior> behaviors;
    private final InterpreterResultMerger merger;

    @Inject
    public PreInterpretationBehaviorContainer(InterpreterResultMerger merger) {
        this.merger = merger;
        this.behaviors = new ArrayList<PreInterpretationBehavior>();
    }

    public InterpreterResult executeBehaviors(InterpreterDefaultContext context) {
        InterpreterResult result = InterpreterResult.OK;
        var localBehaviors = ImmutableList.copyOf(behaviors);
        for (PreInterpretationBehavior b : localBehaviors) {
            result = merger.merge(result, b.execute(context));
        }
        return result;
    }

    public void addBehavior(PreInterpretationBehavior b) {
        if (!behaviors.contains(b)) {
            // no duplicate behaviors allowed, e.g. 2 SWCrashes
            // if you want to add 2 delay behaviors, they should be 2 different objects
            // (!delay1.equals(delay2))
            // this makes it possible to remove the delay behaviors separately if necessary.
            behaviors.add(b);
        }
    }

    public void removeBehavior(PreInterpretationBehavior b) {
        behaviors.remove(b);
    }

    public void removeAllBehaviors() {
        behaviors.clear();
    }
}
