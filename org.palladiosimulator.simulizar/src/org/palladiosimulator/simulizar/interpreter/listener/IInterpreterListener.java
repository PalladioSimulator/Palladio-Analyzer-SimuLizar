package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.repository.OperationSignature;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;

public interface IInterpreterListener {

    public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void beginExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);

    public void endExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);

    public <T extends EObject> void beginUnknownElementInterpretation(ModelElementPassedEvent<T> event);

    public <T extends EObject> void endUnknownElementInterpretation(ModelElementPassedEvent<T> event);

    public void beginSystemOperationCallInterpretation(ModelElementPassedEvent<OperationSignature> event);

    public void endSystemOperationCallInterpretation(ModelElementPassedEvent<OperationSignature> event);
    
    public <R extends ProvidedRole, S extends Signature> void beginAssemblyProvidedOperationCallInterpretation(AssemblyProvidedOperationPassedEvent<R, S> event);
    
    public <R extends ProvidedRole, S extends Signature> void endAssemblyProvidedOperationCallInterpretation(AssemblyProvidedOperationPassedEvent<R, S> event);
}
