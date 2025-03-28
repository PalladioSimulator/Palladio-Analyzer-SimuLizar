package org.palladiosimulator.simulizar.di.component.interfaces;

import de.uka.ipd.sdq.workflow.jobs.IJob;

public interface AnalysisRuntimeComponent {
    
    IJob runtimeJob();
    
    public interface Factory {
        AnalysisRuntimeComponent create();
    }
    
}
