package org.palladiosimulator.simulizar.test.commons.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.palladiosimulator.simulizar.test.commons.util.LoadPCMInstanceFromBundleSupplier;

@Retention(RUNTIME)
@Target(METHOD)
@PCMInstanceFromSupplier(LoadPCMInstanceFromBundleSupplier.class)
public @interface LoadPCMInstanceFromBundle {

    String bundleName();
    
    String basePath() default "";
    
    String[] modelFiles();
    
}
