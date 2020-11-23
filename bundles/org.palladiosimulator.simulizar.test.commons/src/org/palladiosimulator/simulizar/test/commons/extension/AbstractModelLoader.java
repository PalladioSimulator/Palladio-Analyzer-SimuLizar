package org.palladiosimulator.simulizar.test.commons.extension;

import static org.junit.platform.commons.support.AnnotationSupport.findRepeatableAnnotations;

import java.lang.annotation.Annotation;
import java.util.List;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.simulizar.test.commons.util.ModelLoader;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;
import de.uka.ipd.sdq.workflow.mdsd.blackboard.ResourceSetPartition;

public abstract class AbstractModelLoader<ConfigurationAnnotationType extends Annotation> implements ModelLoader {
    protected final List<ConfigurationAnnotationType> annotations;
    protected final ExtensionContext context;
    
    public AbstractModelLoader(ExtensionContext context, Class<ConfigurationAnnotationType> annotationClass) {
        annotations = findRepeatableAnnotations(context.getElement(), annotationClass);
        this.context = context;
    }
    
    protected void addOrMergePartitionIntoBlackboard(ResourceSetPartition partition, String partitionId, MDSDBlackboard blackboard) {
        if (blackboard.hasPartition(partitionId)) {
            var existingPart = blackboard.getPartition(partitionId);
            existingPart.getResourceSet()
                .getResources()
                .addAll(partition.getResourceSet()
                    .getResources());
        } else {
            blackboard.addPartition(partitionId, partition);
        }   
    }

}
