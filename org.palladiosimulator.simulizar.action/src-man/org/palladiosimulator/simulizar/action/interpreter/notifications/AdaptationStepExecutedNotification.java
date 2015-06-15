package org.palladiosimulator.simulizar.action.interpreter.notifications;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;

public class AdaptationStepExecutedNotification extends NotificationImpl {
    public static final int ADAPTATION_STEP_EXECUTED_EVENT_TYPE = 43;
    
    public AdaptationStepExecutedNotification(AdaptationStep executedAdaptationStep) {
        super(ADAPTATION_STEP_EXECUTED_EVENT_TYPE, null, executedAdaptationStep);
    }
    
    @Override
    public AdaptationStep getNewValue() {
        //typesafe as passed in sole ctor
        return (AdaptationStep) super.newValue;
    }
    
    @Override
    public AdaptationStep getNotifier() {
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
