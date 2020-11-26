package org.palladiosimulator.simulizar;

import javax.inject.Named;

import org.palladiosimulator.simulizar.launcher.SimulizarConstants;

import dagger.Subcomponent;
import de.uka.ipd.sdq.workflow.jobs.IJob;

@Subcomponent
public interface SimuLizarModelLoadComponent {

    @Named(SimulizarConstants.MODEL_LOAD_JOB_ID) IJob modelLoadJob();
    
    @Subcomponent.Builder
    public interface Builder {
        SimuLizarModelLoadComponent build();
    }
    
}
