package org.palladiosimulator.simulizar.test.commons.extension.modelloader;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.extension.AbstractModelLoader;

import de.uka.ipd.sdq.workflow.mdsd.blackboard.MDSDBlackboard;

public class FromBundleModelLoader extends AbstractModelLoader<LoadPCMInstanceFromBundle> {
    public FromBundleModelLoader(ExtensionContext context) {
        super(context, LoadPCMInstanceFromBundle.class);
    }

    @Override
    public void loadModel(MDSDBlackboard blackboard) {
        for (var annotation : annotations) {
            var partition = new PCMResourceSetPartition();
            var baseUrl = Path.forPosix(annotation.basePath());
            var modelFiles = Arrays.asList(annotation.modelFiles());
            var bundle = Platform.getBundle(annotation.bundleName());

            for (var file : modelFiles) {
                var path = baseUrl.append(file);
                var url = FileLocator.find(bundle, path);
                try {
                    var uri = URI.createURI(url.toURI()
                        .toString());
                    var res = partition.getResourceSet()
                        .createResource(uri);
                    res.load(FileLocator.openStream(bundle, path, true), Collections.emptyMap());
                } catch (IOException | URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            }
            EcoreUtil.resolveAll(partition.getResourceSet());

            addOrMergePartitionIntoBlackboard(partition, annotation.partitionId(), blackboard);
        }
    }

}
