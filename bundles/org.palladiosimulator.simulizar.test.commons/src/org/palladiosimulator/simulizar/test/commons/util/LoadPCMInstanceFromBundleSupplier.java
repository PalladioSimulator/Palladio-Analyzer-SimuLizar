package org.palladiosimulator.simulizar.test.commons.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.palladiosimulator.analyzer.workflow.core.blackboard.PCMResourceSetPartition;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;

public class LoadPCMInstanceFromBundleSupplier implements Supplier<PCMResourceSetPartition> {

    final LoadPCMInstanceFromBundle annotation;

    public LoadPCMInstanceFromBundleSupplier(ExtensionContext context) {
        annotation = context.getElement()
            .flatMap(ae -> Optional.ofNullable(ae.getAnnotation(LoadPCMInstanceFromBundle.class)))
            .orElseThrow(() -> new IllegalArgumentException(
                    "Invalid annotation configuration. No annotation of type LoadPCMInstanceFromBundle found."));
    }

    @Override
    public PCMResourceSetPartition get() {
        var result = new PCMResourceSetPartition();
        var baseUrl = Path.forPosix(annotation.basePath());
        var modelFiles = Arrays.asList(annotation.modelFiles());
        var bundle = Platform.getBundle(annotation.bundleName());

        for (var file : modelFiles) {
            var path = baseUrl.append(file);
            var url = FileLocator.find(bundle, path);
            try {
                var uri = URI.createURI(url.toURI()
                    .toString());
                var res = result.getResourceSet()
                    .createResource(uri);
                res.load(FileLocator.openStream(bundle, path, true), Collections.emptyMap());
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        EcoreUtil.resolveAll(result.getResourceSet());

        return result;
    }

}
