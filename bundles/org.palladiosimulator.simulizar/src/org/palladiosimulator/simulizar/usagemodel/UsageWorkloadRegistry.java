package org.palladiosimulator.simulizar.usagemodel;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.pcm.usagemodel.Workload;

import de.uka.ipd.sdq.simucomframework.core.usage.IWorkloadDriver;
import de.uka.ipd.sdq.simucomframework.usage.ICancellableWorkloadDriver;

public interface UsageWorkloadRegistry {
    
    IWorkloadDriver createAndAddWorkloadDriver(final UsageScenario usageScenario);
    
    ICancellableWorkloadDriver getWorkloadDriver(final Workload workload);

}
