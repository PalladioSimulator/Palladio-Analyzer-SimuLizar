package org.palladiosimulator.simulizar.interpreter.linking.impl;

import java.util.Collections;

import javax.inject.Inject;

import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;
import org.palladiosimulator.simulizar.interpreter.linking.ITransmissionSimulationStrategy;

import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;

public class SimulatedLinkingResourceContainerTransmissionStrategy implements
        ITransmissionSimulationStrategy<EntityReference<LinkingResource>, Double, InterpreterDefaultContext> {

    private final ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> linkingResourceAccess;

    @Inject
    public SimulatedLinkingResourceContainerTransmissionStrategy(
            ISimulatedModelEntityAccess<LinkingResource, SimulatedLinkingResource> linkingResourceAccess) {
        this.linkingResourceAccess = linkingResourceAccess;
    }

    @Override
    public void simulateTransmission(EntityReference<LinkingResource> linkRef, Double demand,
            InterpreterDefaultContext context) {
        var link = linkingResourceAccess.getSimulatedEntity(linkRef.getId());
        link.consumeResource(context.getThread(), 0, Collections.emptyMap(), demand);
    }

}
