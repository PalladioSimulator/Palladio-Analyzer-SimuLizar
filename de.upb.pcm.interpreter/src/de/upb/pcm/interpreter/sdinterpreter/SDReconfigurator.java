package de.upb.pcm.interpreter.sdinterpreter;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.access.PRMAccess;
import de.upb.pcm.interpreter.access.SDAccess;
import de.upb.pcm.interpreter.utils.InterpreterLogger;
import de.upb.pcm.prm.PCMModelElementMeasurement;
import de.upb.pcm.prm.ResourceContainerMeasurement;
import de.upb.pcm.prm.util.PrmSwitch;

public class SDReconfigurator extends AbstractReconfigurator {

	private final static Logger logger = Logger.getLogger(SDReconfigurator.class);
	
	private final PRMAccess prmAccess;
	
	private final SDAccess sdAccess;
	
	private final SDExecutor sdExecutor;
	
	public SDReconfigurator(final IModelAccessFactory modelAccessFactory) {
		super(modelAccessFactory);
		this.prmAccess = modelAccessFactory.getPRMModelAccess();
		this.sdAccess = modelAccessFactory.getSDAccess();
	    this.sdExecutor = new SDExecutor(modelAccessFactory);

		this.prmAccess.getModel().eAdapters().add(new EContentAdapter(){

			@Override
			public void notifyChanged(Notification notification) {
				super.notifyChanged(notification);
				triggerReconfigurations(notification);
			}
			
		});
	}
	
	protected void triggerReconfigurations(Notification notification) {
		EObject monitoredElement = getMonitoredElement(notification);
		/*
		 * Value changed, adapt (start sd interpreter)
		 */
		if (monitoredElement != null && this.sdAccess.sdModelsExist()) {
			InterpreterLogger.debug(logger, "Checking reconfiguration rules due to PRM change");
			boolean result = this.sdExecutor.executeActivities(monitoredElement);
			InterpreterLogger.debug(logger, result ? "Reconfigured system by a matching rule" : "No reconfiguration rule was executed, all conditions were false");
		}
	}

	private EObject getMonitoredElement(Notification notification) {
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
