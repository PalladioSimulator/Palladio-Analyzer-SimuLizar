package org.palladiosimulator.simulizar.modelobserver;

import java.util.Collection;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager.Global;
import org.palladiosimulator.simulizar.di.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.usagemodel.UsageEvolverFacade;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

@SimulationRuntimeScope
public class UsageEvolutionSyncer extends AbstractUsageEvolutionObserver {

    private static final Logger LOGGER = Logger.getLogger(UsageEvolutionSyncer.class);
    private UsageEvolverFacade usageEvolverFacade;

    @Inject
    public UsageEvolutionSyncer(@Global PCMResourceSetPartition globalPCMInstance, UsageEvolverFacade usageEvolverFacade) {
        super(globalPCMInstance);
        this.usageEvolverFacade = usageEvolverFacade;
    }
    
    @Override
    public void initialize() {
        super.initialize();

        globalPCMInstance.getElement(UsageevolutionPackage.eINSTANCE.getUsageEvolution())
            .stream()
            .map(UsageEvolution.class::cast)
            .map(UsageEvolution::getUsages)
            .flatMap(Collection::stream)
            .forEach(usage -> usageEvolverFacade.startUsageEvolution(usage));
    }
    
    @Override
    protected void add(Notification notification) {
        if (UsageevolutionPackage.eINSTANCE.getUsageEvolution().isInstance(notification.getNotifier())
                && UsageevolutionPackage.eINSTANCE.getUsageEvolution_Usages().equals(notification.getFeature())) {
            this.syncUsageAddition(notification);
        } else {
            LOGGER.error(
                    "Usage Evolution Model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }
    
    @Override
    protected void remove(Notification notification) {
        if (UsageevolutionPackage.eINSTANCE.getUsageEvolution().isInstance(notification.getNotifier())
                && UsageevolutionPackage.eINSTANCE.getUsageEvolution_Usages().equals(notification.getFeature())) {
            this.syncUsageRemoval(notification);
        } else {
            LOGGER.error(
                    "Usage Evolution Model changed...But no resync strategy is known. Simulation results most likely are wrong.");
        }
    }

    @Override
    protected void set(final Notification notification) {
        LOGGER.error(
               "Usage Evolution Model changed...But no resync strategy is known. Simulation results most likely are wrong.");
    }

    private void syncUsageAddition(Notification notification) {
        LOGGER.debug("Initializing execution of new usage evolution");
        usageEvolverFacade.startUsageEvolution((Usage) notification.getNewValue()); 
        LOGGER.debug("Execution of new usage scenario started");
    }
    
    private void syncUsageRemoval(Notification notification) {
        LOGGER.debug("Stopping execution of a particular usage evolution");
        usageEvolverFacade.stopUsageEvolution((Usage) notification.getOldValue()); 
        LOGGER.debug("Stopping execution of a particular usage evolution"); 
    }

}
