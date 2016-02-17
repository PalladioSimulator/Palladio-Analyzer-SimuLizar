package org.palladiosimulator.simulizar.syncer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public abstract class AbstractModelObserver<T extends EObject> implements IModelObserver {

    protected static final Logger LOGGER = Logger.getLogger(AbstractModelObserver.class);

    protected SimuLizarRuntimeState runtimeModel;
    protected T model;

    private EContentAdapter adapter;

    /**
     * @param simuComModel
     */
    protected AbstractModelObserver() {
        super();
    }

    public void initialize(final T model, final SimuLizarRuntimeState runtimeState) {
        this.initialize(model);
        this.runtimeModel = runtimeState;
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

                    try {
                        AbstractModelObserver.this.synchronizeSimulationEntities(notification);
                    } catch (final Exception e) {
                        LOGGER.error("Sync Exception: " + e);
                    }
                }
            }

        };
        model.eAdapters().add(this.adapter);
    }

    protected abstract void synchronizeSimulationEntities(final Notification notification);

    @Override
    public void unregister() {
        this.model.eAdapters().remove(this.adapter);
    }

}
