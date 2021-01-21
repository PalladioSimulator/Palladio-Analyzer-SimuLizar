package org.palladiosimulator.simulizar.di.extension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;
import org.palladiosimulator.simulizar.di.extension.ExtensionComponent.Factory;

import com.google.common.collect.Streams;

import dagger.Component;

public class ExtensionComponentDependencyResolution {
    /**
     * Static LOGGER of this class.
     */
    private static final Logger LOGGER = Logger.getLogger(ExtensionComponentDependencyResolution.class);

    private final Set<Object> bootStrappingComponents;
    private final Set<Factory> extensionComponentFactories;

    @Inject
    public ExtensionComponentDependencyResolution(@RegisteredComponent Set<Object> bootStrappingComponents,
            Set<ExtensionComponent.Factory> extensionComponentFactories) {
        this.bootStrappingComponents = bootStrappingComponents;
        this.extensionComponentFactories = extensionComponentFactories;
    }

    @SuppressWarnings("unchecked")
    public Set<? extends ExtensionComponent<?>> initializeExtensionComponentSet() {
        Map<Class<?>, Supplier<?>> componentProviders = new HashMap<>();
        for (var comp : bootStrappingComponents) {
            Streams.stream(GenericComponentFactory.getClassHierarchy(comp.getClass()))
                .filter(cls -> cls.getAnnotation(Component.class) != null)
                .forEach(cls -> componentProviders.put(cls, () -> comp));
        }
        var graphBuilder = DefaultDirectedGraph.<Class<?>, DefaultEdge> createBuilder(DefaultEdge.class);
        var genericFactories = extensionComponentFactories.stream()
            .map(fact -> new GenericComponentFactory<>(fact))
            .collect(Collectors.toSet());

        genericFactories.forEach(gcf -> {
            var provClass = gcf.getProvidedComponentType();
            componentProviders.put(provClass, gcf);
            graphBuilder.addVertex(provClass);
            gcf.getUnfullfilledRequirements()
                .forEach(req -> {
                    graphBuilder.addVertex(req);
                    graphBuilder.addEdge(provClass, req);
                });
        });

        var componentGraph = graphBuilder.build();
        var cycleDetector = new CycleDetector<>(componentGraph);

        if (cycleDetector.detectCycles()) {
            var cycle = cycleDetector.findCycles();
            LOGGER.error("Detected dependency cycle:");
            cycle.forEach(cls -> LOGGER.error("Component: " + cls.toString()));
            LOGGER.error("End of dependency cycle");
            throw new IllegalStateException("Failed due to a cyclic dependency. Check the Log.");
        }
        var resultingComponents = new HashSet<ExtensionComponent<?>>(
                bootStrappingComponents.size() + extensionComponentFactories.size());
        (new TopologicalOrderIterator<>(componentGraph)).forEachRemaining(cls -> {
            componentGraph.outgoingEdgesOf(cls)
                .forEach(edge -> {
                    var dependencyTarget = componentGraph.getEdgeTarget(edge);
                    ((GenericComponentFactory<Object>) componentProviders.get(cls)).fulfillRequirement(
                            (Class<Object>) dependencyTarget, () -> componentProviders.get(dependencyTarget)
                                .get());
                });
            var component = componentProviders.get(cls)
                .get();
            componentProviders.put(cls, () -> component);
            if (component instanceof ExtensionComponent) resultingComponents.add((ExtensionComponent<?>)component);
        });

        return resultingComponents;
    }

}
