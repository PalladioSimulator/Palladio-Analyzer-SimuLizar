package org.palladiosimulator.simulizar.test.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.palladiosimulator.analyzer.workflow.ConstantsContainer;
import org.palladiosimulator.simulizar.test.commons.extension.ExtensibleLoadModelExtension;
import org.palladiosimulator.simulizar.test.commons.extension.SimuLizarTestParameterProvider;
import org.palladiosimulator.simulizar.test.commons.extension.modelloader.FromSupplierModelLoader;
import org.palladiosimulator.simulizar.test.commons.util.PartitionSupplier;

/**
 * Uses the provided Supplier to retrieve the pcm instance for running the test. It needs to provide
 * a constructor either without parameters or accepting a JUnit extension context as parameter.
 * 
 * By default, a valid but empty pcm instance is provided.
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@ExtendWith({ ExtensibleLoadModelExtension.class, SimuLizarTestParameterProvider.class })
@UseModelLoader(FromSupplierModelLoader.class)
@Repeatable(PCMInstanceFromSupplier.Suppliers.class)
public @interface PCMInstanceFromSupplier {
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
    public @interface Suppliers {
        PCMInstanceFromSupplier[] value();
    }
    
    Class<? extends PartitionSupplier> value();
    
    String partitionId() default ConstantsContainer.DEFAULT_PCM_INSTANCE_PARTITION_ID;
}