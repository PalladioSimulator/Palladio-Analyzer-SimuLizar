package org.palladiosimulator.simulizar.test.commons.extension.modelloader;

import java.util.Arrays;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadModelFromURI;
import org.palladiosimulator.simulizar.test.commons.extension.AbstractModelLoader;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class FromURIModelLoader extends AbstractModelLoader<LoadModelFromURI> {
    public FromURIModelLoader(ExtensionContext context) {
        super(context, LoadModelFromURI.class);
    }

    @Override
    public void loadModel(MDSDBlackboard blackboard) {
        for (var annotation : annotations) {
            var partition = new PCMResourceSetPartition();
            Arrays.asList(annotation.value())
                .stream()
                .map(s -> URI.createURI(s))
                .forEach(uri -> partition.getResourceSet()
                    .getResource(uri, true));

            EcoreUtil.resolveAll(partition.getResourceSet());

            addOrMergePartitionIntoBlackboard(partition, annotation.partitionId(), blackboard);
        }
    }

}
