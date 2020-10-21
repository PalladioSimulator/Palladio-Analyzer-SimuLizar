package org.palladiosimulator.simulizar.entity;

import java.util.WeakHashMap;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.pcm.core.entity.Entity;

import com.google.common.collect.Iterators;

public class EntityReference<EntityType extends Entity> {
    public static abstract class AbstractEntityReferenceFactory<EntityType extends Entity>
            implements EntityReferenceFactory<EntityType> {

        @Override
        public EntityReference<EntityType> createCached(EntityType entity) {
            var ref = create(entity.getId());
            ref.elementCache.put(entity.eResource().getResourceSet(), entity);
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

    public EntityType getModelElement(PCMResourceSetPartition partition) {
        return elementCache.computeIfAbsent(partition.getResourceSet(), rs -> this.retrieveModelElement(partition));
    }

    public String getId() {
        return id;
    }

    protected EntityType retrieveModelElement(PCMResourceSetPartition partition) {
        return Iterators.filter(Iterators.filter(partition.getResourceSet()
            .getAllContents(), entityClass),
                it -> it.getId()
                    .equals(id))
            .next();
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

}
