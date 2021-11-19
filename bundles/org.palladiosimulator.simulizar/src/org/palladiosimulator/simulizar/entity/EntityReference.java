package org.palladiosimulator.simulizar.entity;

import java.util.Iterator;
import java.util.Optional;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.entity.Entity;

import com.google.common.collect.Iterators;

/**
 * The entity reference class serves as a type safe model element pointer. As the blackboard
 * partitions reflect the current state of the global model at the point in time when the
 * interpretation started, there can be several model element instances pointing to the same entity.
 * 
 * Storing model elements directly can lead to memory leaks, as older resource sets which are not
 * referenced by interpreters anymore are not properly cleaned up. The entity model reference allows
 * to access the model element directly given a {@link PCMResourceSetPartition}. It encapsulates the
 * required lookup logic. Once a lookup is done, the model element is cached, but may be cleaned up
 * by the garbage collector.
 * 
 * Using an entity reference has the advantage of giving some semantics and type-safety to a
 * {@link Entity#getId()}. It can be stored, and checked for equality using
 * {@link Object#equals(Object)} and {@link Object#hashCode()}.
 * 
 * NOTE: In order to provide a more efficient model element lookup mechanism a specialized
 * implementation can be provided. The default behavior is to check the ID of every single Entity in
 * the provided partition until a suitable match is found.
 *
 * @param <EntityType>
 *            the type of the referenced model entity.
 */
public class EntityReference<EntityType extends Entity> implements InterpretableLocationReference {
    public static abstract class AbstractEntityReferenceFactory<EntityType extends Entity>
            implements EntityReferenceFactory<EntityType> {

        @Override
        public EntityReference<EntityType> createCached(EntityType entity) {
            var ref = create(entity.getId());
            ref.elementCache.put(entity.eResource()
                .getResourceSet(), entity);
            return ref;
        }
    }

    private final String id;
    private Class<EntityType> entityClass;
    private WeakHashMap<ResourceSet, EntityType> elementCache;

    EntityReference(String id, Class<EntityType> entityClass) {
        this.id = id;
        this.entityClass = entityClass;
        elementCache = new WeakHashMap<>();
    }

    /**
     * Gets the model element of the referenced entity from the given resource set partition.
     */
    public EntityType getModelElement(PCMResourceSetPartition partition) {
        return getModelElementIfPresent(partition)
            .orElseThrow(() -> new IllegalStateException("Referenced element not present in resource set partition."));
    }
    
    /**
     * Gets the model element of the referenced entity from the given resource set partition.
     */
    public Optional<EntityType> getModelElementIfPresent(PCMResourceSetPartition partition) {
        var result = Optional.ofNullable(elementCache.get(partition.getResourceSet()));
        
        if (result.isEmpty()) {
            var iter = retrieveModelElements(partition);
            if (iter.hasNext()) {
                result = Optional.of(iter.next());
                elementCache.put(partition.getResourceSet(), result.get());
            }
        }
            
        return result;
    }

    public String getId() {
        return id;
    }
    
    protected Iterator<EntityType> retrieveModelElements(PCMResourceSetPartition partition) {
        return Iterators.filter(Iterators.filter(partition.getResourceSet()
                .getAllContents(), entityClass),
                    it -> it.getId()
                        .equals(id));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((entityClass == null) ? 0 : entityClass.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntityReference other = (EntityReference) obj;
        if (entityClass == null) {
            if (other.entityClass != null)
                return false;
        } else if (!entityClass.equals(other.entityClass))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public boolean isLocationIdentifiedBy(EntityReference<?>... entityReferences) {
        if (entityReferences == null || entityReferences.length != 1) {
            return false;
        } else {
            return entityReferences[0].equals(this);
        }
    }

    @Override
    public String getLocationIdentifier() {
        return this.id;
    }

}
