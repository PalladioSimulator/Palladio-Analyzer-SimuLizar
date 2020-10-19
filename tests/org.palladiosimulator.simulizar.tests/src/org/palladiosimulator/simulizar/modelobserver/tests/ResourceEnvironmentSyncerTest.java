package org.palladiosimulator.simulizar.modelobserver.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.CommunicationLinkResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.simulizar.modelobserver.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;
import org.palladiosimulator.simulizar.test.annotation.Identified;
import org.palladiosimulator.simulizar.test.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.annotation.Named;
import org.palladiosimulator.simulizar.test.annotation.PCMInstanceFromSupplier;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;
import de.uka.ipd.sdq.stoex.StoexPackage;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SimuLizarTestExtension.class)
class ResourceEnvironmentSyncerTest {
    ResourceEnvironmentSyncer syncerUnderTest;

    private void setUpSyncer(AbstractSimuLizarRuntimeState runtimeState) {
        syncerUnderTest = new ResourceEnvironmentSyncer();
        syncerUnderTest.initialize(runtimeState);
    }

    /**
     * Test, if model observer registers its adapter properly.
     */
    @Test
    @PCMInstanceFromSupplier(TestModelBase.Empty.class)
    @MockSimulation
    final void testRegisterAdapter(AbstractSimuLizarRuntimeState runtimeState, PCMResourceSetPartition partition) {
        var adapterCount = partition.getResourceEnvironment()
            .eAdapters()
            .size();

        setUpSyncer(runtimeState);

        assertThat(partition.getResourceEnvironment()
            .eAdapters(), hasSize(adapterCount + 1));
    }

    /**
     * Test, if adding a single resource container to an otherwise empty resource environment
     * triggers the expected actions by the observer.
     */
    @Test
    @PCMInstanceFromSupplier(TestModelBase.Empty.class)
    @MockSimulation
    final void testAddResourceContainer(AbstractSimuLizarRuntimeState runtimeState, PCMResourceSetPartition partition,
            ResourceRegistry resourceRegistry) {
        setUpSyncer(runtimeState);
        var container = ResourceenvironmentFactory.eINSTANCE.createResourceContainer();
        partition.getResourceEnvironment()
            .getResourceContainer_ResourceEnvironment()
            .add(container);
        verify(resourceRegistry).createResourceContainer(container.getId());
        verify(resourceRegistry, Mockito.never()).getResourceContainer(container.getId());
    }

    /**
     * Test, if adding a single linking resource to an otherwise empty resource environment triggers
     * the expected actions by the observer.
     */
    @Test
    @PCMInstanceFromSupplier(TestModelBase.Empty.class)
    @MockSimulation
    final void testAddLinkingResource(AbstractSimuLizarRuntimeState runtimeState, PCMResourceSetPartition partition,
            ResourceRegistry resourceRegistry) {
        setUpSyncer(runtimeState);
        var link = ResourceenvironmentFactory.eINSTANCE.createLinkingResource();
        partition.getResourceEnvironment()
            .getLinkingResources__ResourceEnvironment()
            .add(link);
        verify(resourceRegistry).createLinkingResourceContainer(link.getId());
    }

