package org.palladiosimulator.simulizar.core.entity;

import org.palladiosimulator.pcm.core.entity.Entity;

/**
 * The factory creates a new entity reference given either a resolved model element or by a given
 * id. If the reference is created based solely on the id, there is not necessarily a check in
 * place, whether a model element of the appropriate type actually exists.
 *
 * @param <EntityType>
 *            the model element type referenced by the constructed references.
 */
public interface EntityReferenceFactory<EntityType extends Entity> {

    /**
     * Creates a new EntityReference for an entity identified by the given id.
     * 
     * @param id
     *            the id of the model element. See {@link Entity#getId()}
     * @return a new reference
     */
    EntityReference<EntityType> create(String id);

    /**
     * Creates a new EntityReference which matches the provided model element. The resolved entity
     * is cached by will not prevent garbage collection from cleaning it up.
     * 
     * @param entity
     *            a resolved model element.
     * @return a new reference
     */
    EntityReference<EntityType> createCached(EntityType entity);

}
