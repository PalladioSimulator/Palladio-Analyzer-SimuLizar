package org.palladiosimulator.simulizar.interpreter.impl;

import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.simulizar.di.component.core.SimulatedThreadComponent;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext.MainContext;
import org.palladiosimulator.simulizar.usagemodel.IScenarioRunnerFactory;

import dagger.assisted.Assisted;
import dagger.assisted.AssistedFactory;
import dagger.assisted.AssistedInject;
import de.uka.ipd.sdq.simucomframework.SimuComSimProcess;
import de.uka.ipd.sdq.simucomframework.usage.IScenarioRunner;

public class SimulatedThreadComponentDelegatingScenarioRunner implements IScenarioRunner {
    @AssistedFactory
    public static interface Factory extends IScenarioRunnerFactory<Entity> {
        SimulatedThreadComponentDelegatingScenarioRunner create(EntityReference<? extends Entity> reference);
      
        @Override
        default SimulatedThreadComponentDelegatingScenarioRunner createScenarioRunner(
                EntityReference<? extends Entity> reference) {
            return create(reference);
        }
    }

    private final SimulatedThreadComponent.Factory simulatedThreadComponentFactory;
    private final InterpreterDefaultContext rootContext;
    private final EntityReference<? extends Entity> reference;

    @AssistedInject
    public SimulatedThreadComponentDelegatingScenarioRunner(@Assisted EntityReference<? extends Entity> reference,
            SimulatedThreadComponent.Factory simulatedThreadComponentFactory,
            @MainContext InterpreterDefaultContext rootContext) {
        this.reference = reference;
        this.simulatedThreadComponentFactory = simulatedThreadComponentFactory;
        this.rootContext = rootContext;
    }

    @Override
    public void scenarioRunner(SimuComSimProcess thread) {
        simulatedThreadComponentFactory.create(rootContext, thread)
            .interpreterFacade()
            .submit(reference);
    }
}
