package org.palladiosimulator.simulizar.interpreter.listener;

import de.uka.ipd.sdq.pcm.seff.ExternalCallAction;
import de.uka.ipd.sdq.pcm.usagemodel.EntryLevelSystemCall;
import de.uka.ipd.sdq.pcm.usagemodel.UsageScenario;

public interface IInterpreterListener {
    public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void beginExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);

    public void endExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);
}
