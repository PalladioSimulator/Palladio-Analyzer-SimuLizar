package org.palladiosimulator.simulizar.modelobserver;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DirectedAcyclicGraph;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.allocation.Allocation;
import org.palladiosimulator.pcm.allocation.AllocationContext;
import org.palladiosimulator.pcm.allocation.AllocationPackage;
import org.palladiosimulator.pcm.core.composition.AssemblyContext;
import org.palladiosimulator.pcm.core.composition.ComposedStructure;
import org.palladiosimulator.pcm.core.entity.Entity;
import org.palladiosimulator.pcm.repository.CompositeComponent;
import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.pcm.system.System;
import org.palladiosimulator.simulizar.core.entity.EntityReference;
import org.palladiosimulator.simulizar.core.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.core.utils.PCMPartitionManager.Global;
import org.palladiosimulator.simulizar.di.base.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.runtimestate.AssemblyAllocationManager;
import org.palladiosimulator.simulizar.runtimestate.FQComponentID;

/**
 * The Allocation Lookup Syncer updates the cache for the mapping of
 * allocation context to resource container.
 * 
 * In contrast to the generated SimuComContext variant, the allocation lookup
 * syncer also supports nested assembly contexts through the usage of
 * <code>FQComponentID</code>s.
 * 
 * LIMITATION: Due to the way the listener is currently registered, changes to
 * CompositeComponents will not trigger notifications. If this feature is
 * required the syncer also needs to listen to changes in the repository model.
 * 
 * @author Sebastian Krach
 *
 */
@SimulationRuntimeScope
public class AllocationLookupSyncer implements IModelObserver {
    private static final Logger LOGGER = Logger.getLogger(AllocationLookupSyncer.class);
    
    private final EntityReferenceFactory<ResourceContainer> resourceContainerReferenceFactory;
    private final AssemblyAllocationManager allocationManager;
    private PCMResourceSetPartition globalPartition;
    
    /**
     * Creates a new Allocation Lookup Syncer.
     * 
     * @param resourceContainerAccess a service to look up simulate resource
     *                                container instances based on the id of their
     *                                model counterpart.
     */
    @Inject
    public AllocationLookupSyncer(
            EntityReferenceFactory<ResourceContainer> resourceContainerReferenceFactory,
            @Global PCMResourceSetPartition globalPartition,
            AssemblyAllocationManager allocationManager) {
        this.resourceContainerReferenceFactory = resourceContainerReferenceFactory;
        this.globalPartition = globalPartition;
        this.allocationManager = allocationManager;
        
    }
    
    @Override
    public void initialize() {
        var allocation = globalPartition.getAllocation();
        allocation.eAdapters().add(new EContentAdapter() {
            @Override
            public void notifyChanged(Notification notification) {
                handleNotification(notification);
                super.notifyChanged(notification);
            }
        });
        addInitialAllocations(allocation);
    }
    
    protected void handleNotification(Notification msg) {
    	switch (msg.getEventType()) {
		case Notification.ADD:
		case Notification.ADD_MANY:
		case Notification.SET:
			checkAndAddAllocationContextFromNotification(msg);
			break;
			
		case Notification.REMOVE:
		case Notification.REMOVE_MANY:
			checkAndRemoveAllocationContextFromNotification(msg);
			break;

        default:
            throw new UnsupportedOperationException(
                    String.format("The event type %d is not supported for changes of feature %s by %s",
                            msg.getEventType(), msg.getFeature().toString(), this.getClass().getName()));
		}
    	
    }


    /**
     * Traverses a provided Allocation model and creates mappings for all containers
     * Allocation contexts.
     * 
     * @param allocation the Allocation model
     */
    protected void addInitialAllocations(Allocation allocation) {
        allocation.getAllocationContexts_Allocation().forEach(this::doAddAllocationContext);
    }

