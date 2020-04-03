package org.palladiosimulator.simulizar.modelobserver.tests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIn.in;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.allocation.AllocationFactory;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.resourceenvironment.ResourceEnvironment;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentFactory;
import org.palladiosimulator.pcm.resourceenvironment.ResourceenvironmentPackage;
import org.palladiosimulator.simulizar.modelobserver.ResourceEnvironmentSyncer;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

@ExtendWith(MockitoExtension.class)
class ResourceEnvironmentSyncerTest {
    PCMResourceSetPartition resourceSetPartition;
    ResourceEnvironment resourceEnvironment;
    ResourceEnvironmentSyncer syncerUnderTest;

    @Mock
    AbstractSimuLizarRuntimeState runtimeStateMock;

    @Mock
    PCMPartitionManager partitionManagerMock;

    @Mock
    SimuComModel simuComModelMock;

    ResourceRegistry resourceRegistry;

    @BeforeEach
    void setUp() throws Exception {
        resourceSetPartition = new PCMResourceSetPartition();

        EcorePlugin.ExtensionProcessor.process(null);
    }

    private ResourceRegistry setUpMockRegistry() {
        var res = Mockito.spy(new ResourceRegistry(simuComModelMock));

        Mockito.lenient()
            .doAnswer(new Answer<AbstractSimulatedResourceContainer>() {
                @Override
                public AbstractSimulatedResourceContainer answer(InvocationOnMock invocation) throws Throwable {
                    var src = resourceEnvironment.getResourceContainer_ResourceEnvironment()
                        .stream()
                        .filter(c -> c.getId()
                            .equals(invocation.getArgument(0)))
                        .findAny()
                        .map(ResourceEnvironmentSyncerTest.this::createMock)
                        .orElseGet(() -> {
                            fail();
                            return null;
                        });

                    res.addResourceContainer(src);
                    return src;
                }
            })
            .when(res)
            .createResourceContainer(Mockito.anyString());

        Mockito.lenient()
            .doAnswer(new Answer<AbstractSimulatedResourceContainer>() {
                @Override
                public AbstractSimulatedResourceContainer answer(InvocationOnMock invocation) throws Throwable {
                    var src = resourceEnvironment.getLinkingResources__ResourceEnvironment()
                        .stream()
                        .filter(c -> c.getId()
                            .equals(invocation.getArgument(0)))
                        .findAny()
                        .map(ResourceEnvironmentSyncerTest.this::createMock)
                        .orElseGet(() -> {
                            fail();
                            return null;
                        });

                    res.addLinkingResourceContainer(src);
                    return src;
                }
            })
            .when(res)
            .createLinkingResourceContainer(Mockito.anyString());

        return res;
    }

    private void setUpMocks(Supplier<ResourceEnvironment> resourceEnvironmentProvider) {
        resourceEnvironment = resourceEnvironmentProvider.get();
        var alloc = AllocationFactory.eINSTANCE.createAllocation();
        alloc.setTargetResourceEnvironment_Allocation(resourceEnvironment);

        var allocation = resourceSetPartition.getResourceSet()
            .createResource(URI.createFileURI("test.allocation"));
        var resource = resourceSetPartition.getResourceSet()
            .createResource(URI.createFileURI("test.resourceenvironment"));

        resource.getContents()
            .add(resourceEnvironment);
        allocation.getContents()
            .add(alloc);

        Mockito.lenient()
            .when(partitionManagerMock.getGlobalPCMModel())
            .thenReturn(resourceSetPartition);
        Mockito.lenient()
            .when(runtimeStateMock.getPCMPartitionManager())
            .thenReturn(partitionManagerMock);
        Mockito.lenient()
            .when(runtimeStateMock.getModel())
            .thenReturn(simuComModelMock);
        resourceRegistry = setUpMockRegistry();
        Mockito.lenient()
            .when(simuComModelMock.getResourceRegistry())
            .thenReturn(resourceRegistry);
    }

    private void setUpSyncer() {
        syncerUnderTest = new ResourceEnvironmentSyncer();
        syncerUnderTest.initialize(runtimeStateMock);
    }

    @Test
    final void testRegisterAdapter() {
        setUpMocks(ResourceEnvironmentTestModels::empty);
        var adapterCount = resourceEnvironment.eAdapters()
            .size();

        setUpSyncer();

        assertThat(resourceEnvironment.eAdapters(), hasSize(adapterCount + 1));
    }

    @Test
    final void testAddResourceContainer() {
        setUpMocks(ResourceEnvironmentTestModels::empty);
        setUpSyncer();
        var container = ResourceenvironmentFactory.eINSTANCE.createResourceContainer();
        resourceEnvironment.getResourceContainer_ResourceEnvironment()
            .add(container);
        verify(resourceRegistry).createResourceContainer(container.getId());
        verify(resourceRegistry, Mockito.never()).getResourceContainer(container.getId());
    }

