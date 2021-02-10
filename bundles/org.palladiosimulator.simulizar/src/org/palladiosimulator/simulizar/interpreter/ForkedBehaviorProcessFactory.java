package org.palladiosimulator.simulizar.interpreter;

import org.palladiosimulator.pcm.seff.ForkedBehaviour;

import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;

public interface ForkedBehaviorProcessFactory {
    
    ForkedBehaviourProcess create(ForkedBehaviour behavior, InterpreterDefaultContext parentContext, boolean isAsync);

}
