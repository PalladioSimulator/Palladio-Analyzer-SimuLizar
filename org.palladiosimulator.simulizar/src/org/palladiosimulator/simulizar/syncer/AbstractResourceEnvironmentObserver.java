package org.palladiosimulator.simulizar.syncer;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.mdsdprofiles.notifier.MDSDProfilesNotifier;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public abstract class AbstractResourceEnvironmentObserver extends AbstractModelObserver<ResourceEnvironment> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractResourceEnvironmentObserver.class.getName());

    @Override
    protected void synchronizeSimulationEntities(final Notification notification) {

        switch (notification.getEventType()) {
        case Notification.ADD:
            this.add(notification);
            break;
        case Notification.REMOVE:
            this.remove(notification);
            break;
        case Notification.SET:
            this.set(notification);
            break;
        case Notification.MOVE:
            this.move(notification);
            break;
        case Notification.ADD_MANY:
            this.addMany(notification);
            break;
        case Notification.EVENT_TYPE_COUNT:
            this.eventTypeCount(notification);
            break;
        case Notification.NO_FEATURE_ID:
            this.noFeatureID(notification);
            break;
        case Notification.REMOVE_MANY:
            this.removeMany(notification);
            break;
        case Notification.REMOVING_ADAPTER:
            this.removingAdapter(notification);
            break;
        case Notification.RESOLVE:
            this.resolve(notification);
            break;
        case Notification.UNSET:
            this.unset(notification);
            break;
        case MDSDProfilesNotifier.SET_TAGGED_VALUE:
            this.setTaggedValue(notification);
            break;
        default:
            throw new RuntimeException("Ignoring notification with event type \"" + notification.getEventType() + "\"");
        }
    }

    protected void setTaggedValue(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void unset(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void resolve(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void removingAdapter(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void removeMany(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void noFeatureID(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void eventTypeCount(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void addMany(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void add(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void remove(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void set(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void move(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void logDebugInfo(final Notification notification) {
        if (LOGGER.isDebugEnabled()) {
            final StringBuilder message = new StringBuilder();
            message.append("Ignoring notification with event type \"");
            message.append(notification.getEventType());
            message.append("\"");
            LOGGER.debug(message.toString());
        }
    }

    @Override
    public void initialize(final SimuLizarRuntimeState runtimeState) {
        super.initialize(runtimeState.getModelAccess().getGlobalPCMModel().getAllocation()
                .getTargetResourceEnvironment_Allocation(), Objects.requireNonNull(runtimeState));
    }
}
