package org.palladiosimulator.simulizar.action.interpreter.notifications;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.palladiosimulator.simulizar.action.core.Action;

public class ActionExecutedNotification extends NotificationImpl {
    public static final int ACTION_EXECUTED_EVENT_TYPE = 42;
    
    public ActionExecutedNotification(Action executedAction) {
        super(ACTION_EXECUTED_EVENT_TYPE, null, executedAction);
    }
    
    @Override
    public Action getNewValue() {
        //typesafe as passed in sole ctor
        return (Action) super.newValue;
    }
    
    @Override
    public Action getNotifier() {
        return this.getNewValue();
    }
    
    @Override
    public boolean isTouch() {
        return true;
    }
    
    @Override
    public boolean isReset() {
        return false;
        
    }
}
