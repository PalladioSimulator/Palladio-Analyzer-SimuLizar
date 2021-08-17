package org.palladiosimulator.simulizar.interpreter.listener;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.repository.ProvidedRole;
import org.palladiosimulator.pcm.repository.Signature;
import org.palladiosimulator.pcm.seff.ExternalCallAction;
import org.palladiosimulator.pcm.usagemodel.EntryLevelSystemCall;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.di.extension.Extension;

public interface IInterpreterListener extends Extension {
    
    default public void initialize() {
        
    }
    
    default void cleanup() {
        
    }

    public void beginUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void endUsageScenarioInterpretation(ModelElementPassedEvent<UsageScenario> event);

    public void beginEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void endEntryLevelSystemCallInterpretation(ModelElementPassedEvent<EntryLevelSystemCall> event);

    public void beginExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);

    public void endExternalCallInterpretation(RDSEFFElementPassedEvent<ExternalCallAction> event);

    public <T extends EObject> void beginUnknownElementInterpretation(ModelElementPassedEvent<T> event);

    public <T extends EObject> void endUnknownElementInterpretation(ModelElementPassedEvent<T> event);

    public <T extends org.palladiosimulator.pcm.system.System, R extends ProvidedRole, S extends Signature> void beginSystemOperationCallInterpretation(SystemOperationPassedEvent<T, R, S> ev);

    public <T extends org.palladiosimulator.pcm.system.System, R extends ProvidedRole, S extends Signature> void endSystemOperationCallInterpretation(SystemOperationPassedEvent<T, R, S> ev);
    
    public <T extends AssemblyContext, R extends ProvidedRole, S extends Signature> void beginAssemblyProvidedOperationCallInterpretation(AssemblyProvidedOperationPassedEvent<T, R, S> event);
    
    public <T extends AssemblyContext, R extends ProvidedRole, S extends Signature> void endAssemblyProvidedOperationCallInterpretation(AssemblyProvidedOperationPassedEvent<T, R, S> event);
    
}
