package org.palladiosimulator.simulizar.entity;

import org.palladiosimulator.pcm.core.entity.Entity;

public interface EntityReferenceFactory<EntityType extends Entity> {
    
    EntityReference<EntityType> create(String id);
    
    EntityReference<EntityType> createCached(EntityType entity);

}
