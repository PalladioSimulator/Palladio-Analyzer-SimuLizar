package org.palladiosimulator.simulizar.interpreter.adapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResultMerger;

public class PreInterpretationBehaviorAdapter implements Adapter {
    
    private List<PreInterpretationBehavior> behavior;
    private Notifier target;
    private final InterpreterResultMerger merger;
    
    @Inject
    public PreInterpretationBehaviorAdapter (InterpreterResultMerger merger) {
        this.merger = merger;
        this.behavior = new ArrayList<PreInterpretationBehavior>();
    }
    
    public InterpreterResult executeBehavior() {
        InterpreterResult result = InterpreterResult.OK;
        for (PreInterpretationBehavior b : behavior) {
            result = merger.merge(result, b.execute());
        }
        return result;
    }
    
    public void addBehavior(PreInterpretationBehavior b) {
        behavior.add(b);
    }
    
    public void removeAllBehaviors() {
        behavior.clear();
    }
    
    @Override
    public void notifyChanged(Notification notification) {
    }

    @Override
    public Notifier getTarget() {
        return this.target;
    }

    @Override
    public void setTarget(Notifier newTarget) {
        this.target = newTarget;
    }

    @Override
    public boolean isAdapterForType(Object type) {
        return type instanceof PreInterpretationBehaviorAdapter;
    }

}
