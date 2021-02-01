package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.Objects;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Local;

public class EMFPackageBasedInterpreterFacade implements InterpreterFacade {

    private final InterpreterDefaultContext context;
    private final PCMResourceSetPartition localPartition;
    private final UsageScenarioSwitch.Factory usageScenarioSwitchFactory;

    @Inject
    public EMFPackageBasedInterpreterFacade(InterpreterDefaultContext context,
            @Local PCMResourceSetPartition localPartition, UsageScenarioSwitch.Factory usageScenarioSwitchFactory) {
        this.context = context;
        this.localPartition = localPartition;
        this.usageScenarioSwitchFactory = usageScenarioSwitchFactory;
    }

    @Override
    public void submit(EntityReference<?> object) {
        object.getModelElementIfPresent(localPartition)
            .ifPresent(elem -> {
                this.submit(elem);
            });
    }

    @Override
    public void submit(EObject object) {
        var entity = Objects.requireNonNull(object);
        var packageId = entity.eClass()
            .getEPackage()
            .getNsURI();
        switch (packageId) {
        case UsagemodelPackage.eNS_URI:
            usageScenarioSwitchFactory.create(context)
                .doSwitch(entity);
            break;
        default:
            throw new UnsupportedOperationException(
                    "The current interpreter facade is incompatible with elements of the package " + packageId);
        }
    }

}
