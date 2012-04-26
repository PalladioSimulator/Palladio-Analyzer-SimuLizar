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
	private final Adapter prmListener = new EContentAdapter(){
	
			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				triggerReconfigurations(notification);
			}
			
	};
	private Adapter loggerAdapter = new EContentAdapter() {

		@Override
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			InterpreterLogger.info(logger,notification.toString());
		}
		
	};
	private final GlobalPCMAccess pcmAccess;
	private final PRMAccess prmAccess;
	private final IReconfigurator[] reconfigurators;
	
	public ReconfigurationListener(
			final IModelAccessFactory modelAccessFactory,
			final IReconfigurator[] reconfigurators) {
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
			final PCMModels pcmModels = pcmAccess.getModel();
			pcmModels.getRepository().eAdapters().add(loggerAdapter);
			pcmModels.getResourceEnvironment().eAdapters().add(loggerAdapter);
			pcmModels.getSystem().eAdapters().add(loggerAdapter);
			pcmModels.getUsageModel().eAdapters().add(loggerAdapter);
			pcmModels.getAllocation().eAdapters().add(loggerAdapter);
		}
		this.prmAccess.getModel().eAdapters().add(prmListener);
	}

	public void stopListening() {
		this.prmAccess.getModel().eAdapters().add(prmListener);
		if (logger.isInfoEnabled()) {
			final PCMModels pcmModels = pcmAccess.getModel();
			pcmModels.getRepository().eAdapters().remove(loggerAdapter);
			pcmModels.getResourceEnvironment().eAdapters().remove(loggerAdapter);
			pcmModels.getSystem().eAdapters().remove(loggerAdapter);
			pcmModels.getUsageModel().eAdapters().remove(loggerAdapter);
			pcmModels.getAllocation().eAdapters().remove(loggerAdapter);		
		}
	}

	protected void triggerReconfigurations(Notification notification) {
		EObject monitoredElement = getMonitoredElement(notification);
		/*
		 * Value changed, adapt (start sd interpreter)
		 */
		if (monitoredElement != null) {
			for (IReconfigurator reconfigurator : reconfigurators) {
				reconfigurator.runReconfiguration(monitoredElement);
			}
		}
	}
	
	protected EObject getMonitoredElement(Notification notification) {
		switch (notification.getEventType()) {
		case Notification.ADD:
			return new PrmSwitch<EObject>() {
	
				@Override
				public EObject casePCMModelElementMeasurement(
						PCMModelElementMeasurement object) {
					return object.getPcmModelElement();
				}
	
				@Override
				public EObject caseResourceContainerMeasurement(
						ResourceContainerMeasurement object) {
					return object.getPcmModelElement();
				}
			
			}.doSwitch((EObject) notification.getNewValue());
		case Notification.REMOVE:
			return null;
		case Notification.REMOVING_ADAPTER:
			return null;
		default:
			InterpreterLogger.info(logger,"Unsupported PRM Notification: "+notification);
			return null;
		}
	}

}
