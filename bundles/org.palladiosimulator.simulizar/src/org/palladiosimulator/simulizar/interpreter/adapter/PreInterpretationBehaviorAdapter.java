package org.palladiosimulator.simulizar.interpreter.adapter;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;

public class PreInterpretationBehaviorAdapter implements Adapter {
    
    private PreInterpretationBehavior behavior;
    private Notifier target;
    
    public PreInterpretationBehaviorAdapter (PreInterpretationBehavior behavior, Notifier target) {
        this.behavior = behavior;
        this.target = target;
    }
    
    public InterpreterResult executeBehavior() {
        return behavior.execute();
    }
    
    
    @Override
    public void notifyChanged(Notification notification) {
        // TODO Auto-generated method stub
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
