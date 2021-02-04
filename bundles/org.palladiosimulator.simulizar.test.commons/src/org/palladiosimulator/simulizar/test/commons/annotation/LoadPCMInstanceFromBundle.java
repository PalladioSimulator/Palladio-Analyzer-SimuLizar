package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.simulizar.test.commons.extension.ExtensibleLoadModelExtension;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestParameterProvider;
import org.palladiosimulator.simulizar.test.commons.extension.modelloader.FromBundleModelLoader;

@Retention(RUNTIME)
@Target(METHOD)
@ExtendWith({ ExtensibleLoadModelExtension.class, SimuLizarTestParameterProvider.class })
@UseModelLoader(FromBundleModelLoader.class)
@Repeatable(LoadPCMInstanceFromBundle.LoadPCMInstanceFromBundles.class)
public @interface LoadPCMInstanceFromBundle {
    @Retention(RUNTIME)
    @Target(METHOD)
    public @interface LoadPCMInstanceFromBundles {
        LoadPCMInstanceFromBundle[] value();
    }

    String bundleName();

    String basePath() default "";

    String[] modelFiles();

    String partitionId() default ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID;

}
