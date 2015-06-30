package org.palladiosimulator.simulizar.reconfiguration.storydiagram;

import java.io.OutputStream;
import java.io.PrintStream;

import de.mdelab.sdm.interpreter.core.facade.IExpressionFacade;
import de.mdelab.sdm.interpreter.core.facade.IStoryPatternFacade;
import de.mdelab.sdm.interpreter.core.facade.IStoryPatternObjectFacade;
import de.mdelab.sdm.interpreter.core.facade.MetamodelFacadeFactory;
import de.mdelab.sdm.interpreter.core.notifications.InterpreterNotification;
import de.mdelab.sdm.interpreter.core.notifications.NotificationReceiver;
import de.mdelab.sdm.interpreter.core.notifications.StoryPatternMatchingFailedNotification;
import de.mdelab.sdm.interpreter.core.notifications.StoryPatternMatchingSuccessfulNotification;

/**
 * NotificationReceiver that receives notifications of the StoryDiagram Interpreter and provides
 * information about whether a reconfiguration rule was successfully applied or not.
 * 
 * @author Matthias Becker
 *
 * @param <Activity>
 * @param <ActivityNode>
 * @param <ActivityEdge>
 * @param <StoryPattern>
 * @param <StoryPatternObject>
 * @param <StoryPatternLink>
 * @param <Classifier>
 * @param <Feature>
 * @param <Expression>
 */
public class SDReconfigurationNotificationReceiver<Activity, ActivityNode, ActivityEdge, StoryPattern, StoryPatternObject, StoryPatternLink, Classifier, Feature, Expression>
        implements NotificationReceiver<Classifier> {

    private IExpressionFacade<Expression> expressionFacade;
    private IStoryPatternObjectFacade<StoryPatternObject, StoryPatternLink, Classifier, Feature, Expression> spoFacade;
    private IStoryPatternFacade<StoryPattern, StoryPatternObject, StoryPatternLink, Expression> spFacade;
    private boolean success;

    public SDReconfigurationNotificationReceiver(
            MetamodelFacadeFactory<Activity, ActivityNode, ActivityEdge, StoryPattern, StoryPatternObject, StoryPatternLink, Classifier, Feature, Expression> facadeFactory,
            OutputStream outputStream) {
        assert facadeFactory != null;

        if (outputStream == null) {
        } else {
            new PrintStream(outputStream);
        }

        this.spoFacade = facadeFactory.getStoryPatternObjectFacade();
        this.spFacade = facadeFactory.getStoryPatternFacade();
        this.expressionFacade = facadeFactory.getExpressionFacade();
        this.success = true;
    }

    public SDReconfigurationNotificationReceiver(
            MetamodelFacadeFactory<Activity, ActivityNode, ActivityEdge, StoryPattern, StoryPatternObject, StoryPatternLink, Classifier, Feature, Expression> facadeFactory) {
        this(facadeFactory, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void notifyChanged(InterpreterNotification<Classifier> notification) {
        switch (notification.getNotificationType()) {
        case STORY_PATTERN_MATCHING_FAILED:
            this.storyPatternMatchingFailed((StoryPatternMatchingFailedNotification<StoryPattern, Classifier>) notification);
            break;
        case STORY_PATTERN_MATCHING_SUCCESSFUL:
            this.storyPatternMatchingSuccessful((StoryPatternMatchingSuccessfulNotification<StoryPattern, Classifier>) notification);
            break;
        default:
            break;

        }

    }

    public void storyPatternMatchingSuccessful(
            StoryPatternMatchingSuccessfulNotification<StoryPattern, Classifier> notification) {
        this.success &= true;
    }

    public void storyPatternMatchingFailed(StoryPatternMatchingFailedNotification<StoryPattern, Classifier> notification) {
        this.success &= false;
    }

    /**
     * Provides the success state of a StoryDiagram reconfiguration rule application.
     * 
     * @return Returns true if the StoryDiagram reconfiguration rule was successfully applied, i.e.,
     *         all StoryPatterns within the StoryDiagram were matched successfully.
     */
    public boolean applicationSuccessful() {
        return this.success;
    }

    /**
     * Call reset before executing a new reconfiguration rule. Resets the success state.
     */
    public void reset() {
        this.success = true;
    }

}
