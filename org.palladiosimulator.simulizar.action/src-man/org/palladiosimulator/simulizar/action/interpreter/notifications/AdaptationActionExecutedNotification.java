package org.palladiosimulator.simulizar.action.interpreter.notifications;

import java.util.Objects;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationAction;

/**
 * Notification class to indicate that an {@link AdaptationAction} was executed successfully.
 */
public class AdaptationActionExecutedNotification extends NotificationImpl {
    public static final int ADAPTATION_ACTION_EXECUTED_EVENT_TYPE = 43;

    /**
     * Initializes a new instance of the {@link AdaptationActionExecutedNotification} class.
     * 
     * @param executedAdaptationStep
     *            The {@link AdaptationStep} that terminated successfully.
     * @throws NullPointerException
     *             In case {@code executedAdaptationStep} is {@code null}.
     */
    public AdaptationActionExecutedNotification(AdaptationAction executedAdaptationStep) {
        super(ADAPTATION_ACTION_EXECUTED_EVENT_TYPE, null,
                Objects.requireNonNull(executedAdaptationStep, "Given adaptation action must not be null."));
    }

    /**
     * Gets adaptation step that was executed.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNotifier()}.
     * 
     * @return The {@code AdaptationAction} that was executed.
     * @see #getNotifier()
     */
    @Override
    public AdaptationAction getNewValue() {
        // typesafe as passed in sole ctor
        return (AdaptationAction) super.newValue;
    }

    /**
     * Gets adaptation step that is associated with this notification.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNewValue()}.
     * 
     * @return The {@code AdaptationAction} that was executed.
     * @see #getNewValue()()
     */
    @Override
    public AdaptationAction getNotifier() {
        return this.getNewValue();
    }

    /**
     * {@inheritDoc}
     * 
     * @return This method always returns {@code true}.
     */
    @Override
    public boolean isTouch() {
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @return This method always returns {@code false}.
     */
    @Override
    public boolean isReset() {
        return false;

    }
}
