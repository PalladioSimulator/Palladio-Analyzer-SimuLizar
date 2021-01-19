package org.palladiosimulator.simulizar.interpreter;

import org.eclipse.emf.ecore.EObject;

public interface ModelInterpreter<ElementType extends EObject> {
    
    void interpret(ElementType element);
    
    public interface WithResult<ElementType extends EObject, ResultType> extends ModelInterpreter<ElementType> {
        ResultType interpretAndGet(ElementType element);
        
        default void interpret(ElementType element) {
            interpretAndGet(element);            
        }
    }

}