    /**
     * Adds an entry for the provided assembly context taking into account a stack
     * of assembly contexts capturing the assemblies of containing composite
     * components.
     * 
     * In case the assembly refers to a CompositeComponent the method recursively
     * descends to include mappings for the assemblies contained in the
     * CompositeComponent.
     * 
     * @param ctx          the assembly context to create a mapping for.
     * @param ctxHierarchy the assemblies of potential enclosing
     *                     CompositeComponents. Provide an empty list if none.
     * @param container    the simulated resource container to which the assembly
     *                     context is allocated.
     */
    protected void addAssemblyAllocation(AssemblyContext ctx, System system, List<AssemblyContext> ctxHierarchy,
            EntityReference<ResourceContainer> container) {
        List<AssemblyContext> baseHierarchy;
        if (ctxHierarchy.isEmpty()) {
            // We need to determine the assembly base path, in case the allocation refers to a
            // nested assembly.
            baseHierarchy = determineBaseAssemblyPath(ctx, system);
        } else {
            // In this case the add operation has been called recursively, therefore, the
            // ctxHierarchy already contains the appropriate base path
            baseHierarchy = new LinkedList<AssemblyContext>(ctxHierarchy);
            baseHierarchy.add(ctx);
        }
        allocationManager.allocateAssembly(new FQComponentID(baseHierarchy).getFQIDString(), container);

        var component = ctx.getEncapsulatedComponent__AssemblyContext();         
        if (component instanceof ComposedStructure) {
            var composite = (ComposedStructure) component;
            for (var compCtx : composite.getAssemblyContexts__ComposedStructure()) {
                addAssemblyAllocation(compCtx, system, baseHierarchy, container);
            }
        }
    }
    
    protected List<AssemblyContext> determineBaseAssemblyPath(AssemblyContext ctx, System system) {
        var parentStructure = ctx.getParentStructure__AssemblyContext();
        if (parentStructure instanceof System) {
            // In this case we do not have a nested component assembly
            return Collections.singletonList(ctx);
        } else {
            // In this case we unfortunately have to make sure, that the path is unique
            Graph<Entity, ?> graph = new DirectedAcyclicGraph<>(DefaultEdge.class);
            system.getAssemblyContexts__ComposedStructure().forEach(c -> 
                    appendAssemblyContextRecursively(system, c, graph));
            var pathLookup = new AllDirectedPaths<>(graph);
            var paths = pathLookup.getAllPaths(system, ctx, false, 100);
            if ((paths.size() > 1) && !(arePathsUnique(paths))) {
                LOGGER.error("Ambiguous assembly allocation. The following paths exist:");
                for (var path : paths) {
                    var pathStr = path.getVertexList().stream().map(Entity::getId).collect(Collectors.joining("->"));
                    LOGGER.error("Alternative: " + pathStr);
                }
                throw new IllegalStateException("Cannot determine unique path to nested assembly context " + ctx.getId());
            } else if (paths.isEmpty()) {
                throw new IllegalStateException("Could not determine any path from the system to the nested assembly context " + ctx.getId());
            } else {
                var vertexList = paths.get(0).getVertexList(); 
                return (List<AssemblyContext>) (List) vertexList.subList(1, vertexList.size());
            }
        }
    }
    
    private boolean arePathsUnique(List<? extends GraphPath<Entity, ?>> paths) {
        Set<String> uniquePaths = new HashSet<>();

        for (GraphPath<Entity, ?> path : paths) {
            String pathStr = path.getVertexList()
                .stream()
                .map(Entity::getId)
                .collect(Collectors.joining("-"));

            if (uniquePaths.contains(pathStr)) {
                return false;
            }

            uniquePaths.add(pathStr);
        }

        return true;
    }
    
    protected void appendAssemblyContextRecursively(Entity container, AssemblyContext context, Graph<Entity, ?> graph) {
        graph.addVertex(container);
        graph.addVertex(context);
        graph.addEdge(container, context);
        if (context.getEncapsulatedComponent__AssemblyContext() instanceof ComposedStructure) {
            ((ComposedStructure)context.getEncapsulatedComponent__AssemblyContext()).getAssemblyContexts__ComposedStructure()
                .forEach(ctx -> appendAssemblyContextRecursively(context, ctx, graph));
        }
    }

