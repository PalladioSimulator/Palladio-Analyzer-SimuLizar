package de.upb.pcm.interpreter.sdinterpreter;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import de.upb.pcm.interpreter.access.GlobalPCMAccess;
import de.upb.pcm.interpreter.access.IModelAccessFactory;
import de.upb.pcm.interpreter.utils.InterpreterLogger;

public class AbstractReconfigurator {
	
	private static final Logger logger = Logger.getLogger(AbstractReconfigurator.class);
	private EContentAdapter loggerAdapter;
	
	public AbstractReconfigurator(IModelAccessFactory modelAccessFactory) {
		super();
		if (logger.isInfoEnabled()) {
			this.loggerAdapter = new EContentAdapter() {

				@Override
				public void notifyChanged(Notification notification) {
					super.notifyChanged(notification);
					InterpreterLogger.info(logger,notification.toString());
				}
				
			};
			GlobalPCMAccess pcmAccess = modelAccessFactory.getGlobalPCMAccess();
			pcmAccess.getModel().getRepository().eAdapters().add(loggerAdapter);
			pcmAccess.getModel().getResourceEnvironment().eAdapters().add(loggerAdapter);
			pcmAccess.getModel().getSystem().eAdapters().add(loggerAdapter);
			pcmAccess.getModel().getUsageModel().eAdapters().add(loggerAdapter);
			pcmAccess.getModel().getAllocation().eAdapters().add(loggerAdapter);
		}
	}

}
