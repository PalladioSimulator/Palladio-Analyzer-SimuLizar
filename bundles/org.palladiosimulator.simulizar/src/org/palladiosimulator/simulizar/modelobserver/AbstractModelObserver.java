package org.palladiosimulator.simulizar.modelobserver;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.mdsdprofiles.notifier.MDSDProfilesNotifier;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;

public abstract class AbstractModelObserver<T extends EObject> implements IModelObserver {

    protected static final Logger LOGGER = Logger.getLogger(AbstractModelObserver.class);

    protected AbstractSimuLizarRuntimeState runtimeModel;
    protected T model;

    private EContentAdapter adapter;

    protected AbstractModelObserver() {
        super();
    }

    public void initialize(final T model, final AbstractSimuLizarRuntimeState runtimeState) {
        if (model != null) {
            initialize(model);
        } else {
            LOGGER.info(this.getClass().getName() + " cannot observe model, as none has been specified");
        }
        this.runtimeModel = Objects.requireNonNull(runtimeState);
    }

    private void initialize(final T model) {
        this.model = model;
        this.adapter = new EContentAdapter() {

            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
                if (!(notification.getEventType() == Notification.REMOVING_ADAPTER
                        || notification.getEventType() == Notification.RESOLVE)) {
                    LOGGER.debug(model.eClass().getName() + " changed by reconfiguration - Resync simulation entities: "
                            + notification);

                    AbstractModelObserver.this.notifyModelObservers(notification);
                }
            }

        };
        model.eAdapters().add(this.adapter);
    }

    @Override
    public void unregister() {
        if (this.adapter != null && this.model.eAdapters().contains(this.adapter)) {
            this.model.eAdapters().remove(this.adapter);
        }
    }

    private void notifyModelObservers(final Notification notification) {

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
        case MDSDProfilesNotifier.APPLY_PROFILE:
            this.applyProfile(notification);
            break;
        case MDSDProfilesNotifier.UNAPPLY_PROFILE:
            this.unapplyProfile(notification);
            break;
        case MDSDProfilesNotifier.APPLY_STEREOTYPE:
            this.applyStereotype(notification);
            break;
        case MDSDProfilesNotifier.UNAPPLY_STEREOTYPE:
            this.unapplyStereotype(notification);
            break;
        case MDSDProfilesNotifier.SET_TAGGED_VALUE:
            this.setTaggedValue(notification);
            break;
        default:
            throw new RuntimeException("Ignoring notification with event type \"" + notification.getEventType() + "\"");
        }
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

    private void unapplyStereotype(final Notification notification) {
        logDebugInfo(notification);
    }

    private void applyStereotype(final Notification notification) {
        logDebugInfo(notification);
    }

    private void unapplyProfile(final Notification notification) {
        logDebugInfo(notification);
    }

    private void applyProfile(final Notification notification) {
        logDebugInfo(notification);
    }

    protected void setTaggedValue(final Notification notification) {
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

}