    /**
     * Removes the entry for the provided assembly context taking into account a
     * stack of assembly contexts capturing the assemblies of containing composite
     * components.
     * 
     * In case the assembly refers to a CompositeComponent the method recursively
     * descends to also remove mappings for the assemblies contained in the
     * CompositeComponent.
     * 
     * @param ctx          the assembly context to remove the mapping.
     * @param ctxHierarchy the assemblies of potential enclosing
     *                     CompositeComponents. Provide an empty list if none.
     */
    protected void removeAssemblyAllocation(AssemblyContext ctx, List<AssemblyContext> ctxHierarchy) {
        var hierarchy = ctxHierarchy;
        if (ctxHierarchy.isEmpty()) {
            allocationManager.deallocateAssembly(ctx.getId());
        } else {
            var newHierarchy = new LinkedList<AssemblyContext>(ctxHierarchy);
            newHierarchy.push(ctx);
            allocationManager.deallocateAssembly(new FQComponentID(newHierarchy).getFQIDString());
            hierarchy = newHierarchy;
        }

        if (ctx.getEncapsulatedComponent__AssemblyContext() instanceof CompositeComponent) {
            var composite = (CompositeComponent) ctx;
            for (var compCtx : composite.getAssemblyContexts__ComposedStructure()) {
                removeAssemblyAllocation(compCtx, hierarchy);
            }
        }
    }

    /**
     * This method delegates the processing of the AllocationContexts contained in
     * the notification to the supplied processor. If the notification is of _MANY
     * type the processor is called for every contained AllocationContext.
     * 
     * @param notification the EMF notification
     * @param extractor    a function which gets the changed object from the
     *                     notification.
     * @param processor    the consumer which processes a single AllocationContext
     *                     instance
     */
    @SuppressWarnings("unchecked")
    private void processNotification(Notification notification, Function<Notification, Object> extractor,
            Consumer<AllocationContext> processor) {
        assert notification.getFeature() == AllocationPackage.Literals.ALLOCATION__ALLOCATION_CONTEXTS_ALLOCATION;

        Object feature = notification.getNewValue();
        if (feature instanceof Collection) {
            ((Collection<AllocationContext>) feature).forEach(processor);
        } else {
            /*
             * If the new element is not an allocation context something about the model
             * changed. Then we will need to revisit this class again anyhow.
             */
            doAddAllocationContext((AllocationContext) feature);
        }
    }

    /**
     * Convenience method to add the provided allocation context.
     */
    private void doAddAllocationContext(AllocationContext ctx) {
        if (ctx.getAssemblyContext_AllocationContext() != null) {
            addAssemblyAllocation(ctx.getAssemblyContext_AllocationContext(), ctx.getAllocation_AllocationContext()
                .getSystem_Allocation(), Collections.emptyList(),
                    resourceContainerReferenceFactory.createCached(ctx.getResourceContainer_AllocationContext()));    
        } 
    }

    /**
     * Convenience method to remove the provided allocation context.
     */
    private void doRemoveAllocationContext(AllocationContext ctx) {
        if (ctx.getAssemblyContext_AllocationContext() != null) {
            removeAssemblyAllocation(ctx.getAssemblyContext_AllocationContext(), Collections.emptyList());
        }
    }

    /**
     * Convenience method to add the allocation context if present in the notification
     */
    private void checkAndAddAllocationContextFromNotification(Notification notification) {
        if (notification.getFeature() == AllocationPackage.Literals.ALLOCATION__ALLOCATION_CONTEXTS_ALLOCATION) {
            processNotification(notification, Notification::getNewValue, this::doAddAllocationContext);
        }
    }
    
    /**
     * Convenience method to remove the allocation context if present in the notification
     */
    private void checkAndRemoveAllocationContextFromNotification(Notification notification) {
        if (notification.getFeature() == AllocationPackage.Literals.ALLOCATION__ALLOCATION_CONTEXTS_ALLOCATION) {
            processNotification(notification, Notification::getOldValue, this::doRemoveAllocationContext);
        }
    }
}
