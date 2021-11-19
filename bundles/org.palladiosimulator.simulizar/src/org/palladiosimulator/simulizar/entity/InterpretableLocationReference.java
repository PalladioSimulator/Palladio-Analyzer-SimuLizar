package org.palladiosimulator.simulizar.entity;

/**
 * This interface is used to point to a generic element within the simulated world. The element
 * might be characterized by multiple model elements, as e.g. the operation of a component instance
 * (assembly) is identified by the Assembly Context as well as the Signature of the Operation
 * Interface.
 * 
 * We use this reference to inform listeners about events occurring during simulation.
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpretableLocationReference {

    /**
     * Checks whether the referenced location is represented by the union of the provided
     * references.
     * 
     * @return true, if the location is the same
     */
    boolean isLocationIdentifiedBy(EntityReference<?>... entityReferences);

    /**
     * Provides an identifier, which is derived based on all of the representing entities. The
     * identifier can be used as a lookup key for storing related elements is hash map like data
     * structs.
     * 
     * @return a stable identifier, uniquely determined based on the representing elements.
     */
    String getLocationIdentifier();

}
