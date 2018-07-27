package org.palladiosimulator.simulizar.action.interpreter.notifications;

import java.util.Objects;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationBehavior;

/**
 * Notification class to indicate that an {@link AdaptationBehavior} was executed successfully.
 */
public class AdaptationBehaviorExecutedNotification extends NotificationImpl {
    public static final int ADAPTATION_BEHAVIOR_EXECUTED_EVENT_TYPE = 42;

    /**
     * Initializes a new instance of the {@link AdaptationBehaviorExecutedNotification} class.
     * 
     * @param executedAction
     *            The {@link AdaptationBehavior} that terminated successfully.
     * @throws NullPointerException
     *             In case {@code executedAction} is {@code null}.
     */
    public AdaptationBehaviorExecutedNotification(AdaptationBehavior executedAction) {
        super(ADAPTATION_BEHAVIOR_EXECUTED_EVENT_TYPE, null,
                Objects.requireNonNull(executedAction, "The given adaptation behavior must not be null"));
    }

    /**
     * Gets action that was executed.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNotifier()}.
     * 
     * @return The {@code AdaptationBehavior} that was executed.
     * @see #getNotifier()
     */
    @Override
    public AdaptationBehavior getNewValue() {
        // typesafe as passed in sole ctor
        return (AdaptationBehavior) super.newValue;
    }

    /**
     * Gets action that is associated with this notification.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNewValue()}.
     * 
     * @return The {@code AdaptationBehavior} that was executed.
     * @see #getNewValue()()
     */
    @Override
    public AdaptationBehavior getNotifier() {
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