    /**
     * Test, if syncer initializes properly for non-empty resource environment.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation
    final void testInitialize(AbstractSimuLizarRuntimeState runtimeState, PCMResourceSetPartition partition,
            ResourceRegistry resourceRegistry, @Named("Container A") ResourceContainer rca,
            @Named("Container B") ResourceContainer rcb, @Named("Link 1") LinkingResource link,
            @Identified("A1") ProcessingResourceSpecification specRca,
            @Identified("B1") ProcessingResourceSpecification specRcb) {
        setUpSyncer(runtimeState);

        verify(resourceRegistry).createResourceContainer(rca.getId());
        verify(resourceRegistry).createResourceContainer(rcb.getId());
        verify(resourceRegistry).createLinkingResourceContainer(link.getId());

        var srca = (SimulatedResourceContainer) resourceRegistry.getResourceContainer(rca.getId());
        verify(srca).addActiveResourceWithoutCalculators(Mockito.eq(specRca), Mockito.any(), Mockito.eq(rca.getId()),
                Mockito.anyString());

        var srcb = (SimulatedResourceContainer) resourceRegistry.getResourceContainer(rcb.getId());
        verify(srcb).addActiveResourceWithoutCalculators(Mockito.eq(specRcb), Mockito.any(), Mockito.eq(rcb.getId()),
                Mockito.anyString());

        var slink = (SimulatedLinkingResourceContainer) resourceRegistry.getResourceContainer(link.getId());
        verify(slink).addActiveResourceWithoutCalculators(link, link.getId());
    }

    /**
     * Test, if syncer does not try to create simulation entities, if they do already exist.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation(initializeRegistry = true)
    final void testNoUnnecessaryInitialize(AbstractSimuLizarRuntimeState runtimeState,
            ResourceRegistry resourceRegistry) {

        setUpSyncer(runtimeState);

        verify(resourceRegistry, Mockito.never()).createResourceContainer(Mockito.anyString());
        verify(resourceRegistry, Mockito.never()).createLinkingResourceContainer(Mockito.anyString());
    }

    /**
     * Test various ways of updating the processing rate within the model and check, if the changes
     * are properly reflected.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation(initializeRegistry = true)
    final void testSyncProcessingRate(AbstractSimuLizarRuntimeState runtimeState, ResourceRegistry resourceRegistry,
            @Named("Container A") ResourceContainer rca, @Identified("A1") ProcessingResourceSpecification specRca) {

        setUpSyncer(runtimeState);

        var schedResource = (ScheduledResource) resourceRegistry.getResourceContainer(rca.getId())
            .getAllActiveResources()
            .get(specRca.getActiveResourceType_ActiveResourceSpecification()
                .getId());

        // Test setting the specification string directly
        specRca.getProcessingRate_ProcessingResourceSpecification()
            .setSpecification("2000");
        verify(schedResource).setProcessingRate("2000");

        // Test setting the entire stoex
        specRca.setProcessingRate_ProcessingResourceSpecification(TestModelBase.stoex("3000"));
        verify(schedResource).setProcessingRate("3000");

        // Test setting the stoex, but via its container reference
        TestModelBase.stoex("4000")
            .setProcessingResourceSpecification_processingRate_PCMRandomVariable(specRca);
        verify(schedResource).setProcessingRate("4000");

        // lastly check setting via EMF reflections
        specRca.getProcessingRate_ProcessingResourceSpecification()
            .eSet(StoexPackage.Literals.RANDOM_VARIABLE__SPECIFICATION, "5000");
        verify(schedResource).setProcessingRate("5000");

    }

    /**
     * Test various ways of updating linking resource parameters within the model and check, if the
     * changes are properly reflected.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation(initializeRegistry = true)
    final void testSyncLinkingResource(AbstractSimuLizarRuntimeState runtimeState, ResourceRegistry resourceRegistry,
            @Named("Link 1") LinkingResource link, @Identified("L1") CommunicationLinkResourceSpecification specLink) {

        setUpSyncer(runtimeState);

        var schedResource = (SimulatedLinkingResource) resourceRegistry.getResourceContainer(link.getId())
            .getAllActiveResources()
            .get(specLink.getCommunicationLinkResourceType_CommunicationLinkResourceSpecification()
                .getId());

        // Test setting the specification string directly
        specLink.getThroughput_CommunicationLinkResourceSpecification()
            .setSpecification("2000");
        verify(schedResource).setThroughput("2000");

        specLink.getLatency_CommunicationLinkResourceSpecification()
            .setSpecification("300");
        verify(schedResource).setLatency("300");

        // Test setting the entire stoex
        specLink.setThroughput_CommunicationLinkResourceSpecification(TestModelBase.stoex("3000"));
        verify(schedResource).setThroughput("3000");

        // Test setting the stoex, but via its container reference
        TestModelBase.stoex("4000")
            .setCommunicationLinkResourceSpecifcation_throughput_PCMRandomVariable(specLink);
        verify(schedResource).setThroughput("4000");
    }
}
