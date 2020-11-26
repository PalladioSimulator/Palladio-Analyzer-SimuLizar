package org.palladiosimulator.simulizar.test.commons.util;

import javax.inject.Named;
import javax.inject.Singleton;

import org.palladiosimulator.simulizar.SimuLizarRootComponent;
import org.palladiosimulator.simulizar.launcher.SimulizarConstants;
import org.palladiosimulator.simulizar.modules.SimuLizarRootModule;

import dagger.Component;
import de.uka.ipd.sdq.workflow.jobs.IJob;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

@Component(modules = { SimuLizarRootModule.class, TestPreferencesModule.class })
@Singleton
public interface SimuLizarTestComponent extends SimuLizarRootComponent {
    
    @Named(SimulizarConstants.INTERPRETER_JOB_ID) IJob interpreterJob();
    
    MDSDBlackboard blackboard();

}