    @Test
    final void testAddLinkingResource() {
        setUpMocks(ResourceEnvironmentTestModels::empty);
        setUpSyncer();
        var link = ResourceenvironmentFactory.eINSTANCE.createLinkingResource();
        resourceEnvironment.getLinkingResources__ResourceEnvironment()
            .add(link);
        verify(resourceRegistry).createLinkingResourceContainer(link.getId());
    }

    private SimulatedResourceContainer createMock(ResourceContainer rc) {
        var src = Mockito.mock(SimulatedResourceContainer.class);
        var mockResources = new HashMap<String, AbstractScheduledResource>();
        Mockito.lenient()
            .when(src.getResourceContainerID())
            .thenReturn(rc.getId());
        Mockito.lenient()
            .when(src.addActiveResourceWithoutCalculators(Mockito.any(), Mockito.any(), Mockito.eq(rc.getId()),
                    Mockito.anyString()))
            .thenAnswer(new Answer<ScheduledResource>() {
                @Override
                public ScheduledResource answer(InvocationOnMock invocation) throws Throwable {
                    var prs = invocation.<ProcessingResourceSpecification> getArgument(0);
                    assertThat(invocation.getArgument(0),
                            is(in(rc.getActiveResourceSpecifications_ResourceContainer())));

                    var sr = Mockito.mock(ScheduledResource.class);
                    mockResources.put(prs.getActiveResourceType_ActiveResourceSpecification()
                        .getId(), sr);
                    return sr;
                }
            });
        Mockito.lenient()
            .when(src.getAllActiveResources())
            .thenReturn(mockResources);
        return src;
    }

    private SimulatedLinkingResourceContainer createMock(LinkingResource lr) {
        var src = Mockito.mock(SimulatedLinkingResourceContainer.class);
        var mockResources = new HashMap<String, AbstractScheduledResource>();
        Mockito.lenient()
            .when(src.getResourceContainerID())
            .thenReturn(lr.getId());
        Mockito.lenient()
            .doAnswer(new Answer<ScheduledResource>() {
                @Override
                public ScheduledResource answer(InvocationOnMock invocation) throws Throwable {
                    assertThat(lr, equalTo(invocation.getArgument(0)));

                    var sr = Mockito.mock(ScheduledResource.class);
                    mockResources.put(lr.getCommunicationLinkResourceSpecifications_LinkingResource()
                        .getCommunicationLinkResourceType_CommunicationLinkResourceSpecification()
                        .getId(), sr);
                    return sr;
                }
            })
            .when(src)
            .addActiveResource(Mockito.any(), Mockito.anyString());
        Mockito.lenient()
            .when(src.getAllActiveResources())
            .thenReturn(mockResources);
        return src;
    }

    private void setUpFullyMockedScenario(Supplier<ResourceEnvironment> resourceEnvironmentProvider) {
        setUpMocks(resourceEnvironmentProvider);
    }

    @Test
    final void testInitialize() {
        setUpFullyMockedScenario(ResourceEnvironmentTestModels::withTwoContainersAndOneLink);
        var rca = resourceEnvironment.getResourceContainer_ResourceEnvironment()
            .get(0);
        var rcb = resourceEnvironment.getResourceContainer_ResourceEnvironment()
            .get(1);
        var link = resourceEnvironment.getLinkingResources__ResourceEnvironment()
            .get(0);

        setUpSyncer();

        verify(resourceRegistry).createResourceContainer(rca.getId());
        verify(resourceRegistry).createResourceContainer(rcb.getId());
        verify(resourceRegistry).createLinkingResourceContainer(link.getId());

        verify((SimulatedResourceContainer) resourceRegistry.getResourceContainer(rca.getId()))
            .addActiveResourceWithoutCalculators(Mockito.eq(rca.getActiveResourceSpecifications_ResourceContainer()
                .get(0)), Mockito.any(), Mockito.eq(rca.getId()), Mockito.anyString());
        verify((SimulatedResourceContainer) resourceRegistry.getResourceContainer(rcb.getId()))
            .addActiveResourceWithoutCalculators(Mockito.eq(rcb.getActiveResourceSpecifications_ResourceContainer()
                .get(0)), Mockito.any(), Mockito.eq(rcb.getId()), Mockito.anyString());
        verify((SimulatedLinkingResourceContainer) resourceRegistry.getResourceContainer(link.getId()))
            .addActiveResource(link, link.getId());
    }

}
