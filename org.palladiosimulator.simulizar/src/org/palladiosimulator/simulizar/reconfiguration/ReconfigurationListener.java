package org.palladiosimulator.simulizar.reconfiguration;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.palladiosimulator.simulizar.access.GlobalPCMAccess;
import org.palladiosimulator.simulizar.access.IModelAccessFactory;
import org.palladiosimulator.simulizar.access.PRMAccess;
import org.palladiosimulator.simulizar.prm.PCMModelElementMeasurement;
import org.palladiosimulator.simulizar.prm.ResourceContainerMeasurement;
import org.palladiosimulator.simulizar.prm.util.PrmSwitch;
import org.palladiosimulator.simulizar.utils.PCMModels;

/**
 * Class whose objects will listen on changes in the PCM@Runtime and trigger reconfigurations respectively.
 * 
 * @author snowball
 *
 */
public class ReconfigurationListener {

    /**
     * This class' internal logger. 
     */
    private static final Logger LOGGER = Logger.getLogger(ReconfigurationListener.class);
    
    /**
     * Change listener, which will convert selected changes in the PRM instance into reconfiguration checks.
     */
    private final Adapter prmListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            ReconfigurationListener.this.checkAndExecuteReconfigurations(notification);
        }

    };
    
    /**
     * A log listener which logs all changes in the PRM. 
     */
    private final Adapter loggerAdapter = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            LOGGER.info(notification.toString());
        }

    };
    
    /**
     * Access interface to the global PCM@Runtime model. 
     */
    private final GlobalPCMAccess pcmAccess;
    
    /**
     * Access interface to the PRM runtime model. 
     */
    private final PRMAccess prmAccess;
    
    /**
     * Set of all registered reconfigurators, i.e., objects that can change the PCM@Runtime. 
     */
    private final IReconfigurator[] reconfigurators;

    /**
     * Constructor.
     * @param modelAccessFactory Access factory for model access interfaces.
     * @param reconfigurators Set of reconfigurators which will be triggered as soon as new, interesting monitoring data
     * arrives.
     */
    public ReconfigurationListener(final IModelAccessFactory modelAccessFactory, 
            final IReconfigurator[] reconfigurators) {
        super();
        this.pcmAccess = modelAccessFactory.getGlobalPCMAccess();
        this.prmAccess = modelAccessFactory.getPRMModelAccess();
        this.reconfigurators = reconfigurators;
    }

    /**
	 * Setup all listeners to listen for their respective model changes.
	 */
    public void startListening() {
        if (LOGGER.isInfoEnabled()) {
            final PCMModels pcmModels = this.pcmAccess.getModel();
            pcmModels.getRepository().eAdapters().add(this.loggerAdapter);
            pcmModels.getResourceEnvironment().eAdapters().add(this.loggerAdapter);
            pcmModels.getSystem().eAdapters().add(this.loggerAdapter);
            pcmModels.getUsageModel().eAdapters().add(this.loggerAdapter);
            pcmModels.getAllocation().eAdapters().add(this.loggerAdapter);
        }
        this.prmAccess.getModel().eAdapters().add(this.prmListener);
    }

    /**
     * Detach all model listeners. 
     */
    public void stopListening() {
        this.prmAccess.getModel().eAdapters().remove(this.prmListener);
        if (LOGGER.isInfoEnabled()) {
            final PCMModels pcmModels = this.pcmAccess.getModel();
            pcmModels.getRepository().eAdapters().remove(this.loggerAdapter);
            pcmModels.getResourceEnvironment().eAdapters().remove(this.loggerAdapter);
            pcmModels.getSystem().eAdapters().remove(this.loggerAdapter);
            pcmModels.getUsageModel().eAdapters().remove(this.loggerAdapter);
            pcmModels.getAllocation().eAdapters().remove(this.loggerAdapter);
        }
    }

    /**
     * Method which is called on a change in the PRM. All reconfigurators are informed and can check for
     * potential reconfigurations.
     * @param notification The notification event, which describes a change in the PRM.
     */
    protected void checkAndExecuteReconfigurations(final Notification notification) {
        final EObject monitoredElement = this.getMonitoredElement(notification);
        /*
         * Value changed, adapt (start sd interpreter)
         */
        if (isNotificationNewMeasurement(monitoredElement)) {
            for (final IReconfigurator reconfigurator : this.reconfigurators) {
                reconfigurator.checkAndExecute(monitoredElement);
            }
        }
    }

    /**
     * @param monitoredElement
     * @return
     */
    private boolean isNotificationNewMeasurement(final EObject monitoredElement) {
        return monitoredElement != null;
    }

    /**
     * Visitor singleton which is used to query the monitored PCM object from a PRM notification
     * (which is a change in a {@link PCMModelElementMeasurement}).
     */
    private static final PrmSwitch<EObject> MONITORED_ELEMENT_RETRIEVER = new PrmSwitch<EObject>() {

        @Override
        public EObject casePCMModelElementMeasurement(final PCMModelElementMeasurement object) {
            return object.getPcmModelElement();
        }

        @Override
        public EObject caseResourceContainerMeasurement(final ResourceContainerMeasurement object) {
            return object.getPcmModelElement();
        }

    };
    
    /**
     * Retrieve the monitored PCM element from the PRM change event.
     * @param notification The PRM change event.
     * @return The PCM element whose monitoring triggered the change event.
     */
    protected EObject getMonitoredElement(final Notification notification) {
        switch (notification.getEventType()) {
        case Notification.ADD:
            return MONITORED_ELEMENT_RETRIEVER.doSwitch((EObject) notification.getNewValue());
        case Notification.REMOVE:
            return null;
        case Notification.REMOVING_ADAPTER:
            return null;
        default:
            LOGGER.warn("Unsupported PRM Notification: " + notification);
            return null;
        }
    }

}
