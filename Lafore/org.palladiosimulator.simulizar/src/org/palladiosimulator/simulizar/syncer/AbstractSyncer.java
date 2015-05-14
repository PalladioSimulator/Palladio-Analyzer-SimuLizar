package org.palladiosimulator.simulizar.syncer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;

public abstract class AbstractSyncer<T extends EObject> implements IModelSyncer {

    private static final Logger LOGGER = Logger.getLogger(AbstractSyncer.class);

    protected final SimuLizarRuntimeState runtimeModel;
    protected final T model;

    private final EContentAdapter adapter;

    /**
     * @param simuComModel
     */
    protected AbstractSyncer(final SimuLizarRuntimeState simuComModel, final T model) {
        super();
        this.runtimeModel = simuComModel;
        this.model = model;
        this.adapter = new EContentAdapter() {

            @Override
            public void notifyChanged(final Notification notification) {
                super.notifyChanged(notification);
                if (!(notification.getEventType() == Notification.REMOVING_ADAPTER || notification.getEventType() == Notification.RESOLVE)) {
                    LOGGER.info(model.eClass().getName() + " changed by reconfiguration - Resync simulation entities: "
                            + notification);
                    synchronizeSimulationEntities(notification);
                }
            }

        };
        model.eAdapters().add(adapter);
    }

    protected abstract void synchronizeSimulationEntities(final Notification notification);

    /*
     * (non-Javadoc)
     * 
     * @see org.palladiosimulator.simulizar.syncer.IModelSyncer#stopSyncer()
     */
    @Override
    public void stopSyncer() {
        model.eAdapters().remove(adapter);
    }

}
