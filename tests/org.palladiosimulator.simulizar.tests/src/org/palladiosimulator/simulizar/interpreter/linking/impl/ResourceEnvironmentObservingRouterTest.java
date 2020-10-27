package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.modelobserver.tests.ResourceEnvironmentTestModels;
import org.palladiosimulator.simulizar.test.BaseTestModule;
import org.palladiosimulator.simulizar.test.SimuLizarTestExtension;
import org.palladiosimulator.simulizar.test.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager.Global;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SimuLizarTestExtension.class)
public class ResourceEnvironmentObservingRouterTest {
    protected class RouterTestModule extends BaseTestModule {
        private PCMResourceSetPartition partition;

        public RouterTestModule(PCMResourceSetPartition partition) {
            this.partition = partition;
        }

        @Override
        protected void configure() {
            super.configure();

            bind(PCMResourceSetPartition.class).annotatedWith(Global.class)
                .toInstance(partition);
            bind(new TypeLiteral<ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>>>() {
            }).to(ResourceEnvironmentObservingLegacyRouter.class);
        }
    }

    ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>> routerUnderTest;
    EntityReferenceFactory<ResourceContainer> rcRefFactory;
    EntityReferenceFactory<LinkingResource> linkRefFactory;

    private void setUpRouter(PCMResourceSetPartition partition) {
        var injector = Guice.createInjector(new RouterTestModule(partition));
        routerUnderTest = injector.getInstance(Key.get(
                new TypeLiteral<ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>>>() {}));
        rcRefFactory = injector.getInstance(Key.get(new TypeLiteral<EntityReferenceFactory<ResourceContainer>>() {}));
        linkRefFactory = injector.getInstance(Key.get(new TypeLiteral<EntityReferenceFactory<LinkingResource>>() {}));
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

        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CB"));
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

        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CB"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
        
        var link = partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().get(0);
        partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().clear();
        res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CB"));
        assertFalse(res.isPresent());
        
        partition.getResourceEnvironment().getLinkingResources__ResourceEnvironment().add(link);
        res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CB"));
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

        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CB"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC1", links.get(0).getId());
        
        res = routerUnderTest.findRoute(rcRefFactory.create("CB"), rcRefFactory.create("CC"));
        assertTrue(res.isPresent());
        links = Lists.newArrayList(res.get());
        assertEquals(1, links.size());
        assertEquals("LC2", links.get(0).getId());
        
        res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CC"));
        assertFalse(res.isPresent());
    }
    
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithThreeContainersAndTwoLinks.class)
    @MockSimulation
    final void testAttachContainerToLinkingResource(PCMResourceSetPartition partition) {
        setUpRouter(partition);
        
        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CC"));
        assertFalse(res.isPresent());
        
        linkRefFactory.create("LC1").getModelElement(partition).getConnectedResourceContainers_LinkingResource()
            .add(rcRefFactory.create("CC").getModelElement(partition));
        
        res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CC"));
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

        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CA"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(0, links.size());
    }
}
