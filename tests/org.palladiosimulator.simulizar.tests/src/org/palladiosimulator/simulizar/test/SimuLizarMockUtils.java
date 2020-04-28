package org.palladiosimulator.simulizar.test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIn.in;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ProcessingResourceSpecification;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.runtimestate.AbstractSimuLizarRuntimeState;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;

import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ScheduledResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResource;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedLinkingResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.SimulatedResourceContainer;

public class SimuLizarMockUtils {

    /**
     * Creates a mocked RuntimeState providing a global pcm model instance and a resource registry.
     * 
     * It is suggested not to use this method directly, but use the {@link SimuLizarTestExtension}
     * extension for JUnit 5.
     * 
     * @param resourceEnvironmentProvider
     *            the test model instance.
     * @param initializeRegistry
     *            should the ResourceRegistry be populated with simulated resource entities?
     */
    public static Map<Class<?>, Object> setUpMockRuntimeStateForModelObserving(PCMResourceSetPartition pcmInstance,
            boolean initializeRegistry) {
        var partitionManagerMock = Mockito.mock(PCMPartitionManager.class);
        var runtimeStateMock = Mockito.mock(AbstractSimuLizarRuntimeState.class);
        var simuComModelMock = Mockito.mock(SimuComModel.class);

        Mockito.lenient()
            .when(partitionManagerMock.getGlobalPCMModel())
            .thenReturn(pcmInstance);
        Mockito.lenient()
            .when(runtimeStateMock.getPCMPartitionManager())
            .thenReturn(partitionManagerMock);
        Mockito.lenient()
            .when(runtimeStateMock.getModel())
            .thenReturn(simuComModelMock);
        var resourceRegistry = setUpMockRegistry(pcmInstance, simuComModelMock);
        Mockito.lenient()
            .when(simuComModelMock.getResourceRegistry())
            .thenReturn(resourceRegistry);

        if (initializeRegistry) {
            pcmInstance.getResourceEnvironment()
                .getResourceContainer_ResourceEnvironment()
                .stream()
                .forEach(rc -> {
                    var asrc = (SimulatedResourceContainer) resourceRegistry.createResourceContainer(rc.getId());
                    rc.getActiveResourceSpecifications_ResourceContainer()
                        .stream()
                        .forEach(prs -> {
                            asrc.addActiveResourceWithoutCalculators(prs, null, rc.getId(), prs.getSchedulingPolicy()
                                .getId());
                        });
                });
            pcmInstance.getResourceEnvironment()
                .getLinkingResources__ResourceEnvironment()
                .stream()
                .forEach(lr -> {
                    var slrc = (SimulatedLinkingResourceContainer) resourceRegistry
                        .createLinkingResourceContainer(lr.getId());
                    slrc.addActiveResourceWithoutCalculators(lr, lr.getId());
                });
            Mockito.clearInvocations(resourceRegistry);
        }

        //@formatter:off
        return Map.of(
                PCMResourceSetPartition.class, pcmInstance, 
                PCMPartitionManager.class, partitionManagerMock, 
                AbstractSimuLizarRuntimeState.class, runtimeStateMock, 
                SimuComModel.class, simuComModelMock,
                ResourceRegistry.class, resourceRegistry);
        //@formatter:on
    }

    /**
     * Creates a ResourceRegistry and mocks the two factory methods for simulated resource
     * containers with equivalents, which create mock instances.
     */
    private static ResourceRegistry setUpMockRegistry(PCMResourceSetPartition partition, SimuComModel simuComModel) {
        var res = Mockito.spy(new ResourceRegistry(simuComModel));

        Mockito.lenient()
            .doAnswer(new Answer<AbstractSimulatedResourceContainer>() {
                @Override
                public AbstractSimulatedResourceContainer answer(InvocationOnMock invocation) throws Throwable {
                    var src = partition.getResourceEnvironment()
                        .getResourceContainer_ResourceEnvironment()
                        .stream()
                        .filter(c -> c.getId()
                            .equals(invocation.getArgument(0)))
                        .findAny()
                        .map(SimuLizarMockUtils::createMock)
                        .orElseGet(() -> fail("Create method called with invalid model element"));

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
                    var src = partition.getResourceEnvironment()
                        .getLinkingResources__ResourceEnvironment()
                        .stream()
                        .filter(c -> c.getId()
                            .equals(invocation.getArgument(0)))
                        .findAny()
                        .map(SimuLizarMockUtils::createMock)
                        .orElseGet(() -> fail("Create method called with invalid model element"));

                    res.addLinkingResourceContainer(src);
                    return src;
                }
            })
            .when(res)
            .createLinkingResourceContainer(Mockito.anyString());

        return res;
    }

    /** Creates a mocked SimulatedResourceContainer for the provided resource container */
    private static SimulatedResourceContainer createMock(ResourceContainer rc) {
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

    /** Creates a mocked SimulatedLinkingResourceContainer for the provided linking resource */
    private static SimulatedLinkingResourceContainer createMock(LinkingResource lr) {
        var src = Mockito.mock(SimulatedLinkingResourceContainer.class);
        var mockResources = new HashMap<String, AbstractScheduledResource>();
        Mockito.lenient()
            .when(src.getResourceContainerID())
            .thenReturn(lr.getId());
        Mockito.lenient()
            .doAnswer(new Answer<SimulatedLinkingResource>() {
                @Override
                public SimulatedLinkingResource answer(InvocationOnMock invocation) throws Throwable {
                    assertThat(lr, equalTo(invocation.getArgument(0)));

                    var sr = Mockito.mock(SimulatedLinkingResource.class);
                    mockResources.put(lr.getCommunicationLinkResourceSpecifications_LinkingResource()
                        .getCommunicationLinkResourceType_CommunicationLinkResourceSpecification()
                        .getId(), sr);
                    return sr;
                }
            })
            .when(src)
            .addActiveResourceWithoutCalculators(Mockito.any(), Mockito.anyString());
        Mockito.lenient()
            .when(src.getAllActiveResources())
            .thenReturn(mockResources);
        return src;
    }
}
