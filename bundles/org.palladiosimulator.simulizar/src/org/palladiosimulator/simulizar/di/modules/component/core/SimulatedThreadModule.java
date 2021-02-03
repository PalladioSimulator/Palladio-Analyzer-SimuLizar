package org.palladiosimulator.simulizar.di.modules.component.core;

import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.di.modules.scoped.thread.CoreSimulatedThreadBindings;
import org.palladiosimulator.simulizar.di.modules.stateless.core.CoreBindingsModule;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.ParentContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;
import org.palladiosimulator.simulizar.interpreter.impl.EMFPackageBasedInterpreterFacade;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Local;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

@Module(includes = { CoreBindingsModule.class, 
        CoreSimulatedThreadBindings.class } )
public interface SimulatedThreadModule {

    @Provides
    @SimulatedThreadScope
    static InterpreterDefaultContext provideContext(@ParentContext InterpreterDefaultContext parentContext, SimuComSimProcess simProcess) {
        return InterpreterDefaultContext.createChildContext(parentContext, simProcess);
    }
    
    @Provides
    @Local
    static PCMResourceSetPartition provideLocalPartition(InterpreterDefaultContext context) {
        return context.getLocalPCMModelAtContextCreation();
    }
    
    @Binds
    @SimulatedThreadScope
    InterpreterFacade bindInterpreterFacade(EMFPackageBasedInterpreterFacade impl);
}
