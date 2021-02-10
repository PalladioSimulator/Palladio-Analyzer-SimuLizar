package org.palladiosimulator.simulizar.interpreter.impl;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.palladiosimulator.pcm.seff.ForkedBehaviour;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.di.component.interfaces.SimulatedThreadComponent.Factory;
import org.palladiosimulator.simulizar.interpreter.ForkedBehaviorProcessFactory;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;

import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.simucomframework.fork.ForkedBehaviourProcess;

public class ForkedBehaviorProcessFactoryImpl implements ForkedBehaviorProcessFactory {
    private static final Logger LOGGER = Logger.getLogger(ForkedBehaviorProcessFactoryImpl.class);
    private final IResourceTableManager resourceTableManager;
    private final Factory simulatedThreadComponentFactory;
    
    @Inject
    public ForkedBehaviorProcessFactoryImpl(IResourceTableManager resourceTableManager, SimulatedThreadComponent.Factory simulatedThreadComponentFactory) {
        this.resourceTableManager = resourceTableManager;
        this.simulatedThreadComponentFactory = simulatedThreadComponentFactory;
    }

    @Override
    public ForkedBehaviourProcess create(ForkedBehaviour behavior, InterpreterDefaultContext parentContext,
            boolean isAsync) {
        return new ForkedBehaviourProcess(parentContext,
                parentContext.getAssemblyContextStack().peek().getId(), isAsync, resourceTableManager) {
            
            InterpreterFacade facade = simulatedThreadComponentFactory.create(parentContext, this).interpreterFacade();
            
            @Override
            protected void executeBehaviour() {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Created new RDSeff interpreter for " + ((this.isAsync()) ? "asynced" : "synced")
                            + " forked baviour: " + this);
                }
                
                facade.submit(behavior);
            }
        };
    }

}
