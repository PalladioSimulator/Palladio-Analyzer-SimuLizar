package org.palladiosimulator.simulizar.modules.component.core;

import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.ParentContext;
import org.palladiosimulator.simulizar.interpreter.impl.EMFPackageBasedInterpreterFacade;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;
import org.palladiosimulator.simulizar.modules.stateless.core.CoreBindingsModule;
import org.palladiosimulator.simulizar.modules.stateless.mdsd.PCMPartitionManagerAdapterModule;
import org.palladiosimulator.simulizar.scopes.SimulatedThreadScope;

import dagger.Binds;
import dagger.Module;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;

@Module(includes = { CoreBindingsModule.class, PCMPartitionManagerAdapterModule.class } )
public interface SimulatedThreadModule {

    @SimulatedThreadScope
    static InterpreterDefaultContext provideContext(@ParentContext InterpreterDefaultContext parentContext, SimuComSimProcess simProcess) {
        return new InterpreterDefaultContext(parentContext, simProcess);
    }
    
    @Binds
    @SimulatedThreadScope
    InterpreterFacade bindInterpreterFacade(EMFPackageBasedInterpreterFacade impl);
}
