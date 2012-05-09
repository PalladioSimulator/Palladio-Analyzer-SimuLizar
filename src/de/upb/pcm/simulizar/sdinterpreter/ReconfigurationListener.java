package de.upb.pcm.simulizar.sdinterpreter;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.upb.pcm.prm.PCMModelElementMeasurement;
import de.upb.pcm.prm.ResourceContainerMeasurement;
import de.upb.pcm.prm.util.PrmSwitch;
import de.upb.pcm.simulizar.access.GlobalPCMAccess;
import de.upb.pcm.simulizar.access.IModelAccessFactory;
import de.upb.pcm.simulizar.access.PRMAccess;
import de.upb.pcm.simulizar.utils.InterpreterLogger;
import de.upb.pcm.simulizar.utils.PCMModels;

public class ReconfigurationListener {

    private static final Logger logger = Logger.getLogger(ReconfigurationListener.class);
    private final Adapter prmListener = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            ReconfigurationListener.this.triggerReconfigurations(notification);
        }

    };
    private final Adapter loggerAdapter = new EContentAdapter() {

        @Override
        public void notifyChanged(final Notification notification) {
            super.notifyChanged(notification);
            InterpreterLogger.info(logger, notification.toString());
        }

    };
    private final GlobalPCMAccess pcmAccess;
    private final PRMAccess prmAccess;
    private final IReconfigurator[] reconfigurators;

    public ReconfigurationListener(final IModelAccessFactory modelAccessFactory, final IReconfigurator[] reconfigurators) {
        super();
        this.pcmAccess = modelAccessFactory.getGlobalPCMAccess();
        this.prmAccess = modelAccessFactory.getPRMModelAccess();
        this.reconfigurators = reconfigurators;
    }

    /**
	 * 
	 */
    public void startListening() {
        if (logger.isInfoEnabled()) {
            final PCMModels pcmModels = this.pcmAccess.getModel();
            pcmModels.getRepository().eAdapters().add(this.loggerAdapter);
            pcmModels.getResourceEnvironment().eAdapters().add(this.loggerAdapter);
            pcmModels.getSystem().eAdapters().add(this.loggerAdapter);
            pcmModels.getUsageModel().eAdapters().add(this.loggerAdapter);
            pcmModels.getAllocation().eAdapters().add(this.loggerAdapter);
        }
        this.prmAccess.getModel().eAdapters().add(this.prmListener);
    }

    public void stopListening() {
        this.prmAccess.getModel().eAdapters().add(this.prmListener);
        if (logger.isInfoEnabled()) {
            final PCMModels pcmModels = this.pcmAccess.getModel();
            pcmModels.getRepository().eAdapters().remove(this.loggerAdapter);
            pcmModels.getResourceEnvironment().eAdapters().remove(this.loggerAdapter);
            pcmModels.getSystem().eAdapters().remove(this.loggerAdapter);
            pcmModels.getUsageModel().eAdapters().remove(this.loggerAdapter);
            pcmModels.getAllocation().eAdapters().remove(this.loggerAdapter);
        }
    }

    protected void triggerReconfigurations(final Notification notification) {
        final EObject monitoredElement = this.getMonitoredElement(notification);
        /*
         * Value changed, adapt (start sd interpreter)
         */
        if (monitoredElement != null) {
            for (final IReconfigurator reconfigurator : this.reconfigurators) {
                reconfigurator.runReconfiguration(monitoredElement);
            }
        }
    }

    protected EObject getMonitoredElement(final Notification notification) {
        switch (notification.getEventType()) {
        case Notification.ADD:
            return new PrmSwitch<EObject>() {

                @Override
                public EObject casePCMModelElementMeasurement(final PCMModelElementMeasurement object) {
                    return object.getPcmModelElement();
                }

                @Override
                public EObject caseResourceContainerMeasurement(final ResourceContainerMeasurement object) {
                    return object.getPcmModelElement();
                }

            }.doSwitch((EObject) notification.getNewValue());
        case Notification.REMOVE:
            return null;
        case Notification.REMOVING_ADAPTER:
            return null;
        default:
            InterpreterLogger.info(logger, "Unsupported PRM Notification: " + notification);
            return null;
        }
    }

}
