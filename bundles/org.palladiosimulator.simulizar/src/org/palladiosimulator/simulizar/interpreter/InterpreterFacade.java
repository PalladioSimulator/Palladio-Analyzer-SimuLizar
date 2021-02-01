package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EObject;
import org.palladiosimulator.simulizar.entity.EntityReference;

/**
 * The InterpreterFacade consitutes the entry point for model-traversal based interpretation.
 * 
 * It hides the concrete visitor implementation (e. g. EMF switches).
 * 
 * @author Sebastian Krach
 *
 */
public interface InterpreterFacade {

    /**
     * Submit the given object for interpretation. The call will return, once the interpretation has
     * finished.
     * 
     * If the model element is contained in the blackboard,
     * {@link InterpreterFacade#submit(EntityReference)} should be preferred, as it checks, whether
     * the entity is still resolvable or has been removed between submission and start of
     * interpretation.
     * 
     * @param object
     *            the model element to interpret
     */
    void submit(EObject object);

    /**
     * Submit the referenced entity for interpretation. The entity will be resolved against the
     * default PCM blackboard partition. This method should be preferred, as it checks, whether the
     * entity is still resolvable or has been removed between submission and start of
     * interpretation.
     * 
     * The call will return, once the interpretation has finished.
     * 
     * @param object
     *            the reference to the model element to interpret.
     */
    void submit(EntityReference<?> object);

}
