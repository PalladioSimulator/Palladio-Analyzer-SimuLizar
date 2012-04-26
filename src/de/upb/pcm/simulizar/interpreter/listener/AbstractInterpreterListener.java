/**
 * 
 */
package de.upb.pcm.simulizar.interpreter.listener;

import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

/**
 * @author snowball
 *
 */
public abstract class AbstractInterpreterListener implements IInterpreterListener {

	/**
	 * 
	 */
	public AbstractInterpreterListener() {
		super();
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#beginUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#endUsageScenarioInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event) {
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#beginEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
	}

	/* (non-Javadoc)
	 * @see de.upb.pcm.interpreter.interpreter.listener.IInterpreterListener#endEntryLevelSystemCallInterpretation(de.upb.pcm.interpreter.interpreter.listener.ModelElementPassedEvent)
	 */
	@Override
	public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event) {
	}

}
