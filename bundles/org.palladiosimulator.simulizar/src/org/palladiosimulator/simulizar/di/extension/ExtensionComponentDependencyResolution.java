package org.palladiosimulator.simulizar.di.extension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent;
import org.palladiosimulator.simulizar.di.core.extension.ExtensionComponent.Factory;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Streams;

import dagger.Component;

public class ExtensionComponentDependencyResolution {
    /**
     * Static LOGGER of this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ExtensionComponentDependencyResolution.class);

    private Set<Object> bootStrappingComponents;
    private Set<Factory> extensionComponentFactories;
    
    private Set<ExtensionComponent> extensionComponents;

    public ExtensionComponentDependencyResolution(Set<Object> bootStrappingComponents,
            Set<ExtensionComponent.Factory> extensionComponentFactories) {
        this.bootStrappingComponents = bootStrappingComponents;
        this.extensionComponentFactories = extensionComponentFactories;
    }
    
    public Set<ExtensionComponent> getExtensionComponents() {
        if (extensionComponents == null) {
            synchronized (this) {
                if (extensionComponents == null) {
                    extensionComponents = initializeExtensionComponentSet();
                }
            }
        }
        return extensionComponents;
    }

    @SuppressWarnings("unchecked")
    private Set<ExtensionComponent> initializeExtensionComponentSet() {
        Map<String, Supplier<?>> componentProviders = new HashMap<>();
        Multimap<Supplier<?>, String> supplierToSupportedTypes = HashMultimap.create();
        for (var comp : bootStrappingComponents) {
            Streams.stream(GenericComponentFactory.getClassHierarchy(comp.getClass()))
                .filter(cls -> cls.getAnnotation(Component.class) != null)
                .forEach(cls -> componentProviders.put(cls.getName(), () -> comp));
        }
        var graphBuilder = DefaultDirectedGraph.<String, DefaultEdge>createBuilder(DefaultEdge.class);
        var genericFactories = extensionComponentFactories.stream()
            .map(fact -> new GenericComponentFactory<>(fact))
            .collect(Collectors.toSet());

        // Build up fully fledged dependency graph
        genericFactories.forEach(gcf -> {
            var requirements = gcf.getUnfullfilledRequirements();
            gcf.getProvidedComponentTypes().forEach(pct -> {
                graphBuilder.addVertex(pct.getName());
                if (!componentProviders.containsKey(pct.getName())) {
                    componentProviders.put(pct.getName(), gcf);
                    // Store the backwards relationship to be able to merge the nodes again
                    supplierToSupportedTypes.put(gcf, pct.getName()); 
                    requirements.forEach(req -> {
                        graphBuilder.addVertex(req.getName());
                        graphBuilder.addEdge(req.getName(), pct.getName());
                    });
                } else if (componentProviders.get(pct.getName()) instanceof GenericComponentFactory) {
                    LOGGER.warn("Duplicate registration for " + pct.getName() + " skipping second one.");
                } // Otherwise the component was already bootstrapped, which is fine.
            });
        });

        // Remove extensions which depend on components for which no factory or bootstrapping component is supplied
        var componentGraph = graphBuilder.build();
        var missingComponents = componentGraph.vertexSet()
            .stream()
            .filter(v -> !componentProviders.containsKey(v))
            .collect(Collectors.toSet());
        var unfullfillableComponents = 
                Streams.stream(new BreadthFirstIterator<>(componentGraph, missingComponents)).collect(Collectors.toSet());
        unfullfillableComponents.forEach(c -> {
            componentGraph.removeVertex(c);
            var provider = componentProviders.remove(c);
            if (provider != null) {
                supplierToSupportedTypes.removeAll(provider);
            }
        });
        
        // Merge nodes which are provided by the same component factory (in case of inheritance)
        supplierToSupportedTypes.keySet().forEach(supplier -> {
            var providedTypes = supplierToSupportedTypes.get(supplier);
            // We create a new node for the actual supplier, by prepending one of the provided types
            var supplierIdentifier = supplier.getClass().getName() + "<" + providedTypes.iterator().next() + ">"; 
            componentGraph.addVertex(supplierIdentifier);
            componentProviders.put(supplierIdentifier, supplier);
            providedTypes.forEach(pt -> {
                var outgoingEdges = ImmutableSet.copyOf(componentGraph.outgoingEdgesOf(pt));
                outgoingEdges.forEach(edge -> {
                    var target = componentGraph.getEdgeTarget(edge);
                    if (!supplierIdentifier.equals(target)) {
                        componentGraph.addEdge(supplierIdentifier, target);
                    }
                });
                var incomingEdges = ImmutableSet.copyOf(componentGraph.incomingEdgesOf(pt));
                incomingEdges.forEach(edge -> {
                    var source = componentGraph.getEdgeSource(edge);
                    if (!supplierIdentifier.equals(source)) {
                        componentGraph.addEdge(source, supplierIdentifier);
                    }
                });
                componentGraph.removeAllEdges(outgoingEdges);
                componentGraph.removeAllEdges(incomingEdges);
                componentGraph.removeVertex(pt);
            });
        });

        // After merging the provided types to the actual suppliers we can check for cycles which are normally detected already by dagger
        // but could occur through sub-classing of components
        var cycleDetector = new CycleDetector<>(componentGraph);
        if (cycleDetector.detectCycles()) {
            var cycle = cycleDetector.findCycles();
            LOGGER.error("Detected dependency cycle:");
            cycle.forEach(cls -> LOGGER.error("Component: " + cls.toString()));
            LOGGER.error("End of dependency cycle");
            throw new IllegalStateException("Failed due to a cyclic dependency. Check the Log.");
        }
        
        var resultingComponents = new HashSet<ExtensionComponent>(
                bootStrappingComponents.size() + extensionComponentFactories.size());
        
        (new TopologicalOrderIterator<>(componentGraph)).forEachRemaining(cls -> {
            // The topologic iterator guarantees us, that every node of the incoming edges has already been visited,
            // and consequently the respective component has been instantiated.
            componentGraph.incomingEdgesOf(cls)
                .forEach(edge -> {
                    var dependency = componentGraph.getEdgeSource(edge);
                    //If the element has dependencies, its a GenericComponentFactory and we can safely cast
                    ((GenericComponentFactory<Object>) componentProviders.get(cls))
                        .fulfillRequirement(componentProviders.get(dependency)); 
                });
            
            var component = componentProviders.get(cls).get();
            componentProviders.put(cls, () -> component);
            if ((component instanceof ExtensionComponent) && (!bootStrappingComponents.contains(component))) {
                resultingComponents.add((ExtensionComponent) component);
            }
        });
        
        // We do not longer need to store the following references
        extensionComponentFactories = null;
        bootStrappingComponents = null;

        return resultingComponents;
    }

}
