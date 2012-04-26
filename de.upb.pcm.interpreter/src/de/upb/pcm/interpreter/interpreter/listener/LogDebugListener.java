/**
 * 
 */
package de.upb.pcm.interpreter.interpreter.listener;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

/**
 * @author snowball
 *
 */
public class LogDebugListener extends AbstractInterpreterListener {
	private final static Logger logger = Logger.getLogger(LogDebugListener.class);
	
	/**
	 * 
	 */
	public LogDebugListener() {
		super();
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#beginUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
		logEvent(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#endUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
		logEvent(event);
	}

	
	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#beginEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
		logEvent(event);
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.AbstractInterpreterListener#endEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
		logEvent(event);
	}

	private <T extends EObject> void logEvent(ModelElementPassedEvent<T> event) {
		if (logger.isDebugEnabled()) {
			StringBuilder msgBuilder = new StringBuilder();
			switch (event.getEventType()) {
			case BEGIN:
				msgBuilder.append("Starting to interpret ");
				break;
			case END:
				msgBuilder.append("Finished interpreting ");
			}
			msgBuilder.append(event.getModelElement().eClass().getName());
			msgBuilder.append(" in Simuation Thread \"");
			msgBuilder.append(event.getThread().getId());
			msgBuilder.append("\" at simulation time ");
			msgBuilder.append(event.getPassageTime());
			logger.debug(msgBuilder.toString());
		}
	}

}
