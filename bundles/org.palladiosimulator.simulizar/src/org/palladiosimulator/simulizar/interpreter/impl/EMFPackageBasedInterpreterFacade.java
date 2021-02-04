package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.Objects;

import javax.inject.Inject;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.seff.SeffPackage;
import org.palladiosimulator.pcm.usagemodel.UsagemodelPackage;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.InterpreterFacade;
import org.palladiosimulator.simulizar.interpreter.UsageScenarioSwitch;
import org.palladiosimulator.simulizar.interpreter.result.InterpreterResult;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Local;

public class EMFPackageBasedInterpreterFacade implements InterpreterFacade {

    private final InterpreterDefaultContext context;
    private final PCMResourceSetPartition localPartition;
    private final UsageScenarioSwitch.Factory usageScenarioSwitchFactory;
    private final ComposedRDSeffSwitchFactory seffSwitchFactory;

    @Inject
    public EMFPackageBasedInterpreterFacade(InterpreterDefaultContext context,
            @Local PCMResourceSetPartition localPartition, 
            UsageScenarioSwitch.Factory usageScenarioSwitchFactory, 
            ComposedRDSeffSwitchFactory seffSwitchFactory) {
        this.context = context;
        this.localPartition = localPartition;
        this.usageScenarioSwitchFactory = usageScenarioSwitchFactory;
        this.seffSwitchFactory = seffSwitchFactory;
    }

    @Override
    public InterpreterResult submit(EntityReference<?> object) {
        return object.getModelElementIfPresent(localPartition)
            .map(elem -> this.submit(elem))
            .orElse(InterpreterResult.OK);
    }

    @Override
    public InterpreterResult submit(EObject object) {
        var entity = Objects.requireNonNull(object);
        var packageId = entity.eClass()
            .getEPackage()
            .getNsURI();
        switch (packageId) {
        case UsagemodelPackage.eNS_URI:
            return usageScenarioSwitchFactory
                .create(context)
                .doSwitch(entity);
        case SeffPackage.eNS_URI:
            return seffSwitchFactory
                .createRDSeffSwitch(context)
                .doSwitch(entity);

        default:
            throw new UnsupportedOperationException(
                    "The current interpreter facade is incompatible with elements of the package " + packageId);
        }
    }

}
