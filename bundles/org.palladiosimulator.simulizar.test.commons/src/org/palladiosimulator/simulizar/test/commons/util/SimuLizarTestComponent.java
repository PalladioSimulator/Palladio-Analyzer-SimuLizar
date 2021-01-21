package org.palladiosimulator.simulizar.test.commons.util;

import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.di.modules.SimuLizarRootModule;
import org.palladiosimulator.simulizar.launcher.jobs.PCMStartInterpretationJob;

import dagger.Component;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Component(modules = { SimuLizarRootModule.class, TestPreferencesModule.class })
@Singleton
public interface SimuLizarTestComponent extends SimuLizarRootComponent {
    
    PCMStartInterpretationJob interpreterJob();
    
    MDSDBlackboard blackboard();

}
