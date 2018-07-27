package org.palladiosimulator.simulizar.action.interpreter.notifications;

import java.util.Objects;

import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.palladiosimulator.simulizar.action.core.AdaptationStep;

/**
 * Notification class to indicate that an {@link AdaptationStep} was executed successfully.
 */
public class AdaptationStepExecutedNotification extends NotificationImpl {
    public static final int ADAPTATION_STEP_EXECUTED_EVENT_TYPE = 43;

    /**
     * Initializes a new instance of the {@link AdaptationStepExecutedNotification} class.
     * 
     * @param executedAdaptationStep
     *            The {@link AdaptationStep} that terminated successfully.
     * @throws NullPointerException
     *             In case {@code executedAdaptationStep} is {@code null}.
     */
    public AdaptationStepExecutedNotification(AdaptationStep executedAdaptationStep) {
        super(ADAPTATION_STEP_EXECUTED_EVENT_TYPE, null,
                Objects.requireNonNull(executedAdaptationStep, "Given adaptation step must not be null."));
    }

    /**
     * Gets adaptation step that was executed.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNotifier()}.
     * 
     * @return The {@code AdaptationStep} that was executed.
     * @see #getNotifier()
     */
    @Override
    public AdaptationStep getNewValue() {
        // typesafe as passed in sole ctor
        return (AdaptationStep) super.newValue;
    }

    /**
     * Gets adaptation step that is associated with this notification.<br>
     * <b>Note:</b> Calling this method is is equivalent to calling {@link #getNewValue()}.
     * 
     * @return The {@code AdaptationStep} that was executed.
     * @see #getNewValue()()
     */
    @Override
    public AdaptationStep getNotifier() {
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
