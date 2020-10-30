package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.inject.Singleton;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.modules.EntityReferenceFactoryFoundationModule;
import org.palladiosimulator.simulizar.test.commons.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.commons.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.test.commons.models.ResourceEnvironmentTestModels;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import com.google.common.collect.Lists;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;

@ExtendWith(MockitoExtension.class)
public class ResourceEnvironmentObservingRouterTest {
    
    @Component (modules = { EntityReferenceFactoryFoundationModule.class, RouterUnderTestModule.class })
    @Singleton
    protected static interface RouterTestComponent {

        ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>> routerUnderTest();
        
        EntityReferenceFactory<ResourceContainer> rcRefFactory();
        
        EntityReferenceFactory<LinkingResource> linkRefFactory();
        
        @Component.Builder
        interface Builder {
            @BindsInstance
            Builder pcmResourceSetPartition(@Global PCMResourceSetPartition partition);
            
            RouterTestComponent build();
        }  
    }
    
    @Module
    protected static interface RouterUnderTestModule {
        @Binds
        @Singleton
        ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>> bindRouterUnderTest(
                ResourceEnvironmentObservingLegacyRouter impl);
    }
  
    RouterTestComponent tc;
    
    
    private void setUpRouter(PCMResourceSetPartition partition) {
        tc = DaggerResourceEnvironmentObservingRouterTest_RouterTestComponent.builder()
                .pcmResourceSetPartition(partition)
                .build();
        
        tc.routerUnderTest();
    }

    /**
     * Test, if model observer registers its adapter properly.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation
    final void testRegisterAdapter(PCMResourceSetPartition partition) {
        var adapterCount = partition.getResourceEnvironment()
            .eAdapters()
            .size();

        setUpRouter(partition);

        assertThat(partition.getResourceEnvironment()
            .eAdapters(), hasSize(adapterCount + 1));
    }

    /**
     * Test, if model observer registers its adapter properly.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation
    final void testSimpleLink(PCMResourceSetPartition partition) {
        setUpRouter(partition);

        var res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CB"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
    }
    
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation
    final void testLinkRemovalAndAdd(PCMResourceSetPartition partition) {
        setUpRouter(partition);

        var res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CB"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
        
        var link = partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().get(0);
        partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().clear();
        res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CB"));
        assertFalse(res.isPresent());
        
        partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().add(link);
        res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CB"));
        assertTrue(res.isPresent());
        links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
    }
    
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithThreeContainersAndTwoLinks.class)
    @MockSimulation
    final void testNoTransitiveConnection(PCMResourceSetPartition partition) {
        setUpRouter(partition);

        var res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CB"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
        
        res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CB"), tc.rcRefFactory().create("CC"));
        assertTrue(res.isPresent());
        links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC2", links.get(0).getId());
        
        res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CC"));
        assertFalse(res.isPresent());
    }
    
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithThreeContainersAndTwoLinks.class)
    @MockSimulation
    final void testAttachContainerToLinkingResource(PCMResourceSetPartition partition) {
        setUpRouter(partition);
        
        var res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CC"));
        assertFalse(res.isPresent());
        
        tc.linkRefFactory().create("LC1").getModelElement(partition).getConnectedResourceContainers_LinkingResource()
            .add(tc.rcRefFactory().create("CC").getModelElement(partition));
        
        res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CC"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
    }
    
    
    
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
    @MockSimulation
    final void testSameHost(PCMResourceSetPartition partition) {
        setUpRouter(partition);

        var res = tc.routerUnderTest().findRoute(tc.rcRefFactory().create("CA"), tc.rcRefFactory().create("CA"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(0, links.size());
    }
}
