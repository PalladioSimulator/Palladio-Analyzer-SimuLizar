package org.palladiosimulator.simulizar.interpreter.linking.impl;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.resourceenvironment.LinkingResource;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.entity.EntityReference;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.entity.SimuLizarEntityReferenceFactories;
import org.palladiosimulator.simulizar.interpreter.linking.ILinkingResourceRouter;
import org.palladiosimulator.simulizar.test.commons.annotation.MockSimulation;
import org.palladiosimulator.simulizar.test.commons.annotation.PCMInstanceFromSupplier;
import org.palladiosimulator.simulizar.test.commons.models.ResourceEnvironmentTestModels;

import com.google.common.collect.Lists;

@ExtendWith(MockitoExtension.class)
public class ResourceEnvironmentObservingRouterTest {
    ILinkingResourceRouter<EntityReference<ResourceContainer>, EntityReference<LinkingResource>> routerUnderTest;    
    EntityReferenceFactory<ResourceContainer> rcRefFactory;    
    EntityReferenceFactory<LinkingResource> linkRefFactory;
    
    private void setUpRouter(PCMResourceSetPartition partition) {
        linkRefFactory = new SimuLizarEntityReferenceFactories.LinkingResource();
        rcRefFactory = new SimuLizarEntityReferenceFactories.ResourceContainer();
        routerUnderTest = new ResourceEnvironmentObservingLegacyRouter(partition, linkRefFactory);
    }

    /**
     * Test, if model observer registers its adapter properly.
     */
    @Test
    @PCMInstanceFromSupplier(ResourceEnvironmentTestModels.WithTwoContainersAndOneLink.class)
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
    final void testSameHost(PCMResourceSetPartition partition) {
        setUpRouter(partition);

        var res = routerUnderTest.findRoute(rcRefFactory.create("CA"), rcRefFactory.create("CA"));
        assertTrue(res.isPresent());
        var links = Lists.newArrayList(res.get());
        assertEquals(0, links.size());
    }
}
