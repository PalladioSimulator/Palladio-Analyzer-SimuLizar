package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.simucomframework.usage.ICancellableWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.IWorkloadDriver;

public interface UsageWorkloadRegistry {
    
    IWorkloadDriver createAndAddWorkloadDriver(final UsageScenario usageScenario);
    
    ICancellableWorkloadDriver getWorkloadDriver(final Workload workload);

}
